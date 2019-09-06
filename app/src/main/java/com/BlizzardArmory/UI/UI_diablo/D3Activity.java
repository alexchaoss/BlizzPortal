package com.BlizzardArmory.UI.UI_diablo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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
import java.util.List;
import java.util.Objects;

public class D3Activity extends AppCompatActivity {

    private SharedPreferences prefs;
    private BnOAuth2Helper bnOAuth2Helper;
    private BnOAuth2Params bnOAuth2Params;


    private ImageButton wowButton;
    private ImageButton sc2Button;
    private ImageButton owButton;
    private LinearLayout linearLayoutCharacters;
    private RelativeLayout loadingCircle;
    private JSONObject D3AccountInfo;
    private TextView paragonLevel;
    private TextView lifetimeKills;
    private TextView eliteKills;
    private AccountInformation accountInformation;

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
        btag.setText(UserInformation.getBattleTag());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        new PrepareDataD3Activity(this).execute();

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
            Gson gson = new GsonBuilder().create();

            try {
                activity.D3AccountInfo = new JSONObject(new ConnectionService(URLConstants.getBaseURLforAPI() + URLConstants.getD3URLBtag()
                        + URLConstants.ACCESS_TOKEN_QUERY + activity.bnOAuth2Helper.getAccessToken(), activity.getApplicationContext()).getStringJSONFromRequest().get(0));
                activity.accountInformation = gson.fromJson(activity.D3AccountInfo.toString(), AccountInformation.class);
                Log.i("json", activity.D3AccountInfo.toString());
            }catch (Exception e){
                Log.e("Error", e.toString());
            }

            List<Drawable> portraits = activity.getCharacterImage(activity.accountInformation.getHeroes());

            LinearLayout.LayoutParams layoutParamsImage = new LinearLayout.LayoutParams(350, 350);
            layoutParamsImage.setMargins(15,0,0,0);

            LinearLayout.LayoutParams layoutParamsCharacters = new LinearLayout.LayoutParams(400, 400);
            layoutParamsImage.setMargins(20,0,0,0);

            for(int i = 0; i < activity.accountInformation.getHeroes().size(); i++){
                ImageView portrait = new ImageView(activity.getApplicationContext());
                portrait.setImageDrawable(portraits.get(i));
                portrait.setLayoutParams(layoutParamsImage);

                TextView name = new TextView(activity.getApplicationContext());
                name.setText(activity.accountInformation.getHeroes().get(i).getName());

                TextView eliteKills = new TextView(activity.getApplicationContext());
                eliteKills.setText(String.valueOf(activity.accountInformation.getHeroes().get(i).getKills().getElites()));

                TextView level = new TextView(activity.getApplicationContext());
                level.setText(String.valueOf(activity.accountInformation.getHeroes().get(i).getLevel()));

                LinearLayout linearLayoutCharacter = new LinearLayout(activity.getApplicationContext());
                linearLayoutCharacter.setOrientation(LinearLayout.HORIZONTAL);

                linearLayoutCharacter.addView(portrait);
                linearLayoutCharacter.addView(name);
                linearLayoutCharacter.addView(eliteKills);
                linearLayoutCharacter.addView(level);

                linearLayoutCharacter.setLayoutParams(layoutParamsCharacters);
                activity.linearLayoutCharacters.addView(linearLayoutCharacter);
            }


            return null;
        }

        protected void onPostExecute(Void param) {
            super.onPostExecute(param);
            D3Activity  activity = activityReference.get();

            String paragon = activity.accountInformation.getParagonLevel() +
                    " | " +
                    "<font color=#FF0000>" +
                    activity.accountInformation.getParagonLevelHardcore() +
                    "</font>";

            activity.paragonLevel.setText(Html.fromHtml(paragon, Html.FROM_HTML_MODE_LEGACY));
            activity.eliteKills.setText(String.valueOf(activity.accountInformation.getKills().getElites()));
            activity.lifetimeKills.setText(String.valueOf(activity.accountInformation.getKills().getMonsters()));

            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            activity.loadingCircle.setVisibility(View.GONE);
        }
    }

    public List<Drawable> getCharacterImage(List<Hero> heroes){

        List<Drawable> portraits = new ArrayList<>();

        for(int i = 0; i < heroes.size(); i++){
            switch(heroes.get(i).getClassSlug()) {
                case "barbarian":
                    portraits.add(null);
                    break;
                case "wizard":
                    portraits.add(null);
                    break;
                case "demon-hunter":
                    portraits.add(null);
                    break;
                case "witch-doctor":
                    portraits.add(null);
                    break;
                case "necromencer":
                    portraits.add(null);
                    break;
                case "monk":
                    portraits.add(null);
                    break;
                case "crusader":
                    portraits.add(null);
                    break;
                default:
                    portraits.add(null);
            }
        }
        return portraits;
    }
}
