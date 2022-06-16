package uom.team2.weball_statistics.Model.Actions.MatchFlow;

import android.content.Context;
import android.content.res.Resources;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.BelongsTo;
import uom.team2.weball_statistics.R;

public class MatchFlowComment extends Action {

    private FlowType flowType; //Start or pause or resume or completed
    private Context context;

    private static String getMyImage(FlowType flowType) {
        switch (flowType) {
            case PAUSE:
                return "pause_30px";
            case RESUME:
                return "resume_button_30px";
            default:
                return "whistle2_30px"; //For anything else
        }
    }

    public MatchFlowComment(String timeHappened, FlowType flowType, Context context) {
        super(timeHappened, BelongsTo.GENERAL, getMyImage(flowType));
        this.flowType = flowType;
        this.context = context;
        this.actionDesc = this.formatActionDesc();
    }

    @Override
    protected String formatActionDesc() {
        switch (this.flowType) {
            case PAUSE:
                return this.commentGeneratorForPause();
            case RESUME:
                return this.commentGeneratorForResume();
            case START:
                return this.commentGeneratorForStart();
            case COMPLETED:
                return this.commentGeneratorForCompleted();
            default:
                return "Undefined";
        }
    }

    private String commentGeneratorForPause() {
        String[] stringArray = this.context.getResources().getStringArray(R.array.matchPause);
        int randomStringPosition = (int)Math.floor(Math.random()*(stringArray.length));

        return stringArray[randomStringPosition];
    }

    private String commentGeneratorForResume() {
        String[] stringArray = this.context.getResources().getStringArray(R.array.matchResume);
        int randomStringPosition = (int)Math.floor(Math.random()*(stringArray.length));

        return stringArray[randomStringPosition];
    }

    private String commentGeneratorForStart() {
        String[] stringArray = this.context.getResources().getStringArray(R.array.matchStart);
        int randomStringPosition = (int)Math.floor(Math.random()*(stringArray.length));

        return stringArray[randomStringPosition];
    }

    private String commentGeneratorForCompleted() {
        String[] stringArray = this.context.getResources().getStringArray(R.array.matchCompleted);
        int randomStringPosition = (int)Math.floor(Math.random()*(stringArray.length));

        return stringArray[randomStringPosition];
    }
}
