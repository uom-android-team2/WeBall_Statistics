package uom.team2.weball_statistics.UI_Controller.MatchesOnMainPage.Service;

import android.os.StrictMode;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.utils.JSONHandler;

public class MatchesOnMainPageService {

    private ArrayList<Match> allMatches = new ArrayList<Match>();
    private ArrayList<Match> allCompletedMatches = new ArrayList<Match>();
    private ArrayList<Match> allUpcomingMatches = new ArrayList<Match>();
    private ArrayList<Match> allLiveMatches = new ArrayList<Match>();

    public MatchesOnMainPageService() {
        try {
            this.fetchMatches();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.sortMatches();
    }

    public void fetchMatches() throws IOException, JSONException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(Config.MATCHES_API)
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        allMatches = JSONHandler.deserializeListOfMatches(response.body().string());
    }

    public void sortMatches(){
        for(int i=0;i<allMatches.size();i++){
            if(allMatches.get(i).isCompleted() == true && allMatches.get(i).isProgress()==false){
                allCompletedMatches.add(allMatches.get(i));
            }
            if(allMatches.get(i).isCompleted() == false && allMatches.get(i).isProgress()==false){
                allUpcomingMatches.add(allMatches.get(i));
            }
            if(allMatches.get(i).isCompleted() == false && allMatches.get(i).isProgress()==true){
                allLiveMatches.add(allMatches.get(i));
            }
        }
    }

    public ArrayList<Match> getAllCompletedMatches() {
        return allCompletedMatches;
    }

    public ArrayList<Match> getAllUpcomingMatches() {
        return allUpcomingMatches;
    }

    public ArrayList<Match> getAllLiveMatches() {
        return allLiveMatches;
    }
}
