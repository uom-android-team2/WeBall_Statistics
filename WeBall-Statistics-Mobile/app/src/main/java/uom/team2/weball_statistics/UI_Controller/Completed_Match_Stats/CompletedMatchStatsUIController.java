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
import uom.team2.weball_statistics.databinding.CompleteMatchLeadersBinding;
import uom.team2.weball_statistics.databinding.CompletedMatchHeaderLayoutBinding;
import uom.team2.weball_statistics.databinding.CompletedMatchTeamPlayerStatsBinding;
import uom.team2.weball_statistics.utils.JSONHandler;

//--------------------CLASS NEEDS REFACTORING--------------------

public class CompletedMatchStatsUIController {

    private TeamLiveStatistics homeTeamLiveStats;
    private ArrayList<Player> homeTeamPlayers = new ArrayList<>();
    private TeamLiveStatistics awayTeamLiveStats;
    private ArrayList<Player> awayTeamPlayers = new ArrayList<>();


    private PlayerLiveStatistics bestPgH;
    private PlayerLiveStatistics bestSgH;
    private PlayerLiveStatistics bestSfH ;
    private PlayerLiveStatistics bestPfH ;
    private PlayerLiveStatistics bestCH ;


    private PlayerLiveStatistics bestPgA;
    private PlayerLiveStatistics bestSgA;
    private PlayerLiveStatistics bestSfA;
    private PlayerLiveStatistics bestPfA;
    private PlayerLiveStatistics bestCA;

    private ArrayList<PlayerLiveStatistics> filteredAllHomePlayerStats = new ArrayList<>();
    private ArrayList<PlayerLiveStatistics> filteredAllAwayPlayerStats = new ArrayList<>();


    public static CompletedMatchStatsUIController instance = new CompletedMatchStatsUIController();

    //Implement singleton pattern
    public static CompletedMatchStatsUIController getInstance() {
        if (instance == null) {
            instance = new CompletedMatchStatsUIController();
        }
        return instance;
    }
    //Works
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

    //Works
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

    //Does this work?
    public void getPlayerLiveStatsForBoth(Match myMatch, Team homeTeam, Team awayTeam) throws IOException, JSONException {

        //fetching stats for all players of the hometeam for this match
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(Config.API_URL+"playerLiveStatistics.php?match_id="+myMatch.getId())
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        ArrayList<PlayerLiveStatistics> unfilteredAllHomePlayerStats = JSONHandler.deserializeListOfPlayerLiveStatistics(response.body().string());
        for(int i=0;i<unfilteredAllHomePlayerStats.size();i++){
            if(this.isInTeam(homeTeamPlayers,unfilteredAllHomePlayerStats.get(i).getPlayer_id())){
                filteredAllHomePlayerStats.add(unfilteredAllHomePlayerStats.get(i));
            }
        }

        //fetching stats for all players of the away team for this match
        OkHttpClient client2 = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType2 = MediaType.parse("application/json");
        Request request2 = new Request.Builder()
                .url(Config.API_URL+"playerLiveStatistics.php?match_id="+myMatch.getId())
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response2 = client2.newCall(request2).execute();
        ArrayList<PlayerLiveStatistics> unfilteredAllAwayPlayerStats = JSONHandler.deserializeListOfPlayerLiveStatistics(response2.body().string());
        for(int i=0;i<unfilteredAllAwayPlayerStats.size();i++){
            if(this.isInTeam(awayTeamPlayers,unfilteredAllAwayPlayerStats.get(i).getPlayer_id())){
                filteredAllAwayPlayerStats.add(unfilteredAllAwayPlayerStats.get(i));
            }
        }

        //HOME TEAM
        int maxPGH=-100;
        int maxSGH=-100;
        int maxSFH=-100;
        int maxPFH=-100;
        int maxCH=-100;



        //Sorting stats to the best 5 for the home team
        for(int i=0;i<filteredAllHomePlayerStats.size();i++){
            if(findPlayerById(homeTeamPlayers,filteredAllHomePlayerStats.get(i).getPlayer_id()).getPosition().equals("POINT_GUARD")) {
                if(BestStarting5Factory.calculateEffic(filteredAllHomePlayerStats.get(i))>maxPGH) {
                    maxPGH=BestStarting5Factory.calculateEffic(filteredAllHomePlayerStats.get(i));
                    bestPgH=filteredAllHomePlayerStats.get(i);
                }
            }
            else if(findPlayerById(homeTeamPlayers,filteredAllHomePlayerStats.get(i).getPlayer_id()).getPosition().equals("SHOOTING_GUARD")) {
                if(BestStarting5Factory.calculateEffic(filteredAllHomePlayerStats.get(i))>maxSGH) {
                    maxSGH=BestStarting5Factory.calculateEffic(filteredAllHomePlayerStats.get(i));
                    bestSgH=filteredAllHomePlayerStats.get(i);
                }
            }
            else if(findPlayerById(homeTeamPlayers,filteredAllHomePlayerStats.get(i).getPlayer_id()).getPosition().equals("SMALL_FORWARD")) {
                if(BestStarting5Factory.calculateEffic(filteredAllHomePlayerStats.get(i))>maxSFH) {
                    maxSFH=BestStarting5Factory.calculateEffic(filteredAllHomePlayerStats.get(i));
                    bestSfH=filteredAllHomePlayerStats.get(i);
                }
            }
            else if(findPlayerById(homeTeamPlayers,filteredAllHomePlayerStats.get(i).getPlayer_id()).getPosition().equals("POWER_FORWARD")) {
                if(BestStarting5Factory.calculateEffic(filteredAllHomePlayerStats.get(i))>maxPFH) {
                    maxPFH=BestStarting5Factory.calculateEffic(filteredAllHomePlayerStats.get(i));
                    bestPfH=filteredAllHomePlayerStats.get(i);
                }
            }
            else if(findPlayerById(homeTeamPlayers,filteredAllHomePlayerStats.get(i).getPlayer_id()).getPosition().equals("CENTER")) {
                if(BestStarting5Factory.calculateEffic(filteredAllHomePlayerStats.get(i))>maxCH) {
                    maxCH=BestStarting5Factory.calculateEffic(filteredAllHomePlayerStats.get(i));
                    bestCH=filteredAllHomePlayerStats.get(i);
                }
            }
        }

        //AWAY TEAM
        int maxPGA=-100;
        int maxSGA=-100;
        int maxSFA=-100;
        int maxPFA=-100;
        int maxCA=-100;

        //Sorting stats to the best 5 for the away team
        for(int i=0;i<filteredAllAwayPlayerStats.size();i++){
            if(findPlayerById(awayTeamPlayers,filteredAllAwayPlayerStats.get(i).getPlayer_id()).getPosition().equals("POINT_GUARD")) {
                if(BestStarting5Factory.calculateEffic(filteredAllAwayPlayerStats.get(i))>maxPGA) {
                    maxPGA=BestStarting5Factory.calculateEffic(filteredAllAwayPlayerStats.get(i));
                    bestPgA=filteredAllAwayPlayerStats.get(i);
                }
            }
            else if(findPlayerById(awayTeamPlayers,filteredAllAwayPlayerStats.get(i).getPlayer_id()).getPosition().equals("SHOOTING_GUARD")) {
                if(BestStarting5Factory.calculateEffic(filteredAllAwayPlayerStats.get(i))>maxSGA) {
                    maxSGA=BestStarting5Factory.calculateEffic(filteredAllAwayPlayerStats.get(i));
                    bestSgA=filteredAllAwayPlayerStats.get(i);
                }
            }
            else if(findPlayerById(awayTeamPlayers,filteredAllAwayPlayerStats.get(i).getPlayer_id()).getPosition().equals("SMALL_FORWARD")) {
                if(BestStarting5Factory.calculateEffic(filteredAllAwayPlayerStats.get(i))>maxSFA) {
                    maxSFA=BestStarting5Factory.calculateEffic(filteredAllAwayPlayerStats.get(i));
                    bestSfA=filteredAllAwayPlayerStats.get(i);
                }
            }
            else if(findPlayerById(awayTeamPlayers,filteredAllAwayPlayerStats.get(i).getPlayer_id()).getPosition().equals("POWER_FORWARD")) {
                if(BestStarting5Factory.calculateEffic(filteredAllAwayPlayerStats.get(i))>maxPFA) {
                    maxPFA=BestStarting5Factory.calculateEffic(filteredAllAwayPlayerStats.get(i));
                    bestPfA=filteredAllAwayPlayerStats.get(i);
                }
            }
            else if(findPlayerById(awayTeamPlayers,filteredAllAwayPlayerStats.get(i).getPlayer_id()).getPosition().equals("CENTER")) {
                if(BestStarting5Factory.calculateEffic(filteredAllAwayPlayerStats.get(i))>maxCA) {
                    maxCA=BestStarting5Factory.calculateEffic(filteredAllAwayPlayerStats.get(i));
                    bestCA=filteredAllAwayPlayerStats.get(i);
                }
            }
        }

    }
    public Player findPlayerById(ArrayList<Player> myPlayers,int id){

        for(int i=0;i<myPlayers.size();i++){
            if(myPlayers.get(i).getId()==id){

                return myPlayers.get(i);

            }
        }
        return null;
    }

    public boolean isInTeam(ArrayList<Player> myPlayers, int id){
        for(int i=0;i<myPlayers.size();i++){
            if(myPlayers.get(i).getId()==id)
                return true;
        }
        return false;
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


        //Pgs
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

        //Sgs
        TextView sgNameH = completedMatchHeaderLayoutBindingHome.sgName;
        TextView sgMinsH = completedMatchHeaderLayoutBindingHome.sgMins;
        TextView sgPtsH = completedMatchHeaderLayoutBindingHome.sgPts;
        TextView sgAstH = completedMatchHeaderLayoutBindingHome.sgAst;
        TextView sgBlkH = completedMatchHeaderLayoutBindingHome.sgBlk;
        TextView sgRebsH = completedMatchHeaderLayoutBindingHome.sgRebs;
        TextView sgStlH = completedMatchHeaderLayoutBindingHome.sgStl;

        TextView sgNameA = completedMatchHeaderLayoutBindingAway.sgName;
        TextView sgMinsA = completedMatchHeaderLayoutBindingAway.sgMins;
        TextView sgPtsA = completedMatchHeaderLayoutBindingAway.sgPts;
        TextView sgAstA = completedMatchHeaderLayoutBindingAway.sgAst;
        TextView sgBlkA = completedMatchHeaderLayoutBindingAway.sgBlk;
        TextView sgRebsA = completedMatchHeaderLayoutBindingAway.sgRebs;
        TextView sgStlA = completedMatchHeaderLayoutBindingAway.sgStl;

        //Sfs
        TextView sfNameH = completedMatchHeaderLayoutBindingHome.sfName;
        TextView sfMinsH = completedMatchHeaderLayoutBindingHome.sfMins;
        TextView sfPtsH = completedMatchHeaderLayoutBindingHome.sfPts;
        TextView sfAstH = completedMatchHeaderLayoutBindingHome.sfAst;
        TextView sfBlkH = completedMatchHeaderLayoutBindingHome.sfBlk;
        TextView sfRebsH = completedMatchHeaderLayoutBindingHome.sfRebs;
        TextView sfStlH = completedMatchHeaderLayoutBindingHome.sfStl;

        TextView sfNameA = completedMatchHeaderLayoutBindingAway.sfName;
        TextView sfMinsA = completedMatchHeaderLayoutBindingAway.sfMins;
        TextView sfPtsA = completedMatchHeaderLayoutBindingAway.sfPts;
        TextView sfAstA = completedMatchHeaderLayoutBindingAway.sfAst;
        TextView sfBlkA = completedMatchHeaderLayoutBindingAway.sfBlk;
        TextView sfRebsA = completedMatchHeaderLayoutBindingAway.sfRebs;
        TextView sfStlA = completedMatchHeaderLayoutBindingAway.sfStl;

        //Pfs
        TextView pfNameH = completedMatchHeaderLayoutBindingHome.pfName;
        TextView pfMinsH = completedMatchHeaderLayoutBindingHome.pfMins;
        TextView pfPtsH = completedMatchHeaderLayoutBindingHome.pfPts;
        TextView pfAstH = completedMatchHeaderLayoutBindingHome.pfAst;
        TextView pfBlkH = completedMatchHeaderLayoutBindingHome.pfBlk;
        TextView pfRebsH = completedMatchHeaderLayoutBindingHome.pfRebs;
        TextView pfStlH = completedMatchHeaderLayoutBindingHome.pfStl;

        TextView pfNameA = completedMatchHeaderLayoutBindingAway.pfName;
        TextView pfMinsA = completedMatchHeaderLayoutBindingAway.pfMins;
        TextView pfPtsA = completedMatchHeaderLayoutBindingAway.pfPts;
        TextView pfAstA = completedMatchHeaderLayoutBindingAway.pfAst;
        TextView pfBlkA = completedMatchHeaderLayoutBindingAway.pfBlk;
        TextView pfRebsA = completedMatchHeaderLayoutBindingAway.pfRebs;
        TextView pfStlA = completedMatchHeaderLayoutBindingAway.pfStl;

        //Cs
        TextView cNameH = completedMatchHeaderLayoutBindingHome.cName;
        TextView cMinsH = completedMatchHeaderLayoutBindingHome.cMins;
        TextView cPtsH = completedMatchHeaderLayoutBindingHome.cPts;
        TextView cAstH = completedMatchHeaderLayoutBindingHome.cAst;
        TextView cBlkH = completedMatchHeaderLayoutBindingHome.cBlk;
        TextView cRebsH = completedMatchHeaderLayoutBindingHome.cRebs;
        TextView cStlH = completedMatchHeaderLayoutBindingHome.cStl;

        TextView cNameA = completedMatchHeaderLayoutBindingAway.cName;
        TextView cMinsA = completedMatchHeaderLayoutBindingAway.cMins;
        TextView cPtsA = completedMatchHeaderLayoutBindingAway.cPts;
        TextView cAstA = completedMatchHeaderLayoutBindingAway.cAst;
        TextView cBlkA = completedMatchHeaderLayoutBindingAway.cBlk;
        TextView cRebsA = completedMatchHeaderLayoutBindingAway.cRebs;
        TextView cStlA = completedMatchHeaderLayoutBindingAway.cStl;


        //Pgs
        this.getPlayerLiveStatsForBoth(myMatch, homeTeam, awayTeam);

        pgNameH.setText(findPlayerById(homeTeamPlayers,bestPgH.getPlayer_id()).getName()+"");
        pgNameA.setText(findPlayerById(awayTeamPlayers,bestPgA.getPlayer_id()).getName()+"");



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


        pgMinsH.setText(bestPgH.getMinutes()+"");
        pgMinsA.setText(bestPgA.getMinutes()+"");


        pgPtsH.setText(bestPgH.getSuccessful_freethrow()*1+bestPgH.getSuccessful_twopointer()*2+bestPgH.getSuccessful_threepointer()*3+"");
        pgPtsA.setText(bestPgA.getSuccessful_freethrow()*1+bestPgA.getSuccessful_twopointer()*2+bestPgA.getSuccessful_threepointer()*3+"");



        pgAstH.setText(bestPgH.getAssist()+"");
        pgAstA.setText(bestPgA.getAssist()+"");

        pgBlkH.setText(bestPgH.getBlock()+"");
        pgBlkA.setText(bestPgA.getBlock()+"");

        pgRebsH.setText(bestPgH.getRebound()+"");
        pgRebsA.setText(bestPgA.getRebound()+"");

        pgStlH.setText(bestPgH.getSteal()+"");
        pgStlA.setText(bestPgA.getSteal()+"");

        //Sgs
        sgMinsH.setText(bestSgH.getMinutes()+"");
        sgMinsA.setText(bestSgA.getMinutes()+"");

        sgPtsH.setText(bestSgH.getSuccessful_freethrow()*1+bestSgH.getSuccessful_twopointer()*2+bestSgH.getSuccessful_threepointer()*3+"");
        sgPtsA.setText(bestSgA.getSuccessful_freethrow()*1+bestSgA.getSuccessful_twopointer()*2+bestSgA.getSuccessful_threepointer()*3+"");

        sgAstH.setText(bestSgH.getAssist()+"");
        sgAstA.setText(bestSgA.getAssist()+"");

        sgBlkH.setText(bestSgH.getBlock()+"");
        sgBlkA.setText(bestSgA.getBlock()+"");

        sgRebsH.setText(bestSgH.getRebound()+"");
        sgRebsA.setText(bestSgA.getRebound()+"");

        sgStlH.setText(bestSgH.getSteal()+"");
        sgStlA.setText(bestSgA.getSteal()+"");

        sgNameH.setText(findPlayerById(homeTeamPlayers,bestSgH.getPlayer_id()).getName()+"");
        sgNameA.setText(findPlayerById(awayTeamPlayers,bestSgA.getPlayer_id()).getName()+"");

        //Sfs
        sfMinsH.setText(bestSfH.getMinutes()+"");
        sfMinsA.setText(bestSfA.getMinutes()+"");

        sfPtsH.setText(bestSfH.getSuccessful_freethrow()*1+bestSfH.getSuccessful_twopointer()*2+bestSfH.getSuccessful_threepointer()*3+"");
        sfPtsA.setText(bestSfA.getSuccessful_freethrow()*1+bestSfA.getSuccessful_twopointer()*2+bestSfA.getSuccessful_threepointer()*3+"");

        sfAstH.setText(bestSfH.getAssist()+"");
        sfAstA.setText(bestSfA.getAssist()+"");

        sfBlkH.setText(bestSfH.getBlock()+"");
        sfBlkA.setText(bestSfA.getBlock()+"");

        sfRebsH.setText(bestSfH.getRebound()+"");
        sfRebsA.setText(bestSfA.getRebound()+"");

        sfStlH.setText(bestSfH.getSteal()+"");
        sfStlA.setText(bestSfA.getSteal()+"");

        sfNameH.setText(findPlayerById(homeTeamPlayers,bestSfH.getPlayer_id()).getName()+"");
        sfNameA.setText(findPlayerById(awayTeamPlayers,bestSfA.getPlayer_id()).getName()+"");

        //Pfs
        pfMinsH.setText(bestPfH.getMinutes()+"");
        pfMinsA.setText(bestPfA.getMinutes()+"");

        pfPtsH.setText(bestPfH.getSuccessful_freethrow()*1+bestPfH.getSuccessful_twopointer()*2+bestPfH.getSuccessful_threepointer()*3+"");
        pfPtsA.setText(bestPfA.getSuccessful_freethrow()*1+bestPfA.getSuccessful_twopointer()*2+bestPfA.getSuccessful_threepointer()*3+"");

        pfAstH.setText(bestPfH.getAssist()+"");
        pfAstA.setText(bestPfA.getAssist()+"");

        pfBlkH.setText(bestPfH.getBlock()+"");
        pfBlkA.setText(bestPfA.getBlock()+"");

        pfRebsH.setText(bestPfH.getRebound()+"");
        pfRebsA.setText(bestPfA.getRebound()+"");

        pfStlH.setText(bestPfH.getSteal()+"");
        pfStlA.setText(bestPfA.getSteal()+"");

        pfNameH.setText(findPlayerById(homeTeamPlayers,bestPfH.getPlayer_id()).getName()+"");
        pfNameA.setText(findPlayerById(awayTeamPlayers,bestPfA.getPlayer_id()).getName()+"");

        //Cs
        cMinsH.setText(bestCH.getMinutes()+"");
        cMinsA.setText(bestCA.getMinutes()+"");

        cPtsH.setText(bestCH.getSuccessful_freethrow()*1+bestCH.getSuccessful_twopointer()*2+bestCH.getSuccessful_threepointer()*3+"");
        cPtsA.setText(bestCA.getSuccessful_freethrow()*1+bestCA.getSuccessful_twopointer()*2+bestCA.getSuccessful_threepointer()*3+"");

        cAstH.setText(bestCH.getAssist()+"");
        cAstA.setText(bestCA.getAssist()+"");

        cBlkH.setText(bestCH.getBlock()+"");
        cBlkA.setText(bestCA.getBlock()+"");

        cRebsH.setText(bestCH.getRebound()+"");
        cRebsA.setText(bestCA.getRebound()+"");

        cStlH.setText(bestCH.getSteal()+"");
        cStlA.setText(bestCA.getSteal()+"");

        cNameH.setText(findPlayerById(homeTeamPlayers,bestCH.getPlayer_id()).getName()+"");
        cNameA.setText(findPlayerById(awayTeamPlayers,bestCA.getPlayer_id()).getName()+"");

    }
    public void fillCompleteMatchTeamLeaderStats(CompletedMatchStats completedMatchStats, Team homeTeam, Team awayTeam) throws IOException, JSONException {
        CompleteMatchLeadersBinding completeMatchLeadersBinding = completedMatchStats.getBinding().includeLeaders;

        //Team Logos
        ImageView homeTeamLogo = completeMatchLeadersBinding.imageView5;
        ImageView awayTeamLogo = completeMatchLeadersBinding.imageView6;
        Picasso.get()
                .load(Config.TEAM_IMAGES_RESOURCES + homeTeam.getBadgePath())
                .resize(400, 400)
                .centerCrop()
                .into(homeTeamLogo);

        Picasso.get()
                .load(Config.TEAM_IMAGES_RESOURCES + awayTeam.getBadgePath())
                .resize(400, 400)
                .centerCrop()
                .into(awayTeamLogo);

        //Most Pts for each team
        ImageView ptsLeaderH = completeMatchLeadersBinding.mostPtsHphoto;
        ImageView ptsLeaderA = completeMatchLeadersBinding.mostPtsAphoto;

        TextView ptsLeadernameH = completeMatchLeadersBinding.mostPtsHname;
        TextView ptsLeadernameA = completeMatchLeadersBinding.mostPtsAname;

        TextView ptsLeaderValueH = completeMatchLeadersBinding.mostPtsHvalue;
        TextView ptsLeaderValueA = completeMatchLeadersBinding.mostPtsAvalue;


        //Most Ast for each team
        ImageView astLeaderH = completeMatchLeadersBinding.mostAstHphoto;
        ImageView astLeaderA = completeMatchLeadersBinding.mostAstAphoto;

        TextView astLeadernameH = completeMatchLeadersBinding.mostAstHname;
        TextView astLeadernameA = completeMatchLeadersBinding.mostAstAname;

        TextView astLeaderValueH = completeMatchLeadersBinding.mostAstHvalue;
        TextView astLeaderValueA = completeMatchLeadersBinding.mostAstAvalue;

        //Most Rebs for each team
        ImageView rebsLeaderH = completeMatchLeadersBinding.mostRebsHphoto;
        ImageView rebsLeaderA = completeMatchLeadersBinding.mostRebsAphoto;

        TextView rebsLeadernameH = completeMatchLeadersBinding.mostRebsHname;
        TextView rebsLeadernameA = completeMatchLeadersBinding.mostRebsAname;

        TextView rebsLeaderValueH = completeMatchLeadersBinding.mostRebsHvalue;
        TextView rebsLeaderValueA = completeMatchLeadersBinding.mostRebsAvalue;


        //Calculating pts Leader for both teams
        PlayerLiveStatistics[] homePlayers = {bestPgH,bestSgH,bestSfH,bestPfH,bestCH};
        int maxHome = bestPgH.getSuccessful_freethrow()*1+bestPgH.getSuccessful_twopointer()*2+bestPgH.getSuccessful_threepointer()*3;

        PlayerLiveStatistics[] awayPlayers = {bestPgA,bestSgA,bestSfA,bestPfA,bestCA};
        int maxAway = bestPgA.getSuccessful_freethrow()*1+bestPgA.getSuccessful_twopointer()*2+bestPgA.getSuccessful_threepointer()*3;

        Player ptsLeaderHOME = findPlayerById(homeTeamPlayers, bestPgH.getPlayer_id());
        Player ptsLeaderAWAY = findPlayerById(awayTeamPlayers, bestPgA.getPlayer_id());
        for(int j=1;j<5;j++){
            if(homePlayers[j].getSuccessful_freethrow()*1+homePlayers[j].getSuccessful_twopointer()*2+homePlayers[j].getSuccessful_threepointer()*3>=maxHome){
                ptsLeaderHOME = findPlayerById(homeTeamPlayers, homePlayers[j].getPlayer_id());
                maxHome = homePlayers[j].getSuccessful_freethrow()*1+homePlayers[j].getSuccessful_twopointer()*2+homePlayers[j].getSuccessful_threepointer()*3;
            }
            if(awayPlayers[j].getSuccessful_freethrow()*1+awayPlayers[j].getSuccessful_twopointer()*2+awayPlayers[j].getSuccessful_threepointer()*3>=maxAway){
                ptsLeaderAWAY = findPlayerById(awayTeamPlayers, awayPlayers[j].getPlayer_id());
                maxAway = awayPlayers[j].getSuccessful_freethrow()*1+awayPlayers[j].getSuccessful_twopointer()*2+awayPlayers[j].getSuccessful_threepointer()*3;
            }
        }

        //Calculating ast Leader for both teams
        int maxHomeAST = bestPgH.getAssist();
        int maxAwayAST = bestPgA.getAssist();

        Player astLeaderHOME = findPlayerById(homeTeamPlayers, bestPgH.getPlayer_id());
        Player astLeaderAWAY = findPlayerById(awayTeamPlayers, bestPgA.getPlayer_id());

        for(int j=1;j<5;j++){
            if(homePlayers[j].getAssist()>=maxHomeAST){
                astLeaderHOME = findPlayerById(homeTeamPlayers, homePlayers[j].getPlayer_id());
                maxHomeAST = homePlayers[j].getAssist();
            }
            if(awayPlayers[j].getAssist()>=maxAwayAST){
                astLeaderAWAY = findPlayerById(awayTeamPlayers, awayPlayers[j].getPlayer_id());
                maxAwayAST = awayPlayers[j].getAssist();
            }
        }

        //Calculating rebs Leader for both teams
        int maxHomeREBS = bestPgH.getRebound();
        int maxAwayREBS = bestPgA.getRebound();

        Player rebsLeaderHOME = findPlayerById(homeTeamPlayers, bestPgH.getPlayer_id());
        Player rebsLeaderAWAY = findPlayerById(awayTeamPlayers, bestPgA.getPlayer_id());

        for(int j=1;j<5;j++){
            if(homePlayers[j].getRebound()>=maxHomeREBS){
                rebsLeaderHOME = findPlayerById(homeTeamPlayers, homePlayers[j].getPlayer_id());
                maxHomeREBS = homePlayers[j].getRebound();
            }
            if(awayPlayers[j].getRebound()>=maxAwayREBS){
                rebsLeaderAWAY = findPlayerById(awayTeamPlayers, awayPlayers[j].getPlayer_id());
                maxAwayREBS = awayPlayers[j].getRebound();
            }
        }

        //Pts leaders pictures
        Picasso.get()
                .load(Config.PLAYER_IMAGES_RESOURCES+ptsLeaderHOME.getImagePath())
                .resize(400, 400)
                .centerCrop()
                .into(ptsLeaderH);
        Picasso.get()
                .load(Config.PLAYER_IMAGES_RESOURCES+ptsLeaderAWAY.getImagePath())
                .resize(400, 400)
                .centerCrop()
                .into(ptsLeaderA);

        //Ast leader pictures
        Picasso.get()
                .load(Config.PLAYER_IMAGES_RESOURCES+astLeaderHOME.getImagePath())
                .resize(400, 400)
                .centerCrop()
                .into(astLeaderH);
        Picasso.get()
                .load(Config.PLAYER_IMAGES_RESOURCES+astLeaderAWAY.getImagePath())
                .resize(400, 400)
                .centerCrop()
                .into(astLeaderA);

        //Rebs leader pictures
        Picasso.get()
                .load(Config.PLAYER_IMAGES_RESOURCES+rebsLeaderHOME.getImagePath())
                .resize(400, 400)
                .centerCrop()
                .into(rebsLeaderH);
        Picasso.get()
                .load(Config.PLAYER_IMAGES_RESOURCES+rebsLeaderAWAY.getImagePath())
                .resize(400, 400)
                .centerCrop()
                .into(rebsLeaderA);


        ptsLeadernameH.setText(ptsLeaderHOME.getName()+"");
        ptsLeadernameA.setText(ptsLeaderAWAY.getName()+"");

        astLeadernameH.setText(astLeaderHOME.getName()+"");
        astLeadernameA.setText(astLeaderAWAY.getName()+"");

        rebsLeadernameH.setText(rebsLeaderHOME.getName()+"");
        rebsLeadernameA.setText(rebsLeaderAWAY.getName()+"");

        ptsLeaderValueH.setText(maxHome+"");
        ptsLeaderValueA.setText(maxAway+"");

        astLeaderValueH.setText(maxHomeAST+"");
        astLeaderValueA.setText(maxAwayAST+"");

        rebsLeaderValueH.setText(maxHomeREBS+"");
        rebsLeaderValueA.setText(maxAwayREBS+"");
    }
}