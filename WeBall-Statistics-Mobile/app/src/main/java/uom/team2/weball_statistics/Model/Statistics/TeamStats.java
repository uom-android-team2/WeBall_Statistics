package uom.team2.weball_statistics.Model.Statistics;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import uom.team2.weball_statistics.configuration.Config;

public class TeamStats extends Stats {

    protected int team_id;
    private int total_matches;
    private int win;
    private int lose;
    protected Boolean isMatchId = false;
    private ArrayList<String> UniqueKeysOfTeam = new ArrayList<String>(Arrays.asList("team_id", "total_matches","win", "lose")); // This arraylist holds the unique fields of the team.
    private String team_name;
    public TeamStats()  {
        isMatchId = true;
    }

    @Override
    public double calculateFreethrowPointAVG() {
        return total_matches > 0 ? Math.round((Double.valueOf(successful_freethrow) / Double.valueOf(total_matches))*100.0)/100.0 : 0;
    }

    @Override
    public double calculateTwoPointAVG() {
        return total_matches > 0 ? Math.round((Double.valueOf(successful_twopointer) / Double.valueOf(total_matches))*100.0)/100.0 : 0;
    }

    @Override
    public double calculateThreePointsAVG() {
        return total_matches > 0 ? Math.round((Double.valueOf(successful_threepointer) / Double.valueOf(total_matches))*100.0)/100.0 : 0;
    }

    public TeamStats(int successful_effort, int total_effort, int successful_freethrow, int total_freethrow, int successful_twopointer, int total_twopointer, int successful_threepointer, int total_threepointer, int steal, int rebound, int assist, int block, int foul, int turnover, int team_id, int total_matches, int win, int lose) {
        super(successful_effort, total_effort, successful_freethrow, total_freethrow, successful_twopointer, total_twopointer, successful_threepointer, total_threepointer, steal, rebound, assist, block, foul, turnover);
        this.team_id = team_id;
        this.total_matches = total_matches;
        this.win = win;
        this.lose = lose;
    }

    @Override
    public void editJON(String data) {

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
            team_id = Integer.parseInt(hashMapData.get("team_id"));
            if(isMatchId){
                total_matches = Integer.parseInt(hashMapData.get("total_matches"));
                win = Integer.parseInt(hashMapData.get("win"));
                lose = Integer.parseInt(hashMapData.get("lose"));
            }
            super.editJON(data);
       } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public int getGrades(){
        return win * Config.COEFFICIENT_WIN + lose * Config.COEFFICIENT_LOOSE;
    }

    public double calculatePointsPercentage(){
        return total_matches > 0 ?  Math.round((Double.valueOf(super.calculateTotalPoints()) / Double.valueOf(total_matches))*100.0)/100.0 : 0;
    }

    public double calculateAssistPercentage(){
        return total_matches > 0 ? Math.round((Double.valueOf(assist) / Double.valueOf(total_matches))*100.0)/100.0 : 0;
    }

    public double calculateReboundPercentage(){
        return total_matches > 0 ? Math.round((Double.valueOf(rebound) / Double.valueOf(total_matches))*100.0)/100.0 : 0;
    }

    public double calculateBlockPercentage(){
       return total_matches > 0 ? Math.round((Double.valueOf(block) / Double.valueOf(total_matches))*100.0)/100.0 : 0;
    }

    public double calculateSteelPercentage(){
        return total_matches > 0 ? Math.round((Double.valueOf(steal) / Double.valueOf(total_matches))*100.0)/100.0 : 0;

    }

    public double calculateFoulPercentage(){
      return total_matches > 0  ? Math.round((Double.valueOf(foul) / Double.valueOf(total_matches))*100.0)/100.0 : 0;

    }

    public double calculateTurnoverPercentage(){
        return total_matches > 0 ? Math.round((Double.valueOf(turnover) / Double.valueOf(total_matches))*100.0)/100.0 : 0;
    }

    public int getTotalMatches(){ return total_matches;}

    public void setMatches(){
        total_matches++;
    }

    public void setWins(){
        win++;
    }

    public void setLoses(){
        lose++;
    }

    public int getWins(){return win;}

    public  int getLoses(){return lose;}

    public int getTeamId() {
        return team_id;
    }
}
