package com.example.blizzardprofiles;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatSpinner regions = findViewById(R.id.spinner);
        String [] REGIONLIST={"CN", "US", "EU", "KR", "TW"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, REGIONLIST);
        regions.setAdapter(arrayAdapter);

    }
}
