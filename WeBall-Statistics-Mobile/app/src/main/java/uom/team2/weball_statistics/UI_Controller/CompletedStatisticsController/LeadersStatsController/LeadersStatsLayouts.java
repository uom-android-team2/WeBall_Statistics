package uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController.LeadersStatsController;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import uom.team2.weball_statistics.R;

public class LeadersStatsLayouts {

    public static View createPlayersStatsLayout(Fragment fragment, String name){
        View playersStatslayout = fragment.getLayoutInflater().inflate(R.layout.fragment_players_stats_layout, null);
        TextView statsName = playersStatslayout.findViewById(R.id.TITLE);
        statsName.setText(name);
        return playersStatslayout;
    }
}
