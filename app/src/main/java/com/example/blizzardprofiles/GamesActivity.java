package com.example.blizzardprofiles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;

public class GamesActivity extends AppCompatActivity {

    private BnOAuth2Params bnOAuth2Params;
    private Button wowButton;
    private Button sc2Button;
    private Button d3Button;
    private Button owButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        bnOAuth2Params = this.getIntent().getExtras().getParcelable(BnConstants.BUNDLE_BNPARAMS);



    }
}
