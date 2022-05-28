package uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController.TeamStats;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uom.team2.weball_statistics.R;

public class TeamStatsFragment extends Fragment {

    public TeamStatsFragment() {
        // Required empty public constructor
    }

    public static TeamStatsFragment newInstance() {
        TeamStatsFragment fragment = new TeamStatsFragment();
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
        return inflater.inflate(R.layout.fragment_team_stats, container, false);
    }
}