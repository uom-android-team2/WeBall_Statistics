package uom.team2.weball_statistics.Service;

import android.os.StrictMode;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.configuration.Config;

public class MatchService {
    private final String api = "match.php";

    public MatchService() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void statusUpdate(Match match) throws IOException {
        Gson gson = new Gson();

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create(gson.toJson(match), MediaType.parse("application/json"));
        Request request = new Request.Builder().url("http://" + Config.IP + "/WeBall_Statistics-Backend/API/" + api).method("PUT", body).build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

}
