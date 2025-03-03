package com.leebarcaglobal.worldtravel3d.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UtilClass {

    public static void setAdRemovalPurchased(Context context, boolean isPurchased) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("AD_S_REMOVED", isPurchased);
        editor.apply();
    }

    public static boolean isAdRemovalPurchased(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("AD_S_REMOVED", false);
    }
}
