package com.sebastian.scislak.pokerstatistics.Activities;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sebastian.scislak.pokerstatistics.R;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.SharedPreferenceManager;

/**
 * Created by User on 2018-01-21.
 */

public class SaveData extends AppCompatActivity{
    private float accountBalance;
    private int countMinutes;
    private int seatAtTheTable;
    private String idTable;

    private int hours;
    private int minute;

    private EditText accountBalanceEdit;
    private EditText timeEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        init();
    }

    private void init(){
        accountBalanceEdit = findViewById(R.id.balance_before);
        timeEdit = findViewById(R.id.time_before);

        accountBalanceEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    accountBalance = Float.valueOf(accountBalanceEdit.getText().toString());
                    accountBalanceEdit.setText(accountBalance + "$");
                }else
                    accountBalanceEdit.setText("");
            }
        });

        timeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(0);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == 0)
            return new TimePickerDialog(SaveData.this, timePickerListener, hours, minute, true);
        return null;
    }

    protected TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            hours = i;
            minute = i1;
            countMinutes = hours *60 + minute;
            timeEdit.setText(hours + ":" + minute);
            Toast.makeText(SaveData.this, "Last the session: " + countMinutes, Toast.LENGTH_SHORT).show();
        }
    };

    public void CountPlayers(View view) {
        switch (view.getId()){
            case R.id.sixSeat:
                seatAtTheTable = 6;
                break;
            case R.id.nineSeat:
                seatAtTheTable = 9;
                break;
            case R.id.tenSeat:
                seatAtTheTable = 10;
                break;
            default:
                break;
        }
    }

    public void PreSavingInfo(View view) {
    }

    public void PreSaving(View view) {
        SharedPreferenceManager preferenceManager = new SharedPreferenceManager(SaveData.this);
        preferenceManager.AddTable(idTable, accountBalance, countMinutes, seatAtTheTable);
    }
}
