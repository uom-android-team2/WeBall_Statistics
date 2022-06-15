package uom.team2.weball_statistics.UI_Controller.LiveController.Statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.io.IOException;
import java.util.HashMap;

import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.DAOLiveMatchService;
import uom.team2.weball_statistics.Service.TeamService;
import uom.team2.weball_statistics.UIFactory.LayoutFactory;
import uom.team2.weball_statistics.databinding.FragmentLiveGameStatisticsBinding;
import uom.team2.weball_statistics.utils.Utils;

/*
 * @author Leonard Pepa ics20033
 */
public class LiveGameStatistics extends Fragment {

    private final DAOLiveMatchService daoLiveMatchService = DAOLiveMatchService.getInstance();
    private final TeamService teamService = new TeamService();
    private FragmentLiveGameStatisticsBinding binding;
    private HashMap<String, View> mapOfStatistics;
    private Match match;
    private Team teamLandlord;
    private Team teamGuest;

    public LiveGameStatistics() {
        // Required empty public constructor
    }

    public static LiveGameStatistics getInstance(Bundle bundle) {
        LiveGameStatistics liveGameStatistics = new LiveGameStatistics();
        liveGameStatistics.setArguments(bundle);
        return liveGameStatistics;
    }

    public void addProgressBars(LinearLayout progressBarContainer) {
        String[] statisticsArray = Utils.getStringArray(getContext(), R.array.team_statistics);
        for (String statName : statisticsArray) {
            View progressBarLayout = LayoutFactory.createProgressBarLayout(this, statName);
            statName = statName.replace(" ", "_").toLowerCase();
            progressBarLayout.setTag(statName);
            mapOfStatistics.put(statName, progressBarLayout);
            progressBarContainer.addView(progressBarLayout);
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapOfStatistics = new HashMap<>();
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
        Bundle bundle = getArguments();

        match = (Match) bundle.getSerializable("match");
        teamLandlord = (Team) bundle.getSerializable("teamLandlord");
        teamGuest = (Team) bundle.getSerializable("teamGuest");
        daoLiveMatchService.initializeTable(match.getId(), teamLandlord.getId(), teamGuest.getId());
    }

    @Override
    public void onStart() {
        super.onStart();
        DAOLiveMatchService.getInstance().clockDataListener(this, binding.header.clock.clockText, match.getId());
        daoLiveMatchService.setDataChangeListener(this, match.getId(), teamLandlord.getId(), teamGuest.getId());
        daoLiveMatchService.setListenerForPoints(this, binding.header, match.getId(), teamLandlord.getId(), teamGuest.getId());

        try {
            UIHandler.updateTeamImageInMatch(LiveGameStatistics.this, teamLandlord, binding.header.team1.getRoot());
            UIHandler.updateTeamImageInMatch(LiveGameStatistics.this, teamGuest, binding.header.team2.getRoot());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void navigateToLivePlayerStats() {
        binding.playerLiveStatsButton.setOnClickListener(e -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_tabContainer_to_livePlayerStatistics, getArguments());
        });
    }

    public FragmentLiveGameStatisticsBinding getBinding() {
        return binding;
    }

    public HashMap<String, View> getMapOfStatistics() {
        return mapOfStatistics;
    }
}