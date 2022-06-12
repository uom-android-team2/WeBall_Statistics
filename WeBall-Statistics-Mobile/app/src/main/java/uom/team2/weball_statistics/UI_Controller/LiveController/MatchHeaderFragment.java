package uom.team2.weball_statistics.UI_Controller.LiveController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import uom.team2.weball_statistics.R;

import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.Service.DAOLiveTeamService;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.UIHandler;
import uom.team2.weball_statistics.databinding.MatchHeaderLayoutBinding;


public class MatchHeaderFragment extends Fragment {

    private MatchHeaderLayoutBinding binding;
    private Bundle bundle;

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
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.bundle = bundle;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = MatchHeaderLayoutBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (this.bundle != null) {
            Match match = (Match) bundle.getSerializable("match");
            Team teamLandlord = (Team) bundle.getSerializable("teamLandlord");
            Team teamGuest = (Team) bundle.getSerializable("teamGuest");

            try {
                DAOLiveTeamService.getInstance().setListenerForPoints(this, binding, match.getId(), teamLandlord.getId(), teamGuest.getId());
                UIHandler.updateTeamImageInMatch(this, teamLandlord, binding.getRoot().findViewById(R.id.team1));
                UIHandler.updateTeamImageInMatch(this, teamGuest, binding.getRoot().findViewById(R.id.team2));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}


