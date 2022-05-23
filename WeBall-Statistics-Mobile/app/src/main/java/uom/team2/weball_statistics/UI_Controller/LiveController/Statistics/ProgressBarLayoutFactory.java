package uom.team2.weball_statistics.UI_Controller.LiveController.Statistics;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.lang.reflect.Array;

import uom.team2.weball_statistics.R;

public class ProgressBarLayoutFactory {

    private static  View createProgressBarLayout(Fragment fragment, String name){
        View progressBarLayout = fragment.getLayoutInflater().inflate(R.layout.live_statistics_progressbar_layout, null);
        progressBarLayout.setTag(name);
        TextView statsName = progressBarLayout.findViewById(R.id.name_of_statistic);
        statsName.setText(name);
        return progressBarLayout;
    }

    public static void initializeAndAddProgressLayouts(Fragment fragment,  LinearLayout progressBarContainer){
        String[] statisticsArray;
        statisticsArray = fragment.getResources().getStringArray(R.array.team_statistics);

        for(String stat: statisticsArray){
            progressBarContainer.addView(createProgressBarLayout(fragment, stat));
        }
    }
}
