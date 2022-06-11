package uom.team2.weball_statistics.UI_Controller.Completed_Match_Stats;

import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.Model.TeamLiveStatistics;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.databinding.CompletedMatchHeaderLayoutBinding;
import uom.team2.weball_statistics.utils.JSONHandler;

public class CompletedMatchStatsUIController {

    public static CompletedMatchStatsUIController instance = new CompletedMatchStatsUIController();

    //Implement singleton pattern
    public static CompletedMatchStatsUIController getInstance() {
        if (instance == null) {
            instance = new CompletedMatchStatsUIController();
        }
        return instance;
    }

    public void fillMatchHeaderInformation(CompletedMatchStats completedMatchStats, Match myMatch, Team homeTeam, Team awayTeam) throws IOException, JSONException {
        CompletedMatchHeaderLayoutBinding completedMatchHeaderLayoutBinding = completedMatchStats.getBinding().includeMatchHeader;

        ImageView imageViewHome = completedMatchHeaderLayoutBinding.team1Logo;
        ImageView imageViewAway = completedMatchHeaderLayoutBinding.team2Logo;
        TextView textViewScore = completedMatchHeaderLayoutBinding.scoreText;
        TextView textViewHomeTeamName = completedMatchHeaderLayoutBinding.team1Name;
        TextView textViewHomeTeamAway = completedMatchHeaderLayoutBinding.team2Name;
        TextView textViewMatchDate = completedMatchHeaderLayoutBinding.matchStartDate;

        //Fetching score statistics for the home team
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(Config.API_URL+"teamLiveStatistics.php?team_id="+homeTeam.getId()+"&match_id="+myMatch.getId())
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        TeamLiveStatistics homeTeamStats = JSONHandler.deserializeTeamLiveStatistics(response.body().string());
        int homeTeamScore = homeTeamStats.getSuccessful_freethrow()*1 + homeTeamStats.getSuccessful_twopointer()*2+ homeTeamStats.getSuccessful_threepointer()*3;

        //Fetching score statistics for the away team
        OkHttpClient client2 = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType2 = MediaType.parse("application/json");
        Request request2 = new Request.Builder()
                .url(Config.API_URL+"teamLiveStatistics.php?team_id="+awayTeam.getId()+"&match_id="+myMatch.getId())
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response2 = client2.newCall(request2).execute();
        TeamLiveStatistics awayTeamStats = JSONHandler.deserializeTeamLiveStatistics(response2.body().string());
        int awayTeamScore = awayTeamStats.getSuccessful_freethrow()*1 + awayTeamStats.getSuccessful_twopointer()*2+ awayTeamStats.getSuccessful_threepointer()*3;


        textViewScore.setText(homeTeamScore+" - "+awayTeamScore);//done

        textViewHomeTeamName.setText(homeTeam.getTeamName());
        textViewHomeTeamAway.setText(awayTeam.getTeamName());
        textViewMatchDate.setText("Week: "+myMatch.getDate());
        Picasso.get()
                .load(Config.TEAM_IMAGES_RESOURCES+homeTeam.getBadgePath())
                .resize(400, 400)
                .centerCrop()
                .into(imageViewHome);
        Picasso.get()
                .load(Config.TEAM_IMAGES_RESOURCES+awayTeam.getBadgePath())
                .resize(400, 400)
                .centerCrop()
                .into(imageViewAway);


    }
}