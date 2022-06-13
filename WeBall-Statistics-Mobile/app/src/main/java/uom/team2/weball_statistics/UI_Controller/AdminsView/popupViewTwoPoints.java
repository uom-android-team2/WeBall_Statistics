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
import uom.team2.weball_statistics.Model.Actions.Shots.ShotType;
import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Statistics.DBDataRecovery;
import uom.team2.weball_statistics.Model.Statistics.Stats;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.DAOAction;
import uom.team2.weball_statistics.Service.DAOLivePlayerStatistics;
import uom.team2.weball_statistics.Service.DAOLiveTeamService;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.LiveStatisticsEnum;
import uom.team2.weball_statistics.configuration.Config;

public class popupViewTwoPoints extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    Stats playerStats;
    Stats teamStats;
    private final DBDataRecovery dbdatarecovery;
    private String str;
    private Match match;
    private Team team;
    private Player player;
    private long time; //For the action when happened;
    private final int points;


    public popupViewTwoPoints(Activity a, int p, Stats ps, Stats ts, DBDataRecovery dbd, Match match, Team team, Player player, long time) {
        super(a);
        points = p;
        dbdatarecovery = dbd;
        playerStats = ps;
        teamStats = ts;
        this.match = match;
        this.team = team;
        this.player = player;
        this.time = time;
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_popup_view);

        TextView text = findViewById(R.id.dialog_info);
        text.setText("2-Pointer Basket Made?");

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
                playerStats.setSuccessfulTwoPointer();
                playerStats.setSuccessfulEffort();
                teamStats.setSuccessfulTwoPointer();
                teamStats.setSuccessfulEffort();
                DAOLiveTeamService.getInstance().updateByMatchAndTeamId(match.getId(),team.getId(), LiveStatisticsEnum.successful_effort);
                DAOLiveTeamService.getInstance().updateByMatchAndTeamId(match.getId(),team.getId(), LiveStatisticsEnum.succesful_twopointer);
                DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(),player.getId(), LiveStatisticsEnum.successful_effort);
                DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(),player.getId(), LiveStatisticsEnum.succesful_twopointer);

                //Insert 2point's action to firebase
                Action twoPointThrowAction = null;

                if (this.match.getTeamLandlord_id() == this.team.getId()) {
                    twoPointThrowAction = new Shot(String.valueOf(time), BelongsTo.HOME, player, team, ShotType.TWO_POINTER, true, null);
                } else if (this.match.getTeamguest_id() == this.team.getId()) {
                    twoPointThrowAction = new Shot(String.valueOf(time), BelongsTo.GUEST, player, team, ShotType.TWO_POINTER, true, null);
                }

                if (twoPointThrowAction != null) {
                    DAOAction.getInstance().insert(twoPointThrowAction, match);
                }

                //dismiss();
                break;
            case R.id.dialog_No:
                dismiss();
                break;
            default:
                break;
        }
        playerStats.setTotalTwoPointer();
        playerStats.setTotalEffort();
        teamStats.setTotalTwoPointer();
        teamStats.setTotalEffort();
        try {
            dbdatarecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dbdatarecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
            DAOLiveTeamService.getInstance().updateByMatchAndTeamId(match.getId(),team.getId(), LiveStatisticsEnum.total_effort);
            DAOLiveTeamService.getInstance().updateByMatchAndTeamId(match.getId(),team.getId(), LiveStatisticsEnum.total_twopointer);
            DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(),player.getId(), LiveStatisticsEnum.total_effort);
            DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(),player.getId(), LiveStatisticsEnum.total_twopointer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        dismiss();
    }
}