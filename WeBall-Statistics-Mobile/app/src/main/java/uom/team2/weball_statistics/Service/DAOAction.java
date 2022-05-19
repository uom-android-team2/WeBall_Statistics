package uom.team2.weball_statistics.Service;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import uom.team2.weball_statistics.Model.Action;

public class DAOAction {
    private DatabaseReference databaseReference;

    public DAOAction() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Action.class.getSimpleName());
    }

    public Task<Void> insert(Action action) {

        return databaseReference.push().setValue(action);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap) {
        return  databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key) {
        return databaseReference.child(key).removeValue();
    }
}
