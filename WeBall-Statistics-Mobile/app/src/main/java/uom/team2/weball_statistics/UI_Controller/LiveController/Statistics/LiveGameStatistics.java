package uom.team2.weball_statistics.UI_Controller.LiveController.Statistics;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentLiveGameStatisticsBinding;

/*
 * @author Leonard Pepa ics20033
 */
public class LiveGameStatistics extends Fragment {

    private FragmentLiveGameStatisticsBinding binding;

    public LiveGameStatistics() {
        // Required empty public constructor
    }

    public static LiveGameStatistics getInstance(){
        return new LiveGameStatistics();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLiveGameStatisticsBinding.inflate(inflater, container, false);
        initializeAndAddProgressLayouts(binding.progressbarLayoutContainer);
        navigateToLivePlayerStats();


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public View createProgressBarLayout(String name){
        View progressBarLayout = getLayoutInflater().inflate(R.layout.live_statistics_progressbar_layout, null);
        progressBarLayout.setTag(name);
        TextView statsName = progressBarLayout.findViewById(R.id.name_of_statistic);
        statsName.setText(name);
        return progressBarLayout;
    }

    public void initializeAndAddProgressLayouts(LinearLayout progressBarContainer){
        String[] statisticsArray = getResources().getStringArray(R.array.statistics);

        for(String stat: statisticsArray){
            progressBarContainer.addView(createProgressBarLayout(stat));
        }
    }

    public void navigateToLivePlayerStats(){
        binding.playerLiveStatsButton.setOnClickListener(e -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_tabContainer_to_livePlayerStatistics);
        });
    }

}