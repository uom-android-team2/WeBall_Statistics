package uom.team2.weball_statistics.UI_Controller.TeamScore;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import uom.team2.weball_statistics.R;

public class TeamScoreLayout {
    public static View createTeamScoreLayout(Fragment fragment,int pos, String name, int g, int w ,int l, double p){
        View teamScoreLayout = fragment.getLayoutInflater().inflate(R.layout.team_score_layout, null);
        TextView positionNumber = teamScoreLayout.findViewById(R.id.position_frame).findViewById(R.id.position);
        positionNumber.setText(String.valueOf(pos+1));
        TextView teamName = teamScoreLayout.findViewById(R.id.teamName);
        teamName.setText(name);
        TextView games = teamScoreLayout.findViewById(R.id.games);
        games.setText(String.valueOf(g));
        TextView wins = teamScoreLayout.findViewById(R.id.wins);
        wins.setText(String.valueOf(w));
        TextView loses = teamScoreLayout.findViewById(R.id.loses);
        loses.setText(String.valueOf(g));
        TextView points = teamScoreLayout.findViewById(R.id.points);
        points.setText(String.valueOf(p));
        return teamScoreLayout;
    }
}
