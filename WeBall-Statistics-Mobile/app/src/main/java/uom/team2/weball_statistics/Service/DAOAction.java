package uom.team2.weball_statistics.Service;

import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
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

    public void getRealTimeActionsData(Match matchData, LiveGameProgress liveGameProgressFragment) {
        //Get data snapshot
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Action").child("Actions for match with id: " + matchData.getId()).child("Actions");
        ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (liveGameProgressFragment.isAdded() && liveGameProgressFragment.getActivity() != null) {

                            liveGameProgressFragment.getBinding().actionsLayoutContainer.removeAllViews();

                            if (dataSnapshot.getChildrenCount() <= 0 ) {
                                liveProgressUIController.noActionsMessage(liveGameProgressFragment);
                            } else {
                                for (DataSnapshot data : dataSnapshot.getChildren()) {
                                    Action action = data.getValue(Action.class);
                                    if (action.getBelongsTo() == BelongsTo.HOME) {
                                        liveProgressUIController.addActionForHomeTeam(liveGameProgressFragment, action);
                                    } else if (action.getBelongsTo() == BelongsTo.GUEST) {
                                        liveProgressUIController.addActionForGuestTeam(liveGameProgressFragment, action);
                                    } else if (action.getBelongsTo() == BelongsTo.GENERAL) {
                                        liveProgressUIController.addActionForGeneral(liveGameProgressFragment, action);
                                    }
                                }
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

    public void getRealTimeCommentsData(Match matchData, LiveGameProgress liveGameProgressFragment) {
        //Get data snapshot
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Action").child("Actions for match with id: " + matchData.getId()).child("Comments");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (liveGameProgressFragment.isAdded() && liveGameProgressFragment.getActivity() != null) {

                    liveGameProgressFragment.getBinding().actionsLayoutContainer.removeAllViews();

                    if (dataSnapshot.getChildrenCount() <= 0 ) {
                        liveProgressUIController.noActionsMessage(liveGameProgressFragment);
                    } else {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Action action = data.getValue(Action.class);
                            if (action.getBelongsTo() == BelongsTo.HOME) {
                                liveProgressUIController.addActionForHomeTeam(liveGameProgressFragment, action);
                            } else if (action.getBelongsTo() == BelongsTo.GUEST) {
                                liveProgressUIController.addActionForGuestTeam(liveGameProgressFragment, action);
                            } else if (action.getBelongsTo() == BelongsTo.GENERAL) {
                                liveProgressUIController.addActionForGeneral(liveGameProgressFragment, action);
                            }
                        }
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

    public Task<Void> insertAction(Action actionData, Match matchData) {

        FirebaseDatabase.getInstance().getReference().child("Action").child("Actions for match with id: " + matchData.getId()).child("Actions").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                int num = Math.toIntExact(dataSnapshot.getChildrenCount()); //The existing number of actions for a match
                matchData.setActionsCount(num);

                databaseReference.child("Actions for match with id: " + matchData.getId()).child("Actions").child(matchData.getActionsCount() + "").setValue(actionData);
            }
        });

        return null;
    }

    public Task<Void> insertCommentDesc(Action actionData, Match matchData) {

        FirebaseDatabase.getInstance().getReference().child("Action").child("Actions for match with id: " + matchData.getId()).child("Comments").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                int num = Math.toIntExact(dataSnapshot.getChildrenCount()); //The existing number of actions for a match
                matchData.setActionsCount(num);

                databaseReference.child("Actions for match with id: " + matchData.getId()).child("Comments").child(matchData.getActionsCount() + "").setValue(actionData);
            }
        });

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
