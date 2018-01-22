package com.sebastian.scislak.pokerstatistics.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sebastian.scislak.pokerstatistics.R;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void OpenWriteStatistics(View view) {
        startActivity(new Intent(this, SaveData.class));
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
}
