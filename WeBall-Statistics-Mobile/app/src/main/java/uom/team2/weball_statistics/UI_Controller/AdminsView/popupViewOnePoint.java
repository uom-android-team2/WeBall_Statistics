package uom.team2.weball_statistics.UI_Controller.AdminsView;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

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

public class popupViewOnePoint extends Dialog implements android.view.View.OnClickListener {

    private final DBDataRecovery dataRecovery;
    private final int points;
    private final Match match;
    private final Team team;
    private final Player player;
    private final String time; //For action when happened
    private final View view;
    public Activity c;
    public Dialog d;
    public Button yes, no;
    Stats playerStats;
    Stats teamStats;
    private String str;

    public popupViewOnePoint(Activity a, View view, int p, Stats ps, Stats ts, DBDataRecovery dbd, Match m, Team team, Player player, String time) {
        super(a);
        playerStats = ps;
        teamStats = ts;
        points = p;
        dataRecovery = dbd;
        this.match = m;
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
        text.setText("Freethrow Basket Made?");

        yes = findViewById(R.id.dialog_Yes);
        no = findViewById(R.id.dialog_No);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

        //


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_Yes:
                playerStats.setSuccessfulFreeThrow();
                teamStats.setSuccessfulFreeThrow();
                DAOLiveMatchService.getInstance().updateByMatchAndTeamId(match.getId(), team.getId(), LiveStatisticsEnum.successful_freethrow);
                DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(), player.getId(), LiveStatisticsEnum.successful_freethrow);

                LayoutFactory.createSnackbar(view, "Successful freethrow-pointer for " + player.getName() + " " + player.getSurname(), R.color.success_green).show();

                //Insert freethrow's action to firebase
                Action freeThrowAction = null;
                Action freeThrowComment = null;
                if (this.match.getTeamLandlord_id() == this.team.getId()) {
                    freeThrowAction = new Shot(String.valueOf(time), BelongsTo.HOME, player, team, ShotType.FREETHROW, true, null);
                    freeThrowComment = new ShotComment(String.valueOf(time), BelongsTo.HOME, player, team, ShotType.FREETHROW, true, null, getContext());
                } else if (this.match.getTeamguest_id() == this.team.getId()) {
                    freeThrowAction = new Shot(String.valueOf(time), BelongsTo.GUEST, player, team, ShotType.FREETHROW, true, null);
                    freeThrowComment = new ShotComment(String.valueOf(time), BelongsTo.GUEST, player, team, ShotType.FREETHROW, true, null, getContext());
                }

                if (freeThrowAction != null) {
                    DAOAction.getInstance().insertAction(freeThrowAction, match);
                }

                if (freeThrowComment != null) {
                    DAOAction.getInstance().insertCommentDesc(freeThrowComment, match);
                }

                //dismiss();
                break;
            case R.id.dialog_No:
                DAOLiveMatchService.getInstance().updateByMatchAndTeamId(match.getId(), team.getId(), LiveStatisticsEnum.total_freethrow);
                DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(), player.getId(), LiveStatisticsEnum.total_freethrow);
                LayoutFactory.createSnackbar(view, "Unsuccessful freethrow-pointer for" + player.getName() + " " + player.getSurname(), R.color.missed)
                        .setTextColor(Utils.getColor(getContext(), R.color.black))
                        .show();

                //Insert missed freethrow's action to firebase
                Action freeThrowCommentMissed = null;
                if (this.match.getTeamLandlord_id() == this.team.getId()) {
                    freeThrowCommentMissed = new ShotComment(String.valueOf(time), BelongsTo.HOME, player, team, ShotType.FREETHROW, false, null, getContext());
                } else if (this.match.getTeamguest_id() == this.team.getId()) {
                    freeThrowCommentMissed = new ShotComment(String.valueOf(time), BelongsTo.GUEST, player, team, ShotType.FREETHROW, false, null, getContext());
                }

                if (freeThrowCommentMissed != null) {
                    DAOAction.getInstance().insertCommentDesc(freeThrowCommentMissed, match);
                }

                dismiss();
                break;
            default:
                break;
        }
        playerStats.setTotalFreeThrow();
        teamStats.setTotalFreeThrow();
        try {
            dataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        dismiss();
    }
}