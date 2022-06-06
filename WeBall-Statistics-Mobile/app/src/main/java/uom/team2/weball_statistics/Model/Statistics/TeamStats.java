package uom.team2.weball_statistics.Model.Statistics;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import uom.team2.weball_statistics.Model.Config;

public class TeamStats extends Stats {

    protected int teamId;
    private int totalMatches;
    private int wins;
    private int loses;
    private ArrayList<String> UniqueKeysOfTeam = new ArrayList<String>(Arrays.asList("team_id", "total_matches","win", "lose")); // This arraylist holds the unique fields of the team.

    public TeamStats()  {

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
            totalMatches = Integer.parseInt(hashMapData.get("total_matches"));
            wins = Integer.parseInt(hashMapData.get("win"));
            loses = Integer.parseInt(hashMapData.get("lose"));
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

    public  int getLooses(){return loses;}
}
