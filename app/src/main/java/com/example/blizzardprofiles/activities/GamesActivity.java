package com.example.blizzardprofiles.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.example.blizzardprofiles.URLConstants;
import com.example.blizzardprofiles.connection.ConnectionService;
import com.example.blizzardprofiles.R;
import com.example.blizzardprofiles.UserInformation;

import net.sf.json.*;

import java.io.IOException;

public class GamesActivity extends AppCompatActivity {

    private final String USER_INFO_URL = "/oauth/userinfo";


    private SharedPreferences prefs;
    private BnOAuth2Helper bnOAuth2Helper;
    private BnOAuth2Params bnOAuth2Params;


    private ImageButton wowButton;
    private ImageButton sc2Button;
    private ImageButton d3Button;
    private ImageButton owButton;
    private TextView btag;
    JSONObject userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        wowButton = findViewById(R.id.wowButton);
        sc2Button = findViewById(R.id.starcraft2Button);
        d3Button = findViewById(R.id.diablo3Button);
        owButton = findViewById(R.id.overwatchButton);
        btag = findViewById(R.id.btag_header);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        bnOAuth2Params = this.getIntent().getExtras().getParcelable(BnConstants.BUNDLE_BNPARAMS);
        bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

        try {
            userInfo = (JSONObject) JSONSerializer.toJSON(ConnectionService.getStringJSONFromRequest(getBaseURL(), URLConstants.END_USER_INFO_URL, bnOAuth2Helper.getAccessToken()));
        }catch (IOException e){
            Log.e("Error", e.toString());
        }

        UserInformation.setBattleTag(userInfo.getString("battletag"));
        UserInformation.setUserID(userInfo.getString("id"));

        btag.setText(UserInformation.getBattleTag());

        //Button calls
        wowButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showProgressBar();
                callNextActivity(WoWActivity.class);
            }
        });

        d3Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showProgressBar();
                callNextActivity(D3Activity.class);
            }
        });

        sc2Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showProgressBar();
                callNextActivity(SC2Activity.class);
            }
        });

        owButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showProgressBar();
                callNextActivity(OWActivity.class);
            }
        });
    }

    private void showProgressBar() {
        ProgressBar progressBar = new ProgressBar(GamesActivity.this,null,android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100,100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        ConstraintLayout layout = findViewById(R.id.background);
        layout.addView(progressBar,params);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed(){
            super.onBackPressed();
            Intent intent = new Intent(GamesActivity.this, MainActivity.class);
            startActivity(intent);
    }

    private void callNextActivity(Class activity){
        final Intent intent = new Intent(this, activity);
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        startActivity(intent);
    }

    private String getBaseURL(){
        if(MainActivity.selectedRegion.equals("cn")){
            return URLConstants.BASE_URL_CN_USER_INFO;
            }else {
            return URLConstants.END_USER_INFO_URL;
        }
    }
}
