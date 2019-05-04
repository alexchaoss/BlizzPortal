package com.example.blizzardprofiles;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;

public class GamesActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    private BnOAuth2Helper bnOAuth2Helper;
    private BnOAuth2Params bnOAuth2Params;

    private ConnectionService.RequestApiInterface requestApiInterface;

    private  SectionsStatePageAdapter mSectionStatePageAdapter;
    private ViewPager mViewPager;
    private ImageButton wowButton;
    private ImageButton sc2Button;
    private ImageButton d3Button;
    private ImageButton owButton;
    EditText btag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        wowButton = findViewById(R.id.wowButton);
        sc2Button = findViewById(R.id.starcraft2Button);
        d3Button = findViewById(R.id.diablo3Button);
        owButton = findViewById(R.id.overwatchButton);

        btag = findViewById(R.id.btag_header);
        btag.setText(MainActivity.battleTag);
        btag.setFocusable(false);

        mSectionStatePageAdapter = new SectionsStatePageAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        this.prefs = PreferenceManager.getDefaultSharedPreferences(this);

        bnOAuth2Params = this.getIntent().getExtras().getParcelable(BnConstants.BUNDLE_BNPARAMS);
        bnOAuth2Helper = new BnOAuth2Helper(this.prefs, bnOAuth2Params);
        requestApiInterface = ConnectionService.getRequestApiInterface(bnOAuth2Params.getZone());

        //Button calls
        sc2Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                sc2Button.setBackgroundResource(R.drawable.sc2_icon_glow);
                owButton.setBackgroundResource(R.drawable.overwatch_icon);
                d3Button.setBackgroundResource(R.drawable.diablo3_icon);
                wowButton.setBackgroundResource(R.drawable.wow_icon);
                GamesActivity.this.setViewPager(3);
            }
        });

        owButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                sc2Button.setBackgroundResource(R.drawable.sc2_icon);
                owButton.setBackgroundResource(R.drawable.overwatch_icon_glow);
                d3Button.setBackgroundResource(R.drawable.diablo3_icon);
                wowButton.setBackgroundResource(R.drawable.wow_icon);
                GamesActivity.this.setViewPager(4);
            }
        });

        d3Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                sc2Button.setBackgroundResource(R.drawable.sc2_icon);
                owButton.setBackgroundResource(R.drawable.overwatch_icon);
                d3Button.setBackgroundResource(R.drawable.diablo3_icon_glow);
                wowButton.setBackgroundResource(R.drawable.wow_icon);
                GamesActivity.this.setViewPager(2);
            }
        });

        wowButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                sc2Button.setBackgroundResource(R.drawable.sc2_icon);
                owButton.setBackgroundResource(R.drawable.overwatch_icon);
                d3Button.setBackgroundResource(R.drawable.diablo3_icon);
                wowButton.setBackgroundResource(R.drawable.wow_icon_glow);
                GamesActivity.this.setViewPager(1);
            }
        });


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
        mViewPager.setCurrentItem(fragmentNumber);
    }

}
