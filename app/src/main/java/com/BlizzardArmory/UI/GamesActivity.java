package com.BlizzardArmory.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.BlizzardArmory.R;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.UserInformation;
import com.BlizzardArmory.UI.UI_diablo.D3Activity;
import com.BlizzardArmory.UI.UI_overwatch.OWActivity;
import com.BlizzardArmory.UI.UI_starcraft.SC2Activity;
import com.BlizzardArmory.UI.UI_warcraft.WoWActivity;
import com.BlizzardArmory.connection.ConnectionService;

import org.json.JSONObject;

import java.util.Objects;

public class GamesActivity extends AppCompatActivity {

    private BnOAuth2Helper bnOAuth2Helper;
    private BnOAuth2Params bnOAuth2Params;


    private ImageButton wowButton;
    private ImageButton sc2Button;
    private ImageButton d3Button;
    private ImageButton owButton;
    private TextView btag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        wowButton = findViewById(R.id.wowButton);
        sc2Button = findViewById(R.id.starcraft2Button);
        d3Button = findViewById(R.id.diablo3Button);
        owButton = findViewById(R.id.overwatchButton);
        btag = findViewById(R.id.btag_header);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        bnOAuth2Params = Objects.requireNonNull(this.getIntent().getExtras()).getParcelable(BnConstants.BUNDLE_BNPARAMS);
        assert bnOAuth2Params != null;
        bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
            JSONObject userInfo = new JSONObject(new ConnectionService(URLConstants.getBaseURLforUserInformation() +
                    URLConstants.END_USER_INFO_URL + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken(), GamesActivity.this).getStringJSONFromRequest().get(0));
            UserInformation.setBattleTag(userInfo.getString("battletag"));
            UserInformation.setUserID(userInfo.getString("id"));
        }catch (Exception e){
            Log.e("Error", e.toString());
        }

        btag.setText(UserInformation.getBattleTag());

        wowButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    if (ConnectionService.isConnected()) {
                        callNextActivity(WoWActivity.class);
                    }else{
                        ConstraintLayout constraintLayout = findViewById(R.id.background);
                        ConnectionService.showNoConnectionMessage(GamesActivity.this);
                    }
                }catch (Exception e){
                    Log.e("Error", e.toString());
                }
            }
        });

        d3Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    if (ConnectionService.isConnected()) {
                        callNextActivity(D3Activity.class);
                    }else{
                        ConstraintLayout constraintLayout = findViewById(R.id.background);
                        ConnectionService.showNoConnectionMessage(GamesActivity.this);
                    }
                }catch (Exception e){
                    Log.e("Error", e.toString());
                }
            }
        });

        sc2Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    if (ConnectionService.isConnected()) {
                        callNextActivity(SC2Activity.class);
                    }else{
                        ConstraintLayout constraintLayout = findViewById(R.id.background);
                        ConnectionService.showNoConnectionMessage(GamesActivity.this);
                    }
                }catch (Exception e){
                    Log.e("Error", e.toString());
                }

            }
        });

        owButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    if (ConnectionService.isConnected()) {
                        callNextActivity(OWActivity.class);
                    }else{
                        ConstraintLayout constraintLayout = findViewById(R.id.background);
                        ConnectionService.showNoConnectionMessage(GamesActivity.this);
                    }
                }catch (Exception e){
                    Log.e("Error", e.toString());
                }

            }
        });
    }

    @Override
    public void onBackPressed(){
            super.onBackPressed();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
    }

    private void callNextActivity(Class activity){
        final Intent intent = new Intent(this, activity);
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        startActivity(intent);
    }
}
