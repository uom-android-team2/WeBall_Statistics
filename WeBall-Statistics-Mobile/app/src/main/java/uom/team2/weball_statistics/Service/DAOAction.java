package uom.team2.weball_statistics.Service;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

import uom.team2.weball_statistics.Model.Actions.Action;

public class DAOAction implements DAOCRUDService <Action> {
    private DatabaseReference databaseReference;

    @Override
    public Task<Void> insert(Action data) {
        return null;
    }

    @Override
    public Task<Void> update(HashMap<String, Action> data) {
        return null;
    }

    @Override
    public Task<Void> delete(Action data) {
        return null;
    }

    @Override
    public Action get() {
        return null;
    }

    @Override
    public Action get(Action data) {
        return null;
    }

    @Override
    public void update(Action data) {

    }
}
