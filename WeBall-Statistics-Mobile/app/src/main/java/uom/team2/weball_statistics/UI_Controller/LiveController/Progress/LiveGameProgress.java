package uom.team2.weball_statistics.UI_Controller.LiveController.Progress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.Service.DAOAction;
import uom.team2.weball_statistics.databinding.FragmentLiveGameProgressBinding;

public class LiveGameProgress extends Fragment {

    private FragmentLiveGameProgressBinding binding;
    private final LiveProgressUIController liveProgressUIController = LiveProgressUIController.getInstance();
    private DAOAction daoAction;
    private Match match;
    private Team teamLandlord;
    private Team teamGuest;

    public LiveGameProgress() {
    }

    public static LiveGameProgress getInstance(Bundle bundle) {
        LiveGameProgress liveGameProgress = new LiveGameProgress();
        liveGameProgress.setArguments(bundle);
        return liveGameProgress;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        daoAction = DAOAction.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLiveGameProgressBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        match = (Match) bundle.getSerializable("match");
        teamLandlord = (Team) bundle.getSerializable("teamLandlord");
        teamGuest = (Team) bundle.getSerializable("teamGuest");

        daoAction.getRealTimeActionsData(match, LiveGameProgress.this);

        liveProgressUIController.fillMatchInformation(this, this.match, this.teamLandlord); //Fill details for the match
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public FragmentLiveGameProgressBinding getBinding() {
        return binding;
    }

}
