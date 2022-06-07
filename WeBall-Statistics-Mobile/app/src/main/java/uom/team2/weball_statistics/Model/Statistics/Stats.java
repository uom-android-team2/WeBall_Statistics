package uom.team2.weball_statistics.Model.Statistics;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

import uom.team2.weball_statistics.Model.Config;

public abstract class Stats implements editFieldsFromDB {
    
    protected String fieldGoalsPercentage;
    protected String freeThrowsPercentage;
    protected int successful_effort;
    protected int total_effort;
    protected int successful_freethrow;
    protected int total_freethrow;
    protected String twoPointsPercentage;
    protected int successful_twopointer;
    protected int total_twopointer;
    protected String threePointsPercentage;
    protected int successful_threepointer;
    protected int total_threepointer;
    protected int steal;
    protected int rebound;
    protected int assist;
    protected int block;
    protected int foul;
    protected int turnover;

    public Stats(int successful_effort, int total_effort, int successful_freethrow, int total_freethrow, int successful_twopointer, int total_twopointer, int successful_threepointer, int total_threepointer, int steal, int rebound, int assist, int block, int foul, int turnover) {
        this.successful_effort = successful_effort;
        this.total_effort = total_effort;
        this.successful_freethrow = successful_freethrow;
        this.total_freethrow = total_freethrow;
        this.successful_twopointer = successful_twopointer;
        this.total_twopointer = total_twopointer;
        this.successful_threepointer = successful_threepointer;
        this.total_threepointer = total_threepointer;
        this.steal = steal;
        this.rebound = rebound;
        this.assist = assist;
        this.block = block;
        this.foul = foul;
        this.turnover = turnover;
    }

    public Stats() {
    }

    private void formatStats(){
        calculateFieldGoalPercentageToString();
        calculateFreeThrowPercentageToString();
        calculateTwoPointPercentageToString();
        calculateThreePointPercentageToString();
    }


    /* This private method calculates the field goal percentage including two and three points attempts .
       Also returns this percentage in format of string.*/
    private void calculateFieldGoalPercentageToString(){
         fieldGoalsPercentage = Integer.toString(successful_effort).concat("/").concat(Integer.toString(total_effort));
    }

    private void calculateTwoPointPercentageToString(){
         twoPointsPercentage = Integer.toString(successful_twopointer).concat("/").concat(Integer.toString(total_twopointer));
    }

    private void calculateFreeThrowPercentageToString(){
         freeThrowsPercentage = Integer.toString(successful_freethrow).concat("/").concat(Integer.toString(total_freethrow));
    }

    public void calculateThreePointPercentageToString(){
         threePointsPercentage = Integer.toString(successful_threepointer).concat("/").concat(Integer.toString(total_threepointer));
    }

    protected double calculateFreeThrowsPercentageToNumber(){
       return total_freethrow > 0 ? (successful_freethrow * 100)/total_freethrow : 0;
    }

    public double calculateFieldGoalPercentageToNumber(){
        return total_effort > 0 ? (successful_effort * 100)/total_effort : 0;
    }

    public double calculateTwoPointsPercentageToNumber(){
        return total_twopointer > 0 ? (successful_twopointer * 100)/total_twopointer : 0;
    }

    public double calculateThreePointsPercentageToNumber(){
        return total_threepointer > 0 ? (successful_threepointer * 100)/total_threepointer : 0;
    }

    public int calculateTotalPoints(){
        return successful_twopointer * Config.COEFFICIENT_TWO_POINT + successful_freethrow * Config.COEFFICIENT_ONE_POINT + successful_threepointer * Config.COEFFICIENT_THREE_POINT;
    }

    public abstract double calculatePointsPercentage();

    public String getFieldGoalPercentage(){
        return fieldGoalsPercentage;
    }

    public String getFreeThrowPercentage(){
        return freeThrowsPercentage;
    }

    public int getSuccessfulFreeThrow(){
        return successful_freethrow;
    }

    public int getTotalFreeThrow(){
        return successful_freethrow;
    }

    public int getSuccessfulEffort(){
        return successful_effort;
    }

    public int getTotalEffort(){
        return total_effort;
    }

    public String getTwoPointsPercentage(){
        return twoPointsPercentage;
    }

    public int getTotalTwoPointer(){
        return total_twopointer;
    }

    public int getSuccessFulTwoPointer(){
        return successful_twopointer;
    }

    public String getThreePointsPercentage(){
        return threePointsPercentage;
    }

    public int getSuccessFulThreePointer(){
        return successful_threepointer;
    }

    public int getTotalThreePointer(){
        return total_threepointer;
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

    public void setTotalEffort(){total_effort++; }

    public void setSuccessfulEffort(){successful_effort++; }

    public void setTotalFreeThrow(){total_freethrow++; }

    public void setSuccessfulFreeThrow(){successful_freethrow++; }

    public void setTotalTwoPointer(){total_twopointer++; }

    public void setSuccessfulTwoPointer(){successful_twopointer++; }

    public void setTotalThreePointer(){total_threepointer++; }

    public void setSuccessfulThreePointer(){successful_threepointer++; }

    public void setTotalSteels(){steal++; }

    public void setTotalRebounds(){ rebound++;}

    public void setTotalAssists(){ assist++;}

    public void setTotalBlock(){ block++; }

    public void setTotalFouls(){ foul++;}

    public void setTotalTurnovers(){ turnover++;}



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
                hashMapData.put(key, dataFromKey);

                if(keys.equals("turnover")) break;

            }

            successful_effort = Integer.parseInt(hashMapData.get("successful_effort"));
            total_effort = Integer.parseInt(hashMapData.get("total_effort"));
            successful_freethrow = Integer.parseInt(hashMapData.get("successful_freethrow"));
            total_freethrow = Integer.parseInt(hashMapData.get("total_freethrow"));
            successful_twopointer = Integer.parseInt(hashMapData.get("successful_twopointer"));
            total_twopointer = Integer.parseInt(hashMapData.get("total_twopointer"));
            successful_threepointer = Integer.parseInt(hashMapData.get("successful_threepointer"));
            total_threepointer = Integer.parseInt(hashMapData.get("total_threepointer"));
            steal = Integer.parseInt(hashMapData.get("steal"));
            rebound = Integer.parseInt(hashMapData.get("rebound"));
            assist = Integer.parseInt(hashMapData.get("assist"));
            block = Integer.parseInt(hashMapData.get("block"));
            foul = Integer.parseInt(hashMapData.get("foul"));
            turnover = Integer.parseInt(hashMapData.get("turnover"));

            formatStats();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setFieldGoalsPercentage(String fieldGoalsPercentage) {
        this.fieldGoalsPercentage = fieldGoalsPercentage;
    }

    public void setFreeThrowsPercentage(String freeThrowsPercentage) {
        this.freeThrowsPercentage = freeThrowsPercentage;
    }

    public void setSuccessful_effort(int successful_effort) {
        this.successful_effort = successful_effort;
    }

    public void setTotal_effort(int total_effort) {
        this.total_effort = total_effort;
    }

    public void setSuccessful_freethrow(int successful_freethrow) {
        this.successful_freethrow = successful_freethrow;
    }

    public void setTotal_freethrow(int total_freethrow) {
        this.total_freethrow = total_freethrow;
    }

    public void setTwoPointsPercentage(String twoPointsPercentage) {
        this.twoPointsPercentage = twoPointsPercentage;
    }

    public void setSuccessful_twopointer(int successful_twopointer) {
        this.successful_twopointer = successful_twopointer;
    }

    public void setTotal_twopointer(int total_twopointer) {
        this.total_twopointer = total_twopointer;
    }

    public void setThreePointsPercentage(String threePointsPercentage) {
        this.threePointsPercentage = threePointsPercentage;
    }

    public void setSuccessful_threepointer(int successful_threepointer) {
        this.successful_threepointer = successful_threepointer;
    }

    public void setTotal_threepointer(int total_threepointer) {
        this.total_threepointer = total_threepointer;
    }

    public void setSteal(int steal) {
        this.steal = steal;
    }

    public void setRebound(int rebound) {
        this.rebound = rebound;
    }

    public void setAssist(int assist) {
        this.assist = assist;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public void setFoul(int foul) {
        this.foul = foul;
    }

    public void setTurnover(int turnover) {
        this.turnover = turnover;
    }

}
