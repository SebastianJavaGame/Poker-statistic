package com.sebastian.scislak.pokerstatistics.ScriptsClass;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by User on 2018-01-31.
 */

public class SettingPreference {
    public static final String NAME = "SETTING_PREF";
    public static final String SCALING_GRAPH = "SCALING_GRAPH";
    public static final String INIT_EVERY_SESSION = "INIT_EVERY_SESSION";

    private SharedPreferences preferences;

    public SettingPreference(Context context){
        preferences = context.getSharedPreferences(NAME, 0);
    }

    public void setScalingGraph(boolean scalingGraph){
        preferences.edit().putBoolean(SCALING_GRAPH, scalingGraph).apply();
    }

    public boolean getScalingGraph(){
        return preferences.getBoolean(SCALING_GRAPH, false);
    }

    public void setInitEverySession(boolean initEverySession){
        preferences.edit().putBoolean(INIT_EVERY_SESSION, initEverySession).apply();
    }

    public boolean getInitEverySession(){
        return preferences.getBoolean(INIT_EVERY_SESSION, false);
    }
}
