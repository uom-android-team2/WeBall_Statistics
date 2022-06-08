package uom.team2.weball_statistics.Service;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uom.team2.weball_statistics.Model.Statistics.TeamStats;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.CallbackListener;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.IP;

/*
 * @author Aravella Lousta ics20032
 */
public class TeamChampioshipStatsService {

    private TeamStats teamStats;
    private ArrayList<TeamStats> listOfTeamStats;

    public TeamChampioshipStatsService(){
        listOfTeamStats = new ArrayList<>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void getAllTeamStatistics(CallbackListener<ArrayList<TeamStats>> callbackListener) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();
                    MediaType mediaType = MediaType.parse("application/json");
                    Request request = new Request.Builder()
                            .url("http://"+ IP.IP +"/WeBall_Statistics-Backend/API/teamStatisticsCompleted.php")
                            .method("GET", null)
                            .addHeader("Content-Type", "application/json")
                            .build();
                    Response response = client.newCall(request).execute();

                    String data = response.body().string();
                    JSONArray jsonArray = new JSONArray(data);

                    for (int i=0; i<jsonArray.length(); i++){
                        teamStats = new TeamStats();
                        teamStats.editJON(jsonArray.getJSONObject(i).toString());
                        listOfTeamStats.add(teamStats);
                    }
                    callbackListener.callback(listOfTeamStats);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }


}
