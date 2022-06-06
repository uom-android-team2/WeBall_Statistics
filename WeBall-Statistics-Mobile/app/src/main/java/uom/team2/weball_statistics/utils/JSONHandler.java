package uom.team2.weball_statistics.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import uom.team2.weball_statistics.Model.Player;
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


}
