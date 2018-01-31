package com.sebastian.scislak.pokerstatistics.ScriptsClass;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by User on 2018-01-31.
 */

public class SettingPreference {
    public static final String NAME = "SETTING_PREF";
    public static final String SCALING_GRAPH = "SCALING_GRAPH";

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
}
