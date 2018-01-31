package com.sebastian.scislak.pokerstatistics.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.sebastian.scislak.pokerstatistics.R;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.SettingPreference;

/**
 * Created by User on 2018-01-31.
 */

public class SettingActivity extends AppCompatActivity {
    public CheckBox scalingGraph;

    private SettingPreference setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);

        setting = new SettingPreference(this);

        scalingGraph = findViewById(R.id.scaling_graph_checkbox);
        if(setting.getScalingGraph())
            scalingGraph.setChecked(true);
        else
            scalingGraph.setChecked(false);
    }

    public void ClickCheckBox(View view) {
        if(scalingGraph.isChecked())
            setting.setScalingGraph(true);
        else
            setting.setScalingGraph(false);
    }
}
