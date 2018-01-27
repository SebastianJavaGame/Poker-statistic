package com.sebastian.scislak.pokerstatistics.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sebastian.scislak.pokerstatistics.R;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.MyTimePicker;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.SharedPreferenceManager;

/**
 * Created by User on 2018-01-26.
 */

public class SaveDataAfter extends MyTimePicker {
    private SharedPreferenceManager preferenceManager;

    private EditText accountBalance;
    private EditText playedHands;
    private EditText goingFlop;
    private EditText goingFlopWithoutBlinds;
    private EditText winning;
    private EditText winningWithoutShow;

    private int time;
    private int timeOut;

    private boolean isClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data_after);
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
        beforeAccountBalance.setText(String.valueOf(preferenceManager.getAccountBalance()));
        beforeCountTables.setText(String.valueOf(preferenceManager.getCountTables()));
        beforeMaxPlayer.setText(String.valueOf(preferenceManager.getCountSeat()));

        final int hour = preferenceManager.getTimeSession() /60;
        int minutes = preferenceManager.getTimeSession() % 60;

        if(minutes < 10)
            beforeTime.setText(hour + ":0" + minutes);
        else
            beforeTime.setText(hour + ":" + minutes);

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
            //TODO save to SQLite
        }
    }

    public void SavingInfo(View view) {
        Toast.makeText(this, "Fill in the during end of the session", Toast.LENGTH_SHORT).show();
    }

    public void DeleteSession(View view) {
        new SharedPreferenceManager(this).ClearPref();
        finish();
    }
}
