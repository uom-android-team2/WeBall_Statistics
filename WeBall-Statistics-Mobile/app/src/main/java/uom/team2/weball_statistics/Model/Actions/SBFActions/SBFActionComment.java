package uom.team2.weball_statistics.Model.Actions.SBFActions;

import android.content.Context;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.BelongsTo;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.configuration.Config;

public class SBFActionComment extends Action {

    private Player playerObj; //The player (obj) who does the action
    private Team teamObj; //The team (obj) that the player who does the action belongs to
    private SBFActionType sbfActionType; //STEAL, BLOCK OR FOUL
    private Context context;

    private static String getMyImage(SBFActionType sbfActionType) {
        switch (sbfActionType) {
            case STEAL:
                return "basketball_player_steal_30px";
            case BLOCK:
                return "block_30px";
            case FOUL:
                return "foul_30px";
            default:
                return "whistle_30px";
        }
    }

    public SBFActionComment(String timeHappened, BelongsTo belongsTo, Player playerObj, Team teamObj, SBFActionType sbfActionType, Context context) {
        super(timeHappened, belongsTo, getMyImage(sbfActionType));
        this.playerObj = playerObj;
        this.teamObj = teamObj;
        this.sbfActionType = sbfActionType;
        this.context = context;
        this.actionDesc = this.formatActionDesc();
    }

    @Override
    protected String formatActionDesc() {

        switch (this.sbfActionType) {
            case STEAL:
                return this.commentGeneratorForSteal();
            case BLOCK:
                return this.commentGeneratorForBlock();
            case FOUL:
                return this.commentGeneratorForFoul();
            default:
                return "Undefined";
        }
    }

    private String commentGeneratorForSteal() {
        String[] stringArray = this.context.getResources().getStringArray(R.array.steal);
        int randomStringPosition = (int)Math.floor(Math.random()*(stringArray.length));

        return stringArray[randomStringPosition] + " " + this.playerObj.getSurname();
    }

    private String commentGeneratorForBlock() {
        String[] stringArray = this.context.getResources().getStringArray(R.array.block);
        int randomStringPosition = (int)Math.floor(Math.random()*(stringArray.length));

        return stringArray[randomStringPosition] + " " + this.playerObj.getSurname();
    }

    private String commentGeneratorForFoul() {
        String[] stringArray = this.context.getResources().getStringArray(R.array.foul);
        int randomStringPosition = (int)Math.floor(Math.random()*(stringArray.length));

        return stringArray[randomStringPosition] + " " + this.playerObj.getSurname();
    }
}
