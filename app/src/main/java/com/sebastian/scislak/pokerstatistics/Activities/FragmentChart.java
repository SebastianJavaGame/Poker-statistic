package com.sebastian.scislak.pokerstatistics.Activities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sebastian.scislak.pokerstatistics.R;

/**
 * Created by User on 2018-01-29.
 */

public class FragmentChart extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_chart, container, false);
    }
}
