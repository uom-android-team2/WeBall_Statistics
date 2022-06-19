package uom.team2.weball_statistics.UI_Controller.LiveController;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.Service.DAOLiveMatchService;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.UIHandler;
import uom.team2.weball_statistics.databinding.MatchHeaderLayoutBinding;

/*
 * @author Leonard Pepa ics20033
 */
public class MatchHeaderFragment extends Fragment {

    private MatchHeaderLayoutBinding binding;
    private Match match;
    private Team teamLandlord;
    private Team teamGuest;
    private ProgressDialog progressDialog;

    public MatchHeaderFragment() {

    }

    public static MatchHeaderFragment getInstance(Bundle bundle) {
        MatchHeaderFragment matchHeaderFragment = new MatchHeaderFragment();
        matchHeaderFragment.setArguments(bundle);
        return matchHeaderFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = MatchHeaderLayoutBinding.inflate(inflater, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {

            match = (Match) bundle.getSerializable("match");
            teamLandlord = (Team) bundle.getSerializable("teamLandlord");
            teamGuest = (Team) bundle.getSerializable("teamGuest");
        }
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        if (binding != null && match != null && teamGuest != null && teamGuest != null) {
            UIHandler.updateTeamImageInMatch(this, teamLandlord, binding.team1.getRoot());
            UIHandler.updateTeamImageInMatch(this, teamGuest, binding.team2.getRoot());
            DAOLiveMatchService.getInstance().clockDataListener(this, binding.clock.clockText, match.getId());
            DAOLiveMatchService.getInstance().setListenerForPoints(this, binding.scoreText, match.getId(), teamLandlord.getId(), teamGuest.getId());
            binding.matchHeaderWeek.setText("Week " + match.getDate());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}


