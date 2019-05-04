package com.example.blizzardprofiles;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.activities.BnOAuthAccessTokenActivity;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final String CALLBACK_URL = "https://localhost";
    private SharedPreferences sharedPreferences;
    public static String selectedItem = "";
    public static String battleTag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner regions = findViewById(R.id.spinner);
        String [] REGION_LIST={"Select Region", "CN", "US", "EU", "KR", "TW"};

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, REGION_LIST) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                }
                return true;
            }

            /**
             * Set drop down menu color and text size.
             *
             * @param position
             * @param convertView
             * @param parent
             * @return
             */
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent){
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setBackgroundColor(Color.BLACK);
                tv.setTextSize(20);
                tv.setGravity(Gravity.CENTER);
                if(position == 0){
                    tv.setTextColor(Color.GRAY);
                }else{
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
                selectedItem = (String) parent.getItemAtPosition(position);
                ((TextView) view).setTextColor(Color.WHITE);
                ((TextView) view).setTextSize(20);
                ((TextView) view).setGravity(Gravity.CENTER);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER);
                ((TextView) parent.getChildAt(0)).setTextColor(000000);
            }
        });

        Button login = findViewById(R.id.buttonLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText btag = findViewById(R.id.battleTag);
                battleTag = btag.getText().toString();
                if(!battleTag.matches(Constants.BATTLE_TAG_REGEX)){
                    Toast.makeText(getApplicationContext(),"Invalid Battle Tag", Toast.LENGTH_SHORT).show();
                }else if(selectedItem.equals("Select Region")){
                    Toast.makeText(getApplicationContext(),"Please select a region", Toast.LENGTH_SHORT).show();
                }else{
                    CreateToken();
                    setContentView(R.layout.activity_games);
                }
            }
        });
    }

    private void CreateToken() {
        final BnOAuth2Params bnOAuth2Params = new BnOAuth2Params(OAuthTokens.WARCRAFT.getClientKey(), OAuthTokens.WARCRAFT.getSecretKey(),
                selectedItem.toLowerCase(), CALLBACK_URL,
                "Blizzard Profiles", BnConstants.SCOPE_WOW, BnConstants.SCOPE_SC2);
        startOauthFlow(bnOAuth2Params);
    }

    private void startOauthFlow(final BnOAuth2Params bnOAuth2Params) {
        final Intent intent = new Intent(this, BnOAuthAccessTokenActivity.class);
        // Send BnOAuth2Params
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        // Send redirect Activity
        intent.putExtra(BnConstants.BUNDLE_REDIRECT_ACTIVITY, GamesActivity.class);
        startActivity(intent);
    }

    private void clearCredentials(final BnOAuth2Params bnOAuth2Params)  {
        try {
            new BnOAuth2Helper(sharedPreferences, bnOAuth2Params).clearCredentials();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
