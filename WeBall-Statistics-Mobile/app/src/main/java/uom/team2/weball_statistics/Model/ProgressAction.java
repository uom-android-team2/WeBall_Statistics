package uom.team2.weball_statistics.Model;

public class ProgressAction {
    private String actionDescription;
    private String timeHappened;

    public ProgressAction(String actionDescription, String timeHappened) {
        this.actionDescription = actionDescription;
        this.timeHappened = timeHappened;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    public String getTimeHappened() {
        return timeHappened;
    }

    public void setTimeHappened(String timeHappened) {
        this.timeHappened = timeHappened;
    }
}
