package uom.team2.weball_statistics.UI_Controller.TeamScore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import uom.team2.weball_statistics.Model.Statistics.TeamStats;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.TeamChampioshipStatsService;
import uom.team2.weball_statistics.Service.TeamService;
import uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController.TeamStats.TeamStatsFragment;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.CallbackListener;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.databinding.FragmentTeamScoreBinding;


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
        binding.top5.setOnClickListener(e -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_teamScore_to_bestStarting5);
        });

        binding.imageView2.setOnClickListener(e -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_teamScore_to_completedMatchStats3);
        });
        //addTeamScoreLayout(binding.scoreTable,teams);
    }

    @Override
    public void onStart() {
        super.onStart();
        TeamService teamService = new TeamService();
        TeamChampioshipStatsService teamChampioshipStatsService = new TeamChampioshipStatsService();

        teamChampioshipStatsService.getAllTeamStatistics(new CallbackListener<ArrayList<TeamStats>>() {
            @Override
            public void callback(ArrayList<TeamStats> returnedObject) {

                //System.out.println(returnedObject);

//                ArrayList<TeamStats> bestByPoints = new ArrayList<>();
//                ArrayList<TeamStats> bestByAssists = new ArrayList<>();
//                ArrayList<TeamStats> bestByBlocks = new ArrayList<>();
//                ArrayList<TeamStats> bestByRebounds = new ArrayList<>();
//
//                bestByPoints = sortByPoints(returnedObject);
//                bestByAssists = sortByAssists(returnedObject);
//                bestByBlocks = sortByBlocks(returnedObject);
//                bestByRebounds = sortByRebounds(returnedObject);
//
                updateRows(teamService, returnedObject);
//                udateRows(teamService, bestByAssists, TeamStatsFragment.Type.ASSISTS);
//                udateRows(teamService, bestByBlocks, TeamStatsFragment.Type.BLOCKS);
//                udateRows(teamService, bestByRebounds, TeamStatsFragment.Type.REBOUNDS);

            }
        });

    }

    public void updateRows(TeamService teamService, ArrayList<TeamStats> teamStats) {
        int n = teamStats.size();

    System.out.println("teamStats:" + n);
        for (int i = 0; i < n; i++) {
            int finalI = i;
            double grades = -1;
            int wins = -1;
            int loses = -1;
            int games = -1;

            TableLayout layout = binding.teamsContainer;

            grades = teamStats.get(i).getGrades();
            wins = teamStats.get(i).getWins();
            loses = teamStats.get(i).getLoses();
            games = teamStats.get(i).getWins() + teamStats.get(i).getLoses();


            //double finalValue = value;
            TableLayout finalLayout = layout;
            int finalGames = games;
            int finalWins = wins;
            int finalLoses = loses;
            double finalGrades = grades;
            teamService.findTeamById(teamStats.get(i).getTeamId(), new CallbackListener<Team>() {
                @Override
                public void callback(Team returnedObject) {
                    try {
                        createRow(finalLayout,
                                returnedObject.getTeamName(), finalGames,finalWins,finalLoses,finalGrades);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


    }

    public void createRow(TableLayout teamsContainer,String name, int games, int wins, int loses , double grades) throws InterruptedException {
        TeamScore.this.requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View teamScoreLayout = TeamScoreLayout.createTeamScoreLayout(TeamScore.this, name,games,wins,loses,grades);
                teamsContainer.addView(teamScoreLayout);
            }

        });
    }
//    public void addTeamScoreLayout(TableLayout teamsContainer){
//        int position = 0;
//
//            View playersLayout = TeamScoreLayout.createTeamScoreLayout(this, team.getTeamName());
//            teamsContainer.addView(playersLayout);
//
//    }

}