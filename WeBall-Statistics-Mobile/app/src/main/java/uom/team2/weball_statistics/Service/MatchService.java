package uom.team2.weball_statistics.Service;

import android.os.StrictMode;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.*;
import uom.team2.weball_statistics.Model.Config;
import uom.team2.weball_statistics.Model.Match;

public class MatchService {
    private String api="match.php";

    public MatchService(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void statusUpdate(Match match) throws IOException {
        Gson gson = new Gson();

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create(gson.toJson(match), MediaType.parse("application/json"));
        Request request = new Request.Builder().url("http://192.168.1.52/WeBall_Statistics-Backend/API/" + api).method("PUT", body).build();
        Response response = client.newCall(request).execute();

    }

}
