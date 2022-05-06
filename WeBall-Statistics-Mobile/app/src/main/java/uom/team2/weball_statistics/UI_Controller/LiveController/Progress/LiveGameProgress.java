package uom.team2.weball_statistics.UI_Controller.LiveController.Progress;

import android.os.Bundle;
import android.view.*;

import androidx.fragment.app.Fragment;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.LiveGameStatistics;
import uom.team2.weball_statistics.databinding.FragmentLiveGameProgressBinding;

/*
 * @author Minas - Theodoros Charakopoulos ics20072
 */
public class LiveGameProgress extends Fragment {

    private FragmentLiveGameProgressBinding binding;

    public LiveGameProgress() { }

    public static LiveGameProgress getInstance(){

        return new LiveGameProgress();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLiveGameProgressBinding.inflate(inflater, container, false);

        //Just for testing...Improvements will made
        for (int i = 0; i <= 10; i++) {
            View actionLayoutLandlord = getLayoutInflater().inflate(R.layout.card_progress_layout_landlord, null);
            View actionLayoutGuest = getLayoutInflater().inflate(R.layout.card_progress_layout_guest, null);
            binding.actionsLayoutContainer.addView(actionLayoutLandlord);
            binding.actionsLayoutContainer.addView(actionLayoutGuest);
        }
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
