package uom.team2.weball_statistics.Model.Actions.Turnover;

import android.content.Context;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.BelongsTo;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.configuration.Config;

public class TurnoverComment extends Action {

    //This fields needed for comments where we mention more details about this
    private Player playerObj; //The player (obj) who does the fault and the the team loses the ball
    private Team teamObj; //The team (obj) that loses the ball
    private Context context;

    public TurnoverComment(String timeHappened, BelongsTo belongsTo, Player playerObj, Team teamObj, Context context) {
        super(timeHappened, belongsTo, "turnover_30px");
        this.playerObj = playerObj;
        this.teamObj = teamObj;
        this.context = context;
        this.actionDesc = this.formatActionDesc();
    }

    @Override
    protected String formatActionDesc() {

        return this.commentGeneratorForTurnover();
    }

    private String commentGeneratorForTurnover() {
        String[] stringArray = this.context.getResources().getStringArray(R.array.turnover);
        int randomStringPosition = (int)Math.floor(Math.random()*(stringArray.length));

        return stringArray[randomStringPosition] + " " + playerObj.getSurname();
    }
}
