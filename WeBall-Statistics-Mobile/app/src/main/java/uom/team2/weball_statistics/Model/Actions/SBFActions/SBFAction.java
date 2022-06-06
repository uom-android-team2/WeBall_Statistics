package uom.team2.weball_statistics.Model.Actions.SBFActions;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.BelongsTo;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;

/*
 *Steal or Block or Foul
 *Class with this 3 actions because the information we want to display for all 3 is common.
 * Also for the specific 3, in a future software upgrade we can add the opponent player to whom the action was caused.
 */
public class SBFAction extends Action {
    private Player playerObj; //The player (obj) who does the action
    private Team teamObj; //The team (obj) that the player who does the action belongs to
    private SBFActionType sbfActionType; //STEAL, BLOCK OR FOUL

    public SBFAction(String timeHappened, int id, BelongsTo belongsTo, Player playerObj, Team teamObj, SBFActionType sbfActionType) {
        super(timeHappened, id, belongsTo);
        this.playerObj = playerObj;
        this.teamObj = teamObj;
        this.sbfActionType = sbfActionType;
        this.actionDesc = this.formatActionDesc();
    }

    @Override
    protected String formatActionDesc() {

        String playerName = this.playerObj.getName().charAt(0) + "." + this.playerObj.getSurname();

        switch (this.sbfActionType) {
            case STEAL:
                return ("Steal from " + playerName);
            case BLOCK:
                return ("Block from " + playerName);
            case FOUL:
                return ("Foul from " + playerName);
            default:
                return "Undefined";
        }
    }
}
