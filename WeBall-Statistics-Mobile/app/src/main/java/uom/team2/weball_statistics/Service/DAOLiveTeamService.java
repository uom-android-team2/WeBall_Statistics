package uom.team2.weball_statistics.Service;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import uom.team2.weball_statistics.Model.TeamLiveStatistics;

public class DAOLiveTeamService implements DAOCRUDService<TeamLiveStatistics>{
    private DatabaseReference databaseReference;

    public DAOLiveTeamService() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(TeamLiveStatistics.class.getSimpleName());
    }

    public void setDataChangeListener() {
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

    @Override
    public Task<Void> insert(TeamLiveStatistics data) {
        return databaseReference.push().setValue(data);
    }

    @Override
    public Task<Void> update(HashMap<String, TeamLiveStatistics> data) {
        return null;
    }

    @Override
    public Task<Void> delete(TeamLiveStatistics data) {
        return null;
    }

    @Override
    public Task<Void> get() {
        return null;
    }

    @Override
    public Task<Void> get(TeamLiveStatistics data) {
        return null;
    }
}



