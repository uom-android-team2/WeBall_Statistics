package uom.team2.weball_statistics.Service;

import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import uom.team2.weball_statistics.Model.PlayerLiveStatistics;
import uom.team2.weball_statistics.Model.TeamLiveStatistics;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.LivePlayerStatistics;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.LiveStatisticsEnum;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.UIHandler;
import uom.team2.weball_statistics.configuration.Config;

/*
 * @author Leonard Pepa ics20033
 */
public class DAOLivePlayerStatistics implements DAOCRUDService<PlayerLiveStatistics> {

    public static DAOLivePlayerStatistics instance;
    private final DatabaseReference databaseReference;

    private DAOLivePlayerStatistics() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(PlayerLiveStatistics.class.getSimpleName());
    }

    public static DAOLivePlayerStatistics getInstance() {
        if (instance == null) {
            instance = new DAOLivePlayerStatistics();
        }
        return instance;
    }

    public void setDataChangeListener(LivePlayerStatistics fragment, int matchId, int teamId, int playerId) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.

                DAOLiveMatchService.getInstance().get(matchId, teamId).addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        TeamLiveStatistics teamLiveStatistics = dataSnapshot.getValue(TeamLiveStatistics.class);
                        PlayerLiveStatistics playerLiveStatistics = snapshot.child("match_id: " + matchId).child("player_id: " + playerId).getValue(PlayerLiveStatistics.class);
                        if (teamLiveStatistics == null || playerLiveStatistics == null) {
                            return;
                        }
                        HashMap<String, View> mapof = fragment.getMapOfStatistics();

                        for (LiveStatisticsEnum statistic : LiveStatisticsEnum.values()) {
                            if (fragment.getActivity() != null &&  fragment.isAdded() && mapof.get(statistic.name()) != null) {
                                UIHandler.updateProgressBarLayoutTeam1(fragment,
                                        mapof,
                                        statistic,
                                        LiveStatisticsEnum.getStatisticValueByName(teamLiveStatistics, statistic),
                                        LiveStatisticsEnum.getStatisticValueByName(playerLiveStatistics, statistic)
                                );
                            }

                        }
                    }
                });


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
    public Task<Void> insert(PlayerLiveStatistics data) {
        return databaseReference.child("match_id: " + data.getMatch_id()).child("player_id: " + data.getPlayer_id()).setValue(data);
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
        return null;
    }

    @Override
    public void update(PlayerLiveStatistics data) {
        LiveStatisticsService.getInstance().updateModel(data, Config.API_URL + Config.API_PLAYER_STATS_LIVE);
        HashMap<String, Object> h = (HashMap<String, Object>) data.toMap();
        databaseReference.child("match_id: " + data.getMatch_id()).child("player_id: " + data.getPlayer_id()).updateChildren(h);
    }

    public void initializeTable(int matchId, int playerId) {
        databaseReference.child("match_id: " + matchId).child("player_id: " + playerId).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                } else {
                    PlayerLiveStatistics playerLiveStatistics = new PlayerLiveStatistics(matchId, playerId, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                    insert(playerLiveStatistics);
                }
            }
        });
    }

    public void updateByMatchAndTeamId(int matchId, int playerId, LiveStatisticsEnum statisticsEnum) {
        databaseReference.child("match_id: " + matchId).child("player_id: " + playerId).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    PlayerLiveStatistics playerLiveStatistics = dataSnapshot.getValue(PlayerLiveStatistics.class);
                    LiveStatisticsEnum.updateStatistic(playerLiveStatistics, statisticsEnum);
                    update(playerLiveStatistics);
                } else {
                    PlayerLiveStatistics newPlayerLiveStatistics = new PlayerLiveStatistics(matchId, playerId, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                    insert(newPlayerLiveStatistics).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            LiveStatisticsEnum.updateStatistic(newPlayerLiveStatistics, statisticsEnum);
                            update(newPlayerLiveStatistics);
                        }
                    });
                }
            }
        });
    }
}
