package com.sebastian.scislak.pokerstatistics.Activities;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.sebastian.scislak.pokerstatistics.R;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.Session;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.SessionDB;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.SettingPreference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by User on 2018-01-29.
 */

public class FragmentChart extends Fragment {
    private GraphView graphView;
    private LineGraphSeries<DataPoint> series;
    private SessionDB sessionDB;
    private SimpleDateFormat simpleDateFormat;

    private SettingPreference setting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment_chart, container, false);
        graphView = rootView.findViewById(R.id.graph_view);

        initSeries();

        graphView.getViewport().setScrollable(true);
        graphView.getViewport().setScrollableY(true);

        if(setting.getScalingGraph())
            graphView.getViewport().setScalableY(true);
        else
            graphView.getViewport().setScalableY(false);

        if(!setting.getInitEverySession()) {
            graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                @Override
                public String formatLabel(double value, boolean isValueX) {
                    if (isValueX) {
                        return simpleDateFormat.format(new Date((long) value));
                    } else {
                        return super.formatLabel(value, isValueX);
                    }
                }
            });

            graphView.getGridLabelRenderer().setNumHorizontalLabels(4);
        }else{
            graphView.getGridLabelRenderer().setNumHorizontalLabels(8);
        }

        graphView.addSeries(series);

        return rootView;
    }

    private void initSeries() {
        setting = new SettingPreference(getActivity());

        series = new LineGraphSeries<>();
        sessionDB = new SessionDB(getActivity());
        simpleDateFormat = new SimpleDateFormat("dd MMM");
        
        if (setting.getInitEverySession())
            initSeriesSessionId();
        else
            initSeriesDate();

    }

    private void initSeriesSessionId() {
        SQLiteDatabase sqLiteDatabase = sessionDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(Session.TABLE_NAME, new String[]{Session._ID, Session.ACCOUNT_BALANCE_AFTER}, null, null, null, null, null);

        int countColumn = cursor.getCount();

        while (cursor.moveToNext()){
            double sessionId = Double.valueOf(cursor.getString(cursor.getColumnIndex(Session._ID)));
            double after = (double) cursor.getFloat(cursor.getColumnIndex(Session.ACCOUNT_BALANCE_AFTER));

            series.appendData(new DataPoint(sessionId, after), true, countColumn);
        }
        cursor.close();
    }

    private void initSeriesDate() {
        SQLiteDatabase sqLiteDatabase = sessionDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(Session.TABLE_NAME, new String[]{Session.DATA, Session.ACCOUNT_BALANCE_AFTER}, null, null, null, null, null);

        int countColumn = cursor.getCount();

        while (cursor.moveToNext()) {
            String dateOfDB = cursor.getString(cursor.getColumnIndex(Session.DATA));
            int dayOfMonth = Integer.valueOf(dateOfDB.substring(0, 2));
            int month = Integer.valueOf(dateOfDB.substring(3, 5));

            //dayOfMonth--;
            month--;

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            calendar.set(Calendar.MONTH, month);

            Date date = calendar.getTime();

            double after = (double) cursor.getFloat(cursor.getColumnIndex(Session.ACCOUNT_BALANCE_AFTER));

            series.appendData(new DataPoint(date, after), true, countColumn);
            //Log.d("series", date + " " + after);
        }
        cursor.close();
    }
}
