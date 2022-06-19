package uom.team2.weball_statistics.Model.Statistics;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

public class PlayerStats extends Stats {

    protected int player_id;
    protected int matches_played;
    protected float minutes;
    protected boolean hasMatchPlayed = false;
    private ArrayList<String> UniqueKeysOfPlayer = new ArrayList<String>(Arrays.asList("player_id", "matches_played","minutes")); // This arraylist holds the unique fields of the team.
    private BigDecimal bigDecimal;


    // This method is the constructor of class PlayerStats which initialize the parameters of this class.
    public PlayerStats(){
        hasMatchPlayed = true;
    }


    public PlayerStats(int successful_effort, int total_effort, int successful_freethrow, int total_freethrow, int successful_twopointer, int total_twopointer, int successful_threepointer, int total_threepointer, int steal, int rebound, int assist, int block, int foul, int turnover, int player_id, int matches_played, int minutes) {
        super( successful_effort, total_effort, successful_freethrow, total_freethrow, successful_twopointer, total_twopointer, successful_threepointer, total_threepointer, steal, rebound, assist, block, foul, turnover);
        this.player_id = player_id;
        this.matches_played = matches_played;
        this.minutes = minutes;
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
              if(hasMatchPlayed){
                  matches_played = Integer.parseInt(hashMapData.get("matches_played"));
                  hasMatchPlayed = false;
              }
              minutes = Float.parseFloat(hashMapData.get("minutes"));
              super.editJON(data);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public double calculatePointsPercentage() {
        return   matches_played > 0 ? Math.round((Double.valueOf(super.calculateTotalPoints()) / Double.valueOf(matches_played))*100.0)/100.0 : 0;
    }
    @Override
    public double calculateFreethrowPointAVG() {
        return   matches_played > 0 ?  Math.round((Double.valueOf(successful_freethrow) / Double.valueOf(matches_played))*100.0)/100.0 : 0;
    }

    @Override
    public double calculateTwoPointAVG() {
        return   matches_played > 0 ?  Math.round((Double.valueOf(successful_twopointer) / Double.valueOf(matches_played))*100.0)/100.0 : 0;
    }

    @Override
    public double calculateThreePointsAVG() {
        return   matches_played > 0 ?  Math.round((Double.valueOf(successful_threepointer) / Double.valueOf(matches_played))*100.0)/100.0 : 0;
    }

    public double calculateSteelPercentage() {
        return   matches_played > 0 ? Math.round((Double.valueOf(steal) / Double.valueOf(matches_played))*100.0)/100.0 : 0;
    }

    public double calculateReboundPercentage() {
        return   matches_played > 0 ?  Math.round((Double.valueOf(rebound) / Double.valueOf(matches_played))*100.0)/100.0 : 0;
    }

    public double calculateAssistPercentage() {

        return   matches_played > 0 ? Math.round((Double.valueOf(assist) / Double.valueOf(matches_played))*100.0)/100.0 : 0;
    }

    public double calculateBlockPercentage() {
        return   matches_played > 0 ? Math.round((Double.valueOf(block) / Double.valueOf(matches_played))*100.0)/100.0 : 0;
    }

    public double calculateFoulPercentage() {
        return   matches_played > 0 ? Math.round((Double.valueOf(foul) / Double.valueOf(matches_played))*100.0)/100.0 : 0;
    }

    public double calculateTurnoverPercentage() {
        return   matches_played > 0 ?  Math.round((Double.valueOf(turnover) / Double.valueOf(matches_played))*100.0)/100.0 : 0;
    }

    public int getMatchesPlayed(){return matches_played;}

    public float getMinutes(){return minutes;}

    public int getPlayer_id() {
        return player_id;
    }

    public void setByOneMatches(){ matches_played++;}

    public void setMatchesPlayed(int matches_played) {
        this.matches_played = matches_played;
    }

    public void setMinutes(float minutes) {
        this.minutes = minutes;
    }


}