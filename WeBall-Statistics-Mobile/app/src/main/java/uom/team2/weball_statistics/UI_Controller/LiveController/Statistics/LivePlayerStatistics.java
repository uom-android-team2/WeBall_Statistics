package uom.team2.weball_statistics.UI_Controller.LiveController.Statistics;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentLivePlayerStatisticsBinding;

public class LivePlayerStatistics extends Fragment {

    private FragmentLivePlayerStatisticsBinding binding;

    public LivePlayerStatistics() {
        // Required empty public constructor
    }

    public static LivePlayerStatistics newInstance(String param1, String param2) {
        LivePlayerStatistics fragment = new LivePlayerStatistics();
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

        binding = FragmentLivePlayerStatisticsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}