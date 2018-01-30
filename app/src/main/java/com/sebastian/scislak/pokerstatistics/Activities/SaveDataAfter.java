package com.sebastian.scislak.pokerstatistics.Activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sebastian.scislak.pokerstatistics.R;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.MyTimePicker;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.Session;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.SessionDB;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.SharedPreferenceManager;

import java.util.Date;
import java.util.Locale;

/**
 * Created by User on 2018-01-26.
 */

public class SaveDataAfter extends MyTimePicker {
    private SharedPreferenceManager preferenceManager;
    private SessionDB sessionDB;

    private EditText accountBalance;
    private EditText playedHands;
    private EditText goingFlop;
    private EditText goingFlopWithoutBlinds;
    private EditText winning;
    private EditText winningWithoutShow;

    private int time;
    private int timeOut;
    private float accountBalanceValue;

    private boolean isClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data_after);
        sessionDB = new SessionDB(this);
        init();
    }

    private void init() {
        timeEdit = findViewById(R.id.time_edit_after);
        TextView beforeSessionName = findViewById(R.id.id_table_after);
        TextView beforeAccountBalance = findViewById(R.id.balance_after);
        TextView beforeTime = findViewById(R.id.time_after);
        TextView beforeCountTables = findViewById(R.id.count_tables_after);
        TextView beforeMaxPlayer = findViewById(R.id.max_player_after);

        accountBalance = findViewById(R.id.id_account_balance_after);
        playedHands = findViewById(R.id.id_played_hands);
        goingFlop = findViewById(R.id.id_going_to_flop);
        goingFlopWithoutBlinds = findViewById(R.id.id_going_to_flop_without_blinds);
        winning = findViewById(R.id.id_winning);
        winningWithoutShow = findViewById(R.id.id_winning_without_show_hands);

        preferenceManager = new SharedPreferenceManager(this);
        beforeSessionName.setText(preferenceManager.getTableName());
        String accountBalanceFormat = String.format(Locale.getDefault(), "%, .2f", preferenceManager.getAccountBalance());
        beforeAccountBalance.setText(String.valueOf(accountBalanceFormat + "$"));
        beforeCountTables.setText(String.valueOf(preferenceManager.getCountTables()));
        beforeMaxPlayer.setText(String.valueOf(preferenceManager.getCountSeat()));

        final int hour = preferenceManager.getTimeSession() /60;
        int minutes = preferenceManager.getTimeSession() % 60;

        if(minutes < 10)
            beforeTime.setText(String.valueOf(hour + ":0" + minutes));
        else
            beforeTime.setText(String.valueOf(hour + ":" + minutes));

        timeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveDataAfter.super.showDialog(0);
            }
        });

        timeEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    isClicked = true;
                    time = SaveDataAfter.super.getHours() * 60 + SaveDataAfter.super.getMinute();
                    if(preferenceManager.getTimeSession() < time)
                        timeOut = time - preferenceManager.getTimeSession();
                    else
                        timeOut = preferenceManager.getTimeSession() - time;
                }
            }
        });

        accountBalance.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !accountBalance.getText().toString().equals("")) {
                    accountBalanceValue = Float.valueOf(accountBalance.getText().toString());
                    String aroundNumber = String.format(Locale.getDefault(), "%, .2f", accountBalanceValue);
                    accountBalance.setText(String.valueOf(aroundNumber + "$"));
                }else
                    accountBalance.setText("");
            }
        });
    }

    private boolean checkIsFieldsEmpty(){
        if(!isClicked || accountBalance.getText().equals("") || playedHands.getText().equals("") || goingFlop.getText().equals("")
                || goingFlopWithoutBlinds.getText().equals("") || winning.getText().equals("") || winningWithoutShow.getText().equals(""))
            return true;
        else
            return false;
    }

    public void Saving(View view) {
        if(checkIsFieldsEmpty())
            Toast.makeText(this, "Fill in all fields and save again", Toast.LENGTH_SHORT).show();
        else{
            String date = getActualDate();
            addSession(date, preferenceManager.getTableName(), preferenceManager.getAccountBalance(), timeOut, preferenceManager.getCountSeat(),
                    preferenceManager.getCountTables(), accountBalanceValue, Integer.valueOf(playedHands.getText().toString()),
                    Integer.valueOf(goingFlop.getText().toString()), Integer.valueOf(goingFlopWithoutBlinds.getText().toString()),
                    Integer.valueOf(winning.getText().toString()), Integer.valueOf(winningWithoutShow.getText().toString()));
            Toast.makeText(this, "Saving complete", Toast.LENGTH_SHORT).show();
            preferenceManager.ClearPref();
            finish();
        }
    }

    public void SavingInfo(View view) {
        Toast.makeText(this, "Fill in the during end of the session", Toast.LENGTH_SHORT).show();
    }

    public void DeleteSession(View view) {
        new SharedPreferenceManager(this).ClearPref();
        finish();
    }

    private void addSession(String date, String name, float accountBefore, int length, int maxPlayers, int countTables, float accountAfter,
                            int playedHands, int goingFlop, int goingFlopWithoutBlinds, int winning, int winningWithoutShow){
        SQLiteDatabase db = sessionDB.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Session.DATA, date);
        cv.put(Session.SESSION_NAME, name);
        cv.put(Session.ACCOUNT_BALANCE_BEFORE, accountBefore);
        cv.put(Session.LENGTH_OF_THE_SESSION, length);
        cv.put(Session.COUNT_MAX_PLAYERS, maxPlayers);
        cv.put(Session.COUNT_TABLES, countTables);
        cv.put(Session.ACCOUNT_BALANCE_AFTER, accountAfter);
        cv.put(Session.PLAYED_HANDS, playedHands);
        cv.put(Session.GOING_TO_FLOP, goingFlop);
        cv.put(Session.GOING_TO_FLOP_WITHOUT_BLINDS, goingFlopWithoutBlinds);
        cv.put(Session.WINNING, winning);
        cv.put(Session.WINNING_WITHOUT_SHOW_HAND, winningWithoutShow);

        long id = db.insert(Session.TABLE_NAME, null, cv);
        Log.d("DataBaseWrite", "Number row of the table: " + id);
    }

    private String getActualDate(){
        String dateDay = String.format("%1$td", new Date());
        String dateMonth = String.format("%1$tm", new Date());

        return String.valueOf(dateDay + "." + dateMonth);
    }
}
