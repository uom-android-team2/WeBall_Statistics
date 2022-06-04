package uom.team2.weball_statistics.UI_Controller.R1;

import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.*;

public class OkHttpHandler {

    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    //ΑΝ χρησιμοποιησω ArrayList να αλλάξω το CarBrand
   /* ArrayList<CarBrand> populateDropDown(String url) throws Exception {
        //ΑΛΛΑΓΗ του <CarBrand>
        ArrayList<CarBrand> cbList = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        System.out.println("My Response: " + data);
        try {
            //ΑΛΛΑΓΕΣ ΣΕ ΠΟΛΛΑ
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            while(keys.hasNext()) {
                String brand = keys.next();
                String models = json.getJSONObject(brand).getString("grouped_models").toString();
                String images = json.getJSONObject(brand).getString("images").toString();
                cbList.add(new CarBrand(brand, models, images));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cbList;
    }*/

    public void logHistory(String url) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        System.out.println("My Response: " + response);
    }
}
