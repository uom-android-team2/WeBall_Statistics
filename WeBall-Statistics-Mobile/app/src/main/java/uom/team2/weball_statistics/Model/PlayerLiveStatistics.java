package uom.team2.weball_statistics.Model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;
// @TODO fix the fields
@IgnoreExtraProperties
public class PlayerLiveStatistics implements firebaseModel{
    private int matchId;
    private int playerId;
    private int successfulEffort;
    private int totalEffort;
    private int successfulFreethrow;
    private int totalFreethrow;
    private int succesfulTwopointer;
    private int succesfulThreepointer;
    private int totalThreepointer;
    private int steal;
    private int assist;
    private int block;
    private int rebound;
    private int foul;
    private int turnover;
    private double minutes;

    public PlayerLiveStatistics(int matchId, int playerId, int successfulEffort, int totalEffort, int successfulFreethrow, int totalFreethrow, int succesfulTwopointer, int succesfulThreepointer, int totalThreepointer, int steal, int assist, int block, int rebound, int foul, int turnover, double minutes) {
        this.matchId = matchId;
        this.playerId = playerId;
        this.successfulEffort = successfulEffort;
        this.totalEffort = totalEffort;
        this.successfulFreethrow = successfulFreethrow;
        this.totalFreethrow = totalFreethrow;
        this.succesfulTwopointer = succesfulTwopointer;
        this.succesfulThreepointer = succesfulThreepointer;
        this.totalThreepointer = totalThreepointer;
        this.steal = steal;
        this.assist = assist;
        this.block = block;
        this.rebound = rebound;
        this.foul = foul;
        this.turnover = turnover;
        this.minutes = minutes;
    }

    @Override
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("matchId",matchId);
        result.put("playerId",playerId);
        result.put("successfulEffort",successfulEffort);
        result.put("totalEffort",totalEffort);
        result.put("successfulFreethrow", successfulFreethrow);
        result.put("totalFreethrow",totalFreethrow);
        result.put("succesfulTwopointer", succesfulTwopointer);
        result.put("succesfulThreepointer", succesfulThreepointer);
        result.put("totalThreepointer", totalThreepointer);
        result.put("steal", steal);
        result.put("assist", assist);
        result.put("block", block);
        result.put("rebound", rebound);
        result.put("foul", foul);
        result.put("turnover", turnover);
        result.put("minutes", minutes);
        return result;
    }


    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getSuccessfulEffort() {
        return successfulEffort;
    }

    public void setSuccessfulEffort(int successfulEffort) {
        this.successfulEffort = successfulEffort;
    }

    public int getTotalEffort() {
        return totalEffort;
    }

    public void setTotalEffort(int totalEffort) {
        this.totalEffort = totalEffort;
    }

    public int getSuccessfulFreethrow() {
        return successfulFreethrow;
    }

    public void setSuccessfulFreethrow(int successfulFreethrow) {
        this.successfulFreethrow = successfulFreethrow;
    }

    public int getTotalFreethrow() {
        return totalFreethrow;
    }

    public void setTotalFreethrow(int totalFreethrow) {
        this.totalFreethrow = totalFreethrow;
    }

    public int getSuccesfulTwopointer() {
        return succesfulTwopointer;
    }

    public void setSuccesfulTwopointer(int succesfulTwopointer) {
        this.succesfulTwopointer = succesfulTwopointer;
    }

    public int getSuccesfulThreepointer() {
        return succesfulThreepointer;
    }

    public void setSuccesfulThreepointer(int succesfulThreepointer) {
        this.succesfulThreepointer = succesfulThreepointer;
    }

    public int getTotalThreepointer() {
        return totalThreepointer;
    }

    public void setTotalThreepointer(int totalThreepointer) {
        this.totalThreepointer = totalThreepointer;
    }

    public int getSteal() {
        return steal;
    }

    public void setSteal(int steal) {
        this.steal = steal;
    }

    public int getAssist() {
        return assist;
    }

    public void setAssist(int assist) {
        this.assist = assist;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getRebound() {
        return rebound;
    }

    public void setRebound(int rebound) {
        this.rebound = rebound;
    }

    public int getFoul() {
        return foul;
    }

    public void setFoul(int foul) {
        this.foul = foul;
    }

    public int getTurnover() {
        return turnover;
    }

    public void setTurnover(int turnover) {
        this.turnover = turnover;
    }

    public double getMinutes() {
        return minutes;
    }

    public void setMinutes(double minutes) {
        this.minutes = minutes;
    }

}
