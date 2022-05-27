package uom.team2.weball_statistics.UI_Controller.TeamScore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentTeamScoreBinding;


public class TeamScore extends Fragment {

    private FragmentTeamScoreBinding binding;

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
        binding.bestPerPositionButton.setOnClickListener(e -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_teamScore_to_bestStarting5);
        });
    }
}