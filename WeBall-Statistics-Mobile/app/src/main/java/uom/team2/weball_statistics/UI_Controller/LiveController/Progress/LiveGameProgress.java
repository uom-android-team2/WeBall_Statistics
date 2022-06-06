package uom.team2.weball_statistics.UI_Controller.LiveController.Progress;

import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import uom.team2.weball_statistics.Model.Status;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.DAOAction;
import uom.team2.weball_statistics.databinding.FragmentLiveGameProgressBinding;

/*
 * @author Minas - Theodoros Charakopoulos ics20072
 */
public class LiveGameProgress extends Fragment {

    private FragmentLiveGameProgressBinding binding;
    private DAOAction daoAction;

    public LiveGameProgress() { }

    public static LiveGameProgress getInstance(){

        return new LiveGameProgress();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        daoAction = DAOAction.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLiveGameProgressBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Generate action for test
        startQuarter(binding.actionsLayoutContainer, getLayoutInflater().inflate(R.layout.quarter_layout, null));
        binding.actionsLayoutContainer.addView(getLayoutInflater().inflate(R.layout.card_progress_layout_general, null), 0);
        for (int i = 0; i <= 10; i++) {
            addActionToFragment(binding.actionsLayoutContainer, getLayoutInflater().inflate(R.layout.card_progress_layout_guest, null), i);
            addActionToFragment(binding.actionsLayoutContainer, getLayoutInflater().inflate(R.layout.card_progress_layout_landlord, null), i);
            if (i == 4 || i == 7) {
                startQuarter(binding.actionsLayoutContainer, getLayoutInflater().inflate(R.layout.quarter_layout, null));
            }
        };

        Action action1 = new MatchFlow("0.00", 0, FlowType.START);
        Action action2 = new MatchFlow("1.00", 1, FlowType.PAUSE);
        Action action3 = new MatchFlow("1.20", 2, FlowType.RESUME);
        Action action4 = new MatchFlow("10", 3, FlowType.COMPLETED);
        Match match = new Match(5, null, null, new Date(), Status.ONGOING);
        daoAction.insert(action1, match);
        daoAction.insert(action2, match);
        daoAction.insert(action3, match);
        daoAction.insert(action4, match);

        Action action11 = new MatchFlow("0.00", 0, FlowType.START);
        Action action22 = new MatchFlow("1.00", 1, FlowType.PAUSE);
        Action action33 = new MatchFlow("1.20", 2, FlowType.RESUME);
        Action action44 = new MatchFlow("11", 3, FlowType.COMPLETED);

        Team team = new Team("Paok");
        Player player = new Player("Minas", "Charakopoulos");
        Action action55 = new Shot("12", 4, BelongsTo.GUEST, player, team, ShotType.THREE_POINTER, true, null);

        Match match2 = new Match(6, null, null, new Date(), Status.ONGOING);
        daoAction.insert(action11, match2);
        daoAction.insert(action22, match2);
        daoAction.insert(action33, match2);
        daoAction.insert(action44, match2);
        daoAction.insert(action55, match2);

        daoAction.getRealTimeData(match);
        daoAction.getRealTimeData(match2);
    }

    public void addActionToFragment(LinearLayout actionLayout, View actionAsView, int action) {
        TextView time = (TextView) actionAsView.findViewById(R.id.time_happened);
        time.setText(action + "");
        ImageView picture = (ImageView) actionAsView.findViewById(R.id.action_happened_photo);
        TextView smallDescription = (TextView) actionAsView.findViewById(R.id.action_small_desc);

        actionLayout.addView(actionAsView, 0);
    }

    public void startQuarter(LinearLayout actionLayout, View quarterView) {
        actionLayout.addView(quarterView, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
