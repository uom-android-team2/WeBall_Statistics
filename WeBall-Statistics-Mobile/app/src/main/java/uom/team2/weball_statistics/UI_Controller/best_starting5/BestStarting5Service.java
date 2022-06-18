package uom.team2.weball_statistics.UI_Controller.best_starting5;

import android.os.StrictMode;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.PlayerLiveStatistics;
import uom.team2.weball_statistics.Model.PlayerPosition;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.utils.JSONHandler;

public class BestStarting5Service {

    public static ArrayList<Match> getCompletedMatches() throws IOException, JSONException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ArrayList<Match> allMatches;
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(Config.API_URL+"match.php?completed=true")
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return allMatches = JSONHandler.deserializeListOfMatches(response.body().string());
    }
    public static ArrayList<PlayerLiveStatistics> getPlayerLiveStatistics()throws IOException, JSONException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ArrayList<PlayerLiveStatistics> allPlayerLiveStatistics;
        OkHttpClient client2 = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType2 = MediaType.parse("application/json");
        Request request2 = new Request.Builder()
                .url(Config.API_URL+"playerLiveStatistics.php")
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response2 = client2.newCall(request2).execute();
        return allPlayerLiveStatistics = JSONHandler.deserializeListOfPlayerLiveStatistics(response2.body().string());
    }
    public static ArrayList<Player> getPlayers()throws IOException, JSONException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ArrayList<Player> allPlayers;
        OkHttpClient client3 = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType3 = MediaType.parse("application/json");
        Request request3 = new Request.Builder()
                .url(Config.API_URL+"player.php")
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response3 = client3.newCall(request3).execute();
        return allPlayers = JSONHandler.deserializeListOfPlayers2(response3.body().string());
    }
}
