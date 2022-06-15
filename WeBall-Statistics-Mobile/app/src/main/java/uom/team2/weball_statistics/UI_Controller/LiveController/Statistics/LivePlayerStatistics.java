package uom.team2.weball_statistics.UI_Controller.LiveController.Statistics;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.DAOLiveMatchService;
import uom.team2.weball_statistics.Service.DAOLivePlayerStatistics;
import uom.team2.weball_statistics.UIFactory.LayoutFactory;
import uom.team2.weball_statistics.databinding.FragmentLivePlayerStatisticsBinding;
import uom.team2.weball_statistics.utils.Utils;

public class LivePlayerStatistics extends Fragment {
    private final ArrayList<View> teamLandlordPlayerViews = new ArrayList<>();
    private final ArrayList<View> teamGuestPlayerViews = new ArrayList<>();
    // you need to have a list of data that you want the spinner to display
    private final ArrayList<String> spinnerArray = new ArrayList<String>();
    private final boolean dataRetrieved = false;
    private final int spinnerItemSelected = 0;
    private ArrayList<Player> teamLandlordPlayers = new ArrayList<>();
    private ArrayList<Player> teamGuestPlayers = new ArrayList<>();
    private Match match;
    private Team teamLandlord;
    private Team teamGuest;
    private boolean teamSelected = true;
    private ProgressDialog progress;
    private int matchId;
    private int teamLandlordId;
    private int teamGuestId;
    private FragmentLivePlayerStatisticsBinding binding;
    private HashMap<String, View> mapOfStatistics;
    private int playerSelectedId = -1;

    public LivePlayerStatistics() {
        // Required empty public constructor
    }

    public static LivePlayerStatistics newInstance() {
        LivePlayerStatistics fragment = new LivePlayerStatistics();
        return fragment;
    }

    public void addProgressBars(LinearLayout progressBarContainer) {
        String[] statisticsArray = Utils.getStringArray(getContext(), R.array.player_statistics);
        for (String statName : statisticsArray) {
            View progressBarLayout = LayoutFactory.createProgressBarLayout(this, statName);
            progressBarContainer.addView(progressBarLayout);
            statName = statName.replace(" ", "_").toLowerCase();
            progressBarLayout.setTag(statName);
            mapOfStatistics.put(statName, progressBarLayout);
        }
    }

    public void changeTeam(int index) {

        ArrayList<View> tempViews = new ArrayList<>();
        ArrayList<Player> tempPlayers = new ArrayList<>();
        Team tempTeam = null;

        if (index == 0) {
            tempViews = teamLandlordPlayerViews;
            tempPlayers = teamLandlordPlayers;
            tempTeam = teamLandlord;
            teamSelected = true;
        } else {
            tempViews = teamGuestPlayerViews;
            tempPlayers = teamGuestPlayers;
            tempTeam = teamGuest;
            teamSelected = false;
        }

        ArrayList<Player> finalTempPlayers = tempPlayers;
        ArrayList<View> finalTempViews = tempViews;

        Team finalTempTeam = tempTeam;
        if (LivePlayerStatistics.this.getActivity() != null && LivePlayerStatistics.this.isAdded()) {
            LivePlayerStatistics.this.requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        UIHandler.updateTeamImage(LivePlayerStatistics.this, finalTempTeam, binding.header.teamImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    autoSelectPlayer(finalTempPlayers.get(0));
                    addPlayers(finalTempViews);
                    finalTempViews.get(0).setBackgroundColor(Utils.getColor(LivePlayerStatistics.this.getContext(), R.color.alt_blue));
                    changePlayer(finalTempViews);
                }

            });
        }
    }

    public void addSpinnerListener() {
        binding.header.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                changeTeam(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void fillSpinner(ArrayList<String> spinnerArray) {
        if (LivePlayerStatistics.this.getActivity() != null && LivePlayerStatistics.this.isAdded()) {
            this.requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            LivePlayerStatistics.this.getContext(), android.R.layout.simple_spinner_item, spinnerArray);

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.header.spinner.setAdapter(adapter);

                }
            });
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapOfStatistics = new HashMap<>();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addProgressBars(binding.progressbarContainer);

        spinnerArray.add(teamLandlord.getTeamName());
        spinnerArray.add(teamGuest.getTeamName());
        fillSpinner(spinnerArray);

        addSpinnerListener();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        match = (Match) bundle.getSerializable("match");
        teamLandlord = (Team) bundle.getSerializable("teamLandlord");
        teamGuest = (Team) bundle.getSerializable("teamGuest");
        matchId = match.getId();
        teamLandlordId = teamLandlord.getId();
        teamGuestId = teamGuest.getId();
        teamLandlordPlayers = teamLandlord.getTeamPlayers();
        teamGuestPlayers = teamGuest.getTeamPlayers();
        binding = FragmentLivePlayerStatisticsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        DAOLiveMatchService.getInstance().clockDataListener(this, binding.header.clock.clockText, matchId);

        loadInitialTeamsPlayers();
        loadTeamPlayers();

        try {
            UIHandler.updateTeamImage(LivePlayerStatistics.this, teamLandlord, binding.header.teamImage);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void loadInitialTeamsPlayers() {

        for (Player player : teamLandlordPlayers) {
            DAOLivePlayerStatistics.getInstance().initializeTable(matchId, player.getId());
        }
        createPlayers(teamLandlordPlayers, teamLandlordPlayerViews);
        autoSelectPlayer(teamLandlordPlayers.get(0));

        DAOLivePlayerStatistics.getInstance().setDataChangeListener(LivePlayerStatistics.this, matchId, teamLandlordId, teamLandlordPlayers.get(0).getId());
        DAOLiveMatchService.getInstance().setDataListenerForPlayer(LivePlayerStatistics.this, matchId, teamLandlordId);

        if (LivePlayerStatistics.this.getActivity() != null && LivePlayerStatistics.this.isAdded()) {
            LivePlayerStatistics.this.requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    addPlayers(teamLandlordPlayerViews);
                    changePlayer(teamLandlordPlayerViews);
                    Utils.changeBackgroundColorInView(getContext(), teamLandlordPlayerViews.get(0), R.color.alt_blue);
                }
            });
        }
    }

    public void loadTeamPlayers() {
        for (Player player : teamGuestPlayers) {
            DAOLivePlayerStatistics.getInstance().initializeTable(matchId, player.getId());
        }
        createPlayers(teamGuestPlayers, teamGuestPlayerViews);
    }


    public void addPlayers(ArrayList<View> views) {
        if (LivePlayerStatistics.this.getActivity() != null && LivePlayerStatistics.this.isAdded()) {
            this.requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    LinearLayout layout = binding.horizontalPlayerContainer.cardview.findViewById(R.id.horizontal_players);
                    layout.removeAllViews();
                    for (View v : views) {
                        v.setBackgroundColor(Utils.getColor(LivePlayerStatistics.this.getContext(), R.color.grayback));
                        layout.addView(v);
                    }
                }
            });
        }
    }

    public void createPlayers(ArrayList<Player> players, ArrayList<View> views) {
        for (Player player : players) {
            try {
                View playerView = LayoutFactory.createPayerImageLayout(LivePlayerStatistics.this, player.getName(), player.getImagePath());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                );
                if (LivePlayerStatistics.this.getActivity() != null && LivePlayerStatistics.this.isAdded()) {
                    this.requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            params.setMargins(5, 2, 5, 0);
                            playerView.setLayoutParams(params);
                        }
                    });
                    views.add(playerView);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void autoSelectPlayer(Player player) {
        int index = 0;
        int playerSelectedId = teamSelected ? teamLandlordPlayers.get(index).getId() : teamGuestPlayers.get(index).getId();
        int teamSelectedId = teamSelected ? teamLandlordId : teamGuestId;

        DAOLivePlayerStatistics.getInstance().setDataChangeListener(LivePlayerStatistics.this, matchId, teamSelectedId, playerSelectedId);
        DAOLiveMatchService.getInstance().setDataListenerForPlayer(LivePlayerStatistics.this, matchId, teamSelectedId);

        try {
            UIHandler.updateSelectedPlayerImageLayout(LivePlayerStatistics.this,
                    player.getImagePath(),
                    player.getName(),
                    binding.selectedPlayerLayout.getRoot());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void changePlayer(ArrayList<View> views) {

        for (View playerView : views) {
            playerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int index = views.indexOf(playerView);

                    int temp = teamSelected ? teamLandlordPlayers.get(index).getId() : teamGuestPlayers.get(index).getId();

                    if (temp == playerSelectedId) {
                        return;
                    }

                    ArrayList<Player> tempPlayers = teamSelected ? teamLandlordPlayers : teamGuestPlayers;

                    playerSelectedId = temp;

                    int teamSelectedId = teamSelected ? teamLandlordId : teamGuestId;

                    DAOLivePlayerStatistics.getInstance().setDataChangeListener(LivePlayerStatistics.this, match.getId(), teamSelectedId, playerSelectedId);
                    DAOLiveMatchService.getInstance().setDataListenerForPlayer(LivePlayerStatistics.this, match.getId(), teamSelectedId);

                    Utils.changeBackgroundColorInView(getContext(), playerView, R.color.alt_blue);


                    try {
                        UIHandler.updateSelectedPlayerImageLayout(LivePlayerStatistics.this,
                                tempPlayers.get(index).getImagePath(),
                                tempPlayers.get(index).getName(),
                                binding.selectedPlayerLayout.getRoot());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (View other : views) {
                        if (!other.equals(playerView)) {
                            Utils.changeBackgroundColorInView(getContext(), other, R.color.statistics_background);
                        }
                    }
                }
            });
        }

    }

    public HashMap<String, View> getMapOfStatistics() {
        return mapOfStatistics;
    }
}