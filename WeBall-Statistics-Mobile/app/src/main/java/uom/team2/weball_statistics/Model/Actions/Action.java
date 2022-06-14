package uom.team2.weball_statistics.Model.Actions;

public class Action {
    protected String actionDesc;
    protected String timeHappened;
    protected BelongsTo belongsTo;
    protected String imageActionPath;

    public Action(){
        //Empty constructor required
    }

    public Action (String timeHappened, BelongsTo belongsTo, String imageActionPath) {
        this.timeHappened = timeHappened;
        this.belongsTo = belongsTo;
        this.imageActionPath = imageActionPath;
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

    public BelongsTo getBelongsTo() {
        return belongsTo;
    }
}
