package uom.team2.weball_statistics.UI_Controller.LiveController.Statistics;


import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import uom.team2.weball_statistics.R;

public class ProgressBarLayoutFactory {

    public static  View createProgressBarLayout(Fragment fragment, String name){
        View progressBarLayout = fragment.getLayoutInflater().inflate(R.layout.live_statistics_progressbar_layout, null);
        TextView statsName = progressBarLayout.findViewById(R.id.name_of_statistic);
        statsName.setText(name);
        return progressBarLayout;
    }

}
