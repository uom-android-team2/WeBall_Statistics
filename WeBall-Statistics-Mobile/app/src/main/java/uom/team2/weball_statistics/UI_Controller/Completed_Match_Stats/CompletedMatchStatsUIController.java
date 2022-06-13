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
import uom.team2.weball_statistics.Model.PlayerLiveStatistics;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.Model.TeamLiveStatistics;
import uom.team2.weball_statistics.UI_Controller.best_starting5.BestStarting5;
import uom.team2.weball_statistics.UI_Controller.best_starting5.BestStarting5Factory;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.databinding.CompletedMatchHeaderLayoutBinding;
import uom.team2.weball_statistics.databinding.CompletedMatchTeamPlayerStatsBinding;
import uom.team2.weball_statistics.utils.JSONHandler;

public class CompletedMatchStatsUIController {

    private TeamLiveStatistics homeTeamLiveStats;
    private ArrayList<Player> homeTeamPlayers = new ArrayList<>();
    private TeamLiveStatistics awayTeamLiveStats;
    private ArrayList<Player> awayTeamPlayers = new ArrayList<>();
    private ArrayList<PlayerLiveStatistics> best5StatsHome = new ArrayList<>();
    private ArrayList<PlayerLiveStatistics> best5StatsAway = new ArrayList<>();



    public static CompletedMatchStatsUIController instance = new CompletedMatchStatsUIController();

    //Implement singleton pattern
    public static CompletedMatchStatsUIController getInstance() {
        if (instance == null) {
            instance = new CompletedMatchStatsUIController();
        }
        return instance;
    }

    public void getPlayerLiveStatsForBoth(Match myMatch, Team homeTeam, Team awayTeam) throws IOException, JSONException {

        //fetching stats for all players of the hometeam for this match
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(Config.API_URL+"playerLiveStatistics.php?match_id="+myMatch.getId()+"&team_id="+homeTeam.getId())
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        ArrayList<PlayerLiveStatistics> allHomePlayerStats = JSONHandler.deserializeListOfPlayerLiveStatistics(response.body().string());

        //fetching stats for all players of the away team for this match
        OkHttpClient client2 = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType2 = MediaType.parse("application/json");
        Request request2 = new Request.Builder()
                .url(Config.API_URL+"playerLiveStatistics.php?match_id="+myMatch.getId()+"&team_id="+homeTeam.getId())
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response2 = client2.newCall(request2).execute();
        ArrayList<PlayerLiveStatistics> allAwayPlayerStats = JSONHandler.deserializeListOfPlayerLiveStatistics(response2.body().string());

        //HOME TEAM
        int maxPGH=-100;
        PlayerLiveStatistics bestPgH = null;
        int maxSGH=-100;
        PlayerLiveStatistics bestSgH = null;
        int maxSFH=-100;
        PlayerLiveStatistics bestSfH = null;
        int maxPFH=-100;
        PlayerLiveStatistics bestPfH = null;
        int maxCH=-100;
        PlayerLiveStatistics bestCH = null;

        //Sorting stats to the best 5 for the home team
        for(int i=0;i<allHomePlayerStats.size();i++){
            if(findPlayerById(homeTeamPlayers,allHomePlayerStats.get(i).getPlayer_id()).getPosition().equals("POINT_GUARD")) {
                if(BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i))>maxPGH) {
                    maxPGH=BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i));
                    bestPgH=allHomePlayerStats.get(i);
                }
            }
            else if(findPlayerById(homeTeamPlayers,allHomePlayerStats.get(i).getPlayer_id()).getPosition().equals("SHOOTING_GUARD")) {
                if(BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i))>maxSGH) {
                    maxSGH=BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i));
                    bestSgH=allHomePlayerStats.get(i);
                }
            }
            else if(findPlayerById(homeTeamPlayers,allHomePlayerStats.get(i).getPlayer_id()).getPosition().equals("SMALL_FORWARD")) {
                if(BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i))>maxSFH) {
                    maxSFH=BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i));
                    bestSfH=allHomePlayerStats.get(i);
                }
            }
            else if(findPlayerById(homeTeamPlayers,allHomePlayerStats.get(i).getPlayer_id()).getPosition().equals("POWER_FORWARD")) {
                if(BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i))>maxPFH) {
                    maxPFH=BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i));
                    bestPfH=allHomePlayerStats.get(i);
                }
            }
            else if(findPlayerById(homeTeamPlayers,allHomePlayerStats.get(i).getPlayer_id()).getPosition().equals("CENTER")) {
                if(BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i))>maxCH) {
                    maxCH=BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i));
                    bestCH=allHomePlayerStats.get(i);
                }
            }
        }
        best5StatsHome.add(bestPgH);
        best5StatsHome.add(bestSgH);
        best5StatsHome.add(bestSfH);
        best5StatsHome.add(bestPfH);
        best5StatsHome.add(bestCH);

        //AWAY TEAM
        int maxPGA=-100;
        PlayerLiveStatistics bestPgA = null;
        int maxSGA=-100;
        PlayerLiveStatistics bestSgA = null;
        int maxSFA=-100;
        PlayerLiveStatistics bestSfA = null;
        int maxPFA=-100;
        PlayerLiveStatistics bestPfA = null;
        int maxCA=-100;
        PlayerLiveStatistics bestCA = null;

        //Sorting stats to the best 5 for the home team
        for(int i=0;i<allHomePlayerStats.size();i++){
            if(findPlayerById(homeTeamPlayers,allHomePlayerStats.get(i).getPlayer_id()).getPosition().equals("POINT_GUARD")) {
                if(BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i))>maxPGA) {
                    maxPGA=BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i));
                    bestPgA=allHomePlayerStats.get(i);
                }
            }
            else if(findPlayerById(homeTeamPlayers,allHomePlayerStats.get(i).getPlayer_id()).getPosition().equals("SHOOTING_GUARD")) {
                if(BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i))>maxSGA) {
                    maxSGA=BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i));
                    bestSgA=allHomePlayerStats.get(i);
                }
            }
            else if(findPlayerById(homeTeamPlayers,allHomePlayerStats.get(i).getPlayer_id()).getPosition().equals("SMALL_FORWARD")) {
                if(BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i))>maxSFA) {
                    maxSFA=BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i));
                    bestSfA=allHomePlayerStats.get(i);
                }
            }
            else if(findPlayerById(homeTeamPlayers,allHomePlayerStats.get(i).getPlayer_id()).getPosition().equals("POWER_FORWARD")) {
                if(BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i))>maxPFA) {
                    maxPFA=BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i));
                    bestPfA=allHomePlayerStats.get(i);
                }
            }
            else if(findPlayerById(homeTeamPlayers,allHomePlayerStats.get(i).getPlayer_id()).getPosition().equals("CENTER")) {
                if(BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i))>maxCA) {
                    maxCA=BestStarting5Factory.calculateEffic(allHomePlayerStats.get(i));
                    bestCA=allHomePlayerStats.get(i);
                }
            }
        }
        best5StatsAway.add(bestPgA);
        best5StatsAway.add(bestSgA);
        best5StatsAway.add(bestSfA);
        best5StatsAway.add(bestPfA);
        best5StatsAway.add(bestCA);

    }
    public Player findPlayerById(ArrayList<Player> myPlayers,int id){
        for(int i=0;i<myPlayers.size();i++){
            if(myPlayers.get(i).getId()==id){
                return myPlayers.get(i);
            }
        }
        return null;
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

        this.getPlayerLiveStatsForBoth(myMatch, homeTeam, awayTeam);
        pgNameH.setText(findPlayerById(homeTeamPlayers,best5StatsHome.get(0).getPlayer_id()).getName());
        pgNameA.setText(findPlayerById(awayTeamPlayers,best5StatsAway.get(0).getPlayer_id()).getName());

        Picasso.get()
                .load(Config.TEAM_IMAGES_RESOURCES + homeTeam.getBadgePath())
                .resize(400, 400)
                .centerCrop()
                .into(homeLogo);

        Picasso.get()
                .load(Config.TEAM_IMAGES_RESOURCES + awayTeam.getBadgePath())
                .resize(400, 400)
                .centerCrop()
                .into(awayLogo);

        //player 1
        pgMinsH.setText(best5StatsHome.get(0).getMinutes()+"");
        pgMinsA.setText(best5StatsAway.get(0).getMinutes()+"");

        pgPtsH.setText(best5StatsHome.get(0).getSuccessful_freethrow()*1+best5StatsHome.get(0).getSuccessful_twopointer()*2+best5StatsHome.get(0).getSuccessful_threepointer()*3);
        pgPtsA.setText(best5StatsAway.get(0).getSuccessful_freethrow()*1+best5StatsAway.get(0).getSuccessful_twopointer()*2+best5StatsAway.get(0).getSuccessful_threepointer()*3);

        pgAstH.setText(best5StatsHome.get(0).getAssist());
        pgAstA.setText(best5StatsAway.get(0).getAssist());

        pgBlkH.setText(best5StatsHome.get(0).getBlock());
        pgBlkA.setText(best5StatsAway.get(0).getBlock());

        pgRebsH.setText(best5StatsHome.get(0).getRebound());
        pgRebsA.setText(best5StatsAway.get(0).getRebound());

        pgStlH.setText(best5StatsHome.get(0).getSteal());
        pgStlA.setText(best5StatsAway.get(0).getSteal());

        //repeat process for other 4 players

    }

}