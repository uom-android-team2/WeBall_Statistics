package uom.team2.weball_statistics.Service;

import android.os.StrictMode;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.CallbackListener;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.utils.JSONHandler;

/*
 * @author Leonard Pepa ics20033
 */
public class PlayerService {


    private ArrayList<Player> listOfplayers;
    private Player player;

    public PlayerService() {
        listOfplayers = new ArrayList<>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    public void findPlayerById(int id, CallbackListener<Player> callbackListener) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();
                    MediaType mediaType = MediaType.parse("application/json");
                    Request request = new Request.Builder()
                            .url(Config.API_URL + Config.PLAYER + "?id=" + id)
                            .method("GET", null)
                            .addHeader("Content-Type", "application/json")
                            .build();
                    Response response = client.newCall(request).execute();

                    String data = response.body().string();
                    player = JSONHandler.deserializePlayer(data);
                    callbackListener.callback(player);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    public void findPlayerById2(int id, CallbackListener<Player> callbackListener) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();
                    MediaType mediaType = MediaType.parse("application/json");
                    Request request = new Request.Builder()
                            .url(Config.API_URL + Config.PLAYER + "?id=" + id)
                            .method("GET", null)
                            .addHeader("Content-Type", "application/json")
                            .build();
                    Response response = client.newCall(request).execute();

                    String data = response.body().string();
                    player = JSONHandler.deserializePlayer2(data);
                    callbackListener.callback(player);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    public void findAllPlayers(CallbackListener<ArrayList<Player>> callbackListener) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();
                    MediaType mediaType = MediaType.parse("application/json");
                    Request request = new Request.Builder()
                            .url(Config.API_URL + Config.PLAYER)
                            .method("GET", null)
                            .addHeader("Content-Type", "application/json")
                            .build();
                    Response response = client.newCall(request).execute();

                    String data = response.body().string();
                    listOfplayers = JSONHandler.deserializeListOfPlayers(data);
                    callbackListener.callback(listOfplayers);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }



    public void findAllPlayersByTeamName(String teamName, CallbackListener<ArrayList<Player>> callbackListener) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();
                    MediaType mediaType = MediaType.parse("application/json");
                    Request request = new Request.Builder()
                            .url(Config.API_URL + Config.PLAYER + "?team=" + teamName)
                            .method("GET", null)
                            .addHeader("Content-Type", "application/json")
                            .build();
                    Response response = client.newCall(request).execute();

                    String data = response.body().string();
                    listOfplayers = JSONHandler.deserializeListOfPlayers(data);
                    callbackListener.callback(listOfplayers);

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
