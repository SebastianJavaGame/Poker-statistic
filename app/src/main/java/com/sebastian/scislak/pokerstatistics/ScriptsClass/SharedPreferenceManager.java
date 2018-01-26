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
    //private static final String ITERATOR = "ITERATOR";
    public static final String NAME_TABLE = "NAME_TABLE";
    public static final String ACCOUNT_NAME = "ACCOUNT";
    public static final String TIME_NAME = "TIME";
    public static final String SEAT_NAME = "SEAT";
    public static final String TABLES_NAME = "SEAT";
    private static int MAX_COUNT_OPENED_TABLES = 10;

    private static final int ITERATOR_ID = 0;

    private SharedPreferences mainPref;

    public SharedPreferenceManager(Context context){
        mainPref = context.getSharedPreferences(PREF, 0);
        //iterator = mainPref.getInt(ITERATOR, 0);
    }

    public void AddTable(String idTable, float balanceAccount, int timeForMinutes, int countSeat, int countTables){
        if(ITERATOR_ID < MAX_COUNT_OPENED_TABLES) {
            mainPref.edit().putString(ITERATOR_ID + NAME_TABLE, idTable);
            mainPref.edit().putFloat(ITERATOR_ID + ACCOUNT_NAME, balanceAccount);
            mainPref.edit().putInt(ITERATOR_ID + TIME_NAME, timeForMinutes);
            mainPref.edit().putInt(ITERATOR_ID + SEAT_NAME, countSeat);
            mainPref.edit().putInt(ITERATOR_ID + TABLES_NAME, countTables);
            mainPref.edit().apply();
        }
            /*
            do {
                iterator++;
            } while (!mainPref.getString(iterator + ID_TABLE, "").equals(""));

        }else
            Toast.makeText(context, "Dicrease count opened the tables", Toast.LENGTH_SHORT).show();
            */
    }

    public String getTableName(){
        return mainPref.getString(ITERATOR_ID + NAME_TABLE, "");
    }

    public float getAccountBalance(){
        return mainPref.getFloat(ITERATOR_ID + ACCOUNT_NAME, 0);
    }

    public int getTimeSession(){
        return  mainPref.getInt(ITERATOR_ID + TIME_NAME, 0);
    }

    public int getCountSeat(){
        return  mainPref.getInt(ITERATOR_ID + SEAT_NAME, 9);
    }

    public int getCountTables(){
        return mainPref.getInt(ITERATOR_ID + TABLES_NAME, 1);
    }

    public void deleteSession(int idTournament) throws Exception {
        String id = String.valueOf(idTournament);
        for(int i = 0; i < MAX_COUNT_OPENED_TABLES; i++){
            if(mainPref.getString(i + NAME_TABLE, "").equals(id)){
                mainPref.edit().remove(i + NAME_TABLE);
                mainPref.edit().remove(i + ACCOUNT_NAME);
                mainPref.edit().remove(i + TIME_NAME);
                mainPref.edit().remove(i + SEAT_NAME);
                mainPref.edit().apply();
            }
        }
        throw new Exception();
    }

    public boolean doPrefIsEmpty(){
        for(int i = 0; i < MAX_COUNT_OPENED_TABLES; i++){
            if(!mainPref.getString(i + NAME_TABLE, "").equals(""))
                return false;
        }
        return true;
    }

    public void ClearPref(){
        mainPref.edit().clear().apply();
        //iterator = 0;
    }
}
