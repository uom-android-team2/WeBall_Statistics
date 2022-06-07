package uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController.LeadersStatsController;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentExpandedLeadersStatBinding;
import uom.team2.weball_statistics.databinding.FragmentLeadersStatsBinding;


public class ExpandedLeadersStat extends Fragment {

    private String title;
    FragmentExpandedLeadersStatBinding binding;

    public ExpandedLeadersStat() {
        // Required empty public constructor
    }


    public static ExpandedLeadersStat newInstance() {
        ExpandedLeadersStat fragment = new ExpandedLeadersStat();

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
        binding = FragmentExpandedLeadersStatBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}