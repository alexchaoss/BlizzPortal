package com.BlizzardArmory.ui.ui_warcraft;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.BlizzardArmory.R;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.ui.MainActivity;
import com.BlizzardArmory.ui.MetricConversion;
import com.BlizzardArmory.warcraft.media.Media;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Objects;

public class WoWCharacterSearchDialog {

    private static String characterClicked = "";
    private static String characterRealm = "";
    private static Media media;
    private static String url = "";
    private static String selectedRegion = "";
    private static RequestQueue requestQueue;

    private static void callCharacterFragment(Activity activity, Fragment fragment) {
        if (fragment != null && fragment.isVisible()) {
            fragment.getFragmentManager().beginTransaction().remove(fragment).commit();
        }
        String mediaString = new Gson().toJson(media);
        Bundle bundle = new Bundle();
        bundle.putString("character", characterClicked);
        bundle.putString("realm", characterRealm);
        bundle.putString("media", mediaString);
        bundle.putString("url", url);
        bundle.putString("region", selectedRegion);
        WoWCharacterFragment wowCharacterFragment = new WoWCharacterFragment();
        wowCharacterFragment.setArguments(bundle);
        FragmentManager fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.fragment, wowCharacterFragment);
        fragmentTransaction.addToBackStack(null).commit();
        ((FragmentActivity) activity).getSupportFragmentManager().executePendingTransactions();
    }

    public static void characterSearchPrompt(Activity activity, Fragment fragment) {
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
        linearLayout.addView(regions);
        linearLayout.addView(searchForCharacterButton);

        dialogWoW.addContentView(linearLayout, layoutParams);

        searchForCharacterButton.setOnClickListener(v -> {
            if (characterField.getText().toString().equals("")) {
                Toast.makeText(activity.getApplicationContext(), "Please enter the character name", Toast.LENGTH_SHORT).show();
            } else if (realmField.getText().toString().equals("")) {
                Toast.makeText(activity.getApplicationContext(), "Please enter the realm", Toast.LENGTH_SHORT).show();
            } else if (regions.getSelectedItem().toString().equalsIgnoreCase("Select Region")) {
                Toast.makeText(activity.getApplicationContext(), "Please enter the region", Toast.LENGTH_SHORT).show();
            } else {
                characterClicked = characterField.getText().toString().toLowerCase();
                characterRealm = realmField.getText().toString().toLowerCase().replace(" ", "-");

                Cache cache = new DiskBasedCache(Objects.requireNonNull(activity).getCacheDir(), 1024 * 1024 * 5); // 1MB cap
                Network network = new BasicNetwork(new HurlStack());
                requestQueue = new RequestQueue(cache, network);
                requestQueue.start();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
                BnOAuth2Params bnOAuth2Params = Objects.requireNonNull(Objects.requireNonNull(activity).getIntent().getExtras()).getParcelable(BnConstants.BUNDLE_BNPARAMS);
                assert bnOAuth2Params != null;
                final BnOAuth2Helper bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);
                try {
                    JsonObjectRequest jsonRequestMedia = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI(MainActivity.selectedRegion.toLowerCase())
                            + URLConstants.MEDIA_QUERY.replace("zone", MainActivity.selectedRegion.toLowerCase()).replace("realm", characterRealm.toLowerCase())
                            .replace("charactername", characterClicked) + bnOAuth2Helper.getAccessToken(), null,
                            response -> {
                                Gson gsonMedia = new GsonBuilder().create();
                                media = gsonMedia.fromJson(response.toString(), Media.class);
                                dialogWoW.cancel();
                                callCharacterFragment(activity, fragment);
                            }, error -> {
                        callCharacterFragment(activity, fragment);
                    });
                    requestQueue.add(jsonRequestMedia);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}
