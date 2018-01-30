package com.sebastian.scislak.pokerstatistics.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sebastian.scislak.pokerstatistics.R;

/**
 * Created by User on 2018-01-21.
 */

public class ShowData extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_show);

        getFragmentManager().beginTransaction().add(R.id.id_fragment, new FragmentList()).commit();
    }

    public void ListDataOfTheSessions(View view) {
        getFragmentManager().beginTransaction().replace(R.id.id_fragment, new FragmentList()).commit();
    }

    public void ChartDataOfTheSessions(View view) {
        getFragmentManager().beginTransaction().replace(R.id.id_fragment, new FragmentChart()).commit();
    }
}
