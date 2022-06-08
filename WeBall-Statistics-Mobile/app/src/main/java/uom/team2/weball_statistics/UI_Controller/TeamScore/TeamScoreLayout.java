package uom.team2.weball_statistics.UI_Controller.TeamScore;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import uom.team2.weball_statistics.R;

public class TeamScoreLayout {
    public static View createTeamScoreLayout(Fragment fragment, int position, String name){
        View teamScoreLayout = fragment.getLayoutInflater().inflate(R.layout.team_score_layout, null);
        TextView positionNumber = teamScoreLayout.findViewById(R.id.position_frame).findViewById(R.id.position);
        positionNumber.setText(String.valueOf(position));
        TextView teamName = teamScoreLayout.findViewById(R.id.teamName);
        teamName.setText(name);
        return teamScoreLayout;
    }
}
