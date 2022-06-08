package uom.team2.weball_statistics.Service;

import android.os.StrictMode;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.CallbackListener;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.utils.JSONHandler;

/*
 * @author Leonard Pepa ics20033
 */
public class TeamService {

    private Team team;
    private ArrayList<Team> listOfTeams;

    public TeamService() {
        listOfTeams = new ArrayList<>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    public void findTeamById(int id, CallbackListener<Team> callbackListener) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();
                    MediaType mediaType = MediaType.parse("application/json");
                    Request request = new Request.Builder()
                            .url(Config.API_URL + Config.TEAM + "?id=" + id)
                            .method("GET", null)
                            .addHeader("Content-Type", "application/json")
                            .build();
                    Response response = client.newCall(request).execute();

                    String data = response.body().string();
                    team = JSONHandler.deserializeTeam(data);
                    callbackListener.callback(team);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    public void findAllTeams(CallbackListener<ArrayList<Team>> callbackListener) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();
                    MediaType mediaType = MediaType.parse("application/json");
                    Request request = new Request.Builder()
                            .url(Config.API_URL + Config.TEAM)
                            .method("GET", null)
                            .addHeader("Content-Type", "application/json")
                            .build();
                    Response response = client.newCall(request).execute();

                    String data = response.body().string();

                    listOfTeams = JSONHandler.deserializeListOfTeams(data);

                    callbackListener.callback(listOfTeams);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
