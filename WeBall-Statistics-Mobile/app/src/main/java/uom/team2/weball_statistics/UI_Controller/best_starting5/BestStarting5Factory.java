package uom.team2.weball_statistics.UI_Controller.best_starting5;

public class BestStarting5Factory {

    //Strings need to be changed to Player
    private String bestPG;
    private String bestSG;
    private String bestSF;
    private String bestPF;
    private String bestC;

    //Get statistics from db for all players during their last match
    //Calculate Plus-Minus and Effic for every player
    //Find Highest Plus-Minus per position and create fragment for this player



    //----How to calculate Efficiency----
    /* Effic – a measure of a player's efficiency, Effic = Pts + Rebs + Ast + Stl + Blk – (TO + FG Misses + FT Misses) */

    //Instead of individual stats I can have the Player as a Parameter and get those stats from his fields
    public int calculateEffic(int Pts,int Rebs,int Ast, int Stl, int Blk, int TO, int FG_Misses, int FT_Misses){

        int playerEffic = Pts+Ast+Stl+Blk-TO-FG_Misses-FT_Misses;
        return playerEffic;
    }

    //----How to calculate Plus-Minus----
    /* +/- The plus/minus statistic is a measure of the point differential when players are in and
    out of a game. It is calculated by taking the difference in the score when the player enters the
    game and subtracting it from the score when the player exits the game. These differences are added
    up over the entire game to give the score. Thus, it is a measure of the impact a player has on the
    score of the game when he is in the game without measuring any specific statistic. */

    //Instead of individual plusMinus stat I can have the Player as a Parameter and get that stat from his field
    public int calculatePlusMinus(int totalPlusMinus,int myTeamScore,int opponentTeamScore){
        //int totalPlusMinus = player.getPlusMinus()+myTeamScore-opponentTeamScore
        totalPlusMinus = totalPlusMinus+myTeamScore-opponentTeamScore;

        return totalPlusMinus;
    }
}
