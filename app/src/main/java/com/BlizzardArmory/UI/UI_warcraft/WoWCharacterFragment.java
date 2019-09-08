package com.BlizzardArmory.UI.UI_warcraft;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
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
import android.widget.Toast;

import com.BlizzardArmory.R;
import com.BlizzardArmory.UI.UI_diablo.D3Activity;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.connection.ConnectionService;
import com.BlizzardArmory.connection.ImageDownload;
import com.BlizzardArmory.warcraft.Spells.AzeritePower;
import com.BlizzardArmory.warcraft.CharacterInformation;
import com.BlizzardArmory.warcraft.Items.Gear;
import com.BlizzardArmory.warcraft.Items.Item;
import com.BlizzardArmory.warcraft.Items.ItemInformation;
import com.BlizzardArmory.warcraft.Spells.ItemSpell;
import com.BlizzardArmory.warcraft.Items.Stat;
import com.BlizzardArmory.warcraft.Items.StatsEnum;
import com.BlizzardArmory.warcraft.Spells.Talents;
import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class WoWCharacterFragment extends Fragment {

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
    private TextView  noTalent;
    private TextView  fifteen;
    private TextView  thirty;
    private TextView  forty_five;
    private TextView  sixty;
    private TextView  seventy_five;
    private TextView  ninety;
    private TextView  hundred;
    private TextView  fifteenTalent;
    private TextView  thirtyTalent;
    private TextView  forty_fiveTalent;
    private TextView  sixtyTalent;
    private TextView  seventy_fiveTalent;
    private TextView  ninetyTalent;
    private TextView  hundredTalent;

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

    private SparseArray<String> stats = new SparseArray<>();
    private SparseArray<String> nameList = new SparseArray<>();

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
        spec = view.findViewById(R.id.specialization);
        specs = view.findViewById(R.id.tabLayout);

        enableHttpResponseCache();

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

        new PrepareDataWoWCharacter(this).execute();
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

    private static class PrepareDataWoWCharacter extends AsyncTask<Void, Void, Void> {

        private WeakReference<WoWCharacterFragment> activityReference;

        PrepareDataWoWCharacter(WoWCharacterFragment context) {
            activityReference = new WeakReference<>(context);
        }


        protected void onPreExecute () {
            super.onPreExecute();
            WoWCharacterFragment activity = activityReference.get();
            activity.loadingCircle.setVisibility(View.VISIBLE);
        }

        protected Void doInBackground(Void... param) {
            long startTime = System.nanoTime();

            WoWCharacterFragment activity = activityReference.get();

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity.getContext());
            BnOAuth2Params bnOAuth2Params = Objects.requireNonNull(Objects.requireNonNull(activity.getActivity()).getIntent().getExtras()).getParcelable(BnConstants.BUNDLE_BNPARAMS);
            assert bnOAuth2Params != null;
            BnOAuth2Helper bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

            try {
                activity.characterInformation = new JSONObject(new ConnectionService(URLConstants.getBaseURLforAPI() +
                        activity.swapRealmCharacterFromURL() + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken(), activity.getContext()).getStringJSONFromRequest().get(0));
            } catch (Exception e) {
                Log.e("Error", e.toString());
            }

            try {
                if (activity.characterInformation.isNull("items")) {
                    Log.i("Character too old", "Havn't logged in in a long time.");
                } else {
                    Gson gson = new GsonBuilder().create();
                    activity.characterInfo = gson.fromJson(activity.characterInformation.toString(), CharacterInformation.class);
                    activity.itemObject = activity.characterInformation.getJSONObject("items");
                    activity.statsObject = activity.characterInformation.getJSONObject("stats");
                    activity.backgroundMain = new ImageDownload(activity.urlMain, URLConstants.getRenderZoneURL(), activity.getContext(), activity.characterInformation).getImageFromURL().get(0);

                    activity.equipment = new Gear(activity.itemObject);
                    activity.getIcons(activity.getView());
                    activity.urlItemInfo = new ArrayList<>();

                    for (int i = 0; i < activity.gear.size(); i++) {
                        if (activity.itemsInfoList.get(i).getName() != null) {
                            activity.urlItemInfo.add(URLConstants.getBaseURLforAPI() +
                                    activity.swapItemIDBonusID(i) + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken());
                        } else {
                            activity.urlItemInfo.add(null);
                        }
                    }
                    ArrayList<String> tempJSON = new ConnectionService(activity.urlItemInfo, activity.getContext()).getStringJSONFromRequest();

                    for (int i = 0; i < activity.urlItemInfo.size(); i++) {
                        if (tempJSON.get(i) != null) {
                            activity.bonusIDList.add(new JSONObject(tempJSON.get(i)));
                        } else {
                            activity.bonusIDList.add(null);
                        }
                    }




                    for (int i = 0; i < activity.bonusIDList.size(); i++) {
                        if (activity.bonusIDList.get(i) != null) {
                            ItemInformation itemInformation = gson.fromJson(activity.bonusIDList.get(i).toString(), ItemInformation.class);
                            activity.itemInformations.add(itemInformation);
                        } else {
                            activity.itemInformations.add(new ItemInformation());
                        }
                    }

                        ArrayList<String> tempSpell= new ArrayList<>();
                    if(activity.itemsInfoList.get(0).getAzeriteEmpoweredItem().getAzeritePowers() != null) {
                        activity.powerHead = activity.itemsInfoList.get(0).getAzeriteEmpoweredItem().getAzeritePowers();
                    }
                    if(activity.itemsInfoList.get(2).getAzeriteEmpoweredItem().getAzeritePowers() != null) {
                        activity.powerShoulder = activity.itemsInfoList.get(2).getAzeriteEmpoweredItem().getAzeritePowers();
                    }
                    if(activity.itemsInfoList.get(4).getAzeriteEmpoweredItem().getAzeritePowers() != null) {
                        activity.powerChest = activity.itemsInfoList.get(4).getAzeriteEmpoweredItem().getAzeritePowers();
                    }

                        for (AzeritePower azeritePower : activity.powerHead) {
                            if (azeritePower.getSpellId() != 0) {
                                tempSpell.add(URLConstants.getBaseURLforAPI() + URLConstants.SPELL_ID_QUERY + azeritePower.getSpellId() + "?" +
                                        URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken());
                            }
                        }

                        ArrayList<String> tempSpellHead = new ConnectionService(tempSpell, activity.getContext()).getStringJSONFromRequest();
                        tempSpell.clear();

                        for (AzeritePower azeritePower : activity.powerShoulder) {
                            if (azeritePower.getSpellId() != 0) {
                                tempSpell.add(URLConstants.getBaseURLforAPI() + URLConstants.SPELL_ID_QUERY + azeritePower.getSpellId() + "?" +
                                        URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken());
                            }
                        }

                        ArrayList<String> tempSpellShoulder = new ConnectionService(tempSpell, activity.getContext()).getStringJSONFromRequest();
                        tempSpell.clear();

                        for (AzeritePower azeritePower : activity.powerChest) {
                            if (azeritePower.getSpellId() != 0) {
                                tempSpell.add(URLConstants.getBaseURLforAPI() + URLConstants.SPELL_ID_QUERY + azeritePower.getSpellId() + "?" +
                                        URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken());
                            }
                        }

                        ArrayList<String> tempSpellChest = new ConnectionService(tempSpell, activity.getContext()).getStringJSONFromRequest();
                        tempSpell.clear();
                        Log.i("test", tempSpellHead.toString());

                        for (int i = 0; i < tempSpellHead.size(); i++) {
                            activity.azeriteSpellsHead.add(new JSONObject(tempSpellHead.get(i)));
                        }
                        for (int i = 0; i < tempSpellShoulder.size(); i++) {
                            activity.azeriteSpellsShoulder.add(new JSONObject(tempSpellShoulder.get(i)));
                        }
                        for (int i = 0; i < tempSpellChest.size(); i++) {
                            activity.azeriteSpellsChest.add(new JSONObject(tempSpellChest.get(i)));
                        }



                }

            } catch (JSONException | NullPointerException | IOException e) {
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

            WoWCharacterFragment activity = activityReference.get();

            try {
                if (activity.characterInformation.isNull("items")) {
                    Log.i("test", "test");
                    activity.characterName.setText(activity.characterInformation.get("reason").toString());
                    activity.background.setBackgroundColor(activity.getResources().getColor(R.color.wowBackgroundColor, Objects.requireNonNull(activity.getContext()).getTheme()));
                    activity.itemLVL.setText("");
                } else {
                    setCharacterInformationTextviews(activity);

                    setTalentInformation(activity);

                    for(final ImageView imageView: activity.gear){

                        if(activity.itemsInfoList.get(activity.index).getName() != null){
                            setCharacterItemsInformation(activity, imageView);
                        }

                        setOnPressItemInformation(activity, imageView);
                    }
                }

            } catch (JSONException e) {
                Log.e("Error", e.toString());
            }
            Objects.requireNonNull(activity.getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            activity.loadingCircle.setVisibility(View.GONE);
        }

        private void setOnPressItemInformation(WoWCharacterFragment activity, final ImageView imageView) {
            imageView.setId(activity.index);
            imageView.setImageDrawable(activity.icons.get(activity.index));
            imageView.setPadding(3,3,3,3);
            imageView.setClipToOutline(true);

            imageView.setOnTouchListener(new View.OnTouchListener() {

                WoWCharacterFragment activity = activityReference.get();

                @Override
                @SuppressWarnings("deprecation")
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                        {
                            if(activity.stats.get(imageView.getId()) != null){
                                activity.nameView.setText(activity.nameList.get(imageView.getId()));
                                activity.nameView.setTextColor(activity.getItemColor(activity.itemsInfoList.get(imageView.getId())));
                                activity.nameView.setTextSize(17);
                                if(Build.VERSION.SDK_INT >= 24){
                                    activity.statsView.setText(Html.fromHtml(activity.stats.get(imageView.getId()), Html.FROM_HTML_MODE_LEGACY));
                                }else {
                                    activity.statsView.setText(Html.fromHtml(activity.stats.get(imageView.getId())));
                                }
                                activity.statsView.setTextColor(Color.WHITE);
                                activity.statsView.setTextSize(13);
                                activity.cardView.setContentPadding(10,10,10,10);
                                activity.cardView.setBackground(imageView.getBackground());
                                activity.cardView.setVisibility(View.VISIBLE);
                            }
                            break;
                        }
                        case MotionEvent.ACTION_UP: {
                            activity.cardView.setVisibility(View.GONE);
                        }
                    }
                    return true;
                }
            });

            activity.scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {

                WoWCharacterFragment activity = activityReference.get();

                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if(oldScrollY != scrollY){
                        activity.cardView.setVisibility(View.GONE);
                    }
                }
            });
            activity.index++;
        }

        private void setTalentInformation(WoWCharacterFragment activity) {
            for(int i = 0; i < activity.characterInfo.getTalents().size(); i++) {

                if(activity.characterInfo.getTalents().get(i).getSelected()) {
                    activity.talents.addAll(activity.characterInfo.getTalents().get(i).getTalents());
                    activity.spec.setText(String.format("Specialization: %s", activity.characterInfo.getTalents().get(i).getSpec().getName()));
                }
            }

            try {
                activity.specs.addTab(activity.specs.newTab());
                activity.specs.getTabAt(3).setText(activity.characterInfo.getTalents().get(3).getSpec().getName());
            }catch (NullPointerException e){
                Log.e("Error", e.toString());
                activity.specs.removeTab(activity.specs.getTabAt(3));
            }

            for(int i = 0; i < 3; i++) {
                TabLayout.Tab tab = activity.specs.getTabAt(i);
                try {
                    assert tab != null;
                    tab.setText(activity.characterInfo.getTalents().get(i).getSpec().getName());
                }catch (NullPointerException e) {
                    Log.e("Error", e.toString());
                    tab.setText("Unavailable");
                }
            }

            activity.talentsTierContainer = new ArrayList<>(Arrays.asList(activity.fifteen, activity.thirty, activity.forty_five,
                    activity.sixty, activity.seventy_five, activity.ninety, activity.hundred));
            activity.talentsContainer = new ArrayList<>(Arrays.asList(activity.fifteenTalent, activity.thirtyTalent, activity.forty_fiveTalent,
                    activity.sixtyTalent, activity.seventy_fiveTalent, activity.ninetyTalent, activity.hundredTalent));
            activity.talentsTier = new ArrayList<>(Arrays.asList("15", "30", "45", "60", "75", "90", "100"));

            try{
                sortTalents(activity);

                if(activity.talents.size() > 0) {
                    for(int i = 0; i < activity.talents.size(); i++){
                        activity.noTalent.setVisibility(View.INVISIBLE);
                        activity.talentsTierContainer.get(i).setGravity(Gravity.CENTER);
                        activity.talentsTierContainer.get(i).setText(activity.talentsTier.get(i));
                        activity.talentsContainer.get(i).setText(activity.talents.get(i).getSpell().getName());
                    }
                }else {
                    removeTalents(activity);
                    activity.noTalent.setVisibility(View.VISIBLE);

                }
            }catch (NullPointerException e){
                Log.e("Error", e.toString());
                activity.noTalent.setVisibility(View.VISIBLE);
            }

            activity.specs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                WoWCharacterFragment activity = activityReference.get();

                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    switch(tab.getPosition()){
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
                    try{
                        activity.talents.clear();
                        activity.talents.addAll(activity.characterInfo.getTalents().get(position).getTalents());
                        if(activity.talents.size() == 0){
                            activity.noTalent.setVisibility(View.VISIBLE);
                            removeTalents(activity);
                        }else {
                            activity.noTalent.setVisibility(View.INVISIBLE);
                        }
                        sortTalents(activity);
                        for(int i = 0; i < activity.talents.size(); i++){
                            activity.talentsTierContainer.get(i).setGravity(Gravity.CENTER);
                            activity.talentsTierContainer.get(i).setText(activity.talentsTier.get(i));
                            activity.talentsContainer.get(i).setText(activity.talents.get(i).getSpell().getName());
                        }
                    }catch (NullPointerException e){
                        removeTalents(activity);
                        activity.noTalent.setVisibility(View.VISIBLE);
                        Log.e("Error", e.toString());
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
    }

    private static void setCharacterItemsInformation(WoWCharacterFragment activity, ImageView imageView) throws JSONException {
        String description = "";
        String nameDescription = "";
        String trigger = "";
        String damageInfo = "";
        String durability = "Durability " + activity.itemInformations.get(activity.index).getMaxDurability() + "/" + activity.itemInformations.get(activity.index).getMaxDurability();
        String requiredLevel = "Requires Level " + activity.itemInformations.get(activity.index).getRequiredLevel();
        Drawable backgroundStroke = activity.itemColor(activity.itemsInfoList.get(activity.index), new GradientDrawable());
        String itemName = activity.itemsInfoList.get(activity.index).getName();
        String itemLvl = "<font color=#edc201>Items Level " + activity.itemsInfoList.get(activity.index).getItemLevel().toString() + "</font>";
        String armor = activity.itemsInfoList.get(activity.index).getArmor().toString();
        if(activity.itemInformations.get(activity.index).getName() != null){
            description = "<font color=#edc201>" + activity.itemInformations.get(activity.index).getDescription() + "</font>";
            nameDescription = "<font color=#00cc00>" + activity.itemInformations.get(activity.index).getNameDescription() + "</font><br>";
            trigger = activity.getTrigger(activity.index);
        }

        String itemSlot = activity.getResources().getResourceEntryName(imageView.getId());
        itemSlot = activity.formatItemSlotName(itemSlot);


        damageInfo = activity.getDamageInformation(itemSlot, damageInfo);

        String tempStats = "";
        List<Stat> statList = activity.itemsInfoList.get(activity.index).getStats();
        activity.sortStats(statList);
        tempStats = activity.getStatsFormatted(tempStats, statList);

        activity.nameList.put(activity.index, itemName);

        if(Integer.valueOf(armor) > 0){
            activity.stats.put(activity.index, String.format("%s<br>%s%s<br>%s Armor<br>%s",itemLvl, itemSlot, damageInfo, armor, tempStats));
        }else {
            activity.stats.put(activity.index, String.format("%s<br>%s%s<br>%s",itemLvl, itemSlot, damageInfo,tempStats));
        }

        if(itemSlot.equals("Head") && activity.azeriteSpellsHead.size() > 0){
            activity.stats.put(activity.index, activity.stats.get(activity.index) + String.format("%s", activity.getAzeritePowers(activity.azeriteSpellsHead)));
        }

        if(itemSlot.equals("Shoulder") && activity.azeriteSpellsShoulder.size() > 0){
            activity.stats.put(activity.index, activity.stats.get(activity.index) + String.format("%s", activity.getAzeritePowers(activity.azeriteSpellsShoulder)));
        }

        if(itemSlot.equals("Chest") && activity.azeriteSpellsChest.size() > 0){
            activity.stats.put(activity.index, activity.stats.get(activity.index) + String.format("%s", activity.getAzeritePowers(activity.azeriteSpellsChest)));
        }

        if(!nameDescription.equals("<font color=#00cc00></font><br>")){
            activity.stats.put(activity.index, nameDescription + activity.stats.get(activity.index));
        }

        if (!trigger.equals("")){
            activity.stats.put(activity.index, activity.stats.get(activity.index) + String.format("<br>%s<br>", trigger));
        }

        if(!activity.itemInformations.get(activity.index).getDescription().equals("")){
            activity.stats.put(activity.index, activity.stats.get(activity.index) + String.format("<br>%s<br>", description));
        }
        if(activity.itemInformations.get(activity.index).getMaxDurability() != 0){
            activity.stats.put(activity.index, activity.stats.get(activity.index) + String.format("<br>%s<br>%s", durability, requiredLevel));
        }else{
            activity.stats.put(activity.index, activity.stats.get(activity.index) + String.format("<br>%s", requiredLevel));
        }


        imageView.setBackground(backgroundStroke);
    }

    private static void setCharacterInformationTextviews(WoWCharacterFragment activity) throws JSONException {
        activity.background.setImageDrawable(activity.backgroundMain);
        activity.characterName.setText(activity.characterInfo.getName());
        activity.itemLVL.setText(String.format("Items Level: %s", activity.itemObject.get("averageItemLevel")));

        activity.health.setText(String.format("Health: %s", activity.statsObject.get("health")));
        activity.power.setText(String.format("%s: %s", activity.formatItemSlotName(activity.statsObject.get("powerType").toString().replace("-", " ")), activity.statsObject.get("power")));

        activity.strength.setText(String.format("Strength: %s", activity.statsObject.get("str")));
        activity.agility.setText(String.format("Agility: %s", activity.statsObject.get("agi")));
        activity.intellect.setText(String.format("Intellect: %s", activity.statsObject.get("int")));
        activity.stamina.setText(String.format("Stamina: %s", activity.statsObject.get("sta")));

        activity.crit.setText(String.format(Locale.ENGLISH, "Critical Strike: %.2f%%", (double) activity.statsObject.get("crit")));
        activity.haste.setText(String.format(Locale.ENGLISH, "Haste: %.2f%%", (double) activity.statsObject.get("haste")));
        activity.mastery.setText(String.format(Locale.ENGLISH, "Mastery: %.2f%%", (double) activity.statsObject.get("mastery")));
        activity.versatility.setText(String.format(Locale.ENGLISH, "Versatility: %.2f%%", (double) activity.statsObject.get("versatilityDamageDoneBonus")));
    }

    private static void removeTalents(WoWCharacterFragment activity) {
        for (int i = 0; i < activity.talentsContainer.size(); i++) {
            activity.talentsTierContainer.get(i).setText("");
            activity.talentsContainer.get(i).setText("");
        }
    }

    private static void sortTalents(WoWCharacterFragment activity) {
        activity.talents.sort(new Comparator<Talents>() {
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
