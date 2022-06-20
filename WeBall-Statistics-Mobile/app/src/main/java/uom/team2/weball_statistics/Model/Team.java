package uom.team2.weball_statistics.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {
    private int id;
    private String teamName;
    private String teamCity;
    private String badgePath; //Path for the badge image
    private Coach teamCoach;
    private ArrayList<Player> teamPlayers;
    private Championship championshipIn; //The championship that participates
    private  ArrayList<Player> keyPlayers = new ArrayList<Player>();
    private  ArrayList<Player> subPlayers = new ArrayList<Player>();


    //only for testing
    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team(int id, String teamName, String teamCity, String badgePath) {
        this.id = id;
        this.teamName = teamName;
        this.teamCity = teamCity;
        this.badgePath = badgePath;
    }

    public Team(int id, String teamName, String teamCity, String badgePath, Coach teamCoach, ArrayList<Player> teamPlayers, Championship championshipIn) {
        this.id = id;
        this.teamName = teamName;
        this.teamCity = teamCity;
        this.badgePath = badgePath;
        this.teamCoach = teamCoach;
        this.teamPlayers = teamPlayers;
        this.championshipIn = championshipIn;
        setPlayersInKeyAndSub();
    }


    public void setPlayersInKeyAndSub() {
        for (int i = 0; i < teamPlayers.size(); i++) {
            if (i < 5) {
                keyPlayers.add(teamPlayers.get(i));
            } else {
                subPlayers.add(teamPlayers.get(i));
            }
        }
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

    public ArrayList<Player> getSubPlayers() {
        return this.subPlayers;
    }

    public ArrayList<Player> getKeyPlayers() {
        return this.keyPlayers;
    }

    public void setKeyPlayers(int k, Player p) {
        this.keyPlayers.remove(k);
        this.keyPlayers.add(k, p);
    }

    public void setSubPlayers(int k, Player p) {
        this.subPlayers.remove(k);
        this.subPlayers.add(k, p);
    }

    public void setTeamPlayers(ArrayList<Player> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }

    public void addPlayerToKey(Player p) {
        keyPlayers.add(p);
    }

    public void addPlayerToSub(Player p) {
        subPlayers.add(p);
    }

    public void setSubPlayersList(ArrayList<Player> pList){
        subPlayers=pList;
    }

    public void setKeyPlayersList(ArrayList<Player> pList){
        keyPlayers=pList;
    }

}
