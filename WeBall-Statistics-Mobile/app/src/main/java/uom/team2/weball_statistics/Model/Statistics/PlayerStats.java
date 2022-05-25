package uom.team2.weball_statistics.Model.Statistics;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

public class PlayerStats extends Stats {

    protected int playerId;
    protected int gamesPlayed;
    protected int totalMinutes;

    // This method is the constructor of class PlayerStats which initialize the parameters of this class.
    public PlayerStats(){


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

                if(key.equals("player_id") || key.equals("minutes")){
                    hashMapData.put(key, dataFromKey);

                }

                playerId = Integer.parseInt(hashMapData.get("player_id"));
                //totalMinutes = Integer.parseInt(hashMapData.get("minutes").substring(12,15));




                super.editJON(data);



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        super.editJON(data);


        System.out.println(totalSteels);


    }
}