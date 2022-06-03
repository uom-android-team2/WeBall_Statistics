package uom.team2.weball_statistics.Model.Statistics;


import org.json.JSONArray;
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
            System.out.println(teamId + " " + totalMatches + " " + wins + " " + loses + " " + " " + succesful_effort + " " + total_effort + " " + successful_freethrow + " " + total_freethrow + " " + succesful_twopointer + " " + total_twopointer + " " + succesful_threepointer + " " + total_freethrow + " " + steal + " " + block + " " + rebound + " " + assist + " " + foul + " " + turnover );
       } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public int getGrades(){
        return wins * Config.COEFFICIENT_WIN + loses * Config.COEFFICIENT_LOOSE;
    }

    public double calculatePointsPercentage(){
        if(totalMatches != 0 ){
            return super.calculateTotalPoints() / totalMatches;
        }else{
            return 0;
        }
    }

    public int getTotalMatches(){ return totalMatches;}

    public int getWins(){return wins;}

    public  int getLooses(){return loses;}
}
