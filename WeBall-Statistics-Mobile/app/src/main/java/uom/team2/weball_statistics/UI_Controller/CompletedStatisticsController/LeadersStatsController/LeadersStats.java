package uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController.LeadersStatsController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Statistics.PlayerStats;
import uom.team2.weball_statistics.databinding.FragmentLeadersStatsBinding;

/*
 *  @author Evmorfia Elpida Dasyra ics20006
 */

public class LeadersStats extends Fragment {

    String[] statsNames;
    private FragmentLeadersStatsBinding binding;
    private ArrayList<Player> players;
    private ArrayList<PlayerStats> playersStats;


    public LeadersStats() {
        // Required empty public constructor
        players = new ArrayList<>();
        playersStats = new ArrayList<>();
        statsNames = new String[] {"Points Per Game", "Rebounds Per Game", "Assist Per Game", "Blocks Per Game", "Fouls Per Game"};
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
        addTitles(binding.leadersContainer);
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
            players = h.findPlayers();
            playersStats = h.getAllPlayersStats();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addTitles(LinearLayout leadersContainer) {
        for (String s: statsNames) {
            View playersStatsLayout = LeadersStatsLayouts.createPlayersStatsLayout(this,s);
            leadersContainer.addView(playersStatsLayout);
            s = s.replace("","");
            playersStatsLayout.setTag(s);
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void addPlayers() {

        for (Player p: players){

        }

    }




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