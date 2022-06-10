package uom.team2.weball_statistics.UI_Controller.Completed_Match_Stats;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import uom.team2.weball_statistics.databinding.CompleteMatchLeadersBinding;
import uom.team2.weball_statistics.databinding.CompletedMatchHeaderLayoutBinding;
import uom.team2.weball_statistics.databinding.CompletedMatchTeamPlayerStatsBinding;
import uom.team2.weball_statistics.databinding.CompletedMatchTeamStatsBinding;
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

    public void fillCompletedQuarterInformation(CompletedMatchStats completedMatchStats) {
        CompletedQuarterScoresBinding completedQuarterScoresBinding  =  completedMatchStats.getBinding().includeCompletedQuarter;

        ImageView imageViewHome = completedQuarterScoresBinding.TeamHomeLogo;
        ImageView imageViewAway = completedQuarterScoresBinding.TeamAwayLogo;
        TextView textViewHomeQ1 = completedQuarterScoresBinding.team1Q1;
        TextView textViewHomeQ2 = completedQuarterScoresBinding.team1Q2;
        TextView textViewHomeQ3 = completedQuarterScoresBinding.team1Q3;
        TextView textViewHomeQ4 = completedQuarterScoresBinding.team1Q4;
        TextView textViewHomeFinal = completedQuarterScoresBinding.team1FinalScore;
        TextView textViewAwayQ1 = completedQuarterScoresBinding.team2Q1;
        TextView textViewAwayQ2 = completedQuarterScoresBinding.team2Q2;
        TextView textViewAwayQ3 = completedQuarterScoresBinding.team2Q3;
        TextView textViewAwayQ4 = completedQuarterScoresBinding.team2Q4;
        TextView textViewAwayFinal = completedQuarterScoresBinding.team2FinalScore;

        textViewHomeQ1.setText("31");
        textViewAwayQ1.setText("30");
        textViewHomeQ2.setText("21");
        textViewAwayQ2.setText("20");
        textViewHomeQ3.setText("35");
        textViewAwayQ3.setText("14");
        textViewHomeQ4.setText("20");
        textViewAwayQ4.setText("24");
        textViewHomeFinal.setText("107");
        textViewAwayFinal.setText("88");
    }

    public void fillCompletedMatchTeamPlayersStats(CompletedMatchStats completedMatchStats) {
        CompletedMatchTeamPlayerStatsBinding CompletedMatchTeamPlayersStatsBinding  = completedMatchStats.getBinding().includeTeamPlayerStats;

        Button HomeTeamButton = CompletedMatchTeamPlayersStatsBinding.Team1Button;
        Button AwayTeamButton = CompletedMatchTeamPlayersStatsBinding.Team2Button;

        HomeTeamButton.setText("Paok");
        AwayTeamButton.setText("Aris");
    }

    public void fillCompletedMatchLeaders(CompletedMatchStats completedMatchStats) {
        CompleteMatchLeadersBinding CompletedMatchTeamPlayersStatsBinding  = completedMatchStats.getBinding().includeCompletedLeaders;

        ImageView HomeTeamLogo = CompletedMatchTeamPlayersStatsBinding.TeamHomeLogo;
        ImageView AwayTeamLogo = CompletedMatchTeamPlayersStatsBinding.TeamAwayLogo;
    }


    public void fillCompletedMatchTeamStats(CompletedMatchStats completedMatchStats) {
        CompletedMatchTeamStatsBinding CompletedMatchTeamStatsBinding  = completedMatchStats.getBinding().includeCompletedTeamStats;

        ImageView HomeTeamLogo = CompletedMatchTeamStatsBinding.HomeTeamLogo;
        ImageView AwayTeamLogo = CompletedMatchTeamStatsBinding.AwayTeamLogo;
    }
}
