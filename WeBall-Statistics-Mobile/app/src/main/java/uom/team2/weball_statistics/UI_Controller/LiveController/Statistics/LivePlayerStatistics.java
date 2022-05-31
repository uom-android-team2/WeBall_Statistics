package uom.team2.weball_statistics.UI_Controller.LiveController.Statistics;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.UIFactory.LayoutFactory;
import uom.team2.weball_statistics.databinding.FragmentLivePlayerStatisticsBinding;
import uom.team2.weball_statistics.utils.Utils;

public class LivePlayerStatistics extends Fragment {

    private FragmentLivePlayerStatisticsBinding binding;

    public LivePlayerStatistics() {
        // Required empty public constructor
    }

    public static LivePlayerStatistics newInstance() {
        LivePlayerStatistics fragment = new LivePlayerStatistics();
        return fragment;
    }

    public void addProgressBars(LinearLayout progressBarContainer){
        String[] statisticsArray = Utils.getStringArray(getContext(), R.array.player_statistics);
        for(String stat: statisticsArray){
            progressBarContainer.addView(LayoutFactory.createProgressBarLayout(this, stat));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addProgressBars(binding.progressbarContainer);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLivePlayerStatisticsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}