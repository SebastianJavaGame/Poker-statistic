package com.sebastian.scislak.pokerstatistics.Activities;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sebastian.scislak.pokerstatistics.R;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.SharedPreferenceManager;

/**
 * Created by User on 2018-01-21.
 */

public class SaveDataBefore extends AppCompatActivity{
    private float accountBalance;
    private int countMinutes;
    private int seatAtTheTable;
    private int countTables;
    private String sessionName;

    private int hours;
    private int minute;

    private EditText accountBalanceEdit;
    private EditText timeEdit;
    private EditText tableNumber;
    private EditText countTablesEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data_before);
        countTables = 1;
        seatAtTheTable = 9;
        sessionName = "MySession";
        accountBalance = 50.00f;
        init();
    }

    private void init(){
        accountBalanceEdit = findViewById(R.id.balance_before);
        timeEdit = findViewById(R.id.time_before);
        tableNumber = findViewById(R.id.id_table);
        countTablesEdit = findViewById(R.id.count_tables);

        accountBalanceEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !accountBalanceEdit.getText().toString().equals("")) {
                    accountBalance = Float.valueOf(accountBalanceEdit.getText().toString());
                    String aroundNumber = String.format("%, .2f", accountBalance);
                    accountBalanceEdit.setText(aroundNumber + "$");
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

        tableNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    sessionName = tableNumber.getText().toString();
                }
            }
        });

        countTablesEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocused) {
                if(!hasFocused){
                    countTables = Integer.decode(countTablesEdit.getText().toString());
                }
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == 0)
            return new TimePickerDialog(SaveDataBefore.this, timePickerListener, hours, minute, true);
        return null;
    }

    protected TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            hours = i;
            minute = i1;
            countMinutes = hours *60 + minute;
            timeEdit.setText(hours + ":" + minute);
            Toast.makeText(SaveDataBefore.this, "Length of the session: " + countMinutes + " minutes", Toast.LENGTH_SHORT).show();
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

    public boolean checkToFieldsIsEmpty(){
        if(accountBalanceEdit.getText().equals("") || timeEdit.getText().equals("") || countTables < 1)
            return true;
        else
            return false;
    }

    public void PreSavingInfo(View view) {
        Toast.makeText(this, "Fill in the fields before starting a session", Toast.LENGTH_LONG).show();
    }

    public void PreSaving(View view) {
        if(!checkToFieldsIsEmpty()) {
            if(sessionName.length() < 5)
                Toast.makeText(this, "Name or ID tournament is too short. Min 5 chars", Toast.LENGTH_LONG).show();
            else {
                new SharedPreferenceManager(SaveDataBefore.this).AddTable(sessionName, accountBalance, countMinutes, seatAtTheTable, countTables);
            }
        }
    }
}
