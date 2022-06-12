package uom.team2.weball_statistics.UI_Controller.LiveController.Progress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Date;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.BelongsTo;
import uom.team2.weball_statistics.Model.Actions.MatchFlow.FlowType;
import uom.team2.weball_statistics.Model.Actions.MatchFlow.MatchFlow;
import uom.team2.weball_statistics.Model.Actions.Shots.Shot;
import uom.team2.weball_statistics.Model.Actions.Shots.ShotType;
import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Referee;
import uom.team2.weball_statistics.Model.Status;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.Service.DAOAction;
import uom.team2.weball_statistics.databinding.FragmentLiveGameProgressBinding;

/*
 * @author Minas - Theodoros Charakopoulos ics20072
 */
public class LiveGameProgress extends Fragment {

    private FragmentLiveGameProgressBinding binding;
    private final LiveProgressUIController liveProgressUIController = LiveProgressUIController.getInstance();
    private DAOAction daoAction;

    public LiveGameProgress() {
    }

    public static LiveGameProgress getInstance(Bundle bundle) {
        LiveGameProgress liveGameProgress = new LiveGameProgress();
        liveGameProgress.setArguments(bundle);
        return liveGameProgress;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        daoAction = DAOAction.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
//        DAOLiveTeamService.getInstance().clockDataListener(this, binding.header.clock.clockText, 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLiveGameProgressBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Team teamTest1 = new Team(1, "Paok", "Thessaloniki", "fds");
        Team teamTest2 = new Team(2, "Osfp", "Athens", "Ffd");
        Referee refereeTest = new Referee(1, "Minas", "Theodoros");
        Match matchTest = new Match(1, teamTest1, teamTest2, new Date(), Status.ONGOING, refereeTest);
        liveProgressUIController.fillMatchInformation(this, matchTest);

        Testing();

        LiveGameProgress liveGameProgressFr = this;

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        Testing2();
                    }
                },
                10000
        );

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        Testing3();
                    }
                },
                13000
        );
    }

    public void Testing() {
        Match match2 = new Match(6, null, null, new Date(), Status.ONGOING, null);

        daoAction.countMatchActions(match2); //Test code to count actions for match
        Action action11 = new MatchFlow("0.00", FlowType.START, match2);
        daoAction.insert(action11, match2);

        daoAction.countMatchActions(match2); //Test code to count actions for match
        Action action22 = new MatchFlow("1.00", FlowType.PAUSE, match2);
        daoAction.insert(action22, match2);

        daoAction.countMatchActions(match2); //Test code to count actions for match
        Action action33 = new MatchFlow("1.20", FlowType.RESUME, match2);
        daoAction.insert(action33, match2);

        daoAction.countMatchActions(match2); //Test code to count actions for match
        Action action44 = new MatchFlow("11", FlowType.COMPLETED, match2);
        daoAction.insert(action44, match2);

        Team team = new Team("Paok");
        Player player = new Player("Minas", "Charakopoulos");

        daoAction.countMatchActions(match2); //Test code to count actions for match
        Action action55 = new Shot("12", BelongsTo.GUEST, player, team, ShotType.THREE_POINTER, true, null, match2);
        daoAction.insert(action55, match2);

        daoAction.countMatchActions(match2); //Test code to count actions for match
        Action action66 = new Shot("12", BelongsTo.HOME, player, team, ShotType.FREETHROW, true, null, match2);
        daoAction.insert(action66, match2);

        //That i will call from onViewCreated()
        daoAction.getRealTimeData(match2, this);
    }

    public void Testing2() {
        Match match2 = new Match(6, null, null, new Date(), Status.ONGOING, null);

        daoAction.countMatchActions(match2); //Test code to count actions for match
        Action action111 = new MatchFlow("0.00", FlowType.START, match2);
        daoAction.insert(action111, match2);

        daoAction.countMatchActions(match2); //Test code to count actions for match
        Action action222 = new MatchFlow("1.00", FlowType.PAUSE, match2);
        daoAction.insert(action222, match2);

        daoAction.countMatchActions(match2); //Test code to count actions for match
        Action action333 = new MatchFlow("1.20", FlowType.RESUME, match2);
        daoAction.insert(action333, match2);

        daoAction.countMatchActions(match2); //Test code to count actions for match
        Action action444 = new MatchFlow("11", FlowType.COMPLETED, match2);
        daoAction.insert(action444, match2);

        Team team = new Team("Paok");
        Player player = new Player("Minas", "Charakopoulos");

        daoAction.countMatchActions(match2); //Test code to count actions for match
        Action action555 = new Shot("12", BelongsTo.GUEST, player, team, ShotType.THREE_POINTER, true, null, match2);
        daoAction.insert(action555, match2);

        daoAction.countMatchActions(match2); //Test code to count actions for match
        Action action666 = new Shot("12", BelongsTo.HOME, player, team, ShotType.FREETHROW, true, null, match2);
        daoAction.insert(action666, match2);

        //That i will call from onViewCreated()
        daoAction.getRealTimeData(match2, this);
    }

    public void Testing3() {
        Match match2 = new Match(6, null, null, new Date(), Status.ONGOING, null);

        Team team = new Team("Paok");
        Player player = new Player("Minas", "Theodoros");

        daoAction.countMatchActions(match2); //Test code to count actions for match
        Action action55555 = new Shot("2.33'", BelongsTo.GUEST, player, team, ShotType.TWO_POINTER, true, null, match2);
        daoAction.insert(action55555, match2);

        //That i will call from onViewCreated()
        daoAction.getRealTimeData(match2, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public FragmentLiveGameProgressBinding getBinding() {
        return binding;
    }
}
