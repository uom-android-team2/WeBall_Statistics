package uom.team2.weball_statistics.UIFactory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.net.URL;

import uom.team2.weball_statistics.R;

/*
* @author Leonard Pepa ics20033
 */
public class LayoutFactory {


    public static View createProgressBarLayout(Fragment fragment, String name){
        View progressBarLayout = fragment.getLayoutInflater().inflate(R.layout.live_statistics_progressbar_layout, null);
        TextView statsName = progressBarLayout.findViewById(R.id.name_of_statistic);
        statsName.setText(name);
        return progressBarLayout;
    }

    public static View createPayerImageLayout(Fragment fragment, String name, String imageUrl) throws IOException {
        View playerImageLayout = fragment.getLayoutInflater().inflate(R.layout.player_image_layout, null);
        TextView playerName = playerImageLayout.findViewById(R.id.player_name);
        playerName.setText(name);
        ImageView playerImage = playerImageLayout.findViewById(R.id.player_image);

        playerImage.requestLayout();

        playerImage.getLayoutParams().height = 70;
        playerImage.getLayoutParams().width = 100;

        URL url = new URL(imageUrl);
        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        playerImage.setImageBitmap(bmp);
        return  playerImageLayout;
    }

}
