package com.BlizzardArmory.ui.ui_starcraft;

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
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.UserInformation;
import com.BlizzardArmory.starcraft.Player;
import com.BlizzardArmory.starcraft.profile.Profile;
import com.BlizzardArmory.ui.GamesActivity;
import com.BlizzardArmory.ui.ui_diablo.DiabloProfileSearchDialog;
import com.BlizzardArmory.ui.ui_overwatch.OWPlatformChoiceDialog;
import com.BlizzardArmory.ui.ui_warcraft.WoWActivity;
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
    private Gson gson;


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
    private LinearLayout statistics;
    private LinearLayout campaign;

    private ImageView ones;
    private ImageView archon;
    private ImageView twos;
    private ImageView threes;
    private ImageView fours;
    private TextView onesText;
    private TextView archonText;
    private TextView twosText;
    private TextView threesText;
    private TextView foursText;

    private TextView terranWins;
    private TextView zergWins;
    private TextView protossWins;
    private TextView playedSeason;
    private TextView careerGames;
    private TextView bestOne;
    private TextView bestTeam;
    private ImageView bestOneIcon;
    private ImageView bestTeamIcon;

    private ImageView terranImage;
    private ImageView zergImage;
    private ImageView protossImage;
    private TextView terranLevel;
    private TextView zergLevel;
    private TextView protossLevel;

    private ImageView wol;
    private ImageView hots;
    private ImageView lotv;
    private TextView wol_text;
    private TextView hots_text;
    private TextView lotv_text;

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
        statistics = findViewById(R.id.statistics);
        terranImage = findViewById(R.id.terran_image);
        zergImage = findViewById(R.id.zerg_image);
        protossImage = findViewById(R.id.protoss_image);
        terranLevel = findViewById(R.id.terran_level);
        zergLevel = findViewById(R.id.zerg_level);
        protossLevel = findViewById(R.id.protoss_level);
        wol = findViewById(R.id.wol_icon);
        hots = findViewById(R.id.hots_icon);
        lotv = findViewById(R.id.lotv_icon);
        wol_text = findViewById(R.id.campaign_wol);
        hots_text = findViewById(R.id.campaign_hots);
        lotv_text = findViewById(R.id.campaign_lotv);
        campaign = findViewById(R.id.campaign);

        GradientDrawable summaryBG = new GradientDrawable();
        summaryBG.setStroke(6, Color.parseColor("#122a42"));
        summaryBG.setColor(Color.parseColor("#75091c2e"));

        GradientDrawable statsBG = new GradientDrawable();
        statsBG.setStroke(6, Color.parseColor("#122a42"));
        statsBG.setColor(Color.parseColor("#75091c2e"));

        GradientDrawable snapshotBG = new GradientDrawable();
        snapshotBG.setStroke(6, Color.parseColor("#122a42"));
        snapshotBG.setColor(Color.parseColor("#75091c2e"));

        GradientDrawable campaignBG = new GradientDrawable();
        campaignBG.setStroke(6, Color.parseColor("#122a42"));
        campaignBG.setColor(Color.parseColor("#75091c2e"));

        summary.setBackground(summaryBG);
        snapshot.setBackground(snapshotBG);
        statistics.setBackground(statsBG);
        campaign.setBackground(campaignBG);


        ones = findViewById(R.id.one_one);
        archon = findViewById(R.id.archon);
        twos = findViewById(R.id.two_two);
        threes = findViewById(R.id.three_three);
        fours = findViewById(R.id.four_four);
        onesText = findViewById(R.id.one_one_text);
        archonText = findViewById(R.id.archon_text);
        twosText = findViewById(R.id.two_two_text);
        threesText = findViewById(R.id.three_three_text);
        foursText = findViewById(R.id.four_four_text);

        terranWins = findViewById(R.id.terran_wins);
        zergWins = findViewById(R.id.zerg_wins);
        protossWins = findViewById(R.id.protoss_wins);
        playedSeason = findViewById(R.id.season_played);
        careerGames = findViewById(R.id.career_played);
        bestOne = findViewById(R.id.best_one);
        bestTeam = findViewById(R.id.best_team);
        bestOneIcon = findViewById(R.id.best_one_icon);
        bestTeamIcon = findViewById(R.id.best_team_icon);

        int startColor = 0xFF0066cc;
        int endColor = 0x00000000;
        GradientDrawable avatarShadow = new GradientDrawable();
        avatarShadow.setColors(new int[]{endColor, startColor});
        avatarShadow.setStroke(6, Color.parseColor("#0066cc"));
        avatarShadow.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        avatarShadow.setGradientRadius(800.0f);

        GradientDrawable raceImageBG = new GradientDrawable();
        raceImageBG.setColors(new int[]{endColor, startColor});
        raceImageBG.setStroke(6, Color.parseColor("#122a42"));
        raceImageBG.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        raceImageBG.setGradientRadius(800.0f);

        terranImage.setImageDrawable(raceImageBG);
        zergImage.setImageDrawable(raceImageBG);
        protossImage.setImageDrawable(raceImageBG);

        avatar.setImageDrawable(avatarShadow);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        bnOAuth2Params = Objects.requireNonNull(getIntent().getExtras()).getParcelable(BnConstants.BUNDLE_BNPARAMS);
        assert bnOAuth2Params != null;
        bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);
        gson = new GsonBuilder().create();

        downloadAccountInformation();

        //Button calls
        wowButton.setOnClickListener(v -> callNextActivity(WoWActivity.class));

        d3Button.setOnClickListener(v -> DiabloProfileSearchDialog.diabloPrompt(SC2Activity.this, bnOAuth2Params));

        owButton.setOnClickListener(v -> OWPlatformChoiceDialog.overwatchPrompt(SC2Activity.this, bnOAuth2Params));
    }

    private void downloadAccountInformation() {
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

                            JsonObjectRequest profileRequest = new JsonObjectRequest(Request.Method.GET, url + bnOAuth2Helper.getAccessToken(), null,
                                    response1 -> {

                                        sc2Profile = gson.fromJson(response1.toString(), Profile.class);
                                        setSummaryInformation();
                                        setSnapshotInformation();
                                        setStatisticsInformation();
                                        setRaceLevelInformation();
                                        setCampaignInformation();

                                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                        loadingCircle.setVisibility(View.GONE);

                                    },
                                    error -> showNoConnectionMessage(SC2Activity.this, 0));

                            requestQueue.add(profileRequest);

                        } catch (Exception e) {
                            Log.e("Error", e.toString());
                        }
                        try {
                            downloadAvatar(requestQueue);
                        }catch (Exception e){
                            Log.e("Avatar", "No avatar");
                        }
                    },
                    error -> {
                        if (error.networkResponse == null) {
                            showNoConnectionMessage(SC2Activity.this, 0);
                        } else {
                            showNoConnectionMessage(SC2Activity.this, error.networkResponse.statusCode);
                        }
                    });

            requestQueue.add(jsonRequest);
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    private void setCampaignInformation() {
        if (sc2Profile.getCampaign().getDifficultyCompleted().getWingsOfLiberty() != null) {
            switch (sc2Profile.getCampaign().getDifficultyCompleted().getWingsOfLiberty()) {
                case "CASUAL":
                    wol.setImageResource(R.drawable.campaign_badge_wol_casual);
                    wol_text.setText("Casual Campaign Ace");
                    break;
                case "NORMAL":
                    wol.setImageResource(R.drawable.campaign_badge_lotv_casual);
                    wol_text.setText("Normal Campaign Ace");
                    break;
                case "HARD":
                    wol.setImageResource(R.drawable.campaign_badge_wol_hard);
                    wol_text.setText("Hard Campaign Ace");
                    break;
                case "BRUTAL":
                    wol.setImageResource(R.drawable.campaign_badge_wol_brutal);
                    wol_text.setText("Brutal Campaign Ace");
                    break;
            }
        }

        if (sc2Profile.getCampaign().getDifficultyCompleted().getHeartOfTheSwarm() != null) {
            switch (sc2Profile.getCampaign().getDifficultyCompleted().getHeartOfTheSwarm()) {
                case "CASUAL":
                    hots.setImageResource(R.drawable.campaign_badge_hots_casual);
                    hots_text.setText("Casual Campaign Ace");
                    break;
                case "NORMAL":
                    hots.setImageResource(R.drawable.campaign_badge_hots_normal);
                    hots_text.setText("Normal Campaign Ace");
                    break;
                case "HARD":
                    hots.setImageResource(R.drawable.campaign_badge_hots_hard);
                    hots_text.setText("Hard Campaign Ace");
                    break;
                case "BRUTAL":
                    hots.setImageResource(R.drawable.campaign_badge_hots_brutal);
                    hots_text.setText("Brutal Campaign Ace");
                    break;
            }
        }

        if (sc2Profile.getCampaign().getDifficultyCompleted().getLegacyOfTheVoid() != null) {
            switch (sc2Profile.getCampaign().getDifficultyCompleted().getLegacyOfTheVoid()) {
                case "CASUAL":
                    lotv.setImageResource(R.drawable.campaign_badge_lotv_casual);
                    lotv_text.setText("Casual Campaign Ace");
                    break;
                case "NORMAL":
                    lotv.setImageResource(R.drawable.campaign_badge_lotv_normal);
                    lotv_text.setText("Normal Campaign Ace");
                    break;
                case "HARD":
                    lotv.setImageResource(R.drawable.campaign_badge_lotv_hard);
                    lotv_text.setText("Hard Campaign Ace");
                    break;
                case "BRUTAL":
                    lotv.setImageResource(R.drawable.campaign_badge_lotv_brutal);
                    lotv_text.setText("Brutal Campaign Ace");
                    break;
            }
        }
    }

    private void setRaceLevelInformation() {
        String terranTemp = "Level " + sc2Profile.getSwarmLevels().getTerran().getLevel();
        String zergTemp = "Level " + sc2Profile.getSwarmLevels().getZerg().getLevel();
        String protossTemp = "Level " + sc2Profile.getSwarmLevels().getProtoss().getLevel();
        terranLevel.setText(terranTemp);
        zergLevel.setText(zergTemp);
        protossLevel.setText(protossTemp);
    }

    private void setStatisticsInformation() {
        terranWins.setText(String.valueOf(sc2Profile.getCareer().getTerranWins()));
        zergWins.setText(String.valueOf(sc2Profile.getCareer().getZergWins()));
        protossWins.setText(String.valueOf(sc2Profile.getCareer().getProtossWins()));
        playedSeason.setText(String.valueOf(sc2Profile.getCareer().getTotalGamesThisSeason()));
        careerGames.setText(String.valueOf(sc2Profile.getCareer().getTotalCareerGames()));
        if (sc2Profile.getCareer().getBest1v1Finish().getLeagueName() != null) {
            setSnapshotIcons(sc2Profile.getCareer().getBest1v1Finish().getLeagueName(), 500, bestOneIcon);
            String temp = sc2Profile.getCareer().getBest1v1Finish().getLeagueName().substring(1).toLowerCase();
            temp = sc2Profile.getCareer().getBest1v1Finish().getLeagueName().substring(0, 1) + temp;
            bestOne.setText(temp);
        } else {
            bestOne.setVisibility(View.GONE);
        }
        if (sc2Profile.getCareer().getBestTeamFinish().getLeagueName() != null) {
            setSnapshotIcons(sc2Profile.getCareer().getBestTeamFinish().getLeagueName(), 500, bestTeamIcon);
            String temp = sc2Profile.getCareer().getBestTeamFinish().getLeagueName().substring(1).toLowerCase();
            temp = sc2Profile.getCareer().getBestTeamFinish().getLeagueName().substring(0, 1) + temp;
            bestTeam.setText(temp);
        } else {
            bestTeam.setVisibility(View.GONE);
        }
    }

    private void setSnapshotInformation() {
        setSnapshotIcons(sc2Profile.getSnapshot().getSeasonSnapshot().get1v1().getLeagueName(), sc2Profile.getSnapshot().getSeasonSnapshot().get1v1().getRank(), ones);
        setSnapshotIcons(sc2Profile.getSnapshot().getSeasonSnapshot().getArchon().getLeagueName(), sc2Profile.getSnapshot().getSeasonSnapshot().getArchon().getRank(), archon);
        setSnapshotIcons(sc2Profile.getSnapshot().getSeasonSnapshot().get2v2().getLeagueName(), sc2Profile.getSnapshot().getSeasonSnapshot().get2v2().getRank(), twos);
        setSnapshotIcons(sc2Profile.getSnapshot().getSeasonSnapshot().get3v3().getLeagueName(), sc2Profile.getSnapshot().getSeasonSnapshot().get3v3().getRank(), threes);
        setSnapshotIcons(sc2Profile.getSnapshot().getSeasonSnapshot().get4v4().getLeagueName(), sc2Profile.getSnapshot().getSeasonSnapshot().get4v4().getRank(), fours);
        setSnapshotText(sc2Profile.getSnapshot().getSeasonSnapshot().get1v1().getTotalGames(), sc2Profile.getSnapshot().getSeasonSnapshot().get1v1().getTotalWins(), onesText);
        setSnapshotText(sc2Profile.getSnapshot().getSeasonSnapshot().getArchon().getTotalGames(), sc2Profile.getSnapshot().getSeasonSnapshot().getArchon().getTotalWins(), archonText);
        setSnapshotText(sc2Profile.getSnapshot().getSeasonSnapshot().get2v2().getTotalGames(), sc2Profile.getSnapshot().getSeasonSnapshot().get2v2().getTotalWins(), twosText);
        setSnapshotText(sc2Profile.getSnapshot().getSeasonSnapshot().get3v3().getTotalGames(), sc2Profile.getSnapshot().getSeasonSnapshot().get3v3().getTotalWins(), threesText);
        setSnapshotText(sc2Profile.getSnapshot().getSeasonSnapshot().get4v4().getTotalGames(), sc2Profile.getSnapshot().getSeasonSnapshot().get4v4().getTotalWins(), foursText);
    }

    private void setSnapshotText(int totalGames, int totalWins, TextView text) {
        if (totalGames != 0) {
            String tempText = " - " + totalGames + " Games | " + totalWins + " Wins";
            text.setText(tempText);
        }

    }

    private void setSnapshotIcons(String league, int rank, ImageView icon) {
        if (league != null) {
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
        if (sc2Profile.getSummary().getClanName() != null) {
            String clanName = "[" + sc2Profile.getSummary().getClanTag() + "] " + sc2Profile.getSummary().getClanName();
            clan.setText(clanName);
        } else {
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, GamesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void showNoConnectionMessage(final Context context, final int responseCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogTransparent);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(10, 20, 10, 20);

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
        button.setLayoutParams(buttonParams);
        button.setBackground(context.getDrawable(R.drawable.buttonstyle));

        Button button2 = new Button(context);

        button2.setTextSize(20);
        button2.setTextColor(Color.WHITE);
        button2.setGravity(Gravity.CENTER);
        button2.setWidth(200);
        button2.setHeight(100);
        button2.setLayoutParams(buttonParams);
        button2.setBackground(context.getDrawable(R.drawable.buttonstyle));

        LinearLayout buttonLayout = new LinearLayout(context);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonLayout.setGravity(Gravity.CENTER);
        buttonLayout.addView(button);

        if (responseCode == 404) {
            titleText.setText("The account could not be found");
            messageText.setText("There is no Starcraft 2 profile associated with this account.");
            button.setText("OK");
        } else if (responseCode == 403) {
            titleText.setText("Unavailable");
            messageText.setText("The Starcraft 2 community servers are down temporarily.");
            button.setText("Back");
        } else {
            titleText.setText("No Internet Connection");
            messageText.setText("Make sure that Wi-Fi or mobile data is turned on, then try again.");
            button.setText("Retry");
            button2.setText("Back");
            buttonLayout.addView(button2);
        }

        final AlertDialog dialog = builder.show();
        Objects.requireNonNull(dialog.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setPadding(20, 20, 20, 20);

        linearLayout.addView(titleText);
        linearLayout.addView(messageText);
        linearLayout.addView(buttonLayout);

        dialog.addContentView(linearLayout, layoutParams);

        if (responseCode == 404 || responseCode == 403) {
            dialog.setOnCancelListener(dialog1 -> SC2Activity.this.finish());
        } else {
            dialog.setOnCancelListener(dialog1 -> downloadAccountInformation());
        }

        button.setOnClickListener(v -> dialog.cancel());
        button2.setOnClickListener(v -> onBackPressed());
    }
}
