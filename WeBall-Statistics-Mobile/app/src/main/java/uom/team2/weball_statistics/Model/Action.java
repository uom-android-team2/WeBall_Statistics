package uom.team2.weball_statistics.Model;

public abstract class Action {
    private String actionDesc;
    private String timeHappened;

    //Method that will set the value that we want to appear to the action ui
    //set value to the actionDesc field cause this will returned to appear
    public abstract String formatActionDesc ();
}
