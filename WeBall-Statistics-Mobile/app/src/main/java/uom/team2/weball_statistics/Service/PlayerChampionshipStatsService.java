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
import uom.team2.weball_statistics.Model.Statistics.PlayerStats;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.CallbackListener;
import uom.team2.weball_statistics.configuration.Config;

/*
 * @author Evmorfia Elpida Dasyra ics20006
 *
 * retrieving players' championship statistics
 */

public class PlayerChampionshipStatsService {

    private PlayerStats playerStats;
    private ArrayList<PlayerStats> listOfPlayerStats;

    public PlayerChampionshipStatsService(){
        listOfPlayerStats = new ArrayList<>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void getAllPlayerStatistics(CallbackListener<ArrayList<PlayerStats>> callbackListener) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient().newBuilder().build();
                    MediaType media = MediaType.parse("application/json");
                    Request request = new Request.Builder()
                            .url(Config.API_URL + Config.API_PLAYER_STATISTICS_COMPLETED)
                            .method("GET",null)
                            .addHeader("Content-Type","application/json")
                            .build();

                    Response response = client.newCall(request).execute();
                    String data = response.body().string();

                    JSONArray jsonArray = new JSONArray(data);
                    for (int i=0; i<jsonArray.length(); i++)
                    {
                        playerStats = new PlayerStats();
                        playerStats.editJON(jsonArray.getJSONObject(i).toString());
                        listOfPlayerStats.add(playerStats);
                    }
                    callbackListener.callback(listOfPlayerStats);

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
