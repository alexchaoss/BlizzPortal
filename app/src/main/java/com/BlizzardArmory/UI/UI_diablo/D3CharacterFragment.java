package com.BlizzardArmory.UI.UI_diablo;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.BlizzardArmory.BuildConfig;
import com.BlizzardArmory.R;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.diablo.Character.Active;
import com.BlizzardArmory.diablo.Character.CharacterInformation;
import com.BlizzardArmory.diablo.Character.Skill;
import com.BlizzardArmory.diablo.Item.SingleItem;
import com.BlizzardArmory.diablo.Items.Item;
import com.BlizzardArmory.diablo.Items.Items;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

public class D3CharacterFragment extends Fragment {

    private static CharacterInformation characterInformation;
    private JSONObject characterInfo;
    private Items itemsInformation;

    private int imageIndex = 0;

    private RelativeLayout loadingCircle;

    ConstraintLayout mainLayout;

    private HashMap<String, ImageView> imageViewItem = new HashMap<>();
    private ImageView shoulders;
    private ImageView hands;
    private ImageView leftFinger;
    private ImageView mainHand;
    private ImageView head;
    private ImageView torso;
    private ImageView waist;
    private ImageView legs;
    private ImageView feet;
    private ImageView neck;
    private ImageView bracers;
    private ImageView rightFinger;
    private ImageView offhand;
    private HashMap<String, String> itemIconURL = new HashMap<>();


    private TextView name;
    private TextView lvl_class;

    private ArrayList<Item> items = new ArrayList<>();


    private TextView strength;
    private TextView vitality;
    private TextView intelligence;
    private TextView dexterity;
    private TextView damage;
    private TextView toughness;
    private TextView recovery;

    private HashMap<Integer, String> primaryStatsMap = new HashMap<>();
    private HashMap<Integer, String> secondaryStatsMap = new HashMap<>();
    private HashMap<Integer, String> gemsMap = new HashMap<>();

    public ScrollView itemScrollView;
    private ImageButton closeButton;

    private TabLayout d3Nav;
    private ConstraintLayout stats_layout;
    private ConstraintLayout gear_layout;
    private ConstraintLayout cube_layout;
    private ConstraintLayout skills_layout;
    private LinearLayout linearLayoutItemStats;
    private LinearLayout linearLayoutItemArmorDamage;
    private LinearLayout.LayoutParams layoutParamsStats;
    private ImageView weaponEffect;

    private TextView itemName;
    private TextView typeName;
    private TextView slot;
    private TextView armor;
    private TextView dps;
    private TextView primarystats;
    private TextView secondarystats;
    private TextView gems;
    private TextView set;
    private TextView transmog;
    private TextView flavortext;
    private TextView misctext;
    private ImageView iconTooltip;

    private ImageView cubeSword;
    private ImageView cubeArmor;
    private ImageView cubeRing;
    private TextView cubeInfo;
    private ArrayList<SingleItem> singleItem = new ArrayList<>();

    private HashMap<String, Pair<Integer, Skill>> passiveIcons = new HashMap<>();
    private ArrayList<ImageView> passiveList = new ArrayList<>();
    private ImageView passive1;
    private ImageView passive2;
    private ImageView passive3;
    private ImageView passive4;

    private HashMap<String, Pair<Integer, Active>> skillIcons = new HashMap<>();
    private ArrayList<ImageView> skillList = new ArrayList<>();
    private ArrayList<TextView> skillNameList = new ArrayList<>();
    private ArrayList<TextView> skillRuneList = new ArrayList<>();
    private ArrayList<ImageView> skillLayoutList = new ArrayList<>();
    private TextView skill1Name;
    private TextView skill2Name;
    private TextView skill3Name;
    private TextView skill4Name;
    private TextView skill5Name;
    private TextView skill6Name;
    private TextView skill1Rune;
    private TextView skill2Rune;
    private TextView skill3Rune;
    private TextView skill4Rune;
    private TextView skill5Rune;
    private TextView skill6Rune;
    private ImageView skill1;
    private ImageView skill2;
    private ImageView skill3;
    private ImageView skill4;
    private ImageView skill5;
    private ImageView skill6;
    private ImageView skillLayout1;
    private ImageView skillLayout2;
    private ImageView skillLayout3;
    private ImageView skillLayout4;
    private ImageView skillLayout5;
    private ImageView skillLayout6;

    private LinearLayout tooltipSkillLayout;
    private TextView skillName;
    private ImageView tooltipSkill;
    private TextView skillText;
    private ImageView tooltipPassive;
    private TextView passiveText;
    private View runeSeparator;
    ScrollView skillToolTipScroll;


    private RequestQueue requestQueueImage;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.d3_character_fragment, container, false);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        assert bundle != null;
        int id = bundle.getInt("id");

        mainLayout = view.findViewById(R.id.item_d3_character);

        closeButton = new ImageButton(view.getContext());
        closeButton.setBackground(Objects.requireNonNull(getContext()).getDrawable(R.drawable.close_button_d3));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0);
        params.setMargins(0, 0, 0, 20);
        closeButton.setLayoutParams(params);

        strength = view.findViewById(R.id.strength_text);
        intelligence = view.findViewById(R.id.intelligence_text);
        dexterity = view.findViewById(R.id.dexterity_text);
        vitality = view.findViewById(R.id.vitality_text);
        damage = view.findViewById(R.id.damage);
        toughness = view.findViewById(R.id.toughness);
        recovery = view.findViewById(R.id.recovery);

        d3Nav = view.findViewById(R.id.d3_nav);
        stats_layout = view.findViewById(R.id.stats_layout);
        gear_layout = view.findViewById(R.id.gear_layout);
        cube_layout = view.findViewById(R.id.cube_layout);
        skills_layout = view.findViewById(R.id.skill_layout);

        passive1 = view.findViewById(R.id.passive1);
        passive2 = view.findViewById(R.id.passive2);
        passive3 = view.findViewById(R.id.passive3);
        passive4 = view.findViewById(R.id.passive4);

        skill1 = view.findViewById(R.id.skill1_icon);
        skill2 = view.findViewById(R.id.skill2_icon);
        skill3 = view.findViewById(R.id.skill3_icon);
        skill4 = view.findViewById(R.id.skill4_icon);
        skill5 = view.findViewById(R.id.skill5_icon);
        skill6 = view.findViewById(R.id.skill6_icon);
        skill1Name = view.findViewById(R.id.skill1_name);
        skill2Name = view.findViewById(R.id.skill2_name);
        skill3Name = view.findViewById(R.id.skill3_name);
        skill4Name = view.findViewById(R.id.skill4_name);
        skill5Name = view.findViewById(R.id.skill5_name);
        skill6Name = view.findViewById(R.id.skill6_name);
        skill1Rune = view.findViewById(R.id.skill1_rune);
        skill2Rune = view.findViewById(R.id.skill2_rune);
        skill3Rune = view.findViewById(R.id.skill3_rune);
        skill4Rune = view.findViewById(R.id.skill4_rune);
        skill5Rune = view.findViewById(R.id.skill5_rune);
        skill6Rune = view.findViewById(R.id.skill6_rune);
        skillLayout1 = view.findViewById(R.id.skill1);
        skillLayout2 = view.findViewById(R.id.skill2);
        skillLayout3 = view.findViewById(R.id.skill3);
        skillLayout4 = view.findViewById(R.id.skill4);
        skillLayout5 = view.findViewById(R.id.skill5);
        skillLayout6 = view.findViewById(R.id.skill6);

        skillToolTipScroll = view.findViewById(R.id.skill_tooltip_scroll);
        tooltipSkillLayout = view.findViewById(R.id.skill_tooltip);
        GradientDrawable skillTooltipBG = new GradientDrawable();
        skillTooltipBG.setStroke(6, Color.parseColor("#2e2a27"));
        skillTooltipBG.setColor(Color.BLACK);
        skillToolTipScroll.setBackground(skillTooltipBG);
        skillName = view.findViewById(R.id.skill_name);
        tooltipSkill = view.findViewById(R.id.tooltip_skill_icon);
        skillText = view.findViewById(R.id.skill_tooltip_text);
        tooltipPassive = view.findViewById(R.id.tooltip_rune_icon);
        passiveText = view.findViewById(R.id.rune_tooltip_text);
        runeSeparator = view.findViewById(R.id.rune_separator);

        Collections.addAll(skillList, skill1, skill2, skill3, skill4, skill5, skill6);
        Collections.addAll(skillNameList, skill1Name, skill2Name, skill3Name, skill4Name, skill5Name, skill6Name);
        Collections.addAll(skillRuneList, skill1Rune, skill2Rune, skill3Rune, skill4Rune, skill5Rune, skill6Rune);
        Collections.addAll(skillLayoutList, skillLayout1, skillLayout2, skillLayout3, skillLayout4, skillLayout5, skillLayout6);

        passiveList.add(passive1);
        passiveList.add(passive2);
        passiveList.add(passive3);
        passiveList.add(passive4);

        cubeArmor = view.findViewById(R.id.cube_armor_item);
        cubeSword = view.findViewById(R.id.cube_sword_item);
        cubeRing = view.findViewById(R.id.cube_ring_item);
        cubeArmor.setImageResource(0);
        cubeSword.setImageResource(0);
        cubeRing.setImageResource(0);
        cubeInfo = view.findViewById(R.id.cube_text);

        shoulders = view.findViewById(R.id.shoulder);
        hands = view.findViewById(R.id.gloves);
        leftFinger = view.findViewById(R.id.ring1);
        mainHand = view.findViewById(R.id.main_hand);
        head = view.findViewById(R.id.head);
        torso = view.findViewById(R.id.chest);
        waist = view.findViewById(R.id.belt);
        legs = view.findViewById(R.id.legs);
        feet = view.findViewById(R.id.boots);
        neck = view.findViewById(R.id.amulet);
        bracers = view.findViewById(R.id.bracers);
        rightFinger = view.findViewById(R.id.ring2);
        offhand = view.findViewById(R.id.off_hand);

        loadingCircle = view.findViewById(R.id.loadingCircle);

        name = view.findViewById(R.id.character_name);

        lvl_class = view.findViewById(R.id.level_class);
        typeName = view.findViewById(R.id.typeName);
        slot = view.findViewById(R.id.slot);

        itemScrollView = view.findViewById(R.id.item_scroll_view);

        linearLayoutItemStats = view.findViewById(R.id.item_stats);
        linearLayoutItemArmorDamage = view.findViewById(R.id.armor_damage);

        weaponEffect = view.findViewById(R.id.weapon_effect);

        itemName = view.findViewById(R.id.item_name);
        itemName.setTextColor(Color.WHITE);
        itemName.setGravity(View.TEXT_ALIGNMENT_CENTER);

        dps = new TextView(view.getContext());
        dps.setTextColor(Color.WHITE);

        armor = new TextView(view.getContext());
        armor.setTextColor(Color.WHITE);

        primarystats = new TextView(view.getContext());
        primarystats.setTextColor(Color.WHITE);

        secondarystats = new TextView(view.getContext());
        secondarystats.setTextColor(Color.WHITE);

        gems = new TextView(view.getContext());
        gems.setTextColor(Color.WHITE);

        set = new TextView(view.getContext());

        transmog = new TextView(view.getContext());
        transmog.setTextColor(Color.WHITE);

        flavortext = new TextView(view.getContext());
        flavortext.setTextColor(Color.WHITE);

        misctext = new TextView(view.getContext());
        misctext.setTextColor(Color.WHITE);

        iconTooltip = view.findViewById(R.id.item_icon);

        layoutParamsStats = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        layoutParamsStats.setMargins(20, 0, 20, 0);

        final ImageView chatgemInactive = view.findViewById(R.id.chatgem_inactive);
        final ImageView chatgemActive = view.findViewById(R.id.chatgem_active);
        final ImageView chatgemStatue = view.findViewById(R.id.chatgem_statue);

        chatgemInactive.setOnTouchListener((v, event) -> {
            if (chatgemActive.getVisibility() == View.VISIBLE) {
                chatgemActive.setVisibility(View.GONE);
            } else {
                chatgemActive.setVisibility(View.VISIBLE);
            }
            return false;
        });

        addImageViewItemsToList();

        closeButton.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                v.performClick();
                Log.i("CLOSE", "CLICKED");
                itemScrollView.setVisibility(View.GONE);
                skillToolTipScroll.setVisibility(View.GONE);

                linearLayoutItemStats.removeView(primarystats);
                linearLayoutItemStats.removeView(secondarystats);
                linearLayoutItemStats.removeView(gems);
                linearLayoutItemStats.removeView(transmog);
                linearLayoutItemStats.removeView(flavortext);
                linearLayoutItemStats.removeView(misctext);
                linearLayoutItemArmorDamage.removeView(dps);
                linearLayoutItemArmorDamage.removeView(armor);
                linearLayoutItemStats.removeView(closeButton);
                linearLayoutItemStats.removeView(set);
                weaponEffect.setImageDrawable(null);
                itemScrollView.scrollTo(0, 0);
            }
            return false;
        });

        navigateTabs(chatgemInactive, chatgemActive, chatgemStatue);


        loadingCircle.setVisibility(View.VISIBLE);
        Objects.requireNonNull(D3CharacterFragment.this.getActivity()).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        long startTime = System.nanoTime();
        final Gson gson = new GsonBuilder().create();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        BnOAuth2Params bnOAuth2Params = Objects.requireNonNull(Objects.requireNonNull(getActivity()).getIntent().getExtras()).getParcelable(BnConstants.BUNDLE_BNPARAMS);
        assert bnOAuth2Params != null;
        final BnOAuth2Helper bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

        Cache cache = new DiskBasedCache(Objects.requireNonNull(getContext()).getCacheDir(), 1024 * 1024 * 5);
        Log.i("Cache", getContext().getCacheDir().getAbsolutePath());
        Network network = new BasicNetwork(new HurlStack());
        RequestQueue requestQueue = new RequestQueue(cache, network);
        requestQueueImage = new RequestQueue(cache, network);
        requestQueueImage.start();
        requestQueue.start();

        try {

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI() +
                    URLConstants.getD3HeroURL(id) + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken(), null,
                    response -> {
                        Log.i("Response", response.toString());
                        characterInfo = response;
                        characterInformation = gson.fromJson(characterInfo.toString(), CharacterInformation.class);
                        setName();
                        getCubeIcons();
                        downloadCubeItems(bnOAuth2Helper, gson);
                        downloadSkillIcons();
                        downloadPssiveIcons();
                        DecimalFormat primaryStats = new DecimalFormat("#0");

                        strength.setText(String.valueOf(primaryStats.format(characterInformation.getStats().getStrength())));
                        dexterity.setText(String.valueOf(primaryStats.format(characterInformation.getStats().getDexterity())));
                        intelligence.setText(String.valueOf(primaryStats.format(characterInformation.getStats().getIntelligence())));
                        vitality.setText(String.valueOf(primaryStats.format(characterInformation.getStats().getVitality())));

                        damage.setText(Html.fromHtml("<br><br>Damage<br><font color=\"#FFFFFF\">" + primaryStats.format(characterInformation.getStats().getDamage()) + "</font>", Html.FROM_HTML_MODE_LEGACY));
                        toughness.setText(Html.fromHtml("Toughness<br><font color=\"#FFFFFF\">" + primaryStats.format(characterInformation.getStats().getToughness()) + "</font>", Html.FROM_HTML_MODE_LEGACY));
                        recovery.setText(Html.fromHtml("Recovery<br><font color=\"#FFFFFF\">" + primaryStats.format(characterInformation.getStats().getHealing()) + "</font>", Html.FROM_HTML_MODE_LEGACY));

                    }, error -> Log.i("test", error.toString()));
            requestQueue.add(jsonRequest);

            JsonObjectRequest jsonRequest2 = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI() +
                    URLConstants.getD3HeroItemsURL(id) + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken(), null,
                    response -> {
                        itemsInformation = gson.fromJson(response.toString(), Items.class);
                        getItemInformation();
                        setItemBackgroundColor();
                        getItemIconURL();
                        getItemIcons();
                        for (int i = 0; i < items.size(); i++) {
                            setItemInformation(i);
                        }

                    }, error -> Log.i("Error", error.toString()));
            requestQueue.add(jsonRequest2);
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000000;
        Log.i("time", String.valueOf(duration));
    }

    private void closeViewsWithButton() {
        itemScrollView.setVisibility(View.GONE);
        skillToolTipScroll.setVisibility(View.GONE);

        linearLayoutItemStats.removeView(primarystats);
        linearLayoutItemStats.removeView(secondarystats);
        linearLayoutItemStats.removeView(gems);
        linearLayoutItemStats.removeView(transmog);
        linearLayoutItemStats.removeView(flavortext);
        linearLayoutItemStats.removeView(misctext);
        linearLayoutItemArmorDamage.removeView(dps);
        linearLayoutItemArmorDamage.removeView(armor);
        linearLayoutItemStats.removeView(closeButton);
        linearLayoutItemStats.removeView(set);
        weaponEffect.setImageDrawable(null);
        itemScrollView.scrollTo(0, 0);

    }

    private void downloadSkillIcons() {
        for(int i = 0; i < characterInformation.getSkills().getActive().size(); i++){
            Pair<Integer, Active> tempPair = new Pair<>(i, characterInformation.getSkills().getActive().get(i));
            skillIcons.put(characterInformation.getSkills().getActive().get(i).getSkill().getName(), tempPair);
        }
        for (String key : skillIcons.keySet()) {
            final String tempKey = key;
            ImageRequest imageRequest = new ImageRequest(URLConstants.D3_ICON_SKILLS.replace("url", Objects.requireNonNull(skillIcons.get(key)).second.getSkill().getIcon()), bitmap -> {
                Drawable skill = new BitmapDrawable(getResources(), bitmap);
                skillList.get(Objects.requireNonNull(skillIcons.get(tempKey)).first).setImageDrawable(skill);
                skillNameList.get(Objects.requireNonNull(skillIcons.get(tempKey)).first).setText(Objects.requireNonNull(skillIcons.get(tempKey)).second.getSkill().getName());
                String smallRune = "";
                try {
                    smallRune = getSmallRuneIcon(Objects.requireNonNull(skillIcons.get(tempKey)).second.getRune().getType());
                }catch (Exception e){
                    Log.e("Rune", "none");
                }
                final String runeText = smallRune;
                if(!smallRune.equals("")){
                    skillRuneList.get(Objects.requireNonNull(skillIcons.get(tempKey)).first).setText(Html.fromHtml( "<img src=\"" + smallRune + "\">" +
                            Objects.requireNonNull(skillIcons.get(tempKey)).second.getRune().getName(), Html.FROM_HTML_MODE_LEGACY, source -> {
                        int resourceId = getResources().getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID);
                        Drawable drawable = getResources().getDrawable(resourceId, Objects.requireNonNull(D3CharacterFragment.this.getContext()).getTheme());
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        return drawable;
                    }, null));
                }

                setOnTouchSkillTooltip(tempKey, runeText, skill);

            }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                    error -> Log.e("Error", error.toString()));
            requestQueueImage.add(imageRequest);
        }
    }

    private void setOnTouchSkillTooltip(String tempKey, String runeText, Drawable icon) {
        skillList.get(Objects.requireNonNull(skillIcons.get(tempKey)).first).setOnTouchListener((v, event) ->{
            tooltipPassive.setVisibility(View.VISIBLE);
            try {
                ((ViewGroup) closeButton.getParent()).removeView(closeButton);
            }catch (Exception e){
                Log.e("Parent", "None");
            }
            tooltipSkillLayout.addView(closeButton);
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                skillName.setText(Objects.requireNonNull(skillIcons.get(tempKey)).second.getSkill().getName());
                tooltipSkill.setImageDrawable(icon);
                tooltipSkill.setBackgroundResource(0);
                tooltipSkill.setPadding(0, 0, 0, 0);
                skillText.setText(Html.fromHtml(Objects.requireNonNull(skillIcons.get(tempKey)).second.getSkill().getDescriptionHtml()
                        .replaceAll("<span class=\"d3-color-green", "<font color=\"#00ff00")
                        .replaceAll("<span class=\"d3-color-gold", "<font color=\"#c7b377")
                        .replaceAll("<span class=\"d3-color-yellow", "<font color=\"#ffff00")
                        .replaceAll("</span>", "</font>"), Html.FROM_HTML_MODE_LEGACY));

                if (!runeText.equals("")) {
                    runeSeparator.setVisibility(View.VISIBLE);
                    tooltipPassive.setImageResource(getRuneIcon(Objects.requireNonNull(skillIcons.get(tempKey)).second.getRune().getType()));
                    passiveText.setText(Html.fromHtml(("<big><font color=\"#FFFFFF\">" + Objects.requireNonNull(skillIcons.get(tempKey)).second.getRune().getName() + "</font></big><br>"
                            + Objects.requireNonNull(skillIcons.get(tempKey)).second.getRune().getDescriptionHtml())
                            .replaceAll("<span class=\"d3-color-green", "<font color=\"#00ff00")
                            .replaceAll("<span class=\"d3-color-gold", "<font color=\"#c7b377")
                            .replaceAll("<span class=\"d3-color-yellow", "<font color=\"#ffff00")
                            .replaceAll("</span>", "</font>"), Html.FROM_HTML_MODE_LEGACY));
                }else{
                    passiveText.setText("");
                    tooltipPassive.setImageResource(0);
                    runeSeparator.setVisibility(View.GONE);
                }
                skillToolTipScroll.setVisibility(View.VISIBLE);
            }
            return true;
        });
    }

    private String getSmallRuneIcon(String type) {
        switch (type){
            case "a":
                return "small_rune_a";
            case "b":
                return "small_rune_b";
            case "c":
                return "small_rune_c";
            case "d":
                return "small_rune_d";
            case "e":
                return "small_rune_e";
        }
        return "";
    }

    private int getRuneIcon(String type) {
        switch (type){
            case "a":
                return R.drawable.rune_a;
            case "b":
                return R.drawable.rune_b;
            case "c":
                return R.drawable.rune_c;
            case "d":
                return R.drawable.rune_d;
            case "e":
                return R.drawable.rune_e;
        }
        return 0;
    }

    private void downloadPssiveIcons() {
        for(int i = 0; i < characterInformation.getSkills().getPassive().size(); i++){
            Pair<Integer, Skill> tempPair = new Pair<>(i, characterInformation.getSkills().getPassive().get(i).getSkill());
            passiveIcons.put(characterInformation.getSkills().getPassive().get(i).getSkill().getName(), tempPair);
        }
        for (String key : passiveIcons.keySet()) {
            final String tempKey = key;
            ImageRequest imageRequest = new ImageRequest(URLConstants.D3_ICON_SKILLS.replace("url", Objects.requireNonNull(passiveIcons.get(key)).second.getIcon()), bitmap -> {
                Drawable passive = new BitmapDrawable(getResources(), bitmap);
                passiveList.get(Objects.requireNonNull(passiveIcons.get(tempKey)).first).setImageDrawable(passive);
                setOnTouchPassiveTooltip(tempKey, passive);
            }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                    error -> Log.e("Error", error.toString()));
            requestQueueImage.add(imageRequest);
        }
    }

    private void setOnTouchPassiveTooltip(String tempKey, Drawable icon) {
        passiveList.get(Objects.requireNonNull(passiveIcons.get(tempKey)).first).setOnTouchListener((v, event) ->{
            runeSeparator.setVisibility(View.VISIBLE);
            tooltipPassive.setImageResource(0);
            tooltipPassive.setVisibility(View.GONE);
            try {
                ((ViewGroup) closeButton.getParent()).removeView(closeButton);
            }catch (Exception e){
                Log.e("Parent", "None");
            }
            tooltipSkillLayout.addView(closeButton);
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                skillName.setText(Objects.requireNonNull(passiveIcons.get(tempKey)).second.getName());
                tooltipSkill.setImageDrawable(icon);
                tooltipSkill.setBackgroundResource(R.drawable.d3_passive_unselected);
                tooltipSkill.setPadding(15, 15, 15, 15);
                skillText.setText(Html.fromHtml(Objects.requireNonNull(passiveIcons.get(tempKey)).second.getDescriptionHtml()
                        .replaceAll("<span class=\"d3-color-green", "<font color=\"#00ff00")
                        .replaceAll("<span class=\"d3-color-gold", "<font color=\"#c7b377")
                        .replaceAll("<span class=\"d3-color-yellow", "<font color=\"#ffff00")
                        .replaceAll("</span>", "</font>"), Html.FROM_HTML_MODE_LEGACY));
                skillToolTipScroll.setVisibility(View.VISIBLE);
                try {
                    passiveText.setText(Html.fromHtml("<i>" + Objects.requireNonNull(passiveIcons.get(tempKey)).second.getFlavorText() + "</i>", Html.FROM_HTML_MODE_LEGACY));

                }catch (Exception e){
                    runeSeparator.setVisibility(View.GONE);
                }
            }
            return true;
        });
    }

    private void downloadCubeItems(BnOAuth2Helper bnOAuth2Helper, final Gson gson) {
        try {
            for (int i = 0; i < characterInformation.getLegendaryPowers().size(); i++) {
                final int index = i;
                JsonObjectRequest jsonRequest2 = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI() +
                        URLConstants.D3_ITEM.replace("item", characterInformation.getLegendaryPowers().get(i).getTooltipParams()) + URLConstants.ACCESS_TOKEN_QUERY
                        + bnOAuth2Helper.getAccessToken(), null,
                        response -> {
                            singleItem.add(gson.fromJson(response.toString(), SingleItem.class));

                            if(index == characterInformation.getLegendaryPowers().size() -1){
                                setCubeText();
                            }

                        }, error -> Log.i("Error", error.toString()));
                requestQueueImage.add(jsonRequest2);
            }
        } catch (IOException e) {
            Log.e("Error", e.toString());
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setCubeText() {

        final String [] armorArray = {"shoulder", "spirit", "voodoo", "wizard", "gloves", "helm", "chest", "cloak", "belt", "mighty", "pants", "boots", "bracers"};
        final String [] ringArray = {"ring", "amulet"};
        final String [] swordArray = {"shoulder", "spirit", "voodoo", "wizard", "gloves", "helm", "chest", "cloak", "belt", "mighty", "pants", "boots", "bracers", "ring", "amulet"};

        cubeSword.setOnTouchListener((v, event) -> {
            String sword = "";
            for(int i = 0; i < singleItem.size(); i++){
                if(Arrays.stream(swordArray).parallel().noneMatch(characterInformation.getLegendaryPowers().get(i).getIcon()::contains)){
                    for(int j = 0; j < singleItem.get(i).getAttributes().getSecondary().size(); j++){
                        if(singleItem.get(i).getAttributes().getSecondary().get(j).getTextHtml().contains("d3-color-ffff8000")){
                            sword = "<big>" + singleItem.get(i).getName() + "</big><br>" + singleItem.get(i).getAttributes().getSecondary().get(j).getTextHtml()
                                    .replaceAll("<span class=\"d3-color-ff", "<font color=\"#").replaceAll("<span class=\"d3-color-magic\">", "<font color=\"#7979d4\">")
                                    .replaceAll("</span>", "</font>");
                        }
                    }
                }
            }
            cubeInfo.setVisibility(View.VISIBLE);
            cubeInfo.setText(Html.fromHtml(sword, Html.FROM_HTML_MODE_LEGACY));
            return false;
        });

        cubeArmor.setOnTouchListener((v, event) -> {
            String armor = "";
            for(int i = 0; i < singleItem.size(); i++){
                if(Arrays.stream(armorArray).parallel().anyMatch(characterInformation.getLegendaryPowers().get(i).getIcon()::contains)){
                    for(int j = 0; j < singleItem.get(i).getAttributes().getSecondary().size(); j++){
                        if(singleItem.get(i).getAttributes().getSecondary().get(j).getTextHtml().contains("d3-color-ffff8000")){
                            armor = "<big>" + singleItem.get(i).getName() + "</big><br>" + singleItem.get(i).getAttributes().getSecondary().get(j).getTextHtml()
                                    .replaceAll("<span class=\"d3-color-ff", "<font color=\"#").replaceAll("<span class=\"d3-color-magic\">", "<font color=\"#7979d4\">")
                                    .replaceAll("span", "font");
                        }
                    }
                }
            }
            cubeInfo.setVisibility(View.VISIBLE);
            cubeInfo.setText(Html.fromHtml(armor, Html.FROM_HTML_MODE_LEGACY));
            return false;
        });

        cubeRing.setOnTouchListener((v, event) -> {
            String ring = "";
            for(int i = 0; i < singleItem.size(); i++){
                if(Arrays.stream(ringArray).parallel().anyMatch(characterInformation.getLegendaryPowers().get(i).getIcon()::contains)){
                    for(int j = 0; j < singleItem.get(i).getAttributes().getSecondary().size(); j++){
                        if(singleItem.get(i).getAttributes().getSecondary().get(j).getTextHtml().contains("d3-color-ffff8000")){
                            ring = "<big>" + singleItem.get(i).getName() + "</big><br>" + singleItem.get(i).getAttributes().getSecondary().get(j).getTextHtml()
                                    .replaceAll("<span class=\"d3-color-ff", "<font color=\"#").replaceAll("<span class=\"d3-color-magic\">", "<font color=\"#7979d4\">")
                                    .replaceAll("</span>", "</font>");
                        }
                    }
                }
            }
            cubeInfo.setVisibility(View.VISIBLE);
            cubeInfo.setText(Html.fromHtml(ring, Html.FROM_HTML_MODE_LEGACY));
            return false;
        });
    }

    private void navigateTabs(final ImageView chatgemInactive, final ImageView chatgemActive, final ImageView chatgemStatue) {
        d3Nav.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        mainLayout.setBackgroundResource(R.drawable.diablo3_main_window_no_separation);
                        stats_layout.setVisibility(View.VISIBLE);
                        gear_layout.setVisibility(View.GONE);
                        skills_layout.setVisibility(View.GONE);
                        cube_layout.setVisibility(View.GONE);
                        closeViewsWithButton();
                        chatgemInactive.setVisibility(View.VISIBLE);
                        chatgemStatue.setVisibility(View.VISIBLE);


                        break;
                    case 1:
                        mainLayout.setBackgroundResource(R.drawable.diablo3_main_window_no_separation);
                        stats_layout.setVisibility(View.GONE);
                        gear_layout.setVisibility(View.VISIBLE);
                        cube_layout.setVisibility(View.GONE);
                        skills_layout.setVisibility(View.GONE);
                        closeViewsWithButton();
                        chatgemActive.setVisibility(View.GONE);
                        chatgemInactive.setVisibility(View.GONE);
                        chatgemStatue.setVisibility(View.GONE);


                        break;
                    case 2:
                        mainLayout.setBackgroundResource(R.drawable.diablo3_main_window);
                        stats_layout.setVisibility(View.GONE);
                        gear_layout.setVisibility(View.GONE);
                        cube_layout.setVisibility(View.GONE);
                        skills_layout.setVisibility(View.VISIBLE);
                        closeViewsWithButton();
                        chatgemInactive.setVisibility(View.VISIBLE);
                        chatgemStatue.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        mainLayout.setBackgroundResource(R.drawable.diablo3_main_window);
                        stats_layout.setVisibility(View.GONE);
                        gear_layout.setVisibility(View.GONE);
                        cube_layout.setVisibility(View.VISIBLE);
                        skills_layout.setVisibility(View.GONE);
                        closeViewsWithButton();
                        chatgemInactive.setVisibility(View.VISIBLE);
                        chatgemStatue.setVisibility(View.VISIBLE);
                        break;
                    default:
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setItemInformation(int index) {

        StringBuilder primary = new StringBuilder();
        StringBuilder secondary = new StringBuilder();
        StringBuilder gem = new StringBuilder();

        try {
            for (int j = 0; j < items.get(index).getAttributesHtml().getPrimary().size(); j++) {
                String attribute = items.get(index).getAttributesHtml().getPrimary().get(j).replaceAll("<span class=\"tooltip-icon-enchant\"></span>", "<img src=\"utility\">");
                attribute = attribute.replaceAll("<span class=\"tooltip-icon-utility\"></span>", "<img src=\"utility\">");
                attribute = attribute.replaceAll("<span class=\"tooltip-icon-bullet\"></span>", "<img src=\"primary" +
                        "\">");
                attribute = attribute.replaceAll("span class=\"d3-color-ff", "font color=\"#");
                attribute = attribute.replaceAll("span class=\"d3-color-magic", "font color=\"#7979d4");
                primary.append(attribute.replaceAll("</span>", "</font>")).append("<br>");
            }
            Log.i("Test secondary", primary.toString());
        } catch (Exception e) {
            primary.append("");
        }


        try {
            for (int j = 0; j < items.get(index).getAttributesHtml().getSecondary().size(); j++) {
                String attribute = items.get(index).getAttributesHtml().getSecondary().get(j).replaceAll("<span class=\"tooltip-icon-enchant\"></span>", "<img src=\"utility\">");
                attribute = attribute.replaceAll("<span class=\"tooltip-icon-bullet\"></span>", "<img src=\"primary" +
                        "\">");
                attribute = attribute.replaceAll("<span class=\"tooltip-icon-utility\"></span>", "<img src=\"utility\">");
                attribute = attribute.replaceAll("span class=\"d3-color-ff", "font color=\"#");
                attribute = attribute.replaceAll("span class=\"d3-color-magic", "font color=\"#7979d4");
                secondary.append(attribute.replaceAll("</span>", "</font>")).append("<br>");
            }
            Log.i("Test secondary", secondary.toString());
        } catch (Exception e) {
            secondary.append("");
        }


        try {
            for (int j = 0; j < items.get(index).getGems().size(); j++) {
                StringBuilder gemAttributes = new StringBuilder();
                if (items.get(index).getGems().get(j).getItem().getId().contains("Unique")) {
                    gemAttributes.append("<font color=\"#ff8000\"> ");
                }
                for (int k = 0; k < items.get(index).getGems().get(j).getAttributes().size(); k++) {
                    gemAttributes.append(" <img src=\"").append(items.get(index).getGems().get(j).getItem().getIcon()).append("\">");
                    gemAttributes.append(" <img src=\"primary\"> ");
                    gemAttributes.append(items.get(index).getGems().get(j).getAttributes().get(k).replaceAll("\\n", "<br>")).append("<br>");
                }
                gemAttributes.append("</font>");
                gem.append(gemAttributes);
            }
            if (items.get(index).getOpenSockets() > 0) {
                for (int i = 0; i < items.get(index).getOpenSockets(); i++) {
                    gem.append("<img src=\"empty_socket_d3\"> ").append("Empty Socket<br>");
                }
            }
        } catch (Exception e) {
            gem.append("");
            Log.e("Error", e.toString());
        }

        primaryStatsMap.put(index, primary.toString());
        secondaryStatsMap.put(index, secondary.toString());
        gemsMap.put(index, gem.toString());

        try {
            setOnPressItemInformation(Objects.requireNonNull(imageViewItem.get(items.get(index).getSlots())), index);
        } catch (Exception e) {
            Log.e("Item", "empty");
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setOnPressItemInformation(final ImageView imageView, final int index) {

        imageView.setOnTouchListener((v, event) -> {

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                GradientDrawable backgroundStroke = new GradientDrawable();
                backgroundStroke.setColor(Color.parseColor("#000000"));
                backgroundStroke.setStroke(8, Color.parseColor(getItemBorderColor(index)));
                itemScrollView.setBackground(backgroundStroke);

                itemName.setBackground(getHeaderBackground(index));

                selectColor(items.get(index).getDisplayColor(), iconTooltip);
                iconTooltip.setImageDrawable(imageView.getDrawable());

                RelativeLayout.LayoutParams jewelleryParams = new RelativeLayout.LayoutParams((int) (60 * Resources.getSystem().getDisplayMetrics().density),
                        (int) (61 * Resources.getSystem().getDisplayMetrics().density));
                getResources();
                RelativeLayout.LayoutParams normalIconParams = new RelativeLayout.LayoutParams((int) (54 * Resources.getSystem().getDisplayMetrics().density),
                        (int) (103 * Resources.getSystem().getDisplayMetrics().density));
                normalIconParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                if (items.get(index).getSlots().equals("neck")
                        || items.get(index).getSlots().equals("leftFinger")
                        || items.get(index).getSlots().equals("rightFinger")
                        || items.get(index).getSlots().equals("waist")) {
                    iconTooltip.setLayoutParams(jewelleryParams);
                } else {
                    iconTooltip.setLayoutParams(normalIconParams);
                }

                try {
                    String color = selectColor(items.get(index).getDisplayColor());
                    itemName.setText(Html.fromHtml("<font color=\"" + color + "\">" + items.get(index).getName() + "</font>", Html.FROM_HTML_MODE_LEGACY));
                    if (items.get(index).getName().length() > 23) {
                        itemName.setTextSize(18);
                    }
                    itemName.setGravity(Gravity.CENTER_HORIZONTAL);
                    itemName.setGravity(Gravity.CENTER_VERTICAL);
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }

                String typeNameString = items.get(index).getTypeName();
                if (typeNameString.length() > 22) {
                    int lastSpace = typeNameString.lastIndexOf(" ");
                    String beforeLastSpace = typeNameString.substring(0, lastSpace);
                    int lastSpace2 = beforeLastSpace.lastIndexOf(" ");
                    typeNameString = typeNameString.substring(0, lastSpace2) + "<br>" + typeNameString.substring(lastSpace2);
                }

                typeName.setText(Html.fromHtml(typeNameString, Html.FROM_HTML_MODE_LEGACY));
                typeName.setTextColor(Color.parseColor(selectColor(items.get(index).getDisplayColor())));

                slot.setText(items.get(index).getSlots());

                try {
                    if (items.get(index).getArmor() > 0) {
                        armor.setText(Html.fromHtml("<big><big><big><big><big>" + (int) Math.round(items.get(index).getArmor()) + "</big></big></big></big></big><br><font color=\"#696969\">Armor</font>", Html.FROM_HTML_MODE_LEGACY));
                        linearLayoutItemArmorDamage.addView(armor, layoutParamsStats);
                    }
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }

                try {
                    if (!items.get(index).getType().getTwoHanded() && items.get(index).getMinDamage() > 0) {
                        slot.setText(Html.fromHtml("1-Hand", Html.FROM_HTML_MODE_LEGACY));
                    } else if (items.get(index).getType().getTwoHanded() && items.get(index).getMinDamage() > 0) {
                        slot.setText(Html.fromHtml("2-Hand", Html.FROM_HTML_MODE_LEGACY));
                    } else {
                        slot.setText(items.get(index).getSlots());
                    }
                } catch (Exception e) {
                    Log.e("Error", "No TYPE");
                }
                RelativeLayout.LayoutParams effectParams = new RelativeLayout.LayoutParams((int) (95 * Resources.getSystem().getDisplayMetrics().density),
                        (int) (140 * Resources.getSystem().getDisplayMetrics().density));
                try {
                    if (items.get(index).getMinDamage() > 0 && items.get(index).getMaxDamage() > 0) {
                        NumberFormat formatter = new DecimalFormat("#0.0");
                        double dpsText = Math.round(((items.get(index).getMinDamage() + items.get(index).getMaxDamage()) / 2 * items.get(index).getAttacksPerSecond() * 10) / 10);
                        dps.setText(Html.fromHtml("<big><big><big><big><big>" + formatter.format(dpsText) + "</big></big></big></big></big><br><font color=\"#696969\">Damage Per Second</font><br><br>"
                                + formatter.format(items.get(index).getMinDamage()) + " - "
                                + formatter.format(items.get(index).getMaxDamage()) + "<font color=\"#696969\"> Damage</font><br>"
                                + formatter.format(items.get(index).getAttacksPerSecond()) + "<font color=\"#696969\"> Attacks per Second</font><br>", Html.FROM_HTML_MODE_LEGACY));
                        linearLayoutItemArmorDamage.addView(dps, layoutParamsStats);

                        switch (items.get(index).getElementalType()) {
                            case "fire":
                                weaponEffect.setLayoutParams(effectParams);
                                weaponEffect.setImageDrawable(Objects.requireNonNull(getContext()).getDrawable(R.drawable.fire_effect));
                                break;
                            case "cold":
                                weaponEffect.setLayoutParams(effectParams);
                                weaponEffect.setImageDrawable(Objects.requireNonNull(getContext()).getDrawable(R.drawable.cold_effect));
                                break;
                            case "holy":
                                weaponEffect.setLayoutParams(effectParams);
                                weaponEffect.setImageDrawable(Objects.requireNonNull(getContext()).getDrawable(R.drawable.holy_effect));
                                break;
                            case "poison":
                                weaponEffect.setLayoutParams(effectParams);
                                weaponEffect.setImageDrawable(Objects.requireNonNull(getContext()).getDrawable(R.drawable.poison_effect));
                                break;
                            case "lightning":
                                weaponEffect.setLayoutParams(effectParams);
                                weaponEffect.setImageDrawable(Objects.requireNonNull(getContext()).getDrawable(R.drawable.lightning_effect));
                                break;
                            case "arcane":
                                weaponEffect.setLayoutParams(effectParams);
                                weaponEffect.setImageDrawable(Objects.requireNonNull(getContext()).getDrawable(R.drawable.arcane_effect));
                                break;
                        }
                    } else {
                        RelativeLayout.LayoutParams noEffectParams = new RelativeLayout.LayoutParams(0, 0);
                        weaponEffect.setLayoutParams(noEffectParams);
                    }
                } catch (Exception e) {
                    dps.setText("");
                }

                try {
                    primarystats.setText(Html.fromHtml("Primary<br>" + primaryStatsMap.get(index), Html.FROM_HTML_MODE_LEGACY, source -> {
                        int resourceId = getResources().getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID);
                        Drawable drawable = getResources().getDrawable(resourceId, Objects.requireNonNull(D3CharacterFragment.this.getContext()).getTheme());
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        return drawable;
                    }, null));
                    linearLayoutItemStats.addView(primarystats, layoutParamsStats);
                    secondarystats.setText(Html.fromHtml("Secondary<br>" + secondaryStatsMap.get(index), Html.FROM_HTML_MODE_LEGACY, source -> {
                        int resourceId = getResources().getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID);
                        Drawable drawable = getResources().getDrawable(resourceId, Objects.requireNonNull(D3CharacterFragment.this.getContext()).getTheme());
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        return drawable;
                    }, null));
                    linearLayoutItemStats.addView(secondarystats, layoutParamsStats);
                    gems.setText(Html.fromHtml(gemsMap.get(index), Html.FROM_HTML_MODE_LEGACY, source -> {
                        int resourceId = getResources().getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID);
                        Drawable drawable = getResources().getDrawable(resourceId, Objects.requireNonNull(D3CharacterFragment.this.getContext()).getTheme());
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        return drawable;
                    }, null));
                    LinearLayout.LayoutParams gemParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    gemParams.setMargins(20, 0, 20, 0);
                    gems.setGravity(Gravity.CENTER_VERTICAL);
                    linearLayoutItemStats.addView(gems, gemParams);
                } catch (Exception e) {
                    Log.e("Error", e.toString());

                }

                try {
                    String setText = items.get(index).getSet().getDescriptionHtml();
                    String firstPart = setText.substring(0, setText.indexOf("(2)") - 38);
                    Log.i("TEST-2", firstPart);
                    firstPart = firstPart.replaceAll("<br />", "<br />&nbsp;&nbsp;&nbsp;");
                    String lastPart = setText.substring(setText.indexOf("(2)") - 38);
                    setText = firstPart + lastPart;
                    setText = setText.replaceAll("<span class=\"tooltip-icon-bullet\"></span>", "&nbsp;&nbsp;<img src=\"primary" +
                            "\">");
                    setText = setText.replaceAll("<span class=\"tooltip-icon-utility\"></span>", "&nbsp;&nbsp;<img src=\"utility\">");
                    setText = setText.replaceAll("<span class=\"d3-color-ff", "<font color=\"#").replaceAll("</span>", "</font>");
                    Log.i("TEST", setText);
                    set.setText(Html.fromHtml(setText, Html.FROM_HTML_MODE_LEGACY, source -> {
                        int resourceId = getResources().getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID);
                        Drawable drawable = getResources().getDrawable(resourceId, Objects.requireNonNull(D3CharacterFragment.this.getContext()).getTheme());
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        return drawable;
                    }, null));
                    linearLayoutItemStats.addView(set, layoutParamsStats);
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }

                try {
                    transmog.setText(Html.fromHtml("<font color=\"#7979d4\">Transmogrification:</font><br>" + "<font color=\""
                            + selectColor(items.get(index).getTransmog().getDisplayColor()) + "\">" + items.get(index).getTransmog().getName() + "</font><br>", Html.FROM_HTML_MODE_LEGACY));
                    linearLayoutItemStats.addView(transmog, layoutParamsStats);
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }


                try {
                    if (!items.get(index).getFlavorText().equals("null")) {
                        flavortext.setText(Html.fromHtml("<font color=\"#9d7853\">\"<i>" + items.get(index).getFlavorText() + "</i>\"</font><br>", Html.FROM_HTML_MODE_LEGACY));
                        linearLayoutItemStats.addView(flavortext, layoutParamsStats);
                    }
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }

                try {
                    if (items.get(index).getAccountBound()) {
                        misctext.setText(Html.fromHtml("<font color=\"#a99877\">Required Level: " + (int) Math.round(items.get(index).getRequiredLevel()) + "<br>Account bound</font>", Html.FROM_HTML_MODE_LEGACY));
                        misctext.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                    } else {
                        misctext.setText(Html.fromHtml("<font color=\"#a99877\">Required Level: " + (int) Math.round(items.get(index).getRequiredLevel()) + "<br></font>", Html.FROM_HTML_MODE_LEGACY));
                        misctext.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                    }
                    linearLayoutItemStats.addView(misctext, layoutParamsStats);
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }
                try {
                    ((ViewGroup) closeButton.getParent()).removeView(closeButton);
                }catch (Exception e){
                    Log.e("Parent", "None");
                }
                linearLayoutItemStats.addView(closeButton);
                itemScrollView.setVisibility(View.VISIBLE);
            }
            return true;
        });
    }

    private void getItemInformation() {
        items.add(itemsInformation.getShoulders());
        items.add(itemsInformation.getHands());
        items.add(itemsInformation.getLeftFinger());
        items.add(itemsInformation.getMainHand());
        items.add(itemsInformation.getHead());
        items.add(itemsInformation.getTorso());
        items.add(itemsInformation.getWaist());
        items.add(itemsInformation.getLegs());
        items.add(itemsInformation.getFeet());
        items.add(itemsInformation.getNeck());
        items.add(itemsInformation.getBracers());
        items.add(itemsInformation.getRightFinger());
        items.add(itemsInformation.getOffHand());
    }

    private void setName() {
        String levelClass = "<font color=#d4a94e>" + characterInformation.getLevel() + "</font>" + "<font color=#555da5> (" + characterInformation.getParagonLevel()
                + ")</font> <font color=#d4a94e>" + characterInformation.getClass_();

        if (Build.VERSION.SDK_INT >= 24) {
            lvl_class.setText(Html.fromHtml(levelClass, Html.FROM_HTML_MODE_LEGACY));
        } else {
            lvl_class.setText(Html.fromHtml(levelClass));
        }
        if (characterInformation.getName().length() > 7 && characterInformation.getName().length() < 10) {
            name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
        } else if (characterInformation.getName().length() > 9) {
            name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            name.setPadding(0, 10, 0, 0);
        }
        name.setText(characterInformation.getName());
    }

    private void getItemIcons() {
        for (String key : itemIconURL.keySet()) {
            final String tempKey = key;
            ImageRequest imageRequest = new ImageRequest(itemIconURL.get(key), bitmap -> {
                Drawable item = new BitmapDrawable(getResources(), bitmap);
                Objects.requireNonNull(imageViewItem.get(tempKey)).setImageDrawable(item);
                imageIndex++;

                if (imageIndex == itemIconURL.size()) {
                    Objects.requireNonNull(getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    loadingCircle.setVisibility(View.GONE);
                }
            }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                    error -> {
                        imageIndex++;
                        if (imageIndex == itemIconURL.size()) {
                            Objects.requireNonNull(getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            loadingCircle.setVisibility(View.GONE);
                        }
                        Log.e("Error", error.toString());
                    });
            requestQueueImage.add(imageRequest);
        }
    }

    private void getCubeIcons() {
        HashMap<String, String> cubeURL = new HashMap<>();
        String [] armor = {"shoulder","gloves","helm","chest","waist","legs","boots","bracers"};
        String [] ring = {"ring", "amulet"};
        try {
            for (int i = 0; i < characterInformation.getLegendaryPowers().size(); i++) {
                if (Arrays.stream(armor).parallel().anyMatch(characterInformation.getLegendaryPowers().get(i).getIcon()::contains)) {
                    cubeURL.put("armor", URLConstants.D3_ICON_ITEMS.replace("icon.png", characterInformation.getLegendaryPowers().get(i).getIcon() + ".png"));
                } else if (Arrays.stream(ring).parallel().anyMatch(characterInformation.getLegendaryPowers().get(i).getIcon()::contains)) {
                    cubeURL.put("ring", URLConstants.D3_ICON_ITEMS.replace("icon.png", characterInformation.getLegendaryPowers().get(i).getIcon() + ".png"));
                } else {
                    cubeURL.put("sword", URLConstants.D3_ICON_ITEMS.replace("icon.png", characterInformation.getLegendaryPowers().get(i).getIcon() + ".png"));
                }
            }
        }catch (Exception e){
            Log.e("Power", "None");
        }

        for (String key : cubeURL.keySet()) {
            final String tempKey = key;
            ImageRequest imageRequest = new ImageRequest(cubeURL.get(key), bitmap -> {
                Drawable item = new BitmapDrawable(getResources(), bitmap);
                switch (tempKey) {
                    case "sword":
                        cubeSword.setVisibility(View.VISIBLE);
                        cubeSword.setImageDrawable(item);
                        break;
                    case "armor":
                        cubeArmor.setVisibility(View.VISIBLE);
                        cubeArmor.setImageDrawable(item);
                        break;
                    case "ring":
                        cubeRing.setVisibility(View.VISIBLE);
                        cubeRing.setImageDrawable(item);
                        break;
                }
            }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                    error -> Log.e("CUBE URL ITEM", "DOWNLOAD ERROR"));
            requestQueueImage.add(imageRequest);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void addImageViewItemsToList() {
        imageViewItem.put("shoulders", shoulders);
        imageViewItem.put("hands", hands);
        imageViewItem.put("leftFinger", leftFinger);
        imageViewItem.put("mainHand", mainHand);
        imageViewItem.put("head", head);
        imageViewItem.put("torso", torso);
        imageViewItem.put("waist", waist);
        imageViewItem.put("legs", legs);
        imageViewItem.put("feet", feet);
        imageViewItem.put("neck", neck);
        imageViewItem.put("bracers", bracers);
        imageViewItem.put("rightFinger", rightFinger);
        imageViewItem.put("offHand", offhand);
    }

    private void setItemBackgroundColor() {
        for (int i = 0; i < imageViewItem.size(); i++) {
            try {
                selectColor(items.get(i).getDisplayColor(), imageViewItem.get(items.get(i).getSlots()));
            } catch (Exception e) {
                Log.e("Item", "empty");
            }
        }

        for (String key : imageViewItem.keySet()) {
            if (imageViewItem.get(key) == null) {
                selectColor("", imageViewItem.get(key));
            }
        }
    }

    private void selectColor(String color, ImageView imageView) {
        switch (color) {
            case "blue":
                imageView.setBackgroundResource(R.drawable.blue_bg_item_d3);
                break;
            case "yellow":
                imageView.setBackgroundResource(R.drawable.yellow_bg_item_d3);
                break;
            case "orange":
                imageView.setBackgroundResource(R.drawable.orange_bg_item_d3);
                break;
            case "green":
                imageView.setBackgroundResource(R.drawable.green_bg_item_d3);
                break;
            case "white":
                imageView.setBackgroundResource(R.drawable.brown_bg_item_d3);
                break;
            default:
        }
    }

    private String selectColor(String color) {
        switch (color) {
            case "blue":
                return "#7979d4";
            case "yellow":
                return "#ffff00";
            case "orange":
                return "#bf642f";
            case "green":
                return "#00ff00";
            case "white":
                return "#FFFFFF";
            default:
        }
        return "#FFFFFF";
    }

    private Drawable getHeaderBackground(int index) {
        if (items.get(index).getTypeName().contains("Primal Legendary")) {
            return Objects.requireNonNull(getContext()).getDrawable(R.drawable.d3_item_header_legendary_primal);
        } else if (items.get(index).getTypeName().contains("Primal Set")) {
            return Objects.requireNonNull(getContext()).getDrawable(R.drawable.d3_item_header_legendary_primal);
        } else if (items.get(index).getTypeName().contains("Set")) {
            return Objects.requireNonNull(getContext()).getDrawable(R.drawable.d3_item_header_set);
        } else if (items.get(index).getTypeName().contains("Legendary")) {
            return Objects.requireNonNull(getContext()).getDrawable(R.drawable.d3_item_header_legendary);
        } else if (items.get(index).getTypeName().contains("Rare")) {
            return Objects.requireNonNull(getContext()).getDrawable(R.drawable.d3_item_header_rare);
        } else if (items.get(index).getTypeName().contains("Magic")) {
            return Objects.requireNonNull(getContext()).getDrawable(R.drawable.d3_item_header_magic);
        }
        return Objects.requireNonNull(getContext()).getDrawable(R.drawable.d3_item_header);
    }

    private String getItemBorderColor(int index) {
        String type = items.get(index).getTypeName();
        if (type.contains("Ancient")) {
            return "#b47402";
        } else if (type.contains("Primal")) {
            return "#E52817";
        }
        return "#312a26";
    }

    private void getItemIconURL() {
        for (int i = 0; i < items.size(); i++) {
            try {
                itemIconURL.put(items.get(i).getSlots(), URLConstants.D3_ICON_ITEMS.replace("icon.png", items.get(i).getIcon()) + ".png");
            } catch (Exception e) {
                Log.e("Error", e.toString());
                itemIconURL.put("empty", null);
            }
        }
    }
}
