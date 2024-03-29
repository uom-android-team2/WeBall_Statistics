package uom.team2.weball_statistics.Model.Statistics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class TeamStatsLive extends TeamStats {

    private int match_id;

    public TeamStatsLive(){

    }

    public TeamStatsLive(int successful_effort, int total_effort, int successful_freethrow, int total_freethrow, int successful_twopointer, int total_twopointer, int successful_threepointer, int total_threepointer, int steal, int rebound, int assist, int block, int foul, int turnover, int teamId, int totalMatches, int wins, int loses, int match_id) {
        super(successful_effort, total_effort, successful_freethrow, total_freethrow, successful_twopointer, total_twopointer, successful_threepointer, total_threepointer, steal, rebound, assist, block, foul, turnover, teamId, totalMatches, wins, loses);
        this.match_id = match_id;
    }

    public void editJON(String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            String jsonStr = jsonArray.getJSONObject(0).toString() ;
            JSONObject json = new JSONObject(jsonStr);
            Iterator<String> keys = json.keys();
            HashMap<String , String> hashMapData = new HashMap<String , String>();
            while(keys.hasNext()) {
                String key = keys.next();
                String dataFromKey = json.get(key).toString();

                if(key.equals("match_id")){
                    hashMapData.put(key, dataFromKey);
                }
            }
            match_id = Integer.parseInt(hashMapData.get("match_id"));
            super.editJON(json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getMatchId() {
        return match_id;
    }
}
