package uom.team2.weball_statistics.Service;


import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import uom.team2.weball_statistics.Model.TeamLiveStatistics;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.LiveGameStatistics;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.LiveStatisticsEnum;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.LiveStatisticsUIHandler;

/*
 * @author Leonard Pepa ics20033
 */
public class DAOLiveTeamService implements DAOCRUDService<TeamLiveStatistics> {
    public static DAOLiveTeamService instace;
    private final DatabaseReference databaseReference;

    private DAOLiveTeamService() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(TeamLiveStatistics.class.getSimpleName());
    }

    public static DAOLiveTeamService getInstace() {
        if (instace == null) {
            instace = new DAOLiveTeamService();
        }
        return instace;
    }

    public void setDataChangeListener(LiveGameStatistics fragment, int matchId, int teamId1, int teamId2) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.


                TeamLiveStatistics team1 = snapshot.child("match_id: " + matchId).child("team_id: " + teamId1).getValue(TeamLiveStatistics.class);
                TeamLiveStatistics team2 = snapshot.child("match_id: " + matchId).child("team_id: " + teamId2).getValue(TeamLiveStatistics.class);

                HashMap<String, View> mapof = fragment.getMapOfStatistics();

                for (LiveStatisticsEnum statistic : LiveStatisticsEnum.values()) {
                    if(fragment.getMapOfStatistics().get(statistic.name()) != null){
                        LiveStatisticsUIHandler.updateProgressBarLayoutTeam1(fragment,
                                fragment.getMapOfStatistics(),
                                statistic,
                                LiveStatisticsEnum.getStatisticValueByName(team1, statistic)
                                        + LiveStatisticsEnum.getStatisticValueByName(team2, statistic),
                                LiveStatisticsEnum.getStatisticValueByName(team1, statistic)
                        );

                        LiveStatisticsUIHandler.updateProgressBarLayoutTeam2(fragment,
                                fragment.getMapOfStatistics(),
                                statistic,
                                LiveStatisticsEnum.getStatisticValueByName(team1, statistic)
                                        + LiveStatisticsEnum.getStatisticValueByName(team2, statistic),
                                LiveStatisticsEnum.getStatisticValueByName(team2, statistic)
                        );

                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                throw new RuntimeException(error.getMessage());
            }
        });
    }

    @Override
    public Task<Void> insert(TeamLiveStatistics data) {
        return databaseReference.child("match_id: " + data.getMatch_id()).child("team_id: " + data.getTeam_id()).setValue(data);
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
    public TeamLiveStatistics get() {
        return null;
    }

    @Override
    public TeamLiveStatistics get(TeamLiveStatistics data) {
        return null;
    }

    @Override
    public void update(TeamLiveStatistics data) {
        HashMap<String, Object> h = (HashMap<String, Object>) data.toMap();
        databaseReference.child("match_id: " + data.getMatch_id()).child("team_id: " + data.getTeam_id()).updateChildren(h);
    }
}



