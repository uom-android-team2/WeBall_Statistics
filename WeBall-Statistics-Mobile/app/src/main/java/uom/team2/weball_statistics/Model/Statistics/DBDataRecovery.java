package uom.team2.weball_statistics.Model.Statistics;


import android.os.*;
import org.json.*;

import java.io.IOException;
import java.util.*;
import okhttp3.*;

import java.util.ArrayList;

public class DBDataRecovery {

    public DBDataRecovery() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public ArrayList<String> readData() throws Exception {
        ArrayList<String> dataar = new ArrayList<String>();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("http://192.168.56.1/WeBall_Statistics-Backend/API/player.php?id=1")
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        System.out.println(data);

//        try {
//            JSONObject json = new JSONObject(data);
//            Iterator<String> keys = json.keys();
//            while(keys.hasNext()) {
//                String brand = keys.next();
//                String models = json.get(brand).toString();
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }





        return dataar;

    }


}
