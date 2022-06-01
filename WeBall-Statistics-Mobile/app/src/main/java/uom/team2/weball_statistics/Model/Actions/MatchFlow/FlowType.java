package uom.team2.weball_statistics.Model.Actions.MatchFlow;

public enum FlowType {
    START, //Can happen only one time at the start of the match
    COMPLETED, //Can happen only one time at the end of the match
    PAUSE,
    RESUME //Continue after match paused
}
