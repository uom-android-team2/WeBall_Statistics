package uom.team2.weball_statistics.Model.Statistics;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

import uom.team2.weball_statistics.Model.Config;

public abstract class Stats implements editFieldsFromDB {
    
    protected String fieldGoalsPercentage;
    protected String freeThrowsPercentage;
    protected int succesful_effort;
    protected int total_effort;
    protected int successful_freethrow;
    protected int total_freethrow;
    protected String twoPointsPercentage;
    protected int succesful_twopointer;
    protected int total_twopointer;
    protected String threePointsPercentage;
    protected int succesful_threepointer;
    protected int total_threepointer;
    protected int steal;
    protected int rebound;
    protected int assist;
    protected int block;
    protected int foul;
    protected int turnover;

    // This method initialize the properties of class Stats.
    protected void initializeStats(String fTP, String twoPP, String threePP) {

        formatStats(fTP, twoPP, threePP);

    }


    private  void formatStats(String fTP, String twoPP, String threePP){

    }


    /* This private method calculates the field goal percentage including two and three points attempts .
       Also returns this percentage in format of string.*/
    public String calculateFieldGoalPercentageInString(){
        return fieldGoalsPercentage = Integer.toString(succesful_effort).concat("/").concat(Integer.toString(total_effort));
    }

    public String calculateTwoPointPercentageToString(){
        return twoPointsPercentage = Integer.toString(successful_freethrow).concat("/").concat(Integer.toString(total_freethrow));
    }

    public String calculateFreeThrowPercentageToString(){
        return freeThrowsPercentage = Integer.toString(successful_freethrow).concat("/").concat(Integer.toString(total_freethrow));
    }

    private double calculateFreeThrowsPercentageToNumber(){
       return  (successful_freethrow * 100)/total_freethrow;
    }

    private double calculateTwoPointsPercentageToNumber(){
        return (succesful_twopointer * 100)/total_twopointer;
    }

    private double calculateThreePointsPercentageToNumber(){
        return (succesful_threepointer * 100)/total_threepointer;
    }

    protected  int calculateTotalPoints(){
        return succesful_twopointer * Config.COEFFICIENT_TWO_POINT + successful_freethrow * Config.COEFFICIENT_ONE_POINT + succesful_threepointer * Config.COEFFICIENT_THREE_POINT;
    }

    public String getFieldGoalPercentage(){
        return fieldGoalsPercentage;
    }

    public int getSuccessfulFreeThrow(){
        return successful_freethrow;
    }

    public int getSuccessFulTwoPointer(){
        return succesful_twopointer;
    }

    public int getSuccessFulThreePointer(){
        return succesful_threepointer;
    }

    public int getTotalSteels(){
       return steal;
    }

    public int getTotalRebounds(){
        return rebound;
    }

    public int getTotalAssists(){
       return assist;
    }

    public int getTotalBlocks(){
        return block;
    }

    public int getTotalFouls(){
      return  foul;
    }

    public int getTotalTurnovers(){
        return turnover;
    }

    protected void setTotalSteels(){steal++; }

    protected void setTotalRebounds(){ rebound++;}

    protected void setTotalAssists(){ assist++;}

    public void setTotalBlock(){ block++; }

    public void setTotalFouls(){ foul++;}

    protected void setTotalTurnovers(){ turnover++;}



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
                 System.out.println(key + " " + dataFromKey);
                if(keys.equals("turnover")) break;

            }



            succesful_effort = Integer.parseInt(hashMapData.get("successful_effort"));
            total_effort = Integer.parseInt(hashMapData.get("total_effort"));
            successful_freethrow = Integer.parseInt(hashMapData.get("successful_freethrow"));
            total_freethrow = Integer.parseInt(hashMapData.get("total_freethrow"));
            succesful_twopointer = Integer.parseInt(hashMapData.get("successful_twopointer"));
            total_twopointer = Integer.parseInt(hashMapData.get("total_twopointer"));
            succesful_threepointer = Integer.parseInt(hashMapData.get("successful_threepointer"));
            total_threepointer = Integer.parseInt(hashMapData.get("total_threepointer"));
            steal = Integer.parseInt(hashMapData.get("steal"));
            rebound = Integer.parseInt(hashMapData.get("rebound"));
            assist = Integer.parseInt(hashMapData.get("assist"));
            block = Integer.parseInt(hashMapData.get("block"));
            foul = Integer.parseInt(hashMapData.get("foul"));
            turnover = Integer.parseInt(hashMapData.get("turnover"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
