package uom.team2.weball_statistics.UI_Controller.AdminsView;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.PlayerLiveStatistics;
import uom.team2.weball_statistics.Model.Statistics.DBDataRecovery;
import uom.team2.weball_statistics.Model.Statistics.Stats;
import uom.team2.weball_statistics.Model.Status;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.Model.TeamLiveStatistics;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.DAOLivePlayerStatistics;
import uom.team2.weball_statistics.Service.DAOLiveTeamService;
import uom.team2.weball_statistics.Service.MatchService;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.LiveStatisticsEnum;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.databinding.FragmentAdminsViewBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminsView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminsView extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final Stack<Team> undoTeamStack = new Stack<Team>();
    private final Stack<Player> undoPlayerStack = new Stack<Player>();
    private final Stack<Integer> undoButtonStack = new Stack<Integer>();
    private final Match m = new Match(1, null, null, new Date(), Status.UPCOMING);
    private final ArrayList<Player> keyPlayersLandlord = new ArrayList<Player>();
    private final ArrayList<Player> subPlayersLandlord = new ArrayList<Player>();
    private final ArrayList<Player> keyPlayersGuest = new ArrayList<Player>();
    private final ArrayList<Player> subPlayersGuest = new ArrayList<Player>();
    private final ArrayList<Player> playersTeamLandlord = new ArrayList<Player>();
    private final ArrayList<Player> playersTeamGuest = new ArrayList<Player>();
    private final ArrayList<PlayerLiveStatistics> playerLiveStatisticsTeamLanLord = new ArrayList<PlayerLiveStatistics>();
    private final ArrayList<PlayerLiveStatistics> playerLiveStatisticsTeamGuest = new ArrayList<PlayerLiveStatistics>();
    private final ArrayList<TeamLiveStatistics> teamLiveStatisticsLanLord = new ArrayList<TeamLiveStatistics>();
    //private TeamLiveStatistics teamCheckedLiveStatistics = null;
    private final ArrayList<TeamLiveStatistics> teamLiveStatisticsGuest = new ArrayList<TeamLiveStatistics>();
    private final PlayerLiveStatistics playerCheckedLiveStatistics = null;
    private FragmentAdminsViewBinding binding;
    private Chronometer chronometer;
    private boolean running = false;
    private Button start_end_button;
    private long pauseOffset;
    private boolean started = false;
    private boolean teamSelected = false;
    private int playerChecked = 1;
    private Match match;
    private Team landLord;
    private Team guest;
    private Player playerObjChecked;
    private Team teamObj;
    private Team teamLand;
    private Team teamGuest;
    private Team teamLandlord;
    private TextView freeThrowBtn;
    private TextView twoPointBtn;
    private TextView threePointBtn;
    private TextView reboundBtn;
    private TextView assistBtn;
    private TextView stealBtn;
    private TextView blockBtn;
    private TextView foulBtn;
    private TextView turnoverBtn;
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

        binding = FragmentAdminsViewBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        freeThrowBtn = binding.freethrowButton;
        twoPointBtn = binding.twoPointerButton;
        threePointBtn = binding.threePointerButton;
        reboundBtn = binding.reboundButton;
        assistBtn = binding.assistButton;
        stealBtn = binding.stealButton;
        blockBtn = binding.blockButton;
        foulBtn = binding.foulButton;
        turnoverBtn = binding.turnoverButton;

        match = new Match(1, new Team(1, "jif", "kfsd", "euj"), new Team(2, "fkns", "kdlf", "akdk"), new Date(), Status.COMPLETED);
        teamLandlord = match.getTeamLandlord();
        teamGuest = match.getGuest();

        //test
        match = new Match(1, new Team(1, "jif", "kfsd", "euj"), new Team(2, "fkns", "kdlf", "akdk"), new Date(), Status.COMPLETED);
        landLord = match.getTeamLandlord();
        teamGuest = match.getGuest();

        ArrayList<Player> team1 = new ArrayList<>();
        ArrayList<Player> team2 = new ArrayList<>();
        team1.add(new Player(1,"tp11", "test"));
        team1.add(new Player(2,"tp12", "test"));
        team1.add(new Player(3,"tp13", "test"));
        team1.add(new Player(4,"tp14", "test"));
        team1.add(new Player(5,"tp15", "test"));
        team2.add(new Player(6,"tp21", "test"));
        team2.add(new Player(7,"tp22", "test"));
        team2.add(new Player(8,"tp23", "test"));
        team2.add(new Player(9,"tp68", "test"));
        team2.add(new Player(10,"tp79", "test"));

        for (int i = 0; i < team1.size(); i++) {
            landLord.addPlayerToKey(team1.get(i));
        }
        for (int i = 0; i < team2.size(); i++) {
            teamGuest.addPlayerToKey(team2.get(i));
        }
        for (int i = 0; i < team2.size(); i++) {
            landLord.addPlayerToSub(team2.get(i));
        }

        //'

        //put images of the two teams
        //teamLand=match.getTeamLandlord();
        //teamGuest=match.getGuest();
        // String badgeLand=teamLand.getBadgePath();
        //  String badgeGuest=teamGuest.getBadgePath();

        //binding.team1Banner.setImageURI(Uri.parse(badgeLand));
        //binding.team2Banner.setImageURI(Uri.parse(badgeGuest));


        //

        //When this page opens, we want to have the landlord team already selected
        teamSelected = false;
        teamObj = match.getTeamLandlord();
        playerObjChecked = teamObj.getKeyPlayers().get(0);

        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(75);
        shape.setColor(getResources().getColor(R.color.alt_grey));
        binding.team1Banner.setBackground(shape);
        GradientDrawable shape2 = new GradientDrawable();
        shape2.setCornerRadius(75);
        shape2.setColor(getResources().getColor(R.color.alt_grey));
        binding.player1.setBackground(shape2);
        playerChecked = 1;

        listenEvent();


        //Load data for this team


        //Load the data of the first team players.


        //Banner Buttons -When the first team is selected -> variable "teamSelected"=false. Else, true.
        // Banner1
        binding.team1Banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamSelected = false;
                teamObj = match.getTeamLandlord();

                //When the banner changes ,automatically select the first player of the list.
                deleteThePreviousBackground();
                GradientDrawable shape2 = new GradientDrawable();
                shape2.setCornerRadius(75);
                shape2.setColor(getResources().getColor(R.color.alt_grey));
                binding.player1.setBackground(shape2);
                playerChecked = 1;

                playerObjChecked = teamObj.getKeyPlayers().get(0);


                //put background color to the banner so the admin knows which team is selected
                GradientDrawable shape = new GradientDrawable();
                shape.setCornerRadius(75);
                shape.setColor(getResources().getColor(R.color.alt_grey));
                view.setBackground(shape);

                //remove the background color from the other banner
                binding.team2Banner.setBackgroundColor(0x00000000);


            }
        });

        // Banner2
        binding.team2Banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                teamSelected = true;
                teamObj = match.getGuest();

                //When the banner changes ,automatically select the first player of the list.
                deleteThePreviousBackground();
                GradientDrawable shape2 = new GradientDrawable();
                shape2.setCornerRadius(75);
                shape2.setColor(getResources().getColor(R.color.alt_grey));
                binding.player1.setBackground(shape2);
                playerChecked = 1;
                playerObjChecked = teamObj.getKeyPlayers().get(0);


                //put background color to the banner so the admin knows which team is selected
                GradientDrawable shape = new GradientDrawable();
                shape.setCornerRadius(75);
                shape.setColor(getResources().getColor(R.color.alt_grey));
                view.setBackground(shape);

                //remove the background color from the other banner
                binding.team1Banner.setBackgroundColor(0x00000000);

                //Load data for this team

            }
        });


//Start Button

        //Start Button

        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!started) {
                    binding.clock.setBase(SystemClock.elapsedRealtime());
                    binding.clock.start();
                    // Prosthiki apo leo gia na pairnw ta lepta
                    binding.clock.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                        @Override
                        public void onChronometerTick(Chronometer chronometer) {
                            // to match id tha allazei analoga to match
                            DAOLiveTeamService.getInstance().updateClock(1, chronometer.getText().toString());
                        }
                    });
                    running = true;
                    started = true;
                    binding.startButton.setText("End");
                    binding.pauseButton.setEnabled(true);
                    m.setStatus(Status.ONGOING);
                    m.setProgress();
                    MatchService ms = new MatchService();
                    try {
                        ms.statusUpdate(m);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                //end button
                else {
                    binding.clock.stop();
                    binding.startButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f);
                    binding.startButton.setText("Start/End");
                    binding.startButton.setEnabled(false);
                    binding.pauseButton.setEnabled(false);
                    binding.pauseButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f);
                    binding.pauseButton.setText("Pause/Continue");
                    m.setStatus(Status.COMPLETED);

                    m.setCompleted();
                    MatchService ms = new MatchService();
                    try {
                        ms.statusUpdate(m);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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


        //Player Buttons-

        binding.player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(playerChecked == 1)) {
                    deleteThePreviousBackground();
                    GradientDrawable shape = new GradientDrawable();
                    shape.setCornerRadius(75);
                    shape.setColor(getResources().getColor(R.color.alt_grey));
                    view.setBackground(shape);

                    playerChecked = 1;

                    playerObjChecked = teamObj.getKeyPlayers().get(playerChecked - 1);

                    //
                    binding.freethrowButton.setText(playerObjChecked.getName());

                    listenEvent();
                }

            }
        });

        binding.player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(playerChecked == 2)) {
                    deleteThePreviousBackground();
                    GradientDrawable shape = new GradientDrawable();
                    shape.setCornerRadius(75);
                    shape.setColor(getResources().getColor(R.color.alt_grey));
                    view.setBackground(shape);

                    playerChecked = 2;

                    playerObjChecked = teamObj.getKeyPlayers().get(playerChecked - 1);

                    //
                    binding.freethrowButton.setText(playerObjChecked.getName());
                    listenEvent();

                }

            }
        });

        binding.player3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(playerChecked == 3)) {
                    deleteThePreviousBackground();
                    GradientDrawable shape = new GradientDrawable();
                    shape.setCornerRadius(75);
                    shape.setColor(getResources().getColor(R.color.alt_grey));
                    view.setBackground(shape);

                    playerChecked = 3;

                    playerObjChecked = teamObj.getKeyPlayers().get(playerChecked - 1);

                    //
                    binding.freethrowButton.setText(playerObjChecked.getName());
                    listenEvent();
                }

            }
        });

        binding.player4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(playerChecked == 4)) {
                    deleteThePreviousBackground();
                    GradientDrawable shape = new GradientDrawable();
                    shape.setCornerRadius(75);
                    shape.setColor(getResources().getColor(R.color.alt_grey));
                    view.setBackground(shape);

                    playerChecked = 4;

                    playerObjChecked = teamObj.getKeyPlayers().get(playerChecked - 1);

                    //
                    binding.freethrowButton.setText(playerObjChecked.getName());

                    listenEvent();

                }

            }
        });

        binding.player5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(playerChecked == 5)) {
                    deleteThePreviousBackground();
                    GradientDrawable shape = new GradientDrawable();
                    shape.setCornerRadius(75);
                    shape.setColor(getResources().getColor(R.color.alt_grey));
                    view.setBackground(shape);



                    playerChecked = 5;

                    playerObjChecked = teamObj.getKeyPlayers().get(playerChecked - 1);

                    //
                    binding.freethrowButton.setText(playerObjChecked.getName());


                    listenEvent();

                }


            }
        });

        //Substitution Button
        binding.substitutionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //steile ton paikti

                //emfanise to popup
                SubstitutionPopupView ppv = new SubstitutionPopupView(getActivity(), playerObjChecked, teamObj, playerChecked);
                ppv.show();

                //refresh the players
                if (teamSelected) {
                    binding.team2Banner.callOnClick();
                } else {
                    binding.team1Banner.callOnClick();
                }
            }
        });




    }

    private void listenEvent(){
        DBDataRecovery dataRecovery = new DBDataRecovery();

        System.out.println("erifoejiks");


        try {
            Stats playerStats = dataRecovery.readData(Config.API_PLAYER_STATISTICS_COMPLETED, String.valueOf(playerObjChecked.getId()));
            Stats teamStats = dataRecovery.readData(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, String.valueOf(teamObj.getId()));
            threePointBtn.setOnClickListener(e -> {
                popupViewThreePoints ppv = new popupViewThreePoints(getActivity(), 3, playerStats, teamStats, dataRecovery, match, teamObj, playerObjChecked);
                ppv.show();
            });
            freeThrowBtn.setOnClickListener(e -> {
                popupViewOnePoint ppv = new popupViewOnePoint(getActivity(), 1, playerStats, teamStats, dataRecovery,match, teamObj, playerObjChecked);
                ppv.show();
            });
            twoPointBtn.setOnClickListener(e -> {
                popupViewTwoPoints ppv = new popupViewTwoPoints(getActivity(), 2, playerStats, teamStats, dataRecovery,match, teamObj, playerObjChecked);
                ppv.show();
            });
            reboundBtn.setOnClickListener(e -> updateRebound(playerStats, teamStats, dataRecovery));
            assistBtn.setOnClickListener(e -> updateAssist(playerStats, teamStats, dataRecovery));
            stealBtn.setOnClickListener(e -> updateSteal(playerStats, teamStats, dataRecovery));
            blockBtn.setOnClickListener(e -> updateBlock(playerStats, teamStats, dataRecovery));
            foulBtn.setOnClickListener(e -> updateFoul(playerStats, teamStats, dataRecovery));
            turnoverBtn.setOnClickListener(e -> updateTurnover(playerStats, teamStats, dataRecovery));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateAssist(Stats playerStats, Stats teamStats, DBDataRecovery dbDataRecovery) {
        playerStats.setTotalAssists();
        teamStats.setTotalAssists();
        try {
            DAOLiveTeamService.getInstance().updateByMatchAndTeamId(match.getId(),teamObj.getId(), LiveStatisticsEnum.assist);
            DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(),playerObjChecked.getId(), LiveStatisticsEnum.assist);
            dbDataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dbDataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateRebound(Stats playerStats, Stats teamStats, DBDataRecovery dbDataRecovery) {
        playerStats.setTotalRebounds();
        teamStats.setTotalRebounds();
        try {
            DAOLiveTeamService.getInstance().updateByMatchAndTeamId(match.getId(),teamObj.getId(), LiveStatisticsEnum.rebound);
            DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(),playerObjChecked.getId(), LiveStatisticsEnum.rebound);
            dbDataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dbDataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateSteal(Stats playerStats, Stats teamStats, DBDataRecovery dbDataRecovery) {
        playerStats.setTotalSteels();
        teamStats.setTotalSteels();
        try {
            DAOLiveTeamService.getInstance().updateByMatchAndTeamId(match.getId(),teamObj.getId(), LiveStatisticsEnum.steal);
            DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(),playerObjChecked.getId(), LiveStatisticsEnum.steal);
            dbDataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dbDataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateBlock(Stats playerStats, Stats teamStats, DBDataRecovery dataRecovery) {
        playerStats.setTotalBlock();
        teamStats.setTotalBlock();
        try {
            DAOLiveTeamService.getInstance().updateByMatchAndTeamId(match.getId(),teamObj.getId(), LiveStatisticsEnum.block);
            DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(),playerObjChecked.getId(), LiveStatisticsEnum.block);
            dataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateFoul(Stats playerStats, Stats teamStats, DBDataRecovery dbDataRecovery) {
        playerStats.setTotalFouls();
        teamStats.setTotalFouls();
        try {
            DAOLiveTeamService.getInstance().updateByMatchAndTeamId(match.getId(),teamObj.getId(), LiveStatisticsEnum.foul);
            DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(),playerObjChecked.getId(), LiveStatisticsEnum.foul);
            dbDataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dbDataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateTurnover(Stats playerStats, Stats teamStats, DBDataRecovery dbDataRecovery) {
        playerStats.setTotalTurnovers();
        teamStats.setTotalTurnovers();
        try {
            dbDataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dbDataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
            DAOLiveTeamService.getInstance().updateByMatchAndTeamId(match.getId(),teamObj.getId(), LiveStatisticsEnum.turnover);
            DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(),playerObjChecked.getId(), LiveStatisticsEnum.turnover);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //you call this function when you want to change player
    public void deleteThePreviousBackground() {
        if (playerChecked == 1) {
            binding.player1.setBackgroundColor(0x00000000);
        } else if (playerChecked == 2) {
            binding.player2.setBackgroundColor(0x00000000);
        } else if (playerChecked == 3) {
            binding.player3.setBackgroundColor(0x00000000);
        } else if (playerChecked == 4) {
            binding.player4.setBackgroundColor(0x00000000);
        } else if (playerChecked == 5) {
            binding.player5.setBackgroundColor(0x00000000);
        }


    }


}
