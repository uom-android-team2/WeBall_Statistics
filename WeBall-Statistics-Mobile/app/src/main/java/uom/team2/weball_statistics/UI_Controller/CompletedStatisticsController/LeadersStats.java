package uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;

import uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController.LeadersStatsController.LeadersStatsLayouts;
import uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController.LeadersStatsController.LeadersStatsHandler;
import uom.team2.weball_statistics.databinding.FragmentLeadersStatsBinding;

/*
 *  @author Evmorfia Elpida Dasyra ics20006
 */

public class LeadersStats extends Fragment {

    String[] statsNames;
    private FragmentLeadersStatsBinding binding;
    // private HashMap<String, View> mapOfStats;

    public LeadersStats() {
        // Required empty public constructor
        statsNames = new String[] {"Points Per Game", "Rebounds Per Game", "Assist Per Game", "Steals Per Game", "Blocks Per Game"};
    }

    public static LeadersStats newInstance() {
        LeadersStats fragment = new LeadersStats();
        return fragment;
    }

    public void addPlayersLayout(LinearLayout leadersContainer){
        for (String s : statsNames) {
            View playersLayout = LeadersStatsLayouts.createPlayersStatsLayout(this, s);
            leadersContainer.addView(playersLayout);
            s = s.replace("","").toLowerCase();
            playersLayout.setTag(s);
            //mapOfStats.put(s, playersLayout);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addPlayersLayout(binding.leadersContainer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentLeadersStatsBinding.inflate(inflater,container,false);
        navigate();
        //on();
        return binding.getRoot();

    }

    public void navigate() {
//         for (String s: statsNames)
//         {
//             binding.leadersContainer.
//         }

//        binding.PPG.BUTTON.expButton.setOnClickListener(e -> {
//            NavHostFragment.findNavController(this).navigate(R.id.action_sharedTabContainer_to_expandedLeadersStat); });
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

    }

    public void on() {
        try {
            LeadersStatsHandler h = new LeadersStatsHandler();
            h.findPlayers();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }




}