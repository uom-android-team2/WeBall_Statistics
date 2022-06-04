package uom.team2.weball_statistics.UI_Controller.LiveController.Statistics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import uom.team2.weball_statistics.R;

/*
 * @author Leonard Pepa ics20033
 */
public class LiveStatisticsUIHandler {


    public static void updateTeamImageInMatchHeader(Fragment fragment, String imageUrl, String name, View teamImageLayout) throws IOException {
        URL url = new URL(imageUrl);
        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        fragment.requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView nameTextView = teamImageLayout.findViewById(R.id.team_name);
                ImageView image = teamImageLayout.findViewById(R.id.team_logo);
                nameTextView.setText(name);
                image.setImageBitmap(bmp);
            }
        });
    }

    public static void updateSelectedPlayerImageLayout(Fragment fragment, String imageUrl, String name, View imageLayout) throws IOException {
        URL url = new URL(imageUrl);
        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        fragment.requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView nameTextView = imageLayout.findViewById(R.id.player_name);
                nameTextView.setText(name);
                ImageView image = imageLayout.findViewById(R.id.player_image);
                image.setImageBitmap(bmp);
            }
        });
    }

    public static void updateTeamImage(Fragment fragment, String imageUrl, ImageView image) throws IOException {
        URL url = new URL(imageUrl);
        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        fragment.requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                image.setImageBitmap(bmp);
            }
        });
    }

    public static void updateProgressBarLayoutTeam1(Fragment fragment, HashMap<String, View> mapOfProgressBarLayout, LiveStatisticsEnum key, int max, int value) {
        View view = mapOfProgressBarLayout.get(key.name());
        TextView statisticProgress = view.findViewById(R.id.team1_progress_text);
        ProgressBar progressBar = view.findViewById(R.id.team1_progressbar);
        fragment.requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setMax(max);
                statisticProgress.setText(String.valueOf(value));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    progressBar.setProgress(value, true);
                } else {
                    progressBar.setProgress(value);
                }

            }
        });
    }

    public static void updateProgressBarLayoutTeam2(Fragment fragment, HashMap<String, View> mapOfProgressBarLayout, LiveStatisticsEnum key, int max, int value) {
        View view = mapOfProgressBarLayout.get(key.name());
        TextView statisticProgress = view.findViewById(R.id.team2_progress_text);
        ProgressBar progressBar = view.findViewById(R.id.team2_progressbar);
        fragment.requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setMax(max);
                statisticProgress.setText(String.valueOf(value));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    progressBar.setProgress(value, true);
                } else {
                    progressBar.setProgress(value);
                }
            }
        });
    }


}
