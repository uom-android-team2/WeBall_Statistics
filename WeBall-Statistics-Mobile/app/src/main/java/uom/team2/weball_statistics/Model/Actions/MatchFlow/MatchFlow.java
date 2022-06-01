package uom.team2.weball_statistics.Model.Actions.MatchFlow;

import uom.team2.weball_statistics.Model.Actions.Action;

public class MatchFlow extends Action {

    private FlowType flowType; //Start or pause

    public MatchFlow(String timeHappened, FlowType flowType) {
        super(timeHappened);
        this.flowType = flowType;
        this.actionDesc = this.formatActionDesc();
    }

    @Override
    public String formatActionDesc() {
        switch (this.flowType) {
            case Pause:
                return "Match Paused!";
            case Start:
                return  "Match Started!";
            default:
                return "Undefined";
        }
    }

    @Override
    public String getActionDesc() {
        return this.actionDesc;
    }

    @Override
    public String getTimeHappened() {
        return this.timeHappened;
    }
}
