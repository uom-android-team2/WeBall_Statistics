package uom.team2.weball_statistics.UI_Controller.LiveController.Statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.DAOLivePlayerStatistics;
import uom.team2.weball_statistics.Service.DAOLiveTeamService;
import uom.team2.weball_statistics.Service.PlayerService;
import uom.team2.weball_statistics.Service.TeamService;
import uom.team2.weball_statistics.UIFactory.LayoutFactory;
import uom.team2.weball_statistics.databinding.FragmentLivePlayerStatisticsBinding;
import uom.team2.weball_statistics.utils.Utils;

public class LivePlayerStatistics extends Fragment {

    private final ArrayList<Player> team2Players = new ArrayList<>();
    private final ArrayList<View> team1PlayerViews = new ArrayList<>();
    private final ArrayList<View> team3PlayerViews = new ArrayList<>();
    private ArrayList<Player> team1Players = new ArrayList<>();
    private FragmentLivePlayerStatisticsBinding binding;
    private HashMap<String, View> mapOfStatistics;
    private Team team1;
    private Team team2;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapOfStatistics = new HashMap<>();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addProgressBars(binding.progressbarContainer);
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
//        teamService.findTeamById(3, new CallbackListener<Team>() {
//            @Override
//            public void callback(Team returnedObject) {
//                team1 = returnedObject;
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
//        PlayerService playerService = new PlayerService();
//        playerService.findAllPlayersByTeamName("Brooklyn Nets", new CallbackListener<ArrayList<Player>>() {
//            @Override
//            public void callback(ArrayList<Player> returnedObject) {
//                team1Players = returnedObject;
//                createPlayers(returnedObject, team1PlayerViews);
//                autoSelectPlayer(returnedObject.get(0));
//
//                LinearLayout layout = binding.horizontalPlayerContainer.cardview.findViewById(R.id.horizontal_players);
//                LivePlayerStatistics.this.requireActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        team1PlayerViews.get(0).setBackgroundColor(Utils.getColor(LivePlayerStatistics.this.getContext(), R.color.alt_blue));
//
//                        for (View v : team1PlayerViews) {
//                            layout.addView(v);
//                        }
//                        changePlayer(team1PlayerViews);
//                    }
//                });
//            }
//        });
    }

    public void createPlayers(ArrayList<Player> returnedObject, ArrayList<View> views) {
        for (Player player : returnedObject) {
            try {
                View playerView = LayoutFactory.createPayerImageLayout(LivePlayerStatistics.this, player.getName(),
                        "http://" + IP.IP + "/WeBall_Statistics-Backend/resources/player_images/" + player.getImagePath());

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
                    "http://" + IP.IP + "/WeBall_Statistics-Backend/resources/player_images/" + player.getImagePath(),
                    player.getName(),
                    binding.selectedPlayerLayout.getRoot());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void changePlayer(ArrayList<View> views) {

        for (View v : views) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    v.setBackgroundColor(Utils.getColor(LivePlayerStatistics.this.getContext(), R.color.alt_blue));
                    int index = views.indexOf(v);
                    try {
                        UIHandler.updateSelectedPlayerImageLayout(LivePlayerStatistics.this, "http://" + IP.IP + "/WeBall_Statistics-Backend/resources/player_images/" + team1Players.get(index).getImagePath(), team1Players.get(index).getName(), binding.selectedPlayerLayout.getRoot());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (View other : views) {
                        if (!other.equals(v)) {
                            other.setBackgroundColor(Utils.getColor(LivePlayerStatistics.this.getContext(), R.color.statistics_background));
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