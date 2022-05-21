package uom.team2.weball_statistics.UI_Controller.LiveController.Statistics;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentLivePlayerStatisticsBinding;

public class LivePlayerStatistics extends Fragment {

    private FragmentLivePlayerStatisticsBinding binding;

    public LivePlayerStatistics() {
        // Required empty public constructor
    }

    public static LivePlayerStatistics newInstance() {
        LivePlayerStatistics fragment = new LivePlayerStatistics();
        return fragment;
    }

    private void handleUIMode(){
        int nightModeFlags =
                getContext().getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                binding.cardview.setCardBackgroundColor(Color.DKGRAY);
                binding.horizontalPlayerContainer.cardview.setCardBackgroundColor(Color.DKGRAY);
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                binding.cardview.setCardBackgroundColor(getResources().getColor(R.color.light_gray));
                binding.horizontalPlayerContainer.cardview.setCardBackgroundColor(getResources().getColor(R.color.light_gray));
                break;
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProgressBarLayoutFactory.initializeAndAddProgressLayouts(this, binding.progressbarContainer);
        handleUIMode();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLivePlayerStatisticsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}