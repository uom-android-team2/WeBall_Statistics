package uom.team2.weball_statistics.Model.Statistics;


public class TeamStats extends Stats {

    protected int teamId;
    private int totalMatches;
    private int wins;
    private int looses;

    public TeamStats(String fTP, String twoPP, String threePP, int tI, int tM, int w, int l)  {

        initializeStats(fTP, twoPP, threePP);// This method exists in class called Stats.
        teamId = tI;
        totalMatches = tM;
        wins = w;
        looses = l;




    }
}
