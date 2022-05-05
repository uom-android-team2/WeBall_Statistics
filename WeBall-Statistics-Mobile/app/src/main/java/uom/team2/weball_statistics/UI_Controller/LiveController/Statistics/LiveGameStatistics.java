package uom.team2.weball_statistics.UI_Controller.LiveController.Statistics;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        binding.include2.progressTeam1Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.RED));
        binding.include2.progressTeam2Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
        binding.include4.progressTeam1Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.RED));
        binding.include4.progressTeam2Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
        binding.include3.progressTeam1Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.RED));
        binding.include3.progressTeam2Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
        binding.include5.progressTeam1Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.RED));
        binding.include5.progressTeam2Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
        binding.include6.progressTeam1Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.RED));
        binding.include6.progressTeam2Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
        binding.include7.progressTeam1Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.RED));
        binding.include7.progressTeam2Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
        binding.include8.progressTeam1Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.RED));
        binding.include8.progressTeam2Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.BLACK));

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}