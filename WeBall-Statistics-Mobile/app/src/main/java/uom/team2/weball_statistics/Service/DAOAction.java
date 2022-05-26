package uom.team2.weball_statistics.Service;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import uom.team2.weball_statistics.Model.Action;

public class DAOAction implements DAOCRUDService <Action> {
    private DatabaseReference databaseReference;

    public DAOAction() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Action.class.getSimpleName());
    }

    @Override
    public Task<Void> insert(Action action) {

        return databaseReference.push().setValue(action);
    }

    @Override
    public Task<Void> update(HashMap<String, Action> data) {
        return null;
    }

    //We will keep this
    public Task<Void> update(String key, HashMap<String, Object> hashMap) {

        return  databaseReference.child(key).updateChildren(hashMap);
    }

    @Override
    public Task<Void> delete(Action data) {
        //need to traverse the records maybe
        return null;
    }

    @Override
    public Task<Void> get() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                for(DataSnapshot ds : snapshot.getChildren()) {
                    Action action = ds.getValue(Action.class);
                    System.out.println(action.getActionType());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                System.out.println("Failed");
            }
        });

        return null;
    }

    @Override
    public Task<Void> get(Action data) {
        //retrieve based on a field of the object
        return null;
    }
}
