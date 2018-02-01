package com.sebastian.scislak.pokerstatistics.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.sebastian.scislak.pokerstatistics.R;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.SessionDB;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.SettingPreference;

/**
 * Created by User on 2018-01-31.
 */

public class SettingActivity extends AppCompatActivity {
    public CheckBox scalingGraph;
    public CheckBox initEverySession;

    private SettingPreference setting;

    private boolean delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);

        delete = false;
        setting = new SettingPreference(this);

        scalingGraph = findViewById(R.id.scaling_graph_checkbox);
        initEverySession = findViewById(R.id.init_every_session);

        init();
    }

    private void init() {
        if(setting.getScalingGraph())
            scalingGraph.setChecked(true);
        else
            scalingGraph.setChecked(false);

        if (setting.getInitEverySession())
            initEverySession.setChecked(true);
        else
            initEverySession.setChecked(false);
    }

    public void ScalingChart(View view) {
        if(scalingGraph.isChecked())
            setting.setScalingGraph(true);
        else
            setting.setScalingGraph(false);
    }

    public void DeleteDatabase(View view) {
        if(!delete) {
            delete = true;
            Toast.makeText(this, "Click two time and delete database forever",Toast.LENGTH_LONG).show();
        }
        else {
            this.deleteDatabase(SessionDB.DB_NAME);
            Toast.makeText(this, "Deleted",Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void InitEverySession(View view) {
        if(initEverySession.isChecked())
            setting.setInitEverySession(true);
        else
            setting.setInitEverySession(false);
    }
}
