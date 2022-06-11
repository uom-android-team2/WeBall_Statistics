package uom.team2.weball_statistics.Model;

import java.io.Serializable;

public class Pair<T> implements Serializable {

    public T teamLandlord;
    public T teamGuest;

    public Pair(T teamLandlord, T teamGuest) {
        this.teamLandlord = teamLandlord;
        this.teamGuest = teamGuest;
    }

    public Pair() {

    }
}
