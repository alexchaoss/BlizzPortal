package com.BlizzardArmory.ui.ui_overwatch;

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

public class OWPlatformChoiceDialog {

    private static String username = "";
    private static String platform = "";
    static public boolean myProfileChosen = false;

    private static void callOverWatchActivity(Activity activity, BnOAuth2Params bnOAuth2Params) {
        if (activity.getClass().getSimpleName().equalsIgnoreCase("OWActivity")) {
            activity.finish();
        }
        final Intent intent = new Intent(activity, OWActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("platform", platform);
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        activity.startActivity(intent);
    }

    public static void overwatchPrompt(Activity activity, BnOAuth2Params bnOAuth2Params) {
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
        titleText.setText("Choose your platform");

        TextView messageText = new TextView(activity);
        messageText.setTextSize(18);
        messageText.setGravity(Gravity.CENTER_HORIZONTAL);
        messageText.setLayoutParams(layoutParams);
        messageText.setTextColor(Color.WHITE);
        messageText.setText("Enter your Battle Tag");

        Button myProfile = new Button(activity);
        myProfile.setText("My PC Profile");
        myProfile.setTextSize(16);
        myProfile.setTextColor(Color.BLACK);
        myProfile.setGravity(Gravity.CENTER);
        myProfile.setLayoutParams(buttonParams);
        myProfile.setPadding(10, 0, 10, 0);
        myProfile.setBackground(activity.getDrawable(R.drawable.buttonstyle));

        Button pcButton = new Button(activity);
        pcButton.setText("PC");
        pcButton.setTextSize(16);
        pcButton.setTextColor(Color.WHITE);
        pcButton.setGravity(Gravity.CENTER);
        pcButton.setLayoutParams(buttonParams);
        pcButton.setBackground(activity.getDrawable(R.drawable.buttonstyle));

        Button xboxButton = new Button(activity);
        xboxButton.setText("XBL");
        xboxButton.setTextSize(16);
        xboxButton.setTextColor(Color.BLACK);
        xboxButton.setGravity(Gravity.CENTER);
        xboxButton.setLayoutParams(buttonParams);
        xboxButton.setBackground(activity.getDrawable(R.drawable.buttonstyle));

        Button psButton = new Button(activity);
        psButton.setText("PSN");
        psButton.setTextSize(16);
        psButton.setTextColor(Color.BLACK);
        psButton.setGravity(Gravity.CENTER);
        psButton.setLayoutParams(buttonParams);
        psButton.setBackground(activity.getDrawable(R.drawable.buttonstyle));

        Button okButton = new Button(activity);
        okButton.setText("OK");
        okButton.setTextSize(16);
        okButton.setTextColor(Color.WHITE);
        okButton.setGravity(Gravity.CENTER);
        okButton.setLayoutParams(buttonParams);
        okButton.setBackground(activity.getDrawable(R.drawable.buttonstyle));

        EditText usernameField = new EditText(activity);
        usernameField.setTextColor(Color.WHITE);
        usernameField.setTextSize(16);
        ColorStateList colorStateList = ColorStateList.valueOf(Color.WHITE);
        usernameField.setBackgroundTintList(colorStateList);

        LinearLayout buttonLayout = new LinearLayout(activity);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonLayout.setGravity(Gravity.CENTER);
        buttonLayout.addView(pcButton);
        buttonLayout.addView(xboxButton);
        buttonLayout.addView(psButton);

        final AlertDialog dialogOW = builderOW.create();
        Objects.requireNonNull(dialogOW.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialogOW.show();
        dialogOW.getWindow().setGravity(Gravity.CENTER);
        dialogOW.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialogOW.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialogOW.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setPadding(30, 30, 30, 30);

        linearLayout.addView(titleText);
        linearLayout.addView(buttonLayout);
        linearLayout.addView(myProfile);

        dialogOW.addContentView(linearLayout, layoutParams);

        linearLayout.addView(messageText);
        linearLayout.addView(usernameField);
        linearLayout.addView(okButton);

        pcButton.setOnClickListener(v -> {
            myProfileChosen = false;
            xboxButton.setTextColor(Color.BLACK);
            psButton.setTextColor(Color.BLACK);
            pcButton.setTextColor(Color.WHITE);
            myProfile.setTextColor(Color.BLACK);
            messageText.setText("Enter your Battle Tag");
            platform = "pc";
        });

        xboxButton.setOnClickListener(v -> {
            myProfileChosen = false;
            xboxButton.setTextColor(Color.WHITE);
            psButton.setTextColor(Color.BLACK);
            pcButton.setTextColor(Color.BLACK);
            myProfile.setTextColor(Color.BLACK);
            messageText.setText("Enter your Xbox live username");
            platform = "xbl";
        });

        psButton.setOnClickListener(v -> {
            myProfileChosen = false;
            xboxButton.setTextColor(Color.BLACK);
            psButton.setTextColor(Color.WHITE);
            pcButton.setTextColor(Color.BLACK);
            myProfile.setTextColor(Color.BLACK);
            messageText.setText("Enter your Playstation username");
            platform = "psn";
        });

        okButton.setOnClickListener(v -> {
            if (usernameField.getText().length() == 0 && !myProfileChosen) {
                Toast.makeText(activity.getApplicationContext(), "Please enter a username", Toast.LENGTH_SHORT).show();
            } else {
                if (!myProfileChosen) {
                    username = usernameField.getText().toString();
                }
                dialogOW.cancel();
                callOverWatchActivity(activity, bnOAuth2Params);
            }
        });

        myProfile.setOnClickListener(v -> {
            myProfileChosen = true;
            xboxButton.setTextColor(Color.BLACK);
            psButton.setTextColor(Color.BLACK);
            pcButton.setTextColor(Color.BLACK);
            myProfile.setTextColor(Color.WHITE);
            username = UserInformation.getBattleTag();
            platform = "pc";
        });
    }
}
