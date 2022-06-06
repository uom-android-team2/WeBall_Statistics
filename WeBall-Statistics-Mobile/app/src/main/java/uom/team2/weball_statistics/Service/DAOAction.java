package uom.team2.weball_statistics.Service;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Match;

public class DAOAction implements DAOCRUDService <Match> {

    private DatabaseReference databaseReference;
    public static DAOAction instance = new DAOAction();

    //Implement singleton pattern
    private DAOAction() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Action.class.getSimpleName());
    }

    public static DAOAction getInstance(){
        if(instance == null){
            instance = new DAOAction();
        }
        return instance;
    }

    @Override
    public Task<Void> insert(Match data) {
        return null;
    }

    @Override
    public Task<Void> update(HashMap<String, Match> data) {
        return null;
    }

    @Override
    public Task<Void> delete(Match data) {
        return null;
    }

    @Override
    public Match get() {
        return null;
    }

    @Override
    public Match get(Match data) {
        return null;
    }

    @Override
    public void update(Match data) {

    }
}
