package uom.team2.weball_statistics.Model.Actions.Shots;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;

public class Shot extends Action {
    private Player playerObj; //The player (obj) who shots
    private Team teamObj; //The team (obj) that the player who shots belongs to
    private ShotType shotType;
    private boolean scored; //true if the shots was successful and false if wasn't

    public Shot(String timeHappened, Player playerObj, Team teamObj, ShotType shotType, boolean scored) {
        super(timeHappened);
        this.playerObj = playerObj;
        this.teamObj = teamObj;
        this.shotType = shotType;
        this.scored = scored;
        this.actionDesc = this.formatActionDesc();
    }

    @Override
    public String formatActionDesc() {

        //we want to appear only the successful tries
        if (scored) {

            String playerName = this.playerObj.getName().charAt(0) + "." + playerObj.getSurname();

            switch (shotType) {
                case FREETHROW:
                    return ("Freethrow from " + playerName);
                case TWO_POINTER:
                    return ("+2 from " + playerName);
                case THREE_POINTER:
                    return ("+3 from " + playerName);
                default:
                    return "Undefined";
            }
        }
        return "Undefined";
    }
}
