package uom.team2.weball_statistics.UI_Controller.AdminsView;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Statistics.DBDataRecovery;
import uom.team2.weball_statistics.Model.Statistics.Stats;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.DAOLivePlayerStatistics;
import uom.team2.weball_statistics.Service.DAOLiveTeamService;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.LiveStatisticsEnum;
import uom.team2.weball_statistics.configuration.Config;

public class popupViewThreePoints extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    private String str;
    private final Stats playerStats;
    private final Stats teamStats;
    private final DBDataRecovery dbdatarecovery;
    private Match match;
    private  Team team;
    private  Player player;
    private final int points;


    public popupViewThreePoints(Activity a, int p, Stats ps, Stats ts, DBDataRecovery dbd, Match m, Team t, Player player) {
        super(a);

        dbdatarecovery = dbd;
        playerStats = ps;
        teamStats = ts;
        points = p;
        match = m;
        team = t;
        // TODO Auto-generated constructor stub
        this.c = a;
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
                DAOLiveTeamService.getInstance().updateByMatchAndTeamId(match.getId(),team.getId(), LiveStatisticsEnum.successful_effort);
                DAOLiveTeamService.getInstance().updateByMatchAndTeamId(match.getId(),team.getId(), LiveStatisticsEnum.succesful_threepointer);
                DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(),player.getId(), LiveStatisticsEnum.successful_effort);
                DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(),player.getId(), LiveStatisticsEnum.succesful_threepointer);
                dismiss();
                break;
            case R.id.dialog_No:

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
            DAOLiveTeamService.getInstance().updateByMatchAndTeamId(match.getId(),team.getId(), LiveStatisticsEnum.total_effort);
            DAOLiveTeamService.getInstance().updateByMatchAndTeamId(match.getId(),team.getId(), LiveStatisticsEnum.total_threepointer);
            DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(),player.getId(), LiveStatisticsEnum.total_effort);
            DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(),player.getId(), LiveStatisticsEnum.total_threepointer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
