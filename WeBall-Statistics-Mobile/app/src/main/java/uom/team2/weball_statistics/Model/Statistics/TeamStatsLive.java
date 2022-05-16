package uom.team2.weball_statistics.Model.Statistics;

public class TeamStatsLive extends TeamStats {

    private int matchId;

    public TeamStatsLive(String fTP, String twoPP, String threePP, int mI){
        super( fTP, twoPP, threePP, mI, '_', '_', '_');


        initializeStats(fTP, twoPP, threePP); // This method exists in class called Stats.
        matchId= mI;
    }
}
