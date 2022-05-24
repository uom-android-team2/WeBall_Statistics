package uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController.PlayersStats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import uom.team2.weball_statistics.databinding.FragmentPlayerStatsBinding;

/*
 *   author: Evmorfia Elpida Dasyra ics20006
 */

public class PlayersStats extends Fragment {

    private FragmentPlayerStatsBinding binding;

    public PlayersStats() {
        // Required empty public constructor
    }

    public static PlayersStats newInstance(String param1, String param2) {
        PlayersStats fragment = new PlayersStats();

        return fragment;
    }

    public static Fragment getInstance() {

        return new PlayersStats();
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