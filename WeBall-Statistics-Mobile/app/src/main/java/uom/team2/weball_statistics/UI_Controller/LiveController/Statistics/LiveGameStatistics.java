package uom.team2.weball_statistics.UI_Controller.LiveController.Statistics;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentLiveGameStatisticsBinding;
import uom.team2.weball_statistics.utils.Utils;

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

    public void addProgressBars(LinearLayout progressBarContainer){
        String[] statisticsArray = Utils.getStringArray(getContext(), R.array.team_statistics);
        for(String statName: statisticsArray){
            progressBarContainer.addView(ProgressBarLayoutFactory.createProgressBarLayout(this, statName));
        }
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
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addProgressBars(binding.progressbarLayoutContainer);
        navigateToLivePlayerStats();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void navigateToLivePlayerStats(){
        binding.playerLiveStatsButton.setOnClickListener(e -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_tabContainer_to_livePlayerStatistics);
        });
    }

}