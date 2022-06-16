package uom.team2.weball_statistics.Model.Actions.MatchFlow;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.BelongsTo;

public class MatchFlow extends Action {

    private FlowType flowType; //Start or pause or resume or completed

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

    public MatchFlow(String timeHappened, FlowType flowType) {
        super(timeHappened, BelongsTo.GENERAL, getMyImage(flowType));
        this.flowType = flowType;
        this.actionDesc = this.formatActionDesc();
    }

    @Override
    protected String formatActionDesc() {
        switch (this.flowType) {
            case PAUSE:
                return "Match Paused!";
            case RESUME:
                return "Match Continues!";
            case START:
                return  "Match Started!";
            case COMPLETED:
                return  "Match Completed";
            default:
                return "Undefined";
        }
    }
}
