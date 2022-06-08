package uom.team2.weball_statistics.Model.Actions.ReboundAction;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.BelongsTo;
import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;

public class Rebound extends Action {
    private Player playerObj; //The player (obj) who gets the rebound
    private Team teamObj; //The team (obj) that the player who gets the rebound belongs to

    public Rebound(String timeHappened, BelongsTo belongsTo, Player playerObj, Team teamObj, Match match) {
        super(timeHappened, belongsTo, match);
        this.playerObj = playerObj;
        this.teamObj = teamObj;
        this.actionDesc = this.formatActionDesc();
    }

    @Override
    protected String formatActionDesc() {
        String playerName = this.playerObj.getName().charAt(0) + "." + this.playerObj.getSurname();

        return ("Rebound from " + playerName);
    }
}
