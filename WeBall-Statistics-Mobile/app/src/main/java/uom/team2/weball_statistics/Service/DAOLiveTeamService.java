package uom.team2.weball_statistics.Service;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DAOLiveTeamService {
    private DatabaseReference databaseReference;

    public DAOLiveTeamService() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(TeamLiveStatistics.class.getSimpleName());
    }

    public Task<Void> insertData(TeamLiveStatistics teamStatisticsUpdated){
        return databaseReference.push().setValue(teamStatisticsUpdated);
    }


    public void dataChangeListener() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                System.out.println("Failed");
            }
        });
    }

    public static class TeamLiveStatistics {

        private int matchId;
        private int teamId;
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

        public TeamLiveStatistics(){

        }

        public TeamLiveStatistics(int matchId, int teamId, int successfulEffort, int totalEffort, int successfulFreethrow, int totalFreethrow, int succesfulTwopointer, int succesfulThreepointer, int totalThreepointer, int steal, int assist, int block, int rebound, int foul, int turnover) {
            this.matchId = matchId;
            this.teamId = teamId;
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
        }

        public int getMatchId() {
            return matchId;
        }

        public void setMatchId(int matchId) {
            this.matchId = matchId;
        }

        public int getTeamId() {
            return teamId;
        }

        public void setTeamId(int teamId) {
            this.teamId = teamId;
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
    }

}



