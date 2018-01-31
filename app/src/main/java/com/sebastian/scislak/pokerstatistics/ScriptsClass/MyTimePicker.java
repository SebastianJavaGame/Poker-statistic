package com.sebastian.scislak.pokerstatistics.ScriptsClass;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by User on 2018-01-27.
 */

public abstract class MyTimePicker extends Activity {
    private int hours;
    private int minute;
    private int countMinutes;

    protected Button timeEdit;
    protected boolean isClicked;

    abstract protected void CalculateTime();

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == 0)
            return new TimePickerDialog(this, timePickerListener, hours, minute, true);
        return null;
    }

    protected TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(android.widget.TimePicker timePicker, int i, int i1) {
            isClicked = true;
            hours = i;
            minute = i1;
            countMinutes = hours *60 + minute;
            if(minute < 10)
                timeEdit.setText(hours + ":0" + minute);
            else
                timeEdit.setText(hours + ":" + minute);
            Toast.makeText(MyTimePicker.this, "Length of the session: " + countMinutes + " minutes", Toast.LENGTH_SHORT).show();

            CalculateTime();
        }
    };

    public int getCountMinutes() {
        return countMinutes;
    }

    public int getHours() {
        return hours;
    }

    public int getMinute() {
        return minute;
    }
}
