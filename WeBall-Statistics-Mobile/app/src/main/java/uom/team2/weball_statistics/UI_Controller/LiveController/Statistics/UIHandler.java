package uom.team2.weball_statistics.UI_Controller.LiveController.Statistics;

import android.os.Build;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.HashMap;
import uom.team2.weball_statistics.R;
public class UIHandler {


    public UIHandler(){

    }

    public static void updateProgressBarLayoutTeam1(HashMap<String, View> mapOfProgressBarLayout, LiveStatistics key, int max, int value){
        View view =  mapOfProgressBarLayout.get(key.name());
        TextView statisticProgress = view.findViewById(R.id.team1_progress_text);
        statisticProgress.setText(String.valueOf(value));
        ProgressBar progressBar = view.findViewById(R.id.team1_progressbar);
        progressBar.setMax(max);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressBar.setProgress(value, true);
        }else{
            progressBar.setProgress(value);
        }
    }

    public static void updateProgressBarLayoutTeam2(HashMap<String, View> mapOfProgressBarLayout, LiveStatistics key, int max, int value){
        View view =  mapOfProgressBarLayout.get(key.name());
        TextView statisticProgress = view.findViewById(R.id.team2_progress_text);
        statisticProgress.setText(String.valueOf(value));
        ProgressBar progressBar = view.findViewById(R.id.team2_progressbar);
        progressBar.setMax(max);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressBar.setProgress(value, true);
        }else{
            progressBar.setProgress(value);
        }
    }



}
