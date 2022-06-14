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
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.CallbackListener;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.utils.JSONHandler;

public class MatchesOnMainPageService {

    private ArrayList<Match> allUpcomingMatches = new ArrayList<Match>();
    private ArrayList<Match> allCompletedMatches = new ArrayList<Match>();
    private ArrayList<Match> allLiveMatches = new ArrayList<Match>();
    private ArrayList<Match> allMatches = new ArrayList<Match>();

    public MatchesOnMainPageService() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void fetchLiveMatches(CallbackListener<ArrayList<Match>> callbackListener) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                Request request = new Request.Builder()
                        .url(Config.MATCHES_API + "?live=true")
                        .method("GET", null)
                        .addHeader("Content-Type", "application/json")
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    String data = response.body().string();
                    allLiveMatches = JSONHandler.deserializeListOfMatches(data);
                    callbackListener.callback(allLiveMatches);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void fetchCompletedMatches(CallbackListener<ArrayList<Match>> callbackListener) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                Request request = new Request.Builder()
                        .url(Config.MATCHES_API + "?completed=true")
                        .method("GET", null)
                        .addHeader("Content-Type", "application/json")
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    String data = response.body().string();
                    allCompletedMatches = JSONHandler.deserializeListOfMatches(data);
                    callbackListener.callback(allCompletedMatches);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void fetchUpcomingMatches(CallbackListener<ArrayList<Match>> callbackListener) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                Request request = new Request.Builder()
                        .url(Config.MATCHES_API + "?upcoming=true")
                        .method("GET", null)
                        .addHeader("Content-Type", "application/json")
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    String data = response.body().string();
                    allUpcomingMatches = JSONHandler.deserializeListOfMatches(data);
                    callbackListener.callback(allUpcomingMatches);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }


    public void fetchMatches(CallbackListener<MatchesOnMainPageService> matchesOnMainPageServiceCallbackListener) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                Request request = new Request.Builder()
                        .url(Config.MATCHES_API)
                        .method("GET", null)
                        .addHeader("Content-Type", "application/json")
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    allMatches = JSONHandler.deserializeListOfMatches(response.body().string());
                    sortMatches();
                    matchesOnMainPageServiceCallbackListener.callback(MatchesOnMainPageService.this);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void sortMatches() {
        for (int i = 0; i < allMatches.size(); i++) {
            if (allMatches.get(i).isCompleted() == true && allMatches.get(i).isProgress() == 0) {
                allCompletedMatches.add(allMatches.get(i));
            }
            if (allMatches.get(i).isCompleted() == false && allMatches.get(i).isProgress() == 0) {
                allUpcomingMatches.add(allMatches.get(i));
            }
            if (allMatches.get(i).isCompleted() == false && allMatches.get(i).isProgress() == 1) {
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
