package uom.team2.weball_statistics.Model;

import com.google.firebase.database.Exclude;

import java.util.Map;

public interface firebaseModel {
    @Exclude
    public Map<String, Object> toMap();
}
