package com.BlizzardArmory.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.BlizzardArmory.R;
import com.BlizzardArmory.UI.UI_diablo.D3Activity;
import com.BlizzardArmory.UI.UI_overwatch.OWActivity;
import com.BlizzardArmory.UI.UI_starcraft.SC2Activity;
import com.BlizzardArmory.UI.UI_warcraft.WoWActivity;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.UserInformation;
import com.BlizzardArmory.connection.ConnectionService;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;

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

    private JSONObject userInfo = new JSONObject();

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

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024 * 5); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        RequestQueue requestQueue = new RequestQueue(cache, network);
        requestQueue.start();


        try {
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforUserInformation() +
                    URLConstants.END_USER_INFO_URL + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken(), null,
                    response -> {
                        userInfo = response;
                        try {
                            UserInformation.setBattleTag(userInfo.getString("battletag"));
                            UserInformation.setUserID(userInfo.getString("id"));

                            Log.i("Btag", UserInformation.getBattleTag());
                            Log.i("USER_ID", UserInformation.getUserID());

                        } catch (Exception e) {
                            Log.e("Error-test", e.toString());
                        }

                        btag.setText(UserInformation.getBattleTag());

                        wowButton.setOnClickListener(v -> {
                            try {
                                if (ConnectionService.isConnected()) {
                                    callNextActivity(WoWActivity.class);
                                } else {
                                    //ConnectionService.showNoConnectionMessage(GamesActivity.this);
                                }
                            } catch (Exception e) {
                                Log.e("Error", e.toString());
                            }
                        });

                        d3Button.setOnClickListener(v -> {
                            try {
                                if (ConnectionService.isConnected()) {
                                    callNextActivity(D3Activity.class);
                                } else {
                                    //ConnectionService.showNoConnectionMessage(GamesActivity.this);
                                }
                            } catch (Exception e) {
                                Log.e("Error", e.toString());
                            }
                        });

                        sc2Button.setOnClickListener(v -> {
                            try {
                                if (ConnectionService.isConnected()) {
                                    callNextActivity(SC2Activity.class);
                                } else {
                                    //ConnectionService.showNoConnectionMessage(GamesActivity.this);
                                }
                            } catch (Exception e) {
                                Log.e("Error", e.toString());
                            }

                        });

                        owButton.setOnClickListener(v -> {
                            try {
                                if (ConnectionService.isConnected()) {
                                    callNextActivity(OWActivity.class);
                                } else {
                                    //ConnectionService.showNoConnectionMessage(GamesActivity.this);
                                }
                            } catch (Exception e) {
                                Log.e("Error", e.toString());
                            }

                        });
                    },
                    error -> Log.i("test", error.toString()));

            requestQueue.add(jsonRequest);

        } catch (Exception e) {
            Log.e("Error-test", e.toString());
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void callNextActivity(Class activity) {
        final Intent intent = new Intent(this, activity);
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        startActivity(intent);
    }
}
