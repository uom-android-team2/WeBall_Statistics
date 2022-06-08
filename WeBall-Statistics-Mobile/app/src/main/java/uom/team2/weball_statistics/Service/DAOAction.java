package uom.team2.weball_statistics.Service;

import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import uom.team2.weball_statistics.Model.Actions.*;
import uom.team2.weball_statistics.Model.*;
import uom.team2.weball_statistics.UI_Controller.LiveController.Progress.LiveGameProgress;
import uom.team2.weball_statistics.UI_Controller.LiveController.Progress.LiveProgressUIController;

public class DAOAction implements DAOCRUDService <Action> {

    private DatabaseReference databaseReference;
    private LiveProgressUIController liveProgressUIController = LiveProgressUIController.getInstance();
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

    public void getRealTimeData(Match matchData, LiveGameProgress liveGameProgressFragment) {
        //Get data snapshot
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Action").child("Actions for match with id: " + matchData.getId());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Action action = data.getValue(Action.class);
                            if (action.getBelongsTo() == BelongsTo.HOME) {
                                liveProgressUIController.addActionForHomeTeam(liveGameProgressFragment, action);
                            } else if (action.getBelongsTo() ==BelongsTo.GUEST) {
                                liveProgressUIController.addActionForGuestTeam(liveGameProgressFragment, action);
                            } else {
                                liveProgressUIController.addActionForGeneral(liveGameProgressFragment, action);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                        System.out.println("onCancelled: Error: " + databaseError.getMessage());
                    }
                });
    }

    @Override
    public Task<Void> insert(Action data) {
        return null;
    }

    public Task<Void> insert(Action actionData, Match matchData) {
        return databaseReference.child("Actions for match with id: " + matchData.getId()).child("Action: " + actionData.getId()).setValue(actionData);
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
