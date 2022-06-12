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
                    System.out.println("PLAYERS STATS SIZE = " + listOfPlayerStats.size());

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

//   // public ArrayList<Player> getPlayers() {
//        return players;
//    }

//    public ArrayList<PlayerStats> getAllPlayersStats() {
//        return listOfPlayerStats;
//    }

//    public PlayerPosition getPosition(String position)
//    {
//        PlayerPosition playerPosition = PlayerPosition.CENTER;
//        String tempPosition = position.replace(" ", "_");
//
//        if (tempPosition.equalsIgnoreCase(PlayerPosition.CENTER.name())) {
//            playerPosition = PlayerPosition.CENTER;
//        }
//        if (tempPosition.equalsIgnoreCase(PlayerPosition.POINT_GUARD.name())) {
//            playerPosition = PlayerPosition.POINT_GUARD;
//        }
//        if (tempPosition.equalsIgnoreCase(PlayerPosition.POWER_FORWARD.name())) {
//            playerPosition = PlayerPosition.POWER_FORWARD;
//        }
//        if (tempPosition.equalsIgnoreCase(PlayerPosition.SHOOTING_GUARD.name())) {
//            playerPosition = PlayerPosition.SHOOTING_GUARD;
//        }
//        if (tempPosition.equalsIgnoreCase(PlayerPosition.POWER_FORWARD.name())) {
//            playerPosition = PlayerPosition.POWER_FORWARD;
//        }
//        if (tempPosition.equalsIgnoreCase(PlayerPosition.SMALL_FORWARD.name())) {
//            playerPosition = PlayerPosition.SMALL_FORWARD;
//        }
//
//        return playerPosition;
//    }

    //    public void deserializePlayers() throws InterruptedException {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    OkHttpClient client = new OkHttpClient().newBuilder().build();
//                    MediaType media = MediaType.parse("application/json");
//                    Request request = new Request.Builder()
//                            .url(Config.API_URL + Config.PLAYER)
//                            .method("GET",null)
//                            .addHeader("Content-Type","application/json")
//                            .build();
//
//                    Response response = client.newCall(request).execute();
//                    String data = response.body().string();
//
//                    players = getAllPlayers(data);
//
//                    System.out.println("FIND THEM11111 =  " + players);
//                } catch (IOException | JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.start();
//        thread.join();
//    }
//
//    public Player getPlayer(String data) throws JSONException {
//        // // // // // // // // // // DESERIALIZE // // // // // // // // // //
//        JSONObject jsonObject = new JSONObject(data);
//        int id = jsonObject.getInt("id");
//        String name = jsonObject.getString("name");
//        String surname = jsonObject.getString("surname");
//        int number = jsonObject.getInt("number");
//        String position = jsonObject.getString("position");
//        String team = jsonObject.getString("team");
//        String photo = jsonObject.getString("photo");
//
//        /// KANE TYPOY TEAM TO ΤΕΑΜ
//        return new Player(id, name, surname, number, getPosition(position), team, photo);
//    }
//
//    public ArrayList<Player> getAllPlayers(String data) throws JSONException
//    {
//        ArrayList<Player> players = new ArrayList<>();
//        JSONArray jsonArray = new JSONArray(data);
//
//        for (int i=0; i < jsonArray.length(); i++)
//        {
//            players.add(getPlayer(jsonArray.getJSONObject(i).toString()));
//        }
//
//        return players;
//    }


}
