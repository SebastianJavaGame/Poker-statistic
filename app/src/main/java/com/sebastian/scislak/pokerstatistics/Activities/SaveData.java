package com.sebastian.scislak.pokerstatistics.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.sebastian.scislak.pokerstatistics.R;

/**
 * Created by User on 2018-01-21.
 */

public class SaveData extends AppCompatActivity{
    private float accountBalance;
    private int countMinutes;
    private int seatAtTheTable;

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
                }else{
                    if(accountBalance != 0f)
                        accountBalanceEdit.setText(String.valueOf(accountBalance));
                    else
                        accountBalanceEdit.setText("");
                }
            }
        });

        timeEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() == 2) //TODO add boolean when be separator
                    timeEdit.setText(timeEdit.getText() + ":");
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

    public void PreSavingInfo(View view) {
    }

    public void PreSaving(View view) {
    }
}
