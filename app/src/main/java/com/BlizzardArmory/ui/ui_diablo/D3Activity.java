package com.BlizzardArmory.ui.ui_diablo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.BlizzardArmory.R;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.UserInformation;
import com.BlizzardArmory.diablo.account.AccountInformation;
import com.BlizzardArmory.diablo.account.Hero;
import com.BlizzardArmory.ui.GamesActivity;
import com.BlizzardArmory.ui.IOnBackPressed;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class D3Activity extends AppCompatActivity {

    private SharedPreferences prefs;
    private BnOAuth2Helper bnOAuth2Helper;
    private BnOAuth2Params bnOAuth2Params;
    private RequestQueue requestQueue;

    private List<Drawable> portraits;

    private ImageButton wowButton;
    private ImageButton sc2Button;
    private ImageButton owButton;


    private RelativeLayout loadingCircle;
    private JSONObject D3AccountInfo;
    private static AccountInformation accountInformation;

    private TextView paragonLevel;
    private TextView lifetimeKills;
    private TextView eliteKills;

    private LinearLayout linearLayoutCharacters;
    private LinearLayout fallenCharacterLayout;

    private ImageView act1;
    private ImageView act2;
    private ImageView act3;
    private ImageView act4;
    private ImageView act5;

    private int characterID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d3_activity);
        wowButton = findViewById(R.id.wowButton);
        sc2Button = findViewById(R.id.starcraft2Button);
        owButton = findViewById(R.id.overwatchButton);
        TextView btag = findViewById(R.id.btag_header);
        loadingCircle = findViewById(R.id.loadingCircle);
        loadingCircle.setVisibility(View.VISIBLE);
        paragonLevel = findViewById(R.id.paragonLevel);
        lifetimeKills = findViewById(R.id.lifetime_kills);
        eliteKills = findViewById(R.id.elite_kills);
        linearLayoutCharacters = findViewById(R.id.character_layout);
        fallenCharacterLayout = findViewById(R.id.fallen_character_layout);
        act1 = findViewById(R.id.prog_act1);
        act2 = findViewById(R.id.prog_act2);
        act3 = findViewById(R.id.prog_act3);
        act4 = findViewById(R.id.prog_act4);
        act5 = findViewById(R.id.prog_act5);
        btag.setText(UserInformation.getBattleTag());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        bnOAuth2Params = Objects.requireNonNull(getIntent().getExtras()).getParcelable(BnConstants.BUNDLE_BNPARAMS);
        assert bnOAuth2Params != null;
        bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);


        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024 * 5); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();

        downloadAccountInformation();


        //Button calls
        wowButton.setOnClickListener(v -> callNextActivity(WoWActivity.class));

        sc2Button.setOnClickListener(v -> callNextActivity(SC2Activity.class));

        owButton.setOnClickListener(v -> callNextActivity(OWActivity.class));
    }

    private void downloadAccountInformation() {
        try {
            final Gson gson = new GsonBuilder().create();

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI() + URLConstants.getD3URLBtagProfile()
                    + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken(), null,
                    response -> {
                        D3AccountInfo = response;
                        accountInformation = gson.fromJson(D3AccountInfo.toString(), AccountInformation.class);

                        sortHeroes();

                        portraits = getCharacterImage(accountInformation.getHeroes(), getApplicationContext());

                        String paragon = accountInformation.getParagonLevel() +
                                " | " +
                                "<font color=#b00000>" +
                                accountInformation.getParagonLevelHardcore() +
                                "</font>";

                        paragonLevel.setText(Html.fromHtml(paragon, Html.FROM_HTML_MODE_LEGACY));
                        eliteKills.setText(String.valueOf(accountInformation.getKills().getElites()));
                        lifetimeKills.setText(String.valueOf(accountInformation.getKills().getMonsters()));

                        setCharacterFrames();

                        setFallenCharacterFrames();

                        double barbTime = accountInformation.getTimePlayed().getBarbarian();
                        double wizTime = accountInformation.getTimePlayed().getWizard();
                        double wdTime = accountInformation.getTimePlayed().getWitchDoctor();
                        double necroTime = accountInformation.getTimePlayed().getNecromancer();
                        double crusaderTime = accountInformation.getTimePlayed().getCrusader();
                        double monkTime = accountInformation.getTimePlayed().getMonk();
                        double dhTime = accountInformation.getTimePlayed().getDemonHunter();

                        List<Double> timePlayed = new ArrayList<>(Arrays.asList(barbTime, wdTime, wizTime, necroTime, crusaderTime, monkTime, dhTime));
                        List<Double> timePlayedPercent = new ArrayList<>();

                        double total = 0;

                        for (int i = 0; i < timePlayed.size(); i++) {
                            total += timePlayed.get(i);
                        }

                        for (int i = 0; i < timePlayed.size(); i++) {
                            timePlayedPercent.add((100 * timePlayed.get(i) / total));
                        }


                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        loadingCircle.setVisibility(View.GONE);
                    },
                    error -> showNoConnectionMessage(D3Activity.this, 0));

            requestQueue.add(jsonRequest);


            Log.i("json", D3AccountInfo.toString());
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    private void setFallenCharacterFrames() {
        for (int i = 0; i < accountInformation.getFallenHeroes().size(); i++) {

            ConstraintLayout frameLayout = new ConstraintLayout(getApplicationContext());
            frameLayout.setId((i + 1) * 2);

            ConstraintLayout.LayoutParams frameParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            frameParams.setMarginEnd(20);
            frameLayout.setLayoutParams(frameParams);

            ImageView frame = new ImageView(getApplicationContext());
            frame.setId((i + 10) * 2);
            getFallenHeroFrame(i, frame);

            ImageView levelFrame = new ImageView(getApplicationContext());
            levelFrame.setId((i + 100) * 2);
            levelFrame.setImageResource(R.drawable.fallen_hero_level);

            TextView name = new TextView(getApplicationContext());
            name.setTextColor(Color.parseColor("#a99877"));
            name.setText(accountInformation.getFallenHeroes().get(i).getName());
            name.setId((i + 1000) * 2);

            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
            Date dateFormatted = new Date((long) accountInformation.getFallenHeroes().get(i).getDeath().getTime() * 1000L);
            String dateString = format.format(dateFormatted);
            TextView date = new TextView(getApplicationContext());
            date.setTextColor(Color.parseColor("#937a51"));
            date.setText(dateString);
            date.setTextSize(12);
            date.setId((i + 10000) * 2);

            TextView level = new TextView(getApplicationContext());
            level.setTextColor(Color.parseColor("#b00000"));
            level.setTextSize(15);
            level.setText(String.valueOf(accountInformation.getFallenHeroes().get(i).getLevel()));
            level.setTypeface(null, Typeface.BOLD);
            level.setId((i + 100000) * 2);

            frameLayout.addView(frame);
            frameLayout.addView(name);
            frameLayout.addView(date);
            frameLayout.addView(levelFrame);
            frameLayout.addView(level);

            ConstraintSet setFrame = new ConstraintSet();
            setFrame.clone(frameLayout);
            setFrame.connect(frame.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
            setFrame.connect(frame.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
            setFrame.connect(frame.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
            setFrame.connect(frame.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);

            setFrame.connect(name.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 100);
            setFrame.connect(name.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
            setFrame.connect(name.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
            setFrame.connect(name.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);

            setFrame.connect(date.getId(), ConstraintSet.TOP, name.getId(), ConstraintSet.BOTTOM, 0);
            setFrame.connect(date.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
            setFrame.connect(date.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
            setFrame.connect(date.getId(), ConstraintSet.BOTTOM, levelFrame.getId(), ConstraintSet.TOP, 0);

            setFrame.connect(level.getId(), ConstraintSet.TOP, levelFrame.getId(), ConstraintSet.TOP, 0);
            setFrame.connect(level.getId(), ConstraintSet.LEFT, levelFrame.getId(), ConstraintSet.LEFT, 0);
            setFrame.connect(level.getId(), ConstraintSet.RIGHT, levelFrame.getId(), ConstraintSet.RIGHT, 0);
            setFrame.connect(level.getId(), ConstraintSet.BOTTOM, levelFrame.getId(), ConstraintSet.BOTTOM, 0);

            setFrame.connect(levelFrame.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
            setFrame.connect(levelFrame.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
            setFrame.connect(levelFrame.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, -25);

            setFrame.applyTo(frameLayout);

            fallenCharacterLayout.addView(frameLayout);

        }
    }

    private void getFallenHeroFrame(int i, ImageView frame) {
        switch (accountInformation.getFallenHeroes().get(i).getClass_()) {
            case "barbarian":
                if (accountInformation.getFallenHeroes().get(i).getGender() == 0) {
                    frame.setImageResource(R.drawable.d3_male_barb_dead);
                } else if (accountInformation.getFallenHeroes().get(i).getGender() == 1) {
                    frame.setImageResource(R.drawable.d3_female_barb_dead);
                }
                break;
            case "wizard":
                if (accountInformation.getFallenHeroes().get(i).getGender() == 0) {
                    frame.setImageResource(R.drawable.d3_male_wizard_dead);
                } else if (accountInformation.getFallenHeroes().get(i).getGender() == 1) {
                    frame.setImageResource(R.drawable.d3_female_wizard_dead);
                }
                break;
            case "demon-hunter":
                if (accountInformation.getFallenHeroes().get(i).getGender() == 0) {
                    frame.setImageResource(R.drawable.d3_male_dh_dead);
                } else if (accountInformation.getFallenHeroes().get(i).getGender() == 1) {
                    frame.setImageResource(R.drawable.d3_female_dh_dead);
                }
                break;
            case "witch-doctor":
                if (accountInformation.getFallenHeroes().get(i).getGender() == 0) {
                    frame.setImageResource(R.drawable.d3_male_wd_dead);
                } else if (accountInformation.getFallenHeroes().get(i).getGender() == 1) {
                    frame.setImageResource(R.drawable.d3_female_wd_dead);
                }
                break;
            case "necromancer":
                if (accountInformation.getFallenHeroes().get(i).getGender() == 0) {
                    frame.setImageResource(R.drawable.d3_male_necromancer_dead);
                } else if (accountInformation.getFallenHeroes().get(i).getGender() == 1) {
                    frame.setImageResource(R.drawable.d3_female_necromancer_dead);
                }
                break;
            case "monk":
                if (accountInformation.getFallenHeroes().get(i).getGender() == 0) {
                    frame.setImageResource(R.drawable.d3_male_monk_dead);
                } else if (accountInformation.getFallenHeroes().get(i).getGender() == 1) {
                    frame.setImageResource(R.drawable.d3_female_monk_dead);
                }
                break;
            case "crusader":
                if (accountInformation.getFallenHeroes().get(i).getGender() == 0) {
                    frame.setImageResource(R.drawable.d3_male_crusader_dead);
                } else if (accountInformation.getFallenHeroes().get(i).getGender() == 1) {
                    frame.setImageResource(R.drawable.d3_female_crusader_dead);
                }
                break;
        }
    }

    private void sortHeroes() {
        accountInformation.getHeroes().sort((hero1, hero2) -> {
            if (hero1.getLastUpdated() > hero2.getLastUpdated()) {
                return -1;
            }
            return 0;
        });
    }

    private void setCharacterFrames() {

        setProgression();

        for (int i = 0; i < accountInformation.getHeroes().size(); i++) {
            ConstraintLayout constraintLayoutCharacter = new ConstraintLayout(getApplicationContext());
            constraintLayoutCharacter.setId(i);

            ConstraintLayout.LayoutParams frameParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, 500);

            ImageView frame = new ImageView(getApplicationContext());
            frame.setId(100 + i);
            frame.setLayoutParams(frameParams);

            if(accountInformation.getHeroes().get(i).getHardcore()){
                frame.setImageResource(R.drawable.d3_character_frame_hardcore);
            }else{
                frame.setImageResource(R.drawable.d3_character_frame);
            }

            ConstraintLayout.LayoutParams portraitParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, 320);

            ImageView portrait = new ImageView(getApplicationContext());
            portrait.setId(i + 1000);
            portrait.setImageDrawable(portraits.get(i));
            portrait.setLayoutParams(portraitParams);

            TextView name = new TextView(getApplicationContext());
            name.setText(accountInformation.getHeroes().get(i).getName());
            name.setTextColor(Color.parseColor("#937a51"));

            name.setTextSize(17);
            name.setGravity(Gravity.CENTER);

            TextView eliteKills = new TextView(getApplicationContext());
            eliteKills.setId(10000 + i);
            String eliteKillsText = accountInformation.getHeroes().get(i).getKills().getElites() + " Elite Kills";
            eliteKills.setText(eliteKillsText);
            eliteKills.setTextColor(Color.parseColor("#a99877"));
            eliteKills.setTextSize(13);
            eliteKills.setGravity(Gravity.CENTER);

            TextView level = new TextView(getApplicationContext());
            level.setId(100000 + i);
            level.setText(String.valueOf(accountInformation.getHeroes().get(i).getLevel()));
            level.setTextSize(15);
            level.setGravity(Gravity.CENTER);
            level.setTypeface(null, Typeface.BOLD);

            if (accountInformation.getHeroes().get(i).getHardcore()) {
                level.setTextColor(Color.parseColor("#b00000"));
            } else {
                level.setTextColor(Color.parseColor("#a99877"));
            }


            LinearLayout linearLayoutSeasonal = new LinearLayout(getApplicationContext());
            linearLayoutSeasonal.setId(1000000 + i);
            linearLayoutSeasonal.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutSeasonal.setGravity(Gravity.CENTER);

            linearLayoutSeasonal.addView(name);
            if (accountInformation.getHeroes().get(i).getSeasonal()) {
                ImageView leaf = new ImageView(getApplicationContext());
                leaf.setImageDrawable(getResources().getDrawable(R.drawable.leaf_seasonal, getTheme()));
                linearLayoutSeasonal.addView(leaf);
            }

            constraintLayoutCharacter.addView(frame);
            constraintLayoutCharacter.addView(portrait);
            constraintLayoutCharacter.addView(linearLayoutSeasonal);
            constraintLayoutCharacter.addView(eliteKills);
            constraintLayoutCharacter.addView(level);

            ConstraintSet setFrame = new ConstraintSet();
            setFrame.clone(constraintLayoutCharacter);
            setFrame.connect(frame.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
            setFrame.connect(frame.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
            setFrame.connect(frame.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
            setFrame.connect(frame.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);

            setFrame.connect(portrait.getId(), ConstraintSet.TOP, frame.getId(), ConstraintSet.TOP, 25);
            setFrame.connect(portrait.getId(), ConstraintSet.LEFT, frame.getId(), ConstraintSet.LEFT, 0);
            setFrame.connect(portrait.getId(), ConstraintSet.RIGHT, frame.getId(), ConstraintSet.RIGHT, 0);

            setFrame.connect(linearLayoutSeasonal.getId(), ConstraintSet.TOP, portrait.getId(), ConstraintSet.BOTTOM, 0);
            setFrame.connect(linearLayoutSeasonal.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
            setFrame.connect(linearLayoutSeasonal.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
            setFrame.connect(linearLayoutSeasonal.getId(), ConstraintSet.BOTTOM, eliteKills.getId(), ConstraintSet.TOP, 5);

            setFrame.connect(eliteKills.getId(), ConstraintSet.TOP, linearLayoutSeasonal.getId(), ConstraintSet.BOTTOM, 0);
            setFrame.connect(eliteKills.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 10);
            setFrame.connect(eliteKills.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 50);
            setFrame.connect(eliteKills.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 20);

            setFrame.connect(level.getId(), ConstraintSet.TOP, linearLayoutSeasonal.getId(), ConstraintSet.BOTTOM, 0);
            setFrame.connect(level.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 360);
            setFrame.connect(level.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
            setFrame.connect(level.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 25);

            setFrame.applyTo(constraintLayoutCharacter);

            linearLayoutCharacters.addView(constraintLayoutCharacter);

            setOnClickCharacterFrame(constraintLayoutCharacter);
        }
    }

    private void setOnClickCharacterFrame(ConstraintLayout constraintLayoutCharacter) {
        constraintLayoutCharacter.setOnClickListener(v -> {
            for (int j = 0; j < accountInformation.getHeroes().size(); j++) {
                if (j == constraintLayoutCharacter.getId()) {
                    characterID = accountInformation.getHeroes().get(j).getId();
                }
            }
            displayFragment();
        });
    }

    private void setProgression() {
        if (accountInformation.getProgression().getAct1()) {
            act1.setImageDrawable(getResources().getDrawable(R.drawable.act1_done, getTheme()));
        } else {
            act1.setImageDrawable(getResources().getDrawable(R.drawable.act1_not_done, getTheme()));
        }

        if (accountInformation.getProgression().getAct2()) {
            act2.setImageDrawable(getResources().getDrawable(R.drawable.act2_done, getTheme()));
        } else {
            act2.setImageDrawable(getResources().getDrawable(R.drawable.act2_not_done, getTheme()));
        }

        if (accountInformation.getProgression().getAct3()) {
            act3.setImageDrawable(getResources().getDrawable(R.drawable.act3_done, getTheme()));
        } else {
            act3.setImageDrawable(getResources().getDrawable(R.drawable.act3_not_done, getTheme()));
        }

        if (accountInformation.getProgression().getAct4()) {
            act4.setImageDrawable(getResources().getDrawable(R.drawable.act4_done, getTheme()));
        } else {
            act4.setImageDrawable(getResources().getDrawable(R.drawable.act4_not_done, getTheme()));
        }

        if (accountInformation.getProgression().getAct5()) {
            act5.setImageDrawable(getResources().getDrawable(R.drawable.act5_done, getTheme()));
        } else {
            act5.setImageDrawable(getResources().getDrawable(R.drawable.act5_not_done, getTheme()));
        }
    }

    protected void onResume() {
        super.onResume();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void callNextActivity(Class activity) {
        final Intent intent = new Intent(this, activity);
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        startActivity(intent);
    }

    public List<Drawable> getCharacterImage(List<Hero> heroes, Context context) {

        List<Drawable> portraits = new ArrayList<>();

        for (int i = 0; i < heroes.size(); i++) {
            Drawable characterImage;
            switch (heroes.get(i).getClassSlug()) {
                case "barbarian":
                    if (accountInformation.getHeroes().get(i).getGender() == 0) {
                        characterImage = context.getResources().getDrawable(R.drawable.barb_male, context.getTheme());
                        portraits.add(characterImage);
                    } else if (accountInformation.getHeroes().get(i).getGender() == 1) {
                        characterImage = context.getResources().getDrawable(R.drawable.barb_female, context.getTheme());
                        portraits.add(characterImage);
                    }
                    break;
                case "wizard":
                    if (accountInformation.getHeroes().get(i).getGender() == 0) {
                        characterImage = context.getResources().getDrawable(R.drawable.wizard_male, context.getTheme());
                        portraits.add(characterImage);
                    } else if (accountInformation.getHeroes().get(i).getGender() == 1) {
                        characterImage = context.getResources().getDrawable(R.drawable.wizard_female, context.getTheme());
                        portraits.add(characterImage);
                    }
                    break;
                case "demon-hunter":
                    if (accountInformation.getHeroes().get(i).getGender() == 0) {
                        characterImage = context.getResources().getDrawable(R.drawable.dh_male, context.getTheme());
                        portraits.add(characterImage);
                    } else if (accountInformation.getHeroes().get(i).getGender() == 1) {
                        characterImage = context.getResources().getDrawable(R.drawable.dh_female, context.getTheme());
                        portraits.add(characterImage);
                    }
                    break;
                case "witch-doctor":
                    if (accountInformation.getHeroes().get(i).getGender() == 0) {
                        characterImage = context.getResources().getDrawable(R.drawable.wd_male, context.getTheme());
                        portraits.add(characterImage);
                    } else if (accountInformation.getHeroes().get(i).getGender() == 1) {
                        characterImage = context.getResources().getDrawable(R.drawable.wd_female, context.getTheme());
                        portraits.add(characterImage);
                    }
                    break;
                case "necromancer":
                    if (accountInformation.getHeroes().get(i).getGender() == 0) {
                        characterImage = context.getResources().getDrawable(R.drawable.necro_male, context.getTheme());
                        portraits.add(characterImage);
                    } else if (accountInformation.getHeroes().get(i).getGender() == 1) {
                        characterImage = context.getResources().getDrawable(R.drawable.necro_female, context.getTheme());
                        portraits.add(characterImage);
                    }
                    break;
                case "monk":
                    if (accountInformation.getHeroes().get(i).getGender() == 0) {
                        characterImage = context.getResources().getDrawable(R.drawable.monk_male, context.getTheme());
                        portraits.add(characterImage);
                    } else if (accountInformation.getHeroes().get(i).getGender() == 1) {
                        characterImage = context.getResources().getDrawable(R.drawable.monk_female, context.getTheme());
                        portraits.add(characterImage);
                    }
                    break;
                case "crusader":
                    if (accountInformation.getHeroes().get(i).getGender() == 0) {
                        characterImage = context.getResources().getDrawable(R.drawable.crusader_male, context.getTheme());
                        portraits.add(characterImage);
                    } else if (accountInformation.getHeroes().get(i).getGender() == 1) {
                        characterImage = context.getResources().getDrawable(R.drawable.crusader_female, context.getTheme());
                        portraits.add(characterImage);
                    }
                    break;
            }
        }
        return portraits;
    }

    private void displayFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt("id", characterID);
        D3CharacterFragment d3CharacterFragment = new D3CharacterFragment();
        d3CharacterFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, d3CharacterFragment);
        fragmentTransaction.addToBackStack(null).commit();
        getSupportFragmentManager().executePendingTransactions();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (!(fragment instanceof IOnBackPressed) || !((IOnBackPressed) fragment).onBackPressed()) {
            super.onBackPressed();
        }
        if (fragment == null) {
            Intent intent = new Intent(this, GamesActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    public void showNoConnectionMessage(final Context context, final int responseCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogTransparent);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 20, 0, 0);

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(10, 20, 10, 0);

        TextView titleText = new TextView(context);

        titleText.setTextSize(20);
        titleText.setGravity(Gravity.CENTER_HORIZONTAL);
        titleText.setPadding(0, 20, 0, 20);
        titleText.setLayoutParams(layoutParams);
        titleText.setTextColor(Color.WHITE);

        TextView messageText = new TextView(context);

        messageText.setGravity(Gravity.CENTER_HORIZONTAL);
        messageText.setLayoutParams(layoutParams);
        messageText.setTextColor(Color.WHITE);

        Button button = new Button(context);

        button.setTextSize(18);
        button.setTextColor(Color.WHITE);
        button.setGravity(Gravity.CENTER);
        button.setWidth(200);
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

        if (responseCode == 404) {
            titleText.setText("Information Outdated");
            messageText.setText("Please login in game to update this character's information.");
            button.setText("OK");
        } else {
            titleText.setText("No Internet Connection");
            messageText.setText("Make sure that Wi-Fi or mobile data is turned on, then try again.");
            button.setText("Retry");
            button2.setText("Back");
        }

        final AlertDialog dialog = builder.show();
        Objects.requireNonNull(dialog.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setLayout(800, 500);

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setPadding(20, 20, 20, 20);

        LinearLayout buttonLayout = new LinearLayout(context);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonLayout.setGravity(Gravity.CENTER);
        buttonLayout.addView(button);
        buttonLayout.addView(button2);

        linearLayout.addView(titleText);
        linearLayout.addView(messageText);
        linearLayout.addView(buttonLayout);

        LinearLayout.LayoutParams layoutParamsWindow = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 20, 20, 20);

        dialog.addContentView(linearLayout, layoutParamsWindow);

        dialog.setOnCancelListener(dialog1 -> downloadAccountInformation());

        button.setOnClickListener(v -> dialog.cancel());
        button2.setOnClickListener(v -> onBackPressed());
    }

}
