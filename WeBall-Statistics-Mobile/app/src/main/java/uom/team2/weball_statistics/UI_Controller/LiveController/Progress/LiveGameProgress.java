package uom.team2.weball_statistics.UI_Controller.LiveController.Progress;

import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import uom.team2.weball_statistics.Model.Actions.FlowType;
import uom.team2.weball_statistics.Model.Actions.MatchFlow;
import uom.team2.weball_statistics.Model.Match;
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
        daoAction = new DAOAction();
        daoAction.get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLiveGameProgressBinding.inflate(inflater, container, false);
        //Initialize DAOAction

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
