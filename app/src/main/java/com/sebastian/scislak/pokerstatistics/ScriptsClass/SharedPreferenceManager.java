package com.sebastian.scislak.pokerstatistics.ScriptsClass;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by User on 2018-01-23.
 */

public class SharedPreferenceManager{
    private static final String PREF = "PREF";
    private static final String ITERATOR = "ITERATOR";
    public static final String ID_TABLE = "ID_TABLE";
    public static final String ACCOUNT_NAME = "ACCOUNT";
    public static final String TIME_NAME = "TIME";
    public static final String SEAT_NAME = "SEAT";
    private static int MAX_COUNT_OPENED_TABLES = 10;

    private static int iterator;

    private SharedPreferences mainPref;
    private Context context;

    public SharedPreferenceManager(Context context){
        this.context = context;
        mainPref = context.getSharedPreferences(PREF, 0);
        iterator = mainPref.getInt(ITERATOR, 0);
    }

    public void AddTable(String idTable, float balanceAccount, int timeForMinutes, int countSeat){
        if(iterator < MAX_COUNT_OPENED_TABLES
                ) {
            mainPref.edit().putString(iterator + ID_TABLE, "#ID:" + idTable);
            mainPref.edit().putFloat(iterator + ACCOUNT_NAME, balanceAccount);
            mainPref.edit().putInt(iterator + TIME_NAME, timeForMinutes);
            mainPref.edit().putInt(iterator + SEAT_NAME, countSeat);
            mainPref.edit().apply();

            do {
                iterator++;
            } while (!mainPref.getString(iterator + ID_TABLE, "").equals(""));
        }else
            Toast.makeText(context, "Dicrease count opened the tables", Toast.LENGTH_SHORT).show();
    }

    public String getTableId(int idIterator){
        return mainPref.getString(idIterator + ID_TABLE, null);
    }

    public float getAccountBalance(String idIterator){
        return mainPref.getFloat(idIterator + ACCOUNT_NAME, 0f);
    }

    public int getTimeSession(String idIterator){
        return  mainPref.getInt(idIterator + TIME_NAME, 0);
    }

    public int getCountSeat(int idIterator){
        return  mainPref.getInt(idIterator + SEAT_NAME, 0);
    }

    public void ClearPref(){
        mainPref.edit().clear().apply();
        iterator = 0;
    }

    public static int getIterator(){
        return iterator;
    }
}
