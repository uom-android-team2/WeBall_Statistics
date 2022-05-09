package uom.team2.weball_statistics.Model.Statistics;

public class PlayerStats extends Stats {

    protected int idPlayer;
    protected int gamesPlayed;
    protected int totalMinutes;

    // This method is the constructor of class PlayerStats which initialize the parameters of this class.
    public PlayerStats(String fTP,String twoPP, String threePP, int tSteels, int tRebounds, int tAssists, int tBlocks, int tFouls, int tTurnovers, int tMinutes, int idP , int gameP){

        initializeStats(fTP, twoPP, threePP, tSteels, tRebounds, tAssists, tBlocks, tFouls, tTurnovers); // This method exists in class called Stats.
        idPlayer = idP;
        gamesPlayed = gameP;
        totalMinutes = tMinutes;
    }
}