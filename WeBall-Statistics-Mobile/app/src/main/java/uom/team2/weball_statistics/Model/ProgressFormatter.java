package uom.team2.weball_statistics.Model;

/*
 * @author Minas - Theodoros Charakopoulos ics20072
 */

public class ProgressFormatter {
    private String actionType; //Depending on the button text that will be pressed by the administrator to add match action
    private String playerName; //Player name who does the action (can be empty)
    private String teamName; //Team of the player who does the action (can be empty)
    private String timeHappened; //The time that the action happened

    public void returnFormattedAction() {
        if (this.actionType.equalsIgnoreCase("match_started")) {
            //do something
        } else if (this.actionType.equalsIgnoreCase("match_paused")) {
            //do something
        }
    }
}
