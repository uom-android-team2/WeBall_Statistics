package uom.team2.weball_statistics.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

        return  team;
    }

    // TODO: implemeted this
    public static ArrayList<Team> deserializeListOfTeams(String data
    ) throws JSONException {
        ArrayList<Team> teams = new ArrayList<>();


        return teams;
    };
}
