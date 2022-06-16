package uom.team2.weball_statistics.UI_Controller.LiveController.Comments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Date;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.BelongsTo;
import uom.team2.weball_statistics.Model.Actions.MatchFlow.FlowType;
import uom.team2.weball_statistics.Model.Actions.MatchFlow.MatchFlow;
import uom.team2.weball_statistics.Model.Actions.MatchFlow.MatchFlowComment;
import uom.team2.weball_statistics.Model.Actions.ReboundAction.ReboundComment;
import uom.team2.weball_statistics.Model.Actions.SBFActions.SBFAction;
import uom.team2.weball_statistics.Model.Actions.SBFActions.SBFActionComment;
import uom.team2.weball_statistics.Model.Actions.SBFActions.SBFActionType;
import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Referee;
import uom.team2.weball_statistics.Model.Status;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.DAOAction;
import uom.team2.weball_statistics.databinding.FragmentLiveGameCommentsBinding;

/*
 * @author Minas - Theodoros Charakopoulos ics20072
 */
public class LiveGameComments extends Fragment {

    private FragmentLiveGameCommentsBinding binding;

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
    }

    @Override
    public void onStart() {
        super.onStart();
//        DAOLiveMatchService.getInstance().clockDataListener(this, binding.header.clock.clockText, 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLiveGameCommentsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Player player = new Player("Minas", "Charakopoulos");
        Team team = new Team("Paok");
        Action actionRebound = new ReboundComment("30.21", BelongsTo.HOME, player, team);
        Action action = new MatchFlowComment("0.01", FlowType.START, this.getContext());
        Action actionSteal = new SBFActionComment("32.32", BelongsTo.HOME, player, team, SBFActionType.STEAL, this.getContext());
        Action actionFoul = new SBFActionComment("32.32", BelongsTo.HOME, player, team, SBFActionType.FOUL, this.getContext());
        Action actionBlock = new SBFActionComment("32.32", BelongsTo.HOME, player, team, SBFActionType.BLOCK, this.getContext());
        System.out.println(action.getActionDesc());
        System.out.println(actionRebound.getActionDesc());
        System.out.println(actionSteal.getActionDesc());
        System.out.println(actionFoul.getActionDesc());
        System.out.println(actionBlock.getActionDesc());
        Match match = new Match(1, null, null, new Date(), Status.ONGOING);
        DAOAction.getInstance().insertCommentDesc(action, match);
        DAOAction.getInstance().insertCommentDesc(actionRebound, match);
        DAOAction.getInstance().insertCommentDesc(actionSteal, match);
        DAOAction.getInstance().insertCommentDesc(actionFoul, match);
        DAOAction.getInstance().insertCommentDesc(actionBlock, match);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
