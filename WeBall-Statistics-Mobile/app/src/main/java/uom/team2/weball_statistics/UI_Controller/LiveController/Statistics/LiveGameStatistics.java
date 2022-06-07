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

import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.DAOLiveTeamService;
import uom.team2.weball_statistics.Service.TeamService;
import uom.team2.weball_statistics.UIFactory.LayoutFactory;
import uom.team2.weball_statistics.databinding.FragmentLiveGameStatisticsBinding;
import uom.team2.weball_statistics.utils.Utils;

/*
 * @author Leonard Pepa ics20033
 */
public class LiveGameStatistics extends Fragment {

    private FragmentLiveGameStatisticsBinding binding;
    private HashMap<String, View> mapOfStatistics;

    public LiveGameStatistics() {
        // Required empty public constructor
    }

    public static LiveGameStatistics getInstance() {
        return new LiveGameStatistics();
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
    }

    @Override
    public void onStart() {
        super.onStart();

//        DAOLiveTeamService.getInstace().setDataChangeListener(this, 1, 1, 2);

//        TeamService teamService = new TeamService();
//        // request team with id = 2 and when request is done get the object back
//        teamService.findTeamById(2, new CallbackListener<Team>() {
//            @Override
//            public void callback(Team returnedObject) {
//                View imageLayout = binding.headerContainer.findViewById(R.id.team1);
//                try {
//                    UIHandler.updateTeamImageInMatch(LiveGameStatistics.this, returnedObject, imageLayout);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        teamService.findTeamById(2, new CallbackListener<Team>() {
//            @Override
//            public void callback(Team returnedObject) {
//                View imageLayout = binding.headerContainer.findViewById(R.id.team2);
//                try {
//                    UIHandler.updateTeamImageInMatch(LiveGameStatistics.this, returnedObject, imageLayout);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void navigateToLivePlayerStats() {
        binding.playerLiveStatsButton.setOnClickListener(e -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_tabContainer_to_livePlayerStatistics);
        });
    }

    public FragmentLiveGameStatisticsBinding getBinding() {
        return binding;
    }

    public HashMap<String, View> getMapOfStatistics() {
        return mapOfStatistics;
    }
}