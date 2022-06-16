package uom.team2.weball_statistics.Model.Actions.Shots;

import android.content.Context;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.BelongsTo;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;

public class ShotComment extends Action {

    private Player playerObj; //The player (obj) who shots
    private Team teamObj; //The team (obj) that the player who shots belongs to
    private ShotType shotType;
    private boolean scored; //true if the shots was successful and false if wasn't
    private Assist assist;
    private Context context;

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

    public ShotComment(String timeHappened, BelongsTo belongsTo, Player playerObj, Team teamObj, ShotType shotType, boolean scored, Context context) {
        super(timeHappened, belongsTo, getMyImage(shotType));
        this.playerObj = playerObj;
        this.teamObj = teamObj;
        this.shotType = shotType;
        this.scored = scored;
        this.context = context;
        this.actionDesc = this.formatActionDesc();
    }

    //For Assist
    public ShotComment(String timeHappened, BelongsTo belongsTo, Player playerObj, Team teamObj, ShotType shotType, boolean scored, Assist assist, Context context) {
        super(timeHappened, belongsTo, getMyImage(shotType));
        this.playerObj = playerObj;
        this.teamObj = teamObj;
        this.shotType = shotType;
        this.scored = scored;
        this.assist = assist;
        this.context = context;
        this.actionDesc = this.formatActionDesc();
    }

    @Override
    protected String formatActionDesc() {

        String description = "";

        switch (this.shotType) {
                case FREETHROW:
                    description = this.scored ? this.commentGeneratorForAimedFreethrow() : this.commentGeneratorForMissedFreethrow();
                    break;
                case TWO_POINTER:
                    description = this.scored ? this.commentGeneratorForAimedTwoPointer() : this.commentGeneratorForMissedTwoPointer();
                    break;
                case THREE_POINTER:
                    description = this.scored ? this.commentGeneratorForAimedThreePointer() : this.commentGeneratorForMissedThreePointer();
                    break;
                default:
                    description  = "Undefined";
                    break;
        }

        if (this.assist != null && this.scored) {
            description = description + " with the assist of " + assist.getPlayerObj().getName().charAt(0) + "." + assist.getPlayerObj().getSurname();
        }

        return description;
    }

    private String commentGeneratorForMissedFreethrow() {
        String[] stringArray = this.context.getResources().getStringArray(R.array.freethrow_out);
        int randomStringPosition = (int)Math.floor(Math.random()*(stringArray.length));

        return stringArray[randomStringPosition] + " " + this.playerObj.getSurname();
    }

    private String commentGeneratorForAimedFreethrow() {
        String[] stringArray = this.context.getResources().getStringArray(R.array.freethrow_in);
        int randomStringPosition = (int)Math.floor(Math.random()*(stringArray.length));

        return stringArray[randomStringPosition] + " " + this.playerObj.getSurname();
    }

    private String commentGeneratorForMissedTwoPointer() {
        String[] stringArray = this.context.getResources().getStringArray(R.array.twopointer_out);
        int randomStringPosition = (int)Math.floor(Math.random()*(stringArray.length));

        return stringArray[randomStringPosition] + " " + this.playerObj.getSurname();
    }

    private String commentGeneratorForAimedTwoPointer() {
        String[] stringArray = this.context.getResources().getStringArray(R.array.twopointer_in);
        int randomStringPosition = (int)Math.floor(Math.random()*(stringArray.length));

        return stringArray[randomStringPosition] + " " + this.playerObj.getSurname();
    }

    private String commentGeneratorForMissedThreePointer() {
        String[] stringArray = this.context.getResources().getStringArray(R.array.threepointer_out);
        int randomStringPosition = (int)Math.floor(Math.random()*(stringArray.length));

        return stringArray[randomStringPosition] + " " + this.playerObj.getSurname();
    }

    private String commentGeneratorForAimedThreePointer() {
        String[] stringArray = this.context.getResources().getStringArray(R.array.threepointer_in);
        int randomStringPosition = (int)Math.floor(Math.random()*(stringArray.length));

        return stringArray[randomStringPosition] + " " + this.playerObj.getSurname();
    }
}
