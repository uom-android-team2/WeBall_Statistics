package uom.team2.weball_statistics.UI_Controller.LiveController.Comments;

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
import uom.team2.weball_statistics.databinding.FragmentLiveGameCommentsBinding;

/*
 * @author Minas - Theodoros Charakopoulos ics20072
 */
public class LiveGameComments extends Fragment {

    private FragmentLiveGameCommentsBinding binding;
    private DAOAction daoAction;
    private Match match;
    private Team teamLandlord;
    private Team teamGuest;

    public LiveGameComments() {
    }

    public static LiveGameComments getInstance(Bundle bundle) {
        LiveGameComments liveGameComments = new LiveGameComments();
        liveGameComments.setArguments(bundle);
        return liveGameComments;
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
        binding = FragmentLiveGameCommentsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        match = (Match) bundle.getSerializable("match");
        teamLandlord = (Team) bundle.getSerializable("teamLandlord");
        teamGuest = (Team) bundle.getSerializable("teamGuest");

        daoAction.getRealTimeCommentsData(match, LiveGameComments.this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public FragmentLiveGameCommentsBinding getBinding() {
        return binding;
    }
}
