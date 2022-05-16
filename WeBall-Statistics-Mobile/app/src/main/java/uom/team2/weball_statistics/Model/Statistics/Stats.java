package uom.team2.weball_statistics.Model.Statistics;


public abstract class Stats {

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

    protected int setTotalTurnovers(){
       return totalTurnovers;
    }

}
