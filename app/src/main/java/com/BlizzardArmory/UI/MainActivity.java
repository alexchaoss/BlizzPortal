package com.BlizzardArmory.UI;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.BlizzardArmory.R;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.connection.ConnectionService;
import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.activities.BnOAuthAccessTokenActivity;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static String selectedRegion = "";
    private SharedPreferences sharedPreferences;
    private BnOAuth2Params bnOAuth2Params;
    private String clientID = "";
    private String clientSecret = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner regions = findViewById(R.id.spinner);
        Button login = findViewById(R.id.buttonLogin);
        Button clearCredentials = findViewById(R.id.clear_credentials);
        String[] REGION_LIST = {"Select Region", "CN", "US", "EU", "KR", "TW"};
        clearCacheOlderThan30Days();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, REGION_LIST) {
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

        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        regions.setAdapter(arrayAdapter);

        regions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRegion = (String) parent.getItemAtPosition(position);
                ((TextView) view).setTextColor(Color.WHITE);
                ((TextView) view).setTextSize(20);
                ((TextView) view).setGravity(Gravity.CENTER);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER);
                ((TextView) parent.getChildAt(0)).setTextColor(0);
            }
        });

        login.setOnClickListener(view -> {
            if (selectedRegion.equals("Select Region")) {
                Toast.makeText(getApplicationContext(), "Please select a region", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    if (ConnectionService.isConnected()) {
                        DatabaseReference serverDatabase = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference serverRef = serverDatabase.child("servers");

                        serverRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                clientID = dataSnapshot.child("clientID").getValue(String.class);
                                clientSecret = dataSnapshot.child("clientSecret").getValue(String.class);

                                bnOAuth2Params = new BnOAuth2Params(clientID, clientSecret, selectedRegion.toLowerCase(),
                                        URLConstants.CALLBACK_URL, "Blizzard Games Profiles", BnConstants.SCOPE_WOW, BnConstants.SCOPE_SC2);

                                CreateToken(bnOAuth2Params);
                                setContentView(R.layout.activity_games);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.e("SERVER DATA", databaseError.getMessage());
                            }
                        });
                    } else {
                        showNoConnectionMessage(MainActivity.this, 0);
                    }
                } catch (Exception e) {
                    showNoConnectionMessage(MainActivity.this, 0);
                }
            }

        });


        clearCredentials.setOnClickListener(v -> {
            if (selectedRegion.equals("Select Region")) {
                Toast.makeText(getApplicationContext(), "Please select a region", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Login information cleared", Toast.LENGTH_SHORT).show();
                clearCredentials(bnOAuth2Params);
            }
        });
    }

    private void clearCacheOlderThan30Days() {
        if (getCacheDir().isDirectory()) {
            Log.i("Cache", "exist");
            File[] files = getCacheDir().listFiles();
            for (File file : files) {
                if (null != file) {
                    Log.i("File", "exist");
                    long lastModified = file.lastModified();

                    if (0 < lastModified) {
                        Date lastMDate = new Date(lastModified);
                        Date today = new Date(System.currentTimeMillis());

                        long diff = today.getTime() - lastMDate.getTime();
                        long diffDays = diff / (24 * 60 * 60 * 1000);
                        if (30 < diffDays) {
                            Log.i("File", "deleted");
                            file.delete();
                        }
                    }
                }
            }
        }
    }

    private void CreateToken(BnOAuth2Params bnOAuth2Params) {
        startOauthFlow(bnOAuth2Params);
    }

    private void startOauthFlow(final BnOAuth2Params bnOAuth2Params) {
        final Intent intent = new Intent(this, BnOAuthAccessTokenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        intent.putExtra(BnConstants.BUNDLE_REDIRECT_ACTIVITY, GamesActivity.class);
        startActivity(intent);
    }

    private void clearCredentials(final BnOAuth2Params bnOAuth2Params) {
        try {
            new BnOAuth2Helper(sharedPreferences, bnOAuth2Params).clearCredentials();
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().clear().apply();
        } catch (IOException e) {
            e.printStackTrace();
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

        Button button = new Button(context);
        if (responseCode == -10) {
            button.setText("RETRY");
        } else {
            button.setText("OK");
        }
        button.setTextSize(18);
        button.setTextColor(Color.WHITE);
        button.setGravity(Gravity.CENTER);
        button.setWidth(200);
        button.setLayoutParams(layoutParams);
        button.setBackground(context.getDrawable(R.drawable.buttonstyle));

        final AlertDialog dialog = builder.show();
        Objects.requireNonNull(dialog.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setLayout(800, 500);

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);

        linearLayout.addView(titleText);
        linearLayout.addView(messageText);
        linearLayout.addView(button);

        LinearLayout.LayoutParams layoutParamsWindow = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 20, 20, 20);

        dialog.addContentView(linearLayout, layoutParamsWindow);

        dialog.setOnCancelListener(DialogInterface::cancel);
    }

}
