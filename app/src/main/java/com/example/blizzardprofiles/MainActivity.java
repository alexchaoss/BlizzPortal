package com.example.blizzardprofiles;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    String selectedItem = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner regions = findViewById(R.id.spinner);
        String [] REGION_LIST={"Select Region", "CN", "US", "EU", "KR", "TW"};

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, REGION_LIST) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                }
                return true;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent){
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setBackgroundColor(Color.BLACK);
                tv.setTextSize(20);
                tv.setGravity(Gravity.CENTER);
                if(position == 0){
                    tv.setTextColor(Color.GRAY);
                }else{
                    tv.setTextColor(Color.WHITE);
                }
                return view;
            }
        };

        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        regions.setAdapter(arrayAdapter);

         regions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = (String) parent.getItemAtPosition(position);
                ((TextView) view).setTextColor(Color.WHITE);
                ((TextView) view).setTextSize(20);
                ((TextView) view).setGravity(Gravity.CENTER);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER);
                ((TextView) parent.getChildAt(0)).setTextColor(000000);
            }
        });


    }


}
