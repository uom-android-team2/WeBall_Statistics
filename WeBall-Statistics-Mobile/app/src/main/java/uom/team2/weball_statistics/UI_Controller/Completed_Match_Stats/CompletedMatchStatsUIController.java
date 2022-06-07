package uom.team2.weball_statistics.UI_Controller.Completed_Match_Stats;

import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.databinding.CompletedMatchHeaderLayoutBinding;
import uom.team2.weball_statistics.databinding.CompletedQuarterScoresBinding;

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

    public void fillCompletedCuarterInformation(CompletedMatchStats completedMatchStats) {
        CompletedQuarterScoresBinding completedQuarterScoresBinding  =  completedMatchStats.getBinding().includeCompletedQuarter;

        ImageView imageViewHome = completedQuarterScoresBinding.TeamHomeLogo;
        ImageView imageViewAway = completedQuarterScoresBinding.TeamAwayLogo;
        TextView textViewHomeq1 = completedQuarterScoresBinding.team1Q1;
        TextView textViewHomeq2 = completedQuarterScoresBinding.team1Q2;
        TextView textViewHomeq3 = completedQuarterScoresBinding.team1Q3;
        TextView textViewHomeq4 = completedQuarterScoresBinding.team1Q4;
        TextView textViewHomeFinal = completedQuarterScoresBinding.team1FinalScore;
        TextView textViewAwayq1 = completedQuarterScoresBinding.team2Q1;
        TextView textViewAwayq2 = completedQuarterScoresBinding.team2Q2;
        TextView textViewAwayq3 = completedQuarterScoresBinding.team2Q3;
        TextView textViewAwayq4 = completedQuarterScoresBinding.team2Q4;
        TextView textViewAwayFinal = completedQuarterScoresBinding.team2FinalScore;

        textViewHomeq1.setText("31");
        textViewAwayq1.setText("30");
        textViewHomeq2.setText("21");
        textViewAwayq2.setText("20");
        textViewHomeq3.setText("35");
        textViewAwayq3.setText("14");
        textViewHomeq4.setText("20");
        textViewAwayq4.setText("24");
        textViewHomeFinal.setText("107");
        textViewAwayFinal.setText("88");
    }
}
