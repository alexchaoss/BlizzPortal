package com.BlizzardArmory.ui;


import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.BlizzardArmory.R;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.connection.InternetCheck;
import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.activities.BnOAuthAccessTokenActivity;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static String selectedRegion = "";
    private BnOAuth2Params bnOAuth2Params;
    private String clientID = "";
    private String clientSecret = "";
    private LinearLayout settingsLayout;
    private WebView paypalWebView;
    public static String locale = "en_US";
    private String[] REGION_LIST;
    private String selectedLanguage;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner regions = findViewById(R.id.spinner);
        Spinner language = findViewById(R.id.spinner_language);
        Button login = findViewById(R.id.buttonLogin);
        Button clearCredentials = findViewById(R.id.clear_credentials);
        Button rate = findViewById(R.id.rate);
        paypalWebView = findViewById(R.id.webview);
        REGION_LIST = new String[]{"Select Region", "CN", "US", "EU", "KR", "TW"};
        String[] LANGUAGE_LIST = {"Select Language", "English", "Spanish", "Portuguese", "French", "Russian", "Dutch", "Italian", "Korean", "Chinese", "Taiwanese"};

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!sharedPreferences.contains("locale")) {
            sharedPreferences.edit().putString("locale", "en_US").apply();
            locale = "en_US";
        } else {
            locale = sharedPreferences.getString("locale", "en_US");
        }

        ArrayAdapter regionAdapter = setAdapater(REGION_LIST);

        regionAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        regions.setAdapter(regionAdapter);

        ArrayAdapter languageAdapter = setAdapater(LANGUAGE_LIST);

        languageAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        language.setAdapter(languageAdapter);

        spinnerSelector(regions);
        spinnerSelector(language);

        ImageView settings = findViewById(R.id.settings);
        settingsLayout = findViewById(R.id.settings_layout);
        settings.setOnClickListener(v -> settingsLayout.setVisibility(View.VISIBLE));

        ImageView closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(v -> settingsLayout.setVisibility(View.GONE));

        Button button = findViewById(R.id.licenses);
        OssLicensesMenuActivity.setActivityTitle(getString(R.string.custom_license_title));
        button.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, OssLicensesMenuActivity.class)));

        rate.setOnClickListener(v -> {
            openAppStoreForReview();
        });


        Button donateButton = findViewById(R.id.donation);
        donateButton.setOnClickListener(v -> {
            String url = "https://paypal.me/astpierredev";
            paypalWebView.loadUrl(url);
        });

        openLoginToBattlenet(login);

        clearCredentials.setOnClickListener(v -> {
            showNoConnectionMessage(MainActivity.this, 100);
        });
    }

    @NotNull
    private ArrayAdapter setAdapater(String[] REGION_LIST) {
        return new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, REGION_LIST) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setBackgroundColor(Color.BLACK);
                tv.setTextSize(20);
                tv.setGravity(Gravity.CENTER);
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.WHITE);
                }
                return view;
            }
        };
    }

    private void spinnerSelector(Spinner spinner) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (Arrays.asList(REGION_LIST).contains(parent.getItemAtPosition(position))) {
                    selectedRegion = (String) parent.getItemAtPosition(position);
                } else {
                    selectedLanguage = (String) parent.getItemAtPosition(position);
                    Log.i("lang", selectedLanguage);
                    setLocale();
                }

                try {
                    ((TextView) view).setTextColor(Color.WHITE);
                    ((TextView) view).setTextSize(20);
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
    }

    private void setLocale() {
        switch (selectedLanguage) {
            case "English":
                locale = "en_US";
                break;
            case "Spanish":
                locale = "es_ES";
                break;
            case "French":
                locale = "fr_FR";
                break;
            case "Russian":
                locale = "ru_RU";
                break;
            case "Dutch":
                locale = "de_DE";
                break;
            case "Portuguese":
                locale = "pt_PT";
                break;
            case "Italian":
                locale = "it_IT";
                break;
            case "Korean":
                locale = "ko_KR";
                break;
            case "Chinese":
                locale = "zh_CN";
                break;
            case "Taiwanese":
                locale = "zh_TW";
                break;
            default: {
                if (sharedPreferences.contains("locale")) {
                    locale = sharedPreferences.getString("locale", "en_US");
                }
            }
        }
        sharedPreferences.edit().putString("locale", locale).apply();
    }

    private void openLoginToBattlenet(Button login) {
        login.setOnClickListener(view -> {
            if (selectedRegion.equals("Select Region")) {
                Toast.makeText(getApplicationContext(), "Please select a region", Toast.LENGTH_SHORT).show();
            } else {
                new InternetCheck(internet -> {
                    if (internet) {
                        try {
                            getClientSecret();
                        } catch (Exception e) {
                            showNoConnectionMessage(MainActivity.this, 0);
                        }
                    } else {
                        showNoConnectionMessage(MainActivity.this, 0);
                    }
                });
            }
        });
    }

    private void getClientSecret() {
        DatabaseReference serverDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference serverRef = serverDatabase.child("servers");
        serverRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clientID = dataSnapshot.child("clientID").getValue(String.class);
                clientSecret = dataSnapshot.child("clientSecret").getValue(String.class);

                bnOAuth2Params = new BnOAuth2Params(clientID, clientSecret, selectedRegion.toLowerCase(),
                        URLConstants.CALLBACK_URL, "Blizzard Games Profiles", BnConstants.SCOPE_WOW, BnConstants.SCOPE_SC2);

                startOauthFlow(bnOAuth2Params);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("SERVER DATA", databaseError.getMessage());
            }
        });
    }

    private void openAppStoreForReview() {
        Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
        }
    }

    private void startOauthFlow(final BnOAuth2Params bnOAuth2Params) {
        final Intent intent = new Intent(this, BnOAuthAccessTokenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        intent.putExtra(BnConstants.BUNDLE_REDIRECT_ACTIVITY, GamesActivity.class);
        startActivity(intent);
    }

    private void clearCredentials() {

        ((ActivityManager) this.getSystemService(ACTIVITY_SERVICE)).clearApplicationUserData();
    }

    @Override
    public void onBackPressed() {
        if (settingsLayout.getVisibility() == View.VISIBLE) {
            settingsLayout.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }

    public void showNoConnectionMessage(final Context context, int responseCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogTransparent);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 20, 0, 0);

        TextView titleText = new TextView(context);
        titleText.setText("No Internet Connection");
        titleText.setTextSize(20);
        titleText.setGravity(Gravity.CENTER_HORIZONTAL);
        titleText.setPadding(0, 20, 0, 20);
        titleText.setLayoutParams(layoutParams);
        titleText.setTextColor(Color.WHITE);

        TextView messageText = new TextView(context);
        messageText.setText("Make sure that Wi-Fi or mobile data is turned on, then try again.");
        messageText.setGravity(Gravity.CENTER_HORIZONTAL);
        messageText.setLayoutParams(layoutParams);
        messageText.setTextColor(Color.WHITE);

        LinearLayout buttonLayout = new LinearLayout(context);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonLayout.setGravity(Gravity.CENTER);

        Button button = new Button(context);
        button.setTextSize(18);
        button.setTextColor(Color.WHITE);
        button.setGravity(Gravity.CENTER);
        button.setWidth(200);
        button.setLayoutParams(layoutParams);
        button.setBackground(context.getDrawable(R.drawable.buttonstyle));

        Button button2 = new Button(context);
        button2.setTextSize(18);
        button2.setTextColor(Color.WHITE);
        button2.setGravity(Gravity.CENTER);
        button2.setWidth(200);
        button2.setLayoutParams(layoutParams);
        button2.setBackground(context.getDrawable(R.drawable.buttonstyle));

        if (responseCode == -10) {
            button.setText("RETRY");
            buttonLayout.addView(button);
        } else if (responseCode == 100) {
            button.setText("Yes");
            button2.setText("Cancel");
            titleText.setText("Warning");
            buttonLayout.addView(button);
            buttonLayout.addView(button2);
            messageText.setText("You are about to clear the application data, this will close the application.\n Are you sure you want to continue?");
        } else {
            buttonLayout.addView(button);
            button.setText("OK");
        }

        final AlertDialog dialog = builder.show();
        Objects.requireNonNull(dialog.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setLayout(800, 500);


        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);


        linearLayout.addView(titleText);
        linearLayout.addView(messageText);
        linearLayout.addView(buttonLayout);

        LinearLayout.LayoutParams layoutParamsWindow = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 20, 20, 20);

        dialog.addContentView(linearLayout, layoutParamsWindow);
        if (responseCode == 100) {
            button2.setOnClickListener(v -> dialog.cancel());
            button.setOnClickListener(v -> clearCredentials());
        } else {
            button.setOnClickListener(v -> dialog.cancel());
            dialog.setOnCancelListener(DialogInterface::cancel);
        }
    }

}
