package uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController.LeadersStatsController;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.*;


import uom.team2.weball_statistics.databinding.FragmentPlayersStatsLayoutBinding;


public class PlayersStatsLayout extends Fragment {
    FragmentPlayersStatsLayoutBinding binding;



    public PlayersStatsLayout() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PlayersStatsLayout newInstance(String param1, String param2) {
        PlayersStatsLayout fragment = new PlayersStatsLayout();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPlayersStatsLayoutBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

}