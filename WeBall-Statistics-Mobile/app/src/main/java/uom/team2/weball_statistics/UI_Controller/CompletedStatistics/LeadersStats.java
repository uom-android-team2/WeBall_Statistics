package uom.team2.weball_statistics.UI_Controller.CompletedStatistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import uom.team2.weball_statistics.databinding.FragmentLeadersStatsBinding;


public class LeadersStats extends Fragment {

    private FragmentLeadersStatsBinding binding;


    public LeadersStats() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LeadersStats newInstance(String param1, String param2) {
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
        return binding.getRoot();

    }
}