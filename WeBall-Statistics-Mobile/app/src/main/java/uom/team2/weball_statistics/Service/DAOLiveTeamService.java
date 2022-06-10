package uom.team2.weball_statistics.Service;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import uom.team2.weball_statistics.Model.TeamLiveStatistics;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.LiveGameStatistics;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.LivePlayerStatistics;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.LiveStatisticsEnum;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.UIHandler;
import uom.team2.weball_statistics.databinding.MatchHeaderLayoutBinding;

/*
 * @author Leonard Pepa ics20033
 */
public class DAOLiveTeamService implements DAOCRUDService<TeamLiveStatistics> {
    public static DAOLiveTeamService instance;
    private final DatabaseReference databaseReference;

    private DAOLiveTeamService() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(TeamLiveStatistics.class.getSimpleName());
    }

    public static DAOLiveTeamService getInstance() {
        if (instance == null) {
            instance = new DAOLiveTeamService();
        }
        return instance;
    }

    public void updateClock(int matchId, String value) {
        databaseReference.child("match_id: " + matchId).child("clock").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("clock", value);
                    databaseReference.child("match_id: " + matchId).child("clock").updateChildren(hashMap);
                } else {
                    databaseReference.child("match_id: " + matchId).child("clock").setValue(value);
                }
            }
        });
    }

    public void clockDataListener(Fragment fragment, TextView clockText, int matchId) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.child("match_id: " + matchId).child("clock").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            HashMap<String, Object> hashMap = (HashMap<String, Object>) dataSnapshot.getValue();
                            if (fragment.getActivity() != null) {
                                fragment.requireActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        clockText.setText(hashMap.get("clock").toString());
                                    }
                                });
                            }
                        } else {
                            if (fragment.getActivity() != null) {
                                String clock = "00:00";
                                databaseReference.child("match_id: " + matchId).child("clock").setValue(clock);
                                fragment.requireActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        clockText.setText(clock);
                                    }
                                });
                            }
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setDataListenerForPlayer(LivePlayerStatistics fragment, int matchId, int teamId1) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.

                TeamLiveStatistics team1 = snapshot.child("match_id: " + matchId).child("team_id: " + teamId1).getValue(TeamLiveStatistics.class);
                if (team1 == null) {
                    return;
                }
                HashMap<String, View> mapof = fragment.getMapOfStatistics();

                for (LiveStatisticsEnum statistic : LiveStatisticsEnum.values()) {
                    if (fragment.getActivity() != null) {
                        UIHandler.updateProgressBarLayoutTeam2(fragment,
                                fragment.getMapOfStatistics(),
                                statistic,
                                LiveStatisticsEnum.getStatisticValueByName(team1, statistic),
                                LiveStatisticsEnum.getStatisticValueByName(team1, statistic)
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

    public void setListenerForPoints(Fragment fragment, MatchHeaderLayoutBinding layoutBinding, int matchId, int teamId1, int teamId2) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.


                TeamLiveStatistics team1 = snapshot.child("match_id: " + matchId).child("team_id: " + teamId1).getValue(TeamLiveStatistics.class);
                TeamLiveStatistics team2 = snapshot.child("match_id: " + matchId).child("team_id: " + teamId2).getValue(TeamLiveStatistics.class);

                if (team1 == null || team2 == null) {
                    return;
                }

                int scoreTeam1 = team1.getSuccesful_threepointer() * 3 + team1.getSuccesful_twopointer() * 2 + team1.getSuccessful_freethrow();
                int scoreTeam2 = team2.getSuccesful_threepointer() * 3 + team2.getSuccesful_twopointer() * 2 + team2.getSuccessful_freethrow();
                UIHandler.updateScore(fragment, layoutBinding, scoreTeam1, scoreTeam2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                throw new RuntimeException(error.getMessage());
            }
        });
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

                if (team1 == null || team2 == null) {
                    return;
                }

                HashMap<String, View> mapof = fragment.getMapOfStatistics();

                for (LiveStatisticsEnum statistic : LiveStatisticsEnum.values()) {

                    if (fragment.getActivity() != null && fragment.getMapOfStatistics().get(statistic.name()) != null) {
                        UIHandler.updateProgressBarLayoutTeam1(fragment,
                                fragment.getMapOfStatistics(),
                                statistic,
                                LiveStatisticsEnum.getStatisticValueByName(team1, statistic)
                                        + LiveStatisticsEnum.getStatisticValueByName(team2, statistic),
                                LiveStatisticsEnum.getStatisticValueByName(team1, statistic)
                        );

                        UIHandler.updateProgressBarLayoutTeam2(fragment,
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

    public Task<DataSnapshot> get(int matchId, int teamId) {
        return databaseReference.child("match_id: " + matchId).child("team_id: " + teamId).get();
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

    public void updateByMatchAndTeamId(int matchId, int teamId, LiveStatisticsEnum statisticsEnum) {
        databaseReference.child("match_id: " + matchId).child("team_id: " + teamId).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    TeamLiveStatistics teamLiveStatistics = dataSnapshot.getValue(TeamLiveStatistics.class);
                    LiveStatisticsEnum.updateStatistic(teamLiveStatistics, statisticsEnum);
                    update(teamLiveStatistics);
                } else {
                    TeamLiveStatistics newTeamLiveStatistics = new TeamLiveStatistics(matchId, teamId, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                    insert(newTeamLiveStatistics).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            LiveStatisticsEnum.updateStatistic(newTeamLiveStatistics, statisticsEnum);
                            update(newTeamLiveStatistics);
                        }
                    });
                }
            }
        });
    }

    public void initializeTable(int matchid, int teamId1, int teamId2) {
        databaseReference.child("match_id: " + matchid).child("team_id: " + teamId1).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                } else {
                    TeamLiveStatistics teamLiveStatistics = new TeamLiveStatistics(matchid, teamId1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                    insert(teamLiveStatistics);
                }
            }
        });
        databaseReference.child("match_id: " + matchid).child("team_id: " + teamId2).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                } else {
                    TeamLiveStatistics teamLiveStatistics = new TeamLiveStatistics(matchid, teamId2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                    insert(teamLiveStatistics);
                }
            }
        });
    }


}
