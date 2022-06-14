package uom.team2.weball_statistics.Model.Actions.Turnover;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.BelongsTo;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;

public class Turnover extends Action {
    //This fields needed for comments where we mention more details about this
    private Player playerObj; //The player (obj) who does the fault and the the team loses the ball
    private Team teamObj; //The team (obj) that loses the ball

    public Turnover(String timeHappened, BelongsTo belongsTo, Player playerObj, Team teamObj) {
        super(timeHappened, belongsTo, "turnover_30px");
        this.playerObj = playerObj;
        this.teamObj = teamObj;
        this.actionDesc = this.formatActionDesc();
    }

    @Override
    protected String formatActionDesc() {
        return "Loss of possession!";
    }
}
