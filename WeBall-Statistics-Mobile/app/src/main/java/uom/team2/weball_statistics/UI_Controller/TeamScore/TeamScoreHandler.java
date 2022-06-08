package uom.team2.weball_statistics.UI_Controller.TeamScore;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uom.team2.weball_statistics.Model.Coach;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.PlayerPosition;
import uom.team2.weball_statistics.Model.Team;

public class TeamScoreHandler {

    private ArrayList<Team> teams;
    private Team team;
    //private String myIP ="192.168.2.54";
    private String myIP ="";
    private String url = "http://"+myIP+"/WeBall_Statistics-Backend/API/team.php";

    public TeamScoreHandler(){
        teams = new ArrayList<>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void findTeams() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient().newBuilder().build();
                    MediaType media = MediaType.parse("application/json");
                    Request request = new Request.Builder()
                            .url(url)
                            .method("GET",null)
                            .addHeader("Content-Type","application/json")
                            .build();
                    Response response = client.newCall(request).execute();

                    String data = response.body().string();
                    System.out.println("FIND THEM" + data);
                    teams = deserializeAllTeams(data);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Team deserializeTeam(String data) throws JSONException {
        // // // // // // // // // // DESERIALIZE // // // // // // // // // //

        JSONObject jsonObject = new JSONObject(data);
        int id = jsonObject.getInt("id");
        String teamName = jsonObject.getString("name");
        String teamCity = jsonObject.getString("city");
        String badgePath = jsonObject.getString("badge");

        return new Team(id, teamName, teamCity, badgePath);
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public ArrayList<Team> deserializeAllTeams(String data) throws JSONException
    {
        ArrayList<Team> teams = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(data);

        for (int i=0; i < jsonArray.length(); i++)
        {
            teams.add(deserializeTeam(jsonArray.getJSONObject(i).toString()));
        }

        return teams;
    }
}