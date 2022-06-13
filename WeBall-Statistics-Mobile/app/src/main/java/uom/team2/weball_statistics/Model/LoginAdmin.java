package uom.team2.weball_statistics.Model;

import android.os.StrictMode;

import java.io.IOException;

import okhttp3.*;
import uom.team2.weball_statistics.configuration.Config;


public class LoginAdmin {

    String username;
    String password;
    String API_CHECK_CREDENTIALS = "checkCredentials.php";

    public LoginAdmin(String u, String p) {
        username = u;
        password = p;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public boolean isAdminInDB() throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "username=\"" + username + "\"&password=" + password);
        Request request = new Request.Builder()
                .url(Config.API_URL + API_CHECK_CREDENTIALS)
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = client.newCall(request).execute();

       return Boolean.parseBoolean(response.body().string());
    }

}
