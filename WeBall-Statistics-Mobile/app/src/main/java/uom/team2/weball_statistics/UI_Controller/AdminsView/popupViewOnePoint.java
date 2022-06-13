package uom.team2.weball_statistics.UI_Controller.AdminsView;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.BelongsTo;
import uom.team2.weball_statistics.Model.Actions.Shots.Shot;
import uom.team2.weball_statistics.Model.Actions.Shots.ShotType;
import uom.team2.weball_statistics.Model.Actions.Turnover.Turnover;
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

public class popupViewOnePoint extends Dialog implements android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    Stats playerStats;
    Stats teamStats;
    private final DBDataRecovery dataRecovery;
    private String str;
    private Match match;
    private Team team;
    private Player player;
    private String time; //For action when happened
    private final int points;


    public popupViewOnePoint(Activity a, int p, Stats ps, Stats ts, DBDataRecovery dbd, Match m, Team team, Player player, String time) {
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
                DAOLiveTeamService.getInstance().updateByMatchAndTeamId(match.getId(),team.getId(), LiveStatisticsEnum.succesful_threepointer);
                DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(),player.getId(), LiveStatisticsEnum.succesful_threepointer);

                //Insert freethrow's action to firebase
                Action freeThrowAction = null;

                if (this.match.getTeamLandlord_id() == this.team.getId()) {
                    freeThrowAction = new Shot(String.valueOf(time), BelongsTo.HOME, player, team, ShotType.FREETHROW, true, null);
                } else if (this.match.getTeamguest_id() == this.team.getId()) {
                    freeThrowAction = new Shot(String.valueOf(time), BelongsTo.GUEST, player, team, ShotType.FREETHROW, true, null);
                }

                if (freeThrowAction != null) {
                    DAOAction.getInstance().insert(freeThrowAction, match);
                }

                //dismiss();
                break;
            case R.id.dialog_No:
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
            DAOLiveTeamService.getInstance().updateByMatchAndTeamId(match.getId(),team.getId(), LiveStatisticsEnum.total_freethrow);
            DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(),player.getId(), LiveStatisticsEnum.total_freethrow);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        dismiss();
    }
}