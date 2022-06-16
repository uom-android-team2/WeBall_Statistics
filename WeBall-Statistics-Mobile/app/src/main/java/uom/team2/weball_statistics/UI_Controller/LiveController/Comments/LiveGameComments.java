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
import uom.team2.weball_statistics.Model.Actions.Shots.Assist;
import uom.team2.weball_statistics.Model.Actions.Shots.Shot;
import uom.team2.weball_statistics.Model.Actions.Shots.ShotComment;
import uom.team2.weball_statistics.Model.Actions.Shots.ShotType;
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
        Player playerAssist = new Player("Leo", "Pepa");
        Team team = new Team("Paok");
        Match match = new Match(1, null, null, new Date(), Status.ONGOING);
        Assist assist = new Assist(playerAssist);
        Action actionShot1Done = new ShotComment("32.31",BelongsTo.HOME, player, team, ShotType.FREETHROW, true, assist, this.getContext());
        Action actionShot2Done = new ShotComment("32.31",BelongsTo.HOME, player, team, ShotType.TWO_POINTER, true, assist, this.getContext());
        Action actionShot3Done = new ShotComment("32.31",BelongsTo.HOME, player, team, ShotType.THREE_POINTER, true, assist, this.getContext());
        Action actionShot1Missed = new ShotComment("32.31",BelongsTo.HOME, player, team, ShotType.FREETHROW, false, assist, this.getContext());
        Action actionShot2Missed = new ShotComment("32.31",BelongsTo.HOME, player, team, ShotType.TWO_POINTER, false, assist, this.getContext());
        Action actionShot3Missed = new ShotComment("32.31",BelongsTo.HOME, player, team, ShotType.THREE_POINTER, false, assist, this.getContext());
        System.out.println(actionShot1Done.getActionDesc());
        System.out.println(actionShot2Done.getActionDesc());
        System.out.println(actionShot3Done.getActionDesc());
        System.out.println(actionShot1Missed.getActionDesc());
        System.out.println(actionShot2Missed.getActionDesc());
        System.out.println(actionShot3Missed.getActionDesc());
        DAOAction.getInstance().insertCommentDesc(actionShot1Done, match);
        DAOAction.getInstance().insertCommentDesc(actionShot2Done, match);
        DAOAction.getInstance().insertCommentDesc(actionShot3Done, match);
        DAOAction.getInstance().insertCommentDesc(actionShot1Missed, match);
        DAOAction.getInstance().insertCommentDesc(actionShot2Missed, match);
        DAOAction.getInstance().insertCommentDesc(actionShot3Missed, match);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
