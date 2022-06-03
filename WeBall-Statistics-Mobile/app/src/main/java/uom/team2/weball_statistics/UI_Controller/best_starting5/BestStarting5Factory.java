package uom.team2.weball_statistics.UI_Controller.best_starting5;

public class BestStarting5Factory {

    //Strings need to be changed to Player
    private String bestPG;
    private String bestSG;
    private String bestSF;
    private String bestPF;
    private String bestC;

    //Get statistics from db for all players during their last match
    //Calculate Effic for every player


    //Needs API to access stats

    //----How to calculate Efficiency----
    /* Effic – a measure of a player's efficiency, Effic = Pts + Rebs + Ast + Stl + Blk – (TO + FG Misses + FT Misses) */

    //Instead of individual stats I can have the Player as a Parameter and get those stats from the db with his id
    public int calculateEffic(int Pts,int Rebs,int Ast, int Stl, int Blk, int TO, int FG_Misses, int FT_Misses){

        int playerEffic = Pts+Rebs+Ast+Stl+Blk-TO-FG_Misses-FT_Misses;
        return playerEffic;
    }

}
