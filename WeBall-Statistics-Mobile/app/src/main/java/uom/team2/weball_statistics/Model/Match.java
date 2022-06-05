package uom.team2.weball_statistics.Model;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import uom.team2.weball_statistics.Model.Actions.Action;

/*
 * @author Minas - Theodoros Charakopoulos ics20072
 */
public class Match {
    private int id; //unique id for every match
    private Team teamLandlord; //Home team
    private Team guest; //Team away
    private Date matchDate;
    private Status status; // UPCOMING, ONGOING, COMPLETED
    private ArrayList<Action> actions;
    //Will added fields specified for comments and progress to appear them after the match completed

    public Match(int id, Team teamLandlord, Team guest, Date matchDate, Status status) {
        this.id = id;
        this.teamLandlord = teamLandlord;
        this.guest = guest;
        this.matchDate = matchDate;
        this.status = status;
        actions = new ArrayList<Action>();
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> match = new HashMap<>();

        match.put("id", this.id);
        match.put("teamLandlord", this.teamLandlord);
        match.put("guest", this.guest);
        match.put("matchDate", this.matchDate);
        match.put("status", this.status);
        match.put("actions", this.actions);

        return match;
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

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
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
}
