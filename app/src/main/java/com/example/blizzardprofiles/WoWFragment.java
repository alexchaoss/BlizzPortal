package com.example.blizzardprofiles;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WoWFragment  extends Fragment {
    private static final String TAG = "WoWFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.wow_fragment, container, false);
        return view;
    }
}
