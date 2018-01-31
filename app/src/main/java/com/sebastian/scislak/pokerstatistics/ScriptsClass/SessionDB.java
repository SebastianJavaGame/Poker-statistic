package com.sebastian.scislak.pokerstatistics.ScriptsClass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 2018-01-22.
 */

public class SessionDB extends SQLiteOpenHelper {
    public static final String DB_NAME = "sessions.db";
    public static final int DB_VERSION = 1;

    public static final String CREATE_TABLE_SESSION =
            "CREATE TABLE " + Session.TABLE_NAME + " ("
                    + Session._ID + " INTEGER PRIMARY KEY,"
                    + Session.DATA + " TEXT,"
                    + Session.SESSION_NAME + " TEXT,"
                    + Session.ACCOUNT_BALANCE_BEFORE + " REAL,"
                    + Session.LENGTH_OF_THE_SESSION + " TEXT,"
                    + Session.COUNT_MAX_PLAYERS + " INTEGER,"
                    + Session.COUNT_TABLES + " INTEGER,"
                    + Session.ACCOUNT_BALANCE_AFTER + " REAL,"
                    + Session.PLAYED_HANDS + " INTEGER,"
                    + Session.GOING_TO_FLOP + " INTEGER,"
                    + Session.GOING_TO_FLOP_WITHOUT_BLINDS + " INTEGER,"
                    + Session.WINNING + " INTEGER,"
                    + Session.WINNING_WITHOUT_SHOW_HAND + " INTEGER"
                    + ")";

    public static final String DROP_TABLE_SESSION = "DROP TABLE IF EXISTS " + Session.TABLE_NAME;

    public SessionDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_SESSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE_SESSION);
        onCreate(sqLiteDatabase);
    }
}
