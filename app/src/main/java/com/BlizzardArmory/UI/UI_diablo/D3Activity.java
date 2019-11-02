package com.BlizzardArmory.UI.UI_diablo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.BlizzardArmory.R;
import com.BlizzardArmory.UI.UI_overwatch.OWActivity;
import com.BlizzardArmory.UI.UI_starcraft.SC2Activity;
import com.BlizzardArmory.UI.UI_warcraft.WoWActivity;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.UserInformation;
import com.BlizzardArmory.connection.ConnectionService;
import com.BlizzardArmory.diablo.account.AccountInformation;
import com.BlizzardArmory.diablo.account.Hero;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class D3Activity extends AppCompatActivity {

    private SharedPreferences prefs;
    private BnOAuth2Helper bnOAuth2Helper;
    private BnOAuth2Params bnOAuth2Params;

    List<Drawable> portraits;

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
        final Gson gson = new GsonBuilder().create();

        try {

            Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024 * 5); // 1MB cap
            Network network = new BasicNetwork(new HurlStack());
            RequestQueue requestQueue = new RequestQueue(cache, network);
            requestQueue.start();

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI() + URLConstants.getD3URLBtagProfile()
                    + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken(), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            D3AccountInfo = response;
                            accountInformation = gson.fromJson(D3AccountInfo.toString(), AccountInformation.class);

                            portraits = getCharacterImage(accountInformation.getHeroes(), getApplicationContext());

                            String paragon = accountInformation.getParagonLevel() +
                                    " | " +
                                    "<font color=#FF0000>" +
                                    accountInformation.getParagonLevelHardcore() +
                                    "</font>";

                            paragonLevel.setText(Html.fromHtml(paragon, Html.FROM_HTML_MODE_LEGACY));
                            eliteKills.setText(String.valueOf(accountInformation.getKills().getElites()));
                            lifetimeKills.setText(String.valueOf(accountInformation.getKills().getMonsters()));

                            setCharacterFrames();

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
                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("test", error.toString());
                            //ConnectionService.showNoConnectionMessage(D3Activity.this);
                            finish();
                        }
                    });

            requestQueue.add(jsonRequest);


            Log.i("json", D3AccountInfo.toString());
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }


        //Button calls
        wowButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                callNextActivity(WoWActivity.class);
            }
        });

        sc2Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                callNextActivity(SC2Activity.class);
            }
        });

        owButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                callNextActivity(OWActivity.class);
            }
        });
    }

    private void setCharacterFrames() {

        setProgression();

        for (int i = 0; i < accountInformation.getHeroes().size(); i++) {

            LinearLayout.LayoutParams layoutParamsImage = new LinearLayout.LayoutParams(357, 276);


            LinearLayout.LayoutParams layoutParamsCharacters = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParamsCharacters.setMargins(0, 0, 20, 0);

            ImageView portrait = new ImageView(getApplicationContext());
            portrait.setImageDrawable(portraits.get(i));
            portrait.setLayoutParams(layoutParamsImage);

            TextView name = new TextView(getApplicationContext());
            name.setText(accountInformation.getHeroes().get(i).getName());
            if (accountInformation.getHeroes().get(i).getHardcore()) {
                name.setTextColor(Color.RED);
            } else {
                name.setTextColor(Color.WHITE);
            }
            name.setTextSize(15);
            name.setGravity(Gravity.CENTER);

            TextView eliteKills = new TextView(getApplicationContext());
            String eliteKillsText = "Elite Kills: " + accountInformation.getHeroes().get(i).getKills().getElites();
            eliteKills.setText(eliteKillsText);
            eliteKills.setTextColor(Color.WHITE);
            eliteKills.setTextSize(15);
            eliteKills.setGravity(Gravity.CENTER);

            TextView level = new TextView(getApplicationContext());
            String levelText = "Level: " + accountInformation.getHeroes().get(i).getLevel();
            level.setText(levelText);
            level.setTextColor(Color.WHITE);
            level.setTextSize(15);
            level.setGravity(Gravity.CENTER);

            final LinearLayout linearLayoutCharacter = new LinearLayout(getApplicationContext());
            linearLayoutCharacter.setOrientation(LinearLayout.VERTICAL);
            linearLayoutCharacter.setId(i);

            LinearLayout linearLayoutSeasonal = new LinearLayout(getApplicationContext());
            linearLayoutSeasonal.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutSeasonal.setGravity(Gravity.CENTER);

            linearLayoutSeasonal.addView(name);
            if (accountInformation.getHeroes().get(i).getSeasonal()) {
                ImageView leaf = new ImageView(getApplicationContext());
                leaf.setImageDrawable(getResources().getDrawable(R.drawable.leaf_seasonal, getTheme()));
                linearLayoutSeasonal.addView(leaf);
            }

            linearLayoutCharacter.addView(portrait);
            linearLayoutCharacter.addView(linearLayoutSeasonal);
            linearLayoutCharacter.addView(eliteKills);
            linearLayoutCharacter.addView(level);

            linearLayoutCharacter.setLayoutParams(layoutParamsCharacters);
            linearLayoutCharacters.addView(linearLayoutCharacter);

            linearLayoutCharacter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < accountInformation.getHeroes().size(); i++) {
                        if (i == linearLayoutCharacter.getId()) {
                            characterID = accountInformation.getHeroes().get(i).getId();
                        }
                    }
                    try {
                        if (ConnectionService.isConnected()) {
                            displayFragment();
                        } else {
                            //ConnectionService.showNoConnectionMessage(getApplicationContext());
                        }
                    } catch (Exception e) {
                        Log.e("Error-test", e.toString());
                    }

                }
            });
        }
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
}
