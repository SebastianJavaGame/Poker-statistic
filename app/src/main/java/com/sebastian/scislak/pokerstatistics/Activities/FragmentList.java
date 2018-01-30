package com.sebastian.scislak.pokerstatistics.Activities;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.sebastian.scislak.pokerstatistics.R;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.MyAdapter;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.Session;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.SessionDB;
import com.sebastian.scislak.pokerstatistics.ScriptsClass.SessionItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2018-01-29.
 */

public class FragmentList extends Fragment {

    private static final String TAG = "FragmentList";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;

    private enum LayoutManagerType{
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    private SessionDB sessionDB;

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    protected MyAdapter myAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<SessionItem> sessionItemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment_list, container, false);
        rootView.setTag(TAG);

        mRecyclerView = rootView.findViewById(R.id.recycler_view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if(savedInstanceState != null){
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable(KEY_LAYOUT_MANAGER);
        }

        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        myAdapter = new MyAdapter(sessionItemList);
        mRecyclerView.setAdapter(myAdapter);

        return rootView;
    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType){
        int scrollPosition = 0;

        if(mRecyclerView.getLayoutManager() != null){
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        }

        switch (layoutManagerType){
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(),SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(outState);
    }

    private void initList() {
        int id = 0;
        sessionItemList = new ArrayList<>();
        sessionDB = new SessionDB(getActivity());

        SQLiteDatabase db = sessionDB.getReadableDatabase();

        Cursor cursor = db.query(Session.TABLE_NAME, new String[]{Session.DATA, Session.SESSION_NAME, Session.ACCOUNT_BALANCE_BEFORE, Session.LENGTH_OF_THE_SESSION,
                            Session.COUNT_MAX_PLAYERS, Session.COUNT_TABLES, Session.ACCOUNT_BALANCE_AFTER, Session.PLAYED_HANDS, Session.GOING_TO_FLOP,
                            Session.GOING_TO_FLOP_WITHOUT_BLINDS, Session.WINNING, Session.WINNING_WITHOUT_SHOW_HAND}, null, null,null, null, null);

        while (cursor.moveToNext()){
            id++;
            String date = cursor.getString(cursor.getColumnIndex(Session.DATA));
            String name = cursor.getString(cursor.getColumnIndex(Session.SESSION_NAME));
            String accountBefore = cursor.getString(cursor.getColumnIndex(Session.ACCOUNT_BALANCE_BEFORE));
            String length = cursor.getString(cursor.getColumnIndex(Session.LENGTH_OF_THE_SESSION));
            String maxSeat = cursor.getString(cursor.getColumnIndex(Session.COUNT_MAX_PLAYERS));
            String tables = cursor.getString(cursor.getColumnIndex(Session.COUNT_TABLES));
            String accountAfter = cursor.getString(cursor.getColumnIndex(Session.ACCOUNT_BALANCE_AFTER));
            String hands = cursor.getString(cursor.getColumnIndex(Session.PLAYED_HANDS));
            String goingToFlop = cursor.getString(cursor.getColumnIndex(Session.GOING_TO_FLOP));
            String withoutBlinds = cursor.getString(cursor.getColumnIndex(Session.GOING_TO_FLOP_WITHOUT_BLINDS));
            String winning = cursor.getString(cursor.getColumnIndex(Session.WINNING));
            String withoutShow = cursor.getString(cursor.getColumnIndex(Session.WINNING_WITHOUT_SHOW_HAND));

            float profit = Float.valueOf(accountAfter) - Float.valueOf(accountBefore);
            float averageProfit = profit / Integer.valueOf(tables);

            sessionItemList.add(new SessionItem(id, date, name, profit, averageProfit, Integer.valueOf(tables), Integer.valueOf(maxSeat),
                    Integer.valueOf(length), Integer.valueOf(hands), Integer.valueOf(goingToFlop),Integer.valueOf(withoutBlinds),
                    Integer.valueOf(winning),Integer.valueOf(withoutShow)));

            //Log.d(TAG, id + " " + date + " " + name + " " + accountBefore +" " + length +" " + maxSeat +" " + tables + " " + accountAfter + " " + hands + " " + goingToFlop + " " + withoutBlinds + " " + winning + " " + withoutShow);
        }
        cursor.close();
    }
}
