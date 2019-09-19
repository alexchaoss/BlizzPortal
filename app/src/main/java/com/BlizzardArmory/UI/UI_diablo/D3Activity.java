package com.BlizzardArmory.UI.UI_diablo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.widget.Toast;

import com.BlizzardArmory.UI.UI_warcraft.WoWCharacterFragment;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.connection.ConnectionService;
import com.BlizzardArmory.diablo.account.AccountInformation;
import com.BlizzardArmory.diablo.account.Hero;
import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.BlizzardArmory.R;
import com.BlizzardArmory.UserInformation;
import com.BlizzardArmory.UI.UI_overwatch.OWActivity;
import com.BlizzardArmory.UI.UI_starcraft.SC2Activity;
import com.BlizzardArmory.UI.UI_warcraft.WoWActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
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
    private AccountInformation accountInformation;

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

        try {
            if (ConnectionService.isConnected()) {
                new PrepareDataD3Activity(this).execute();
            }else{
                ConnectionService.showNoConnectionMessage(D3Activity.this);
                finish();
            }
        }catch (Exception e){
            Log.e("Error", e.toString());
        }


        //Button calls
        wowButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                callNextActivity(WoWActivity.class);
            }
        });

        sc2Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                callNextActivity(SC2Activity.class);
            }
        });

        owButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                callNextActivity(OWActivity.class);
            }
        });
    }

    private void callNextActivity(Class activity){
        final Intent intent = new Intent(this, activity);
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        startActivity(intent);
    }

    private static class PrepareDataD3Activity extends AsyncTask<Void, Void, Void> {

        private WeakReference<D3Activity> activityReference;


        PrepareDataD3Activity(D3Activity context) {
            activityReference = new WeakReference<>(context);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            D3Activity  activity = activityReference.get();
            activity.loadingCircle.setVisibility(View.VISIBLE);

        }

        protected Void doInBackground(Void... param) {
            D3Activity  activity = activityReference.get();
            activity.prefs = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
            activity.bnOAuth2Params = Objects.requireNonNull(activity.getIntent().getExtras()).getParcelable(BnConstants.BUNDLE_BNPARAMS);
            assert activity.bnOAuth2Params != null;
            activity.bnOAuth2Helper = new BnOAuth2Helper(activity.prefs, activity.bnOAuth2Params);
            final Gson gson = new GsonBuilder().create();
            getAccountInfo(activity, gson);
            return null;
        }

        protected void onPostExecute(Void param) {
            super.onPostExecute(param);
            D3Activity  activity = activityReference.get();

            activity.portraits = activity.getCharacterImage(activity.accountInformation.getHeroes());

            String paragon = activity.accountInformation.getParagonLevel() +
                    " | " +
                    "<font color=#FF0000>" +
                    activity.accountInformation.getParagonLevelHardcore() +
                    "</font>";

            activity.paragonLevel.setText(Html.fromHtml(paragon, Html.FROM_HTML_MODE_LEGACY));
            activity.eliteKills.setText(String.valueOf(activity.accountInformation.getKills().getElites()));
            activity.lifetimeKills.setText(String.valueOf(activity.accountInformation.getKills().getMonsters()));

            LinearLayout.LayoutParams layoutParamsImage = new LinearLayout.LayoutParams(420, 325);
            layoutParamsImage.setMargins(0,0,30,0);

            LinearLayout.LayoutParams layoutParamsCharacters = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParamsImage.setMargins(20,0,0,0);

            for(int i = 0; i < activity.accountInformation.getHeroes().size(); i++){
                ImageView portrait = new ImageView(activity.getApplicationContext());
                portrait.setImageDrawable(activity.portraits.get(i));
                portrait.setLayoutParams(layoutParamsImage);

                TextView name = new TextView(activity.getApplicationContext());
                name.setText(activity.accountInformation.getHeroes().get(i).getName());
                if(activity.accountInformation.getHeroes().get(i).getHardcore()){
                    name.setTextColor(Color.RED);
                }else {
                    name.setTextColor(Color.WHITE);
                }
                name.setTextSize(15);
                name.setGravity(Gravity.CENTER);

                TextView eliteKills = new TextView(activity.getApplicationContext());
                String eliteKillsText = "Elite Kills: " + activity.accountInformation.getHeroes().get(i).getKills().getElites();
                eliteKills.setText(eliteKillsText);
                eliteKills.setTextColor(Color.WHITE);
                eliteKills.setTextSize(15);
                eliteKills.setGravity(Gravity.CENTER);

                TextView level = new TextView(activity.getApplicationContext());
                String levelText = "Level: " + activity.accountInformation.getHeroes().get(i).getLevel();
                level.setText(levelText);
                level.setTextColor(Color.WHITE);
                level.setTextSize(15);
                level.setGravity(Gravity.CENTER);

                final LinearLayout linearLayoutCharacter = new LinearLayout(activity.getApplicationContext());
                linearLayoutCharacter.setOrientation(LinearLayout.VERTICAL);

                LinearLayout linearLayoutSeasonal = new LinearLayout(activity.getApplicationContext());
                linearLayoutSeasonal.setOrientation(LinearLayout.HORIZONTAL);
                linearLayoutSeasonal.setGravity(Gravity.CENTER);

                linearLayoutCharacter.addView(portrait);
                linearLayoutSeasonal.addView(name);
                if(activity.accountInformation.getHeroes().get(i).getSeasonal()){
                    ImageView leaf = new ImageView(activity.getApplicationContext());
                    leaf.setImageDrawable(activity.getResources().getDrawable(R.drawable.leaf_seasonal, activity.getTheme()));
                    linearLayoutSeasonal.addView(leaf);
                }
                linearLayoutCharacter.addView(linearLayoutSeasonal);
                linearLayoutCharacter.addView(eliteKills);
                linearLayoutCharacter.addView(level);

                linearLayoutCharacter.setLayoutParams(layoutParamsCharacters);
                activity.linearLayoutCharacters.addView(linearLayoutCharacter);

                linearLayoutCharacter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        D3Activity activity = activityReference.get();
                        for(int i = 0; i < activity.accountInformation.getHeroes().size();i++){
                            if(i == linearLayoutCharacter.getId()){
                                activity.characterID = activity.accountInformation.getHeroes().get(i).getId();
                            }
                        }
                        try {
                            if (ConnectionService.isConnected()) {
                                activity.displayFragment();
                            }else{
                                Toast.makeText(activity.getApplicationContext(),"No Internet Connection\nMake sure that Wi-Fi or mobile data is turned on, then try again.", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            Log.e("Error", e.toString());
                        }

                    }
                });
            }


            /*if(activity.accountInformation.getProgression().getAct1()){
                activity.act1.setImageDrawable(activity.getResources().getDrawable(R.drawable.act1_done ,activity.getTheme()));
            }else{
                activity.act1.setImageDrawable(activity.getResources().getDrawable(R.drawable.act1_not_done ,activity.getTheme()));
            }

            if(activity.accountInformation.getProgression().getAct2()){
                activity.act2.setImageDrawable(activity.getResources().getDrawable(R.drawable.act2_done ,activity.getTheme()));
            }else{
                activity.act2.setImageDrawable(activity.getResources().getDrawable(R.drawable.act2_not_done ,activity.getTheme()));
            }

            if(activity.accountInformation.getProgression().getAct3()){
                activity.act3.setImageDrawable(activity.getResources().getDrawable(R.drawable.act3_done ,activity.getTheme()));
            }else{
                activity.act3.setImageDrawable(activity.getResources().getDrawable(R.drawable.act3_not_done ,activity.getTheme()));
            }

            if(activity.accountInformation.getProgression().getAct4()){
                activity.act4.setImageDrawable(activity.getResources().getDrawable(R.drawable.act4_done ,activity.getTheme()));
            }else{
                activity.act4.setImageDrawable(activity.getResources().getDrawable(R.drawable.act4_not_done ,activity.getTheme()));
            }

            if(activity.accountInformation.getProgression().getAct5()){
                activity.act5.setImageDrawable(activity.getResources().getDrawable(R.drawable.act5_done ,activity.getTheme()));
            }else{
                activity.act5.setImageDrawable(activity.getResources().getDrawable(R.drawable.act5_not_done ,activity.getTheme()));
            }*/

            double barbTime = activity.accountInformation.getTimePlayed().getBarbarian();
            double wizTime = activity.accountInformation.getTimePlayed().getWizard();
            double wdTime = activity.accountInformation.getTimePlayed().getWitchDoctor();
            double necroTime = activity.accountInformation.getTimePlayed().getNecromancer();
            double crusaderTime = activity.accountInformation.getTimePlayed().getCrusader();
            double monkTime = activity.accountInformation.getTimePlayed().getMonk();
            double dhTime = activity.accountInformation.getTimePlayed().getDemonHunter();

            List<Double> timePlayed = new ArrayList<>(Arrays.asList(barbTime, wdTime, wizTime, necroTime, crusaderTime, monkTime, dhTime));
            List<Double> timePlayedPercent = new ArrayList<>();

            double total = 0;

            for(int i = 0; i < timePlayed.size(); i++){
                total += timePlayed.get(i);
            }

            for(int i = 0; i < timePlayed.size(); i++){
                timePlayedPercent.add((100*timePlayed.get(i)/total));
            }


            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            activity.loadingCircle.setVisibility(View.GONE);
        }
    }

    private static void getAccountInfo(D3Activity activity, Gson gson) {
        try {
            activity.D3AccountInfo = new JSONObject(new ConnectionService(URLConstants.getBaseURLforAPI() + URLConstants.getD3URLBtagProfile()
                    + URLConstants.ACCESS_TOKEN_QUERY + activity.bnOAuth2Helper.getAccessToken(), activity.getApplicationContext()).getStringJSONFromRequest().get(0));
            activity.accountInformation = gson.fromJson(activity.D3AccountInfo.toString(), AccountInformation.class);
            Log.i("json", activity.D3AccountInfo.toString());
        }catch (Exception e){
            Log.e("Error", e.toString());
        }
    }

    public List<Drawable> getCharacterImage(List<Hero> heroes){

        List<Drawable> portraits = new ArrayList<>();

        for(int i = 0; i < heroes.size(); i++){
            Drawable characterImage;
            switch(heroes.get(i).getClassSlug()) {
                case "barbarian":
                    if(accountInformation.getHeroes().get(i).getGender() == 0){
                        characterImage = getResources().getDrawable(R.drawable.barb_male, this.getTheme());
                        portraits.add(characterImage);
                    }else if (accountInformation.getHeroes().get(i).getGender() == 1){
                        characterImage = getResources().getDrawable(R.drawable.barb_female, this.getTheme());
                        portraits.add(characterImage);
                    }
                    break;
                case "wizard":
                    if(accountInformation.getHeroes().get(i).getGender() == 0){
                        characterImage = getResources().getDrawable(R.drawable.wizard_male, this.getTheme());
                        portraits.add(characterImage);
                    }else if (accountInformation.getHeroes().get(i).getGender() == 1){
                        characterImage = getResources().getDrawable(R.drawable.wizard_female, this.getTheme());
                        portraits.add(characterImage);
                    }
                    break;
                case "demon-hunter":
                    if(accountInformation.getHeroes().get(i).getGender() == 0){
                        characterImage = getResources().getDrawable(R.drawable.dh_male, this.getTheme());
                        portraits.add(characterImage);
                    }else if (accountInformation.getHeroes().get(i).getGender() == 1){
                        characterImage = getResources().getDrawable(R.drawable.dh_female, this.getTheme());
                        portraits.add(characterImage);
                    }
                    break;
                case "witch-doctor":
                    if(accountInformation.getHeroes().get(i).getGender() == 0){
                        characterImage = getResources().getDrawable(R.drawable.wd_male, this.getTheme());
                        portraits.add(characterImage);
                    }else if (accountInformation.getHeroes().get(i).getGender() == 1){
                        characterImage = getResources().getDrawable(R.drawable.wd_female, this.getTheme());
                        portraits.add(characterImage);
                    }
                    break;
                case "necromancer":
                    if(accountInformation.getHeroes().get(i).getGender() == 0){
                        characterImage = getResources().getDrawable(R.drawable.necro_male, this.getTheme());
                        portraits.add(characterImage);
                    }else if (accountInformation.getHeroes().get(i).getGender() == 1){
                        characterImage = getResources().getDrawable(R.drawable.necro_female, this.getTheme());
                        portraits.add(characterImage);
                    }
                    break;
                case "monk":
                    if(accountInformation.getHeroes().get(i).getGender() == 0){
                        characterImage = getResources().getDrawable(R.drawable.monk_male, this.getTheme());
                        portraits.add(characterImage);
                    }else if (accountInformation.getHeroes().get(i).getGender() == 1){
                        characterImage = getResources().getDrawable(R.drawable.monk_female, this.getTheme());
                        portraits.add(characterImage);
                    }
                    break;
                case "crusader":
                    if(accountInformation.getHeroes().get(i).getGender() == 0){
                        characterImage = getResources().getDrawable(R.drawable.crusader_male, this.getTheme());
                        portraits.add(characterImage);
                    }else if (accountInformation.getHeroes().get(i).getGender() == 1){
                        characterImage = getResources().getDrawable(R.drawable.crusader_female, this.getTheme());
                        portraits.add(characterImage);
                    }
                    break;
            }
        }
        return portraits;
    }

    private void displayFragment(){
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
