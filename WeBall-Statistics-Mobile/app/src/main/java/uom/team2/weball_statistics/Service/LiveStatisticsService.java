package uom.team2.weball_statistics.Service;

import android.os.StrictMode;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LiveStatisticsService {

    public static LiveStatisticsService instance;

    private LiveStatisticsService() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public static LiveStatisticsService getInstance() {
        if (instance == null) {
            instance = new LiveStatisticsService();
        }
        return instance;
    }


    public <T> void updateModel(T model, String url) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();

                OkHttpClient client = new OkHttpClient().newBuilder().build();
                RequestBody body = RequestBody.create(gson.toJson(model), MediaType.parse("application/json"));
                Request request = new Request.Builder().url(url).method("PUT", body).build();
                try {
                    Response response = client.newCall(request).execute();
                    String data = response.body().string();
                    System.out.println(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }


}
