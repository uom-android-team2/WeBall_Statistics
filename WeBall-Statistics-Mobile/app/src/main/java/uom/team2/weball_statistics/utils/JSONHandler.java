package uom.team2.weball_statistics.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.PlayerLiveStatistics;
import uom.team2.weball_statistics.Model.PlayerPosition;
import uom.team2.weball_statistics.Model.Team;

/*
 * @author Leonard Pepa ics20033
 */
public class JSONHandler {

    public static Team deserializeTeam(String data) throws JSONException {
        JSONObject jsonObject = new JSONObject(data);

        int id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String city = jsonObject.getString("city");
        String badge = jsonObject.getString("badge");

        Team team = new Team(id, name, city, badge);

        return team;
    }

    public static ArrayList<Team> deserializeListOfTeams(String data
    ) throws JSONException {
        ArrayList<Team> teams = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(data);

        for (int i = 0; i < jsonArray.length(); i++) {
            teams.add(deserializeTeam(jsonArray.getJSONObject(i).toString()));
        }
        return teams;
    }

    public static Player deserializePlayer(String data) throws JSONException {
        JSONObject jsonObject = new JSONObject(data);

        int id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String surname = jsonObject.getString("surname");
        int number = jsonObject.getInt("number");
        String position = jsonObject.getString("position");
        String team = jsonObject.getString("team");
        String photo = jsonObject.getString("photo");

        PlayerPosition playerPosition = PlayerPosition.CENTER;
        String tempPosition = position.replace(" ", "_");

        if (tempPosition.equalsIgnoreCase(PlayerPosition.CENTER.name())) {
            playerPosition = PlayerPosition.CENTER;
        }
        if (tempPosition.equalsIgnoreCase(PlayerPosition.POINT_GUARD.name())) {
            playerPosition = PlayerPosition.POINT_GUARD;
        }
        if (tempPosition.equalsIgnoreCase(PlayerPosition.POWER_FORWARD.name())) {
            playerPosition = PlayerPosition.POWER_FORWARD;
        }
        if (tempPosition.equalsIgnoreCase(PlayerPosition.SHOOTING_GUARD.name())) {
            playerPosition = PlayerPosition.SHOOTING_GUARD;
        }
        if (tempPosition.equalsIgnoreCase(PlayerPosition.POWER_FORWARD.name())) {
            playerPosition = PlayerPosition.POWER_FORWARD;
        }
        if (tempPosition.equalsIgnoreCase(PlayerPosition.SMALL_FORWARD.name())) {
            playerPosition = PlayerPosition.SMALL_FORWARD;
        }


        return new Player(id, name, surname, number, playerPosition, team, photo);
    }


    public static ArrayList<Player> deserializeListOfPlayers(String data
    ) throws JSONException {
        ArrayList<Player> players = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(data);

        for (int i = 0; i < jsonArray.length(); i++) {
            players.add(deserializePlayer(jsonArray.getJSONObject(i).toString()));
        }
        return players;
    }

    public static Match deserializeMatch(String data) throws JSONException {
        JSONObject jsonObject = new JSONObject(data);

        int id = jsonObject.getInt("id");
        int teamlandlord_id = jsonObject.getInt("teamlandlord_id");
        int  teamguest_id = jsonObject.getInt("teamguest_id");
        int date = jsonObject.getInt("date");
        boolean progress =  Boolean.parseBoolean(jsonObject.getInt("progress")+"");
        boolean completed = Boolean.parseBoolean(jsonObject.getInt("completed")+"");

        Match match = new Match(id,teamlandlord_id,teamguest_id,date,progress,completed);

        return match;
    }

    public static ArrayList<Match> deserializeListOfMatches(String data
    ) throws JSONException {
        ArrayList<Match> matches = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(data);

        for (int i = 0; i < jsonArray.length(); i++) {
            matches.add(deserializeMatch(jsonArray.getJSONObject(i).toString()));
        }
        return matches;
    }

    public static PlayerLiveStatistics deserializePlayerLiveStatistics(String data) throws JSONException {
        JSONObject jsonObject = new JSONObject(data);

        int playerId = jsonObject.getInt("player_id");
        int matchId = jsonObject.getInt("match_id");
        int  successfulEffort = jsonObject.getInt("successful_effort");
        int totalEffort = jsonObject.getInt("total_effort");

        int successfulFreethrow = jsonObject.getInt("successful_freethrow");
        int totalFreethrow = jsonObject.getInt("total_freethrow");
        int  succesfulTwopointer = jsonObject.getInt("successful_twopointer");
        int totalTwopointer = jsonObject.getInt("total_twopointer");

        int succesfulThreepointer = jsonObject.getInt("successful_threepointer");
        int totalThreepointer = jsonObject.getInt("total_threepointer");
        int  steal = jsonObject.getInt("steal");
        int assist = jsonObject.getInt("assist");

        int block = jsonObject.getInt("block");
        int rebound = jsonObject.getInt("rebound");
        int  foul = jsonObject.getInt("foul");
        int turnover = jsonObject.getInt("turnover");
        int minutes = jsonObject.getInt("minutes");

        PlayerLiveStatistics playerLiveStatistics = new PlayerLiveStatistics(matchId, playerId, successfulEffort, totalEffort,
                successfulFreethrow, totalFreethrow, succesfulTwopointer, totalTwopointer, succesfulThreepointer, totalThreepointer,
                steal, assist, block, rebound, foul, turnover, minutes);

        return playerLiveStatistics;
    }

    public static ArrayList<PlayerLiveStatistics> deserializeListOfPlayerLiveStatistics(String data
    ) throws JSONException {
        ArrayList<PlayerLiveStatistics> playerLiveStatisticsList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(data);

        for (int i = 0; i < jsonArray.length(); i++) {
            playerLiveStatisticsList.add(deserializePlayerLiveStatistics(jsonArray.getJSONObject(i).toString()));
        }
        return playerLiveStatisticsList;
    }

}
