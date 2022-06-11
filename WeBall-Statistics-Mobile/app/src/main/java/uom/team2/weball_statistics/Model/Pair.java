package uom.team2.weball_statistics.Model;

public class Pair<T> {

    public T teamLandlord;
    public T teamGuest;

    public Pair(T teamLandlord, T teamGuest) {
        this.teamLandlord = teamLandlord;
        this.teamGuest = teamGuest;
    }

    public Pair() {

    }
}
