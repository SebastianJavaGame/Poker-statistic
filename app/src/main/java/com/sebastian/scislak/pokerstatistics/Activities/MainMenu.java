package com.sebastian.scislak.pokerstatistics.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sebastian.scislak.pokerstatistics.R;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.SessionDB;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.SharedPreferenceManager;

import java.util.Calendar;
import java.util.Date;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void OpenWriteStatistics(View view) {
        if(new SharedPreferenceManager(this).getTableName().toString().equals(""))
            startActivity(new Intent(this, SaveDataBefore.class));
        else {
            Toast.makeText(this, "Name: " + new SharedPreferenceManager(this).getTableName().toString(), Toast.LENGTH_SHORT).show();;
            startActivity(new Intent(this, SaveDataAfter.class));
        }
    }

    public void OpenShowStatistics(View view) {
        startActivity(new Intent(this, ShowData.class));
    }

    public void OpenPokerTips(View view) {
        Toast.makeText(this, "This option temporialiry unavaiable", Toast.LENGTH_SHORT).show();
    }

    public void Exit(View view) {
        System.exit(0);
    }

    public void Setting(View view) {
        startActivity(new Intent(this, SettingActivity.class));
    }
}
