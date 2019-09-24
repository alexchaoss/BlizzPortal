package com.BlizzardArmory.UI.UI_warcraft;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import com.BlizzardArmory.R;
import com.BlizzardArmory.UI.GamesActivity;
import com.BlizzardArmory.UI.UI_diablo.D3Activity;
import com.BlizzardArmory.UI.UI_overwatch.OWActivity;
import com.BlizzardArmory.UI.UI_starcraft.SC2Activity;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.UserInformation;
import com.BlizzardArmory.connection.ConnectionService;
import com.BlizzardArmory.connection.ImageDownload;
import com.BlizzardArmory.warcraft.WowCharacters;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;

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
    private ArrayList<String> levels;
    private ArrayList<String> className;
    private ArrayList<Drawable> thumbnails = new ArrayList<>();
    private RequestQueue requestQueue;
    private RequestQueue requestQueueImage;
    private LinearLayout.LayoutParams layoutParamsImage;
    private LinearLayout.LayoutParams layoutParamsInfo;
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

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        loadingCircle.setVisibility(View.VISIBLE);

        try {
            if (ConnectionService.isConnected()) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                bnOAuth2Params = Objects.requireNonNull(getIntent().getExtras()).getParcelable(BnConstants.BUNDLE_BNPARAMS);
                assert bnOAuth2Params != null;
                BnOAuth2Helper bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

                Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024 * 5); // 1MB cap
                Network network = new BasicNetwork(new HurlStack());
                requestQueue = new RequestQueue(cache, network);
                requestQueue.start();

                requestQueueImage = new RequestQueue(cache, network, 1);
                requestQueueImage.start();

                try {
                    JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI() +
                            URLConstants.WOW_CHAR_URL + "?" + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken(), null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    wowCharacters = response;

                                    Log.i("json", wowCharacters.toString());
                                    linearLayout = findViewById(R.id.linear_wow_characters);

                                    characterList = new WowCharacters(wowCharacters);
                                    characterNames = characterList.getCharacterNamesList();
                                    realms = characterList.getRealmsList();
                                    levels = characterList.getLevelList();
                                    className = characterList.getClassList();

                                    linearLayoutCharacterList = new ArrayList<>();

                                    layoutParamsImage = new LinearLayout.LayoutParams(150, 150);
                                    layoutParamsImage.setMargins(15, 0, 0, 0);

                                    layoutParamsInfo = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    layoutParamsInfo.setMargins(25, 0, 0, 0);

                                    layoutParamsClass = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    layoutParamsClass.setMargins(15, 0, 0, 0);

                                    for (int i = 0; i < characterList.getUrlThumbnail().size(); i++) {

                                        ImageRequest imageRequest = new ImageRequest(URLConstants.getRenderZoneURL() + characterList.getUrlThumbnail().get(i) +
                                                URLConstants.NOT_FOUND_URL_AVATAR + characterList.getRaceList().get(i) + "-"
                                                + characterList.getGenderList().get(i) + ".jpg", new Response.Listener<Bitmap>() {
                                            @Override
                                            public void onResponse(Bitmap bitmap) {
                                                Drawable portrait = new BitmapDrawable(getResources(), bitmap);

                                                final LinearLayout linearLayoutCharacters = new LinearLayout(getApplicationContext());
                                                LinearLayout linearLayoutText = new LinearLayout(getApplicationContext());
                                                LinearLayout linearLayoutLevelClass = new LinearLayout(getApplicationContext());
                                                linearLayoutCharacters.setOrientation(LinearLayout.HORIZONTAL);
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
                                                ImageView imageView = new ImageView(getApplicationContext());
                                                imageView.setImageDrawable(portrait);
                                                imageView.setLayoutParams(layoutParamsImage);

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


                                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                                layoutParams.setMargins(100, 0, 100, 75);


                                                linearLayoutCharacters.setId(index);
                                                linearLayout.addView(linearLayoutCharacters);
                                                linearLayoutCharacters.setLayoutParams(layoutParams);
                                                linearLayoutCharacters.setBackground(getResources().getDrawable(R.drawable.inputstyle, getTheme()));
                                                linearLayoutCharacters.setClickable(true);
                                                linearLayoutCharacters.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        for (int i = 0; i < characterNames.size(); i++) {
                                                            if (i == linearLayoutCharacters.getId()) {
                                                                characterClicked = characterNames.get(i);
                                                                characterRealm = realms.get(i);
                                                                url = characterList.getUrlThumbnail().get(i).replace("-avatar.jpg", "-main.jpg");
                                                            }
                                                        }
                                                        try {
                                                            if (ConnectionService.isConnected()) {
                                                                displayFragment();
                                                            } else {
                                                                ConnectionService.showNoConnectionMessage(getApplicationContext());
                                                            }
                                                        } catch (Exception e) {
                                                            Log.e("Error", e.toString());
                                                        }

                                                    }
                                                });
                                                index++;

                                            }
                                        }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                                                new Response.ErrorListener() {
                                                    public void onErrorResponse(VolleyError error) {
                                                        ConnectionService.showNoConnectionMessage(new GamesActivity());
                                                        Log.e("Error", error.toString());
                                                        finish();
                                                    }
                                                });
                                        requestQueueImage.add(imageRequest);
                                    }

                                    for (int i = 0; i < characterNames.size(); i++) {

                                    }

                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                    loadingCircle.setVisibility(View.GONE);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    ConnectionService.showNoConnectionMessage(new GamesActivity());
                                    Log.e("Error", error.toString());
                                    finish();
                                }
                            });
                    requestQueue.add(jsonRequest);

                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }


            } else {
                ConnectionService.showNoConnectionMessage(WoWActivity.this);
            }
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }

        //Button calls

        d3Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                callNextActivity(D3Activity.class);
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

    private void callNextActivity(Class activity) {
        final Intent intent = new Intent(this, activity);
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        startActivity(intent);
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
}
