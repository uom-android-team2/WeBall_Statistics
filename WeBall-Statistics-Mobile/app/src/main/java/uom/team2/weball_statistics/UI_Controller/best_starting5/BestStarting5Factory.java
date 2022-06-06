package uom.team2.weball_statistics.UI_Controller.best_starting5;
import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import okhttp3.*;
import uom.team2.weball_statistics.*;
import uom.team2.weball_statistics.Model.Player;

public class BestStarting5Factory {



    //Calling the api -> using player model for each -> storing in arraylist
    private ArrayList<Player> allPlayers = new ArrayList<Player>();
    //Strings need to be changed to Player
    private Player bestPG;
    private Player bestSG;
    private Player bestSF;
    private Player bestPF;
    private Player bestC;

    //Get statistics from db for all players during their last match
    //Calculate Effic for every player
    public BestStarting5Factory(){

    }

    //Needs API to access stats

    //----How to calculate Efficiency----
    /* Effic – a measure of a player's efficiency, Effic = Pts + Rebs + Ast + Stl + Blk – (TO + FG Misses + FT Misses)-Fouls */

    //Instead of individual stats I can have the Player as a Parameter and get those stats from the db with his id
    public int calculateEffic(int Pts,int Rebs,int Ast, int Stl, int Blk, int TO, int FG_Misses, int FT_Misses,int Fouls){

        int playerEffic = Pts+Rebs+Ast+Stl+Blk-TO-FG_Misses-FT_Misses-Fouls;
        return playerEffic;
    }

    public void getCompletedMatches() throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url("http://192.168.1.16/backend/API/match.php?completed=true")
                .method("GET",null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        // -------
        OkHttpClient client2 = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType2 = MediaType.parse("application/json");
        Request request2 = new Request.Builder()
                .url("http://192.168.1.16/backend/API/playerLiveStatistics.php?matchId=123")
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response2 = client2.newCall(request2).execute();
        System.out.println(response2.body().string());

        try {
            JSONArray json = new JSONArray(response.body().string());
//            Iterator<String> keys = json.keys();
            System.out.println(json);
//
//            while(keys.hasNext()) {
//
//                String key = keys.next();
//
//            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
