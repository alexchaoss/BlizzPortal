package com.BlizzardArmory.ui.ui_diablo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.BlizzardArmory.R;
import com.BlizzardArmory.UserInformation;
import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;

import java.util.Objects;

public class DiabloProfileSearchDialog {

    private static String battleTag = "";


    private static void callD3Activity(Activity activity, BnOAuth2Params bnOAuth2Params) {
        if (activity.getClass().getSimpleName().equalsIgnoreCase("D3Activity")) {
            activity.finish();
        }
        final Intent intent = new Intent(activity, D3Activity.class);
        intent.putExtra("battletag", battleTag);
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        activity.startActivity(intent);
    }

    public static void diabloPrompt(Activity activity, BnOAuth2Params bnOAuth2Params) {
        AlertDialog.Builder builderOW = new AlertDialog.Builder(activity, R.style.DialogTransparent);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;

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
        buttonLayout.addView(myProfile);
        buttonLayout.addView(searchButton);

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
        linearLayout.addView(buttonLayout);

        dialogD3.addContentView(linearLayout, layoutParams);

        searchButton.setOnClickListener(v -> {
            if (!battleTagField.getText().toString().matches(".*#[0-9]*")) {
                Toast.makeText(activity.getApplicationContext(), "Please enter a BattleTag", Toast.LENGTH_SHORT).show();
            } else {
                battleTag = battleTagField.getText().toString();
                dialogD3.cancel();
                callD3Activity(activity, bnOAuth2Params);
            }
        });

        myProfile.setOnClickListener(v -> {
            battleTag = UserInformation.getBattleTag();
            dialogD3.cancel();
            callD3Activity(activity, bnOAuth2Params);
        });
    }
}
