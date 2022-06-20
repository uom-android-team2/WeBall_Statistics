package uom.team2.weball_statistics.Model.Statistics;


import android.os.*;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.*;
import uom.team2.weball_statistics.configuration.Config;

public class DBDataRecovery {

    public DBDataRecovery() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public Stats readData(String api, String id) throws Exception {
        String idName;
        idName = api.equals(Config.API_PLAYER_STATISTICS_COMPLETED) || api.equals(Config.API_PLAYER_STATS_LIVE) ?  Config.PLAYER_ID : Config.TEAM_ID;
        String query = "";
        query = !id.equals("") ? "?" + idName + "=" + id : query;

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(Config.API_URL + api + query)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        Stats statsObject = createObjectStats(api);
        statsObject.editJON(data);

        return statsObject;
    }

    public void updateDataDB(String api, Stats object) throws IOException {
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create(gson.toJson(object), MediaType.parse("application/json"));
        Request request = new Request.Builder().url(Config.API_URL + api).method("PUT", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
    }

    private Stats createObjectStats(String api) {
        if (api.equals(Config.API_PLAYER_STATISTICS_COMPLETED)) {
            return new PlayerStats();
        } else if (api.equals(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED)) {
            return new TeamStats();
        }else if(api.equals(Config.API_PLAYER_STATS_LIVE)){
            return new PlayerStatsLive();
        }else if(api.equals(Config.API_TEAM_STATS_LIVE)){
           return new TeamStatsLive();
        }else{
            return null;
        }
    }
}