package uom.team2.weball_statistics.UI_Controller.LiveController.Statistics;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.autofill.AutofillId;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentLiveGameStatisticsBinding;

/*
 * @author Leonard Pepa ics20033
 */
public class LiveGameStatistics extends Fragment {

    private FragmentLiveGameStatisticsBinding binding;

    public LiveGameStatistics() {
        // Required empty public constructor
    }

    public static LiveGameStatistics getInstance(){
        return new LiveGameStatistics();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLiveGameStatisticsBinding.inflate(inflater, container, false);

//        binding.include2.progressTeam1Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.RED));
//        binding.include2.progressTeam2Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
//        binding.include4.progressTeam1Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.RED));
//        binding.include4.progressTeam2Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
//        binding.include3.progressTeam1Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.RED));
//        binding.include3.progressTeam2Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
//        binding.include5.progressTeam1Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.RED));
//        binding.include5.progressTeam2Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
//        binding.include6.progressTeam1Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.RED));
//        binding.include6.progressTeam2Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
//        binding.include7.progressTeam1Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.RED));
//        binding.include7.progressTeam2Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
//        binding.include8.progressTeam1Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.RED));
//        binding.include8.progressTeam2Eustoxes.setProgressTintList(ColorStateList.valueOf(Color.BLACK));


        binding.container.addView(creatView("ευστοχες προσπαθειες"));
        binding.container.addView(creatView("τριποντα"));
        binding.container.addView(creatView("φαουλ"));
        binding.container.addView(creatView("φαουλ"));
        binding.container.addView(creatView("φαουλ"));
        binding.container.addView(creatView("φαουλ"));
        binding.container.addView(creatView("φαουλ"));
        binding.container.addView(creatView("φαουλ"));
        binding.container.addView(creatView("φαουλ"));
        binding.container.addView(creatView("φαουλ"));
        binding.container.addView(creatView("φαουλ"));
        binding.container.addView(creatView("φαουλ"));
        binding.container.addView(creatView("φαουλ"));
        binding.container.addView(creatView("φαουλ"));
        binding.container.addView(creatView("φαουλ"));
        binding.container.addView(creatView("φαουλ"));
        binding.container.addView(creatView("φαουλ"));
        binding.container.addView(creatView("φαουλ"));
        binding.container.addView(creatView("φαουλ"));
        binding.container.addView(creatView("φαουλ"));
        binding.container.addView(creatView("φαουλ"));

        binding.playerLiveStatsButton.setOnClickListener(e -> {

        });


        return binding.getRoot();
    }

    public View creatView(String statName){
        View view = getLayoutInflater().inflate(R.layout.live_statistics_progressbar_layout, null);
        Toast.makeText(getContext(), view.getId() + "", Toast.LENGTH_LONG).show();

        TextView eu = view.findViewById(R.id.eustoxes_prospathies);
        eu.setText(statName);
        int max = 100;
        int team1 = new Random().nextInt(68);
        int team2 = new Random().nextInt(34);
        ProgressBar pgTeam1 = view.findViewById(R.id.progress_team1_eustoxes);
        pgTeam1.setMax(max);
        pgTeam1.setProgress(team1);

        ProgressBar pgTeam2 = view.findViewById(R.id.progress_team2_eustoxes);
        pgTeam1.setMax(max);
        pgTeam1.setProgress(team2);



        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}