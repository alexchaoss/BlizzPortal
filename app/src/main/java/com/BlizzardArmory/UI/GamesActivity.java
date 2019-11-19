package com.BlizzardArmory.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.BlizzardArmory.R;
import com.BlizzardArmory.UI.UI_diablo.D3Activity;
import com.BlizzardArmory.UI.UI_overwatch.OWActivity;
import com.BlizzardArmory.UI.UI_starcraft.SC2Activity;
import com.BlizzardArmory.UI.UI_warcraft.WoWActivity;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.UserInformation;
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

                        wowButton.setOnClickListener(v -> callNextActivity(WoWActivity.class));

                        d3Button.setOnClickListener(v -> callNextActivity(D3Activity.class));

                        sc2Button.setOnClickListener(v -> callNextActivity(SC2Activity.class));

                        owButton.setOnClickListener(v -> callNextActivity(OWActivity.class));
                    },
                    error -> callErrorAlertDialog(error.networkResponse.statusCode));

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

    private void callErrorAlertDialog(int responseCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(GamesActivity.this, R.style.DialogTransparent);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 20, 0, 0);

        TextView titleText = new TextView(GamesActivity.this);

        titleText.setTextSize(20);
        titleText.setGravity(Gravity.CENTER_HORIZONTAL);
        titleText.setPadding(0, 20, 0, 20);
        titleText.setLayoutParams(layoutParams);
        titleText.setTextColor(Color.WHITE);

        TextView messageText = new TextView(GamesActivity.this);

        messageText.setGravity(Gravity.CENTER_HORIZONTAL);
        messageText.setLayoutParams(layoutParams);
        messageText.setTextColor(Color.WHITE);

        Button button = new Button(GamesActivity.this);

        titleText.setText("No Internet Connection");
        messageText.setText("Make sure that Wi-Fi or mobile data is turned on, then try again.");
        button.setText("RETRY");


        button.setTextSize(18);
        button.setTextColor(Color.WHITE);
        button.setGravity(Gravity.CENTER);
        button.setWidth(200);
        button.setLayoutParams(layoutParams);
        button.setBackground(GamesActivity.this.getDrawable(R.drawable.buttonstyle));

        final AlertDialog dialog = builder.show();
        Objects.requireNonNull(dialog.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setLayout(800, 500);

        LinearLayout linearLayout = new LinearLayout(GamesActivity.this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);

        linearLayout.addView(titleText);
        linearLayout.addView(messageText);
        linearLayout.addView(button);

        LinearLayout.LayoutParams layoutParamsWindow = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 20, 20, 20);

        dialog.addContentView(linearLayout, layoutParamsWindow);

        dialog.setOnCancelListener(dialog1 -> GamesActivity.this.recreate());

        button.setOnClickListener(v -> dialog.cancel());
    }
}
