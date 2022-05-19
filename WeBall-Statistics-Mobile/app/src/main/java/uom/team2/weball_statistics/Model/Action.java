package uom.team2.weball_statistics.Model;

public class Action {
    private String actionType; //Depending on the button text that will be pressed by the administrator to add match action
    private String playerName; //Player name who does the action (can be empty)
    private String teamName; //Team of the player who does the action (can be empty)
    private String timeHappened; //The time that the action happened

    public Action(String actionType, String playerName, String teamName, String timeHappened) {
        this.actionType = actionType;
        this.playerName = playerName;
        this.teamName = teamName;
        this.timeHappened = timeHappened;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTimeHappened() {
        return timeHappened;
    }

    public void setTimeHappened(String timeHappened) {
        this.timeHappened = timeHappened;
    }
}
