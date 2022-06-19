package uom.team2.weball_statistics.UI_Controller.AdminsView;

import android.content.DialogInterface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.BelongsTo;
import uom.team2.weball_statistics.Model.Actions.MatchFlow.FlowType;
import uom.team2.weball_statistics.Model.Actions.MatchFlow.MatchFlow;
import uom.team2.weball_statistics.Model.Actions.MatchFlow.MatchFlowComment;
import uom.team2.weball_statistics.Model.Actions.ReboundAction.Rebound;
import uom.team2.weball_statistics.Model.Actions.ReboundAction.ReboundComment;
import uom.team2.weball_statistics.Model.Actions.SBFActions.SBFAction;
import uom.team2.weball_statistics.Model.Actions.SBFActions.SBFActionComment;
import uom.team2.weball_statistics.Model.Actions.SBFActions.SBFActionType;
import uom.team2.weball_statistics.Model.Actions.Shots.Assist;
import uom.team2.weball_statistics.Model.Actions.Shots.Shot;
import uom.team2.weball_statistics.Model.Actions.Shots.ShotComment;
import uom.team2.weball_statistics.Model.Actions.Shots.ShotType;
import uom.team2.weball_statistics.Model.Actions.Turnover.Turnover;
import uom.team2.weball_statistics.Model.Actions.Turnover.TurnoverComment;
import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.PlayerLiveStatistics;
import uom.team2.weball_statistics.Model.Statistics.DBDataRecovery;
import uom.team2.weball_statistics.Model.Statistics.PlayerStats;
import uom.team2.weball_statistics.Model.Statistics.Stats;
import uom.team2.weball_statistics.Model.Statistics.TeamStats;
import uom.team2.weball_statistics.Model.Status;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.Model.TeamLiveStatistics;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.DAOAction;
import uom.team2.weball_statistics.Service.DAOLiveMatchService;
import uom.team2.weball_statistics.Service.DAOLivePlayerStatistics;
import uom.team2.weball_statistics.Service.MatchService;
import uom.team2.weball_statistics.UIFactory.LayoutFactory;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.LiveStatisticsEnum;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.UIHandler;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.databinding.FragmentAdminsViewBinding;

/*
 * @author Ouzounidis Kyriakos iis20033
 */
public class AdminsView extends Fragment {
    private final Stack<Team> undoTeamStack = new Stack<Team>();
    private final Stack<Player> undoPlayerStack = new Stack<Player>();
    private final Stack<TextView> undoButtonStack = new Stack<TextView>();
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
    private final ArrayList<ImageView> playersImageViewList = new ArrayList<ImageView>();
    private final Stack<String> scoreStack = new Stack<String>();
    private FragmentAdminsViewBinding binding;
    private Chronometer chronometer;
    private boolean running = false;
    private Button start_end_button;
    private long pauseOffset;
    private long keepClock;
    private boolean started = false;
    private boolean teamSelected = false;
    private int playerChecked = 1;
    private Match match;
    private Player playerObjChecked;
    private Team teamObj;
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
    private TextView startBtn;
    private TextView pauseBtn;
    private TextView undoBtn;
    private TextView substitutionBtn;
    private Assist assistMade = null;

    public AdminsView() {
        // Required empty public constructor
    }

    public static AdminsView newInstance() {
        AdminsView fragment = new AdminsView();
        Bundle args = new Bundle();
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


    @Override
    public void onStop() {
        super.onStop();
        DAOLiveMatchService.getInstance().updateClockLong(match.getId(), SystemClock.elapsedRealtime(), "stop");
        //Add pause's action description to firebase
        Action pauseMatchAction = new MatchFlow(binding.clock.getText().toString(), FlowType.PAUSE);
        DAOAction.getInstance().insertAction(pauseMatchAction, match);
        //Add pause's comment description to firebase
        Action pauseMatchComment = new MatchFlowComment(binding.clock.getText().toString(), FlowType.PAUSE, getContext());
        DAOAction.getInstance().insertCommentDesc(pauseMatchComment, match);
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
        startBtn = binding.startButton;
        pauseBtn = binding.pauseButton;
        undoBtn = binding.undoButton;
        substitutionBtn = binding.substitutionButton;

        setAlphaAdminBtn(120);
        binding.startButton.getBackground().setAlpha(255);

        // orismata gia kathe match
        Bundle bundle = getArguments();
        match = (Match) bundle.getSerializable("match");
        teamLandlord = (Team) bundle.getSerializable("teamLandlord");
        teamGuest = (Team) bundle.getSerializable("teamGuest");
        match.setTeamLandlord(teamLandlord);
        match.setGuest(teamGuest);

        DAOLiveMatchService.getInstance().initializeTable(match.getId(), teamLandlord.getId(), teamGuest.getId());
        DAOLiveMatchService.getInstance().setListenerForPoints(this, binding.scoreText, match.getId(), teamLandlord.getId(), teamGuest.getId());

        if (teamLandlord != null) {
            for (Player player : teamLandlord.getTeamPlayers()) {
                DAOLivePlayerStatistics.getInstance().initializeTable(match.getId(), player.getId());
            }
        }

        if (teamGuest != null) {
            for (Player player : teamGuest.getTeamPlayers()) {
                DAOLivePlayerStatistics.getInstance().initializeTable(match.getId(), player.getId());
            }
        }

        UIHandler.updateTeamImage(this, teamLandlord, binding.team1Banner);
        UIHandler.updateTeamImage(this, teamGuest, binding.team2Banner);


        // kathe antikeimeno team exei mia lista me tous paiktes ths sto pedio teamPlayers
        ArrayList<Player> tlList = teamLandlord.getTeamPlayers();
        ArrayList<Player> tgList = teamGuest.getTeamPlayers();

        for (int i = 0; i < tlList.size(); i++) {
            if (i < 5) {
                keyPlayersLandlord.add(tlList.get(i));
            } else {
                subPlayersLandlord.add(tlList.get(i));
            }
        }

        for (int i = 0; i < tgList.size(); i++) {
            if (i < 5) {
                keyPlayersGuest.add(tgList.get(i));
            } else {
                subPlayersGuest.add(tgList.get(i));
            }
        }

        match.getTeamLandlord().setKeyPlayersList(keyPlayersLandlord);
        match.getTeamLandlord().setSubPlayersList(subPlayersLandlord);
        match.getGuest().setKeyPlayersList(keyPlayersGuest);
        match.getGuest().setSubPlayersList(subPlayersGuest);


        teamLandlord.setKeyPlayersList(keyPlayersLandlord);
        teamLandlord.setSubPlayersList(subPlayersLandlord);
        teamGuest.setKeyPlayersList(keyPlayersGuest);
        teamGuest.setSubPlayersList(subPlayersGuest);


        //Add ImageViews for players into an ArrayList
        playersImageViewList.add(binding.player1);
        playersImageViewList.add(binding.player2);
        playersImageViewList.add(binding.player3);
        playersImageViewList.add(binding.player4);
        playersImageViewList.add(binding.player5);
        //Put images of the 1rst team Players

        for (int i = 0; i < playersImageViewList.size(); i++) {
            if (teamLandlord.getKeyPlayers().get(i) != null) {
                Picasso.get()
                        .load(Config.PLAYER_IMAGES_RESOURCES + teamLandlord.getKeyPlayers().get(i).getImagePath())
                        .resize(200, 200)
                        .centerCrop()
                        .into(playersImageViewList.get(i));
            }
        }


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


        //an to paixnidi exei idi ksekinisei alla o admin exei vgei apo
        //tin othoni toy match, tote prepei to chronometer na sunexisei apo
        //tin live ora.
        if (match.isProgress() == 1) {
            //vale to xronometro apo ekei pou stamatise
            running = true;
            started = true;
            binding.startButton.setText("End");
            binding.pauseButton.setEnabled(true);
            //enable again the buttons
            setAlphaAdminBtn(255);
            listenEvent();

            //Add resume's action description to firebase
            Action resumeMatchAction = new MatchFlow(binding.clock.getText().toString(), FlowType.RESUME);
            DAOAction.getInstance().insertAction(resumeMatchAction, match);
            //Add resume's comment description to firebase
            Action resumeMatchComment = new MatchFlowComment(binding.clock.getText().toString(), FlowType.RESUME, getContext());
            DAOAction.getInstance().insertCommentDesc(resumeMatchComment, match);

            DAOLiveMatchService.getInstance().setChronometerTime(match.getId(), AdminsView.this, binding.clock);
            binding.clock.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    DAOLiveMatchService.getInstance().updateClock(match.getId(), chronometer.getText().toString());
                    DAOLiveMatchService.getInstance().updateClockLong(match.getId(), chronometer.getBase(), "time");
                }
            });
        }


        //Done button
        binding.doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.clock.stop();
                if (running) {
                    DAOLiveMatchService.getInstance().updateClockLong(match.getId(), SystemClock.elapsedRealtime(), "stop");
                }
                //Add pause's action description to firebase
                Action pauseMatchAction = new MatchFlow(binding.clock.getText().toString(), FlowType.PAUSE);
                DAOAction.getInstance().insertAction(pauseMatchAction, match);
                //Add pause's comment description to firebase
                Action pauseMatchComment = new MatchFlowComment(binding.clock.getText().toString(), FlowType.PAUSE, getContext());
                DAOAction.getInstance().insertCommentDesc(pauseMatchComment, match);

                Bundle bundle = new Bundle();
                bundle.putBoolean("isAdmin", true);
                NavHostFragment.findNavController(AdminsView.this).navigate(R.id.action_adminsView_to_matchesTabContainer, bundle);
            }
        });


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


                for (int i = 0; i < playersImageViewList.size(); i++) {
                    if (teamLandlord.getKeyPlayers().get(i) != null) {
                        Picasso.get()
                                .load(Config.PLAYER_IMAGES_RESOURCES + teamLandlord.getKeyPlayers().get(i).getImagePath())
                                .resize(200, 200)
                                .centerCrop()
                                .into(playersImageViewList.get(i));
                    }

                }

                listenEvent();

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

                for (int i = 0; i < playersImageViewList.size(); i++) {
                    if (teamLandlord.getKeyPlayers().get(i) != null) {
                        Picasso.get()
                                .load(Config.PLAYER_IMAGES_RESOURCES + teamGuest.getKeyPlayers().get(i).getImagePath())
                                .resize(200, 200)
                                .centerCrop()
                                .into(playersImageViewList.get(i));
                    }

                }

                listenEvent();

            }
        });


        binding.startButton.setOnClickListener(new View.OnClickListener() {


            final DBDataRecovery teamsPlayed = new DBDataRecovery();
            Stats teamLandlordStats = null, teamGuestStats = null;

            @Override
            public void onClick(View view) {


                if (!started) {
                    setAlphaAdminBtn(255);
                    initPlayers();
                    binding.clock.setBase(SystemClock.elapsedRealtime());
                    binding.clock.start();
                    // Prosthiki apo leo gia na pairnw ta lepta
                    binding.clock.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                        @Override
                        public void onChronometerTick(Chronometer chronometer) {
                            // to match id tha allazei analoga to match
                            DAOLiveMatchService.getInstance().updateClock(match.getId(), chronometer.getText().toString());
                            DAOLiveMatchService.getInstance().updateClockLong(match.getId(), chronometer.getBase(), "time");
                        }
                    });
                    running = true;
                    started = true;
                    binding.startButton.setText("End");
                    binding.pauseButton.setEnabled(true);
                    match.setStatus(Status.ONGOING);

                    //Add start's action description to firebase
                    Action startMatchAction = new MatchFlow("00.00", FlowType.START);
                    DAOAction.getInstance().insertAction(startMatchAction, match);
                    //Add start's comment description to firebase
                    Action startMatchComment = new MatchFlowComment("00.00", FlowType.START, getContext());
                    DAOAction.getInstance().insertCommentDesc(startMatchComment, match);

                    match.setProgress(1);

                    MatchService ms = new MatchService();
                    try {
                        ms.statusUpdate(match);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        teamLandlordStats = teamsPlayed.readData(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, String.valueOf(teamLandlord.getId()));
                        teamGuestStats = teamsPlayed.readData(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, String.valueOf(teamGuest.getId()));
                        ((TeamStats) teamLandlordStats).setMatches();
                        ((TeamStats) teamGuestStats).setMatches();
                        teamsPlayed.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamLandlordStats);
                        teamsPlayed.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamGuestStats);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    listenEvent();
                }
                //end button
                else {
                    String[] scores = binding.scoreText.getText().toString().trim().split("-");
                    int scoreTeamLandlord = Integer.parseInt(scores[0].trim());
                    int scoreTeamGuest = Integer.parseInt(scores[1].trim());

                    if (scoreTeamLandlord == scoreTeamGuest) {
                        LayoutFactory.createSnackbar(binding.getRoot(), "Can't finish a draw game!", R.color.red).show();
                    } else {
                        //Appear the dialog window
                        try {
                            teamLandlordStats = teamsPlayed.readData(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, String.valueOf(teamLandlord.getId()));
                            teamGuestStats = teamsPlayed.readData(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, String.valueOf(teamGuest.getId()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        FinishGameDialog finishGameDialog = new FinishGameDialog(getContext(), binding, match, teamLandlordStats, teamGuestStats, teamsPlayed);
                        finishGameDialog.show();
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

                    //Add resume's action description to firebase
                    Action resumeMatchAction = new MatchFlow(binding.clock.getText().toString(), FlowType.RESUME);
                    DAOAction.getInstance().insertAction(resumeMatchAction, match);
                    //Add resume's comment description to firebase
                    Action resumeMatchComment = new MatchFlowComment(binding.clock.getText().toString(), FlowType.RESUME, getContext());
                    DAOAction.getInstance().insertCommentDesc(resumeMatchComment, match);
                } else {
                    binding.pauseButton.setText("Continue");
                    binding.clock.stop();
                    DAOLiveMatchService.getInstance().updateClockLong(match.getId(), SystemClock.elapsedRealtime(), "stop");
                    pauseOffset = SystemClock.elapsedRealtime() - binding.clock.getBase();
                    running = false;

                    //Add pause's action description to firebase
                    Action pauseMatchAction = new MatchFlow(binding.clock.getText().toString(), FlowType.PAUSE);
                    DAOAction.getInstance().insertAction(pauseMatchAction, match);
                    //Add pause's comment description to firebase
                    Action pauseMatchComment = new MatchFlowComment(binding.clock.getText().toString(), FlowType.PAUSE, getContext());
                    DAOAction.getInstance().insertCommentDesc(pauseMatchComment, match);
                }
            }
        });

        //Undo Button
        binding.undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!undoButtonStack.isEmpty()) {
                    Player plObjForUndo = undoPlayerStack.pop();
                    Team teamObjForUndo = undoTeamStack.pop();
                    TextView lastAction = undoButtonStack.pop();


                    DBDataRecovery dataRecovery = new DBDataRecovery();
                    try {
                        Stats playerStats = dataRecovery.readData(Config.API_PLAYER_STATISTICS_COMPLETED, String.valueOf(plObjForUndo.getId()));
                        Stats teamStats = dataRecovery.readData(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, String.valueOf(teamObjForUndo.getId()));

                        if (binding.freethrowButton.equals(lastAction)) {
                            String sc = scoreStack.pop();
                            if (sc.equals(binding.scoreText.getText().toString())) {

                            } else {
                                playerStats.setterForSuccessfulFreethrow(playerStats.getTotalFreeThrow() - 1);
                                teamStats.setterForSuccessfulFreethrow(teamStats.getTotalFreeThrow() - 1);
                            }
                            playerStats.setFreethrow(playerStats.getTotalFreeThrow() - 1);
                            teamStats.setFreethrow(teamStats.getTotalFreeThrow() - 1);
                            try {
                                dataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
                                dataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }


                        } else if (binding.twoPointerButton.equals(lastAction)) {
                            System.out.println("mpika2 re2 mlk2222");
                            String sc = scoreStack.pop();
                            if (sc.equals(binding.scoreText.getText().toString())) {

                            } else {
                                System.out.println("mpika re mlk");
                                playerStats.setterForSuccessfulTwoPointer(playerStats.getTotalTwoPointer() - 1);
                                teamStats.setterForSuccessfulTwoPointer(teamStats.getTotalTwoPointer() - 1);
                            }
                            playerStats.setTwoPointer(playerStats.getTotalTwoPointer() - 1);
                            teamStats.setTwoPointer(teamStats.getTotalTwoPointer() - 1);
                            try {
                                dataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
                                dataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }


                        } else if (binding.threePointerButton.equals(lastAction)) {
                            String sc = scoreStack.pop();
                            if (sc.equals(binding.scoreText.getText().toString())) {

                            } else {
                                playerStats.setterForSuccessfulThreePointer(playerStats.getTotalThreePointer() - 1);
                                teamStats.setterForSuccessfulThreePointer(teamStats.getTotalThreePointer() - 1);
                            }
                            playerStats.setThreePointer(playerStats.getTotalThreePointer() - 1);
                            teamStats.setThreePointer(teamStats.getTotalThreePointer() - 1);
                            try {
                                dataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
                                dataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }


                        } else if (binding.reboundButton.equals(lastAction)) {
                            updateRebound(playerStats, teamStats, dataRecovery, true);
                        } else if (binding.assistButton.equals(lastAction)) {
                            updateAssist(playerStats, teamStats, dataRecovery, true);
                        } else if (binding.stealButton.equals(lastAction)) {
                            updateSteal(playerStats, teamStats, dataRecovery, true);
                        } else if (binding.blockButton.equals(lastAction)) {
                            updateBlock(playerStats, teamStats, dataRecovery, true);
                        } else if (binding.foulButton.equals(lastAction)) {
                            updateFoul(playerStats, teamStats, dataRecovery, true);
                        } else if (binding.turnoverButton.equals(lastAction)) {
                            updateTurnover(playerStats, teamStats, dataRecovery, true);
                        } else {
                            //do nothing
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("No more actions for undo");
                }
            }
        });

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

                    if (started) {
                        listenEvent();
                    }
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

                    if (started) {
                        listenEvent();
                    }
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

                    if (started) {
                        listenEvent();
                    }
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


                    if (started) {
                        listenEvent();
                    }
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

                    if (started) {
                        listenEvent();
                    }
                }
            }
        });

        //Substitution Button
        binding.substitutionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //steile ton paikti

                //emfanise to popup
                SubstitutionPopupView ppv = new SubstitutionPopupView(getActivity(), playerObjChecked, teamObj, playerChecked, playersImageViewList, binding.clock.getText().toString(), match);
                ppv.show();

                ppv.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (teamSelected) {
                            binding.team2Banner.callOnClick();
                        } else {
                            binding.team1Banner.callOnClick();
                        }
                    }
                });
            }
        });

    }

    private void listenEvent() {
        DBDataRecovery dataRecovery = new DBDataRecovery();

        try {
            Stats playerStats = dataRecovery.readData(Config.API_PLAYER_STATISTICS_COMPLETED, String.valueOf(playerObjChecked.getId()));
            Stats teamStats = dataRecovery.readData(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, String.valueOf(teamObj.getId()));

            threePointBtn.setOnClickListener(e -> {
                undoPlayerStack.push(playerObjChecked);
                undoTeamStack.push(teamObj);
                undoButtonStack.push(binding.threePointerButton);
                scoreStack.push(binding.scoreText.getText().toString());

                if (assistMade == null) {
                    popupViewThreePoints ppv = new popupViewThreePoints(getActivity(), binding.getRoot(), 3, playerStats, teamStats, dataRecovery, match, teamObj, playerObjChecked, binding.clock.getText().toString());
                    ppv.show();
                } else {
                    playerStats.setSuccessfulThreePointer();
                    playerStats.setSuccessfulEffort();
                    teamStats.setSuccessfulThreePointer();
                    teamStats.setSuccessfulEffort();

                    DAOLiveMatchService.getInstance().updateByMatchAndTeamId(match.getId(), teamObj.getId(), LiveStatisticsEnum.successful_threepointer);
                    DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(), playerObjChecked.getId(), LiveStatisticsEnum.successful_threepointer);

                    //Insert 3point's action to firebase
                    Action treePointThrowAction = null;
                    Action treePointThrowComment = null;
                    if (this.match.getTeamLandlord_id() == this.playerObjChecked.getId()) {
                        treePointThrowAction = new Shot(binding.clock.getText().toString(), BelongsTo.HOME, playerObjChecked, teamObj, ShotType.THREE_POINTER, true, this.assistMade);
                        treePointThrowComment = new ShotComment(binding.clock.getText().toString(), BelongsTo.HOME, playerObjChecked, teamObj, ShotType.THREE_POINTER, true, this.assistMade, getContext());
                    } else if (this.match.getTeamguest_id() == this.teamObj.getId()) {
                        treePointThrowAction = new Shot(binding.clock.getText().toString(), BelongsTo.GUEST, playerObjChecked, teamObj, ShotType.THREE_POINTER, true, this.assistMade);
                        treePointThrowComment = new ShotComment(binding.clock.getText().toString(), BelongsTo.GUEST, playerObjChecked, teamObj, ShotType.THREE_POINTER, true, this.assistMade, getContext());
                    }

                    if (treePointThrowAction != null) {
                        DAOAction.getInstance().insertAction(treePointThrowAction, match);
                    }

                    if (treePointThrowComment != null) {
                        DAOAction.getInstance().insertCommentDesc(treePointThrowComment, match);
                    }

                    playerStats.setTotalThreePointer();
                    playerStats.setTotalEffort();
                    teamStats.setTotalThreePointer();
                    teamStats.setTotalEffort();
                    try {
                        dataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
                        dataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    LayoutFactory.createSnackbar(binding.getRoot(), "Successful three-pointer for " + playerObjChecked.getName() + " " + playerObjChecked.getSurname(), R.color.success_green).show();
                    enableButtonAfterAssistAndShoot(255);
                }
            });
            freeThrowBtn.setOnClickListener(e -> {

                undoPlayerStack.push(playerObjChecked);
                undoTeamStack.push(teamObj);
                undoButtonStack.push(binding.freethrowButton);
                scoreStack.push(binding.scoreText.getText().toString());
                popupViewOnePoint ppv = new popupViewOnePoint(getActivity(), binding.getRoot(), 1, playerStats, teamStats, dataRecovery, match, teamObj, playerObjChecked, binding.clock.getText().toString());

                ppv.show();

            });
            twoPointBtn.setOnClickListener(e -> {

                undoPlayerStack.push(playerObjChecked);
                undoTeamStack.push(teamObj);
                undoButtonStack.push(binding.twoPointerButton);
                scoreStack.push(binding.scoreText.getText().toString());
                if (assistMade == null) {
                    popupViewTwoPoints ppv = new popupViewTwoPoints(getActivity(), binding.getRoot(), 2, playerStats, teamStats, dataRecovery, match, teamObj, playerObjChecked, binding.clock.getText().toString());
                    ppv.show();
                } else {
                    playerStats.setSuccessfulTwoPointer();
                    playerStats.setSuccessfulEffort();
                    teamStats.setSuccessfulTwoPointer();
                    teamStats.setSuccessfulEffort();

                    DAOLiveMatchService.getInstance().updateByMatchAndTeamId(match.getId(), teamObj.getId(), LiveStatisticsEnum.successful_twopointer);
                    DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(), playerObjChecked.getId(), LiveStatisticsEnum.successful_twopointer);
                    //Insert 2point's action to firebase
                    Action twoPointThrowAction = null;
                    Action twoPointThrowComment = null;
                    if (this.match.getTeamLandlord_id() == teamObj.getId()) {
                        twoPointThrowAction = new Shot(binding.clock.getText().toString(), BelongsTo.HOME, playerObjChecked, teamObj, ShotType.TWO_POINTER, true, this.assistMade);
                        twoPointThrowComment = new ShotComment(binding.clock.getText().toString(), BelongsTo.HOME, playerObjChecked, teamObj, ShotType.TWO_POINTER, true, this.assistMade, getContext());
                    } else if (this.match.getTeamguest_id() == teamObj.getId()) {
                        twoPointThrowAction = new Shot(binding.clock.getText().toString(), BelongsTo.GUEST, playerObjChecked, teamObj, ShotType.TWO_POINTER, true, this.assistMade);
                        twoPointThrowComment = new ShotComment(binding.clock.getText().toString(), BelongsTo.GUEST, playerObjChecked, teamObj, ShotType.TWO_POINTER, true, this.assistMade, getContext());
                    }

                    if (twoPointThrowAction != null) {
                        DAOAction.getInstance().insertAction(twoPointThrowAction, match);
                    }

                    if (twoPointThrowComment != null) {
                        DAOAction.getInstance().insertCommentDesc(twoPointThrowComment, match);
                    }

                    playerStats.setTotalTwoPointer();
                    playerStats.setTotalEffort();
                    teamStats.setTotalTwoPointer();
                    teamStats.setTotalEffort();

                    try {
                        dataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
                        dataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    LayoutFactory.createSnackbar(binding.getRoot(), "Successful two-pointer for" + playerObjChecked.getName() + " " + playerObjChecked.getSurname(), R.color.success_green).show();
                    enableButtonAfterAssistAndShoot(255);
                }
            });

            reboundBtn.setOnClickListener(e -> {
                updateRebound(playerStats, teamStats, dataRecovery, false);

                //Insert rebound's action to firebase
                Action reboundAction = null;
                Action reboundComment = null;
                if (match.getTeamLandlord_id() == teamObj.getId()) {
                    reboundAction = new Rebound(binding.clock.getText().toString(), BelongsTo.HOME, playerObjChecked, teamObj);
                    reboundComment = new ReboundComment(binding.clock.getText().toString(), BelongsTo.HOME, playerObjChecked, teamObj);
                } else {
                    reboundAction = new Rebound(binding.clock.getText().toString(), BelongsTo.GUEST, playerObjChecked, teamObj);
                    reboundComment = new ReboundComment(binding.clock.getText().toString(), BelongsTo.GUEST, playerObjChecked, teamObj);
                }

                if (reboundAction != null) {
                    DAOAction.getInstance().insertAction(reboundAction, match);
                }

                if (reboundComment != null) {
                    DAOAction.getInstance().insertCommentDesc(reboundComment, match);
                }
            });
            assistBtn.setOnClickListener(e -> {
                updateAssist(playerStats, teamStats, dataRecovery, false);
                disableButtonsAfterAssist(120);
            });
            stealBtn.setOnClickListener(e -> {
                updateSteal(playerStats, teamStats, dataRecovery, false);
                //Insert steal's action to firebase
                Action stealAction = null;
                Action stealComment = null;
                if (match.getTeamLandlord_id() == teamObj.getId()) {
                    stealAction = new SBFAction(binding.clock.getText().toString(), BelongsTo.HOME, playerObjChecked, teamObj, SBFActionType.STEAL);
                    stealComment = new SBFActionComment(binding.clock.getText().toString(), BelongsTo.HOME, playerObjChecked, teamObj, SBFActionType.STEAL, getContext());
                } else {
                    stealAction = new SBFAction(binding.clock.getText().toString(), BelongsTo.GUEST, playerObjChecked, teamObj, SBFActionType.STEAL);
                    stealComment = new SBFActionComment(binding.clock.getText().toString(), BelongsTo.GUEST, playerObjChecked, teamObj, SBFActionType.STEAL, getContext());
                }

                if (stealAction != null) {
                    DAOAction.getInstance().insertAction(stealAction, match);
                }

                if (stealComment != null) {
                    DAOAction.getInstance().insertCommentDesc(stealComment, match);
                }
            });
            blockBtn.setOnClickListener(e -> {
                updateBlock(playerStats, teamStats, dataRecovery, false);
                //Insert block's action to firebase
                Action blockAction = null;
                Action blockComment = null;
                if (match.getTeamLandlord_id() == teamObj.getId()) {
                    blockAction = new SBFAction(binding.clock.getText().toString(), BelongsTo.HOME, playerObjChecked, teamObj, SBFActionType.BLOCK);
                    blockComment = new SBFActionComment(binding.clock.getText().toString(), BelongsTo.HOME, playerObjChecked, teamObj, SBFActionType.BLOCK, getContext());
                } else {
                    blockAction = new SBFAction(binding.clock.getText().toString(), BelongsTo.GUEST, playerObjChecked, teamObj, SBFActionType.BLOCK);
                    blockComment = new SBFActionComment(binding.clock.getText().toString(), BelongsTo.GUEST, playerObjChecked, teamObj, SBFActionType.BLOCK, getContext());
                }

                if (blockAction != null) {
                    DAOAction.getInstance().insertAction(blockAction, match);
                }

                if (blockComment != null) {
                    DAOAction.getInstance().insertCommentDesc(blockComment, match);
                }
            });
            foulBtn.setOnClickListener(e -> {
                updateFoul(playerStats, teamStats, dataRecovery, false);
                //Insert block's action to firebase
                Action foulAction = null;
                Action foulComment = null;
                if (match.getTeamLandlord_id() == teamObj.getId()) {
                    foulAction = new SBFAction(binding.clock.getText().toString(), BelongsTo.HOME, playerObjChecked, teamObj, SBFActionType.FOUL);
                    foulComment = new SBFActionComment(binding.clock.getText().toString(), BelongsTo.HOME, playerObjChecked, teamObj, SBFActionType.FOUL, getContext());
                } else {
                    foulAction = new SBFAction(binding.clock.getText().toString(), BelongsTo.GUEST, playerObjChecked, teamObj, SBFActionType.FOUL);
                    foulComment = new SBFActionComment(binding.clock.getText().toString(), BelongsTo.GUEST, playerObjChecked, teamObj, SBFActionType.FOUL, getContext());
                }

                if (foulAction != null) {
                    DAOAction.getInstance().insertAction(foulAction, match);
                }

                if (foulComment != null) {
                    DAOAction.getInstance().insertCommentDesc(foulComment, match);
                }
            });
            turnoverBtn.setOnClickListener(e -> {
                updateTurnover(playerStats, teamStats, dataRecovery, false);
                //Insert block's action to firebase
                Action turnOverAction = null;
                Action turnOverComment = null;
                if (match.getTeamLandlord_id() == teamObj.getId()) {
                    turnOverAction = new Turnover(binding.clock.getText().toString(), BelongsTo.HOME, playerObjChecked, teamObj);
                    turnOverComment = new TurnoverComment(binding.clock.getText().toString(), BelongsTo.HOME, playerObjChecked, teamObj, getContext());
                } else {
                    turnOverAction = new Turnover(binding.clock.getText().toString(), BelongsTo.GUEST, playerObjChecked, teamObj);
                    turnOverComment = new TurnoverComment(binding.clock.getText().toString(), BelongsTo.GUEST, playerObjChecked, teamObj, getContext());
                }

                if (turnOverAction != null) {
                    DAOAction.getInstance().insertAction(turnOverAction, match);
                }

                if (turnOverComment != null) {
                    DAOAction.getInstance().insertCommentDesc(turnOverComment, match);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateAssist(Stats playerStats, Stats teamStats, DBDataRecovery dbDataRecovery, boolean isUndo) {
        if (!isUndo) {
            playerStats.setTotalAssists();
            teamStats.setTotalAssists();

            LayoutFactory.createSnackbar(binding.getRoot(), "Assist for " + playerObjChecked.getName() + " " + playerObjChecked.getSurname(), R.color.success_green).show();

            undoPlayerStack.push(playerObjChecked);
            undoTeamStack.push(teamObj);
            undoButtonStack.push(binding.assistButton);

        } else {
            playerStats.setAssist(playerStats.getTotalAssists() - 1);
            teamStats.setAssist(teamStats.getTotalAssists() - 1);
        }

        if (isUndo) {
            DAOLiveMatchService.getInstance().undo(match.getId(), teamObj.getId(), LiveStatisticsEnum.assist);
            DAOLivePlayerStatistics.getInstance().undo(match.getId(), playerObjChecked.getId(), LiveStatisticsEnum.assist);
        } else {
            DAOLiveMatchService.getInstance().updateByMatchAndTeamId(match.getId(), teamObj.getId(), LiveStatisticsEnum.assist);
            DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(), playerObjChecked.getId(), LiveStatisticsEnum.assist);

        }

        try {
            dbDataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dbDataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateRebound(Stats playerStats, Stats teamStats, DBDataRecovery dbDataRecovery, boolean isUndo) {
        if (!isUndo) {
            playerStats.setTotalRebounds();
            teamStats.setTotalRebounds();
            LayoutFactory.createSnackbar(binding.getRoot(), "Rebound for " + playerObjChecked.getName() + " " + playerObjChecked.getSurname(), R.color.success_green).show();
            undoPlayerStack.push(playerObjChecked);
            undoTeamStack.push(teamObj);
            undoButtonStack.push(binding.reboundButton);
        } else {
            playerStats.setRebound(playerStats.getTotalRebounds() - 1);
            teamStats.setRebound(teamStats.getTotalRebounds() - 1);
        }

        if (isUndo) {
            DAOLiveMatchService.getInstance().undo(match.getId(), teamObj.getId(), LiveStatisticsEnum.rebound);
            DAOLivePlayerStatistics.getInstance().undo(match.getId(), playerObjChecked.getId(), LiveStatisticsEnum.rebound);
        } else {
            DAOLiveMatchService.getInstance().updateByMatchAndTeamId(match.getId(), teamObj.getId(), LiveStatisticsEnum.rebound);
            DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(), playerObjChecked.getId(), LiveStatisticsEnum.rebound);
        }


        try {
            dbDataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dbDataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    private void updateSteal(Stats playerStats, Stats teamStats, DBDataRecovery dbDataRecovery, boolean isUndo) {
        if (!isUndo) {
            playerStats.setTotalSteels();
            teamStats.setTotalSteels();
            LayoutFactory.createSnackbar(binding.getRoot(), "Steal for " + playerObjChecked.getName() + " " + playerObjChecked.getSurname(), R.color.success_green).show();
            undoPlayerStack.push(playerObjChecked);
            undoTeamStack.push(teamObj);
            undoButtonStack.push(binding.stealButton);
        } else {
            playerStats.setSteal(playerStats.getTotalSteels() - 1);
            teamStats.setSteal(teamStats.getTotalSteels() - 1);
        }

        if (isUndo) {
            DAOLiveMatchService.getInstance().undo(match.getId(), teamObj.getId(), LiveStatisticsEnum.steal);
            DAOLivePlayerStatistics.getInstance().undo(match.getId(), playerObjChecked.getId(), LiveStatisticsEnum.steal);
        } else {
            DAOLiveMatchService.getInstance().updateByMatchAndTeamId(match.getId(), teamObj.getId(), LiveStatisticsEnum.steal);
            DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(), playerObjChecked.getId(), LiveStatisticsEnum.steal);
        }

        try {
            dbDataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dbDataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateBlock(Stats playerStats, Stats teamStats, DBDataRecovery dataRecovery, boolean isUndo) {
        if (!isUndo) {
            playerStats.setTotalBlock();
            teamStats.setTotalBlock();
            LayoutFactory.createSnackbar(binding.getRoot(), "Block for " + playerObjChecked.getName() + " " + playerObjChecked.getSurname(), R.color.success_green).show();
            undoPlayerStack.push(playerObjChecked);
            undoTeamStack.push(teamObj);
            undoButtonStack.push(binding.blockButton);
        } else {
            playerStats.setBlock(playerStats.getTotalBlocks() - 1);
            teamStats.setBlock(teamStats.getTotalBlocks() - 1);
        }

        if (isUndo) {
            DAOLiveMatchService.getInstance().undo(match.getId(), teamObj.getId(), LiveStatisticsEnum.block);
            DAOLivePlayerStatistics.getInstance().undo(match.getId(), playerObjChecked.getId(), LiveStatisticsEnum.block);
        } else {
            DAOLiveMatchService.getInstance().updateByMatchAndTeamId(match.getId(), teamObj.getId(), LiveStatisticsEnum.block);
            DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(), playerObjChecked.getId(), LiveStatisticsEnum.block);
        }

        try {
            dataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateFoul(Stats playerStats, Stats teamStats, DBDataRecovery dbDataRecovery, boolean isUndo) {
        if (!isUndo) {
            playerStats.setTotalFouls();
            teamStats.setTotalFouls();
            LayoutFactory.createSnackbar(binding.getRoot(), "Foul for" + playerObjChecked.getName() + " " + playerObjChecked.getSurname(), R.color.success_green).show();
            undoPlayerStack.push(playerObjChecked);
            undoTeamStack.push(teamObj);
            undoButtonStack.push(binding.foulButton);
        } else {
            playerStats.setFoul(playerStats.getTotalFouls() - 1);
            teamStats.setFoul(teamStats.getTotalFouls() - 1);
        }

        if (isUndo) {
            DAOLiveMatchService.getInstance().undo(match.getId(), teamObj.getId(), LiveStatisticsEnum.foul);
            DAOLivePlayerStatistics.getInstance().undo(match.getId(), playerObjChecked.getId(), LiveStatisticsEnum.foul);
        } else {
            DAOLiveMatchService.getInstance().updateByMatchAndTeamId(match.getId(), teamObj.getId(), LiveStatisticsEnum.foul);
            DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(), playerObjChecked.getId(), LiveStatisticsEnum.foul);
        }

        try {
            dbDataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dbDataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateTurnover(Stats playerStats, Stats teamStats, DBDataRecovery dbDataRecovery, boolean isUndo) {
        if (!isUndo) {
            playerStats.setTotalTurnovers();
            teamStats.setTotalTurnovers();
            LayoutFactory.createSnackbar(binding.getRoot(), "Turnover for" + playerObjChecked.getName() + " " + playerObjChecked.getSurname(), R.color.success_green).show();
            undoPlayerStack.push(playerObjChecked);
            undoTeamStack.push(teamObj);
            undoButtonStack.push(binding.turnoverButton);
        } else {
            playerStats.setTurnover(playerStats.getTotalTurnovers() - 1);
            teamStats.setTurnover(teamStats.getTotalTurnovers() - 1);
        }

        if (isUndo) {
            DAOLiveMatchService.getInstance().undo(match.getId(), teamObj.getId(), LiveStatisticsEnum.turnover);
            DAOLivePlayerStatistics.getInstance().undo(match.getId(), playerObjChecked.getId(), LiveStatisticsEnum.turnover);
        } else {
            DAOLiveMatchService.getInstance().updateByMatchAndTeamId(match.getId(), teamObj.getId(), LiveStatisticsEnum.turnover);
            DAOLivePlayerStatistics.getInstance().updateByMatchAndTeamId(match.getId(), playerObjChecked.getId(), LiveStatisticsEnum.turnover);
        }

        try {
            dbDataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, playerStats);
            dbDataRecovery.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamStats);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setStartersMatchPlayed(int indexPlayer, Team team) {
        DBDataRecovery dataRecovery = new DBDataRecovery();
        try {
            Stats stat = dataRecovery.readData(Config.API_PLAYER_STATISTICS_COMPLETED, String.valueOf(team.getTeamPlayers().get(indexPlayer).getId()));
            ((PlayerStats) stat).setByOneMatches();
            dataRecovery.updateDataDB(Config.API_PLAYER_STATISTICS_COMPLETED, stat);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initPlayers() {
        Thread threadPlayerMatch = new Thread() {

            @Override
            public void run() {
                super.run();
                for (int i = 0; i < teamLandlord.getTeamPlayers().size(); i++) {
                    setStartersMatchPlayed(i, teamLandlord);
                }
                for (int i = 0; i < teamGuest.getTeamPlayers().size(); i++) {
                    setStartersMatchPlayed(i, teamGuest);
                }
            }
        };
        threadPlayerMatch.start();
    }

    private void setAlphaAdminBtn(int alpha) {
        freeThrowBtn.getBackground().setAlpha(alpha);
        twoPointBtn.getBackground().setAlpha(alpha);
        threePointBtn.getBackground().setAlpha(alpha);
        reboundBtn.getBackground().setAlpha(alpha);
        assistBtn.getBackground().setAlpha(alpha);
        stealBtn.getBackground().setAlpha(alpha);
        blockBtn.getBackground().setAlpha(alpha);
        foulBtn.getBackground().setAlpha(alpha);
        turnoverBtn.getBackground().setAlpha(alpha);
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

    public void disableButtonsAfterAssist(int alpha) {
        freeThrowBtn.setEnabled(false);
        freeThrowBtn.getBackground().setAlpha(alpha);
        reboundBtn.setEnabled(false);
        reboundBtn.getBackground().setAlpha(alpha);
        assistBtn.setEnabled(false);
        assistBtn.getBackground().setAlpha(alpha);
        stealBtn.setEnabled(false);
        stealBtn.getBackground().setAlpha(alpha);
        blockBtn.setEnabled(false);
        blockBtn.getBackground().setAlpha(alpha);
        turnoverBtn.setEnabled(false);
        turnoverBtn.getBackground().setAlpha(alpha);
        substitutionBtn.setEnabled(false);
        substitutionBtn.getBackground().setAlpha(alpha);
        foulBtn.setEnabled(false);
        foulBtn.getBackground().setAlpha(alpha);

        this.assistMade = new Assist(playerObjChecked);
    }

    public void enableButtonAfterAssistAndShoot(int alpha) {
        freeThrowBtn.setEnabled(true);
        freeThrowBtn.getBackground().setAlpha(alpha);
        reboundBtn.setEnabled(true);
        reboundBtn.getBackground().setAlpha(alpha);
        assistBtn.setEnabled(true);
        assistBtn.getBackground().setAlpha(alpha);
        stealBtn.setEnabled(true);
        stealBtn.getBackground().setAlpha(alpha);
        blockBtn.setEnabled(true);
        blockBtn.getBackground().setAlpha(alpha);
        turnoverBtn.setEnabled(true);
        turnoverBtn.getBackground().setAlpha(alpha);
        substitutionBtn.setEnabled(true);
        substitutionBtn.getBackground().setAlpha(alpha);
        foulBtn.setEnabled(true);
        foulBtn.getBackground().setAlpha(alpha);

        this.assistMade = null;
    }


}
