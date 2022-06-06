package uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController.TeamStats;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.BitSet;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentTeamStatsBinding;
/*
 * @author Aravella Lousta ics20032
 */
public class TeamStatsFragment extends Fragment {

    private FragmentTeamStatsBinding binding;

    public TeamStatsFragment() {
        // Required empty public constructor
    }

    public static TeamStatsFragment newInstance() {
        TeamStatsFragment fragment = new TeamStatsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTeamStatsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeTitles();

        try {
            createRow(binding.assistsPerGame.tableRowContainer,
                    "https://raw.githubusercontent.com/uom-android-team2/WeBall_Statistics-Backend/master/resources/team_images/1st_50px.png",
                    "aravella",
                    5,
                    "33,5");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void createRow(LinearLayout linearLayout, String url, String name, int number, String score) throws InterruptedException {
        View view = linearLayout.getChildAt(number);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL imageUrl = new URL(url);
                    Bitmap bmp = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());
                    TeamStatsFragment.this.requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView scoreView = view.findViewById(R.id.score);
                            scoreView.setText(score);

                            TextView numberView = view.findViewById(R.id.number);
                            numberView.setText(String.valueOf(number));

                            TextView nameView = view.findViewById(R.id.teamName);
                            nameView.setText(name);

                            ImageView imageView = view.findViewById(R.id.badge);
                            imageView.setImageBitmap(bmp);
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

    public void initializeTitles(){
        binding.assistsPerGame.tableTitle.setText("Assists Per Game");
        binding.blocksPerGame.tableTitle.setText("Blocks Per Game");
        binding.reboundsPerGame.tableTitle.setText("Rebounds Per Game");

        changeLabel(binding.assistsPerGame.tableRowContainer, "APG");
        changeLabel(binding.reboundsPerGame.tableRowContainer, "RPG");
        changeLabel(binding.blocksPerGame.tableRowContainer, "BPG");

    }

    public void changeLabel(LinearLayout linearLayout, String value){
        for (int i=1; i<linearLayout.getChildCount(); i++){
            TextView textView = linearLayout.getChildAt(i).findViewById(R.id.label);
            textView.setText(value);
        }

    }
}