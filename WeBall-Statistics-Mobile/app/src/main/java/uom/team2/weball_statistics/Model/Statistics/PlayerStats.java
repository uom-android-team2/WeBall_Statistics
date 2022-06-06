package uom.team2.weball_statistics.Model.Statistics;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

public class PlayerStats extends Stats {

    protected int player_id;
    protected int matches_played;
    protected int minutes;
    private ArrayList<String> UniqueKeysOfPlayer = new ArrayList<String>(Arrays.asList("player_id", "matches_played","minutes")); // This arraylist holds the unique fields of the team.


    // This method is the constructor of class PlayerStats which initialize the parameters of this class.
    public PlayerStats(){

    }

    @Override
    public double calculatePointsPercentage() {
      return   matches_played > 0 ? super.calculateTotalPoints() / matches_played : 0;
    }

    public double calculateSteelPercentage() {
        return   matches_played > 0 ? steal / matches_played : 0;
    }

    public double calculateReboundPercentage() {
        return   matches_played > 0 ? rebound / matches_played : 0;
    }

    public double calculateAssistPercentage() {
        return   matches_played > 0 ? assist / matches_played : 0;
    }

    public double calculateBlockPercentage() {
        return   matches_played > 0 ? block / matches_played : 0;
    }

    public double calculateFoulPercentage() {
        return   matches_played > 0 ? foul / matches_played : 0;
    }

    public double calculateTurnoverPercentage() {
        return   matches_played > 0 ? turnover / matches_played : 0;
    }

    @Override
    public void editJON(String data) {

        System.out.println(data);
        try {
             JSONObject json = new JSONObject(data);
             Iterator<String> keys = json.keys();
             HashMap<String , String> hashMapData = new HashMap<String , String>();

              while(keys.hasNext()) {

                String key = keys.next();
                String dataFromKey = json.get(key).toString();

                if(UniqueKeysOfPlayer.contains(key)){
                    System.out.println(hashMapData);
                    hashMapData.put(key, dataFromKey);
                }

              }

              player_id = Integer.parseInt(hashMapData.get("player_id"));
              matches_played = Integer.parseInt(hashMapData.get("matches_played"));
              minutes = Integer.parseInt(hashMapData.get("minutes").substring(17,19));
              super.editJON(data);



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public int getMatchesPlayed(){return matches_played;}

    public int getMinutes(){return minutes;}
}