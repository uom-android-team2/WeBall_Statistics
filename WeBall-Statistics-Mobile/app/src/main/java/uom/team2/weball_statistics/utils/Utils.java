package uom.team2.weball_statistics.utils;

import android.content.Context;

/*
 *@author Leonard Pepa ics20033
 */
public class Utils {

    public static String[] getStringArray(Context context, int id){
        return context.getResources().getStringArray(id);
    }

    public static int getColor(Context context, int id){
        return context.getResources().getColor(id);
    }


}
