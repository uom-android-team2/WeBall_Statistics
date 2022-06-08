package uom.team2.weball_statistics.Model.Statistics;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import uom.team2.weball_statistics.configuration.Config;

public class TeamStats extends Stats {

    protected int teamId;
    private int totalMatches;
    private int wins;
    private int loses;
    protected Boolean isMatchId = false;
    private ArrayList<String> UniqueKeysOfTeam = new ArrayList<String>(Arrays.asList("team_id", "total_matches","win", "lose")); // This arraylist holds the unique fields of the team.

    public TeamStats()  {
        isMatchId = true;
    }

    public TeamStats(int successful_effort, int total_effort, int successful_freethrow, int total_freethrow, int successful_twopointer, int total_twopointer, int successful_threepointer, int total_threepointer, int steal, int rebound, int assist, int block, int foul, int turnover, int teamId, int totalMatches, int wins, int loses) {
        super(successful_effort, total_effort, successful_freethrow, total_freethrow, successful_twopointer, total_twopointer, successful_threepointer, total_threepointer, steal, rebound, assist, block, foul, turnover);
        this.teamId = teamId;
        this.totalMatches = totalMatches;
        this.wins = wins;
        this.loses = loses;
    }

    @Override
    public void editJON(String data) {


        System.out.println(data);
        try {

            JSONObject json= new JSONObject(data);
            Iterator<String> keys = json.keys();
            HashMap<String , String> hashMapData = new HashMap<String , String>();

            while(keys.hasNext()) {

                String key = keys.next();
                String dataFromKey = json.get(key).toString();
                hashMapData.put(key, dataFromKey);

                if(UniqueKeysOfTeam.contains(key)){
                    hashMapData.put(key, dataFromKey);
                }
            }

            teamId = Integer.parseInt(hashMapData.get("team_id"));
            if(!isMatchId){
                totalMatches = Integer.parseInt(hashMapData.get("total_matches"));
                wins = Integer.parseInt(hashMapData.get("win"));
                loses = Integer.parseInt(hashMapData.get("lose"));
            }


            super.editJON(data);

       } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public int getGrades(){
        return wins * Config.COEFFICIENT_WIN + loses * Config.COEFFICIENT_LOOSE;
    }

    public double calculatePointsPercentage(){
        return totalMatches > 0 ? super.calculateTotalPoints() / totalMatches : 0;
    }

    public double calculateAssistPercentage(){
        return totalMatches > 0 ? assist / totalMatches : 0;
    }

    public double calculateReboundPercentage(){
        return totalMatches > 0 ? rebound / totalMatches : 0;
    }

    public double calculateBlockPercentage(){
       return totalMatches > 0 ? block / totalMatches : 0;
    }

    public double calculateSteelPercentage(){
        return totalMatches > 0 ? steal / totalMatches : 0;

    }

    public double calculateFoulPercentage(){
      return totalMatches > 0  ? foul / totalMatches : 0;

    }

    public double calculateTurnoverPercentage(){
       return totalMatches > 0 ?  turnover / totalMatches : 0;
    }

    public int getTotalMatches(){ return totalMatches;}

    public int getWins(){return wins;}

    public  int getLoses(){return loses;}

    public int getTeamId() {
        return teamId;
    }
}
