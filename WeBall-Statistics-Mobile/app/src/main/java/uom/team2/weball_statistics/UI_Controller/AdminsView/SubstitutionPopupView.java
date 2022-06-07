package uom.team2.weball_statistics.UI_Controller.AdminsView;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;

import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;

public class SubstitutionPopupView extends Dialog implements
        android.view.View.OnClickListener{

    public Activity c;
    public Dialog d;
    public TextView sub1, sub2,sub3,sub4,sub5,xbutton;
    private String str;
    private String n;
    private Player helperVariable;
    private Player swapPlayer;
    private Team team;


    public SubstitutionPopupView(Activity a,Player p, Team t) {
        super(a);
        swapPlayer=p;
        team=t;
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.substitution_popup_view);


        xbutton=(TextView) findViewById(R.id.x_button);
        sub1 = (TextView) findViewById(R.id.sub1_text);
        sub2 = (TextView) findViewById(R.id.sub2_text);
        sub3 = (TextView) findViewById(R.id.sub2_text);
        sub4 = (TextView) findViewById(R.id.sub2_text);
        sub5 = (TextView) findViewById(R.id.sub2_text);


        sub1.setText(team.getSubPlayers().get(0).getName());
        sub2.setText(team.getSubPlayers().get(1).getName());
        sub3.setText(team.getSubPlayers().get(2).getName());
        sub4.setText(team.getSubPlayers().get(3).getName());
        sub5.setText(team.getSubPlayers().get(4).getName());



        xbutton.setOnClickListener(this);
        sub1.setOnClickListener(this);
        sub2.setOnClickListener(this);
        sub3.setOnClickListener(this);
        sub4.setOnClickListener(this);
        sub5.setOnClickListener(this);

        //


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.x_button:
                dismiss();
                break;

            case R.id.sub1_text:

                helperVariable=team.getSubPlayers().get(0);
                team.setSubPlayers(0,swapPlayer);
                team.setKeyPlayers(0,helperVariable);

                dismiss();
                break;
            case R.id.sub2_text:
                helperVariable=team.getSubPlayers().get(1);
                team.setSubPlayers(1,swapPlayer);
                team.setKeyPlayers(1,helperVariable);
                dismiss();
                break;
            case R.id.sub3_text:
                helperVariable=team.getSubPlayers().get(2);
                team.setSubPlayers(2,swapPlayer);
                team.setKeyPlayers(2,helperVariable);

                dismiss();
                break;
            case R.id.sub4_text:
                helperVariable=team.getSubPlayers().get(3);
                team.setSubPlayers(3,swapPlayer);
                team.setKeyPlayers(3,helperVariable);

                dismiss();
                break;
            case R.id.sub5_text:
                helperVariable=team.getSubPlayers().get(4);
                team.setSubPlayers(4,swapPlayer);
                team.setKeyPlayers(4,helperVariable);

                dismiss();
                break;

            default:
                break;
        }
        dismiss();
    }
}