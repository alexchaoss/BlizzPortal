package com.BlizzardArmory.ui.ui_warcraft;

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
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.BlizzardArmory.R;

import java.util.Objects;

public class WoWCharacterSearchDialog {

    private static String characterClicked = "";
    private static String characterRealm = "";
    private static String url = "";

    private static void callCharacterFragment(Activity activity) {
        Bundle bundle = new Bundle();
        bundle.putString("name", characterClicked);
        bundle.putString("realm", characterRealm);
        bundle.putString("url", url);
        WoWCharacterFragment wowCharacterFragment = new WoWCharacterFragment();
        wowCharacterFragment.setArguments(bundle);
        FragmentManager fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.fragment, wowCharacterFragment);
        fragmentTransaction.addToBackStack(null).commit();
        ((FragmentActivity) activity).getSupportFragmentManager().executePendingTransactions();
    }

    public static void characterSearchPrompt(Activity activity) {
        AlertDialog.Builder builderOW = new AlertDialog.Builder(activity, R.style.DialogTransparent);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;

        TextView titleText = new TextView(activity);

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(10, 20, 10, 20);
        buttonParams.weight = 1;

        titleText.setTextSize(18);
        titleText.setGravity(Gravity.CENTER_HORIZONTAL);
        titleText.setPadding(0, 20, 0, 20);
        titleText.setLayoutParams(layoutParams);
        titleText.setTextColor(Color.WHITE);
        titleText.setText("Character Name");

        TextView messageText = new TextView(activity);
        messageText.setTextSize(18);
        messageText.setGravity(Gravity.CENTER_HORIZONTAL);
        messageText.setLayoutParams(layoutParams);
        messageText.setTextColor(Color.WHITE);
        messageText.setText("Realm");

        Button searchForCharacterButton = new Button(activity);
        searchForCharacterButton.setText("Go");
        searchForCharacterButton.setTextSize(16);
        searchForCharacterButton.setTextColor(Color.WHITE);
        searchForCharacterButton.setGravity(Gravity.CENTER);
        searchForCharacterButton.setLayoutParams(buttonParams);
        searchForCharacterButton.setPadding(10, 0, 10, 0);
        searchForCharacterButton.setBackground(activity.getDrawable(R.drawable.buttonstyle));

        EditText characterField = new EditText(activity);
        characterField.setTextColor(Color.WHITE);
        characterField.setTextSize(16);
        ColorStateList colorStateList = ColorStateList.valueOf(Color.WHITE);
        characterField.setBackgroundTintList(colorStateList);

        EditText realmField = new EditText(activity);
        realmField.setTextColor(Color.WHITE);
        realmField.setTextSize(16);
        realmField.setBackgroundTintList(colorStateList);

        final AlertDialog dialogWoW = builderOW.create();
        Objects.requireNonNull(dialogWoW.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialogWoW.show();
        dialogWoW.getWindow().setGravity(Gravity.CENTER);
        dialogWoW.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialogWoW.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialogWoW.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setPadding(30, 30, 30, 30);

        linearLayout.addView(titleText);
        linearLayout.addView(characterField);
        linearLayout.addView(messageText);
        linearLayout.addView(realmField);
        linearLayout.addView(searchForCharacterButton);

        dialogWoW.addContentView(linearLayout, layoutParams);

        searchForCharacterButton.setOnClickListener(v -> {
            if (characterField.getText().equals("") || realmField.getText().equals("")) {
                Toast.makeText(activity.getApplicationContext(), "Please enter the character name and the realm", Toast.LENGTH_SHORT).show();
            } else {
                characterClicked = characterField.getText().toString();
                characterRealm = realmField.getText().toString();
                dialogWoW.cancel();
                callCharacterFragment(activity);
            }
        });

    }


}
