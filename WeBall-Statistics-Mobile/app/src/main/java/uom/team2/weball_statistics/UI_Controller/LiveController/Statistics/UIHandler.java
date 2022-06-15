package uom.team2.weball_statistics.UI_Controller.LiveController.Statistics;

import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.HashMap;

import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.databinding.MatchHeaderLayoutBinding;

/*
 * @author Leonard Pepa ics20033
 */
public class UIHandler {

    public static void updateScore(Fragment fragment, MatchHeaderLayoutBinding layoutBinding, int team1Score, int team2Score) {
        if (fragment.getActivity() != null && fragment.isAdded()) {
            fragment.requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView textView = layoutBinding.scoreText;
                    textView.setText(team1Score + " - " + team2Score);
                }
            });
        }
    }

    public static void updateScore(Fragment fragment, TextView textView, int team1Score, int team2Score) {
        if (fragment.getActivity() != null && fragment.isAdded()) {
            fragment.requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText(team1Score + " - " + team2Score);
                }
            });
        }
    }

    public static void updateTeamImageInMatch(Fragment fragment, Team team, View teamImageLayout) {
        updateTeamImageInMatchHeader(fragment,
                Config.TEAM_IMAGES_RESOURCES + team.getBadgePath(),
                team.getTeamName(),
                teamImageLayout);
    }

    private static void updateTeamImageInMatchHeader(Fragment fragment, String imageUrl, String name, View teamImageLayout) {
        if (fragment.getActivity() != null && fragment.isAdded()) {
            fragment.requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView nameTextView = teamImageLayout.findViewById(R.id.team_name);
                    ImageView image = teamImageLayout.findViewById(R.id.team_logo);
                    nameTextView.setText(name);
                    Picasso.get()
                            .load(imageUrl)
                            .resize(200, 200)
                            .centerCrop()
                            .into(image);
                }
            });
        }
    }

    public static void updateSelectedPlayerImageLayout(Fragment fragment, String imageUrl, String name, View imageLayout) {
        if (fragment.getActivity() != null && fragment.isAdded()) {
            fragment.requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView nameTextView = imageLayout.findViewById(R.id.player_name);
                    nameTextView.setText(name);
                    ImageView image = imageLayout.findViewById(R.id.player_image);
                    Picasso.get()
                            .load(Config.PLAYER_IMAGES_RESOURCES + imageUrl)
                            .resize(200, 200)
                            .centerCrop()
                            .into(image);
                }
            });
        }

    }

    public static void updateTeamImage(Fragment fragment, Team team, ImageView image) {
        updateTeamImage(fragment,
                Config.TEAM_IMAGES_RESOURCES + team.getBadgePath(),
                image);
    }

    private static void updateTeamImage(Fragment fragment, String imageUrl, ImageView image) {
        if (fragment.getActivity() != null && fragment.isAdded()) {
            fragment.requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Picasso.get()
                            .load(imageUrl)
                            .resize(200, 200)
                            .centerCrop()
                            .into(image);
                }
            });
        }
    }

    public static void updateProgressBarLayoutTeam1(Fragment fragment, HashMap<String, View> mapOfProgressBarLayout, LiveStatisticsEnum key, int max, int value) {
        if (fragment.getActivity() != null && fragment.isAdded()) {
            fragment.requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    View view = mapOfProgressBarLayout.get(key.name());
                    TextView statisticProgress = view.findViewById(R.id.team1_progress_text);
                    ProgressBar progressBar = view.findViewById(R.id.team1_progressbar);
                    if (max != progressBar.getMax()) {
                        progressBar.setMax(max);
                    }
                    if (value != progressBar.getProgress()) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            progressBar.setProgress(value, true);
                        } else {
                            progressBar.setProgress(value);
                        }
                    }
                    String stringgValue = String.valueOf(value);

                    if (!stringgValue.equalsIgnoreCase(statisticProgress.getText().toString())) {
                        statisticProgress.setText(stringgValue);
                    }
                }
            });
        }
    }

    public static void updateProgressBarLayoutTeam2(Fragment fragment, HashMap<String, View> mapOfProgressBarLayout, LiveStatisticsEnum key, int max, int value) {
        if (fragment.getActivity() != null && fragment.isAdded()) {
            fragment.requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    View view = mapOfProgressBarLayout.get(key.name());
                    TextView statisticProgress = view.findViewById(R.id.team2_progress_text);
                    ProgressBar progressBar = view.findViewById(R.id.team2_progressbar);
                    if (max != progressBar.getMax()) {
                        progressBar.setMax(max);
                    }
                    if (value != progressBar.getProgress()) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            progressBar.setProgress(value, true);
                        } else {
                            progressBar.setProgress(value);
                        }
                    }
                    String stringgValue = String.valueOf(value);

                    if (!stringgValue.equalsIgnoreCase(statisticProgress.getText().toString())) {
                        statisticProgress.setText(stringgValue);
                    }
                }
            });
        }
    }


}
