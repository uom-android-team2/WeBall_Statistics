package uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentLeadersStatsBinding;

/*
 *  @author Evmorfia Elpida Dasyra ics20006
 */

public class LeadersStats extends Fragment {

    private FragmentLeadersStatsBinding binding;


    public LeadersStats() {
        // Required empty public constructor
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentLeadersStatsBinding.inflate(inflater,container,false);
        navigate();
        return binding.getRoot();

    }

    public void navigate() {

    }




}