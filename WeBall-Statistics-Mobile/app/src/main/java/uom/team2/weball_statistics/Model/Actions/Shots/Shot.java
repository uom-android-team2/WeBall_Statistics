package uom.team2.weball_statistics.Model.Actions.Shots;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.BelongsTo;
import uom.team2.weball_statistics.Model.Actions.MatchFlow.FlowType;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;

public class Shot extends Action {

    private Player playerObj; //The player (obj) who shots
    private Team teamObj; //The team (obj) that the player who shots belongs to
    private ShotType shotType;
    private boolean scored; //true if the shots was successful and false if wasn't
    private Assist assist;

    private static String getMyImage(ShotType shotType) {
        switch (shotType) {
            case FREETHROW:
                return "shot_1_30px";
            case TWO_POINTER:
                return "shot_2_30px";
            case THREE_POINTER:
                return "shot_3_30px";
            default:
                return "whistle_30px";
        }
    }

    public Shot(String timeHappened, BelongsTo belongsTo, Player playerObj, Team teamObj, ShotType shotType, boolean scored) {
        super(timeHappened, belongsTo, getMyImage(shotType));
        this.playerObj = playerObj;
        this.teamObj = teamObj;
        this.shotType = shotType;
        this.scored = scored;
        this.actionDesc = this.formatActionDesc();
    }

    //For Assist
    public Shot(String timeHappened, BelongsTo belongsTo, Player playerObj, Team teamObj, ShotType shotType, boolean scored, Assist assist) {
        super(timeHappened, belongsTo, getMyImage(shotType));
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
