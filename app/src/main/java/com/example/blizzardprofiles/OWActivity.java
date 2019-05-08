package com.example.blizzardprofiles;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;

class OWActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private BnOAuth2Helper bnOAuth2Helper;
    private BnOAuth2Params bnOAuth2Params;


    private ImageButton wowButton;
    private ImageButton sc2Button;
    private ImageButton d3Button;
    private TextView btag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ow_activity);
        wowButton = findViewById(R.id.wowButton);
        sc2Button = findViewById(R.id.starcraft2Button);
        d3Button = findViewById(R.id.diablo3Button);
        btag = findViewById(R.id.btag_header);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        bnOAuth2Params = this.getIntent().getExtras().getParcelable(BnConstants.BUNDLE_BNPARAMS);
        bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

        btag.setText(UserInformation.getBattleTag());

        //Button calls
        wowButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = ProgressDialog.show(OWActivity.this, "", "loading...");
                callNextActivity(WoWActivity.class);
            }
        });

        d3Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = ProgressDialog.show(OWActivity.this, "", "loading...");
                callNextActivity(D3Activity.class);
            }
        });

        sc2Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = ProgressDialog.show(OWActivity.this, "", "loading...");
                callNextActivity(SC2Activity.class);
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(OWActivity.this, GamesActivity.class);
        startActivity(intent);
    }

    private void callNextActivity(Class activity){
        final Intent intent = new Intent(this, activity);
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        startActivity(intent);
    }
}
