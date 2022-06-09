package uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController.LeadersStatsController;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Statistics.PlayerStats;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.PlayerChampionshipStatsService;
import uom.team2.weball_statistics.Service.PlayerService;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.CallbackListener;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.databinding.FragmentLeadersStatsBinding;

/*
 *  @author Evmorfia Elpida Dasyra ics20006
 */

public class LeadersStats extends Fragment {

    private String[] statsNames;
    private FragmentLeadersStatsBinding binding;
//    private ArrayList<Player> players;
//    private ArrayList<PlayerStats> playersStats;
    private String[] tags ;


    public LeadersStats() {
        // Required empty public constructor
//        players = new ArrayList<>();
//        playersStats = new ArrayList<>();
        statsNames = new String[] {"Points Per Game","Assist Per Game", "Rebounds Per Game", "Blocks Per Game", "Fouls Per Game"};
        tags = new String[] {"PPG", "RPG", "APG", "BPG", "FPG"};
    }

    public static LeadersStats newInstance() {
        LeadersStats fragment = new LeadersStats();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addTitles();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentLeadersStatsBinding.inflate(inflater,container,false);

        //navigate();
        return binding.getRoot();
    }

    public void onStart(){
        super.onStart();
        PlayerService playerService = new PlayerService();
        PlayerChampionshipStatsService playerChampionshipStatsService = new PlayerChampionshipStatsService();

        playerChampionshipStatsService.getAllPlayerStatistics(new CallbackListener<ArrayList<PlayerStats>>() {
            @Override
            public void callback(ArrayList<PlayerStats> returnedObject) {
                System.out.println(returnedObject);

                ArrayList<PlayerStats> points = new ArrayList<>();
                ArrayList<PlayerStats> assists = new ArrayList<>();
                ArrayList<PlayerStats> blocks = new ArrayList<>();
                ArrayList<PlayerStats> rebounds = new ArrayList<>();
                ArrayList<PlayerStats> fouls = new ArrayList<>();

                points = sortByPoints(points);
                assists = sortByAssists(assists);
                blocks = sortByBlocks(blocks);
                rebounds = sortByRebounds(rebounds);
                fouls = sortByFouls(fouls);

            }
        });

//        //creates top player
//        try {
//            createTopPlayer(binding.PPG.playersStatContainer.findViewById(R.id.topPlayerContainer),
//                    String.valueOf(players.get(0).getName().charAt(0))+". "+players.get(0).getSurname().toUpperCase(),
//                     players.get(0).getTeamName().toUpperCase().substring(0,3), 0, "22", 34, tags[0] );
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        //creates the other 4 players
//        try {
//            LinearLayout l = binding.leadersContainer.findViewById(R.id.ranksContainer);
//            for(int i=1; i<=l.getChildCount();i++) {
//                createPlayer(binding.PPG.playersStatContainer.findViewById(R.id.ranksContainer),
//                            String.valueOf(players.get(i).getName().charAt(0))+". "+players.get(i).getSurname().toUpperCase(), players.get(i).getTeamName().toUpperCase().substring(0,3), i-1, "22");
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    }

    public void addTitles() {

        binding.PPG.statisticTitle.setText(statsNames[0]);
        binding.APG.statisticTitle.setText(statsNames[1]);
        binding.RPG.statisticTitle.setText(statsNames[2]);
        binding.BPG.statisticTitle.setText(statsNames[3]);
        binding.FPG.statisticTitle.setText(statsNames[4]);


    }

    public void updatePlayers(PlayerService playerService, ArrayList<PlayerStats> playerStats, Type target){

        int ranks = 5;

        if (playerStats.size() < ranks){
            ranks = playerStats.size();
        }
        for (int i=0;i<ranks;i++){
            int finali=i;
            double value = -1;
            LinearLayout layout = binding.

        }
    }

    public void createPlayer(LinearLayout linearLayout, String player, String team, int num, String statValue)
    {
       View view = linearLayout.getChildAt(num);
       System.out.println("RANKS CHILDREN = "+linearLayout.getChildCount());

       LeadersStats.this.requireActivity().runOnUiThread(new Runnable() {
           @Override
           public void run() {
               TextView playerView = view.findViewById(R.id.player);
               playerView.setText(player);

               TextView rankView = view.findViewById(R.id.rank);
               rankView.setText(String.valueOf(num+2));

               TextView teamView = view.findViewById(R.id.team);
               teamView.setText(team);

               TextView statView = view.findViewById(R.id.statValue);
               statView.setText(statValue);
           }
       });
    }

    public void createTopPlayer(LinearLayout linearLayout, String player, String team, int num, String statValue,
                                int playerNumber, String tag)
    {
        View view = linearLayout.getChildAt(num);
        System.out.println("TOP PLAYER CHILDREN = "+ linearLayout.getChildCount());

        LeadersStats.this.requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView playerView = view.findViewById(R.id.topPlayerName);
                playerView.setText(player);

                TextView teamView = view.findViewById(R.id.topTeamName);
                teamView.setText(team);

                TextView valueView = view.findViewById(R.id.topStatValue);
                valueView.setText(statValue);

                TextView numView = view.findViewById(R.id.topNumber);
                numView.setText(String.valueOf(playerNumber));

                TextView statView = view.findViewById(R.id.topStatistic);
                statView.setText(tag);
            }
        });
    }

    public ArrayList<PlayerStats> sortByPoints(ArrayList<PlayerStats> list) {

        Collections.sort(list, new Comparator<PlayerStats>() {
            @Override
            public int compare(PlayerStats playerStats, PlayerStats t1) {
                if (t1.calculatePointsPercentage() - playerStats.calculatePointsPercentage() > 0) {
                    return 1;
                } else if (t1.calculatePointsPercentage() - playerStats.calculatePointsPercentage() == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        return new ArrayList<>(list);
    }

    public ArrayList<PlayerStats> sortByAssists(ArrayList<PlayerStats> list) {
        Collections.sort(list, new Comparator<PlayerStats>() {
            @Override
            public int compare(PlayerStats playerStats, PlayerStats t1) {
                if (t1.calculateAssistPercentage() - playerStats.calculateAssistPercentage() > 0) {
                    return 1;
                } else if (t1.calculateAssistPercentage() - playerStats.calculateAssistPercentage() == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        return new ArrayList<>(list);
    }

    public ArrayList<PlayerStats> sortByBlocks(ArrayList<PlayerStats> list) {
        Collections.sort(list, new Comparator<PlayerStats>() {
            @Override
            public int compare(PlayerStats playerStats, PlayerStats t1) {
                if (t1.calculateBlockPercentage() - playerStats.calculateBlockPercentage() > 0) {
                    return 1;
                } else if (t1.calculateBlockPercentage() - playerStats.calculateBlockPercentage() == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        return new ArrayList<>(list);
    }

    public ArrayList<PlayerStats> sortByRebounds(ArrayList<PlayerStats> list) {
        Collections.sort(list, new Comparator<PlayerStats>() {
            @Override
            public int compare(PlayerStats playerStats, PlayerStats t1) {
                if (t1.calculateReboundPercentage() - playerStats.calculateReboundPercentage() > 0) {
                    return 1;
                } else if (t1.calculateReboundPercentage() - playerStats.calculateReboundPercentage() == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        return new ArrayList<>(list);
    }

    public ArrayList<PlayerStats> sortByFouls(ArrayList<PlayerStats> list) {
        Collections.sort(list, new Comparator<PlayerStats>() {
            @Override
            public int compare(PlayerStats playerStats, PlayerStats t1) {
                if (t1.calculateFoulPercentage() - playerStats.calculateFoulPercentage() > 0) {
                    return 1;
                } else if (t1.calculateFoulPercentage() - playerStats.calculateFoulPercentage() == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        return new ArrayList<>(list);
    }

    enum Type {POINTS, ASSISTS, BLOCKS, REBOUNDS, FOULS}


//    public void navigate() {
//
//        binding.leadersContainer.findViewById(R.id.button).setOnClickListener(e -> {
//                NavHostFragment.findNavController(this).navigate(R.id.action_sharedTabContainer_to_expandedLeadersStat); });
//
//        binding.RPG.BUTTON.expButton.setOnClickListener(e -> {
//            NavHostFragment.findNavController(this).navigate(R.id.action_sharedTabContainer_to_expandedLeadersStat); });
//
//        binding.APG.BUTTON.expButton.setOnClickListener(e -> {
//            NavHostFragment.findNavController(this).navigate(R.id.action_sharedTabContainer_to_expandedLeadersStat); });
//
//        binding.SPG.BUTTON.expButton.setOnClickListener(e -> {
//            NavHostFragment.findNavController(this).navigate(R.id.action_sharedTabContainer_to_expandedLeadersStat); });
//
//        binding.BPG.BUTTON.expButton.setOnClickListener(e -> {
//            NavHostFragment.findNavController(this).navigate(R.id.action_sharedTabContainer_to_expandedLeadersStat); });
//
//        binding.FGM3.BUTTON.expButton.setOnClickListener(e -> {
//            NavHostFragment.findNavController(this).navigate(R.id.action_sharedTabContainer_to_expandedLeadersStat); });
//
//    }

}