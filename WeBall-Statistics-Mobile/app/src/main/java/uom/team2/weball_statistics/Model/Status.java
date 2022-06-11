package uom.team2.weball_statistics.Model;

import java.io.Serializable;

/*
 * @author Minas - Theodoros Charakopoulos ics20072
 */
public enum Status implements Serializable {
    UPCOMING, //Match not started yet
    ONGOING, //Match is in progress
    COMPLETED //Match finished
}
