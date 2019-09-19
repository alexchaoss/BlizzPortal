package com.BlizzardArmory.UI.UI_warcraft;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
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

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.UI.UI_diablo.D3Activity;
import com.BlizzardArmory.UI.UI_overwatch.OWActivity;
import com.BlizzardArmory.UI.UI_starcraft.SC2Activity;
import com.BlizzardArmory.connection.ConnectionService;
import com.BlizzardArmory.R;
import com.BlizzardArmory.UserInformation;
import com.BlizzardArmory.warcraft.WowCharacters;
import com.BlizzardArmory.connection.ImageDownload;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;

public class WoWActivity extends AppCompatActivity {

    private String characterClicked;
    private String characterRealm;
    private String url;

    private BnOAuth2Params bnOAuth2Params;

    public JSONObject wowCharacters;
    private LinearLayout linearLayout;

    private RelativeLayout loadingCircle;

    private WowCharacters characterList;
    private ArrayList<String> characterNames;
    private ArrayList<String> realms;
    private ArrayList<LinearLayout> linearLayoutCharacterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wow_activity);
        ImageButton sc2Button = findViewById(R.id.starcraft2Button);
        ImageButton d3Button = findViewById(R.id.diablo3Button);
        ImageButton owButton = findViewById(R.id.overwatchButton);
        TextView btag = findViewById(R.id.btag_header);
        loadingCircle = findViewById(R.id.loadingCircle);
        btag.setText(UserInformation.getBattleTag());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        try {
            if (ConnectionService.isConnected()) {
                new PrepareDataWoWActivity(this).execute();
            }else{
                ConnectionService.showNoConnectionMessage(WoWActivity.this);
                finish();
            }
        }catch (Exception e){
            Log.e("Error", e.toString());
        }

        //Button calls

        d3Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                callNextActivity(D3Activity.class);
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

    private void displayFragment(){
        Bundle bundle = new Bundle();
        bundle.putString("name", characterClicked);
        bundle.putString("realm", characterRealm);
        bundle.putString("url", url);
        WoWCharacterFragment wowCharacterFragment = new WoWCharacterFragment();
        wowCharacterFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, wowCharacterFragment);
        fragmentTransaction.addToBackStack(null).commit();
        getSupportFragmentManager().executePendingTransactions();
    }

    private static class PrepareDataWoWActivity extends AsyncTask<Void, Void, Void> {

        private WeakReference<WoWActivity> activityReference;

        PrepareDataWoWActivity(WoWActivity context) {
            activityReference = new WeakReference<>(context);
        }

        protected void onPreExecute () {
            super.onPreExecute();
            WoWActivity  activity = activityReference.get();
            activity.loadingCircle.setVisibility(View.VISIBLE);

        }

        protected Void doInBackground(Void... param) {
            WoWActivity activity = activityReference.get();
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
            activity.bnOAuth2Params = Objects.requireNonNull(activity.getIntent().getExtras()).getParcelable(BnConstants.BUNDLE_BNPARAMS);
            assert activity.bnOAuth2Params != null;
            BnOAuth2Helper bnOAuth2Helper = new BnOAuth2Helper(prefs, activity.bnOAuth2Params);

            try {
                activity.wowCharacters = new JSONObject(new ConnectionService(URLConstants.getBaseURLforAPI() +
                        URLConstants.WOW_CHAR_URL + "?" + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken(), activity.getApplicationContext()).getStringJSONFromRequest().get(0));
            }catch (Exception e){
                Log.e("Error", e.toString());
            }

            Log.i("json", activity.wowCharacters.toString());
            activity.linearLayout = activity.findViewById(R.id.linear_wow_characters);

            activity.characterList = new WowCharacters(activity.wowCharacters);
            activity.characterNames = activity.characterList.getCharacterNamesList();
            activity.realms = activity.characterList.getRealmsList();
            ArrayList<String> levels = activity.characterList.getLevelList();
            ArrayList<Drawable> thumbnails = new ImageDownload(activity.characterList.getUrlThumbnail(), URLConstants.getRenderZoneURL(), activity.getApplicationContext(), activity.wowCharacters).getImageFromURL();
            ArrayList<String> className = activity.characterList.getClassList();

            activity.linearLayoutCharacterList = new ArrayList<>();

            LinearLayout.LayoutParams layoutParamsImage = new LinearLayout.LayoutParams(150, 150);
            layoutParamsImage.setMargins(15,0,0,0);

            LinearLayout.LayoutParams layoutParamsInfo = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParamsInfo.setMargins(25,0,0,0);

            LinearLayout.LayoutParams layoutParamsClass = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParamsClass.setMargins(15,0,0,0);


            for(int i = 0; i < activity.characterNames.size();i++) {

                LinearLayout linearLayoutCharacters = new LinearLayout(activity.getApplicationContext());
                LinearLayout linearLayoutText = new LinearLayout(activity.getApplicationContext());
                LinearLayout linearLayoutLevelClass = new LinearLayout(activity.getApplicationContext());
                linearLayoutCharacters.setOrientation(LinearLayout.HORIZONTAL);
                linearLayoutText.setOrientation(LinearLayout.VERTICAL);
                linearLayoutLevelClass.setOrientation(LinearLayout.HORIZONTAL);

                //Add character thumbnail to view
                ImageView imageView = new ImageView(activity.getApplicationContext());
                imageView.setImageDrawable(thumbnails.get(i));

                imageView.setLayoutParams(layoutParamsImage);

                //Add character name to view
                TextView textViewName = new TextView(activity.getApplicationContext());
                textViewName.setText(activity.characterNames.get(i));
                textViewName.setTextColor(Color.WHITE);
                textViewName.setTextSize(17);

                //Add level to view
                TextView textViewLevel = new TextView(activity.getApplicationContext());
                textViewLevel.setText(levels.get(i));
                textViewLevel.setTextColor(Color.WHITE);
                textViewLevel.setTextSize(15);

                //Add class to view
                TextView textViewClass = new TextView(activity.getApplicationContext());
                textViewClass.setText(className.get(i));
                textViewClass.setTextColor(Color.WHITE);
                textViewClass.setTextSize(15);


                //Add realm to view
                TextView textViewRealm = new TextView(activity.getApplicationContext());
                textViewRealm.setText(activity.realms.get(i));
                textViewRealm.setTextColor(Color.WHITE);
                textViewRealm.setTextSize(15);

                //Add level and class to parent layout
                linearLayoutLevelClass.addView(textViewLevel);
                linearLayoutLevelClass.addView(textViewClass, layoutParamsClass);

                //Add layouts of texts to parent layout
                linearLayoutText.addView(textViewName, layoutParamsInfo);
                linearLayoutText.addView(linearLayoutLevelClass, layoutParamsInfo);
                linearLayoutText.addView(textViewRealm, layoutParamsInfo);

                //Add views to layout
                linearLayoutCharacters.addView(imageView);
                linearLayoutCharacters.addView(linearLayoutText);
                linearLayoutCharacters.setGravity(Gravity.CENTER_VERTICAL);
                activity.linearLayoutCharacterList.add(linearLayoutCharacters);
            }
            return null;
        }

        protected void onPostExecute(Void param) {
            super.onPostExecute(param);
            WoWActivity activity = activityReference.get();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(100,0,100,75);

            int i = 0;
            for(final LinearLayout linear: activity.linearLayoutCharacterList) {
                linear.setId(i);
                activity.linearLayout.addView(linear);
                linear.setLayoutParams(layoutParams);
                linear.setBackground(activity.getResources().getDrawable(R.drawable.inputstyle, activity.getTheme()));
                linear.setClickable(true);
                linear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WoWActivity activity = activityReference.get();
                        for(int i = 0; i < activity.characterNames.size();i++){
                            if(i == linear.getId()){
                                activity.characterClicked = activity.characterNames.get(i);
                                activity.characterRealm = activity.realms.get(i);
                                activity.url = activity.characterList.getUrlThumbnail().get(i).replace("-avatar.jpg", "-main.jpg");
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
                i++;
            }

            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            activity.loadingCircle.setVisibility(View.GONE);
        }
    }
}
