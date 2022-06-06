package uom.team2.weball_statistics.Model.Actions.Shots;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.BelongsTo;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;

public class Shot extends Action {
    private Player playerObj; //The player (obj) who shots
    private Team teamObj; //The team (obj) that the player who shots belongs to
    private ShotType shotType;
    private boolean scored; //true if the shots was successful and false if wasn't
    private Assist assist;

    public Shot(String timeHappened, int id, BelongsTo belongsTo, Player playerObj, Team teamObj, ShotType shotType, boolean scored) {
        super(timeHappened, id, belongsTo);
        this.playerObj = playerObj;
        this.teamObj = teamObj;
        this.shotType = shotType;
        this.scored = scored;
        this.actionDesc = this.formatActionDesc();
    }

    //For Assist
    public Shot(String timeHappened, int id, BelongsTo belongsTo, Player playerObj, Team teamObj, ShotType shotType, boolean scored, Assist assist) {
        super(timeHappened, id, belongsTo);
        this.playerObj = playerObj;
        this.teamObj = teamObj;
        this.shotType = shotType;
        this.scored = scored;
        this.assist = assist;
        this.actionDesc = this.formatActionDesc();
    }

    @Override
    protected String formatActionDesc() {

        //we want to appear only the successful tries
        if (this.scored) {

            String playerName = this.playerObj.getName().charAt(0) + "." + this.playerObj.getSurname();
            String description = "";
            
            switch (this.shotType) {
                case FREETHROW:
                    description = "+1 from " + playerName;
                    break;
                case TWO_POINTER:
                    description = "+2 from " + playerName;
                    break;
                case THREE_POINTER:
                    description = "+3 from " + playerName;
                    break;
                default:
                    description  = "Undefined";
                    break;
            }

            if (this.assist != null) {
                description = description + " (" + assist.getPlayerObj().getName().charAt(0) + "." + assist.getPlayerObj().getSurname() + ")";
            }

            return  description;
        }
        return "Undefined";
    }
}
