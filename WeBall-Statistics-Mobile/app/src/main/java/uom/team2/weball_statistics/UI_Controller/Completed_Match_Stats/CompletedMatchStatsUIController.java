package uom.team2.weball_statistics.UI_Controller.Completed_Match_Stats;

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

    public void fillMatchHeaderInformation(CompletedMatchStats completedMatchStats, Match match) {
        CompletedMatchHeaderLayoutBinding completedMatchHeaderLayoutBinding  =  completedMatchStats.getBinding().includeMatchHeader;
    }
}
