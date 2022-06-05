package uom.team2.weball_statistics.Service;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import uom.team2.weball_statistics.Model.Match;

public class DAOAction implements DAOCRUDService <Match> {
    public static DAOAction instace;
    private DatabaseReference databaseReference;

    private DAOAction() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Match.class.getSimpleName());
    }

    public static DAOAction getInstace() {
        if (instace == null) {
            instace = new DAOAction();
        }
        return instace;
    }

    @Override
    public Task<Void> insert(Match data) {
        return databaseReference.child("Actions for match with id: " + data.getId()).setValue(data);
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
        HashMap<String, Object> hashMap = (HashMap<String, Object>) data.toMap();
        databaseReference.child("Actions for match with id: " + data.getId()).updateChildren(hashMap);
    }
}
