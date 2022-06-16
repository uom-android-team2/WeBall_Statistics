package uom.team2.weball_statistics.Model.Actions.Substitution;

import android.content.Context;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.BelongsTo;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;

public class SubstitutionComment extends Action {

    private Player playerObjIn; //The player who will enter the game
    private Player playerObjOut; //The player who will exit the game
    private Team teamObj; //The team object that this two players belongs to
    private Context context;

    public SubstitutionComment(String timeHappened, BelongsTo belongsTo, Player playerObjIn, Player playerObjOut, Team teamObj, Context context) {
        super(timeHappened, belongsTo, "player_change_30px");
        this.playerObjIn = playerObjIn;
        this.playerObjOut = playerObjOut;
        this.teamObj = teamObj;
        this.context = context;
        this.actionDesc = this.formatActionDesc();
    }

    @Override
    protected String formatActionDesc() {

        if (this.playerObjOut != null && this.playerObjIn != null) {
            return this.commentGeneratorForSubstitution();
        } else {
            return "Undefined";
        }
    }

    private String commentGeneratorForSubstitution() {
        String playerInFormattedName = this.playerObjIn.getName().charAt(0) + "." + this.playerObjIn.getSurname();
        String playerOutFormattedName = this.playerObjOut.getName().charAt(0) + "." + this.playerObjOut.getSurname();
        String[] stringArray = this.context.getResources().getStringArray(R.array.substitution);
        int randomStringPosition = (int)Math.floor(Math.random()*(stringArray.length));

        return stringArray[randomStringPosition] + " adding " + playerInFormattedName + " to the game, and taking out " + playerOutFormattedName;
    }
}
