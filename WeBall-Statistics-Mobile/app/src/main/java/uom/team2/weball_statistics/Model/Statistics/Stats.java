package uom.team2.weball_statistics.Model.Statistics;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public abstract class Stats implements editFieldsFromDB {
    
    protected String fieldGoalsPercentage;
    protected String freeThrowsPercentage;
    protected int successfulFreethrows;
    protected int totalFreethrows;
    protected String twoPointsPercentage;
    protected int successfulTwoPoints;
    protected int totalTwoPoints;
    protected String threePointsPercentage;
    protected int successfulThreePoints;
    protected int totalThreePoints;
    protected int totalSteels;
    protected int totalRebounds;
    protected int totalAssists;
    protected int totalBocks;
    protected int totalFouls;
    protected int totalTurnovers;

    // This method initialize the properties of class Stats.
    protected void initializeStats(String fTP, String twoPP, String threePP) {

        formatStats(fTP, twoPP, threePP);

    }

    private  void formatStats(String fTP, String twoPP, String threePP){
        freeThrowsPercentage = fTP;
        String[] stringsFromfTP = fTP.split("\\/");
        successfulFreethrows = Integer.parseInt(stringsFromfTP[0]);
        totalFreethrows = Integer.parseInt(stringsFromfTP[1]);

        twoPointsPercentage = twoPP;
        String[] stringsFromtwoPP = twoPP.split("\\/");
        successfulTwoPoints = Integer.parseInt(stringsFromtwoPP[0]);
        totalTwoPoints = Integer.parseInt(stringsFromtwoPP[1]);

        threePointsPercentage = threePP;
        String[] stringsFromthreePP = threePP.split("\\/");
        successfulThreePoints = Integer.parseInt(stringsFromthreePP[0]);
        totalThreePoints = Integer.parseInt(stringsFromthreePP[1]);

        calculateFielsGoalPercentage();
        calculateFreeThrowsPercentage();
        calculateTwoPointsPercentage();
        calculateThreePointsPercentage();
    }


    /* This private method calculates the field goal percentage including two and three points attempts .
       Also returns this percentage in format of string.*/
    private String calculateFielsGoalPercentage(){
        return fieldGoalsPercentage = Integer.toString(successfulTwoPoints + successfulThreePoints).concat("/").concat(Integer.toString(totalTwoPoints + totalThreePoints));
    }

    private double calculateFreeThrowsPercentage(){
       return  (successfulFreethrows * 100)/totalFreethrows;
    }

    private double calculateTwoPointsPercentage(){
        return (successfulTwoPoints * 100)/totalTwoPoints;
    }

    private double calculateThreePointsPercentage(){
        return (successfulThreePoints * 100)/totalThreePoints;
    }


    protected int getTotalSteels(){
       return totalSteels;
    }

    protected int getTotalRebounds(){
        return totalRebounds;
    }

    protected int getTotalAssists(){
       return totalAssists;
    }

    protected int getTotalBlocks(){
        return totalBocks;
    }

    protected int getTotalFouls(){
      return  totalFouls;
    }

    protected int getTotalTurnovers(){
       return totalTurnovers;
    }

    @Override
    public void editJON(String data) {
        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            HashMap<String , String> hashMapData = new HashMap<String , String>();

            while(keys.hasNext()) {
                String key = keys.next();
                String dataFromKey = json.get(key).toString();
                hashMapData.put(key, dataFromKey);

                if(keys.equals("turnover")) break;

            }

            successfulFreethrows = Integer.parseInt(hashMapData.get("successful_freethrow"));
            totalFreethrows = Integer.parseInt(hashMapData.get("total_freethrow"));
            successfulTwoPoints = Integer.parseInt(hashMapData.get("successful_twopointer"));
            totalTwoPoints = Integer.parseInt(hashMapData.get("total_freethrow"));
            successfulThreePoints = Integer.parseInt(hashMapData.get("successful_threepointer"));
            totalThreePoints = Integer.parseInt(hashMapData.get("total_threepointer"));
            totalSteels = Integer.parseInt(hashMapData.get("steel"));
            totalRebounds = Integer.parseInt(hashMapData.get("rebound"));
            totalAssists = Integer.parseInt(hashMapData.get("assist"));
            totalBocks = Integer.parseInt(hashMapData.get("block"));
            totalFouls = Integer.parseInt(hashMapData.get("foul"));
            totalTurnovers = Integer.parseInt(hashMapData.get("turnover"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
