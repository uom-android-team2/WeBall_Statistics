package uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController.LeadersStatsController;


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
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.PlayerPosition;

public class LeadersStatsHandler {

    private ArrayList<Player> players;
    private Player player;
    private String myIP ="192.168.1.43";
    private String url = "http://"+myIP+"/backend/API/player.php";

    public LeadersStatsHandler(){
        players = new ArrayList<>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void findPlayers() {
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
                    players = deserializeAllPlayers(data);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public Player deserializePlayer(String data) throws JSONException {
        // // // // // // // // // // DESERIALIZE // // // // // // // // // //

        JSONObject jsonObject = new JSONObject(data);
        int id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String surname = jsonObject.getString("surname");
        int number = jsonObject.getInt("number");
        String position = jsonObject.getString("position");
        String team = jsonObject.getString("team");
        String photo = jsonObject.getString("photo");

        PlayerPosition playerPosition = PlayerPosition.CENTER;
        String tempPosition = position.replace(" ", "_");

        if (tempPosition.equalsIgnoreCase(PlayerPosition.CENTER.name())) {
            playerPosition = PlayerPosition.CENTER;
        }
        if (tempPosition.equalsIgnoreCase(PlayerPosition.POINT_GUARD.name())) {
            playerPosition = PlayerPosition.POINT_GUARD;
        }
        if (tempPosition.equalsIgnoreCase(PlayerPosition.POWER_FORWARD.name())) {
            playerPosition = PlayerPosition.POWER_FORWARD;
        }
        if (tempPosition.equalsIgnoreCase(PlayerPosition.SHOOTING_GUARD.name())) {
            playerPosition = PlayerPosition.SHOOTING_GUARD;
        }
        if (tempPosition.equalsIgnoreCase(PlayerPosition.POWER_FORWARD.name())) {
            playerPosition = PlayerPosition.POWER_FORWARD;
        }
        if (tempPosition.equalsIgnoreCase(PlayerPosition.SMALL_FORWARD.name())) {
            playerPosition = PlayerPosition.SMALL_FORWARD;
        }
        /// KANE TYPOY TEAM TO team
        return new Player(id, name, surname, number, playerPosition, team, photo);
    }

    public ArrayList<Player> deserializeAllPlayers(String data) throws JSONException
    {
        ArrayList<Player> players = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(data);

        for (int i=0; i < jsonArray.length(); i++)
        {
            players.add(deserializePlayer(jsonArray.getJSONObject(i).toString()));
        }

        return players;
    }
}


