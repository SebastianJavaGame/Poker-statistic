package com.sebastian.scislak.pokerstatistics.ScriptsClass;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;

/**
 * Created by User on 2018-01-23.
 */

public class SharedPreferenceManager extends AppCompatActivity{
    private static final String PREF = "PREF";
    private static final String ITERATOR = "ITERATOR";
    public static final String ACCOUNT_NAME = "ACCOUNT";
    public static final String TIME_NAME = "TIME";
    public static final String SEAT_NAME = "SEAT";

    private static int iterator;

    private SharedPreferences mainPref;

    public SharedPreferenceManager(Context context){
        mainPref = context.getSharedPreferences(PREF, 0);
        iterator = mainPref.getInt(ITERATOR, 0);
    }

    public void AddTable(float balanceAccount, int timeForMinutes, int countSeat){
        mainPref.edit().putFloat(iterator + ACCOUNT_NAME, balanceAccount);
        mainPref.edit().putInt(iterator + TIME_NAME, timeForMinutes);
        mainPref.edit().putInt(iterator + SEAT_NAME, countSeat);
        mainPref.edit().apply();
    }

    public void ClearPref(){
        mainPref.edit().clear().apply();
    }

    public int getIterator(){
        return iterator;
    }
}
