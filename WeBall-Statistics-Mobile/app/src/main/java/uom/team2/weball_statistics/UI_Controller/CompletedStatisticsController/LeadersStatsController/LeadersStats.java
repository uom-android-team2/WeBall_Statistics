package uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController.LeadersStatsController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


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
    private String[] tags ;


    public LeadersStats() {
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
        return binding.getRoot();
    }

    public void onStart(){
        super.onStart();
        PlayerService playerService = new PlayerService();
        PlayerChampionshipStatsService playerChampionshipStatsService = new PlayerChampionshipStatsService();

        playerChampionshipStatsService.getAllPlayerStatistics(new CallbackListener<ArrayList<PlayerStats>>() {
            @Override
            public void callback(ArrayList<PlayerStats> returnedObject) {
                System.out.println("RETURNED OBJECT");
                System.out.println("TO SIZE EINAI = " + returnedObject.size());
                System.out.println(returnedObject);

                ArrayList<PlayerStats> points = new ArrayList<>();
                ArrayList<PlayerStats> assists = new ArrayList<>();
                ArrayList<PlayerStats> blocks = new ArrayList<>();
                ArrayList<PlayerStats> rebounds = new ArrayList<>();
                ArrayList<PlayerStats> fouls = new ArrayList<>();

                points = sortByPoints(returnedObject);
                System.out.println("POSA EINAI TA POINTS??" + points);
                assists = sortByAssists(returnedObject);
                blocks = sortByBlocks(returnedObject);
                rebounds = sortByRebounds(returnedObject);
                fouls = sortByFouls(returnedObject);

                updatePlayers(playerService,points,Type.POINTS);
                updatePlayers(playerService,assists,Type.ASSISTS);
                updatePlayers(playerService,blocks,Type.REBOUNDS);
                updatePlayers(playerService,rebounds,Type.BLOCKS);
                updatePlayers(playerService,fouls,Type.FOULS);

            }
        });
    }

    public void addTitles() {

        binding.PPG.statisticTitle.setText(statsNames[0]);
        binding.APG.statisticTitle.setText(statsNames[1]);
        binding.RPG.statisticTitle.setText(statsNames[2]);
        binding.BPG.statisticTitle.setText(statsNames[3]);
        binding.FPG.statisticTitle.setText(statsNames[4]);

        changeTopPlayerLayout(binding.PPG.topPlayerContainer,"PPG");
        changeTopPlayerLayout(binding.APG.topPlayerContainer,"APG");
        changeTopPlayerLayout(binding.RPG.topPlayerContainer,"RPG");
        changeTopPlayerLayout(binding.BPG.topPlayerContainer,"BPG");
        changeTopPlayerLayout(binding.FPG.topPlayerContainer,"FPG");

        binding.PPG.ranktab.rankTabStat.setText("PPG");
        binding.APG.ranktab.rankTabStat.setText("APG");
        binding.RPG.ranktab.rankTabStat.setText("RPG");
        binding.BPG.ranktab.rankTabStat.setText("BPG");
        binding.FPG.ranktab.rankTabStat.setText("FPG");
    }

    public void changeTopPlayerLayout(LinearLayout linearLayout, String value){
        View view = linearLayout.getChildAt(0);

        TextView textView = view.findViewById(R.id.topStatistic);
        textView.setText(value);

    }




    public void updatePlayers(PlayerService playerService, ArrayList<PlayerStats> playerStats, Type target){

        int ranks = 4;

        if (playerStats.size() < 4) {
            ranks = playerStats.size();
        }
        for (int i=0;i<ranks;i++) {
            int finalI = i;
            double value = -1;
            LinearLayout topPlayerLayout = binding.PPG.topPlayerContainer;
            LinearLayout playerLayout = binding.PPG.ranksContainer;
            if (target == Type.POINTS){
                value = playerStats.get(i).calculatePointsPercentage();
            } else if (target == Type.ASSISTS){
                value = playerStats.get(i).calculateAssistPercentage();
                topPlayerLayout = binding.APG.topPlayerContainer;
                playerLayout = binding.APG.ranksContainer;
            }  else if (target == Type.REBOUNDS){
                value = playerStats.get(i).calculateReboundPercentage();
                topPlayerLayout = binding.RPG.topPlayerContainer;
                playerLayout = binding.RPG.ranksContainer;
            }  else if (target == Type.BLOCKS){
                value = playerStats.get(i).calculateBlockPercentage();
                topPlayerLayout = binding.BPG.topPlayerContainer;
                playerLayout = binding.BPG.ranksContainer;
            }  else if (target == Type.FOULS){
                value = playerStats.get(i).calculateFoulPercentage();
                topPlayerLayout = binding.FPG.topPlayerContainer;
                playerLayout = binding.FPG.ranksContainer;
            }
            double finalValue = value;


            //creates top Player
            LinearLayout topFinalLayout = topPlayerLayout;
            playerService.findPlayerById2(playerStats.get(0).getPlayer_id(), new CallbackListener<Player>() {

                @Override
                public void callback(Player returnedObject) {
                    createTopPlayer(topFinalLayout, returnedObject.getName()+" " +returnedObject.getSurname(),
                            teamFormat(returnedObject.getTeamString()), 0 , finalValue + "" , returnedObject.getNumber(),
                            positionFormat(returnedObject.getPosition()),
                            Config.PLAYER_IMAGES_RESOURCES + returnedObject.getImagePath());

                }
            });

            //creates other players
            LinearLayout playerFinalLayout = playerLayout;
            playerService.findPlayerById2(playerStats.get(i+1).getPlayer_id(), new CallbackListener<Player>() {

                @Override
                public void callback(Player returnedObject) {
                    createPlayer(playerFinalLayout, nameFormat(returnedObject.getName(), returnedObject.getSurname()),
                            teamFormat(returnedObject.getTeamString()), finalI , finalValue + "");
                }
            });

        }
    }

    // replace "name surname" with "N. SURNAME"
    public String nameFormat(String name, String surname){
        return String.valueOf(name.charAt(0))+ ". " + surname.toUpperCase();
    }

    // replace e.g. "POINT_GUARD" with "PG" or "CENTER" with "C"
    public String positionFormat(String position){
        if (position.contains("_")) {
            position = position.replace("_", " ");
            String[] split = position.split(" ",2);
            split[0] = split[0].substring(0, 1);
            split[1] = split[1].substring(0, 1);
            String join = String.join("",split[0],split[1]);
            return join;
        } else {
            return position.substring(0, 1);
        }
    }

    // replace e.g. "Milwaukee" with "MIL"
    public String teamFormat(String team){
        return team.toUpperCase().substring(0,3);
    }



    public void createPlayer(LinearLayout linearLayout, String player, String team, int num, String statValue)
    {
       View view = linearLayout.getChildAt(num);
       System.out.println("RANKS CHILDREN = "+ linearLayout.getChildCount());

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
                                int playerNumber, String position,  String url)
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

                TextView positionView = view.findViewById(R.id.topPosition);
                positionView.setText(position);

                ImageView imageView = view.findViewById(R.id.topPlayerImage);
                Picasso.get()
                        .load(url)
                        .resize(200,200)
                        .centerCrop()
                        .into(imageView);
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