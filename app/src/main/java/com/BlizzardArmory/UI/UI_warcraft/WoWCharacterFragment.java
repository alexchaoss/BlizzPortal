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
import com.BlizzardArmory.warcraft.CharacterSummary.CharacterSummary;
import com.BlizzardArmory.warcraft.Equipment.Equipment;
import com.BlizzardArmory.warcraft.Equipment.EquippedItem;
import com.BlizzardArmory.warcraft.Items.Item;
import com.BlizzardArmory.warcraft.Items.ItemInformation;
import com.BlizzardArmory.warcraft.Items.Stat;
import com.BlizzardArmory.warcraft.Items.StatsEnum;
import com.BlizzardArmory.warcraft.Media.Media;
import com.BlizzardArmory.warcraft.Spells.AzeritePower;
import com.BlizzardArmory.warcraft.Spells.ItemSpell;
import com.BlizzardArmory.warcraft.Talents.Talent;
import com.BlizzardArmory.warcraft.Talents.Talents;
import com.BlizzardArmory.warcraft.Statistic.Statistic;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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

    //private Gear equipment;
    private CharacterInformation characterInfo;

    //Character information
    CharacterSummary characterSummary;
    Statistic statistic;
    Talents talentsInfo;
    Equipment equipment;

    private HashMap<String, String> imageURLs = new HashMap<>();
    private int iconIndex = 0;

    private TextView characterName;
    private TextView itemLVL;
    private TextView levelRaceClass;
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
    private List<Talent> talents = new ArrayList<>();
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
    private ArrayList<String> itemSlotList = new ArrayList();
    private List<String> slotName;
    private HashMap<String, ImageView> gearImageView = new HashMap<>();
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
    private String socketString = "";
    private int socketIndex = 0;
    private int socketListIndex = 0;
    private ArrayList<String> socketList = new ArrayList<>();
    private ArrayList<String> transmogList = new ArrayList<>();

    private SparseArray<String> stats = new SparseArray<>();
    private SparseArray<String> nameList = new SparseArray<>();

    private int index = 0;
    private int indexMap = 0;

    private Cache cache;
    private Network network;
    private RequestQueue requestQueueImage;
    private RequestQueue requestQueue;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wow_character_fragment, container, false);
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
        levelRaceClass = view.findViewById(R.id.level_race_class);
        background = view.findViewById(R.id.background);
        cardView = view.findViewById(R.id.item_stats);
        cardView.setContentPadding(10, 10, 10, 10);
        ScrollView itemInfoScroll = new ScrollView(view.getContext());
        LinearLayout linearLayoutItemStats = new LinearLayout(view.getContext());
        linearLayoutItemStats.setOrientation(LinearLayout.VERTICAL);
        linearLayoutItemStats.setGravity(Gravity.CENTER);
        itemInfoScroll.addView(linearLayoutItemStats);
        cardView.addView(itemInfoScroll);
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

        gearImageView.put("HEAD", head);
        gearImageView.put("NECK", neck);
        gearImageView.put("SHOULDER", shoulder);
        gearImageView.put("BACK", back);
        gearImageView.put("CHEST", chest);
        gearImageView.put("SHIRT", shirt);
        gearImageView.put("TABARD", tabard);
        gearImageView.put("WRIST", wrist);
        gearImageView.put("HANDS", hands);
        gearImageView.put("WAIST", waist);
        gearImageView.put("LEGS", legs);
        gearImageView.put("FEET", feet);
        gearImageView.put("FINGER_1", finger1);
        gearImageView.put("FINGER_2", finger2);
        gearImageView.put("TRINKET_1", trinket1);
        gearImageView.put("TRINKET_2", trinket2);
        gearImageView.put("MAIN_HAND", mainHand);
        gearImageView.put("OFF_HAND", offHand);

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

        try {
            String characterURL = replaceZoneRealmCharacterNameURL(URLConstants.WOW_CHARACTER_QUERY);
            characterURL = characterURL.replace("TOKEN", bnOAuth2Helper.getAccessToken());

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI() + characterURL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            characterSummary = gson.fromJson(response.toString(), CharacterSummary.class);
                            characterName.setText(characterSummary.getName());
                            itemLVL.setText(String.format("Item Level : %s", characterSummary.getEquippedItemLevel()));
                            String levelRaceClassString = characterSummary.getLevel() + " " + characterSummary.getRace().getName() + " " + characterSummary.getCharacterClass().getName();
                            levelRaceClass.setText(levelRaceClassString);

                            try{
                                String equipmentURL = replaceZoneRealmCharacterNameURL(URLConstants.WOW_ITEM_QUERY);

                                equipmentURL = equipmentURL.replace("TOKEN", bnOAuth2Helper.getAccessToken());
                                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI() + equipmentURL, null,
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                final Gson gsonEquipment = new GsonBuilder().registerTypeAdapter(EquippedItem.class, new EquippedItem.NameDescriptionDeserializer()).create();
                                                equipment = gsonEquipment.fromJson(response.toString(), Equipment.class);

                                                for(int i = 0; i < equipment.getEquippedItems().size(); i++){
                                                    equipment.getEquippedItems().get(i).getMedia().getKey().getHref();
                                                    getImageURLs(equipment, i, equipment.getEquippedItems().get(i).getSlot().getType(),bnOAuth2Helper, gson);

                                                    try {
                                                            setCharacterItemsInformation(i);
                                                    }catch (Exception e){

                                                    }
                                                }

                                                for(String slot: gearImageView.keySet()){
                                                    boolean slotEquipped = false;
                                                    for(int i = 0; i < equipment.getEquippedItems().size(); i++) {
                                                        if(slot.equals(equipment.getEquippedItems().get(i).getSlot().getType())){
                                                            slotEquipped = true;
                                                        }
                                                    }
                                                    if(!slotEquipped){
                                                        getEmptySlotIcon(slot, WoWCharacterFragment.this.getContext());
                                                    }
                                                }




                                                Objects.requireNonNull(getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                                loadingCircle.setVisibility(View.GONE);
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError e) {

                                            }
                                        });

                                requestQueue.add(jsonRequest);
                            }catch (Exception e){
                                Log.e("Error Equipment Download", e.toString());
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
                                Objects.requireNonNull(getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                loadingCircle.setVisibility(View.GONE);
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

        try{
            String statsURL = replaceZoneRealmCharacterNameURL(URLConstants.WOW_STATS_QUERY);
            statsURL = statsURL.replace("TOKEN", bnOAuth2Helper.getAccessToken());
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI() + statsURL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            statistic = gson.fromJson(response.toString(), Statistic.class);
                            setCharacterInformationTextviews();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError e) {

                        }
                    });

            requestQueue.add(jsonRequest);
        }catch (Exception e){
            Log.e("Error Stats Download", e.toString());
        }

        try{
            String talentsURL = replaceZoneRealmCharacterNameURL(URLConstants.WOW_TALENT_QUERY);
            talentsURL = talentsURL.replace("TOKEN", bnOAuth2Helper.getAccessToken());
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI() + talentsURL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            talentsInfo = gson.fromJson(response.toString(), Talents.class);
                            setTalentInformation();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError e) {

                        }
                    });

            requestQueue.add(jsonRequest);
        }catch (Exception e){
            Log.e("Error Talents Download", e.toString());
        }



























/*
        try {

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI() +
                    swapRealmCharacterFromURL() + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken(), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            int maxLogSize = 4052;
                            for(int i = 0; i <= response.toString().length() / maxLogSize; i++) {
                                int start = i * maxLogSize;
                                int end = (i+1) * maxLogSize;
                                end = end > response.toString().length() ? response.toString().length() : end;
                                Log.v("CHARACTER_INFO", response.toString().substring(start, end));
                            }
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
                                Objects.requireNonNull(getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                loadingCircle.setVisibility(View.GONE);
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
        }*/

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000000;
        Log.i("time", String.valueOf(duration));
    }

    private void getImageURLs(final Equipment equipment, final int index, final String itemSlot, BnOAuth2Helper bnOAuth2Helper, final Gson gson) {
        cache = new DiskBasedCache(getContext().getCacheDir(), 1024 * 1024 * 5); // 1MB cap
        network = new BasicNetwork(new HurlStack());
        requestQueueImage = new RequestQueue(cache, network);
        requestQueueImage.start();
        try{

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, equipment.getEquippedItems().get(index).getMedia().getKey().getHref()
                    + "&" + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken(), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Media media = gson.fromJson(response.toString(), Media.class);
                            imageURLs.put(itemSlot, media.getAssets().get(0).getValue());
                            itemSlotList.add(itemSlot);
                            Log.i("Icon URL", media.getAssets().get(0).getValue());

                                    ImageRequest imageRequest = new ImageRequest(imageURLs.get(itemSlot), new Response.Listener<Bitmap>() {
                                        @Override
                                        public void onResponse(Bitmap bitmap) {
                                            item = new BitmapDrawable(getResources(), bitmap);
                                            setIcons(itemSlot, equipment, item);
                                        }
                                    }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                                            new Response.ErrorListener() {
                                                public void onErrorResponse(VolleyError e) {
                                                    Log.e("Error-Image", e.toString());
                                                }
                                            });
                                    requestQueueImage.add(imageRequest);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError e) {

                        }
                    });

            requestQueueImage.add(jsonRequest);
        }catch (Exception e){
            try {
                Log.e("Error Image URL Download", e.toString() + "\n" + equipment.getEquippedItems().get(index).getMedia().getKey().getHref()
                        + "&" + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken());
            }catch (Exception f){

            }
        }
    }

    private void setIcons(String itemSlot, Equipment equipment, Drawable item) {
        gearImageView.get(itemSlot).setImageDrawable(item);
        gearImageView.get(itemSlot).setPadding(3, 3, 3, 3);
        gearImageView.get(itemSlot).setClipToOutline(true);
        Drawable backgroundStroke = null;
        for (int i = 0; i < equipment.getEquippedItems().size(); i++) {
            if (equipment.getEquippedItems().get(i).getSlot().getType().equals(itemSlot)) {
                backgroundStroke = itemColor(equipment.getEquippedItems().get(i), new GradientDrawable());
            }
        }
        gearImageView.get(itemSlot).setBackground(backgroundStroke);
    }

    private String replaceZoneRealmCharacterNameURL(String url){
        String newURL = url;
        newURL = newURL.replace("zone", URLConstants.getRegion());
        newURL = newURL.replace("realm", characterRealm.toLowerCase());
        newURL = newURL.replace("characterName", characterClicked.toLowerCase());
        return newURL;
    }

    private void getItemInformation(final Gson gson, final BnOAuth2Helper bnOAuth2Helper) {

        for(int i = 0; i < urlItemInfo.size(); i++){
            socketList.add("");
        }

        for (int i = 0; i < urlItemInfo.size(); i++) {

            getSocketInformation(gson, bnOAuth2Helper, i);


            try{
                Log.i("ITEM-URL", urlItemInfo.get(i));
            }catch (Exception e){

            }

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, urlItemInfo.get(i), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("ITEM-INFO", response.toString());
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
                    });
            requestQueueImage.add(jsonRequest);
        }
    }

    private void getSocketInformation(final Gson gson, BnOAuth2Helper bnOAuth2Helper, int i) {
        final ArrayList<String> urlSocket = new ArrayList<>();

        try{
            urlSocket.add(URLConstants.getBaseURLforAPI() + URLConstants.BONUSID_QUERY.replace("id?b1=bonusList&", itemsInfoList.get(i).getTooltipParams().getGem0().toString() + "?") + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken());
        }catch (Exception e){
            urlSocket.add("");
            Log.e("URL_SOCKET", "BAD URL");
        }
        try{
            urlSocket.add(URLConstants.getBaseURLforAPI() + URLConstants.BONUSID_QUERY.replace("id?b1=bonusList&", itemsInfoList.get(i).getTooltipParams().getGem1().toString() + "?") + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken());
        }catch (Exception e){
            Log.e("URL_SOCKET", "BAD URL");
        }
        try{
            urlSocket.add(URLConstants.getBaseURLforAPI() + URLConstants.BONUSID_QUERY.replace("id?b1=bonusList&", itemsInfoList.get(i).getTooltipParams().getGem2().toString() + "?") + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken());
        }catch (Exception e){
            Log.e("URL_SOCKET", "BAD URL");
        }

        for (int j = 0; j < urlSocket.size(); j++) {
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, urlSocket.get(j), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            socketIndex++;
                            Item socket = gson.fromJson(response.toString(), Item.class);
                            Log.i("test", socket.getGemInfo().getBonus().getName());
                            if (!socket.getGemInfo().getBonus().getName().equals("Relic Enhancement")) {
                                socketString += "(s) " + socket.getGemInfo().getBonus().getName() + "<br>";
                            }

                            if(socketIndex == urlSocket.size()){
                                socketIndex = 0;
                                socketList.set(socketListIndex,socketString);
                                socketString = "";
                                socketListIndex++;
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError e) {
                            socketIndex = 0;
                            socketString = "";
                            socketListIndex++;
                        }
                    });
            requestQueueImage.add(jsonRequest);
        }
    }

    private void getAzeritePowers(final BnOAuth2Helper bnOAuth2Helper) {
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
                                                gearImageView.get(i).setPadding(3, 3, 3, 3);
                                                gearImageView.get(i).setClipToOutline(true);
                                                //Drawable backgroundStroke = itemColor(itemsInfoList.get(i), new GradientDrawable());
                                                //gearImageView.get(i).setBackground(backgroundStroke);

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
                        gearImageView.get(i).setPadding(3, 3, 3, 3);
                        //gearImageView.get(i).setClipToOutline(true);
                        //Drawable backgroundStroke = itemColor(itemsInfoList.get(i), new GradientDrawable());
                        //gearImageView.get(i).setBackground(backgroundStroke);
                    }
                }
            }
            Objects.requireNonNull(getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            loadingCircle.setVisibility(View.GONE);
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

            for (int k = 0; k < size; k++) {
                bonusIds.append(itemsInfoList.get(i).getBonusLists().get(k)).append(",");
            }
            idURL = itemsInfoList.get(i).getId().toString() + "?b1=" + bonusIds.substring(0, bonusIds.length() - 1);
        } else {
            idURL = itemsInfoList.get(i).getId().toString() + "?b1=";
        }

        return URLConstants.BONUSID_QUERY.replace("id?b1=bonusList", idURL);
    }

    /*private void initItems() {
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

    }*/

    private void getEmptySlotIcon(String itemSlot, Context context) {
        gearImageView.get(itemSlot).setPadding(3, 3, 3, 3);
        gearImageView.get(itemSlot).setClipToOutline(true);
        GradientDrawable backgroundStroke = new GradientDrawable();
        backgroundStroke.setCornerRadius(3);
        backgroundStroke.setColor(Color.parseColor("#000000"));
        backgroundStroke.setStroke(3,  Color.GRAY);
        gearImageView.get(itemSlot).setBackground(backgroundStroke);

        switch (itemSlot){
            case "HEAD":
                gearImageView.get(itemSlot).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_head));
                break;
            case "NECK":
                gearImageView.get(itemSlot).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_neck));
                break;
            case "SHOULDER":
                gearImageView.get(itemSlot).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_shoulders));
                break;
            case "CHEST":
            case "BACK":
                gearImageView.get(itemSlot).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_chest));
                break;
            case "SHIRT":
                gearImageView.get(itemSlot).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_shirt));
                break;
            case "TABARD":
                gearImageView.get(itemSlot).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_tabard));
                break;
            case "WRIST":
                gearImageView.get(itemSlot).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_wrist));
                break;
            case "HANDS":
                gearImageView.get(itemSlot).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_hands));
                break;
            case "WAIST":
                gearImageView.get(itemSlot).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_waist));
                break;
            case "LEGS":
                gearImageView.get(itemSlot).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_legs));
                break;
            case "FEET":
                gearImageView.get(itemSlot).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_feet));
                break;
            case "RING_1":
            case "RING_2":
                gearImageView.get(itemSlot).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_ring));
                break;
            case "TRINKET_1":
            case "TRINKET_2":
                gearImageView.get(itemSlot).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_trinket));
                break;
            case "MAIN_HAND":
                gearImageView.get(itemSlot).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_main_hand));
                break;
            case "OFF_HAND":
                gearImageView.get(itemSlot).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_shield));
                break;
        }
    }

    private String swapRealmCharacterFromURL() {
        return URLConstants.WOW_ITEM_QUERY.replace("realm/character", characterRealm + "/" + characterClicked);
    }

    private Drawable itemColor(EquippedItem equippedItem, GradientDrawable drawable) {
        drawable.setCornerRadius(3);
        drawable.setColor(Color.parseColor("#000000"));
        drawable.setStroke(3, getItemColor(equippedItem));

        return drawable;
    }

    private int getItemColor(EquippedItem item) {
        try {
            if (item.getQuality().getType().equals("POOR")) {
                return Color.GRAY;
            } else if (item.getQuality().getType().equals("COMMON")) {
                return Color.WHITE;
            } else if (item.getQuality().getType().equals("UNCOMMON")) {
                return Color.parseColor("#00cc00");
            } else if (item.getQuality().getType().equals("RARE")) {
                return Color.parseColor("#0070ff");
            } else if (item.getQuality().getType().equals("EPIC")) {
                return Color.parseColor("#663288");
            } else if (item.getQuality().getType().equals("LEGENDARY")) {
                return Color.parseColor("#ff8000");
            } else if (item.getQuality().getType().equals("ARTIFACT")) {
                return Color.parseColor("#b2a06c");
            } else if (item.getQuality().getType().equals("HEIRLOOM")) {
                return Color.parseColor("#01c5f7");
            }
        } catch (Exception e) {
            return Color.GRAY;
        }

        return 0;
    }

    private void setOnPressItemInformation(final ImageView imageView, Drawable icon, String itemSlot) {

        /*imageView.setOnTouchListener(new View.OnTouchListener() {

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
        });*/
        index++;
    }

    private void setTalentInformation() {
        for (int i = 0; i < talentsInfo.getSpecializations().size(); i++) {
            if (talentsInfo.getActiveSpecialization().getName().equals(talentsInfo.getSpecializations().get(i).getSpecialization().getName())) {
                try {
                    talents.addAll(talentsInfo.getSpecializations().get(i).getTalents());
                }catch (Exception e){
                    talents.add(new Talent());
                }
                spec.setText(String.format("Specialization: %s", talentsInfo.getActiveSpecialization().getName()));
            }
        }

        try {
            specs.addTab(specs.newTab());
            specs.getTabAt(3).setText(talentsInfo.getSpecializations().get(3).getSpecialization().getName());
        } catch (Exception e) {
            Log.e("Talents", "No fourth spec");
            specs.removeTab(specs.getTabAt(3));
        }

        for (int i = 0; i < talentsInfo.getSpecializations().size(); i++) {
            TabLayout.Tab tab = specs.getTabAt(i);
            try {
                assert tab != null;
                tab.setText(talentsInfo.getSpecializations().get(i).getSpecialization().getName());
            } catch (Exception e) {
                for (int j = 0; j < e.getStackTrace().length; j++) {
                    Log.e("Error", e.getStackTrace()[j].toString() + "\n");
                }
                tab.setText("Unavailable");
            }
        }

        if(talentsInfo.getSpecializations().size() < 3){
            TabLayout.Tab tab2 = specs.getTabAt(1);
            tab2.setText("Unavailable");
            TabLayout.Tab tab3 = specs.getTabAt(2);
            tab3.setText("Unavailable");
        }else if(talentsInfo.getSpecializations().size() < 2){
            TabLayout.Tab tab2 = specs.getTabAt(1);
            tab2.setText("Unavailable");
        }


        talentsTierContainer = new ArrayList<>(Arrays.asList(fifteen, thirty, forty_five,
                sixty, seventy_five, ninety, hundred));
        talentsContainer = new ArrayList<>(Arrays.asList(fifteenTalent, thirtyTalent, forty_fiveTalent,
                sixtyTalent, seventy_fiveTalent, ninetyTalent, hundredTalent));
        talentsTier = new ArrayList<>(Arrays.asList("15", "30", "45", "60", "75", "90", "100"));

        try {
            //sortTalents();

            if (talents.size() > 0) {
                for (int i = 0; i < talents.size(); i++) {
                    noTalent.setVisibility(View.INVISIBLE);
                    talentsTierContainer.get(i).setGravity(Gravity.CENTER);
                    talentsTierContainer.get(i).setText(talentsTier.get(i));
                    try {
                        talentsContainer.get(i).setText(talents.get(i).getTalent().getName());
                    }catch (Exception e){
                        removeTalents();
                        noTalent.setVisibility(View.VISIBLE);
                    }
                }
            } else {
                removeTalents();
                noTalent.setVisibility(View.VISIBLE);

            }
        } catch (NullPointerException e) {
            Log.e("Talent-Error", e.toString());
            noTalent.setVisibility(View.VISIBLE);
        }

        specs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        if(!tab.getText().equals("Unavailable")) {
                            getTalentsForSpecificSpec(0);
                        }
                        break;
                    case 1:
                        if(!tab.getText().equals("Unavailable")) {
                            getTalentsForSpecificSpec(1);
                        }
                        break;
                    case 2:
                        if(!tab.getText().equals("Unavailable")) {
                            getTalentsForSpecificSpec(2);
                        }
                        break;
                    case 3:
                        if(!tab.getText().equals("Unavailable")) {
                            getTalentsForSpecificSpec(3);
                        }
                        break;
                    default:
                }

            }

            private void getTalentsForSpecificSpec(int position) {
                try {
                    talents.clear();
                    talents.addAll(talentsInfo.getSpecializations().get(position).getTalents());
                    if (talents.size() == 0) {
                        noTalent.setVisibility(View.VISIBLE);
                        removeTalents();
                    } else {
                        noTalent.setVisibility(View.INVISIBLE);
                    }
                    //sortTalents();
                    for (int i = 0; i < talents.size(); i++) {
                        talentsTierContainer.get(i).setGravity(Gravity.CENTER);
                        talentsTierContainer.get(i).setText(talentsTier.get(i));
                        talentsContainer.get(i).setText(talents.get(i).getTalent().getName());
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


    private void setCharacterItemsInformation(final int index) throws JSONException {
        String nameDescription = "";
        String damageInfo = "";
        String durability = equipment.getEquippedItems().get(index).getDurability().getDisplayString();
        String requiredLevel = equipment.getEquippedItems().get(index).getRequirements().getLevel().getDisplayString();
        String itemName = equipment.getEquippedItems().get(index).getName();
        String itemLvl = "<font color=#edc201>" + equipment.getEquippedItems().get(index).getLevel().getDisplayString() + "</font>";
        String armor = equipment.getEquippedItems().get(index).getArmor().getDisplayString();
        String description = "<font color=#edc201>" + equipment.getEquippedItems().get(index).getNameDescription() + "</font>";
        if(equipment.getEquippedItems().get(index).getNameDescription().equals("")) {
            nameDescription = "<font color=#00cc00>" + equipment.getEquippedItems().get(index).getNameDescription() + "</font><br>";
        }else{
            nameDescription = "<font color=#00cc00>" + equipment.getEquippedItems().get(index).getNameDescriptionObject().getDisplayString() + "</font><br>";
        }
        String trigger = getTrigger(index);
        String HoALevel = "";
        if(itemsInfoList.get(1).getName().equals("Heart of Azeroth") && index == 1){
            HoALevel = "<font color=#edc201>Azerite Power Level " + itemsInfoList.get(1).getAzeriteItem().getAzeriteLevel().toString() + "</font><br>";
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
            stats.put(index, String.format("%s<br>%s%s%s<br>%s", itemLvl, HoALevel, itemSlot, damageInfo, tempStats));
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

        if(!socketList.get(index).equals("")){
            stats.put(index, stats.get(index) + String.format("<br>%s", socketList.get(index)));
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

    private void setCharacterInformationTextviews() {
        health.setText(String.format("Health: %s", statistic.getHealth()));
        power.setText(String.format("%s: %s", statistic.getPowerType().getName(), statistic.getPower()));

        strength.setText(String.format("Strength: %s", statistic.getStrength().getEffective()));
        agility.setText(String.format("Agility: %s",  statistic.getAgility().getEffective()));
        intellect.setText(String.format("Intellect: %s",  statistic.getIntellect().getEffective()));
        stamina.setText(String.format("Stamina: %s",  statistic.getStamina().getEffective()));

        crit.setText(String.format(Locale.ENGLISH, "Critical Strike: %.2f%%",  statistic.getMeleeCrit().getValue()));
        haste.setText(String.format(Locale.ENGLISH, "Haste: %.2f%%", (double)  statistic.getMeleeHaste().getValue()));
        mastery.setText(String.format(Locale.ENGLISH, "Mastery: %.2f%%", (double)  statistic.getMastery().getValue()));
        versatility.setText(String.format(Locale.ENGLISH, "Versatility: %.2f%%", (double)  statistic.getVersatilityDamageDoneBonus()));
    }

    private void removeTalents() {
        for (int i = 0; i < talentsContainer.size(); i++) {
            talentsTierContainer.get(i).setText("");
            talentsContainer.get(i).setText("");
        }
    }

    /*private void sortTalents() {
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
    }*/

    private String getAzeritePowers(ArrayList<JSONObject> azeriteSpells) throws JSONException {
        StringBuilder azeriteText = new StringBuilder("<br><font color=#edc201> Active Azerite Powers: </font><br>");
        for (int i = 0; i < azeriteSpells.size(); i++) {
            azeriteText.append(azeriteSpells.get(i).get("name")).append("<br>");
            azeriteText.append("<font color=#00cc00>").append(azeriteSpells.get(i).get("description")).append("</font><br>");
        }
        return azeriteText.toString();
    }
}
