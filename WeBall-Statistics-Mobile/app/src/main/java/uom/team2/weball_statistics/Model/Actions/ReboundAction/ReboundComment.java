package uom.team2.weball_statistics.Model.Actions.ReboundAction;

import android.content.Context;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.BelongsTo;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;

public class ReboundComment extends Action {

    private Player playerObj; //The player (obj) who gets the rebound
    private Team teamObj; //The team (obj) that the player who gets the rebound belongs to

    public ReboundComment(String timeHappened, BelongsTo belongsTo, Player playerObj, Team teamObj) {
        super(timeHappened, belongsTo, "rebound_30px");
        this.playerObj = playerObj;
        this.teamObj = teamObj;
        this.actionDesc = this.formatActionDesc();
    }

    @Override
    protected String formatActionDesc() {
        String playerFullName = this.playerObj.getName() + " " + this.playerObj.getSurname();

        int randomChance = (int)Math.floor(Math.random()*(11)); //random number between 0 and 10

        if (randomChance >=7) {
            return playerFullName + " fought for the rebound, and the " + teamObj.getTeamName() + " wins the possession!";
        } else {
            return  "A rebound is made by " + playerFullName + " for the team of " + teamObj.getTeamName();
        }
    }
}
