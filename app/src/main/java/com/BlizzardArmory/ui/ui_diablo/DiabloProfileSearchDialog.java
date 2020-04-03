package com.BlizzardArmory.ui.ui_diablo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.BlizzardArmory.R;
import com.BlizzardArmory.ui.GamesActivity;
import com.BlizzardArmory.ui.MetricConversion;
import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;

import java.util.Objects;

public class DiabloProfileSearchDialog {

    private static String battleTag = "";
    private static String selectedRegion = "";


    private static void callD3Activity(Activity activity, BnOAuth2Params bnOAuth2Params) {
        if (activity.getClass().getSimpleName().equalsIgnoreCase("D3Activity")) {
            activity.finish();
        }
        final Intent intent = new Intent(activity, D3Activity.class);
        intent.putExtra("battletag", battleTag);
        intent.putExtra("region", selectedRegion);
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        activity.startActivity(intent);
    }

    public static void diabloPrompt(Activity activity, BnOAuth2Params bnOAuth2Params) {
        AlertDialog.Builder builderOW = new AlertDialog.Builder(activity, R.style.DialogTransparent);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;

        Spinner.LayoutParams layoutParamsSpinner = new Spinner.LayoutParams(Spinner.LayoutParams.MATCH_PARENT, MetricConversion.getDPMetric(40, activity));

        String[] REGION_LIST = {"Select Region", "CN", "US", "EU", "KR", "TW"};
        Spinner regions = new Spinner(activity);
        regions.setBackgroundResource(R.drawable.wow_spinner);
        regions.setGravity(Gravity.CENTER);
        regions.setLayoutParams(layoutParamsSpinner);

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_dropdown_item_1line, REGION_LIST) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setBackgroundColor(Color.BLACK);
                tv.setTextSize(18);
                tv.setGravity(Gravity.CENTER);
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
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
                selectedRegion = (String) parent.getItemAtPosition(position);
                try {
                    ((TextView) view).setTextColor(Color.WHITE);
                    ((TextView) view).setTextSize(18);
                    ((TextView) view).setGravity(Gravity.CENTER);
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER);
                ((TextView) parent.getChildAt(0)).setTextColor(0);
            }
        });

        TextView titleText = new TextView(activity);

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(10, 20, 10, 20);
        buttonParams.weight = 1;

        titleText.setTextSize(20);
        titleText.setGravity(Gravity.CENTER_HORIZONTAL);
        titleText.setPadding(0, 20, 0, 20);
        titleText.setLayoutParams(layoutParams);
        titleText.setTextColor(Color.WHITE);
        titleText.setText("Enter a BattleTag");

        Button myProfile = new Button(activity);
        myProfile.setText("My Profile");
        myProfile.setTextSize(16);
        myProfile.setTextColor(Color.WHITE);
        myProfile.setGravity(Gravity.CENTER);
        myProfile.setLayoutParams(buttonParams);
        myProfile.setPadding(10, 0, 10, 0);
        myProfile.setBackground(activity.getDrawable(R.drawable.buttonstyle));

        Button searchButton = new Button(activity);
        searchButton.setText("Search");
        searchButton.setTextSize(16);
        searchButton.setTextColor(Color.WHITE);
        searchButton.setGravity(Gravity.CENTER);
        searchButton.setLayoutParams(buttonParams);
        searchButton.setBackground(activity.getDrawable(R.drawable.buttonstyle));

        EditText battleTagField = new EditText(activity);
        battleTagField.setTextColor(Color.WHITE);
        battleTagField.setTextSize(16);
        ColorStateList colorStateList = ColorStateList.valueOf(Color.WHITE);
        battleTagField.setBackgroundTintList(colorStateList);

        LinearLayout buttonLayout = new LinearLayout(activity);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonLayout.setGravity(Gravity.CENTER);
        buttonLayout.addView(searchButton);
        buttonLayout.addView(myProfile);

        final AlertDialog dialogD3 = builderOW.create();
        Objects.requireNonNull(dialogD3.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialogD3.show();
        dialogD3.getWindow().setGravity(Gravity.CENTER);
        dialogD3.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialogD3.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialogD3.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setPadding(30, 30, 30, 30);

        linearLayout.addView(titleText);
        linearLayout.addView(battleTagField);
        linearLayout.addView(regions);
        linearLayout.addView(buttonLayout);

        dialogD3.addContentView(linearLayout, layoutParams);

        searchButton.setOnClickListener(v -> {
            if (!battleTagField.getText().toString().matches(".*#[0-9]*")) {
                Toast.makeText(activity.getApplicationContext(), "Please enter a BattleTag", Toast.LENGTH_SHORT).show();
            } else if (regions.getSelectedItem().toString().equalsIgnoreCase("Select Region")) {
                Toast.makeText(activity.getApplicationContext(), "Please enter the region", Toast.LENGTH_SHORT).show();
            } else {
                battleTag = battleTagField.getText().toString();
                dialogD3.cancel();
                callD3Activity(activity, bnOAuth2Params);
            }
        });

        myProfile.setOnClickListener(v -> {
            battleTag = GamesActivity.userInformation.getBattleTag();
            selectedRegion = "";
            dialogD3.cancel();
            callD3Activity(activity, bnOAuth2Params);
        });
    }
}
