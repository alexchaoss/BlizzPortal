package com.BlizzardArmory.UI.UI_starcraft;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
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

import com.BlizzardArmory.BuildConfig;
import com.BlizzardArmory.R;
import com.BlizzardArmory.UI.UI_diablo.D3Activity;
import com.BlizzardArmory.UI.UI_overwatch.OWActivity;
import com.BlizzardArmory.UI.UI_warcraft.WoWActivity;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.UserInformation;
import com.BlizzardArmory.starcraft.Player;
import com.BlizzardArmory.starcraft.Profile.Profile;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;

public class SC2Activity extends AppCompatActivity {

    private SharedPreferences prefs;
    private BnOAuth2Helper bnOAuth2Helper;
    private BnOAuth2Params bnOAuth2Params;


    private ImageButton wowButton;
    private ImageButton d3Button;
    private ImageButton owButton;
    private TextView btag;
    private RelativeLayout loadingCircle;

    private Player accountInformation;
    private Profile sc2Profile;
    private ImageView avatar;
    private TextView totalLevel;
    private TextView name;
    private TextView clan;
    private TextView achievement;

    private LinearLayout summary;
    private LinearLayout snapshot;


    private ImageView ones;
    private ImageView archon;
    private ImageView twos;
    private ImageView threes;
    private ImageView fours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sc2_activity);
        wowButton = findViewById(R.id.wowButton);
        d3Button = findViewById(R.id.diablo3Button);
        owButton = findViewById(R.id.overwatchButton);
        btag = findViewById(R.id.btag_header);
        loadingCircle = findViewById(R.id.loadingCircle);
        loadingCircle.setVisibility(View.VISIBLE);
        btag.setText(UserInformation.getBattleTag());
        avatar = findViewById(R.id.avatar);
        totalLevel = findViewById(R.id.total_level_text);
        summary = findViewById(R.id.summary);
        name = findViewById(R.id.name);
        clan = findViewById(R.id.clan);
        achievement = findViewById(R.id.achievement_points);
        snapshot = findViewById(R.id.snapshot);

        GradientDrawable panelsBG = new GradientDrawable();
        panelsBG.setStroke(6, Color.parseColor("#122a42"));
        panelsBG.setColor(Color.parseColor("#75091c2e"));
        summary.setBackground(panelsBG);
        snapshot.setBackground(panelsBG);

        ones = findViewById(R.id.one_one);
        archon = findViewById(R.id.archon);
        twos = findViewById(R.id.two_two);
        threes = findViewById(R.id.three_three);
        fours = findViewById(R.id.four_four);



        int startColor = 0xFF0066cc;
        int endColor = 0x00000000;
        GradientDrawable avatarShadow = new GradientDrawable();
        avatarShadow.setColors(new int[]{endColor, startColor});
        avatarShadow.setStroke(6, Color.parseColor("#0066cc"));
        avatarShadow.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        avatarShadow.setGradientRadius(800.0f);

        avatar.setImageDrawable(avatarShadow);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        bnOAuth2Params = Objects.requireNonNull(getIntent().getExtras()).getParcelable(BnConstants.BUNDLE_BNPARAMS);
        assert bnOAuth2Params != null;
        bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);
        final Gson gson = new GsonBuilder().create();

        try {

            Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024 * 5);
            Network network = new BasicNetwork(new HurlStack());
            RequestQueue requestQueue = new RequestQueue(cache, network);
            requestQueue.start();

            JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, URLConstants.getBaseURLforAPI() + URLConstants.SC2_PROFILE.replace("id", UserInformation.getUserID())
                    + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken(), null,
                    response0 -> {

                        try {
                            accountInformation = gson.fromJson(response0.get(0).toString(), Player.class);

                            String url = getProfileURL();

                            Log.i("URL", url + bnOAuth2Helper.getAccessToken());

                            JsonObjectRequest profileRequest = new JsonObjectRequest(Request.Method.GET, "https://us.api.blizzard.com/sc2/profile/1/1/2150678?:regionId=1&:realmId=1&locale=en_US&access_token=" + bnOAuth2Helper.getAccessToken(), null,
                                    response1 -> {

                                        sc2Profile = gson.fromJson(response1.toString(), Profile.class);
                                        setSummaryInformation();
                                        setSnapshotIcons(sc2Profile.getSnapshot().getSeasonSnapshot().get1v1().getLeagueName(), sc2Profile.getSnapshot().getSeasonSnapshot().get1v1().getRank(), ones);
                                        setSnapshotIcons(sc2Profile.getSnapshot().getSeasonSnapshot().getArchon().getLeagueName(), sc2Profile.getSnapshot().getSeasonSnapshot().getArchon().getRank(), archon);
                                        setSnapshotIcons(sc2Profile.getSnapshot().getSeasonSnapshot().get2v2().getLeagueName(), sc2Profile.getSnapshot().getSeasonSnapshot().get2v2().getRank(), twos);
                                        setSnapshotIcons(sc2Profile.getSnapshot().getSeasonSnapshot().get3v3().getLeagueName(), sc2Profile.getSnapshot().getSeasonSnapshot().get3v3().getRank(), threes);
                                        setSnapshotIcons(sc2Profile.getSnapshot().getSeasonSnapshot().get4v4().getLeagueName(), sc2Profile.getSnapshot().getSeasonSnapshot().get4v4().getRank(), fours);
                                        //sc2Profile.getSnapshot().getSeasonSnapshot().get1v1().getTotalGames();
                                        //sc2Profile.getSnapshot().getSeasonSnapshot().get1v1().getTotalWins();

                                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                        loadingCircle.setVisibility(View.GONE);

                                    },
                                    error -> showNoConnectionMessage(SC2Activity.this, 0));

                            requestQueue.add(profileRequest);

                        } catch (Exception e) {
                            Log.e("Error", e.toString());
                        }

                        downloadAvatar(requestQueue);
                    },
                    error -> {
                        showNoConnectionMessage(SC2Activity.this, 0);
                    });

            requestQueue.add(jsonRequest);
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }

        //Button calls
        wowButton.setOnClickListener(v -> callNextActivity(WoWActivity.class));

        d3Button.setOnClickListener(v -> callNextActivity(D3Activity.class));

        owButton.setOnClickListener(v -> callNextActivity(OWActivity.class));
    }

    private void setSnapshotIcons(String league, int rank, ImageView icon) {
        if(league != null) {
            switch (league) {
                case "GRANDMASTER":
                    if (rank <= 16) {
                        icon.setImageResource(R.drawable.grandmaster_rank_16);
                    } else if (rank <= 50) {
                        icon.setImageResource(R.drawable.grandmaster_rank_50);
                    } else if (rank < 200) {
                        icon.setImageResource(R.drawable.grandmaster_rank_100);
                    } else {
                        icon.setImageResource(R.drawable.grandmaster_rank);
                    }
                    break;
                case "MASTER":
                    if (rank <= 16) {
                        icon.setImageResource(R.drawable.master_rank_16);
                    } else if (rank <= 50) {
                        icon.setImageResource(R.drawable.master_rank_50);
                    } else if (rank < 200) {
                        icon.setImageResource(R.drawable.master_rank_100);
                    } else {
                        icon.setImageResource(R.drawable.master_rank);
                    }
                    break;
                case "DIAMOND":
                    if (rank <= 16) {
                        icon.setImageResource(R.drawable.diamond_rank_16);
                    } else if (rank <= 50) {
                        icon.setImageResource(R.drawable.diamond_rank_50);
                    } else if (rank < 200) {
                        icon.setImageResource(R.drawable.diamond_rank_100);
                    } else {
                        icon.setImageResource(R.drawable.diamond_rank);
                    }
                    break;
                case "PLATINUM":
                    if (rank <= 16) {
                        icon.setImageResource(R.drawable.platinum_rank_16);
                    } else if (rank <= 50) {
                        icon.setImageResource(R.drawable.platinum_rank_50);
                    } else if (rank < 200) {
                        icon.setImageResource(R.drawable.platinum_rank_100);
                    } else {
                        icon.setImageResource(R.drawable.platinum_rank);
                    }
                    break;
                case "GOLD":
                    if (rank <= 16) {
                        icon.setImageResource(R.drawable.gold_rank_16);
                    } else if (rank <= 50) {
                        icon.setImageResource(R.drawable.gold_rank_50);
                    } else if (rank < 200) {
                        icon.setImageResource(R.drawable.gold_rank_100);
                    } else {
                        icon.setImageResource(R.drawable.gold_rank);
                    }
                    break;
                case "SILVER":
                    if (rank <= 16) {
                        icon.setImageResource(R.drawable.silver_rank_16);
                    } else if (rank <= 50) {
                        icon.setImageResource(R.drawable.silver_rank_50);
                    } else if (rank < 200) {
                        icon.setImageResource(R.drawable.silver_rank_100);
                    } else {
                        icon.setImageResource(R.drawable.silver_rank);
                    }
                    break;
                case "BRONZE":
                    if (rank <= 16) {
                        icon.setImageResource(R.drawable.bronze_rank_16);
                    } else if (rank <= 50) {
                        icon.setImageResource(R.drawable.bronze_rank_50);
                    } else if (rank < 200) {
                        icon.setImageResource(R.drawable.bronze_rank_100);
                    } else {
                        icon.setImageResource(R.drawable.bronze_rank);
                    }
                    break;
            }
        }
    }

    private void setSummaryInformation() {
        totalLevel.setText(String.valueOf(sc2Profile.getSwarmLevels().getLevel()));
        name.setText(sc2Profile.getSummary().getDisplayName());
        if(sc2Profile.getSummary().getClanName() != null) {
            String clanName = "[" + sc2Profile.getSummary().getClanTag() + "] " + sc2Profile.getSummary().getClanName();
            clan.setText(clanName);
        }else{
            clan.setVisibility(View.GONE);
        }
        achievement.setText(Html.fromHtml("<img src=\"achievement_sc2\">" + sc2Profile.getSummary().getTotalAchievementPoints(), Html.FROM_HTML_MODE_LEGACY, source -> {
            int resourceId = getResources().getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID);
            Drawable drawable = getResources().getDrawable(resourceId, SC2Activity.this.getTheme());
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            return drawable;
        }, null));
    }

    private String getProfileURL() {
        String url = URLConstants.getBaseURLforAPI() + URLConstants.SC2_PROFILE_INFO;
        url = url.replace("region_id", String.valueOf(accountInformation.getRegionId()));
        url = url.replace("realm_id", String.valueOf(accountInformation.getRealmId()));
        url = url.replace("profile_id", accountInformation.getProfileId());

        return url;
    }

    private void downloadAvatar(RequestQueue requestQueue) {
        ImageRequest imageRequest = new ImageRequest(accountInformation.getAvatarUrl(), bitmap -> {
            BitmapDrawable avatarBM = new BitmapDrawable(getResources(), bitmap);
            avatar.setBackground(avatarBM);
        }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                e -> {
                    showNoConnectionMessage(SC2Activity.this, 0);
                });
        requestQueue.add(imageRequest);
    }

    private void callNextActivity(Class activity) {
        final Intent intent = new Intent(this, activity);
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        startActivity(intent);
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

        dialog.setOnCancelListener(dialog1 -> SC2Activity.this.recreate());

        button.setOnClickListener(v -> dialog.cancel());
    }
}
