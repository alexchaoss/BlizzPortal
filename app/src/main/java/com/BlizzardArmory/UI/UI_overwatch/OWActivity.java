package com.BlizzardArmory.UI.UI_overwatch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.BlizzardArmory.R;
import com.BlizzardArmory.UI.UI_diablo.D3Activity;
import com.BlizzardArmory.UI.UI_starcraft.SC2Activity;
import com.BlizzardArmory.UI.UI_warcraft.WoWActivity;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.UserInformation;
import com.BlizzardArmory.overwatch.Heroes.Hero;
import com.BlizzardArmory.overwatch.Profile;
import com.BlizzardArmory.overwatch.TopHeroes.TopHero;
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
import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
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
    private ArrayList<TopHero> topHeroesQuickPlay;
    private ArrayList<TopHero> topHeroesCompetitive;
    private ArrayList<Hero> careerQuickPlay;
    private ArrayList<Hero> careerCompetitive;

    private ImageView avatar;
    private TextView name;
    private ImageView levelIcon;
    private ImageView prestigeIcon;
    private TextView level;
    private ImageView ratingIconTank;
    private ImageView ratingRankIconTank;
    private TextView ratingTank;
    private ImageView ratingIconDamage;
    private ImageView ratingRankIconDamage;
    private TextView ratingDamage;
    private ImageView ratingIconSupport;
    private ImageView ratingRankIconSupport;
    private TextView ratingSupport;
    private TextView gamesWon;
    private ImageView endorsementIcon;
    private TextView endorsement;
    private ImageView topCharacter;

    private final String TIME_PLAYED = "Time Played";
    private final String GAMES_WON = "Games Won";
    private final String WEAPON_ACCURACY = "Weapon Accuracy";
    private final String ELIMINATIONS_PER_LIFE = "Eliminations per Life";
    private final String MULTIKILL_BEST = "Multikill - Best";
    private final String OBJECTIVE_KILLS = "Objective Kills";
    private final String[] sortHeroList = {TIME_PLAYED, GAMES_WON, WEAPON_ACCURACY, ELIMINATIONS_PER_LIFE, MULTIKILL_BEST, OBJECTIVE_KILLS};
    private ArrayList<String> sortCareerHeroes = new ArrayList<>();

    private LinearLayout heroList;

    private TextView quickplay;
    private TextView competitive;
    private LinearLayout quickComp;
    private boolean comp = false;

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
        ratingIconTank = findViewById(R.id.rating_icon_tank);
        ratingRankIconTank = findViewById(R.id.rating_icon_rank_tank);
        ratingTank = findViewById(R.id.rating_tank);
        ratingIconDamage = findViewById(R.id.rating_icon_damage);
        ratingRankIconDamage = findViewById(R.id.rating_icon_rank_damage);
        ratingDamage = findViewById(R.id.rating_damage);
        ratingIconSupport = findViewById(R.id.rating_icon_support);
        ratingRankIconSupport = findViewById(R.id.rating_icon_rank_support);
        ratingSupport = findViewById(R.id.rating_support);
        level = findViewById(R.id.level);
        gamesWon = findViewById(R.id.games_won);
        topCharacter = findViewById(R.id.top_character);
        /*endorsementIcon = findViewById(R.id.endorsement_icon);
        endorsementIcon.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        endorsement = findViewById(R.id.endorsement_level);*/
        prestigeIcon = findViewById(R.id.prestige_icon);
        Spinner topHeroesListSpinner = findViewById(R.id.spinner);
        Spinner careerListSpinner = findViewById(R.id.spinner_career);
        heroList = findViewById(R.id.hero_list);
        competitive = findViewById(R.id.competitive);
        quickplay = findViewById(R.id.quickplay);
        quickComp  = findViewById(R.id.quick_comp);

        GradientDrawable switchCompQuickBorder = new GradientDrawable();
        switchCompQuickBorder.setCornerRadius(5);
        switchCompQuickBorder.setStroke(3, Color.parseColor("#FFFFFF"));
        quickComp.setBackground(switchCompQuickBorder);

        GradientDrawable switchCompQuickRadius = new GradientDrawable();
        switchCompQuickRadius.setCornerRadius(5);
        switchCompQuickRadius.setColor(Color.parseColor("#FFFFFF"));
        quickplay.setBackground(switchCompQuickRadius);

        btag.setText(UserInformation.getBattleTag());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        prefs = PreferenceManager.getDefaultSharedPreferences(OWActivity.this);
        bnOAuth2Params = Objects.requireNonNull(OWActivity.this.getIntent().getExtras()).getParcelable(BnConstants.BUNDLE_BNPARAMS);
        assert bnOAuth2Params != null;
        bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

        final Gson gson = new GsonBuilder().create();

        try {

            Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024 * 5);
            Network network = new BasicNetwork(new HurlStack());
            RequestQueue requestQueue = new RequestQueue(cache, network);
            requestQueue.start();

            String testURL = "https://ow-api.com/v1/stats/pc/us/FITS-31239/complete";
            Log.i("URL", URLConstants.getOWProfile());

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLConstants.getOWProfile(), null,
                    response -> {

                        try {
                            accountInformation = gson.fromJson(response.toString(), Profile.class);
                            Log.i("Games won", "" + accountInformation.getQuickPlayStats().getGames().getWon());

                            downloadAvatar(requestQueue);
                            setName(requestQueue);
                            setGamesWon();
                            setRatingInformation(requestQueue);
                            //downloadEndorsementIcon(requestQueue);
                            //endorsement.setText(String.valueOf(accountInformation.getEndorsement()));

                            setTopHeroesLists();
                            setCareerLists();
                            setSpinnerCareerList(careerQuickPlay);

                            setTopCharacterImage(topHeroesQuickPlay.get(0).getClass().getSimpleName());
                            setSpinnerTopHeroes(topHeroesListSpinner);
                            setSpinnerCareer(careerListSpinner);

                            quickplay.setOnClickListener(v -> {
                                if(comp) {
                                    comp = false;
                                    quickplay.setBackground(switchCompQuickRadius);
                                    quickplay.setTextColor(Color.parseColor("#000000"));
                                    competitive.setTextColor(Color.parseColor("#FFFFFF"));
                                    competitive.setBackgroundColor(0);
                                    setTopCharacterImage(topHeroesQuickPlay.get(0).getClass().getSimpleName());
                                    setSpinnerTopHeroes(topHeroesListSpinner);
                                    setSpinnerCareerList(careerQuickPlay);
                                    setSpinnerCareer(careerListSpinner);
                                }
                            });

                            competitive.setOnClickListener(v -> {
                                if(!comp) {
                                    comp = true;
                                    competitive.setBackground(switchCompQuickRadius);
                                    competitive.setTextColor(Color.parseColor("#000000"));
                                    quickplay.setBackgroundColor(0);
                                    quickplay.setTextColor(Color.parseColor("#FFFFFF"));
                                    setTopCharacterImage(topHeroesCompetitive.get(0).getClass().getSimpleName());
                                    setSpinnerTopHeroes(topHeroesListSpinner);
                                    setSpinnerCareerList(careerCompetitive);
                                    setSpinnerCareer(careerListSpinner);
                                }
                            });

                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            loadingCircle.setVisibility(View.GONE);

                        } catch (Exception e) {
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            loadingCircle.setVisibility(View.GONE);
                            Log.e("Error", Arrays.toString(e.getStackTrace()));
                        }

                    },
                    error -> {
                        Log.e("Error", error.toString());
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

    private void setCareerLists() {
        careerQuickPlay = accountInformation.getQuickPlayStats().getCareerStats().getHeroList();
        careerCompetitive = accountInformation.getCompetitiveStats().getCareerStats().getHeroList();

        for (int i = 0; i < careerQuickPlay.size(); i++) {
            if (careerQuickPlay.get(i) == null) {
                careerQuickPlay.remove(i);
                i--;
            }
        }

        for (int i = 0; i < careerCompetitive.size(); i++) {
            if (careerCompetitive.get(i) == null) {
                careerCompetitive.remove(i);
                i--;
            }
        }
    }

    private void setSpinnerCareerList(ArrayList<Hero> list) {

        for (int i = 0; i < list.size(); i++) {
            String tempName;
            if (list.get(i).getClass().getSimpleName().equals("WreckingBall")) {
                tempName = "Wrecking Ball";
            } else if (list.get(i).getClass().getSimpleName().equals("Dva")) {
                tempName = "D.Va";
            } else if(list.get(i).getClass().getSimpleName().equals("Soldier76")){
                tempName = "Soldier: 76";
            }else if(list.get(i).getClass().getSimpleName().equals("AllHeroes")){
                tempName = "All Heroes";
            }else{
                tempName = list.get(i).getClass().getSimpleName() + " ";
            }
            sortCareerHeroes.add(tempName);
        }

    }

    private void setTopHeroesLists() {
        topHeroesQuickPlay = accountInformation.getQuickPlayStats().getTopHeroes().getHeroList();
        topHeroesCompetitive = accountInformation.getCompetitiveStats().getTopHeroes().getHeroList();

        for (int i = 0; i < topHeroesQuickPlay.size(); i++) {
            if (topHeroesQuickPlay.get(i) == null) {
                topHeroesQuickPlay.remove(i);
                i--;
            }
        }

        for (int i = 0; i < topHeroesCompetitive.size(); i++) {
            if (topHeroesCompetitive.get(i) == null) {
                topHeroesCompetitive.remove(i);
                i--;
            }
        }

        sortList(topHeroesCompetitive, sortHeroList[0]);
        sortList(topHeroesQuickPlay, sortHeroList[0]);
    }

    private void setSpinnerTopHeroes(Spinner spinner) {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, sortHeroList) {

            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setAllCaps(true);
                tv.setBackgroundColor(Color.WHITE);
                tv.setTextSize(15);
                tv.setGravity(Gravity.CENTER);
                return view;


            }
        };

        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(Color.parseColor("#CCCCCC"));
                ((TextView) view).setTextSize(15);
                ((TextView) view).setGravity(Gravity.CENTER_VERTICAL);


                if(comp) {
                    setHeroList((String) parent.getItemAtPosition(position), topHeroesCompetitive);
                }{
                    sortList(topHeroesQuickPlay, sortHeroList[position]);
                    setHeroList((String) parent.getItemAtPosition(position), topHeroesQuickPlay);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER);
                ((TextView) parent.getChildAt(0)).setTextColor(0);
            }
        });
    }

    private void setSpinnerCareer(Spinner spinner) {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, sortCareerHeroes) {

            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setAllCaps(true);
                tv.setBackgroundColor(Color.WHITE);
                tv.setTextSize(15);
                tv.setGravity(Gravity.CENTER);
                return view;
            }
        };

        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(Color.parseColor("#CCCCCC"));
                ((TextView) view).setTextSize(15);
                ((TextView) view).setGravity(Gravity.CENTER_VERTICAL);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER);
                ((TextView) parent.getChildAt(0)).setTextColor(0);
            }
        });
    }

    private void setHeroList(String itemSelected, ArrayList<TopHero> heroes) {
        heroList.removeAllViews();

        for (int i = 0; i < heroes.size(); i++) {
            LinearLayout linearLayout = new LinearLayout(getApplicationContext());
            heroList.addView(linearLayout);

            LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(100, 100);
            iconParams.setMarginEnd(5);

            RelativeLayout.LayoutParams nameParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            nameParams.setMarginStart(10);
            nameParams.addRule(RelativeLayout.CENTER_VERTICAL);

            RelativeLayout.LayoutParams dataParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            dataParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            dataParams.addRule(RelativeLayout.CENTER_VERTICAL);
            dataParams.setMarginEnd(10);

            ImageView icon = new ImageView(getApplicationContext());
            icon.setBackgroundResource(getHeroIcon(heroes.get(i).getClass().getSimpleName()));
            GradientDrawable iconBorder = new GradientDrawable();
            iconBorder.setCornerRadius(5);
            iconBorder.setStroke(3, Color.parseColor("#CCCCCC"));
            icon.setImageDrawable(iconBorder);

            RelativeLayout relativeLayout = new RelativeLayout(getApplicationContext());
            relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            GradientDrawable layoutBackground = new GradientDrawable();
            layoutBackground.setCornerRadius(5);
            layoutBackground.setColor(Color.parseColor("#283655"));
            relativeLayout.setBackground(layoutBackground);
            relativeLayout.setPadding(5,5,5,5);

            TextView name = new TextView(getApplicationContext());
            name.setAllCaps(true);
            name.setTextColor(Color.WHITE);
            name.setTextSize(20);
            String tempName;

            if (heroes.get(i).getClass().getSimpleName().equals("WreckingBall")) {
                tempName = "Wrecking Ball ";
            } else if (heroes.get(i).getClass().getSimpleName().equals("Dva")) {
                tempName = "D.Va ";
            } else if(heroes.get(i).getClass().getSimpleName().equals("Soldier76")){
                tempName = "Soldier: 76 ";
            }else{
                tempName = heroes.get(i).getClass().getSimpleName() + " ";
            }
            name.setText(tempName);
            Typeface face = ResourcesCompat.getFont(this, R.font.bignoodletoo);
            name.setTypeface(face);

            TextView data = new TextView(getApplicationContext());
            data.setTextColor(Color.WHITE);
            data.setTextSize(15);

            linearLayout.addView(icon, iconParams);
            linearLayout.addView(relativeLayout);

            View backgroundColor = new View(getApplicationContext());

            double viewWidth;

            if(i > 0) {
                switch (itemSelected) {
                    case TIME_PLAYED:
                        double fullWidth = getSeconds(heroes.get(0));
                        viewWidth = (getSeconds(heroes.get(i)) * 100) / fullWidth;
                        backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams((int) (viewWidth * 8.5) , RelativeLayout.LayoutParams.MATCH_PARENT));
                        data.setText(heroes.get(i).getTimePlayed());
                        break;
                    case GAMES_WON:
                        fullWidth = heroes.get(0).getGamesWon();
                        viewWidth = (heroes.get(i).getGamesWon() * 100) / fullWidth;
                        backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams((int) (viewWidth * 8.5) , RelativeLayout.LayoutParams.MATCH_PARENT));
                        data.setText(String.valueOf( (int) heroes.get(i).getGamesWon()));
                        break;
                    case WEAPON_ACCURACY:
                        fullWidth = heroes.get(0).getWeaponAccuracy();
                        viewWidth = (heroes.get(i).getWeaponAccuracy() * 100) / fullWidth;
                        backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams((int) (viewWidth * 8.5) , RelativeLayout.LayoutParams.MATCH_PARENT));
                        String tempData = (int) heroes.get(i).getWeaponAccuracy() + "%";
                        data.setText(tempData);
                        break;
                    case ELIMINATIONS_PER_LIFE:
                        fullWidth = heroes.get(0).getEliminationsPerLife();
                        viewWidth = (heroes.get(i).getEliminationsPerLife() * 100) / fullWidth;
                        backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams((int) (viewWidth * 8.5) , RelativeLayout.LayoutParams.MATCH_PARENT));
                        data.setText(String.valueOf(heroes.get(i).getEliminationsPerLife()));
                        break;
                    case MULTIKILL_BEST:
                        fullWidth = heroes.get(0).getMultiKillBest();
                        viewWidth = (heroes.get(i).getMultiKillBest() * 100) / fullWidth;
                        backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams((int) (viewWidth * 8.5) , RelativeLayout.LayoutParams.MATCH_PARENT));
                        data.setText(String.valueOf((int) heroes.get(i).getMultiKillBest()));
                        break;
                    case OBJECTIVE_KILLS:
                        fullWidth = heroes.get(0).getObjectiveKills();
                        viewWidth = (heroes.get(i).getObjectiveKills() * 100) / fullWidth;
                        backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams((int) (viewWidth * 8.5) , RelativeLayout.LayoutParams.MATCH_PARENT));
                        data.setText(String.valueOf((int) heroes.get(i).getObjectiveKills()));
                }
            }else{
                switch (itemSelected) {
                    case TIME_PLAYED:
                        backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                        data.setText(String.valueOf(heroes.get(i).getTimePlayed()));
                        break;
                    case GAMES_WON:
                        backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                        data.setText(String.valueOf((int) heroes.get(i).getGamesWon()));
                        break;
                    case WEAPON_ACCURACY:
                        backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                        String tempData = (int) heroes.get(i).getWeaponAccuracy() + "%";
                        data.setText(tempData);
                        break;
                    case ELIMINATIONS_PER_LIFE:
                        backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                        data.setText(String.valueOf(heroes.get(i).getEliminationsPerLife()));
                        break;
                    case MULTIKILL_BEST:
                        backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                        data.setText(String.valueOf((int) heroes.get(i).getMultiKillBest()));
                        break;
                    case OBJECTIVE_KILLS:
                        backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                        data.setText(String.valueOf((int) heroes.get(i).getObjectiveKills()));
                }

            }

            setBackgroundColor(backgroundColor, heroes.get(i).getClass().getSimpleName());

            relativeLayout.addView(backgroundColor);
            relativeLayout.addView(name, nameParams);
            relativeLayout.addView(data, dataParams);
        }
    }

    private void setBackgroundColor(View backgroundColor, String topCharacterName){
        GradientDrawable progressBar = new GradientDrawable();
        progressBar.setCornerRadius(5);


        switch (topCharacterName) {
            case "Ana":
                progressBar.setColor(Color.parseColor("#718ab3"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Ashe":
                progressBar.setColor(Color.parseColor("#b3a05f"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Baptiste":
                progressBar.setColor(Color.parseColor("#2892a8"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Bastion":
                progressBar.setColor(Color.parseColor("#7c8f7b"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Brigitte":
                progressBar.setColor(Color.parseColor("#be736e"));
                backgroundColor.setBackground(progressBar);
                break;
            case "DVa":
                progressBar.setColor(Color.parseColor("#ed93c7"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Doomfist":
                progressBar.setColor(Color.parseColor("#815049"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Genji":
                progressBar.setColor(Color.parseColor("#97ef43"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Hanzo":
                progressBar.setColor(Color.parseColor("#b9b48a"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Junkrat":
                progressBar.setColor(Color.parseColor("#ecbd53"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Lúcio":
                progressBar.setColor(Color.parseColor("#85c952"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Mccree":
                progressBar.setColor(Color.parseColor("#ae595c"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Mei":
                progressBar.setColor(Color.parseColor("#6faced"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Mercy":
                progressBar.setColor(Color.parseColor("#ebe8bb"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Moira":
                progressBar.setColor(Color.parseColor("#803c51"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Orisa":
                progressBar.setColor(Color.parseColor("#468c43"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Pharah":
                progressBar.setColor(Color.parseColor("#3e7dca"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Reaper":
                progressBar.setColor(Color.parseColor("#7d3e51"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Reinhardt":
                progressBar.setColor(Color.parseColor("#929da3"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Roadhog":
                progressBar.setColor(Color.parseColor("#b68c52"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Sigma":
                progressBar.setColor(Color.parseColor("#33bbaa"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Soldier76":
                progressBar.setColor(Color.parseColor("#697794"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Sombra":
                progressBar.setColor(Color.parseColor("#7359ba"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Symmetra":
                progressBar.setColor(Color.parseColor("#8ebccc"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Torbjörn":
                progressBar.setColor(Color.parseColor("#c0726e"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Tracer":
                progressBar.setColor(Color.parseColor("#d79342"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Widowmaker":
                progressBar.setColor(Color.parseColor("#9e6aa8"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Winston":
                progressBar.setColor(Color.parseColor("#a2a6bf"));
                backgroundColor.setBackground(progressBar);
                break;
            case "WreckingBall":
                progressBar.setColor(Color.parseColor("#4a575f"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Zarya":
                progressBar.setColor(Color.parseColor("#e77eb6"));
                backgroundColor.setBackground(progressBar);
                break;
            case "Zenyatta":
                progressBar.setColor(Color.parseColor("#ede582"));
                backgroundColor.setBackground(progressBar);
                break;
        }
    }

    private int getHeroIcon(String topCharacterName) {
        int id = 0;
        switch (topCharacterName) {
            case "Ana":
                id = R.drawable.ana_icon;
                break;
            case "Ashe":
                id = R.drawable.ashe_icon;
                break;
            case "Baptiste":
                id = R.drawable.baptiste_icon;
                break;
            case "Bastion":
                id = R.drawable.bastion_icon;
                break;
            case "Brigitte":
                id = R.drawable.brigitte_icon;
                break;
            case "DVa":
                id = R.drawable.dva_icon;
                break;
            case "Doomfist":
                id = R.drawable.doomfist_icon;
                break;
            case "Genji":
                id = R.drawable.genji_icon;
                break;
            case "Hanzo":
                id = R.drawable.hanzo_icon;
                break;
            case "Junkrat":
                id = R.drawable.junkrat_icon;
                break;
            case "Lúcio":
                id = R.drawable.lucio_icon;
                break;
            case "Mccree":
                id = R.drawable.mccree_icon;
                break;
            case "Mei":
                id = R.drawable.mei_icon;
                break;
            case "Mercy":
                id = R.drawable.mercy_icon;
                break;
            case "Moira":
                id = R.drawable.moira_icon;
                break;
            case "Orisa":
                id = R.drawable.orisa_icon;
                break;
            case "Pharah":
                id = R.drawable.pharah_icon;
                break;
            case "Reaper":
                id = R.drawable.reaper_icon;
                break;
            case "Reinhardt":
                id = R.drawable.reinhardt_icon;
                break;
            case "Roadhog":
                id = R.drawable.roadhog_icon;
                break;
            case "Sigma":
                id = R.drawable.sigma_icon;
                break;
            case "Soldier76":
                id = R.drawable.soldier_icon;
                break;
            case "Sombra":
                id = R.drawable.sombra_icon;
                break;
            case "Symmetra":
                id = R.drawable.symmetra_icon;
                break;
            case "Torbjörn":
                id = R.drawable.torbjorn_icon;
                break;
            case "Tracer":
                id = R.drawable.tracer_icon;
                break;
            case "Widowmaker":
                id = R.drawable.widow_icon;
                break;
            case "Winston":
                id = R.drawable.winston_icon;
                break;
            case "WreckingBall":
                id = R.drawable.wrecking_ball_icon;
                break;
            case "Zarya":
                id = R.drawable.zarya_icon;
                break;
            case "Zenyatta":
                id = R.drawable.zenyatta_icon;
                break;
        }
        return id;
    }

    private void setTopCharacterImage(String topCharacterName) {
        switch (topCharacterName) {
            case "Ana":
                topCharacter.setImageResource(R.drawable.ana_portrait);
                break;
            case "Ashe":
                topCharacter.setImageResource(R.drawable.ashe_portrait);
                break;
            case "Baptiste":
                topCharacter.setImageResource(R.drawable.baptiste_portrait);
                break;
            case "Bastion":
                topCharacter.setImageResource(R.drawable.bastion_portrait);
                break;
            case "Brigitte":
                topCharacter.setImageResource(R.drawable.brigitte_portrait);
                break;
            case "DVa":
                topCharacter.setImageResource(R.drawable.dva_portrait);
                break;
            case "Doomfist":
                topCharacter.setImageResource(R.drawable.doomfist_portrait);
                break;
            case "Genji":
                topCharacter.setImageResource(R.drawable.genji_portrait);
                break;
            case "Hanzo":
                topCharacter.setImageResource(R.drawable.hanzo_portrait);
                break;
            case "Junkrat":
                topCharacter.setImageResource(R.drawable.junkrat_portrait);
                break;
            case "Lúcio":
                topCharacter.setImageResource(R.drawable.lucio_portrait);
                break;
            case "Mccree":
                topCharacter.setImageResource(R.drawable.mccree_portrait);
                break;
            case "Mei":
                topCharacter.setImageResource(R.drawable.mei_portrait);
                break;
            case "Mercy":
                topCharacter.setImageResource(R.drawable.mercy_portrait);
                break;
            case "Moira":
                topCharacter.setImageResource(R.drawable.moira_portrait);
                break;
            case "Orisa":
                topCharacter.setImageResource(R.drawable.orisa_portrait);
                break;
            case "Pharah":
                topCharacter.setImageResource(R.drawable.pharah_portrait);
                break;
            case "Reaper":
                topCharacter.setImageResource(R.drawable.reaper_portrait);
                break;
            case "Reinhardt":
                topCharacter.setImageResource(R.drawable.reinhardt_portrait);
                break;
            case "Roadhog":
                topCharacter.setImageResource(R.drawable.roadhog_portrait);
                break;
            case "Sigma":
                topCharacter.setImageResource(R.drawable.sigma_portrait);
                break;
            case "Soldier76":
                topCharacter.setImageResource(R.drawable.soldier_portrait);
                break;
            case "Sombra":
                topCharacter.setImageResource(R.drawable.sombra_portrait);
                break;
            case "Symmetra":
                topCharacter.setImageResource(R.drawable.symmetra_portrait);
                break;
            case "Torbjörn":
                topCharacter.setImageResource(R.drawable.torbjorn_portrait);
                break;
            case "Tracer":
                topCharacter.setImageResource(R.drawable.tracer_portrait);
                break;
            case "Widowmaker":
                topCharacter.setImageResource(R.drawable.widow_portrait);
                break;
            case "Winston":
                topCharacter.setImageResource(R.drawable.winston_portrait);
                break;
            case "WreckingBall":
                topCharacter.setImageResource(R.drawable.wrecking_ball_portrait);
                break;
            case "Zarya":
                topCharacter.setImageResource(R.drawable.zarya_portrait);
                break;
            case "Zenyatta":
                topCharacter.setImageResource(R.drawable.zenyatta_portrait);
                break;
        }
    }

    private void sortList(ArrayList<TopHero> topHeroes, String howToSort) {
        switch (howToSort) {
            case TIME_PLAYED:
                topHeroes.sort((hero1, hero2) -> {
                    int secondsHero1 = getSeconds(hero1);
                    int secondsHero2 = getSeconds(hero2);
                    if (secondsHero1 > secondsHero2) {
                        return -1;
                    } else if (secondsHero1 < secondsHero2) {
                        return 1;
                    }
                    return 0;
                });
                break;
            case GAMES_WON:
                topHeroes.sort((hero1, hero2) -> {
                    if (hero1.getGamesWon() > hero2.getGamesWon()) {
                        return -1;
                    } else if (hero1.getGamesWon() < hero2.getGamesWon()) {
                        return 1;
                    }
                    return 0;
                });
                break;
            case WEAPON_ACCURACY:
                topHeroes.sort((hero1, hero2) -> {
                    if (hero1.getWeaponAccuracy() > hero2.getWeaponAccuracy()) {
                        return -1;
                    } else if (hero1.getWeaponAccuracy() < hero2.getWeaponAccuracy()) {
                        return 1;
                    }
                    return 0;
                });
                break;
            case ELIMINATIONS_PER_LIFE:
                topHeroes.sort((hero1, hero2) -> {
                    if (hero1.getEliminationsPerLife() > hero2.getEliminationsPerLife()) {
                        return -1;
                    } else if (hero1.getEliminationsPerLife() < hero2.getEliminationsPerLife()) {
                        return 1;
                    }
                    return 0;
                });
                break;
            case MULTIKILL_BEST:
                topHeroes.sort((hero1, hero2) -> {
                    if (hero1.getMultiKillBest() > hero2.getMultiKillBest()) {
                        return -1;
                    } else if (hero1.getMultiKillBest() < hero2.getMultiKillBest()) {
                        return 1;
                    }
                    return 0;
                });
                break;
            case OBJECTIVE_KILLS:
                topHeroes.sort((hero1, hero2) -> {
                    if (hero1.getObjectiveKills() > hero2.getObjectiveKills()) {
                        return -1;
                    } else if (hero1.getObjectiveKills() < hero2.getObjectiveKills()) {
                        return 1;
                    }
                    return 0;
                });
        }
    }

    private int getSeconds(TopHero hero) {
        int secondsHero1 = 0;
        if (StringUtils.countMatches(hero.getTimePlayed(), ":") == 2) {
            secondsHero1 += Integer.valueOf(hero.getTimePlayed().substring(0, hero.getTimePlayed().indexOf(":"))) * 3600;
            secondsHero1 += Integer.valueOf(hero.getTimePlayed().substring(hero.getTimePlayed().indexOf(":") + 1, hero.getTimePlayed().lastIndexOf(":"))) * 60;
            secondsHero1 += Integer.valueOf(hero.getTimePlayed().substring(hero.getTimePlayed().lastIndexOf(":") + 1));
        } else if (StringUtils.countMatches(hero.getTimePlayed(), ":") == 1) {
            secondsHero1 += Integer.valueOf(hero.getTimePlayed().substring(0, hero.getTimePlayed().indexOf(":"))) * 60;
            secondsHero1 += Integer.valueOf(hero.getTimePlayed().substring(hero.getTimePlayed().lastIndexOf(":") + 1));
        } else {
            secondsHero1 = Integer.valueOf(hero.getTimePlayed());
        }
        return secondsHero1;
    }

    private void setRatingInformation(RequestQueue requestQueue) {
        if (accountInformation.getRating() > 0) {
            for (int i = 0; i < accountInformation.getRatings().size(); i++) {
                if (accountInformation.getRatings().get(i).getRole().equals("tank") && accountInformation.getRatings().get(i).getLevel() > 0) {
                    ratingTank.setText(String.valueOf(accountInformation.getRatings().get(i).getLevel()));
                    downloadRatingIcon(requestQueue, accountInformation.getRatings().get(i).getRoleIcon(), ratingIconTank);
                    downloadRatingIcon(requestQueue, accountInformation.getRatings().get(i).getRankIcon(), ratingRankIconTank);
                } else {
                    if (accountInformation.getRatings().get(i).getLevel() == 0) {
                        ratingIconTank.setVisibility(View.GONE);
                        ratingRankIconTank.setVisibility(View.GONE);
                        ratingTank.setVisibility(View.GONE);
                    }
                }
                if (accountInformation.getRatings().get(i).getRole().equals("damage") && accountInformation.getRatings().get(i).getLevel() > 0) {
                    ratingDamage.setText(String.valueOf(accountInformation.getRatings().get(i).getLevel()));
                    downloadRatingIcon(requestQueue, accountInformation.getRatings().get(i).getRoleIcon(), ratingIconDamage);
                    downloadRatingIcon(requestQueue, accountInformation.getRatings().get(i).getRankIcon(), ratingRankIconDamage);
                } else {
                    if (accountInformation.getRatings().get(i).getLevel() == 0) {
                        ratingIconDamage.setVisibility(View.GONE);
                        ratingRankIconDamage.setVisibility(View.GONE);
                        ratingDamage.setVisibility(View.GONE);
                    }
                }
                if (accountInformation.getRatings().get(i).getRole().equals("support") && accountInformation.getRatings().get(i).getLevel() > 0) {
                    ratingSupport.setText(String.valueOf(accountInformation.getRatings().get(i).getLevel()));
                    downloadRatingIcon(requestQueue, accountInformation.getRatings().get(i).getRoleIcon(), ratingIconSupport);
                    downloadRatingIcon(requestQueue, accountInformation.getRatings().get(i).getRankIcon(), ratingRankIconSupport);
                } else {
                    if (accountInformation.getRatings().get(i).getLevel() == 0) {
                        ratingIconSupport.setVisibility(View.GONE);
                        ratingRankIconSupport.setVisibility(View.GONE);
                        ratingSupport.setVisibility(View.GONE);
                    }
                }
            }

        } else {
            ratingIconSupport.setVisibility(View.GONE);
            ratingRankIconSupport.setVisibility(View.GONE);
            ratingSupport.setVisibility(View.GONE);
            ratingIconDamage.setVisibility(View.GONE);
            ratingRankIconDamage.setVisibility(View.GONE);
            ratingDamage.setVisibility(View.GONE);
            ratingIconTank.setVisibility(View.GONE);
            ratingRankIconTank.setVisibility(View.GONE);
            ratingTank.setVisibility(View.GONE);
            View view = findViewById(R.id.view2);
            view.setVisibility(View.GONE);
        }
    }

    private void setGamesWon() {
        String tempGames = accountInformation.getGamesWon() + " games won";
        gamesWon.setText(tempGames);
    }

    private void setName(RequestQueue requestQueue) {
        int hashtag = accountInformation.getName().indexOf("#");
        String tempName = accountInformation.getName().substring(0, hashtag) + " ";
        name.setText(tempName);
        downloadLevelIcon(requestQueue);
        level.setText(String.valueOf(accountInformation.getLevel()));
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
                e -> showNoConnectionMessage(OWActivity.this, 0));
        requestQueue.add(imageRequest);
    }

    private void downloadEndorsementIcon(RequestQueue requestQueue) {
        StringRequest stringRequest = new StringRequest(accountInformation.getEndorsementIcon(), string -> {
            try {
                SVG endorsement_icon = SVG.getFromString(string);
                PictureDrawable pd = new PictureDrawable(endorsement_icon.renderToPicture());
                endorsementIcon.setImageDrawable(pd);
            } catch (Exception e) {
                Log.e("SVG Exception", e.toString());
            }

        },
                e -> showNoConnectionMessage(OWActivity.this, 0));
        requestQueue.add(stringRequest);
    }

    private void downloadRatingIcon(RequestQueue requestQueue, String url, ImageView imageView) {
        ImageRequest imageRequest = new ImageRequest(url, bitmap -> {
            BitmapDrawable icon = new BitmapDrawable(getResources(), bitmap);
            imageView.setImageDrawable(icon);
        }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                e -> showNoConnectionMessage(OWActivity.this, 0));
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
                    e -> showNoConnectionMessage(OWActivity.this, 0));
            requestQueue.add(imageRequest2);

        }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                e -> showNoConnectionMessage(OWActivity.this, 0));
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
