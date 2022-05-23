package uom.team2.weball_statistics.UI_Controller.LiveController.Progress;

import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import uom.team2.weball_statistics.Model.Action;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.DAOAction;
import uom.team2.weball_statistics.databinding.FragmentLiveGameProgressBinding;

/*
 * @author Minas - Theodoros Charakopoulos ics20072
 */
public class LiveGameProgress extends Fragment {

    private FragmentLiveGameProgressBinding binding;
    private DAOAction daoAction = new DAOAction();;

    public LiveGameProgress() { }

    public static LiveGameProgress getInstance(){

        return new LiveGameProgress();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        daoAction.getdata();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLiveGameProgressBinding.inflate(inflater, container, false);
        //Initialize DAOAction

        // Generate action for test
        startQuarter(binding.actionsLayoutContainer, getLayoutInflater().inflate(R.layout.quarter_layout, null));
        binding.actionsLayoutContainer.addView(getLayoutInflater().inflate(R.layout.card_progress_layout_general, null), 0);
        for (int i = 0; i <= 10; i++) {
            addActionToFragment(binding.actionsLayoutContainer, getLayoutInflater().inflate(R.layout.card_progress_layout_guest, null), i);
            addActionToFragment(binding.actionsLayoutContainer, getLayoutInflater().inflate(R.layout.card_progress_layout_landlord, null), i);
            if (i == 4 || i == 7) {
                startQuarter(binding.actionsLayoutContainer, getLayoutInflater().inflate(R.layout.quarter_layout, null));
            }
        }

        //Testing write in firebase
        Action actionTest = new Action("+3 correct", "Minas - Ch", "osfp", "2.38");
        daoAction.insert(actionTest).addOnSuccessListener(s -> {
            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(f -> {
            Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
        });

        

        return binding.getRoot();
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
