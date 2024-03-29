package uom.team2.weball_statistics.Model;


import java.io.Serializable;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;

import uom.team2.weball_statistics.Model.Actions.Action;

public class Match implements Serializable {
    private int id; //unique id for every match

    private Team teamLandlord; //Home team
    private Team guest; //Team away
    private Date matchDate;
    private int date;
    private int teamLandlord_id;
    private int Teamguest_id;
    private Status status; // UPCOMING, ONGOING, COMPLETED
    private int progress = 0;
    private long keepClock;
    private boolean completed = false;
    private ArrayList<Action> actions;
    private Referee referee;
    private int actionsCount; //Need to keep the actions number, to give the correct id to each action of the match


    public Match(int id, Team teamLandlord, Team guest, Date matchDate, Status status, Referee referee) {
        this.id = id;
        this.teamLandlord = teamLandlord;
        this.guest = guest;
        this.matchDate = matchDate;
        this.status = status;
        this.referee = referee;
        actions = new ArrayList<Action>();
    }

    public Match(int id, int teamLandlord_id, int Teamguest_id, int date, int progress, boolean completed) {
        this.id = id;
        this.teamLandlord_id = teamLandlord_id;
        this.Teamguest_id = Teamguest_id;
        this.date = date;
        this.progress = progress;
        this.completed = completed;
    }

    public Match(int id, Team teamLandlord, Team teamGuest, Date date, Status status) {
        this.id = id;
        this.teamLandlord = teamLandlord;
        this.guest = teamGuest;
        this.matchDate = date;
        this.status = status;
    }

    public void setActionsCount(int actionsCount) {
        this.actionsCount = actionsCount;
    }

    public int getActionsCount() {
        return this.actionsCount;
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getTeamLandlord() {
        return teamLandlord;
    }

    public void setTeamLandlord(Team teamLandlord) {
        this.teamLandlord = teamLandlord;
    }

    public Team getGuest() {
        return guest;
    }

    public void setGuest(Team guest) {
        this.guest = guest;
    }

    public int getDate() {
        return date;
    }

    public void setMatchDate(int date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }


    public void setProgress(int b) {
        this.progress = b;
    }

    public int isProgress() {
        return progress;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean b) {

        completed = b;
    }

    public Referee getReferee() {
        return referee;

    }

    public int getTeamLandlord_id() {
        return teamLandlord_id;
    }

    public int getTeamguest_id() {
        return Teamguest_id;
    }

    public void setKeepClock(long cl){
        keepClock= cl;
    }

    public long getKeepClock() {
        return keepClock;
    }
}
