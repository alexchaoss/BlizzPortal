package com.BlizzardArmory.ui.ui_warcraft;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.BlizzardArmory.R;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.UserInformation;
import com.BlizzardArmory.ui.GamesActivity;
import com.BlizzardArmory.ui.IOnBackPressed;
import com.BlizzardArmory.ui.ui_diablo.D3Activity;
import com.BlizzardArmory.ui.ui_overwatch.OWActivity;
import com.BlizzardArmory.ui.ui_starcraft.SC2Activity;
import com.BlizzardArmory.warcraft.WowCharacters;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;

import org.json.JSONObject;

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

    private BnOAuth2Helper bnOAuth2Helper;

    private WowCharacters characterList;
    private ArrayList<String> characterNames;
    private ArrayList<String> realms;
    private ArrayList<RelativeLayout> linearLayoutCharacterList;
    private ArrayList<String> levels;
    private ArrayList<String> className;
    private RequestQueue requestQueue;
    private RequestQueue requestQueueImage;
    private ArrayList<String> faction;
    private RelativeLayout.LayoutParams layoutParamsImage;
    private RelativeLayout.LayoutParams layoutParamsLogo;
    private RelativeLayout.LayoutParams layoutParamsInfo;
    private LinearLayout.LayoutParams layoutParamsClass;

    private int index = 0;

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

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        loadingCircle.setVisibility(View.VISIBLE);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        bnOAuth2Params = Objects.requireNonNull(getIntent().getExtras()).getParcelable(BnConstants.BUNDLE_BNPARAMS);
        assert bnOAuth2Params != null;
        bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024 * 5); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();

        requestQueueImage = new RequestQueue(cache, network, 1);
        requestQueueImage.start();

        downloadWoWCharacters();

        //Button calls
        d3Button.setOnClickListener(v -> callNextActivity(D3Activity.class));

        sc2Button.setOnClickListener(v -> callNextActivity(SC2Activity.class));

        owButton.setOnClickListener(v -> callNextActivity(OWActivity.class));
    }

    private void downloadWoWCharacters() {
        try {
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI() +
                    URLConstants.WOW_CHAR_URL + "?" + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken(), null,
                    response -> {
                        wowCharacters = response;

                        Log.i("json", wowCharacters.toString());
                        linearLayout = findViewById(R.id.linear_wow_characters);

                        characterList = new WowCharacters(wowCharacters);
                        characterNames = characterList.getCharacterNamesList();
                        realms = characterList.getRealmsList();
                        levels = characterList.getLevelList();
                        className = characterList.getClassList();
                        faction = characterList.getFactionList();

                        createCharacterListUI();
                    },
                    error -> {
                        try {
                            callErrorAlertDialog(error.networkResponse.statusCode);
                        } catch (Exception e) {
                            callErrorAlertDialog(0);
                        }

                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        loadingCircle.setVisibility(View.GONE);
                    });
            requestQueue.add(jsonRequest);

        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    private void createCharacterListUI() {
        linearLayoutCharacterList = new ArrayList<>();

        layoutParamsImage = new RelativeLayout.LayoutParams(150, 150);
        layoutParamsImage.setMargins(15, 0, 0, 0);
        layoutParamsImage.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        layoutParamsImage.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

        layoutParamsLogo = new RelativeLayout.LayoutParams(150, 150);
        layoutParamsLogo.setMargins(15, 0, 0, 0);
        layoutParamsLogo.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        layoutParamsLogo.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

        layoutParamsClass = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsClass.setMargins(15, 0, 0, 0);


        for (int i = 0; i < characterList.getUrlThumbnail().size(); i++) {

            ImageRequest imageRequest = new ImageRequest(URLConstants.getRenderZoneURL() + characterList.getUrlThumbnail().get(i) +
                    URLConstants.NOT_FOUND_URL_AVATAR + characterList.getRaceList().get(i) + "-"
                    + characterList.getGenderList().get(i) + ".jpg", bitmap -> {
                Drawable portrait = new BitmapDrawable(getResources(), bitmap);

                final RelativeLayout relativeLayoutCharacters = createCharacterLayout(portrait);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(100, 0, 100, 75);


                relativeLayoutCharacters.setId(index);
                linearLayout.addView(relativeLayoutCharacters);
                relativeLayoutCharacters.setLayoutParams(layoutParams);
                relativeLayoutCharacters.setBackground(getResources().getDrawable(R.drawable.inputstyle, getTheme()));
                setOnClickCharacter(relativeLayoutCharacters);
                index++;

            }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                    error -> {
                        callErrorAlertDialog(error.networkResponse.statusCode);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        loadingCircle.setVisibility(View.GONE);
                    });
            requestQueueImage.add(imageRequest);
        }

        if (characterList.getCharacterNamesList().size() == 0) {
            TextView textView = new TextView(getApplicationContext());
            textView.setText("This account has no active characters.");
            linearLayout.addView(textView);
        }
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        loadingCircle.setVisibility(View.GONE);
    }

    private RelativeLayout createCharacterLayout(Drawable portrait) {
        final RelativeLayout relativeLayoutCharacters = new RelativeLayout(getApplicationContext());
        LinearLayout linearLayoutText = new LinearLayout(getApplicationContext());
        LinearLayout linearLayoutLevelClass = new LinearLayout(getApplicationContext());
        linearLayoutText.setOrientation(LinearLayout.VERTICAL);
        linearLayoutLevelClass.setOrientation(LinearLayout.HORIZONTAL);

        //Add character name to view
        TextView textViewName = new TextView(getApplicationContext());
        textViewName.setText(characterNames.get(index));
        textViewName.setTextColor(Color.WHITE);
        textViewName.setTextSize(17);

        //Add level to view
        TextView textViewLevel = new TextView(getApplicationContext());
        textViewLevel.setText(levels.get(index));
        textViewLevel.setTextColor(Color.WHITE);
        textViewLevel.setTextSize(15);

        //Add class to view
        TextView textViewClass = new TextView(getApplicationContext());
        textViewClass.setText(className.get(index));
        textViewClass.setTextColor(Color.WHITE);
        textViewClass.setTextSize(15);


        //Add realm to view
        TextView textViewRealm = new TextView(getApplicationContext());
        textViewRealm.setText(realms.get(index));
        textViewRealm.setTextColor(Color.WHITE);
        textViewRealm.setTextSize(15);

        //Add character thumbnail to view
        ImageView portraitImage = new ImageView(getApplicationContext());
        portraitImage.setId(index + 1);
        portraitImage.setImageDrawable(portrait);
        portraitImage.setLayoutParams(layoutParamsImage);

        //Add level and class to parent layout
        linearLayoutLevelClass.addView(textViewLevel);
        linearLayoutLevelClass.addView(textViewClass, layoutParamsClass);

        layoutParamsInfo = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsInfo.addRule(RelativeLayout.RIGHT_OF, index + 1);
        layoutParamsInfo.setMargins(30, 0, 0, 0);

        //Add layouts of texts to parent layout
        linearLayoutText.addView(textViewName);
        linearLayoutText.addView(linearLayoutLevelClass);
        linearLayoutText.addView(textViewRealm);
        linearLayoutText.setLayoutParams(layoutParamsInfo);

        //Add faction logo
        ImageView factionImage = new ImageView(getApplicationContext());
        if (faction.get(index).equals("Horde")) {
            factionImage.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.horde_logo, getTheme()));
        } else if (faction.get(index).equals("Alliance")) {
            factionImage.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.alliance_logo, getTheme()));
        }
        factionImage.setLayoutParams(layoutParamsLogo);

        //Add views to layout
        relativeLayoutCharacters.addView(portraitImage);
        relativeLayoutCharacters.addView(linearLayoutText);
        relativeLayoutCharacters.addView(factionImage);
        relativeLayoutCharacters.setGravity(Gravity.CENTER_VERTICAL);
        linearLayoutCharacterList.add(relativeLayoutCharacters);
        return relativeLayoutCharacters;
    }

    private void setOnClickCharacter(RelativeLayout relativeLayoutCharacters) {
        relativeLayoutCharacters.setClickable(true);
        relativeLayoutCharacters.setOnClickListener(v -> {
            for (int i1 = 0; i1 < characterNames.size(); i1++) {
                if (i1 == relativeLayoutCharacters.getId()) {
                    characterClicked = characterNames.get(i1);
                    characterRealm = realms.get(i1);
                    url = characterList.getUrlThumbnail().get(i1).replace("-avatar.jpg", "-main.jpg");
                }
            }
            displayFragment();
        });
    }

    private void callNextActivity(Class activity) {
        final Intent intent = new Intent(this, activity);
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (!(fragment instanceof IOnBackPressed) || !((IOnBackPressed) fragment).onBackPressed()) {
            super.onBackPressed();
        } else {
            Intent intent = new Intent(this, GamesActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    private void displayFragment() {
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

    private void callErrorAlertDialog(int responseCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(WoWActivity.this, R.style.DialogTransparent);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(10, 20, 10, 20);

        TextView titleText = new TextView(WoWActivity.this);

        titleText.setTextSize(20);
        titleText.setGravity(Gravity.CENTER_HORIZONTAL);
        titleText.setPadding(0, 20, 0, 20);
        titleText.setLayoutParams(layoutParams);
        titleText.setTextColor(Color.WHITE);

        TextView messageText = new TextView(WoWActivity.this);

        messageText.setGravity(Gravity.CENTER_HORIZONTAL);
        messageText.setLayoutParams(layoutParams);
        messageText.setTextColor(Color.WHITE);

        Button button = new Button(WoWActivity.this);

        button.setTextSize(18);
        button.setTextColor(Color.WHITE);
        button.setGravity(Gravity.CENTER);
        button.setWidth(200);
        button.setLayoutParams(buttonParams);
        button.setBackground(WoWActivity.this.getDrawable(R.drawable.buttonstyle));

        Button button2 = new Button(WoWActivity.this);

        button2.setTextSize(20);
        button2.setTextColor(Color.WHITE);
        button2.setGravity(Gravity.CENTER);
        button2.setWidth(200);
        button2.setHeight(100);
        button2.setLayoutParams(buttonParams);
        button2.setBackground(WoWActivity.this.getDrawable(R.drawable.buttonstyle));

        LinearLayout buttonLayout = new LinearLayout(WoWActivity.this);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonLayout.setGravity(Gravity.CENTER);
        buttonLayout.addView(button);


        if (responseCode == 404) {
            titleText.setText("Information Outdated");
            messageText.setText("Please login in game to update this account's information.");
            button.setText("OK");
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

        LinearLayout linearLayout = new LinearLayout(WoWActivity.this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setPadding(20, 20, 20, 20);

        linearLayout.addView(titleText);
        linearLayout.addView(messageText);
        linearLayout.addView(buttonLayout);

        dialog.addContentView(linearLayout, layoutParams);

        dialog.setOnCancelListener(dialog1 -> downloadWoWCharacters());

        button.setOnClickListener(v -> dialog.cancel());
        button2.setOnClickListener(v -> onBackPressed());
    }
}
