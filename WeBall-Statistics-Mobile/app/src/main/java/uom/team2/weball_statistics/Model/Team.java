package uom.team2.weball_statistics.Model;

import java.util.ArrayList;

/*
 * @author Minas - Theodoros Charakopoulos ics20072
 */
public class Team {
    private int id;
    private String teamName;
    private String teamCity;
    private String badgePath; //Path for the badge image
    private Coach teamCoach;
    private ArrayList<Player> teamPlayers;
    private Championship championshipIn; //The championship that participates

    public Team(int id, String teamName, String teamCity, String badgePath, Coach teamCoach, ArrayList<Player> teamPlayers, Championship championshipIn) {
        this.id = id;
        this.teamName = teamName;
        this.teamCity = teamCity;
        this.badgePath = badgePath;
        this.teamCoach = teamCoach;
        this.teamPlayers = teamPlayers;
        this.championshipIn = championshipIn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamCity() {
        return teamCity;
    }

    public void setTeamCity(String teamCity) {
        this.teamCity = teamCity;
    }

    public String getBadgePath() {
        return badgePath;
    }

    public void setBadgePath(String badgePath) {
        this.badgePath = badgePath;
    }

    public Coach getTeamCoach() {
        return teamCoach;
    }

    public void setTeamCoach(Coach teamCoach) {
        this.teamCoach = teamCoach;
    }

    public Championship getChampionshipIn() {
        return championshipIn;
    }

    public void setChampionshipIn(Championship championshipIn) {
        this.championshipIn = championshipIn;
    }

    public ArrayList<Player> getTeamPlayers() {
        return teamPlayers;
    }


}
