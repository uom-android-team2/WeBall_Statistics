package uom.team2.weball_statistics.UI_Controller.Completed_Match_Stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import uom.team2.weball_statistics.databinding.CompletedMatchStatsBinding;

public class CompletedMatchStats extends Fragment {

    private CompletedMatchStatsUIController completedMatchStatsUIController = CompletedMatchStatsUIController.getInstance();
    private CompletedMatchStatsBinding binding;

    public CompletedMatchStats() {
    }

    public static uom.team2.weball_statistics.UI_Controller.Completed_Match_Stats.CompletedMatchStats getInstance() {
        return new uom.team2.weball_statistics.UI_Controller.Completed_Match_Stats.CompletedMatchStats();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = CompletedMatchStatsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public CompletedMatchStatsBinding getBinding() {
        return binding;
    }
}