package com.BlizzardArmory.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.BlizzardArmory.R;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.UserInformation;
import com.BlizzardArmory.ui.ui_diablo.D3Activity;
import com.BlizzardArmory.ui.ui_overwatch.OWActivity;
import com.BlizzardArmory.ui.ui_starcraft.SC2Activity;
import com.BlizzardArmory.ui.ui_warcraft.WoWActivity;
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

    private RequestQueue requestQueue;

    private String username = "";
    private String platform = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        Log.i("TEST", "TEST");
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
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();

        downloadUserInfo(requestQueue);
    }

    private void downloadUserInfo(RequestQueue requestQueue) {
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

                        owButton.setOnClickListener(v -> overwatchPrompt());
                    },
                    error -> callErrorAlertDialog());

            requestQueue.add(jsonRequest);

        } catch (Exception e) {
            Log.e("Error-test", e.toString());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void callNextActivity(Class activity) {
        final Intent intent = new Intent(this, activity);
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        startActivity(intent);
    }

    private void callOverWatchActivity(String username, String platform) {
        final Intent intent = new Intent(this, OWActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("platform", platform);
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        startActivity(intent);
    }

    private void overwatchPrompt() {
        AlertDialog.Builder builderOW = new AlertDialog.Builder(GamesActivity.this, R.style.DialogTransparent);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView titleText = new TextView(GamesActivity.this);

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(10, 20, 10, 20);
        buttonParams.weight = 1;

        titleText.setTextSize(20);
        titleText.setGravity(Gravity.CENTER_HORIZONTAL);
        titleText.setPadding(0, 20, 0, 20);
        titleText.setLayoutParams(layoutParams);
        titleText.setTextColor(Color.WHITE);
        titleText.setText("Choose your platform");

        TextView messageText = new TextView(GamesActivity.this);
        messageText.setTextSize(18);
        messageText.setGravity(Gravity.CENTER_HORIZONTAL);
        messageText.setLayoutParams(layoutParams);
        messageText.setTextColor(Color.WHITE);

        Button pcButton = new Button(GamesActivity.this);
        pcButton.setText("PC");
        pcButton.setTextSize(16);
        pcButton.setTextColor(Color.BLACK);
        pcButton.setGravity(Gravity.CENTER);
        pcButton.setLayoutParams(buttonParams);
        pcButton.setBackground(GamesActivity.this.getDrawable(R.drawable.buttonstyle));

        Button xboxButton = new Button(GamesActivity.this);
        xboxButton.setText("Xbox");
        xboxButton.setTextSize(16);
        xboxButton.setTextColor(Color.BLACK);
        xboxButton.setGravity(Gravity.CENTER);
        xboxButton.setLayoutParams(buttonParams);
        xboxButton.setBackground(GamesActivity.this.getDrawable(R.drawable.buttonstyle));

        Button psButton = new Button(GamesActivity.this);
        psButton.setText("PS");
        psButton.setTextSize(16);
        psButton.setTextColor(Color.BLACK);
        psButton.setGravity(Gravity.CENTER);
        psButton.setLayoutParams(buttonParams);
        psButton.setBackground(GamesActivity.this.getDrawable(R.drawable.buttonstyle));

        Button okButton = new Button(GamesActivity.this);
        okButton.setText("OK");
        okButton.setTextSize(16);
        okButton.setTextColor(Color.WHITE);
        okButton.setGravity(Gravity.CENTER);
        okButton.setLayoutParams(buttonParams);
        okButton.setBackground(GamesActivity.this.getDrawable(R.drawable.buttonstyle));

        EditText usernameField = new EditText(GamesActivity.this);
        usernameField.setTextColor(Color.WHITE);
        usernameField.setTextSize(16);
        ColorStateList colorStateList = ColorStateList.valueOf(Color.WHITE);
        usernameField.setBackgroundTintList(colorStateList);

        LinearLayout buttonLayout = new LinearLayout(GamesActivity.this);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonLayout.setGravity(Gravity.CENTER);
        buttonLayout.addView(pcButton);
        buttonLayout.addView(xboxButton);
        buttonLayout.addView(psButton);

        final AlertDialog dialogOW = builderOW.show();
        Objects.requireNonNull(dialogOW.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialogOW.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        LinearLayout linearLayout = new LinearLayout(GamesActivity.this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setPadding(30, 30, 30, 30);

        linearLayout.addView(titleText);
        linearLayout.addView(buttonLayout);

        dialogOW.addContentView(linearLayout, layoutParams);

        pcButton.setOnClickListener(v -> {
            xboxButton.setTextColor(Color.BLACK);
            psButton.setTextColor(Color.BLACK);
            pcButton.setTextColor(Color.WHITE);
            platform = "pc";
            if (messageText.getParent() != null) {
                linearLayout.removeView(messageText);
                linearLayout.removeView(usernameField);
            }
            if (okButton.getParent() == null) {
                linearLayout.addView(okButton);
            }
        });

        xboxButton.setOnClickListener(v -> {
            if (okButton.getParent() != null) {
                linearLayout.removeView(okButton);
            }
            xboxButton.setTextColor(Color.WHITE);
            psButton.setTextColor(Color.BLACK);
            pcButton.setTextColor(Color.BLACK);
            messageText.setText("Enter your Xbox live username");
            if (messageText.getParent() == null) {
                linearLayout.addView(messageText);
                linearLayout.addView(usernameField);
            }
            if (okButton.getParent() == null) {
                linearLayout.addView(okButton);
            }
            platform = "xbl";
        });

        psButton.setOnClickListener(v -> {
            if (okButton.getParent() != null) {
                linearLayout.removeView(okButton);
            }
            xboxButton.setTextColor(Color.BLACK);
            psButton.setTextColor(Color.WHITE);
            pcButton.setTextColor(Color.BLACK);
            messageText.setText("Enter your Playstation username");
            if (messageText.getParent() == null) {
                linearLayout.addView(messageText);
                linearLayout.addView(usernameField);
            }
            if (okButton.getParent() == null) {
                linearLayout.addView(okButton);
            }
            platform = "psn";
        });

        okButton.setOnClickListener(v -> {
            if (usernameField.getText().length() == 0 && !platform.equalsIgnoreCase("PC")) {
                Toast.makeText(getApplicationContext(), "Please enter a username", Toast.LENGTH_SHORT).show();
            } else {
                username = usernameField.getText().toString();
                dialogOW.cancel();
                callOverWatchActivity(username, platform);
            }
        });
    }

    private void callErrorAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GamesActivity.this, R.style.DialogTransparent);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView titleText = new TextView(GamesActivity.this);

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(10, 20, 10, 20);

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
        button.setText("Retry");


        button.setTextSize(18);
        button.setTextColor(Color.WHITE);
        button.setGravity(Gravity.CENTER);
        button.setWidth(200);
        button.setLayoutParams(buttonParams);
        button.setBackground(GamesActivity.this.getDrawable(R.drawable.buttonstyle));

        Button button2 = new Button(GamesActivity.this);

        button2.setTextSize(20);
        button2.setTextColor(Color.WHITE);
        button2.setGravity(Gravity.CENTER);
        button2.setWidth(200);
        button2.setLayoutParams(buttonParams);
        button2.setBackground(GamesActivity.this.getDrawable(R.drawable.buttonstyle));
        button2.setText("Back");

        LinearLayout buttonLayout = new LinearLayout(GamesActivity.this);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonLayout.setGravity(Gravity.CENTER);
        buttonLayout.addView(button);
        buttonLayout.addView(button2);

        final AlertDialog dialog = builder.show();
        Objects.requireNonNull(dialog.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        LinearLayout linearLayout = new LinearLayout(GamesActivity.this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);

        linearLayout.addView(titleText);
        linearLayout.addView(messageText);
        linearLayout.addView(buttonLayout);

        dialog.addContentView(linearLayout, layoutParams);

        dialog.setOnCancelListener(dialog1 -> downloadUserInfo(requestQueue));

        button.setOnClickListener(v -> dialog.cancel());
        button2.setOnClickListener(v -> dialog.dismiss());
    }
}
