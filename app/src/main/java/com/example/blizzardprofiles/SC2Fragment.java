package com.example.blizzardprofiles;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SC2Fragment extends Fragment {
    private static final String TAG = "SC2Fragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.sc2_fragment, container, false);
        return view;
    }
}
