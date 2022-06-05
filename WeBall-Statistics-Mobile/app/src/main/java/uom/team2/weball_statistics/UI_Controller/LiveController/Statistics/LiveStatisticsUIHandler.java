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
import uom.team2.weball_statistics.Service.TeamService;

/*
 * @author Leonard Pepa ics20033
 */
public class LiveStatisticsUIHandler {


    public static void updateTeamImageInMatch(Fragment fragment, TeamService teamService, View teamImageLayout) throws IOException, InterruptedException, NullPointerException {
        LiveStatisticsUIHandler.updateTeamImageInMatchHeader(fragment,
                "http://192.168.1.6/WeBall_Statistics-Backend/resources/team_images/" + teamService.getTeam().getBadgePath(),
                teamService.getTeam().getTeamName(),
                teamImageLayout);
    }

    private static void updateTeamImageInMatchHeader(Fragment fragment, String imageUrl, String name, View teamImageLayout) throws IOException, InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
        thread.join();
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

    public static void updateTeamImage(Fragment fragment, TeamService teamService, ImageView image) throws IOException, InterruptedException, NullPointerException {
        updateTeamImage(fragment,
                "http://192.168.1.6/WeBall_Statistics-Backend/resources/team_images/" + teamService.getTeam().getBadgePath(),
                image);
    }

    private static void updateTeamImage(Fragment fragment, String imageUrl, ImageView image) throws IOException, InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(imageUrl);
                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    fragment.requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            image.setImageBitmap(bmp);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        thread.join();

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
