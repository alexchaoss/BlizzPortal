package com.example.blizzardprofiles.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.example.blizzardprofiles.URLConstants;
import com.example.blizzardprofiles.connection.ConnectionService;
import com.example.blizzardprofiles.R;
import com.example.blizzardprofiles.UserInformation;
import com.example.blizzardprofiles.warcraft.WowCharacters;
import com.example.blizzardprofiles.connection.ImageDownload;

import org.json.JSONObject;

import java.util.ArrayList;

public class WoWActivity extends AppCompatActivity {

    private String characterClicked;
    private String characterRealm;
    private String url;

    private SharedPreferences prefs;
    private BnOAuth2Helper bnOAuth2Helper;
    private BnOAuth2Params bnOAuth2Params;

    public static JSONObject wowCharacters;
    private LinearLayout linearLayout;

    private ImageButton sc2Button;
    private ImageButton d3Button;
    private ImageButton owButton;
    private TextView btag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wow_activity);
        sc2Button = findViewById(R.id.starcraft2Button);
        d3Button = findViewById(R.id.diablo3Button);
        owButton = findViewById(R.id.overwatchButton);
        btag = findViewById(R.id.btag_header);

        btag.setText(UserInformation.getBattleTag());

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        bnOAuth2Params = this.getIntent().getExtras().getParcelable(BnConstants.BUNDLE_BNPARAMS);
        bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

        try {
            wowCharacters = new JSONObject(ConnectionService.getStringJSONFromRequest(URLConstants.getBaseURLforAPI(), URLConstants.WOW_CHAR_URL + "?", bnOAuth2Helper.getAccessToken()));
        }catch (Exception e){
            Log.e("Error", e.toString());
        }


        Log.i("json", wowCharacters.toString());
        linearLayout = findViewById(R.id.linear_wow_characters);

        final WowCharacters characterList = new WowCharacters(wowCharacters);
        final ArrayList<String> characterNames = characterList.getCharacterNamesList();
        final ArrayList<String> realms = characterList.getRealmsList();
        ArrayList<String> levels = characterList.getLevelList();
        ArrayList<Drawable> thumbnails =  new ImageDownload(characterList.getUrlThumbnail(), URLConstants.getRenderZoneURL(), this).getImageFromURL();
        ArrayList<String> className = characterList.getClassList();

        ArrayList<LinearLayout> linearLayoutCharacterList = new ArrayList<>();

        LinearLayout.LayoutParams layoutParamsImage = new LinearLayout.LayoutParams(150, 150);
        layoutParamsImage.setMargins(15,0,0,0);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(100,0,100,75);

        LinearLayout.LayoutParams layoutParamsInfo = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsInfo.setMargins(25,0,0,0);

        LinearLayout.LayoutParams layoutParamsClass = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsClass.setMargins(15,0,0,0);


        for(int i = 0; i<characterNames.size();i++) {

            LinearLayout linearLayoutCharacters = new LinearLayout(this);
            LinearLayout linearLayoutText = new LinearLayout(this);
            LinearLayout linearLayoutLevelClass = new LinearLayout(this);
            linearLayoutCharacters.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutText.setOrientation(LinearLayout.VERTICAL);
            linearLayoutLevelClass.setOrientation(LinearLayout.HORIZONTAL);

            //Add character thumbnail to view
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(thumbnails.get(i));

            imageView.setLayoutParams(layoutParamsImage);

            //Add character name to view
            TextView textViewName = new TextView(this);
            textViewName.setText(characterNames.get(i));
            textViewName.setTextColor(Color.WHITE);
            textViewName.setTextSize(17);

            //Add level to view
            TextView textViewLevel = new TextView(this);
            textViewLevel.setText(levels.get(i));
            textViewLevel.setTextColor(Color.WHITE);
            textViewLevel.setTextSize(15);

            //Add class to view
            TextView textViewClass = new TextView(this);
            textViewClass.setText(className.get(i));
            textViewClass.setTextColor(Color.WHITE);
            textViewClass.setTextSize(15);


            //Add realm to view
            TextView textViewRealm = new TextView(this);
            textViewRealm.setText(realms.get(i));
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
            linearLayoutCharacterList.add(linearLayoutCharacters);
        }
        int i = 0;
        for(final LinearLayout linear: linearLayoutCharacterList) {
            linear.setId(i);
            linearLayout.addView(linear);
            linear.setLayoutParams(layoutParams);
            linear.setBackground(getResources().getDrawable(R.drawable.inputstyle));
            linear.setClickable(true);
            linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int i = 0; i<characterNames.size();i++){
                        if(i == linear.getId()){
                            characterClicked = characterNames.get(i);
                            characterRealm = realms.get(i);
                            url = characterList.getUrlThumbnail().get(i).replace("-avatar.jpg", "-main.jpg");
                        }
                    }
                    displayFragment();
                }
            });
            i++;
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
}
