package uom.team2.weball_statistics.UI_Controller.AdminsView;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import uom.team2.weball_statistics.Model.Statistics.DBDataRecovery;
import uom.team2.weball_statistics.Model.Statistics.Stats;
import uom.team2.weball_statistics.R;
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
    private final int points;


    public popupViewThreePoints(Activity a, int p, Stats ps, Stats ts, DBDataRecovery dbd) {
        super(a);

        dbdatarecovery = dbd;
        playerStats = ps;
        teamStats = ts;
        points = p;
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
                //System.out.println(playerStats.getSuccessfulEffort());
                teamStats.setSuccessfulThreePointer();
                teamStats.setSuccessfulEffort();
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
