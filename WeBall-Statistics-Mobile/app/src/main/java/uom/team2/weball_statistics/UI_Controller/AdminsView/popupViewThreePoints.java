package uom.team2.weball_statistics.UI_Controller.AdminsView;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.IOException;

import uom.team2.weball_statistics.Model.Config;
import uom.team2.weball_statistics.Model.Statistics.DBDataRecovery;
import uom.team2.weball_statistics.Model.Statistics.PlayerStats;
import uom.team2.weball_statistics.Model.Statistics.Stats;
import uom.team2.weball_statistics.Model.Statistics.TeamStats;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentAdminsViewBinding;
import uom.team2.weball_statistics.databinding.FragmentPopupViewBinding;

public class popupViewThreePoints extends Dialog implements
        android.view.View.OnClickListener{

    public Activity c;
    public Dialog d;
    public Button yes, no;
    private String str;
    private Stats playerStats;
    private Stats teamStats;
    private DBDataRecovery dbdatarecovery;
private int points;



    public popupViewThreePoints(Activity a, int p, Stats ps, Stats ts, DBDataRecovery dbd) {
        super(a);

        dbdatarecovery = dbd;
        playerStats = ps;
        teamStats = ts;
        points=p;
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_popup_view);

        TextView text=(TextView)findViewById(R.id.dialog_info);
        text.setText("3-Pointer Basket Made?");

        yes = (Button) findViewById(R.id.dialog_Yes);
        no = (Button) findViewById(R.id.dialog_No);
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
