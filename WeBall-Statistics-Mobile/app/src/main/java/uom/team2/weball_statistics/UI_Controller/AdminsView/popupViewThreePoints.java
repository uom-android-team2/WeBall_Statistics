package uom.team2.weball_statistics.UI_Controller.AdminsView;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.BelongsTo;
import uom.team2.weball_statistics.Model.Actions.Shots.Shot;
import uom.team2.weball_statistics.Model.Actions.Shots.ShotComment;
import uom.team2.weball_statistics.Model.Actions.Shots.ShotType;
import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Statistics.DBDataRecovery;
import uom.team2.weball_statistics.Model.Statistics.Stats;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.DAOAction;
import uom.team2.weball_statistics.Service.DAOLiveMatchService;
import uom.team2.weball_statistics.Service.DAOLivePlayerStatistics;
import uom.team2.weball_statistics.UIFactory.LayoutFactory;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.LiveStatisticsEnum;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.utils.Utils;

public class popupViewThreePoints extends Dialog implements
        android.view.View.OnClickListener {

    private final Stats playerStats;
    private final Stats teamStats;
    private final DBDataRecovery dbdatarecovery;
    private final int points;
    private final Match match;
    private final Team team;
    private final Player player;
    private final String time; //For the actions when happened
    private final View view;
    public Activity c;
    public Dialog d;
    public Button yes, no;
    private String str;

    public popupViewThreePoints(Activity a, View view, int p, Stats ps, Stats ts, DBDataRecovery dbd, Match m, Team team, Player player, String time) {
        super(a);

        dbdatarecovery = dbd;
        playerStats = ps;
        teamStats = ts;
        points = p;
        match = m;
        this.team = team;
        this.player = player;
        this.time = time;
        // TODO Auto-generated constructor stub
        this.c = a;
        this.view = view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_popup_view);

        TextView text = findViewById(R.id.dialog_info);
        text.setText("3-Pointer Basket Made?");

        yes = findViewById(R.id.dialog_Yes);
        no = findViewById(R.id.dialog_No);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_Yes:
                playerStats.setSuccessfulThreePointer();
                playerStats.setSuccessfulEffort();
                teamStats.setSuccessfulThreePointer();
                teamStats.setSuccessfulEffort();

                DAOLiveMatchService.getInstance().updateByMatchAndTeamId(match.getId(), team.getId(), LiveStatisticsEnum.successful_threepointer);
                DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(), player.getId(), LiveStatisticsEnum.successful_threepointer);

                LayoutFactory.createSnackbar(view, "Successful three-pointer for " + player.getName() + " " + player.getSurname(), R.color.success_green).show();

                //Insert 3point's action to firebase
                Action treePointThrowAction = null;
                Action treePointThrowComment = null;
                if (this.match.getTeamLandlord_id() == this.team.getId()) {
                    treePointThrowAction = new Shot(String.valueOf(time), BelongsTo.HOME, player, team, ShotType.THREE_POINTER, true, null);
                    treePointThrowComment = new ShotComment(String.valueOf(time), BelongsTo.HOME, player, team, ShotType.THREE_POINTER, true, null, getContext());
                } else if (this.match.getTeamguest_id() == this.team.getId()) {
                    treePointThrowAction = new Shot(String.valueOf(time), BelongsTo.GUEST, player, team, ShotType.THREE_POINTER, true, null);
                    treePointThrowComment = new ShotComment(String.valueOf(time), BelongsTo.GUEST, player, team, ShotType.THREE_POINTER, true, null, getContext());
                }

                if (treePointThrowAction != null) {
                    DAOAction.getInstance().insertAction(treePointThrowAction, match);
                }

                if (treePointThrowComment != null) {
                    DAOAction.getInstance().insertCommentDesc(treePointThrowComment, match);
                }

                dismiss();
                break;
            case R.id.dialog_No:
                DAOLiveMatchService.getInstance().updateByMatchAndTeamId(match.getId(), team.getId(), LiveStatisticsEnum.total_threepointer);
                DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(), player.getId(), LiveStatisticsEnum.total_threepointer);
                LayoutFactory.createSnackbar(view, "Unsuccessful three-pointer for " + player.getName() + " " + player.getSurname(), R.color.missed)
                        .setTextColor(Utils.getColor(getContext(), R.color.black))
                        .show();

                Toast.makeText(c.getApplicationContext(), "Unsuccessful three-pointer for" + player.getName() + " " + player.getSurname(), Toast.LENGTH_LONG).show();
                //Insert 3point's action to firebase
                Action treePointThrowCommentMissed = null;
                if (this.match.getTeamLandlord_id() == this.team.getId()) {
                    treePointThrowCommentMissed = new ShotComment(String.valueOf(time), BelongsTo.HOME, player, team, ShotType.THREE_POINTER, false, null, getContext());
                } else if (this.match.getTeamguest_id() == this.team.getId()) {
                    treePointThrowCommentMissed = new ShotComment(String.valueOf(time), BelongsTo.GUEST, player, team, ShotType.THREE_POINTER, false, null, getContext());
                }

                if (treePointThrowCommentMissed != null) {
                    DAOAction.getInstance().insertCommentDesc(treePointThrowCommentMissed, match);
                }
                dismiss();
                break;
            default:
                break;
        }
        playerStats.setTotalThreePointer();
        playerStats.setTotalEffort();
        teamStats.setTotalThreePointer();
        teamStats.setTotalEffort();
        try {
            dbdatarecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dbdatarecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
