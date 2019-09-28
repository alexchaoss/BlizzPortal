package com.BlizzardArmory.UI.UI_warcraft;

import android.content.Context;
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
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.BlizzardArmory.R;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.warcraft.CharacterInformation;
import com.BlizzardArmory.warcraft.Items.Gear;
import com.BlizzardArmory.warcraft.Items.Item;
import com.BlizzardArmory.warcraft.Items.ItemInformation;
import com.BlizzardArmory.warcraft.Items.Stat;
import com.BlizzardArmory.warcraft.Items.StatsEnum;
import com.BlizzardArmory.warcraft.Spells.AzeritePower;
import com.BlizzardArmory.warcraft.Spells.ItemSpell;
import com.BlizzardArmory.warcraft.Spells.Talents;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class WoWCharacterFragment extends Fragment {

    private Gson gson = new GsonBuilder().create();

    private String characterRealm;
    private String characterClicked;
    private String urlMain;

    private JSONObject characterInformation;
    private JSONObject itemObject = null;
    private JSONObject statsObject = null;

    private Gear equipment;
    private CharacterInformation characterInfo;

    //Character information
    private TextView characterName;
    private TextView itemLVL;
    private ImageView background;
    private CardView cardView;
    private TextView statsView;
    private TextView nameView;
    private ScrollView scrollView;
    private Drawable item;

    //Primary stats
    private TextView strength;
    private TextView agility;
    private TextView intellect;
    private TextView stamina;
    private TextView health;
    private TextView power;

    //Secondary stats
    private TextView crit;
    private TextView haste;
    private TextView mastery;
    private TextView versatility;
    private Drawable backgroundMain = null;

    //Talents
    private List<TextView> talentsTierContainer;
    private List<TextView> talentsContainer;
    private List<String> talentsTier;
    private List<Talents> talents = new ArrayList<>();
    private TabLayout specs;
    private TextView spec;
    private TextView noTalent;
    private TextView fifteen;
    private TextView thirty;
    private TextView forty_five;
    private TextView sixty;
    private TextView seventy_five;
    private TextView ninety;
    private TextView hundred;
    private TextView fifteenTalent;
    private TextView thirtyTalent;
    private TextView forty_fiveTalent;
    private TextView sixtyTalent;
    private TextView seventy_fiveTalent;
    private TextView ninetyTalent;
    private TextView hundredTalent;

    private RelativeLayout loadingCircle;

    //Containers
    private List<String> slotName;
    private ArrayList<ImageView> gearImageView = new ArrayList<>();
    private ArrayList<Item> itemsInfoList = new ArrayList<>();
    private ArrayList<JSONObject> bonusIDList = new ArrayList<>();
    private ArrayList<ItemInformation> itemInformations = new ArrayList<>();
    private ArrayList<String> urlItemInfo = new ArrayList<>();;
    private ArrayList<JSONObject> azeriteSpellsHead = new ArrayList<>();
    private ArrayList<JSONObject> azeriteSpellsShoulder = new ArrayList<>();
    private ArrayList<JSONObject> azeriteSpellsChest = new ArrayList<>();
    private List<AzeritePower> powerHead;
    private List<AzeritePower> powerShoulder;
    private List<AzeritePower> powerChest;
    private ArrayList<String> azeriteSpellURL = new ArrayList<>();
    private int headSpellNumber = 0;
    private int shoulderSpellNumber = 0;
    private int chestSpellNumber = 0;

    private SparseArray<String> stats = new SparseArray<>();
    private SparseArray<String> nameList = new SparseArray<>();

    private int index = 0;
    private int indexBonus = 0;
    private int indexMap = 0;

    private Cache cache;
    private Network network;
    private RequestQueue requestQueueImage;
    private RequestQueue requestQueue;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.character_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        assert bundle != null;
        characterRealm = bundle.getString("realm");
        characterClicked = bundle.getString("name");
        urlMain = bundle.getString("url");

        scrollView = view.findViewById(R.id.scrollView3);
        characterName = view.findViewById(R.id.character_name);
        itemLVL = view.findViewById(R.id.item_lvl);
        background = view.findViewById(R.id.background);
        cardView = view.findViewById(R.id.item_stats);
        cardView.setContentPadding(10, 10, 10, 10);
        LinearLayout linearLayoutItemStats = new LinearLayout(view.getContext());
        linearLayoutItemStats.setOrientation(LinearLayout.VERTICAL);
        linearLayoutItemStats.setGravity(Gravity.CENTER);
        cardView.addView(linearLayoutItemStats);
        statsView = new TextView(view.getContext());
        nameView = new TextView(view.getContext());
        linearLayoutItemStats.addView(nameView);
        linearLayoutItemStats.addView(statsView);
        loadingCircle = view.findViewById(R.id.loadingCircle);
        loadingCircle.setVisibility(View.VISIBLE);
        spec = view.findViewById(R.id.specialization);
        specs = view.findViewById(R.id.tabLayout);

        noTalent = view.findViewById(R.id.no_talent);
        noTalent.setVisibility(View.INVISIBLE);

        fifteen = view.findViewById(R.id.fifteen);
        thirty = view.findViewById(R.id.thirty);
        forty_five = view.findViewById(R.id.forty_five);
        sixty = view.findViewById(R.id.sixty);
        seventy_five = view.findViewById(R.id.seventy_five);
        ninety = view.findViewById(R.id.ninety);
        hundred = view.findViewById(R.id.hundred);

        fifteenTalent = view.findViewById(R.id.fifteenTalent);
        thirtyTalent = view.findViewById(R.id.thirtyTalent);
        forty_fiveTalent = view.findViewById(R.id.forty_fiveTalent);
        sixtyTalent = view.findViewById(R.id.sixtyTalent);
        seventy_fiveTalent = view.findViewById(R.id.seventy_fiveTalent);
        ninetyTalent = view.findViewById(R.id.ninetyTalent);
        hundredTalent = view.findViewById(R.id.hundredTalent);

        Objects.requireNonNull(WoWCharacterFragment.this.getActivity()).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


        LinearLayout.LayoutParams layoutParamsName = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams layoutParamsStats = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsName.setMargins(20, 20, 20, 0);
        nameView.setLayoutParams(layoutParamsName);
        layoutParamsStats.setMargins(20, 0, 20, 0);
        statsView.setLayoutParams(layoutParamsStats);

        slotName = Arrays.asList("Head", "Neck", "Shoulder", "Back", "Chest", "Shirt", "Tabard", "Wrist", "Hands",
                "Waist", "Legs", "Feet", "Finger", "Finger", "Trinket", "Trinket", "Main Hand", "Off Hand");

        ImageView head = view.findViewById(R.id.head);
        ImageView neck = view.findViewById(R.id.neck);
        ImageView shoulder = view.findViewById(R.id.shoulder);
        ImageView back = view.findViewById(R.id.back);
        ImageView chest = view.findViewById(R.id.chest);
        ImageView shirt = view.findViewById(R.id.shirt);
        ImageView tabard = view.findViewById(R.id.tabard);
        ImageView wrist = view.findViewById(R.id.wrist);
        ImageView hands = view.findViewById(R.id.hands);
        ImageView waist = view.findViewById(R.id.waist);
        ImageView legs = view.findViewById(R.id.legs);
        ImageView feet = view.findViewById(R.id.feet);
        ImageView finger1 = view.findViewById(R.id.finger1);
        ImageView finger2 = view.findViewById(R.id.finger2);
        ImageView trinket1 = view.findViewById(R.id.trinket1);
        ImageView trinket2 = view.findViewById(R.id.trinket2);
        ImageView mainHand = view.findViewById(R.id.main_hand);
        ImageView offHand = view.findViewById(R.id.off_hand);

        health = view.findViewById(R.id.health);
        power = view.findViewById(R.id.power);

        strength = view.findViewById(R.id.strength);
        agility = view.findViewById(R.id.agility);
        intellect = view.findViewById(R.id.intellect);
        stamina = view.findViewById(R.id.stamina);

        crit = view.findViewById(R.id.crit);
        haste = view.findViewById(R.id.haste);
        mastery = view.findViewById(R.id.mastery);
        versatility = view.findViewById(R.id.versatility);

        gearImageView.add(head);
        gearImageView.add(neck);
        gearImageView.add(shoulder);
        gearImageView.add(back);
        gearImageView.add(chest);
        gearImageView.add(shirt);
        gearImageView.add(tabard);
        gearImageView.add(wrist);
        gearImageView.add(hands);
        gearImageView.add(waist);
        gearImageView.add(legs);
        gearImageView.add(feet);
        gearImageView.add(finger1);
        gearImageView.add(finger2);
        gearImageView.add(trinket1);
        gearImageView.add(trinket2);
        gearImageView.add(mainHand);
        gearImageView.add(offHand);

        long startTime = System.nanoTime();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        BnOAuth2Params bnOAuth2Params = Objects.requireNonNull(Objects.requireNonNull(getActivity()).getIntent().getExtras()).getParcelable(BnConstants.BUNDLE_BNPARAMS);
        assert bnOAuth2Params != null;
        final BnOAuth2Helper bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

        cache = new DiskBasedCache(getContext().getCacheDir(), 1024 * 1024 * 5); // 1MB cap
        network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();

        final Gson gson = new GsonBuilder().create();

        try {

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI() +
                    swapRealmCharacterFromURL() + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken(), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            characterInformation = response;

                            try {
                                characterInfo = gson.fromJson(characterInformation.toString(), CharacterInformation.class);
                                itemObject = characterInformation.getJSONObject("items");
                                statsObject = characterInformation.getJSONObject("stats");

                                ImageRequest imageRequest = new ImageRequest(URLConstants.getRenderZoneURL() + urlMain, new Response.Listener<Bitmap>() {
                                    @Override
                                    public void onResponse(Bitmap bitmap) {
                                        backgroundMain = new BitmapDrawable(getResources(), bitmap);
                                        background.setImageDrawable(backgroundMain);
                                    }
                                }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                                        new Response.ErrorListener() {
                                            public void onErrorResponse(VolleyError e) {
                                                for (int i = 0; i < e.getStackTrace().length; i++) {
                                                    Log.e("Error-Background", e.getStackTrace()[i].getLineNumber() + "\n");
                                                }
                                            }
                                        });

                                requestQueue.add(imageRequest);

                                equipment = new Gear(itemObject);
                                initItems();
                                getIcons();

                                for (int i = 0; i < gearImageView.size(); i++) {
                                    try {
                                        if (!itemsInfoList.get(i).getName().equalsIgnoreCase("")) {
                                            urlItemInfo.add(URLConstants.getBaseURLforAPI() + swapItemIDBonusID(i) + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken());
                                        } else {
                                            urlItemInfo.add(null);
                                        }
                                    } catch (IOException e) {

                                        Log.e("Error", e.toString() + "\n");
                                        for (int j = 0; j < e.getStackTrace().length; j++) {
                                            Log.e("Error", e.getStackTrace()[j].toString() + "\n");
                                        }
                                    }
                                }
                                getItemInformation(gson, bnOAuth2Helper);

                            } catch (Exception e) {
                                Log.e("Error", e.toString() + "\n");
                                for (int i = 0; i < e.getStackTrace().length; i++) {
                                    Log.e("Error", e.getStackTrace()[i].toString() + "\n");
                                }
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError e) {
                            try {
                                String response = new String(e.networkResponse.data);
                                characterInformation = new JSONObject(response);
                                characterName.setText(characterInformation.get("reason").toString());
                                itemLVL.setText("");
                            } catch (Exception b) {
                                Log.e("Error", b.toString() + "\n");
                                for (int i = 0; i < b.getStackTrace().length; i++) {
                                    Log.e("Error", b.getStackTrace()[i].toString() + "\n");
                                }
                            }
                        }
                    });

            requestQueue.add(jsonRequest);

        } catch (Exception e) {
            Log.e("Error", e.toString() + "\n");
            for (int i = 0; i < e.getStackTrace().length; i++) {
                Log.e("Error", e.getStackTrace()[i].toString() + "\n");
            }
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000000;
        Log.i("time", String.valueOf(duration));
    }

    private void getItemInformation(final Gson gson, final BnOAuth2Helper bnOAuth2Helper) {
        for (int i = 0; i < urlItemInfo.size(); i++) {
                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, urlItemInfo.get(i), null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                bonusIDList.add(response);

                                if (bonusIDList.size() == urlItemInfo.size()) {
                                    for (int i = 0; i < bonusIDList.size(); i++) {
                                        if (bonusIDList.get(i) != null) {
                                            ItemInformation itemInformation = gson.fromJson(bonusIDList.get(i).toString(), ItemInformation.class);
                                            itemInformations.add(itemInformation);
                                        } else {
                                            itemInformations.add(new ItemInformation());
                                        }
                                    }
                                    getAzeritePowers(bnOAuth2Helper);
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError e) {
                                bonusIDList.add(null);
                                Log.e("Error", e.toString() + "\n");
                                for (int i = 0; i < e.getStackTrace().length; i++) {
                                    Log.e("Error", e.getStackTrace()[i].toString() + "\n");
                                }
                            }
                        });
                requestQueueImage.add(jsonRequest);

            indexBonus++;
        }
    }

    private void getAzeritePowers(BnOAuth2Helper bnOAuth2Helper) {

        try {
            powerHead = itemsInfoList.get(0).getAzeriteEmpoweredItem().getAzeritePowers();

            headSpellNumber = powerHead.size() - 1;
            for (int i = 0; i < powerHead.size(); i++) {
                try {
                    azeriteSpellURL.add(URLConstants.getBaseURLforAPI() + URLConstants.SPELL_ID_QUERY + powerHead.get(i).getSpellId()
                            + "?" + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken());
                } catch (Exception e) {
                    Log.e("Error", e.toString() + "\n");
                    for (int j = 0; j < e.getStackTrace().length; j++) {
                        Log.e("Error", e.getStackTrace()[j].toString() + "\n");
                    }
                }
            }
        } catch (Exception e) {
            headSpellNumber = 0;
        }

        try {
            powerShoulder = itemsInfoList.get(2).getAzeriteEmpoweredItem().getAzeritePowers();

            shoulderSpellNumber = powerShoulder.size() + headSpellNumber;
            for (int i = 0; i < powerShoulder.size(); i++) {
                try {
                    azeriteSpellURL.add(URLConstants.getBaseURLforAPI() + URLConstants.SPELL_ID_QUERY + powerShoulder.get(i).getSpellId()
                            + "?" + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken());
                } catch (Exception e) {
                    for (int j = 0; j < e.getStackTrace().length; j++) {
                        Log.e("Error", e.getStackTrace()[j].toString() + "\n");
                    }
                }
            }
        } catch (Exception e) {
            shoulderSpellNumber = 0;
        }

        try {
            powerChest = itemsInfoList.get(4).getAzeriteEmpoweredItem().getAzeritePowers();

            chestSpellNumber = powerChest.size() + shoulderSpellNumber;
            for (int i = 0; i < powerChest.size(); i++) {
                try {
                    azeriteSpellURL.add(URLConstants.getBaseURLforAPI() + URLConstants.SPELL_ID_QUERY + powerChest.get(i).getSpellId()
                            + "?" + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken());
                } catch (Exception e) {
                    for (int j = 0; j < e.getStackTrace().length; j++) {
                        Log.e("Error", e.getStackTrace()[j].toString() + "\n");
                    }
                }
            }
        } catch (Exception e) {
            chestSpellNumber = 0;
        }

        if (azeriteSpellURL.size() > 0) {
            for (int i = 0; i < azeriteSpellURL.size(); i++) {
                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, azeriteSpellURL.get(i), null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if (indexMap <= headSpellNumber && headSpellNumber > 0) {
                                    azeriteSpellsHead.add(response);
                                } else if (indexMap <= shoulderSpellNumber && shoulderSpellNumber > 0) {
                                    azeriteSpellsShoulder.add(response);
                                } else if (indexMap <= chestSpellNumber && chestSpellNumber > 0) {
                                    azeriteSpellsChest.add(response);
                                }

                                if (indexMap == azeriteSpellURL.size() - 1) {
                                    //Set item information for all items once the last azerite spell has been downloaded.
                                    for (int i = 0; i < gearImageView.size(); i++) {

                                        if (itemsInfoList.get(i).getName() != null) {
                                            try {
                                                setCharacterItemsInformation(i);
                                            } catch (Exception e) {
                                                Log.e("Error", e.toString() + "\n");
                                                for (int j = 0; j < e.getStackTrace().length; j++) {
                                                    Log.e("Error", e.getStackTrace()[j].toString() + "\n");
                                                }
                                            }
                                        }
                                    }
                                    Objects.requireNonNull(getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                    loadingCircle.setVisibility(View.GONE);
                                }
                                indexMap++;
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError e) {
                                Log.e("Error", e.toString() + "\n");
                                for (int i = 0; i < e.getStackTrace().length; i++) {
                                    Log.e("Error", e.getStackTrace()[i].toString() + "\n");
                                }
                            }
                        });

                requestQueueImage.add(jsonRequest);
            }
        } else {
            for (int i = 0; i < gearImageView.size(); i++) {

                if (itemsInfoList.get(i).getName() != null) {
                    try {
                        setCharacterItemsInformation(i);
                    } catch (Exception e) {
                        Log.e("Error", e.toString() + "\n");
                        for (int j = 0; j < e.getStackTrace().length; j++) {
                            Log.e("Error", e.getStackTrace()[j].toString() + "\n");
                        }
                    }
                }
            }
        }

        try {
            setCharacterInformationTextviews();
        } catch (Exception e) {
            Log.e("Error", e.toString() + "\n");
            for (int i = 0; i < e.getStackTrace().length; i++) {
                Log.e("Error", e.getStackTrace()[i].toString() + "\n");
            }
        }
        setTalentInformation();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private String getTrigger(int index) {
        StringBuilder trigger = new StringBuilder();
        for (int i = 0; i < itemInformations.get(index).getItemSpells().size(); i++) {
            ItemSpell itemSpell = itemInformations.get(index).getItemSpells().get(i);
            if (itemSpell.getTrigger().equals("ON_EQUIP") && !itemSpell.getScaledDescription().equals("")) {
                trigger.append("<font color=#00cc00>Equip: ").append(itemSpell.getScaledDescription()).append("</font>");
            } else if (itemSpell.getTrigger().equals("ON_USE") && !itemSpell.getScaledDescription().equals("")) {
                if (trigger.toString().equals("")) {
                    trigger.append("<font color=#00cc00>Use: ").append(itemSpell.getScaledDescription()).append("</font>");
                } else {
                    trigger.append("<br><br><font color=#00cc00>Use: ").append(itemSpell.getScaledDescription()).append("</font>");
                }

            }
        }
        return trigger.toString();
    }

    private String getStatsFormatted(String tempStats, List<Stat> statList) {
        StringBuilder tempStatsBuilder = new StringBuilder(tempStats);
        for (Stat stat : statList) {
            String currentStat;
            if (stat.getStat() > 7 && stat.getStat() < 71) {
                currentStat = "<font color=#00cc00>" + "+" + StatsEnum.fromOrdinal(stat.getStat()) + " " + stat.getAmount() + "</font>";
            } else {
                currentStat = "+" + StatsEnum.fromOrdinal(stat.getStat()).toString() + " " + stat.getAmount();
            }
            tempStatsBuilder.append(currentStat).append("<br>");
        }
        tempStats = tempStatsBuilder.toString();
        return tempStats;
    }

    private String getDamageInformation(String itemSlot, String damageInfo) {
        if (itemSlot.equals("Off-Hand") && itemsInfoList.get(index).getWeaponInfo() != null || itemSlot.equals("Main-Hand")) {
            int min = itemsInfoList.get(index).getWeaponInfo().getDamage().getMin();
            int max = itemsInfoList.get(index).getWeaponInfo().getDamage().getMax();
            double speed = itemsInfoList.get(index).getWeaponInfo().getWeaponSpeed();
            double dps = itemsInfoList.get(index).getWeaponInfo().getDps();
            damageInfo = String.format(Locale.ENGLISH, "<br>%d - %d Damage<br>Speed %.2f<br>(%.2f damage per second)", min, max, speed, dps);
        }
        return damageInfo;
    }

    private void sortStats(List<Stat> statList) {
        Collections.sort(statList, new Comparator<Stat>() {
            @Override
            public int compare(Stat o1, Stat o2) {
                if (o2.getStat() > 70) {
                    return 1;
                } else if (o1.getStat() > 70) {
                    return -1;
                }
                return o1.getStat().compareTo(o2.getStat());
            }
        });
    }

    private String formatItemSlotName(String itemSlot) {
        itemSlot = itemSlot.substring(0, 1).toUpperCase() + itemSlot.substring(1);
        itemSlot = itemSlot.replace("_", "-");
        if (itemSlot.contains("-")) {
            char before = itemSlot.charAt(itemSlot.indexOf("-") + 1);
            itemSlot = itemSlot.replace(before, Character.toUpperCase(before));
        }
        itemSlot = itemSlot.replaceAll("[0-9]", "");
        return itemSlot;
    }

    private String swapItemIDBonusID(int i) {
        StringBuilder bonusIds = new StringBuilder();
        String idURL;
        int size = itemsInfoList.get(i).getBonusLists().size();
        if (size > 0) {

            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    bonusIds.append(itemsInfoList.get(i).getBonusLists().get(k)).append(",");
                }
            }
            idURL = itemsInfoList.get(i).getId().toString() + "?b1=" + bonusIds.substring(0, bonusIds.length() - 1);
        } else {
            idURL = itemsInfoList.get(i).getId().toString() + "?b1=";
        }

        return URLConstants.BONUSID_QUERY.replace("id?b1=bonusList", idURL);
    }

    private void getIcons() {

        requestQueueImage = new RequestQueue(cache, network, 1);
        requestQueueImage.start();
        for (int i = 0; i < itemsInfoList.size(); i++) {
            ImageRequest imageRequest = new ImageRequest(URLConstants.WOW_ICONS_URL + "56/" + itemsInfoList.get(i).getIcon() + ".jpg", new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap bitmap) {
                    item = new BitmapDrawable(getResources(), bitmap);
                    setOnPressItemInformation(gearImageView.get(index), item);
                }
            }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError e) {
                            gearImageView.get(index).setImageDrawable(getEmptySlotIcon(index, getContext()));
                            index++;
                            Log.e("Error-Image", e.toString());

                        }
                    });
            requestQueueImage.add(imageRequest);
        }
    }

    private void initItems() {
        final ArrayList<JSONObject> gearList = new ArrayList<>();

        gearList.add(equipment.getHead());
        gearList.add(equipment.getNeck());
        gearList.add(equipment.getShoulder());
        gearList.add(equipment.getBack());
        gearList.add(equipment.getChest());
        gearList.add(equipment.getShirt());
        gearList.add(equipment.getTabard());
        gearList.add(equipment.getWrist());
        gearList.add(equipment.getHands());
        gearList.add(equipment.getWaist());
        gearList.add(equipment.getLegs());
        gearList.add(equipment.getFeet());
        gearList.add(equipment.getFinger1());
        gearList.add(equipment.getFinger2());
        gearList.add(equipment.getTrinket1());
        gearList.add(equipment.getTrinket2());
        gearList.add(equipment.getMainHand());
        gearList.add(equipment.getOffHand());

        for (int i = 0; i < gearList.size(); i++) {
            if (gearList.get(i) == null) {
                itemsInfoList.add(new Item());
            } else {
                Item currentItem = gson.fromJson(gearList.get(i).toString(), Item.class);
                itemsInfoList.add(currentItem);
            }
        }

    }

    private Drawable getEmptySlotIcon(int index, Context context) {
        if (index == 0) {
            return ContextCompat.getDrawable(context, R.drawable.empty_head);
        } else if (index == 1) {
            return ContextCompat.getDrawable(context, R.drawable.empty_neck);
        } else if (index == 2) {
            return ContextCompat.getDrawable(context, R.drawable.empty_shoulders);
        } else if (index == 3) {
            return ContextCompat.getDrawable(context, R.drawable.empty_back);
        } else if (index == 4) {
            return ContextCompat.getDrawable(context, R.drawable.empty_chest);
        } else if (index == 5) {
            return ContextCompat.getDrawable(context, R.drawable.empty_shirt);
        } else if (index == 6) {
            return ContextCompat.getDrawable(context, R.drawable.empty_tabard);
        } else if (index == 7) {
            return ContextCompat.getDrawable(context, R.drawable.empty_wrist);
        } else if (index == 8) {
            return ContextCompat.getDrawable(context, R.drawable.empty_hands);
        } else if (index == 9) {
            return ContextCompat.getDrawable(context, R.drawable.empty_waist);
        } else if (index == 10) {
            return ContextCompat.getDrawable(context, R.drawable.empty_legs);
        } else if (index == 11) {
            return ContextCompat.getDrawable(context, R.drawable.empty_feet);
        } else if (index == 12) {
            return ContextCompat.getDrawable(context, R.drawable.empty_ring);
        } else if (index == 13) {
            return ContextCompat.getDrawable(context, R.drawable.empty_ring);
        } else if (index == 14) {
            return ContextCompat.getDrawable(context, R.drawable.empty_trinket);
        } else if (index == 15) {
            return ContextCompat.getDrawable(context, R.drawable.empty_trinket);
        } else if (index == 16) {
            return ContextCompat.getDrawable(context, R.drawable.empty_main_hand);
        } else if (index == 17) {
            return ContextCompat.getDrawable(context, R.drawable.empty_shield);
        }

        return null;
    }

    private String swapRealmCharacterFromURL() {
        return URLConstants.WOW_ITEM_QUERY.replace("realm/character", characterRealm + "/" + characterClicked);
    }

    private Drawable itemColor(Item item, GradientDrawable drawable) {
        drawable.setCornerRadius(3);
        drawable.setColor(Color.parseColor("#000000"));
        drawable.setStroke(3, getItemColor(item));

        return drawable;
    }

    private int getItemColor(Item item) {
        try {
            if (item.getQuality() == 0) {
                return Color.GRAY;
            } else if (item.getQuality() == 1) {
                return Color.WHITE;
            } else if (item.getQuality() == 2) {
                return Color.parseColor("#00cc00");
            } else if (item.getQuality() == 3) {
                return Color.parseColor("#0070ff");
            } else if (item.getQuality() == 4) {
                return Color.parseColor("#663288");
            } else if (item.getQuality() == 5) {
                return Color.parseColor("#ff8000");
            } else if (item.getQuality() == 6) {
                return Color.parseColor("#b2a06c");
            } else if (item.getQuality() == 7) {
                return Color.parseColor("#01c5f7");
            }
        } catch (Exception e) {
            return Color.parseColor("#01c5f7");
        }

        return 0;
    }

    private void setOnPressItemInformation(final ImageView imageView, Drawable icon) {
        imageView.setId(index);
        imageView.setImageDrawable(icon);
        imageView.setPadding(3, 3, 3, 3);
        imageView.setClipToOutline(true);
        Drawable backgroundStroke = itemColor(itemsInfoList.get(index), new GradientDrawable());
        imageView.setBackground(backgroundStroke);

        imageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            @SuppressWarnings("deprecation")
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        if (stats.get(imageView.getId()) != null) {
                            nameView.setText(nameList.get(imageView.getId()));
                            nameView.setTextColor(getItemColor(itemsInfoList.get(imageView.getId())));
                            nameView.setTextSize(17);
                            if (Build.VERSION.SDK_INT >= 24) {
                                statsView.setText(Html.fromHtml(stats.get(imageView.getId()), Html.FROM_HTML_MODE_LEGACY));
                            } else {
                                statsView.setText(Html.fromHtml(stats.get(imageView.getId())));
                            }
                            statsView.setTextColor(Color.WHITE);
                            statsView.setTextSize(13);
                            cardView.setContentPadding(10, 10, 10, 10);
                            cardView.setBackground(imageView.getBackground());
                            cardView.setVisibility(View.VISIBLE);
                            cardView.setVerticalScrollBarEnabled(true);
                        }
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        cardView.setVisibility(View.GONE);
                    }
                }
                return true;
            }
        });

        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {

            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (oldScrollY != scrollY) {
                    cardView.setVisibility(View.GONE);
                }
            }
        });
        index++;
    }

    private void setTalentInformation() {
        for (int i = 0; i < characterInfo.getTalents().size(); i++) {

            if (characterInfo.getTalents().get(i).getSelected()) {
                talents.addAll(characterInfo.getTalents().get(i).getTalents());
                spec.setText(String.format("Specialization: %s", characterInfo.getTalents().get(i).getSpec().getName()));
            }
        }

        try {
            specs.addTab(specs.newTab());
            specs.getTabAt(3).setText(characterInfo.getTalents().get(3).getSpec().getName());
        } catch (NullPointerException e) {
            Log.e("Error", e.toString() + "\n");
            for (int i = 0; i < e.getStackTrace().length; i++) {
                Log.e("Error", e.getStackTrace()[i].toString() + "\n");
            }
            specs.removeTab(specs.getTabAt(3));
        }

        for (int i = 0; i < 3; i++) {
            TabLayout.Tab tab = specs.getTabAt(i);
            try {
                assert tab != null;
                tab.setText(characterInfo.getTalents().get(i).getSpec().getName());
            } catch (NullPointerException e) {
                for (int j = 0; j < e.getStackTrace().length; j++) {
                    Log.e("Error", e.getStackTrace()[j].toString() + "\n");
                }
                tab.setText("Unavailable");
            }
        }

        talentsTierContainer = new ArrayList<>(Arrays.asList(fifteen, thirty, forty_five,
                sixty, seventy_five, ninety, hundred));
        talentsContainer = new ArrayList<>(Arrays.asList(fifteenTalent, thirtyTalent, forty_fiveTalent,
                sixtyTalent, seventy_fiveTalent, ninetyTalent, hundredTalent));
        talentsTier = new ArrayList<>(Arrays.asList("15", "30", "45", "60", "75", "90", "100"));

        try {
            sortTalents();

            if (talents.size() > 0) {
                for (int i = 0; i < talents.size(); i++) {
                    noTalent.setVisibility(View.INVISIBLE);
                    talentsTierContainer.get(i).setGravity(Gravity.CENTER);
                    talentsTierContainer.get(i).setText(talentsTier.get(i));
                    talentsContainer.get(i).setText(talents.get(i).getSpell().getName());
                }
            } else {
                removeTalents();
                noTalent.setVisibility(View.VISIBLE);

            }
        } catch (NullPointerException e) {
            Log.e("Error", e.toString() + "\n");
            for (int i = 0; i < e.getStackTrace().length; i++) {
                Log.e("Error", e.getStackTrace()[i].toString() + "\n");
            }
            noTalent.setVisibility(View.VISIBLE);
        }

        specs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        getTalentsForSpecificSpec(0);
                        break;
                    case 1:
                        getTalentsForSpecificSpec(1);
                        break;
                    case 2:
                        getTalentsForSpecificSpec(2);
                        break;
                    case 3:
                        getTalentsForSpecificSpec(3);
                        break;
                    default:
                }

            }

            private void getTalentsForSpecificSpec(int position) {
                try {
                    talents.clear();
                    talents.addAll(characterInfo.getTalents().get(position).getTalents());
                    if (talents.size() == 0) {
                        noTalent.setVisibility(View.VISIBLE);
                        removeTalents();
                    } else {
                        noTalent.setVisibility(View.INVISIBLE);
                    }
                    sortTalents();
                    for (int i = 0; i < talents.size(); i++) {
                        talentsTierContainer.get(i).setGravity(Gravity.CENTER);
                        talentsTierContainer.get(i).setText(talentsTier.get(i));
                        talentsContainer.get(i).setText(talents.get(i).getSpell().getName());
                    }
                } catch (NullPointerException e) {
                    removeTalents();
                    noTalent.setVisibility(View.VISIBLE);

                    Log.e("Error", e.toString() + "\n");
                    for (int i = 0; i < e.getStackTrace().length; i++) {
                        Log.e("Error", e.getStackTrace()[i].toString() + "\n");
                    }
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


    private void setCharacterItemsInformation(int index) throws JSONException {
        String description = "";
        String nameDescription = "";
        String trigger = "";
        String damageInfo = "";
        String durability = "Durability " + itemInformations.get(index).getMaxDurability() + "/" + itemInformations.get(index).getMaxDurability();
        String requiredLevel = "Requires Level " + itemInformations.get(index).getRequiredLevel();
        String itemName = itemsInfoList.get(index).getName();
        String itemLvl = "<font color=#edc201>Item Level " + itemsInfoList.get(index).getItemLevel().toString() + "</font>";
        String armor = itemsInfoList.get(index).getArmor().toString();
        if (itemInformations.get(index).getName() != null) {
            description = "<font color=#edc201>" + itemInformations.get(index).getDescription() + "</font>";
            nameDescription = "<font color=#00cc00>" + itemInformations.get(index).getNameDescription() + "</font><br>";
            trigger = getTrigger(index);
        }

        String itemSlot = slotName.get(index);

        damageInfo = getDamageInformation(itemSlot, damageInfo);

        String tempStats = "";
        List<Stat> statList = itemsInfoList.get(index).getStats();
        sortStats(statList);
        tempStats = getStatsFormatted(tempStats, statList);

        nameList.put(index, itemName);

        if (Integer.valueOf(armor) > 0) {
            stats.put(index, String.format("%s<br>%s%s<br>%s Armor<br>%s", itemLvl, itemSlot, damageInfo, armor, tempStats));
        } else {
            stats.put(index, String.format("%s<br>%s%s<br>%s", itemLvl, itemSlot, damageInfo, tempStats));
        }

        if (itemSlot.equals("Head") && azeriteSpellsHead.size() > 0) {
            stats.put(index, stats.get(index) + String.format("<font size=5>%s</font>", getAzeritePowers(azeriteSpellsHead)));
        }

        if (itemSlot.equals("Shoulder") && azeriteSpellsShoulder.size() > 0) {
            stats.put(index, stats.get(index) + String.format("<font size=5>%s</font>", getAzeritePowers(azeriteSpellsShoulder)));
        }

        if (itemSlot.equals("Chest") && azeriteSpellsChest.size() > 0) {
            stats.put(index, stats.get(index) + String.format("<font size=5>%s</font>", getAzeritePowers(azeriteSpellsChest)));
        }

        if (!nameDescription.equals("<font color=#00cc00></font><br>")) {
            stats.put(index, nameDescription + stats.get(index));
        }

        if (!trigger.equals("")) {
            stats.put(index, stats.get(index) + String.format("<br>%s<br>", trigger));
        }

        if (!itemInformations.get(index).getDescription().equals("")) {
            stats.put(index, stats.get(index) + String.format("<br>%s<br>", description));
        }
        if (itemInformations.get(index).getMaxDurability() != 0) {
            stats.put(index, stats.get(index) + String.format("<br>%s<br>%s", durability, requiredLevel));
        } else {
            stats.put(index, stats.get(index) + String.format("<br>%s", requiredLevel));
        }
    }

    private void setCharacterInformationTextviews() throws JSONException {
        characterName.setText(characterInfo.getName());
        itemLVL.setText(String.format("Item Level : %s", itemObject.get("averageItemLevel")));

        health.setText(String.format("Health: %s", statsObject.get("health")));
        power.setText(String.format("%s: %s", formatItemSlotName(statsObject.get("powerType").toString().replace("-", " ")), statsObject.get("power")));

        strength.setText(String.format("Strength: %s", statsObject.get("str")));
        agility.setText(String.format("Agility: %s", statsObject.get("agi")));
        intellect.setText(String.format("Intellect: %s", statsObject.get("int")));
        stamina.setText(String.format("Stamina: %s", statsObject.get("sta")));

        crit.setText(String.format(Locale.ENGLISH, "Critical Strike: %.2f%%", (double) statsObject.get("crit")));
        haste.setText(String.format(Locale.ENGLISH, "Haste: %.2f%%", (double) statsObject.get("haste")));
        mastery.setText(String.format(Locale.ENGLISH, "Mastery: %.2f%%", (double) statsObject.get("mastery")));
        versatility.setText(String.format(Locale.ENGLISH, "Versatility: %.2f%%", (double) statsObject.get("versatilityDamageDoneBonus")));
    }

    private void removeTalents() {
        for (int i = 0; i < talentsContainer.size(); i++) {
            talentsTierContainer.get(i).setText("");
            talentsContainer.get(i).setText("");
        }
    }

    private void sortTalents() {
        talents.sort(new Comparator<Talents>() {
            @Override
            public int compare(Talents o1, Talents o2) {
                if (o1.getTier() < o2.getTier()) {
                    return -1;
                } else if (o1.getTier() > o2.getTier()) {
                    return 1;
                }
                return 0;
            }
        });
    }

    private String getAzeritePowers(ArrayList<JSONObject> azeriteSpells) throws JSONException {
        StringBuilder azeriteText = new StringBuilder("<br><font color=#edc201> Active Azerite Powers: </font><br>");
        for (int i = 0; i < azeriteSpells.size(); i++) {
            azeriteText.append(azeriteSpells.get(i).get("name")).append("<br>");
            azeriteText.append("<font color=#00cc00>").append(azeriteSpells.get(i).get("description")).append("</font><br>");
        }
        return azeriteText.toString();
    }
}
