package uom.team2.weball_statistics.Model.Statistics;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class TeamStats extends Stats {

    protected int teamId;
    private int totalMatches;
    private int wins;
    private int looses;

    public TeamStats()  {



    }

    @Override
    public void editJON(String data) {
        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            HashMap<String , String> hashMapData = new HashMap<String , String>();

            while(keys.hasNext()) {
                String key = keys.next();
                String dataFromKey = json.get(key).toString();
                hashMapData.put(key, dataFromKey);

                if(key.equals("match_id") || key.equals("total_matches") || key.equals("win") || key.equals("lose")){
                    hashMapData.put(key, dataFromKey);

                }

                teamId = Integer.parseInt(hashMapData.get("match_id"));
                totalMatches = Integer.parseInt(hashMapData.get("total_matches"));
                wins = Integer.parseInt(hashMapData.get("win"));
                looses = Integer.parseInt(hashMapData.get("lose"));

                 super.editJON(data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
         super.editJON(data);
    }
}
