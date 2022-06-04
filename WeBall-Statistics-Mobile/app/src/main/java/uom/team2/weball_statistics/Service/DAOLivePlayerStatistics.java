package uom.team2.weball_statistics.Service;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import uom.team2.weball_statistics.Model.PlayerLiveStatistics;
import uom.team2.weball_statistics.Model.TeamLiveStatistics;

/*
 * @author Leonard Pepa ics20033
 */
public class DAOLivePlayerStatistics implements DAOCRUDService<PlayerLiveStatistics>{

    private DatabaseReference databaseReference;
    public static DAOLivePlayerStatistics instace;

    private DAOLivePlayerStatistics() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(PlayerLiveStatistics.class.getSimpleName());
    }

    public static DAOLivePlayerStatistics getInstace(){
        if(instace == null){
            instace = new DAOLivePlayerStatistics();
        }
        return instace;
    }


    @Override
    public Task<Void> insert(PlayerLiveStatistics data) {
        return databaseReference.child(String.valueOf(data.getMatch_id())).setValue(data);
    }

    @Override
    public Task<Void> update(HashMap<String, PlayerLiveStatistics> data) {
        return null;
    }

    @Override
    public Task<Void> delete(PlayerLiveStatistics data) {
        return null;
    }

    @Override
    public PlayerLiveStatistics get() {
        return null;
    }

    @Override
    public PlayerLiveStatistics get(PlayerLiveStatistics data) {
        databaseReference.child(String.valueOf(data.getMatch_id())).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                TeamLiveStatistics team = dataSnapshot.getValue(TeamLiveStatistics.class);
            }
        });
        return null;
    }

    @Override
    public void update(PlayerLiveStatistics data) {
        HashMap<String, Object> h = (HashMap<String, Object>) data.toMap();
        databaseReference.child(String.valueOf(data.getMatch_id())).updateChildren(h);
    }
}
