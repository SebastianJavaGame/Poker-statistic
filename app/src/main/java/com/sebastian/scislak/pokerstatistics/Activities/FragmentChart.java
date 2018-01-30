package com.sebastian.scislak.pokerstatistics.Activities;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.sebastian.scislak.pokerstatistics.R;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.Session;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.SessionDB;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by User on 2018-01-29.
 */

public class FragmentChart extends Fragment {
    private GraphView graphView;
    private LineGraphSeries<DataPoint> series;
    private SessionDB sessionDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment_chart, container, false);
        graphView = rootView.findViewById(R.id.graph_view);

        initSeries();

        graphView.getViewport().setScalableY(true);
        graphView.addSeries(series);

        return rootView;
    }

    private void initSeries(){
        series = new LineGraphSeries<>();
        sessionDB = new SessionDB(getActivity());

        SQLiteDatabase sqLiteDatabase = sessionDB.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(Session.TABLE_NAME, new String[]{Session.DATA, Session.ACCOUNT_BALANCE_AFTER}, null, null, null, null, null);

        int countColumn = cursor.getCount();

        while (cursor.moveToNext()){
            String dateOfDB = cursor.getString(cursor.getColumnIndex(Session.DATA));
            int dayOfMonth = Integer.valueOf(dateOfDB.substring(0, 2));
            int month = Integer.valueOf(dateOfDB.substring(3, 5));

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            calendar.set(Calendar.MONTH, month);

            Date date = calendar.getTime();

            double after = (double)cursor.getFloat(cursor.getColumnIndex(Session.ACCOUNT_BALANCE_AFTER));

            series.appendData(new DataPoint(date, after), true, countColumn);
            Log.d("series", date + " " + after);
        }
        cursor.close();
    }
}
