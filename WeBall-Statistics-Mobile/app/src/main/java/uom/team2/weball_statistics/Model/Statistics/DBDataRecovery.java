package uom.team2.weball_statistics.Model.Statistics;


import android.os.*;
import org.json.*;

import java.io.IOException;
import java.util.*;
import okhttp3.*;
import uom.team2.weball_statistics.Model.Config;

import java.util.ArrayList;

public class DBDataRecovery {

    public DBDataRecovery() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public ArrayList<String> readData(String api, String id) throws Exception {
        String query = "";
        query = !id.equals("") ?  "?player_id=" + id : query;
        ArrayList<String> dataar = new ArrayList<String>();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(Config.API_URL + api + query)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();


       Stats f = createObjectStats(api);


        f.editJON(data);

         return dataar;

    }

    private Stats createObjectStats(String api){
        if(api.equals(Config.API_PLAYER_STATS_LIVE)){
            return new PlayerStatsLive();
        }else if(api.equals(Config.API_PLAYER_STATS_CHAMPIONSHIP)){
            return new PlayerStats();
        }else if(api.equals(Config.API_TEAM_STATS_CHAMPIONSHIP)){
            return new TeamStats();
        }else {
            return null;
        }





    }


}
