package uom.team2.weball_statistics.UI_Controller.R1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import uom.team2.weball_statistics.R;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHandler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_shared_matches);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    String url = "http://192.168.1.12/WeBall//API/team.php";

                    OkHttpClient client = new OkHttpClient().newBuilder().build();
                    MediaType mediaType = MediaType.parse("application/json");
                    RequestBody body = RequestBody.create(mediaType, "");
                    Request request = new Request.Builder()
                            .url(url)
                            .method("GET", null)
                            .addHeader("Content-Type", "application/json")
                            .build();

                    Response response = client.newCall(request).execute();
                    String data = response.body().string();
                    System.out.println(data);

                    JSONObject jsonObject = new JSONObject(data);

                    int id = jsonObject.getInt("id");
                    String name = jsonObject.getString("name");
                    String city = jsonObject.getString("city");
                    String badge = jsonObject.getString("badge");

                    Team team = new Team(id, name, city, badge);






                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }


}
