package uom.team2.weball_statistics.UI_Controller.LiveController.Statistics;

import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;

import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;

/*
 * @author Leonard Pepa ics20033
 */
public class UIHandler {


    public static void updateTeamImageInMatch(Fragment fragment, Team team, View teamImageLayout) throws IOException, InterruptedException, NullPointerException {
        updateTeamImageInMatchHeader(fragment,
                "http://" + IP.IP + "/WeBall_Statistics-Backend/resources/team_images/" + team.getBadgePath(),
                team.getTeamName(),
                teamImageLayout);
    }

    private static void updateTeamImageInMatchHeader(Fragment fragment, String imageUrl, String name, View teamImageLayout) throws IOException, InterruptedException {
        fragment.requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView nameTextView = teamImageLayout.findViewById(R.id.team_name);
                ImageView image = teamImageLayout.findViewById(R.id.team_logo);
                nameTextView.setText(name);
                Picasso.get()
                        .load(imageUrl)
                        .resize(20, 20)
                        .centerCrop()
                        .into(image);
            }
        });
    }

    public static void updateSelectedPlayerImageLayout(Fragment fragment, String imageUrl, String name, View imageLayout) throws IOException, InterruptedException {
        fragment.requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView nameTextView = imageLayout.findViewById(R.id.player_name);
                nameTextView.setText(name);
                ImageView image = imageLayout.findViewById(R.id.player_image);
                Picasso.get()
                        .load(imageUrl)
                        .resize(70, 70)
                        .centerCrop()
                        .into(image);
            }
        });

    }

    public static void updateTeamImage(Fragment fragment, Team team, ImageView image) throws IOException, InterruptedException, NullPointerException {
        updateTeamImage(fragment,
                "http://" + IP.IP + "/WeBall_Statistics-Backend/resources/team_images/" + team.getBadgePath(),
                image);
    }

    private static void updateTeamImage(Fragment fragment, String imageUrl, ImageView image) throws IOException, InterruptedException {
        fragment.requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Picasso.get()
                        .load(imageUrl)
                        .resize(20, 20)
                        .centerCrop()
                        .into(image);
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
