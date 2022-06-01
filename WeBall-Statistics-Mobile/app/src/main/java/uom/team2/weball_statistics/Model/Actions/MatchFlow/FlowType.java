package uom.team2.weball_statistics.Model.Actions.MatchFlow;

public enum FlowType {
    Start, //Can happen only one time at the start of the match
    Completed, //Can happen only one time at the end of the match
    Pause,
    Resume //Continue after match paused
}
