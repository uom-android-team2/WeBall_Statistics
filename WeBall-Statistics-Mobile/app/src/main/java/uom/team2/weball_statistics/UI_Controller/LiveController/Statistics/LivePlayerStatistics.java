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
import java.util.List;

import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.DAOLivePlayerStatistics;
import uom.team2.weball_statistics.Service.DAOLiveTeamService;
import uom.team2.weball_statistics.Service.PlayerService;
import uom.team2.weball_statistics.UIFactory.LayoutFactory;
import uom.team2.weball_statistics.databinding.FragmentLivePlayerStatisticsBinding;
import uom.team2.weball_statistics.utils.Utils;

public class LivePlayerStatistics extends Fragment {

    private final ArrayList<View> team1PlayerViews = new ArrayList<>();
    private final ArrayList<View> team2PlayerViews = new ArrayList<>();
    private final boolean teamSelected = true;
    // you need to have a list of data that you want the spinner to display
    List<String> spinnerArray = new ArrayList<String>();
    ProgressDialog progress;
    private ArrayList<Player> team2Players = new ArrayList<>();
    private ArrayList<Player> team1Players = new ArrayList<>();
    private FragmentLivePlayerStatisticsBinding binding;
    private HashMap<String, View> mapOfStatistics;
    private Team team1;
    private Team team2;
    private boolean dataRetrieved = false;

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
            tempViews = team1PlayerViews;
            tempPlayers = team1Players;
            tempTeam = team1;
        } else {
            tempViews = team2PlayerViews;
            tempPlayers = team2Players;
            tempTeam = team2;
        }

        final ArrayList<Player> finalTempPlayers = tempPlayers;
        final ArrayList<View> finalTempViews = tempViews;

        Team finalTempTeam = tempTeam;
        LivePlayerStatistics.this.requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (finalTempTeam == null || finalTempPlayers.size() == 0 || finalTempViews.size() == 0) {
                    //do nothing
                } else {
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
            }
        });
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

    public void fillSpinner(String name) {
        spinnerArray.add(name);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                LivePlayerStatistics.this.getContext(), android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.header.spinner.setAdapter(adapter);
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
//        addSpinnerListener();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLivePlayerStatisticsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

//        TeamService teamService = new TeamService();
//        PlayerService playerService = new PlayerService();
//
//        teamService.findTeamById(3, new CallbackListener<Team>() {
//            @Override
//            public void callback(Team returnedObject) {
//                team1 = returnedObject;
//                loadInitialTeamsPlayers(playerService, returnedObject.getTeamName());
//                LivePlayerStatistics.this.requireActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        fillSpinner(team1.getTeamName());
//                    }
//                });
//                try {
//                    UIHandler.updateTeamImage(LivePlayerStatistics.this, returnedObject, binding.header.teamImage);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        teamService.findTeamById(7, new CallbackListener<Team>() {
//            @Override
//            public void callback(Team returnedObject) {
//                team2 = returnedObject;
//                loadTeamPlayers(playerService, returnedObject.getTeamName());
//                LivePlayerStatistics.this.requireActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        fillSpinner(team2.getTeamName());
//                    }
//                });
//            }
//        });

    }

    public void loadInitialTeamsPlayers(PlayerService playerService, String name) {
        playerService.findAllPlayersByTeamName(name, new CallbackListener<ArrayList<Player>>() {
            @Override
            public void callback(ArrayList<Player> returnedObject) {
                team1Players = returnedObject;
                createPlayers(returnedObject, team1PlayerViews);
                autoSelectPlayer(returnedObject.get(0));

                LivePlayerStatistics.this.requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addPlayers(team1PlayerViews);
                        changePlayer(team1PlayerViews);
                        Utils.changeBackgroundColorInView(getContext(), team1PlayerViews.get(0), R.color.alt_blue);
                    }
                });
                dataRetrieved = true;
            }
        });
    }

    public void loadTeamPlayers(PlayerService playerService, String name) {
        playerService.findAllPlayersByTeamName(name, new CallbackListener<ArrayList<Player>>() {
            @Override
            public void callback(ArrayList<Player> returnedObject) {
                team2Players = returnedObject;
                createPlayers(returnedObject, team2PlayerViews);
            }
        });
    }


    public void addPlayers(ArrayList<View> views) {
        LinearLayout layout = binding.horizontalPlayerContainer.cardview.findViewById(R.id.horizontal_players);
        for (View v : views) {
            v.setBackgroundColor(Utils.getColor(LivePlayerStatistics.this.getContext(), R.color.grayback));
        }
        layout.removeAllViews();
        for (View v : views) {
            layout.addView(v);
        }
    }

    public void createPlayers(ArrayList<Player> returnedObject, ArrayList<View> views) {
        for (Player player : returnedObject) {
            try {
                View playerView = LayoutFactory.createPayerImageLayout(LivePlayerStatistics.this, player.getName(), player.getImagePath());

                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1.0f
                );
                playerView.setLayoutParams(param);
                views.add(playerView);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void autoSelectPlayer(Player player) {
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

                    Utils.changeBackgroundColorInView(getContext(), playerView, R.color.alt_blue);

                    int index = views.indexOf(playerView);
                    int playerSelectedId = teamSelected ? team1Players.get(index).getId() : team2Players.get(index).getId();
                    int teamSelectedId = teamSelected ? team1.getId() : team2.getId();

                    DAOLivePlayerStatistics.getInstance().setDataChangeListener(LivePlayerStatistics.this, 1, playerSelectedId);
                    DAOLiveTeamService.getInstance().setDataListenerForPlayer(LivePlayerStatistics.this, 1, teamSelectedId);

                    try {
                        UIHandler.updateSelectedPlayerImageLayout(LivePlayerStatistics.this,
                                team1Players.get(index).getImagePath(),
                                team1Players.get(index).getName(),
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