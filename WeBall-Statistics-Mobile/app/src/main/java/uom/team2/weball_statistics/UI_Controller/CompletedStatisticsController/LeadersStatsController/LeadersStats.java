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
import java.util.Locale;

import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Statistics.PlayerStats;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentLeadersStatsBinding;

/*
 *  @author Evmorfia Elpida Dasyra ics20006
 */

public class LeadersStats extends Fragment {

    private String[] statsNames;
    private FragmentLeadersStatsBinding binding;
    private ArrayList<Player> players;
    private ArrayList<PlayerStats> playersStats;
    private String[] tags ;


    public LeadersStats() {
        // Required empty public constructor
        players = new ArrayList<>();
        playersStats = new ArrayList<>();
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

        try {
            LeadersStatsHandler h = new LeadersStatsHandler();
            h.deserializePlayers();
            players = h.getPlayers();
            h.deserializeAllPlayersStatistics();
            playersStats = h.getAllPlayersStats();

            System.out.println("FIND THEM3333 = " + h.getAllPlayersStats().get(0).getPlayer_id()); //Object
            System.out.println("FIND THEM444 = " + h.getPlayers().get(0).getName()); //Object
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        //creates top player
        try {
            createTopPlayer(binding.PPG.playersStatContainer.findViewById(R.id.topPlayerContainer),
                    String.valueOf(players.get(0).getName().charAt(0))+". "+players.get(0).getSurname().toUpperCase(),
                     players.get(0).getTeamName().toUpperCase().substring(0,3), 0, "22", 34 ,"PPG");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //creates the other 4 players
        try {
            LinearLayout l = binding.leadersContainer.findViewById(R.id.ranksContainer);
            for(int i=1; i<=l.getChildCount();i++) {
                createPlayer(binding.PPG.playersStatContainer.findViewById(R.id.ranksContainer),
                            String.valueOf(players.get(i).getName().charAt(0))+". "+players.get(i).getSurname().toUpperCase(), players.get(i).getTeamName().toUpperCase().substring(0,3), i-1, "22");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void addTitles() {

        binding.PPG.statisticTitle.setText(statsNames[0]);
        binding.APG.statisticTitle.setText(statsNames[1]);
        binding.RPG.statisticTitle.setText(statsNames[2]);
        binding.BPG.statisticTitle.setText(statsNames[3]);
        binding.FPG.statisticTitle.setText(statsNames[4]);

    }

    public void createPlayer(LinearLayout linearLayout, String player, String team, int num, String statValue) throws InterruptedException {
       View view = linearLayout.getChildAt(num);
       System.out.println("GOOD YEAH "+linearLayout.getChildCount());

       TextView playerView = view.findViewById(R.id.player);
       playerView.setText(player);

       TextView rankView = view.findViewById(R.id.rank);
       rankView.setText(String.valueOf(num+2));

       TextView teamView = view.findViewById(R.id.team);
       teamView.setText(team);

       TextView statView = view.findViewById(R.id.statValue);
       statView.setText(statValue);

    }

    public void createTopPlayer(LinearLayout linearLayout, String player, String team, int num, String statValue,
                                int playerNumber, String ppg) throws InterruptedException {
        View view = linearLayout.getChildAt(num);
        System.out.println("GOOD YEAH2222222 "+ linearLayout.getChildCount());

        TextView playerView = view.findViewById(R.id.topPlayerName);
        playerView.setText(player);

        TextView teamView = view.findViewById(R.id.topTeamName);
        teamView.setText(team);

        TextView valueView = view.findViewById(R.id.topStatValue);
        valueView.setText(statValue);

        TextView numView = view.findViewById(R.id.topNumber);
        numView.setText(String.valueOf(playerNumber));

        TextView statView = view.findViewById(R.id.topStatistic);
        statView.setText(ppg);

    }



//    public void addTitles(LinearLayout leadersContainer) {
//        for (String s: statsNames) {
//            int i=0;
//            View playersStatsLayout = LeadersStatsLayouts.createPlayersStatsLayout(this,s);
//            leadersContainer.addView(playersStatsLayout);
//            s = s.replace("","");
//            playersStatsLayout.setTag(tags[i]);
//            i++;
//        }
//    }

//    public void addPlayers(LinearLayout leadersContainer) {
//
//        for (String tag: tags) {
//            View playersLayout = leadersContainer.findViewWithTag(tag);
//            playersLayout   = LeadersStatsLayouts.createPlayers(this, players, playersStats) ;
//        }
//    }



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