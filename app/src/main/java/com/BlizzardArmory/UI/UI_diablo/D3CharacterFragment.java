package com.BlizzardArmory.UI.UI_diablo;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
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
import android.support.v7.view.menu.MenuView;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
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
import android.widget.TableLayout;
import android.widget.TextView;

import com.BlizzardArmory.BuildConfig;
import com.BlizzardArmory.R;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.diablo.Character.CharacterInformation;
import com.BlizzardArmory.diablo.Items.Item;
import com.BlizzardArmory.diablo.Items.Items;
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
import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.felipecsl.gifimageview.library.GifImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class D3CharacterFragment extends Fragment {

    private static CharacterInformation characterInformation;
    private JSONObject characterInfo;
    private Items itemsInformation;

    private int imageIndex = 0;

    private RelativeLayout loadingCircle;

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


    private TextView top_stat;
    private TextView vitality;
    private TextView crit_chance;
    private TextView crit_damage;
    private TextView attack_speed;
    private TextView area_damage;
    private TextView cooldown_reduction;
    private double critDamage = 0;
    private double attackSpeed = 0;
    private double critChance = 0;
    private double areaDamage = 0;
    private double cooldownReduction = 0;
    private HashMap<Integer, String> primaryStatsMap = new HashMap<>();
    private HashMap<Integer, String> secondaryStatsMap = new HashMap<>();
    private HashMap<Integer, String> gemsMap = new HashMap<>();

    public ScrollView itemScrollView;
    private ImageButton closeButton;

    private TabLayout d3Nav;
    private ConstraintLayout stats_layout;
    private ConstraintLayout gear_layout;
    private ConstraintLayout cube_layout;
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

    private RequestQueue requestQueueImage;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.d3_character_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        assert bundle != null;
        int id = bundle.getInt("id");

        d3Nav = view.findViewById(R.id.d3_nav);
        stats_layout = view.findViewById(R.id.stats_layout);
        gear_layout = view.findViewById(R.id.gear_layout);
        cube_layout = view.findViewById(R.id.cube_layout);

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
        top_stat = view.findViewById(R.id.top_stat);
        vitality = view.findViewById(R.id.vitality);
        crit_chance = view.findViewById(R.id.crit);
        crit_damage = view.findViewById(R.id.crit_chance);
        attack_speed = view.findViewById(R.id.attack_speed);
        area_damage = view.findViewById(R.id.area_damage);
        cooldown_reduction = view.findViewById(R.id.cooldown_reduction);
        typeName = view.findViewById(R.id.typeName);
        slot = view.findViewById(R.id.slot);

        closeButton = new ImageButton(view.getContext());
        closeButton.setBackground(getContext().getDrawable(R.drawable.close_button_d3));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0);
        params.setMargins(0, 0, 0, 20);
        closeButton.setLayoutParams(params);

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

        chatgemInactive.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (chatgemActive.getVisibility() == View.VISIBLE) {
                    chatgemActive.setVisibility(View.GONE);
                } else {
                    chatgemActive.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        addImageViewItemsToList();

        closeButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.performClick();
                    Log.i("CLOSE", "CLICKED");
                    itemScrollView.setVisibility(View.GONE);

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
            }
        });

        d3Nav.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        stats_layout.setVisibility(View.VISIBLE);
                        gear_layout.setVisibility(View.GONE);
                        chatgemInactive.setVisibility(View.VISIBLE);
                        chatgemStatue.setVisibility(View.VISIBLE);
                        cube_layout.setVisibility(View.GONE);
                        break;
                    case 1:
                        stats_layout.setVisibility(View.GONE);
                        gear_layout.setVisibility(View.VISIBLE);
                        cube_layout.setVisibility(View.GONE);
                        chatgemActive.setVisibility(View.GONE);
                        chatgemInactive.setVisibility(View.GONE);
                        chatgemStatue.setVisibility(View.GONE);

                        break;
                    case 2:
                        stats_layout.setVisibility(View.GONE);
                        gear_layout.setVisibility(View.GONE);
                        cube_layout.setVisibility(View.GONE);
                        chatgemInactive.setVisibility(View.VISIBLE);
                        chatgemStatue.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        stats_layout.setVisibility(View.GONE);
                        gear_layout.setVisibility(View.GONE);
                        cube_layout.setVisibility(View.VISIBLE);
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


        loadingCircle.setVisibility(View.VISIBLE);
        Objects.requireNonNull(D3CharacterFragment.this.getActivity()).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        long startTime = System.nanoTime();
        final Gson gson = new GsonBuilder().create();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        BnOAuth2Params bnOAuth2Params = Objects.requireNonNull(Objects.requireNonNull(getActivity()).getIntent().getExtras()).getParcelable(BnConstants.BUNDLE_BNPARAMS);
        assert bnOAuth2Params != null;
        BnOAuth2Helper bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

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
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("Response", response.toString());
                            characterInfo = response;
                            characterInformation = gson.fromJson(characterInfo.toString(), CharacterInformation.class);
                            //setPaperdoll();
                            setName();

                            String topStatString = "+" + characterInformation.getStats().getStrength() + " Strength";
                            if (characterInformation.getStats().getStrength() < characterInformation.getStats().getIntelligence()) {
                                topStatString = "+" + characterInformation.getStats().getIntelligence() + " Intelligence";
                            } else if (characterInformation.getStats().getIntelligence() < characterInformation.getStats().getDexterity()) {
                                topStatString = "+" + characterInformation.getStats().getDexterity() + " Dexterity";
                            }

                            String vitalityString = "+" + characterInformation.getStats().getVitality() + " Vitality";

                            top_stat.setText(topStatString);
                            vitality.setText(vitalityString);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("test", error.toString());
                }
            });
            requestQueue.add(jsonRequest);

            JsonObjectRequest jsonRequest2 = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI() +
                    URLConstants.getD3HeroItemsURL(id) + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken(), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            itemsInformation = gson.fromJson(response.toString(), Items.class);
                            getItemInformation();
                            setItemBackgroundColor();
                            getItemIconURL();
                            getItemIcons();
                            getAttackStats();
                            String critChanceString = "Critical Hit Chance Increased by " + critChance + "%";
                            String critDamageString = "Critical Hit Damage Increased by " + critDamage + "%";
                            String attackSpeedString = "Increases Attack Speed by " + attackSpeed + "%";
                            String areaDamageString = "Chance to Deal " + areaDamage + "%" + " Area Damage on Hit";
                            String cdReductString = "Reduces cooldown of all skills by " + cooldownReduction + "%";
                            crit_chance.setText(critChanceString);
                            crit_damage.setText(critDamageString);
                            attack_speed.setText(attackSpeedString);
                            area_damage.setText(areaDamageString);
                            cooldown_reduction.setText(cdReductString);
                            for (int i = 0; i < items.size(); i++) {
                                setItemInformation(i);
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("Error", error.toString());
                }
            });
            requestQueue.add(jsonRequest2);
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000000;
        Log.i("time", String.valueOf(duration));
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

        imageView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    GradientDrawable backgroundStroke = new GradientDrawable();
                    backgroundStroke.setColor(Color.parseColor("#000000"));
                    backgroundStroke.setStroke(8, Color.parseColor(getItemBorderColor(index)));
                    itemScrollView.setBackground(backgroundStroke);

                    itemName.setBackground(getHeaderBackground(index));

                    iconTooltip.setImageDrawable(imageView.getDrawable());
                    iconTooltip.setBackground(imageView.getBackground());

                    RelativeLayout.LayoutParams jewelleryParams = new RelativeLayout.LayoutParams((int) (60 * getResources().getSystem().getDisplayMetrics().density), (int) (61 * getResources().getSystem().getDisplayMetrics().density));
                    RelativeLayout.LayoutParams normalIconParams = new RelativeLayout.LayoutParams((int) (54 * getResources().getSystem().getDisplayMetrics().density), (int) (103 * getResources().getSystem().getDisplayMetrics().density));
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
                    RelativeLayout.LayoutParams effectParams = new RelativeLayout.LayoutParams((int) (95 * getResources().getSystem().getDisplayMetrics().density), (int) (140 * getResources().getSystem().getDisplayMetrics().density));
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
                                    weaponEffect.setImageDrawable(getContext().getDrawable(R.drawable.fire_effect));
                                    break;
                                case "cold":
                                    weaponEffect.setLayoutParams(effectParams);
                                    weaponEffect.setImageDrawable(getContext().getDrawable(R.drawable.cold_effect));
                                    break;
                                case "holy":
                                    weaponEffect.setLayoutParams(effectParams);
                                    weaponEffect.setImageDrawable(getContext().getDrawable(R.drawable.holy_effect));
                                    break;
                                case "poison":
                                    weaponEffect.setLayoutParams(effectParams);
                                    weaponEffect.setImageDrawable(getContext().getDrawable(R.drawable.poison_effect));
                                    break;
                                case "lightning":
                                    weaponEffect.setLayoutParams(effectParams);
                                    weaponEffect.setImageDrawable(getContext().getDrawable(R.drawable.lightning_effect));
                                    break;
                                case "arcane":
                                    weaponEffect.setLayoutParams(effectParams);
                                    weaponEffect.setImageDrawable(getContext().getDrawable(R.drawable.arcane_effect));
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
                        primarystats.setText(Html.fromHtml("Primary<br>" + primaryStatsMap.get(index), Html.FROM_HTML_MODE_LEGACY, new Html.ImageGetter() {
                            @Override
                            public Drawable getDrawable(String source) {
                                int resourceId = getResources().getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID);
                                Drawable drawable = getResources().getDrawable(resourceId, Objects.requireNonNull(D3CharacterFragment.this.getContext()).getTheme());
                                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                                return drawable;
                            }
                        }, null));
                        linearLayoutItemStats.addView(primarystats, layoutParamsStats);
                        secondarystats.setText(Html.fromHtml("Secondary<br>" + secondaryStatsMap.get(index), Html.FROM_HTML_MODE_LEGACY, new Html.ImageGetter() {
                            @Override
                            public Drawable getDrawable(String source) {
                                int resourceId = getResources().getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID);
                                Drawable drawable = getResources().getDrawable(resourceId, Objects.requireNonNull(D3CharacterFragment.this.getContext()).getTheme());
                                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                                return drawable;
                            }
                        }, null));
                        linearLayoutItemStats.addView(secondarystats, layoutParamsStats);
                        gems.setText(Html.fromHtml(gemsMap.get(index), Html.FROM_HTML_MODE_LEGACY, new Html.ImageGetter() {
                            @Override
                            public Drawable getDrawable(String source) {
                                int resourceId = getResources().getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID);
                                Drawable drawable = getResources().getDrawable(resourceId, Objects.requireNonNull(D3CharacterFragment.this.getContext()).getTheme());
                                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                                return drawable;
                            }
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
                        set.setText(Html.fromHtml(setText, Html.FROM_HTML_MODE_LEGACY, new Html.ImageGetter() {
                            @Override
                            public Drawable getDrawable(String source) {
                                int resourceId = getResources().getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID);
                                Drawable drawable = getResources().getDrawable(resourceId, Objects.requireNonNull(D3CharacterFragment.this.getContext()).getTheme());
                                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                                return drawable;
                            }
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
                    linearLayoutItemStats.addView(closeButton);
                    itemScrollView.setVisibility(View.VISIBLE);
                }
                return true;
            }
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
            ImageRequest imageRequest = new ImageRequest(itemIconURL.get(key), new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap bitmap) {
                    Drawable item = new BitmapDrawable(getResources(), bitmap);
                    Objects.requireNonNull(imageViewItem.get(tempKey)).setImageDrawable(item);
                    imageIndex++;

                    if (imageIndex == itemIconURL.size()) {
                        Objects.requireNonNull(getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        loadingCircle.setVisibility(View.GONE);
                    }
                }
            }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            imageIndex++;
                            if (imageIndex == itemIconURL.size()) {
                                Objects.requireNonNull(getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                loadingCircle.setVisibility(View.GONE);
                            }
                            Log.e("Error", error.toString());
                        }
                    });
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

    private void getAttackStats() {
        for (int i = 0; i < imageViewItem.size(); i++) {
            try {
                getAttackSpeedCritDamageCritChance(items.get(i).getAttributes().getPrimary());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void getAttackSpeedCritDamageCritChance(List<String> attributes) {
        for (int i = 0; i < attributes.size(); i++) {
            if (attributes.get(i).toLowerCase().contains("Attack Speed".toLowerCase())) {
                String temp = attributes.get(i);
                temp = temp.replaceAll("[^.0123456789]", "");
                attackSpeed += Double.valueOf(temp);
            }
            if (attributes.get(i).toLowerCase().contains("Critical Hit Damage".toLowerCase())) {
                String temp = attributes.get(i);
                temp = temp.replaceAll("[^.0123456789]", "");
                critDamage += Double.valueOf(temp);
            }
            if (attributes.get(i).toLowerCase().contains("Critical Hit Chance".toLowerCase())) {
                String temp = attributes.get(i);
                temp = temp.replaceAll("[^.0123456789]", "");
                critChance += Double.valueOf(temp);
            }
            if (attributes.get(i).toLowerCase().contains("Area Damage".toLowerCase())) {
                String temp = attributes.get(i);
                temp = temp.replaceAll("[^.0123456789]", "");
                areaDamage += Double.valueOf(temp);
            }
            if (attributes.get(i).toLowerCase().contains("cooldown of all skills".toLowerCase())) {
                String temp = attributes.get(i);
                temp = temp.replaceAll("[^.0123456789]", "");
                Log.e("ERROR CD REDUC", temp);
                if (temp.charAt(temp.length() - 1) == '.') {
                    temp = temp.substring(0, temp.length() - 2);
                }
                cooldownReduction += Double.valueOf(temp);
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
                return "#f8cc35";
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
            return getContext().getDrawable(R.drawable.d3_item_header_legendary_primal);
        } else if (items.get(index).getTypeName().contains("Primal Set")) {
            return getContext().getDrawable(R.drawable.d3_item_header_legendary_primal);
        } else if (items.get(index).getTypeName().contains("Set")) {
            return getContext().getDrawable(R.drawable.d3_item_header_set);
        } else if (items.get(index).getTypeName().contains("Legendary")) {
            return getContext().getDrawable(R.drawable.d3_item_header_legendary);
        } else if (items.get(index).getTypeName().contains("Rare")) {
            return getContext().getDrawable(R.drawable.d3_item_header_rare);
        } else if (items.get(index).getTypeName().contains("Magic")) {
            return getContext().getDrawable(R.drawable.d3_item_header_magic);
        }
        return getContext().getDrawable(R.drawable.d3_item_header);
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
