package uom.team2.weball_statistics.Model.Statistics;

public class TeamStats extends Stats {

    protected int teamId;
    private int totalMatches;
    private int wins;
    private int looses;

    public TeamStats(String fTP, String twoPP, String threePP, int tI, int tM, int w, int l, int tS, int tR, int tA, int tB,
                     int tFouls, int tT){

        initializeStats(fTP, twoPP, threePP, tS, tR, tA, tB, tFouls, tT);// This method exists in class called Stats.
        teamId = tI;
        totalMatches = tM;
        wins = w;
        looses = l;
    }
}
