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
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentAdminsViewBinding;
import uom.team2.weball_statistics.databinding.FragmentPopupViewBinding;

public class popupViewTwoPoints extends Dialog implements
        android.view.View.OnClickListener{

    public Activity c;
    public Dialog d;
    public Button yes, no;
    private DBDataRecovery dbdatarecovery;
    private String str;
    Stats playerStats;
    Stats teamStats;
    private int points;



    public popupViewTwoPoints(Activity a, int p, Stats ps, Stats ts, DBDataRecovery dbd) {
        super(a);
        points=p;
        dbdatarecovery = dbd;
        playerStats = ps;
        teamStats = ts;
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_popup_view);

        TextView text=(TextView)findViewById(R.id.dialog_info);
        text.setText("2-Pointer Basket Made?");

        yes = (Button) findViewById(R.id.dialog_Yes);
        no = (Button) findViewById(R.id.dialog_No);
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        dismiss();
    }
}