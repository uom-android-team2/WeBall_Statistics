package uom.team2.weball_statistics.UI_Controller.LiveController.Statistics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import uom.team2.weball_statistics.R;

/*
 * @author Leonard Pepa ics20033
 */
public class LiveStatisticsUIHandler {


    public LiveStatisticsUIHandler(){

    }

    public static void updateTeamImage(String imageUrl, ImageView image) throws IOException {
        URL url = new URL(imageUrl);
        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        image.setImageBitmap(bmp);
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
