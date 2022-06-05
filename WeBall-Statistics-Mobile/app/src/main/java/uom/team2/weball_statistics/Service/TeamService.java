package uom.team2.weball_statistics.Service;

import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import uom.team2.weball_statistics.MainActivity;
import uom.team2.weball_statistics.Model.Config;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.utils.JSONHandler;

/*
 * @author Leonard Pepa ics20033
 */
public class TeamService extends Thread{

    private Team team;
    private ArrayList<Team> listOfTeams;
    private boolean returnOneTeam = false;
    private int id;

    public TeamService() {
        listOfTeams = new ArrayList<>();
    }

    @Override
    public void run(){
        if(returnOneTeam){
            findTeamById(id);
        }else{
            findAllTeams();
        }
    }

    public TeamService prepareFindById(int id){
        this.id = id;
        returnOneTeam = true;
        return this;
    }

    public TeamService prepareTaskFindAll(){
        this.id = -1;
        returnOneTeam = false;
        return this;
    }

    private void findTeamById(int id) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            Request request = new Request.Builder()
                    .url("http://192.168.1.6/WeBall_Statistics-Backend/API/" + "team.php?id=" + id)
                    .method("GET", null)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();

            String data = response.body().string();
            team = JSONHandler.deserializeTeam(data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void findAllTeams(){
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            Request request = new Request.Builder()
                    .url(Config.API_URL + "team.php?")
                    .method("GET", null)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();

            String data = response.body().string();
            listOfTeams = JSONHandler.deserializeListOfTeams(data);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
