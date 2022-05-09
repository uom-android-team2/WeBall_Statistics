package uom.team2.weball_statistics.Model.Statistics;

public class TeamStatsLive extends TeamStats {

    private int matchId;

    public TeamStatsLive(String fTP, String twoPP, String threePP, int mI, int tS, int tR, int tA, int tB,
                     int tFouls, int tT){
        super( fTP, twoPP, threePP, mI, '_', '_', '_', tS, tR, tA, tB, tFouls, tT);


        initializeStats(fTP, twoPP, threePP, tS, tR, tA, tB, tFouls, tT); // This method exists in class called Stats.
        matchId= mI;
    }
}
