package com.BlizzardArmory.ui.ui_overwatch;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.BlizzardArmory.R;
import com.BlizzardArmory.connection.oauth.BnConstants;
import com.BlizzardArmory.connection.oauth.BnOAuth2Params;
import com.BlizzardArmory.ui.GamesActivity;

import java.util.Objects;

/**
 * The type Ow platform choice dialog.
 */
public class OWPlatformChoiceDialog {

    private static String username = "";
    private static String platform = "";
    /**
     * The My profile chosen.
     */
    static public boolean myProfileChosen;

    private static final String OK = "OK";
    private static final String PLATFORM_CHOICE = "Choose your platform";
    private static final String BATTLE_TAG = "Enter your Battle Tag";
    private static final String MY_PROFILE = "My PC Profile";
    private static final String PC = "PC";
    private static final String XBL = "XBL";
    private static final String PSN = "PSN";
    private static final String ENTER_BTAG = "Enter your Battle Tag";
    private static final String ENTER_XBL = "Enter your Xbox live username";
    private static final String ENTER_PSN = "Enter your Playstation username";

    private static void callOverWatchActivity(Activity activity, FragmentManager fragmentManager, BnOAuth2Params bnOAuth2Params) {
        Fragment fragment = new OWActivity();
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putString("platform", platform);
        bundle.putParcelable(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.fragment, fragment, "overwatchfragment").commit();
        fragmentManager.executePendingTransactions();
    }

    /**
     * Overwatch prompt.
     *
     * @param activity       the activity
     * @param bnOAuth2Params the bn o auth 2 params
     */
    public static void overwatchPrompt(Activity activity, FragmentManager fragmentManager, BnOAuth2Params bnOAuth2Params) {
        myProfileChosen = false;
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
        titleText.setText(PLATFORM_CHOICE);

        TextView messageText = new TextView(activity);
        messageText.setTextSize(18);
        messageText.setGravity(Gravity.CENTER_HORIZONTAL);
        messageText.setLayoutParams(layoutParams);
        messageText.setTextColor(Color.WHITE);
        messageText.setText(BATTLE_TAG);

        Button myProfile = new Button(activity);
        myProfile.setText(MY_PROFILE);
        myProfile.setTextSize(16);
        myProfile.setTextColor(Color.BLACK);
        myProfile.setGravity(Gravity.CENTER);
        myProfile.setLayoutParams(buttonParams);
        myProfile.setPadding(10, 0, 10, 0);
        myProfile.setBackground(activity.getDrawable(R.drawable.buttonstyle));

        Button pcButton = new Button(activity);
        pcButton.setText(PC);
        pcButton.setTextSize(16);
        pcButton.setTextColor(Color.WHITE);
        pcButton.setGravity(Gravity.CENTER);
        pcButton.setLayoutParams(buttonParams);
        pcButton.setBackground(activity.getDrawable(R.drawable.buttonstyle));

        Button xboxButton = new Button(activity);
        xboxButton.setText(XBL);
        xboxButton.setTextSize(16);
        xboxButton.setTextColor(Color.BLACK);
        xboxButton.setGravity(Gravity.CENTER);
        xboxButton.setLayoutParams(buttonParams);
        xboxButton.setBackground(activity.getDrawable(R.drawable.buttonstyle));

        Button psButton = new Button(activity);
        psButton.setText(PSN);
        psButton.setTextSize(16);
        psButton.setTextColor(Color.BLACK);
        psButton.setGravity(Gravity.CENTER);
        psButton.setLayoutParams(buttonParams);
        psButton.setBackground(activity.getDrawable(R.drawable.buttonstyle));

        Button okButton = new Button(activity);
        okButton.setText(OK);
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
            messageText.setText(ENTER_BTAG);
            platform = "pc";
        });

        xboxButton.setOnClickListener(v -> {
            myProfileChosen = false;
            xboxButton.setTextColor(Color.WHITE);
            psButton.setTextColor(Color.BLACK);
            pcButton.setTextColor(Color.BLACK);
            myProfile.setTextColor(Color.BLACK);
            messageText.setText(ENTER_XBL);
            platform = "xbl";
        });

        psButton.setOnClickListener(v -> {
            myProfileChosen = false;
            xboxButton.setTextColor(Color.BLACK);
            psButton.setTextColor(Color.WHITE);
            pcButton.setTextColor(Color.BLACK);
            myProfile.setTextColor(Color.BLACK);
            messageText.setText(ENTER_PSN);
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
                callOverWatchActivity(activity, fragmentManager, bnOAuth2Params);
            }
        });

        myProfile.setOnClickListener(v -> {
            myProfileChosen = true;
            xboxButton.setTextColor(Color.BLACK);
            psButton.setTextColor(Color.BLACK);
            pcButton.setTextColor(Color.BLACK);
            myProfile.setTextColor(Color.WHITE);
            username = GamesActivity.Companion.getUserInformation().getBattleTag();
            platform = "pc";
        });
    }
}
