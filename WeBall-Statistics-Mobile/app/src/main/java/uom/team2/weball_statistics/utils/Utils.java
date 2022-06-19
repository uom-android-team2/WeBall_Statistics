package uom.team2.weball_statistics.utils;

import android.content.Context;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

/*
 *@author Leonard Pepa ics20033
 */
public class Utils {

    public static String[] getStringArray(Context context, int id) {
        return context.getResources().getStringArray(id);
    }

    public static int getColor(Context context, int id) {
        return context.getResources().getColor(id);
    }

    public static void changeBackgroundColorInView(Context context, View view, int colorId) {
        view.setBackgroundColor(Utils.getColor(context, colorId));
    }

}
