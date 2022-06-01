package uom.team2.weball_statistics.Model.Actions.ReboundAction;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;

public class Rebound extends Action {
    private Player playerObj; //The player (obj) who gets the rebound
    private Team teamObj; //The team (obj) that the player who gets the rebound belongs to

    public Rebound(String timeHappened, Player playerObj, Team teamObj) {
        super(timeHappened);
        this.playerObj = playerObj;
        this.teamObj = teamObj;
        this.actionDesc = this.formatActionDesc();
    }

    @Override
    public String formatActionDesc() {
        String playerName = this.playerObj.getName().charAt(0) + "." + playerObj.getSurname();

        return ("Rebound from " + playerName);
    }
}
