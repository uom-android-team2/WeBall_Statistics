package uom.team2.weball_statistics.UI_Controller.Completed_Match_Stats;

import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.databinding.CompletedMatchHeaderLayoutBinding;

public class CompletedMatchStatsUIController {

    public static CompletedMatchStatsUIController instance = new CompletedMatchStatsUIController();

    //Implement singleton pattern
    public static CompletedMatchStatsUIController getInstance(){
        if(instance == null){
            instance = new CompletedMatchStatsUIController();
        }
        return instance;
    }

    public void fillMatchHeaderInformation(CompletedMatchStats completedMatchStats) {
        CompletedMatchHeaderLayoutBinding completedMatchHeaderLayoutBinding  =  completedMatchStats.getBinding().includeMatchHeader;

        ImageView imageViewHome = completedMatchHeaderLayoutBinding.team1Logo;
        ImageView imageViewAway = completedMatchHeaderLayoutBinding.team2Logo;
        TextView textViewScore = completedMatchHeaderLayoutBinding.scoreText;
        TextView textViewHomeTeamName = completedMatchHeaderLayoutBinding.team1Name;
        TextView textViewHomeTeamAway = completedMatchHeaderLayoutBinding.team2Name;
        TextView textViewMatchDate = completedMatchHeaderLayoutBinding.matchStartDate;

        textViewScore.setText("43-54");
        textViewHomeTeamName.setText("Paok");
        textViewHomeTeamAway.setText("Aris");
        textViewMatchDate.setText("14/05/2022 17:00");
    }
}
