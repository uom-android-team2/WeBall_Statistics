package uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController.LeadersStatsController;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.R;

public class LeadersStatsLayouts {




    public static View createPlayersStatsLayout(Fragment fragment, String name){
        View playersStatslayout = fragment.getLayoutInflater().inflate(R.layout.fragment_players_stats_layout, null);
        TextView statsName = playersStatslayout.findViewById(R.id.statisticTitle);
        statsName.setText(name);
        return playersStatslayout;
    }

    public static View expandPlayersStatsLayout(Fragment fragment, ArrayList<Player> players){
        View playersStatslayout = fragment.getLayoutInflater().inflate(R.layout.fragment_players_stats_layout, null);
        View playerlayout = fragment.getLayoutInflater().inflate(R.layout.fragment_player_layout,null);
        LinearLayout ranksContainer = playersStatslayout.findViewById(R.id.ranksContainer);
        for (int i=0;i<players.size();i++)
        {
            ranksContainer.addView(playerlayout);
            playerlayout.setTag("player"+ i);
        }

        return playersStatslayout;
    }


}
