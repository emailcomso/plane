package com.example.s5;

import android.content.Context;
import android.content.SharedPreferences;

public class KsDB {
    private static String k0s = "k0s";
    private SharedPreferences preferences;

    public KsDB(Context context){
        String NAME = "k0s";
        preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public void setKsData(String data){
        preferences.edit().putString(KsDB.k0s, data).apply();
    }

    public String getKsData(){
        return preferences.getString(k0s, "");
    }
}
