package com.example.blizzardprofiles.UI.UI_warcraft;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.Log;
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

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.example.blizzardprofiles.R;
import com.example.blizzardprofiles.URLConstants;
import com.example.blizzardprofiles.connection.ConnectionService;
import com.example.blizzardprofiles.connection.ImageDownload;
import com.example.blizzardprofiles.warcraft.AzeritePower;
import com.example.blizzardprofiles.warcraft.CharacterInformation;
import com.example.blizzardprofiles.warcraft.Gear;
import com.example.blizzardprofiles.warcraft.Item;
import com.example.blizzardprofiles.warcraft.ItemInformation;
import com.example.blizzardprofiles.warcraft.ItemSpell;
import com.example.blizzardprofiles.warcraft.Stat;
import com.example.blizzardprofiles.warcraft.StatsEnum;
import com.example.blizzardprofiles.warcraft.Talent;
import com.example.blizzardprofiles.warcraft.Talents;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;


public class WoWCharacterFragment extends Fragment {

    private String characterRealm;
    private String characterClicked;
    private String urlMain;

    private JSONObject characterInformation;
    private JSONObject itemObject = null;
    private JSONObject statsObject = null;

    private Gear equipment;
    private Talent talents;
    private CharacterInformation characterInfo;

    //Character information
    private TextView characterName;
    private TextView itemLVL;
    private ImageView background;
    private CardView cardView;
    private TextView statsView;
    private TextView nameView;
    private ScrollView scrollView;

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
    private TextView  fifteen;
    private TextView  thirty;
    private TextView  forty_five;
    private TextView  sixty;
    private TextView  seventy_five;
    private TextView  ninety;
    private TextView  hundred;

    private RelativeLayout loadingCircle;

    //Containers
    private ArrayList<Drawable> icons = new ArrayList<>();
    private ArrayList<ImageView> gear = new ArrayList<>();
    private ArrayList<Item> itemsInfoList = new ArrayList<>();
    private ArrayList<JSONObject> bonusIDList = new ArrayList<>();
    private ArrayList<ItemInformation> itemInformations = new ArrayList<>();
    private ArrayList<String> urlItemInfo;
    private ArrayList<JSONObject> azeriteSpellsHead = new ArrayList<>();
    private ArrayList<JSONObject> azeriteSpellsShoulder = new ArrayList<>();
    private ArrayList<JSONObject> azeriteSpellsChest = new ArrayList<>();
    private List<AzeritePower> powerHead;
    private List<AzeritePower> powerShoulder;
    private List<AzeritePower> powerChest;

    private Map<Integer, String> stats = new HashMap<>();
    private Map<Integer, String> nameList = new HashMap<>();

    private int index = 0;

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
        cardView.setContentPadding(10,10,10,10);
        LinearLayout linearLayoutItemStats = new LinearLayout(view.getContext());
        linearLayoutItemStats.setOrientation(LinearLayout.VERTICAL);
        linearLayoutItemStats.setGravity(Gravity.CENTER);
        cardView.addView(linearLayoutItemStats);
        statsView = new TextView(view.getContext());
        nameView = new TextView(view.getContext());
        linearLayoutItemStats.addView(nameView);
        linearLayoutItemStats.addView(statsView);
        loadingCircle = view.findViewById(R.id.loadingCircle);

        enableHttpResponseCache();

        fifteen = view.findViewById(R.id.fifteen);
        thirty = view.findViewById(R.id.thirty);
        forty_five = view.findViewById(R.id.forty_five);
        sixty = view.findViewById(R.id.sixty);
        seventy_five = view.findViewById(R.id.seventy_five);
        ninety = view.findViewById(R.id.ninety);
        hundred = view.findViewById(R.id.hundred);

        Objects.requireNonNull(WoWCharacterFragment.this.getActivity()).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        new PrepareData().execute();

        LinearLayout.LayoutParams layoutParamsName = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams layoutParamsStats = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsName.setMargins(20,20,20,0);
        nameView.setLayoutParams(layoutParamsName);
        layoutParamsStats.setMargins(20,0,20,0);
        statsView.setLayoutParams(layoutParamsStats);

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

        gear.add(head);
        gear.add(neck);
        gear.add(shoulder);
        gear.add(back);
        gear.add(chest);
        gear.add(shirt);
        gear.add(tabard);
        gear.add(wrist);
        gear.add(hands);
        gear.add(waist);
        gear.add(legs);
        gear.add(feet);
        gear.add(finger1);
        gear.add(finger2);
        gear.add(trinket1);
        gear.add(trinket2);
        gear.add(mainHand);
        gear.add(offHand);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    private String getTrigger(int index) {
        StringBuilder trigger = new StringBuilder();
        for(int i = 0; i < itemInformations.get(index).getItemSpells().size();i++){
            ItemSpell itemSpell = itemInformations.get(index).getItemSpells().get(i);
            if(itemSpell.getTrigger().equals("ON_EQUIP") && !itemSpell.getScaledDescription().equals("")){
                trigger.append("<font color=#00cc00>Equip: ").append(itemSpell.getScaledDescription()).append("</font>");
            }else if(itemSpell.getTrigger().equals("ON_USE") && !itemSpell.getScaledDescription().equals("")){
                if(trigger.toString().equals("")){
                    trigger.append("<font color=#00cc00>Use: ").append(itemSpell.getScaledDescription()).append("</font>");
                }else{
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
            if(stat.getStat() > 7 && stat.getStat() < 71){
                currentStat = "<font color=#00cc00>" + "+" + StatsEnum.fromOrdinal(stat.getStat())  + " " + stat.getAmount() + "</font>";
            }else{
                currentStat = "+" +  StatsEnum.fromOrdinal(stat.getStat()).toString() + " " + stat.getAmount();
            }
            tempStatsBuilder.append(currentStat).append("<br>");
        }
        tempStats = tempStatsBuilder.toString();
        return tempStats;
    }

    private String getDamageInformation(String itemSlot, String damageInfo) {
        if(itemSlot.equals("Off-Hand") && itemsInfoList.get(index).getWeaponInfo() != null|| itemSlot.equals("Main-Hand")){
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
                if(o2.getStat() > 70){
                    return 1;
                }else if(o1.getStat() > 70){
                    return -1;
                }
                return o1.getStat().compareTo(o2.getStat());
            }
        });
    }

    private String formatItemSlotName(String itemSlot) {
        itemSlot = itemSlot.substring(0,1).toUpperCase() + itemSlot.substring(1);
        itemSlot = itemSlot.replace("_", "-");
        if(itemSlot.contains("-")) {
            char before = itemSlot.charAt(itemSlot.indexOf("-")+1);
            itemSlot = itemSlot.replace(before, Character.toUpperCase(before));
        }
        itemSlot = itemSlot.replaceAll("[0-9]", "");
        return itemSlot;
    }

    private String swapItemIDBonusID(int i) {
        StringBuilder bonusIds = new StringBuilder();
        String idURL;
        int size = itemsInfoList.get(i).getBonusLists().size();
        if(size > 0){

            for(int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    bonusIds.append(itemsInfoList.get(i).getBonusLists().get(k)).append(",");
                }
            }
            idURL = itemsInfoList.get(i).getId().toString() + "?b1=" + bonusIds.substring(0,bonusIds.length()-1);
        }else{
            idURL = itemsInfoList.get(i).getId().toString() + "?b1=";
        }

        return URLConstants.BONUSID_QUERY.replace("id?b1=bonusList",  idURL );
    }

    private void getIcons(View view) {
        Gson gson = new GsonBuilder().create();
        ArrayList<JSONObject> gearList = new ArrayList<>();

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

        for(int i = 0; i < gearList.size(); i++){
            if (gearList.get(i) == null) {
                itemsInfoList.add(new Item());
                Drawable noItem = getEmptySlotIcon(i, view.getContext());
                icons.add(noItem);
            } else {
                Item currentItem = gson.fromJson(gearList.get(i).toString(), Item.class);
                itemsInfoList.add(currentItem);
                Drawable item = new ImageDownload(currentItem.getIcon()+ ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext(), characterInformation).getImageFromURL().get(0);
                icons.add(item);
            }
        }
    }

    private Drawable getEmptySlotIcon(int index, Context context) {
        if(index == 0){
            return ContextCompat.getDrawable(context, R.drawable.empty_head);
        }else if(index == 1){
            return ContextCompat.getDrawable(context, R.drawable.empty_neck);
        }else if(index == 2){
            return ContextCompat.getDrawable(context, R.drawable.empty_shoulders);
        }else if(index == 3){
            return ContextCompat.getDrawable(context, R.drawable.empty_back);
        }else if(index == 4){
            return ContextCompat.getDrawable(context, R.drawable.empty_chest);
        }else if(index == 5){
            return ContextCompat.getDrawable(context, R.drawable.empty_shirt);
        }else if(index == 6){
            return ContextCompat.getDrawable(context, R.drawable.empty_tabard);
        }else if(index == 7){
            return ContextCompat.getDrawable(context, R.drawable.empty_wrist);
        }else if(index == 8){
            return ContextCompat.getDrawable(context, R.drawable.empty_hands);
        }else if(index == 9){
            return ContextCompat.getDrawable(context, R.drawable.empty_waist);
        }else if(index == 10){
            return ContextCompat.getDrawable(context, R.drawable.empty_legs);
        }else if(index == 11){
            return ContextCompat.getDrawable(context, R.drawable.empty_feet);
        }else if(index == 12){
            return ContextCompat.getDrawable(context, R.drawable.empty_ring);
        }else if(index == 13){
            return ContextCompat.getDrawable(context, R.drawable.empty_ring);
        }else if(index == 14){
            return ContextCompat.getDrawable(context, R.drawable.empty_trinket);
        }else if(index == 15){
            return ContextCompat.getDrawable(context, R.drawable.empty_trinket);
        }else if(index == 16){
            return ContextCompat.getDrawable(context, R.drawable.empty_main_hand);
        }else if(index == 17){
            return ContextCompat.getDrawable(context, R.drawable.empty_shield);
        }

        return null;
    }

    private String swapRealmCharacterFromURL() {
        return URLConstants.WOW_ITEM_QUERY.replace("realm/character", characterRealm + "/" + characterClicked);
    }

    private Drawable itemColor(Item item, GradientDrawable drawable){
        drawable.setCornerRadius(3);
        drawable.setColor(Color.parseColor("#000000"));
        drawable.setStroke(3,getItemColor(item));

        return drawable;
    }

    private int getItemColor(Item item){
        if(item.getQuality() == 0){
            return Color.GRAY;
        }else if(item.getQuality() == 1){
            return Color.WHITE;
        }else if(item.getQuality() == 2){
            return Color.parseColor("#00cc00");
        }else if(item.getQuality() == 3){
            return Color.parseColor("#0070ff");
        }else if(item.getQuality() == 4){
            return Color.parseColor("#663288");
        }else if(item.getQuality() == 5){
            return Color.parseColor("#ff8000");
        }else if(item.getQuality() == 6){
            return Color.parseColor("#b2a06c");
        }else if(item.getQuality() == 7){
            return Color.parseColor("#01c5f7");
        }
        return 0;
    }

    private class PrepareData extends AsyncTask<Void, Void, Void> {


        protected void onPreExecute () {
            super.onPreExecute();
            loadingCircle.setVisibility(View.VISIBLE);
        }

        protected Void doInBackground(Void... param) {
            long startTime = System.nanoTime();

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(WoWCharacterFragment.this.getContext());
            BnOAuth2Params bnOAuth2Params = Objects.requireNonNull(Objects.requireNonNull(WoWCharacterFragment.this.getActivity()).getIntent().getExtras()).getParcelable(BnConstants.BUNDLE_BNPARAMS);
            assert bnOAuth2Params != null;
            BnOAuth2Helper bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

            try {
                characterInformation = new JSONObject(new ConnectionService(URLConstants.getBaseURLforAPI() +
                        swapRealmCharacterFromURL() + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken(), getContext()).getStringJSONFromRequest().get(0));
            } catch (Exception e) {
                Log.e("Error", e.toString());
            }

            try {
                if (characterInformation.isNull("items")) {
                    Log.i("Character too old", "Havn't logged in in a long time.");
                } else {
                    Gson gson = new GsonBuilder().create();
                    characterInfo = gson.fromJson(characterInformation.toString(), CharacterInformation.class);
                    itemObject = characterInformation.getJSONObject("items");
                    statsObject = characterInformation.getJSONObject("stats");
                    backgroundMain = new ImageDownload(urlMain, URLConstants.getRenderZoneURL(), WoWCharacterFragment.this.getContext(), characterInformation).getImageFromURL().get(0);

                    equipment = new Gear(itemObject);
                    getIcons(WoWCharacterFragment.this.getView());
                    urlItemInfo = new ArrayList<>();

                    for (int i = 0; i < gear.size(); i++) {
                        if (itemsInfoList.get(i).getName() != null) {
                            urlItemInfo.add(URLConstants.getBaseURLforAPI() +
                                    swapItemIDBonusID(i) + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken());
                        } else {
                            urlItemInfo.add(null);
                        }
                    }
                    ArrayList<String> tempJSON = new ConnectionService(urlItemInfo, getContext()).getStringJSONFromRequest();

                    for (int i = 0; i < urlItemInfo.size(); i++) {
                        if (tempJSON.get(i) != null) {
                            bonusIDList.add(new JSONObject(tempJSON.get(i)));
                        } else {
                            bonusIDList.add(null);
                        }
                    }




                    for (int i = 0; i < bonusIDList.size(); i++) {
                        if (bonusIDList.get(i) != null) {
                            ItemInformation itemInformation = gson.fromJson(bonusIDList.get(i).toString(), ItemInformation.class);
                            itemInformations.add(itemInformation);
                        } else {
                            itemInformations.add(new ItemInformation());
                        }
                    }

                        ArrayList<String> tempSpell= new ArrayList<>();
                    if(itemsInfoList.get(0).getAzeriteEmpoweredItem().getAzeritePowers() != null) {
                        powerHead = itemsInfoList.get(0).getAzeriteEmpoweredItem().getAzeritePowers();
                    }
                    if(itemsInfoList.get(2).getAzeriteEmpoweredItem().getAzeritePowers() != null) {
                        powerShoulder = itemsInfoList.get(2).getAzeriteEmpoweredItem().getAzeritePowers();
                    }
                    if(itemsInfoList.get(4).getAzeriteEmpoweredItem().getAzeritePowers() != null) {
                        powerChest = itemsInfoList.get(4).getAzeriteEmpoweredItem().getAzeritePowers();
                    }

                        for (AzeritePower azeritePower : powerHead) {
                            if (azeritePower.getSpellId() != 0) {
                                tempSpell.add(URLConstants.getBaseURLforAPI() + URLConstants.SPELL_ID_QUERY + azeritePower.getSpellId() + "?" +
                                        URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken());
                            }
                        }

                        ArrayList<String> tempSpellHead = new ConnectionService(tempSpell, getContext()).getStringJSONFromRequest();
                        tempSpell.clear();

                        for (AzeritePower azeritePower : powerShoulder) {
                            if (azeritePower.getSpellId() != 0) {
                                tempSpell.add(URLConstants.getBaseURLforAPI() + URLConstants.SPELL_ID_QUERY + azeritePower.getSpellId() + "?" +
                                        URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken());
                            }
                        }

                        ArrayList<String> tempSpellShoulder = new ConnectionService(tempSpell, getContext()).getStringJSONFromRequest();
                        tempSpell.clear();

                        for (AzeritePower azeritePower : powerChest) {
                            if (azeritePower.getSpellId() != 0) {
                                tempSpell.add(URLConstants.getBaseURLforAPI() + URLConstants.SPELL_ID_QUERY + azeritePower.getSpellId() + "?" +
                                        URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken());
                            }
                        }

                        ArrayList<String> tempSpellChest = new ConnectionService(tempSpell, getContext()).getStringJSONFromRequest();
                        tempSpell.clear();
                        Log.i("test", tempSpellHead.toString());

                        for (int i = 0; i < tempSpellHead.size(); i++) {
                            azeriteSpellsHead.add(new JSONObject(tempSpellHead.get(i)));
                        }
                        for (int i = 0; i < tempSpellShoulder.size(); i++) {
                            azeriteSpellsShoulder.add(new JSONObject(tempSpellShoulder.get(i)));
                        }
                        for (int i = 0; i < tempSpellChest.size(); i++) {
                            azeriteSpellsChest.add(new JSONObject(tempSpellChest.get(i)));
                        }



                }

            } catch (JSONException e) {
                Log.e("Error", e.toString());
            }catch (IOException e) {
                Log.e("Error", e.toString());
            }catch (NullPointerException e) {
                Log.e("Error", e.toString());
            }

            long endTime = System.nanoTime();
            long duration = (endTime - startTime)/1000000000;
            Log.i("time", String.valueOf(duration));
            return null;
        }

        @SuppressLint("ClickableViewAccessibility")
        protected void onPostExecute(Void param) {
            super.onPostExecute(param);

            try {
                if (characterInformation.isNull("items")) {
                    Log.i("test", "test");
                    characterName.setText(characterInformation.get("reason").toString());
                    background.setBackgroundColor(getResources().getColor(R.color.wowBackgroundColor, Objects.requireNonNull(WoWCharacterFragment.this.getContext()).getTheme()));
                    itemLVL.setText("");
                } else {
                    background.setImageDrawable(backgroundMain);
                    characterName.setText(characterInfo.getName());
                    itemLVL.setText(String.format("Item Level: %s", itemObject.get("averageItemLevel")));

                    health.setText(String.format("Health: %s", statsObject.get("health")));
                    power.setText(String.format("%s: %s", formatItemSlotName(statsObject.get("powerType").toString().replace("-", " ")), statsObject.get("power")));

                    strength.setText(String.format("Strength: %s", statsObject.get("str")));
                    agility.setText(String.format("Agility: %s", statsObject.get("agi")));
                    intellect.setText(String.format("Intellect: %s", statsObject.get("int")));
                    stamina.setText(String.format("Stamina: %s", statsObject.get("sta")));

                    crit.setText(String.format(Locale.ENGLISH, "Critical Strike: %.2f%%", (double)statsObject.get("crit")));
                    haste.setText(String.format(Locale.ENGLISH, "Haste: %.2f%%", (double) statsObject.get("haste")));
                    mastery.setText(String.format(Locale.ENGLISH, "Mastery: %.2f%%", (double) statsObject.get("mastery")));
                    versatility.setText(String.format(Locale.ENGLISH, "Versatility: %.2f%%", (double) statsObject.get("versatilityDamageDoneBonus")));

                    List<Talents> talents = new ArrayList<>();
                    for(int i = 0; i < characterInfo.getTalents().size(); i++) {
                        if(characterInfo.getTalents().get(i).getSelected() == true) {
                            talents = characterInfo.getTalents().get(i).getTalents();
                        }

                    }
                    Log.e("Test", String.valueOf(talents.size()));

                    //fifteen.setText(String.format("15 %s", );
                    //thirty.setText(String.format("30 %s", characterInfo.getTalents().get(0).getTalents().get(0).getSpell().getName()));
                    //forty_five.setText(String.format("45 %s", characterInfo.getTalents().get(0).getTalents().get(0).getSpell().getName()));
                    //sixty.setText(String.format("60 %s", characterInfo.getTalents().get(0).getTalents().get(0).getSpell().getName()));
                    //seventy_five.setText(String.format("75 %s", characterInfo.getTalents().get(0).getTalents().get(0).getSpell().getName()));
                    //ninety.setText(String.format("90 %s", characterInfo.getTalents().get(0).getTalents().get(0).getSpell().getName()));
                    //hundred.setText(String.format("100 %s", characterInfo.getTalents().get(0).getTalents().get(0).getSpell().getName()));



                    for(final ImageView imageView: gear){

                        if(itemsInfoList.get(index).getName() != null){
                            String description = "";
                            String nameDescription = "";
                            String trigger = "";
                            String damageInfo = "";
                            String durability = "Durability " + itemInformations.get(index).getMaxDurability() + "/" + itemInformations.get(index).getMaxDurability();
                            String requiredLevel = "Requires Level " + itemInformations.get(index).getRequiredLevel();
                            Drawable backgroundStroke = itemColor(itemsInfoList.get(index), new GradientDrawable());
                            String itemName = itemsInfoList.get(index).getName();
                            String itemLvl = "<font color=#edc201>Item Level " + itemsInfoList.get(index).getItemLevel().toString() + "</font>";
                            String armor = itemsInfoList.get(index).getArmor().toString();
                            if(itemInformations.get(index).getName() != null){
                                description = "<font color=#edc201>" + itemInformations.get(index).getDescription() + "</font>";
                                nameDescription = "<font color=#00cc00>" + itemInformations.get(index).getNameDescription() + "</font><br>";
                                trigger = getTrigger(index);
                            }

                            String itemSlot = WoWCharacterFragment.this.getResources().getResourceEntryName(imageView.getId());
                            itemSlot = formatItemSlotName(itemSlot);


                            damageInfo = getDamageInformation(itemSlot, damageInfo);

                            String tempStats = "";
                            List<Stat> statList = itemsInfoList.get(index).getStats();
                            sortStats(statList);
                            tempStats = getStatsFormatted(tempStats, statList);

                            nameList.put(index, itemName);

                            if(Integer.valueOf(armor) > 0){
                                stats.put(index, String.format("%s<br>%s%s<br>%s Armor<br>%s",itemLvl, itemSlot, damageInfo, armor, tempStats));
                            }else {
                                stats.put(index, String.format("%s<br>%s%s<br>%s",itemLvl, itemSlot, damageInfo,tempStats));
                            }

                            if(itemSlot.equals("Head") && azeriteSpellsHead.size() > 0){
                                stats.put(index, stats.get(index) + String.format("%s", getAzeritePowers(azeriteSpellsHead)));
                            }

                            if(itemSlot.equals("Shoulder") && azeriteSpellsShoulder.size() > 0){
                                stats.put(index, stats.get(index) + String.format("%s", getAzeritePowers(azeriteSpellsShoulder)));
                            }

                            if(itemSlot.equals("Chest") && azeriteSpellsChest.size() > 0){
                                stats.put(index, stats.get(index) + String.format("%s", getAzeritePowers(azeriteSpellsChest)));
                            }

                            if(!nameDescription.equals("<font color=#00cc00></font><br>")){
                                stats.put(index, nameDescription + stats.get(index));
                            }

                            if (!trigger.equals("")){
                                stats.put(index, stats.get(index) + String.format("<br>%s<br>", trigger));
                            }

                            if(!itemInformations.get(index).getDescription().equals("")){
                                stats.put(index, stats.get(index) + String.format("<br>%s<br>", description));
                            }
                            if(itemInformations.get(index).getMaxDurability() != 0){
                                stats.put(index, stats.get(index) + String.format("<br>%s<br>%s", durability, requiredLevel));
                            }else{
                                stats.put(index, stats.get(index) + String.format("<br>%s", requiredLevel));
                            }


                            imageView.setBackground(backgroundStroke);
                        }

                        imageView.setId(index);
                        imageView.setImageDrawable(icons.get(index));
                        imageView.setPadding(3,3,3,3);
                        imageView.setClipToOutline(true);

                        imageView.setOnTouchListener(new View.OnTouchListener() {

                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                switch (event.getAction()){
                                    case MotionEvent.ACTION_DOWN:
                                    {
                                        if(stats.get(imageView.getId()) != null){
                                            nameView.setText(nameList.get(imageView.getId()));
                                            nameView.setTextColor(getItemColor(itemsInfoList.get(imageView.getId())));
                                            nameView.setTextSize(17);
                                            statsView.setText(Html.fromHtml(stats.get(imageView.getId())));
                                            statsView.setTextColor(Color.WHITE);
                                            statsView.setTextSize(13);
                                            cardView.setContentPadding(10,10,10,10);
                                            cardView.setBackground(imageView.getBackground());
                                            cardView.setVisibility(View.VISIBLE);
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
                                if(oldScrollY != scrollY){
                                    cardView.setVisibility(View.GONE);
                                }
                            }


                        });
                        index++;
                    }
                }

            } catch (JSONException e) {
                Log.e("Error", e.toString());
            }
            Objects.requireNonNull(WoWCharacterFragment.this.getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            loadingCircle.setVisibility(View.GONE);
        }
    }

    private String getAzeritePowers(ArrayList<JSONObject> azeriteSpells) throws JSONException{
        StringBuilder azeriteText = new StringBuilder("<br><font color=#edc201> Active Azerite Powers: </font><br>");
        for(int i = 0; i <azeriteSpells.size(); i++){
            azeriteText.append(azeriteSpells.get(i).get("name")).append("<br>");
            azeriteText.append("<font color=#00cc00>").append(azeriteSpells.get(i).get("description")).append("</font><br>");
        }
        return azeriteText.toString();
    }

    public void enableHttpResponseCache() {
        final long httpCacheSize = 30 * 1024 * 1024; // 10 MiB
        final File httpCacheDir = new File(Objects.requireNonNull(getContext()).getCacheDir(), "https");
        try {
            Class.forName("android.net.http.HttpResponseCache")
                    .getMethod("install", File.class, long.class)
                    .invoke(null, httpCacheDir, httpCacheSize);
        } catch (Exception httpResponseCacheNotAvailable) {
            try{
                HttpResponseCache.install(httpCacheDir, httpCacheSize); // Library that implements HttpResponseCache for pre-ICS phones
            } catch(Exception e){
                Log.e("Error: ", e.toString());
            }
        }
    }
}
