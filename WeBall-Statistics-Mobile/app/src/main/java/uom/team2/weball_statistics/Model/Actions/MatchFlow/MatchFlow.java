package uom.team2.weball_statistics.Model.Actions.MatchFlow;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.BelongsTo;
import uom.team2.weball_statistics.Model.Match;

public class MatchFlow extends Action {

    private FlowType flowType; //Start or pause

    public MatchFlow(String timeHappened, FlowType flowType, Match match) {
        super(timeHappened, BelongsTo.GENERAL, match);
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
