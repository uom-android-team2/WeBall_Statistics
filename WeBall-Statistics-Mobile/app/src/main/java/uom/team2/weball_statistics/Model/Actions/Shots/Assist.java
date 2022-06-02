package uom.team2.weball_statistics.Model.Actions.Shots;

import uom.team2.weball_statistics.Model.Player;

public class Assist {
    Player playerObj; //The player who makes the assist

    public Assist(Player playerObj) {
        this.playerObj = playerObj;
    }

    public Player getPlayerObj() {
        return playerObj;
    }

    public void setPlayerObj(Player playerObj) {
        this.playerObj = playerObj;
    }
}
