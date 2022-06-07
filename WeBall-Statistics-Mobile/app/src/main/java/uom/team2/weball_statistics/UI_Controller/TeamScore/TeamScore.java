package uom.team2.weball_statistics.UI_Controller.TeamScore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import java.util.ArrayList;

import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
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

    public void addTeamScoreLayout(TableLayout teamsContainer, ArrayList<Team> teams){
        for (Team team : teams) {
            View playersLayout = TeamScoreLayout.createTeamScoreLayout(this, team.getTeamName());
            teamsContainer.addView(playersLayout);
        }
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
        binding.bestPerPositionButton.setOnClickListener(e -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_teamScore_to_bestStarting5);
        });

        binding.imageView2.setOnClickListener(e -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_teamScore_to_completedMatchStats3);
        });

        onStart();
        addTeamScoreLayout(binding.scoreTable,teams);
    }

    public void onStart() {
        super.onStart();
        try {
            TeamScoreHandler h = new TeamScoreHandler();
            h.findTeams();
            System.out.println("data:" + h.getTeams().get(0));
            teams = h.getTeams();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}