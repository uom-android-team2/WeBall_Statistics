package uom.team2.weball_statistics.Model.Statistics;



public class PlayerStatsLive extends PlayerStats {

    private int idMatch;

    // This method is the constructor of class PlayerStatsLive which initialize the parameters of this class.
    public PlayerStatsLive(String fTP,String twoPP, String threePP, int tMimutes, int idM, int idP, int gameP){

        super(fTP, twoPP, threePP, tMimutes, idP, gameP);
        idMatch = idM;
    }


}
