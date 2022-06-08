package uom.team2.weball_statistics.Model.Actions;

import uom.team2.weball_statistics.Model.Match;

public class Action {
    protected int id; //Action id
    protected String actionDesc;
    protected String timeHappened;
    protected BelongsTo belongsTo;

    public Action(){
        //Empty constructor required
    }

    public Action (String timeHappened, BelongsTo belongsTo, Match match) {
        this.id = match.getActionsCount();
        this.timeHappened = timeHappened;
        this.belongsTo = belongsTo;
    }

    //Method that will set the value that we want to appear to the action ui
    //set value to the actionDesc field cause this will returned to appear
    protected String formatActionDesc (){
        return "";
    }

    public String getActionDesc() {
        return this.actionDesc;
    };

    public String getTimeHappened() {
        return this.timeHappened;
    };

    public int getId() {
        return id;
    }

    public BelongsTo getBelongsTo() {
        return belongsTo;
    }
}
