package uom.team2.weball_statistics.Service;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import uom.team2.weball_statistics.Model.PlayerLiveStatistics;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.LivePlayerStatistics;
/*
 * @author Leonard Pepa ics20033
 */
public class DAOLivePlayerStatistics implements DAOCRUDService<LivePlayerStatistics>{

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
    public Task<Void> insert(LivePlayerStatistics data) {
        return null;
    }

    @Override
    public Task<Void> update(HashMap<String, LivePlayerStatistics> data) {
        return null;
    }

    @Override
    public Task<Void> delete(LivePlayerStatistics data) {
        return null;
    }

    @Override
    public Task<Void> get() {
        return null;
    }

    @Override
    public Task<Void> get(LivePlayerStatistics data) {
        return null;
    }

    @Override
    public Task<Void> update(LivePlayerStatistics data) {
        return null;
    }
}
