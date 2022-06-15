package uom.team2.weball_statistics.UIFactory;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.configuration.Config;

/*
 * @author Leonard Pepa ics20033
 */
public class LayoutFactory {


    public static View createProgressBarLayout(Fragment fragment, String name) {
        View progressBarLayout = fragment.getLayoutInflater().inflate(R.layout.live_statistics_progressbar_layout, null);
        TextView statsName = progressBarLayout.findViewById(R.id.name_of_statistic);
        statsName.setText(name);
        return progressBarLayout;
    }

    public static View createPayerImageLayout(Fragment fragment, String name, String imageEndPoint){
        if (fragment.getActivity() != null && fragment.isAdded()) {
            View playerImageLayout = fragment.getLayoutInflater().inflate(R.layout.player_image_layout, null);
            TextView playerName = playerImageLayout.findViewById(R.id.player_name);
            ImageView playerImage = playerImageLayout.findViewById(R.id.player_image);

            fragment.requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    playerName.setText(name);
                    Picasso.get()
                            .load(Config.PLAYER_IMAGES_RESOURCES + imageEndPoint)
                            .resize(200, 200)
                            .centerCrop()
                            .into(playerImage);

                }
            });
            return playerImageLayout;
        }
        return null;
    }

    public static ProgressDialog createNonCancelableProgressBar(Context context) {
        ProgressDialog progress = new ProgressDialog(context);
        progress.setCancelable(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return progress;
    }

    public static ProgressDialog createNonCancelableProgressBar(Context context, String title, String message) {
        ProgressDialog progress = new ProgressDialog(context);
        progress.setCancelable(false);
        progress.setTitle(title);
        progress.setMessage(message);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return progress;
    }

}
