package com.example.blizzardprofiles.UI.UI_overwatch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.example.blizzardprofiles.R;
import com.example.blizzardprofiles.UserInformation;
import com.example.blizzardprofiles.UI.UI_starcraft.SC2Activity;
import com.example.blizzardprofiles.UI.UI_diablo.D3Activity;
import com.example.blizzardprofiles.UI.UI_warcraft.WoWActivity;

public class OWActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private BnOAuth2Helper bnOAuth2Helper;
    private BnOAuth2Params bnOAuth2Params;


    private ImageButton wowButton;
    private ImageButton sc2Button;
    private ImageButton d3Button;
    private TextView btag;
    private RelativeLayout loadingCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ow_activity);
        wowButton = findViewById(R.id.wowButton);
        sc2Button = findViewById(R.id.starcraft2Button);
        d3Button = findViewById(R.id.diablo3Button);
        btag = findViewById(R.id.btag_header);
        loadingCircle = findViewById(R.id.loadingCircle);
        loadingCircle.setVisibility(View.VISIBLE);
        btag.setText(UserInformation.getBattleTag());

        btag.setText(UserInformation.getBattleTag());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        new PrepareData().execute();

        //Button calls
        wowButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                callNextActivity(WoWActivity.class);
            }
        });

        d3Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                callNextActivity(D3Activity.class);
            }
        });

        sc2Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                callNextActivity(SC2Activity.class);
            }
        });
    }

    private void callNextActivity(Class activity){
        final Intent intent = new Intent(this, activity);
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        startActivity(intent);
    }

    private class PrepareData extends AsyncTask<Void, Void, Void> {

        protected void onPreExecute(Void param) {
            super.onPreExecute();
            // THIS WILL DISPLAY THE PROGRESS CIRCLE

        }

        protected Void doInBackground(Void... param) {
            prefs = PreferenceManager.getDefaultSharedPreferences(OWActivity.this);
            bnOAuth2Params = OWActivity.this.getIntent().getExtras().getParcelable(BnConstants.BUNDLE_BNPARAMS);
            bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);
            return null;
        }

        protected void onPostExecute(Void param) {
            super.onPostExecute(param);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            loadingCircle.setVisibility(View.GONE);
        }
    }
}
