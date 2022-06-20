package uom.team2.weball_statistics.Model;

import java.io.Serializable;

public enum Status implements Serializable {
    UPCOMING, //Match not started yet
    ONGOING, //Match is in progress
    COMPLETED //Match finished
}
