package uom.team2.weball_statistics.UI_Controller.CompletedStatistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import uom.team2.weball_statistics.databinding.FragmentPlayerStatsBinding;


public class PlayerStats extends Fragment {

    private FragmentPlayerStatsBinding binding;


    public PlayerStats() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PlayerStats newInstance(String param1, String param2) {
        PlayerStats fragment = new PlayerStats();

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
        binding = FragmentPlayerStatsBinding.inflate(inflater,container,false);
        return binding.getRoot();

    }
}