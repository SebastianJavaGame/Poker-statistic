package com.sebastian.scislak.pokerstatistics.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sebastian.scislak.pokerstatistics.R;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.MyTimePicker;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.SharedPreferenceManager;

import java.util.Locale;

/**
 * Created by User on 2018-01-21.
 */

public class SaveDataBefore extends MyTimePicker{
    private float accountBalance;
    private int seatAtTheTable;

    private EditText accountBalanceEdit;
    private EditText tableNumber;
    private EditText countTablesEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data_before);
        seatAtTheTable = 9;
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
                    String aroundNumber = String.format(Locale.getDefault(), "%, .2f", accountBalance);
                    accountBalanceEdit.setText(String.valueOf(aroundNumber + "$"));
                }else
                    accountBalanceEdit.setText("");
            }
        });

        timeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveDataBefore.super.showDialog(0);
            }
        });
    }

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
        if(accountBalanceEdit.getText().toString().equals("") || timeEdit.getText().toString().equals("") || Integer.valueOf(countTablesEdit.getText().toString()) < 1 || !isClicked)
            return true;
        else
            return false;
    }

    public void PreSavingInfo(View view) {
        Toast.makeText(this, "Fill in the fields before starting a session", Toast.LENGTH_LONG).show();
    }

    public void PreSaving(View view) {
        if(!checkToFieldsIsEmpty()) {
            if(tableNumber.getText().length() < 3 || tableNumber.getText().length() > 7)
                Toast.makeText(this, "Name or ID tournament is too short. Min 3 chars and Max 7 char", Toast.LENGTH_LONG).show();
            else {
                new SharedPreferenceManager(SaveDataBefore.this).AddTable(tableNumber.getText().toString(), accountBalance, super.getCountMinutes(), seatAtTheTable, Integer.valueOf(countTablesEdit.getText().toString()));
                Toast.makeText(this, "Saving complete", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void CalculateTime() {
    }
}
