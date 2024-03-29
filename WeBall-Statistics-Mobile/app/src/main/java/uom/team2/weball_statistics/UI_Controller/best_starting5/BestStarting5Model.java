package uom.team2.weball_statistics.UI_Controller.best_starting5;
import android.os.StrictMode;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.*;
import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.PlayerLiveStatistics;
import uom.team2.weball_statistics.Model.TeamLiveStatistics;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.utils.JSONHandler;


/*
 * @author Dionisis Lougaris ics20058
 */
public class BestStarting5Model {

    private ArrayList<Match> allMatches = new ArrayList<Match>();
    private ArrayList<PlayerLiveStatistics> allPlayerLiveStatistics= new ArrayList<>();
    private ArrayList<PlayerLiveStatistics> usefullData = new ArrayList<>();
    private ArrayList<Match> allLatestMatches = new ArrayList<>();
    private ArrayList<Player> allPlayers = new ArrayList<Player>();
    private Player bestPG;
    private Player bestSG;
    private Player bestSF;
    private Player bestPF;
    private Player bestC;

    //Get statistics from db for all players during their last match
    //Calculate Effic for every player

    public BestStarting5Model() throws JSONException, IOException {
        //Chaining all methods
        allMatches=BestStarting5Service.getCompletedMatches();
        allLatestMatches = this.removePreviousWeekMatches(allMatches);
        allPlayerLiveStatistics=BestStarting5Service.getPlayerLiveStatistics();
        allPlayers=BestStarting5Service.getPlayers();
        this.cleanData();
        this.getBestStarting5();

    }

    //----How to calculate Efficiency----
    /* Effic – a measure of a player's efficiency, Effic = Pts + Rebs + Ast + Stl + Blk – (TO*4 + FG Misses + FT Misses)-Fouls*2 */

    //Instead of individual stats I can have the Player as a Parameter and get those stats from the db with his id
    public static int calculateEffic(PlayerLiveStatistics myStats){

        int playerEffic = (myStats.getSuccessful_twopointer()*2)+(myStats.getSuccessful_threepointer()*3)+(myStats.getSuccessful_freethrow()*1)
        +myStats.getRebound()+myStats.getAssist()+myStats.getSteal()+myStats.getBlock()-(myStats.getTurnover()*4)-(myStats.getFoul()*2)
        -(myStats.getTotal_threepointer()+myStats.getTotal_twopointer()-myStats.getSuccessful_threepointer()-myStats.getSuccessful_twopointer())
                -(myStats.getTotal_freethrow()-myStats.getSuccessful_freethrow());
        return playerEffic;
    }
    public static int calculateTeamEffic(TeamLiveStatistics myStats){

        int teamEffic = (myStats.getSuccessful_twopointer()*2)+(myStats.getSuccessful_threepointer()*3)+(myStats.getSuccessful_freethrow()*1)
                +myStats.getRebound()+myStats.getAssist()+myStats.getSteal()+myStats.getBlock()-(myStats.getTurnover()*4)-(myStats.getFoul()*2)
                -(myStats.getTotal_threepointer()+myStats.getTotal_twopointer()-myStats.getSuccessful_threepointer()-myStats.getSuccessful_twopointer())
                -(myStats.getTotal_freethrow()-myStats.getSuccessful_freethrow());
        return teamEffic;
    }

    public void cleanData() {
        //Getting playerstatistics for all COMPLETED LATEST matches
        for (int i = 0; i < allPlayerLiveStatistics.size(); i++) {
            if (this.hasMatchInCompleted(allPlayerLiveStatistics.get(i).getMatch_id())) {
                usefullData.add(allPlayerLiveStatistics.get(i));
            }
        }
    }

                                    int maxEfficPG=-100;
                                    int maxEfficSG=-100;
                                    int maxEfficSF=-100;
                                    int maxEfficPF=-100;
                                    int maxEfficC=-100;


    public void getBestStarting5() {

        if(usefullData.size()<5){
            bestPG = new Player(0,"PLAYER","NOT FOUND",0,"-","-","-");
            bestSG = new Player(0,"PLAYER","NOT FOUND",0,"-","-","-");
            bestSF = new Player(0,"PLAYER","NOT FOUND",0,"-","-","-");
            bestPF = new Player(0,"PLAYER","NOT FOUND",0,"-","-","-");
            bestC = new Player(0,"PLAYER","NOT FOUND",0,"-","-","-");
        }
        else {
            for (int i = 0; i < usefullData.size(); i++) {

                if ((this.findPlayerById(usefullData.get(i).getPlayer_id()).getPosition()).equals("POINT_GUARD")) {
                    int tempEffic = this.calculateEffic(usefullData.get(i));
                    if (tempEffic >= maxEfficPG) {
                        maxEfficPG = tempEffic;
                        bestPG = this.findPlayerById(usefullData.get(i).getPlayer_id());
                        this.findPlayerById(usefullData.get(i).getPlayer_id()).setEfficiency(tempEffic);
                    }
                }
                if ((this.findPlayerById(usefullData.get(i).getPlayer_id()).getPosition()).equals("SHOOTING_GUARD")) {
                    int tempEffic = this.calculateEffic(usefullData.get(i));
                    if (tempEffic >= maxEfficSG) {
                        maxEfficSG = tempEffic;
                        bestSG = this.findPlayerById(usefullData.get(i).getPlayer_id());
                        this.findPlayerById(usefullData.get(i).getPlayer_id()).setEfficiency(tempEffic);
                    }
                }
                if ((this.findPlayerById(usefullData.get(i).getPlayer_id()).getPosition()).equals("SMALL_FORWARD")) {
                    int tempEffic = this.calculateEffic(usefullData.get(i));
                    if (tempEffic >= maxEfficSF) {
                        maxEfficSF = tempEffic;
                        bestSF = this.findPlayerById(usefullData.get(i).getPlayer_id());
                        this.findPlayerById(usefullData.get(i).getPlayer_id()).setEfficiency(tempEffic);
                    }
                }
                if ((this.findPlayerById(usefullData.get(i).getPlayer_id()).getPosition()).equals("POWER_FORWARD")) {
                    int tempEffic = this.calculateEffic(usefullData.get(i));
                    if (tempEffic >= maxEfficPF) {
                        maxEfficPF = tempEffic;
                        bestPF = this.findPlayerById(usefullData.get(i).getPlayer_id());
                        this.findPlayerById(usefullData.get(i).getPlayer_id()).setEfficiency(tempEffic);
                    }
                }
                if ((this.findPlayerById(usefullData.get(i).getPlayer_id()).getPosition()).equals("CENTER")) {
                    int tempEffic = this.calculateEffic(usefullData.get(i));
                    if (tempEffic >= maxEfficC) {
                        maxEfficC = tempEffic;
                        bestC = this.findPlayerById(usefullData.get(i).getPlayer_id());
                        this.findPlayerById(usefullData.get(i).getPlayer_id()).setEfficiency(tempEffic);
                    }
                }
            }
        }
    }

    public ArrayList<Match> removePreviousWeekMatches(ArrayList<Match> myMatches){
        int currentWeek = 0;
        ArrayList<Match> currentWeekMatches = new ArrayList<>();
        for(int i=0;i<myMatches.size();i++){
            if(myMatches.get(i).getDate()>currentWeek){
                currentWeek = myMatches.get(i).getDate();
            }
        }
        for(int i=0;i<myMatches.size();i++) {
            if(myMatches.get(i).getDate()==currentWeek){
                currentWeekMatches.add(myMatches.get(i));
            }
        }
        return currentWeekMatches;

    }

    public boolean hasMatchInCompleted(int match_id){
        for(int i=0;i<allLatestMatches.size();i++){
            if(allLatestMatches.get(i).getId()==match_id){
                return true;
            }
        }
        return false;
    }
    public Player findPlayerById(int id){
        for(int i=0;i<allPlayers.size();i++){
            if(allPlayers.get(i).getId()==id){
                return allPlayers.get(i);
            }
        }
        return null;
    }

    public Player getBestPG() {
        return bestPG;
    }

    public Player getBestSG() {
        return bestSG;
    }

    public Player getBestSF() {
        return bestSF;
    }

    public Player getBestPF() {
        return bestPF;
    }

    public Player getBestC() {
        return bestC;
    }
}
