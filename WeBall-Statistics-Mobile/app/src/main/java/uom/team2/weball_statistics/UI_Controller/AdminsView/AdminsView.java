package uom.team2.weball_statistics.UI_Controller.AdminsView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import android.os.SystemClock;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Stack;

import uom.team2.weball_statistics.MainActivity;
import uom.team2.weball_statistics.Model.Championship;
import uom.team2.weball_statistics.Model.Coach;
import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.PlayerPosition;
import uom.team2.weball_statistics.Model.Statistics.TeamStats;
import uom.team2.weball_statistics.Model.Status;
import uom.team2.weball_statistics.Model.Team;

import java.io.IOException;

import uom.team2.weball_statistics.MainActivity;
import uom.team2.weball_statistics.Model.Config;
import uom.team2.weball_statistics.Model.PlayerLiveStatistics;
import uom.team2.weball_statistics.Model.Statistics.DBDataRecovery;
import uom.team2.weball_statistics.Model.Statistics.Stats;

import uom.team2.weball_statistics.Model.TeamLiveStatistics;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.DAOLivePlayerStatistics;
import uom.team2.weball_statistics.databinding.FragmentAdminsViewBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminsView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminsView extends Fragment {
    private FragmentAdminsViewBinding binding;
    private Chronometer chronometer;
    private boolean running=false;
    private Button start_end_button;
    private long pauseOffset;
    private boolean started=false;
    private int playerChecked=1;
    private Stack<Team> undoTeamStack=new Stack<Team>();
    private Stack<Player> undoPlayerStack=new Stack<Player>();
    private Stack<Integer>  undoButtonStack =new Stack<Integer>();
    private Match match;
    private Team teamLandlord;
    private Team teamGuest;
    private ArrayList<Player> keyPlayersLandlord=new ArrayList<Player>();
    private ArrayList<Player> subPlayersLandlord=new ArrayList<Player>();
    private ArrayList<Player> keyPlayersGuest=new ArrayList<Player>();
    private ArrayList<Player> subPlayersGuest=new ArrayList<Player>();
    private TextView freeThrowBtn;
    private TextView twoPointBtn;
    private TextView threePointBtn;
    private TextView reboundBtn;
    private TextView assistBtn;
    private TextView stealBtn;
    private TextView blockBtn;
    private TextView foulBtn;
    private TextView turnoverBtn;
    private ArrayList<Player> playersTeamLandlord = new ArrayList<Player>();
    private ArrayList<Player> playersTeamGuest = new ArrayList<Player>();
    private ArrayList<PlayerLiveStatistics> playerLiveStatisticsTeamLanLord = new ArrayList<PlayerLiveStatistics>();
    private ArrayList<PlayerLiveStatistics> playerLiveStatisticsTeamGuest = new ArrayList<PlayerLiveStatistics>();
    private ArrayList<TeamLiveStatistics> teamLiveStatisticsLanLord = new ArrayList<TeamLiveStatistics>();
    private ArrayList<TeamLiveStatistics> teamLiveStatisticsGuest = new ArrayList<TeamLiveStatistics>();
    private PlayerLiveStatistics playerCheckedLiveStatistics = null;
    //private TeamLiveStatistics teamCheckedLiveStatistics = null;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminsView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminsViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminsView newInstance(String param1, String param2) {
        AdminsView fragment = new AdminsView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentAdminsViewBinding.inflate(inflater,container,false);

        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        match = new Match(1,new Team(1,"jif", "kfsd", "euj"), new Team(2,"fkns", "kdlf", "akdk"), new Date(), Status.COMPLETED);
        teamLandlord = match.getTeamLandlord();
        teamGuest = match.getGuest();


        //Banner Buttons -When the first team is selected -> variable "teamSelected"=false. Else, true.
        // Banner1
        binding.team1Banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //put background color to the banner so the admin knows which team is selected
                GradientDrawable shape =  new GradientDrawable();
                shape.setCornerRadius( 75 );
                shape.setColor(getResources().getColor(R.color.alt_grey));
                view.setBackground(shape);

                //remove the background color from the other banner
                binding.team2Banner.setBackgroundColor(0x00000000);


                //Load data for this team
                teamLandlord=match.getTeamLandlord();

                //Load the data of the first team players.

            }
        });

        // Banner2
        binding.team2Banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //put background color to the banner so the admin knows which team is selected
                GradientDrawable shape = new GradientDrawable();
                shape.setCornerRadius(75);
                shape.setColor(getResources().getColor(R.color.alt_grey));
                view.setBackground(shape);

                //remove the background color from the other banner
                binding.team1Banner.setBackgroundColor(0x00000000);

                //Load data for this team
                teamGuest=match.getGuest();
                //Load the data of the second team players.

            }
        });

        //Start Button
        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!started) {
                   binding.clock.setBase(SystemClock.elapsedRealtime());
                   binding.clock.start();
                    running = true;
                    started=true;
                    binding.startButton.setText("End");
                    binding.pauseButton.setEnabled(true);

                }
                else{
                    binding.clock.stop();
                    binding.startButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f);
                    binding.startButton.setText("Start/End");
                    binding.startButton.setEnabled(false);
                    binding.pauseButton.setEnabled(false);
                    binding.pauseButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f);
                    binding.pauseButton.setText("Pause/Continue");
                }
            }

        });

        //Pause Button
        binding.pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!running) {
                    binding.clock.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    binding.clock.start();
                    binding.pauseButton.setText("Pause");
                    running = true;

                } else {
                    binding.pauseButton.setText("Continue");
                    binding.clock.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - binding.clock.getBase();
                    running = false;
                }
            }
        });

       //Undo Button
        binding.undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        //Player Buttons-
        binding.player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GradientDrawable shape =  new GradientDrawable();
                shape.setCornerRadius( 75 );
                shape.setColor(getResources().getColor(R.color.alt_grey));
                view.setBackground(shape);

                if(!(playerChecked==1)){
                    deleteThePreviousBackground();
                    playerChecked=1;
                }

            }
        });

        binding.player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GradientDrawable shape =  new GradientDrawable();
                shape.setCornerRadius( 75 );
                shape.setColor(getResources().getColor(R.color.alt_grey));
                view.setBackground(shape);

                if(!(playerChecked==2)){
                    deleteThePreviousBackground();
                    playerChecked=2;

                }

            }
        });

        binding.player3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GradientDrawable shape =  new GradientDrawable();
                shape.setCornerRadius( 75 );
                shape.setColor(getResources().getColor(R.color.alt_grey));
                view.setBackground(shape);

                if(!(playerChecked==3)){
                    deleteThePreviousBackground();
                    playerChecked=3;

                }

            }
        });

        binding.player4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GradientDrawable shape =  new GradientDrawable();
                shape.setCornerRadius( 75 );
                shape.setColor(getResources().getColor(R.color.alt_grey));
                view.setBackground(shape);

                if(!(playerChecked==4)){
                    deleteThePreviousBackground();
                    playerChecked=4;

                }

            }
        });

        binding.player5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GradientDrawable shape =  new GradientDrawable();
                shape.setCornerRadius( 75 );
                shape.setColor(getResources().getColor(R.color.alt_grey));
                view.setBackground(shape);

                if(!(playerChecked==5)){
                    deleteThePreviousBackground();
                    playerChecked=5;

                }



            }
        });

        //Substitution Button
        binding.substitutionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //steile ton paikti

                //emfanise to popup
                SubstitutionPopupView ppv=new SubstitutionPopupView(getActivity(),"panais");
                ppv.show();
            }
        });

        freeThrowBtn = binding.freethrowButton;
        twoPointBtn = binding.twoPointerButton;
        threePointBtn = binding.threePointerButton;
        reboundBtn = binding.reboundButton;
       assistBtn = binding.assistButton;
       stealBtn = binding.stealButton;
       blockBtn =  binding.blockButton;
       foulBtn = binding.foulButton;
       turnoverBtn = binding.turnoverButton;

        DBDataRecovery dataRecovery = new DBDataRecovery();

            try {
                Stats playerStats = dataRecovery.readData(Config.API_PLAYER_STATISTICS_COMPLETED, "1");
                Stats teamStats = dataRecovery.readData(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, "1");
                threePointBtn.setOnClickListener(e -> {
                    popupViewThreePoints ppv=new popupViewThreePoints(getActivity(),3,playerStats,teamStats,dataRecovery);
                    ppv.show();
                });
                freeThrowBtn.setOnClickListener(e ->{
                    popupViewOnePoint ppv=new popupViewOnePoint(getActivity(),1,playerStats,teamStats,dataRecovery);
                    ppv.show();

                });
                twoPointBtn.setOnClickListener(e -> {
                    popupViewTwoPoints ppv= new popupViewTwoPoints(getActivity(),2,playerStats,teamStats,dataRecovery);
                    ppv.show();
                });
                reboundBtn.setOnClickListener(e -> updateRebound(playerStats, teamStats,dataRecovery));
                assistBtn.setOnClickListener(e -> updateAssist(playerStats,teamStats,dataRecovery));
                stealBtn.setOnClickListener(e -> updateSteal(playerStats,teamStats,dataRecovery));
                blockBtn.setOnClickListener(e -> updateBlock(playerStats,teamStats,dataRecovery));
                foulBtn.setOnClickListener(e -> updateFoul(playerStats,teamStats,dataRecovery));
                turnoverBtn.setOnClickListener(e -> updateTurnover(playerStats,teamStats,dataRecovery));

            } catch (Exception ex) {
                    ex.printStackTrace();
            }
    }

    private void updateAssist(Stats playerStats,Stats teamStats,DBDataRecovery dbDataRecovery){
        playerStats.setTotalAssists();
        teamStats.setTotalAssists();
       // PlayerLiveStatistics p = new PlayerLiveStatistics(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);
       // p.setAssist(playerStats.getTotalAssists());
        try {
            dbDataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dbDataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
           // DAOLivePlayerStatistics.getInstace().update(p);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateRebound(Stats playerStats,Stats teamStats,DBDataRecovery dbDataRecovery){
        playerStats.setTotalRebounds();
        teamStats.setTotalRebounds();
        try {
            dbDataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dbDataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateSteal(Stats playerStats,Stats teamStats,DBDataRecovery dbDataRecovery){
        playerStats.setTotalSteels();
        teamStats.setTotalSteels();
        try {
            dbDataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dbDataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateBlock(Stats playerStats,Stats teamStats,DBDataRecovery dataRecovery){
        playerStats.setTotalBlock();
        teamStats.setTotalBlock();
        try {
            dataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateFoul(Stats playerStats,Stats teamStats, DBDataRecovery dbDataRecovery){
        playerStats.setTotalFouls();
        teamStats.setTotalFouls();
        try {
            dbDataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dbDataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateTurnover(Stats playerStats,Stats teamStats,DBDataRecovery dbDataRecovery){
        playerStats.setTotalTurnovers();
        teamStats.setTotalTurnovers();
        try {
            dbDataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dbDataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //you call this function when you want to change player
    public void deleteThePreviousBackground(){
        if(playerChecked==1){
            binding.player1.setBackgroundColor(0x00000000);
        }
        else if(playerChecked==2){
            binding.player2.setBackgroundColor(0x00000000);
        }
        else if(playerChecked==3){
            binding.player3.setBackgroundColor(0x00000000);
        }
        else if(playerChecked==4){
            binding.player4.setBackgroundColor(0x00000000);
        }
        else if (playerChecked==5){
            binding.player5.setBackgroundColor(0x00000000);
        }
    }


}