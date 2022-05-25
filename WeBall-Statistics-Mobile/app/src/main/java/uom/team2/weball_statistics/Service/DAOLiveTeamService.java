package uom.team2.weball_statistics.Service;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import uom.team2.weball_statistics.Model.TeamLiveStatistics;

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

}



