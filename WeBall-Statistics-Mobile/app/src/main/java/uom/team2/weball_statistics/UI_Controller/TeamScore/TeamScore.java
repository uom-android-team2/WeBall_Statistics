package uom.team2.weball_statistics.UI_Controller.TeamScore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import uom.team2.weball_statistics.Model.Statistics.TeamStats;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.TeamChampioshipStatsService;
import uom.team2.weball_statistics.Service.TeamService;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.CallbackListener;
import uom.team2.weball_statistics.databinding.FragmentTeamScoreBinding;
/*
 *  @author Ampatzidou Elisavet ics20038
 */
public class TeamScore extends Fragment {

    private FragmentTeamScoreBinding binding;
    private  ArrayList<Team> teams = new ArrayList<>();

    public TeamScore() {
        // Required empty public constructor
    }

    public static TeamScore newInstance(String param1, String param2) {
        TeamScore fragment = new TeamScore();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTeamScoreBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navigate();

    }

    @Override
    public void onStart() {
        super.onStart();
        TeamService teamService = new TeamService();
        TeamChampioshipStatsService teamChampioshipStatsService = new TeamChampioshipStatsService();

        teamChampioshipStatsService.getAllTeamStatistics(new CallbackListener<ArrayList<TeamStats>>() {
            @Override
            public void callback(ArrayList<TeamStats> returnedObject) {


                ArrayList<TeamStats> bestByPoints = new ArrayList<>();

                bestByPoints = sortByPoints(returnedObject);

                updateRows(teamService, bestByPoints);

            }
        });

    }

    public void navigate() {
        binding.top5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TeamScore.this).navigate(R.id.action_teamScore_to_bestStarting52);
            }
        });

        binding.statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TeamScore.this).navigate(R.id.action_teamScore_to_sharedTabContainer);
            }
        });
    }

    public void updateRows(TeamService teamService, ArrayList<TeamStats> teamStats) {
        int n = teamStats.size();


        for (int i = 0; i < n; i++) {
            int finalI = i;
            double grades = -1;
            int wins = -1;
            int loses = -1;
            int games = -1;

            LinearLayout layout = binding.teamsContainer;

            grades = teamStats.get(i).getGrades();
            wins = teamStats.get(i).getWins();
            loses = teamStats.get(i).getLoses();
            games = teamStats.get(i).getWins() + teamStats.get(i).getLoses();


            //double finalValue = value;
            LinearLayout finalLayout = layout;
            int finalGames = games;
            int finalWins = wins;
            int finalLoses = loses;
            double finalGrades = grades;

            try {
                createRow(finalLayout,finalI, teamStats.get(i).getTeam_name(), finalGames,finalWins,finalLoses,finalGrades);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }

    public void createRow(LinearLayout teamsContainer,int pos ,String name, int games, int wins, int loses , double grades) throws InterruptedException {

        if (this.getActivity() != null && this.isAdded()) {
            TeamScore.this.requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    View teamScoreLayout = TeamScoreLayout.createTeamScoreLayout(TeamScore.this, pos, name, games, wins, loses, grades);
                    teamsContainer.addView(teamScoreLayout);
                }
            });
        }
    }

    public ArrayList<TeamStats> sortByPoints(ArrayList<TeamStats> list) {

        Collections.sort(list, new Comparator<TeamStats>() {
            @Override
            public int compare(TeamStats teamStats, TeamStats t1) {
                if (t1.getGrades() - teamStats.getGrades() > 0) {
                    return 1;
                } else if (t1.getGrades() - teamStats.getGrades() == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        return new ArrayList<>(list);
    }

}