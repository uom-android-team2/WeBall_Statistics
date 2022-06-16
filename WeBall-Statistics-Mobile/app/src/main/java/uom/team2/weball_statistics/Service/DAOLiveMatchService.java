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
import uom.team2.weball_statistics.configuration.Config;

/*
 * @author Leonard Pepa ics20033
 */
public class DAOLiveMatchService implements DAOCRUDService<TeamLiveStatistics> {
    public static DAOLiveMatchService instance;
    private final DatabaseReference databaseReference;

    private DAOLiveMatchService() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(TeamLiveStatistics.class.getSimpleName());
    }

    public static DAOLiveMatchService getInstance() {
        if (instance == null) {
            instance = new DAOLiveMatchService();
        }
        return instance;
    }


    public void undo(int matchId, int teamId, LiveStatisticsEnum statisticsEnum) {
        get(matchId, teamId).addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                TeamLiveStatistics teamLiveStatistics = dataSnapshot.getValue(TeamLiveStatistics.class);
                if (teamLiveStatistics != null) {
                    LiveStatisticsEnum.undoStatistic(teamLiveStatistics, statisticsEnum);
                    update(teamLiveStatistics);
                }
            }
        });
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
                            try {
                                String clock = ((HashMap<String, String>) dataSnapshot.getValue()).get("clock");
                                if (fragment.getActivity() != null && fragment.isAdded()) {
                                    fragment.requireActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            clockText.setText(clock);
                                        }
                                    });
                                }
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        } else {
                            if (fragment.getActivity() != null && fragment.isAdded()) {
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

                TeamLiveStatistics team = snapshot.child("match_id: " + matchId).child("team_id: " + teamId1).getValue(TeamLiveStatistics.class);
                if (team == null) {
                    return;
                }
                HashMap<String, View> mapof = fragment.getMapOfStatistics();

                for (LiveStatisticsEnum statistic : LiveStatisticsEnum.values()) {
                    if (fragment.getActivity() != null && fragment.isAdded()) {
                        UIHandler.updateProgressBarLayoutTeam2(fragment,
                                mapof,
                                statistic,
                                LiveStatisticsEnum.getStatisticValueByName(team, statistic),
                                LiveStatisticsEnum.getStatisticValueByName(team, statistic)
                        );
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
            }
        });
    }

    //
    public void setListenerForPoints(Fragment fragment, TextView textView, int matchId, int teamId1, int teamId2) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.


                TeamLiveStatistics team1 = snapshot.child("match_id: " + matchId).child("team_id: " + teamId1).getValue(TeamLiveStatistics.class);
                TeamLiveStatistics team2 = snapshot.child("match_id: " + matchId).child("team_id: " + teamId2).getValue(TeamLiveStatistics.class);

                int scoreTeam1 = 0;
                int scoreTeam2 = 0;

                if (team1 != null) {
                    scoreTeam1 = team1.getSuccessful_threepointer() * 3 + team1.getSuccessful_twopointer() * 2 + team1.getSuccessful_freethrow();
                }

                if (team2 != null) {
                    scoreTeam2 = team2.getSuccessful_threepointer() * 3 + team2.getSuccessful_twopointer() * 2 + team2.getSuccessful_freethrow();
                }

                UIHandler.updateScore(fragment, textView, scoreTeam1, scoreTeam2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
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

                    if (fragment.getActivity() != null && fragment.isAdded() && fragment.getMapOfStatistics().get(statistic.name()) != null) {
                        UIHandler.updateProgressBarLayoutTeam1(fragment,
                                mapof,
                                statistic,
                                LiveStatisticsEnum.getStatisticValueByName(team1, statistic)
                                        + LiveStatisticsEnum.getStatisticValueByName(team2, statistic),
                                LiveStatisticsEnum.getStatisticValueByName(team1, statistic)
                        );

                        UIHandler.updateProgressBarLayoutTeam2(fragment,
                                mapof,
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
        LiveStatisticsService.getInstance().updateModel(data, Config.API_URL + Config.API_TEAM_STATS_LIVE);
    }

    public void updateByMatchAndTeamId(int matchId, int teamId, LiveStatisticsEnum statisticsEnum) {
        get(matchId, teamId).addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
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

    public void initializeTable(int matchId, int teamId1, int teamId2) {
        get(matchId, teamId1).addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                } else {
                    TeamLiveStatistics teamLiveStatistics = new TeamLiveStatistics(matchId, teamId1);
                    insert(teamLiveStatistics);
                }
            }
        });
        get(matchId, teamId2).addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                } else {
                    TeamLiveStatistics teamLiveStatistics = new TeamLiveStatistics(matchId, teamId2);
                    insert(teamLiveStatistics);
                }
            }
        });
    }


}
