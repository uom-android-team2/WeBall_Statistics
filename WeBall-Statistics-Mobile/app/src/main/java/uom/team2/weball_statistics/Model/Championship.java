package uom.team2.weball_statistics.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Championship implements Serializable {
    private ArrayList<Team> teams; //All teams in championship
    private ArrayList<Coach> coaches; //All coaches in championship;
    private ArrayList<Referee> referees; //All referees in championship
    private ArrayList <Match> matches; //All matches (completed/upcoming) in championship

    public Championship() {
        this.teams = new ArrayList<>();
        this.coaches = new ArrayList<>();
        this.referees = new ArrayList<>();
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public ArrayList<Coach> getCoaches() {
        return coaches;
    }

    public ArrayList<Referee> getReferees() {
        return referees;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }
}
