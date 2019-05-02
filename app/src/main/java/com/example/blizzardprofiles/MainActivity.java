package com.example.blizzardprofiles;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    String [] SPINNERLIST={"CN", "US", "EU", "KR", "TW"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        MaterialBetterSpinner betterSpinner = findViewById(R.id.android_material_design_spinner);
        betterSpinner.setBackgroundColor(Color.parseColor("#0F1014"));
        betterSpinner.setAdapter(arrayAdapter);

    }
}
