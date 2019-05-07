package com.example.blizzardprofiles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;

import net.sf.json.*;

import java.io.IOException;
import java.util.Stack;

public class GamesActivity extends AppCompatActivity {

    private final int WOW_FRAGMENT = 1;
    private final int D3_FRAGMENT = 2;
    private final int SC2_FRAGMENT = 3;
    private final int OW_FRAGMENT = 4;
    private final String USER_INFO_URL = "/oauth/userinfo";


    private SharedPreferences prefs;
    private BnOAuth2Helper bnOAuth2Helper;
    private BnOAuth2Params bnOAuth2Params;

    private SectionsStatePageAdapter mSectionStatePageAdapter;
    private ViewPager mViewPager;
    private ImageButton wowButton;
    private ImageButton sc2Button;
    private ImageButton d3Button;
    private ImageButton owButton;
    private TextView btag;
    private Stack<Integer> stack = new Stack<>();
    JSONObject userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        wowButton = findViewById(R.id.wowButton);
        sc2Button = findViewById(R.id.starcraft2Button);
        d3Button = findViewById(R.id.diablo3Button);
        owButton = findViewById(R.id.overwatchButton);
        btag = findViewById(R.id.btag_header);

        mSectionStatePageAdapter = new SectionsStatePageAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);


        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        bnOAuth2Params = this.getIntent().getExtras().getParcelable(BnConstants.BUNDLE_BNPARAMS);
        bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

        try {
            userInfo = (JSONObject) JSONSerializer.toJSON(ConnectionService.getStringJSONFromRequest(USER_INFO_URL, bnOAuth2Helper.getAccessToken()));
        }catch (IOException e){
            Log.e("Error", e.toString());
        }

        btag.setText(userInfo.get("battletag").toString());

        //Button calls
        wowButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                sc2Button.setBackgroundResource(R.drawable.sc2_icon);
                owButton.setBackgroundResource(R.drawable.overwatch_icon);
                d3Button.setBackgroundResource(R.drawable.diablo3_icon);
                wowButton.setBackgroundResource(R.drawable.wow_icon_glow);
                GamesActivity.this.setViewPager(WOW_FRAGMENT);
            }
        });

        d3Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                sc2Button.setBackgroundResource(R.drawable.sc2_icon);
                owButton.setBackgroundResource(R.drawable.overwatch_icon);
                d3Button.setBackgroundResource(R.drawable.diablo3_icon_glow);
                wowButton.setBackgroundResource(R.drawable.wow_icon);
                GamesActivity.this.setViewPager(D3_FRAGMENT);
            }
        });

        sc2Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                sc2Button.setBackgroundResource(R.drawable.sc2_icon_glow);
                owButton.setBackgroundResource(R.drawable.overwatch_icon);
                d3Button.setBackgroundResource(R.drawable.diablo3_icon);
                wowButton.setBackgroundResource(R.drawable.wow_icon);
                GamesActivity.this.setViewPager(SC2_FRAGMENT);
            }
        });

        owButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                sc2Button.setBackgroundResource(R.drawable.sc2_icon);
                owButton.setBackgroundResource(R.drawable.overwatch_icon_glow);
                d3Button.setBackgroundResource(R.drawable.diablo3_icon);
                wowButton.setBackgroundResource(R.drawable.wow_icon);
                GamesActivity.this.setViewPager(OW_FRAGMENT);
            }
        });
    }

    @Override
    public void onBackPressed(){
        if(mViewPager.getCurrentItem() == 0){
            super.onBackPressed();
            Intent intent = new Intent(GamesActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            mViewPager.setCurrentItem(stack.pop());
        }
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsStatePageAdapter adapter = new SectionsStatePageAdapter(getSupportFragmentManager());
        adapter.addFragment(new mainFragment(), "mainFragment");
        adapter.addFragment(new WoWFragment(), "WoWFragment");
        adapter.addFragment(new D3Fragment(), "D3Fragment");
        adapter.addFragment(new SC2Fragment(), "SC2Fragment");
        adapter.addFragment(new OWFragment(), "OWFragment");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        stack.push(mViewPager.getCurrentItem());
        mViewPager.setCurrentItem(fragmentNumber);
    }
}
