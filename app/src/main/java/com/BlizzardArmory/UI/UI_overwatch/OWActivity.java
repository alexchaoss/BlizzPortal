package com.BlizzardArmory.UI.UI_overwatch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.BlizzardArmory.R;
import com.BlizzardArmory.UI.UI_diablo.D3Activity;
import com.BlizzardArmory.UI.UI_starcraft.SC2Activity;
import com.BlizzardArmory.UI.UI_warcraft.WoWActivity;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.UserInformation;
import com.BlizzardArmory.overwatch.Profile;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;

public class OWActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private BnOAuth2Helper bnOAuth2Helper;
    private BnOAuth2Params bnOAuth2Params;


    private ImageButton wowButton;
    private ImageButton sc2Button;
    private ImageButton d3Button;
    private TextView btag;
    private RelativeLayout loadingCircle;
    private Profile accountInformation;

    private ImageView avatar;
    private TextView name;
    private ImageView levelIcon;
    private ImageView prestigeIcon;
    private ImageView ratingIcon;
    private TextView level;
    private TextView rating;
    private TextView gamesWon;
    private ImageView endorsementIcon;
    private TextView endorsement;

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
        avatar = findViewById(R.id.avatar);
        name = findViewById(R.id.name);
        levelIcon = findViewById(R.id.level_icon);
        ratingIcon = findViewById(R.id.rating_icon);
        level = findViewById(R.id.level);
        rating = findViewById(R.id.rating);
        gamesWon = findViewById(R.id.games_won);
        /*endorsementIcon = findViewById(R.id.endorsement_icon);
        endorsementIcon.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        endorsement = findViewById(R.id.endorsement_level);*/
        prestigeIcon = findViewById(R.id.prestige_icon);

        btag.setText(UserInformation.getBattleTag());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        prefs = PreferenceManager.getDefaultSharedPreferences(OWActivity.this);
        bnOAuth2Params = OWActivity.this.getIntent().getExtras().getParcelable(BnConstants.BUNDLE_BNPARAMS);
        bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

        final Gson gson = new GsonBuilder().create();

        try {

            Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024 * 5);
            Network network = new BasicNetwork(new HurlStack());
            RequestQueue requestQueue = new RequestQueue(cache, network);
            requestQueue.start();

            Log.i("URL", URLConstants.getOWProfile());
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLConstants.getOWProfile(), null,
                    response -> {

                        try {
                            accountInformation = gson.fromJson(response.toString(), Profile.class);
                            Log.i("Games won", "" + accountInformation.getQuickPlayStats().getGames().getWon());

                            downloadAvatar(requestQueue);
                            int hashtag = accountInformation.getName().indexOf("#");
                            String tempName = accountInformation.getName().substring(0, hashtag) + " ";
                            name.setText(tempName);
                            downloadLevelIcon(requestQueue);
                            level.setText(String.valueOf(accountInformation.getLevel()));
                            //downloadEndorsementIcon(requestQueue);
                            //endorsement.setText(String.valueOf(accountInformation.getEndorsement()));
                            String tempGames = accountInformation.getGamesWon() + " games won";
                            gamesWon.setText(tempGames);
                            if (accountInformation.getRating() != 0) {
                                downloadRatingIcon(requestQueue);
                                rating.setText(String.valueOf(accountInformation.getPrestige()));
                            } else {
                                ratingIcon.setVisibility(View.GONE);
                                rating.setVisibility(View.GONE);
                                View view = findViewById(R.id.view2);
                                view.setVisibility(View.GONE);
                            }


                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            loadingCircle.setVisibility(View.GONE);

                        } catch (Exception e) {
                            Log.e("Error", e.toString());
                        }

                    },
                    error -> {
                        Log.e("Error", error.getMessage());
                        showNoConnectionMessage(OWActivity.this, 0);
                    });

            requestQueue.add(jsonRequest);
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }

        //Button calls
        wowButton.setOnClickListener(v -> callNextActivity(WoWActivity.class));

        d3Button.setOnClickListener(v -> callNextActivity(D3Activity.class));

        sc2Button.setOnClickListener(v -> callNextActivity(SC2Activity.class));
    }

    private void callNextActivity(Class activity) {
        final Intent intent = new Intent(this, activity);
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        startActivity(intent);
    }

    private void downloadAvatar(RequestQueue requestQueue) {
        ImageRequest imageRequest = new ImageRequest(accountInformation.getIcon(), bitmap -> {
            BitmapDrawable avatarBM = new BitmapDrawable(getResources(), bitmap);
            avatar.setBackground(avatarBM);
        }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                e -> {
                    showNoConnectionMessage(OWActivity.this, 0);
                });
        requestQueue.add(imageRequest);
    }

    private void downloadEndorsementIcon(RequestQueue requestQueue) {
        StringRequest stringRequest = new StringRequest(accountInformation.getEndorsementIcon(), string -> {
            try {
                SVG endorsement_icon = SVG.getFromString(string);
                PictureDrawable  pd = new PictureDrawable(endorsement_icon.renderToPicture());
                endorsementIcon.setImageDrawable(pd);
            } catch (Exception e) {
                Log.e("SVG Exception", e.toString());
            }

        },
                e -> {
                    showNoConnectionMessage(OWActivity.this, 0);
                });
        requestQueue.add(stringRequest);
    }

    private void downloadRatingIcon(RequestQueue requestQueue) {
        ImageRequest imageRequest = new ImageRequest(accountInformation.getRatingIcon(), bitmap -> {
            BitmapDrawable avatarBM = new BitmapDrawable(getResources(), bitmap);
            ratingIcon.setBackground(avatarBM);
        }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                e -> {
                    showNoConnectionMessage(OWActivity.this, 0);
                });
        requestQueue.add(imageRequest);
    }

    private void downloadLevelIcon(RequestQueue requestQueue) {
        ImageRequest imageRequest = new ImageRequest(accountInformation.getLevelIcon(), bitmap -> {

            BitmapDrawable avatarBM = new BitmapDrawable(getResources(), bitmap);
            levelIcon.setBackground(avatarBM);

            ImageRequest imageRequest2 = new ImageRequest(accountInformation.getPrestigeIcon(), bitmap2 -> {
                BitmapDrawable avatarBM2 = new BitmapDrawable(getResources(), bitmap2);
                prestigeIcon.setImageDrawable(avatarBM2);
            }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                    e -> {
                        showNoConnectionMessage(OWActivity.this, 0);
                    });
            requestQueue.add(imageRequest2);

        }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                e -> {
                    showNoConnectionMessage(OWActivity.this, 0);
                });
        requestQueue.add(imageRequest);
    }

    public void showNoConnectionMessage(final Context context, final int responseCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogTransparent);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        TextView titleText = new TextView(context);

        titleText.setTextSize(20);
        titleText.setGravity(Gravity.CENTER_HORIZONTAL);
        titleText.setPadding(0, 20, 0, 20);
        titleText.setLayoutParams(layoutParams);
        titleText.setTextColor(Color.WHITE);

        TextView messageText = new TextView(context);

        messageText.setGravity(Gravity.CENTER_HORIZONTAL);
        messageText.setPadding(0, 0, 0, 20);
        messageText.setLayoutParams(layoutParams);
        messageText.setTextColor(Color.WHITE);

        Button button = new Button(context);

        button.setTextSize(20);
        button.setTextColor(Color.WHITE);
        button.setGravity(Gravity.CENTER);
        button.setWidth(200);
        button.setHeight(100);
        button.setLayoutParams(layoutParams);
        button.setBackground(context.getDrawable(R.drawable.buttonstyle));


        titleText.setText("No Internet Connection");
        messageText.setText("Make sure that Wi-Fi or mobile data is turned on, then try again.");
        button.setText("Retry");

        final AlertDialog dialog = builder.show();
        Objects.requireNonNull(dialog.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setLayout(800, 450);

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setPadding(20, 20, 20, 20);

        linearLayout.addView(titleText);
        linearLayout.addView(messageText);
        linearLayout.addView(button);

        dialog.addContentView(linearLayout, layoutParams);

        dialog.setOnCancelListener(dialog1 -> OWActivity.this.recreate());

        button.setOnClickListener(v -> dialog.cancel());
    }
}
