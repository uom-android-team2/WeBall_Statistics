package uom.team2.weball_statistics.UI_Controller.Completed_Match_Stats;

import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.Model.TeamLiveStatistics;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.databinding.CompletedMatchHeaderLayoutBinding;
import uom.team2.weball_statistics.databinding.CompletedMatchTeamPlayerStatsBinding;
import uom.team2.weball_statistics.utils.JSONHandler;

public class CompletedMatchStatsUIController {

    private TeamLiveStatistics homeTeamLiveStats;
    private ArrayList<Player> homeTeamPlayers = new ArrayList<>();
    private TeamLiveStatistics awayTeamLiveStats;
    private ArrayList<Player> awayTeamPlayers = new ArrayList<>();
    public static CompletedMatchStatsUIController instance = new CompletedMatchStatsUIController();

    //Implement singleton pattern
    public static CompletedMatchStatsUIController getInstance() {
        if (instance == null) {
            instance = new CompletedMatchStatsUIController();
        }
        return instance;
    }
    public void getTeamLiveStatsForBoth(Match myMatch, Team homeTeam, Team awayTeam) throws IOException, JSONException {
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
        homeTeamLiveStats = JSONHandler.deserializeTeamLiveStatistics(response.body().string());
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
        awayTeamLiveStats = JSONHandler.deserializeTeamLiveStatistics(response2.body().string());
    }

    public void getPlayersForBoth(Team homeTeam, Team awayTeam) throws IOException, JSONException {
        //Fetching all players playing for the home team
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(Config.API_URL+"player.php?team="+homeTeam.getTeamName())
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        homeTeamPlayers = JSONHandler.deserializeListOfPlayers2(response.body().string());

        //Fetching all players playing for the away team
        OkHttpClient client2 = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType2 = MediaType.parse("application/json");
        Request request2 = new Request.Builder()
                .url(Config.API_URL+"player.php?team="+awayTeam.getTeamName())
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response2 = client2.newCall(request2).execute();
        awayTeamPlayers = JSONHandler.deserializeListOfPlayers2(response2.body().string());
    }

    public void fillMatchHeaderInformation(CompletedMatchStats completedMatchStats, Match myMatch, Team homeTeam, Team awayTeam) throws IOException, JSONException {
        CompletedMatchHeaderLayoutBinding completedMatchHeaderLayoutBinding = completedMatchStats.getBinding().includeMatchHeader;

        ImageView imageViewHome = completedMatchHeaderLayoutBinding.team1Logo;
        ImageView imageViewAway = completedMatchHeaderLayoutBinding.team2Logo;
        TextView textViewScore = completedMatchHeaderLayoutBinding.scoreText;
        TextView textViewHomeTeamName = completedMatchHeaderLayoutBinding.team1Name;
        TextView textViewHomeTeamAway = completedMatchHeaderLayoutBinding.team2Name;
        TextView textViewMatchDate = completedMatchHeaderLayoutBinding.matchStartDate;

        this.getTeamLiveStatsForBoth(myMatch,homeTeam,awayTeam);
        int homeTeamScore = homeTeamLiveStats.getSuccessful_freethrow()*1 + homeTeamLiveStats.getSuccessful_twopointer()*2+ homeTeamLiveStats.getSuccessful_threepointer()*3;
        int awayTeamScore = awayTeamLiveStats.getSuccessful_freethrow()*1 + awayTeamLiveStats.getSuccessful_twopointer()*2+ awayTeamLiveStats.getSuccessful_threepointer()*3;


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
    public void fillCompleteMatchTeamPlayerStats(CompletedMatchStats completedMatchStats, Match myMatch, Team homeTeam, Team awayTeam) throws IOException, JSONException {

        CompletedMatchTeamPlayerStatsBinding completedMatchHeaderLayoutBindingHome = completedMatchStats.getBinding().includeHomeTeamPlayerStats;
        CompletedMatchTeamPlayerStatsBinding completedMatchHeaderLayoutBindingAway = completedMatchStats.getBinding().includeAwayTeamPlayerStats;

        this.getPlayersForBoth(homeTeam,awayTeam);

        ImageView homeLogo = completedMatchHeaderLayoutBindingHome.teamLogo;
        TextView pgNameH = completedMatchHeaderLayoutBindingHome.pgName;
        TextView pgMinsH = completedMatchHeaderLayoutBindingHome.pgMins;
        TextView pgPtsH = completedMatchHeaderLayoutBindingHome.pgPts;
        TextView pgAstH = completedMatchHeaderLayoutBindingHome.pgAst;
        TextView pgBlkH = completedMatchHeaderLayoutBindingHome.pgBlk;
        TextView pgRebsH = completedMatchHeaderLayoutBindingHome.pgRebs;
        TextView pgStlH = completedMatchHeaderLayoutBindingHome.pgStl;

        ImageView awayLogo = completedMatchHeaderLayoutBindingAway.teamLogo;
        TextView pgNameA = completedMatchHeaderLayoutBindingAway.pgName;
        TextView pgMinsA = completedMatchHeaderLayoutBindingAway.pgMins;
        TextView pgPtsA = completedMatchHeaderLayoutBindingAway.pgPts;
        TextView pgAstA = completedMatchHeaderLayoutBindingAway.pgAst;
        TextView pgBlkA = completedMatchHeaderLayoutBindingAway.pgBlk;
        TextView pgRebsA = completedMatchHeaderLayoutBindingAway.pgRebs;
        TextView pgStlA = completedMatchHeaderLayoutBindingAway.pgStl;



    }

}