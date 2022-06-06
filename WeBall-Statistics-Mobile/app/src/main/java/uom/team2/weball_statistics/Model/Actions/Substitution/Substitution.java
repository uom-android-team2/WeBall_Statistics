package uom.team2.weball_statistics.Model.Actions.Substitution;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;

public class Substitution extends Action {

    private Player playerObjIn; //The player who will enter the game
    private Player playerObjOut; //The player who will exit the game
    private Team teamObj; //The team object that this two players belongs to

    public Substitution(String timeHappened, int id, Player playerObjIn, Player playerObjOut, Team teamObj) {
        super(timeHappened, id);
        this.playerObjIn = playerObjIn;
        this.playerObjOut = playerObjOut;
        this.teamObj = teamObj;
        this.actionDesc = this.formatActionDesc();
    }

    @Override
    protected String formatActionDesc() {

        if (this.playerObjOut != null && this.playerObjIn != null) {
            String playerInFormattedName = this.playerObjIn.getName().charAt(0) + "." + this.playerObjIn.getSurname();
            String playerOutFormattedName = this.playerObjOut.getName().charAt(0) + "." + this.playerObjOut.getSurname();

            return playerInFormattedName + " (" + playerOutFormattedName + ")";
        } else {
            return "Undefined";
        }
    }
}
