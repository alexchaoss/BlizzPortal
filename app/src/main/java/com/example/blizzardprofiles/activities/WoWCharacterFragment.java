package com.example.blizzardprofiles.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.example.blizzardprofiles.R;
import com.example.blizzardprofiles.URLConstants;
import com.example.blizzardprofiles.connection.ConnectionService;
import com.example.blizzardprofiles.connection.ImageDownload;
import com.example.blizzardprofiles.warcraft.Gear;
import com.example.blizzardprofiles.warcraft.Item;
import com.example.blizzardprofiles.warcraft.Stat;
import com.example.blizzardprofiles.warcraft.StatsEnum;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class WoWCharacterFragment extends Fragment {

    private String characterRealm;
    private String characterClicked;
    private String urlMain;

    private SharedPreferences prefs;
    private BnOAuth2Helper bnOAuth2Helper;
    private BnOAuth2Params bnOAuth2Params;

    private JSONObject characterInformation;
    private JSONObject bonusID;

    private Gear equipment;

    private TextView characterName;
    private TextView itemLVL;
    private ImageView background;
    private LinearLayout linearLayoutItemStats;
    private CardView cardView;
    private TextView statsView;
    private TextView nameView;

    private ArrayList<Drawable> icons = new ArrayList<>();
    private ArrayList<ImageView> gear = new ArrayList<>();
    private ArrayList<Item> itemsInfoList = new ArrayList<>();
    private ArrayList<JSONObject> bonusIDList = new ArrayList<>();

    private Map<Integer, String> stats = new HashMap<>();
    private Map<Integer, String> nameList = new HashMap<>();

    private String itemName;
    private String itemLvl;
    private String armor;

    private int index = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.character_fragment, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        characterRealm = bundle.getString("realm");
        characterClicked = bundle.getString("name");
        urlMain = bundle.getString("url");
        characterName = view.findViewById(R.id.character_name);
        itemLVL = view.findViewById(R.id.item_lvl);
        background = view.findViewById(R.id.background);
        cardView = view.findViewById(R.id.item_stats);
        linearLayoutItemStats = new LinearLayout(view.getContext());
        linearLayoutItemStats.setOrientation(LinearLayout.VERTICAL);
        cardView.addView(linearLayoutItemStats);
        statsView = new TextView(view.getContext());
        nameView = new TextView(view.getContext());
        linearLayoutItemStats.addView(nameView);
        linearLayoutItemStats.addView(statsView);


        prefs = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        bnOAuth2Params = this.getActivity().getIntent().getExtras().getParcelable(BnConstants.BUNDLE_BNPARAMS);
        bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

        Drawable backgroundMain = null;

        try {
            characterInformation = new JSONObject(ConnectionService.getStringJSONFromRequest(URLConstants.getBaseURLforAPI(),
                    swapRealmCharacterFromURL(URLConstants.WOW_ITEM_QUERY), bnOAuth2Helper.getAccessToken()));

        } catch (Exception e) {
            Log.e("Error", e.toString());
        }

        JSONObject itemObject = null;
        JSONObject statsObject = null;

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

        TextView strength = view.findViewById(R.id.strength);
        TextView agility = view.findViewById(R.id.agility);
        TextView intellect = view.findViewById(R.id.intellect);
        TextView stamina = view.findViewById(R.id.stamina);

        TextView crit = view.findViewById(R.id.crit);
        TextView haste = view.findViewById(R.id.haste);
        TextView mastery = view.findViewById(R.id.mastery);
        TextView versatility = view.findViewById(R.id.versatility);

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

        try {
            if (characterInformation.isNull("items")) {
                Log.i("test", "test");
                characterName.setText(characterInformation.get("reason").toString());
                background.setBackgroundColor(getResources().getColor(R.color.wowBackgroundColor));
                itemLVL.setText("");
            } else {
                itemObject = characterInformation.getJSONObject("items");
                statsObject = characterInformation.getJSONObject("stats");
                backgroundMain = new ImageDownload(urlMain, URLConstants.getRenderZoneURL(), view.getContext(), characterInformation).getImageFromURL().get(0);
                background.setImageDrawable(backgroundMain);
                characterName.setText(characterInformation.get("name").toString());
                itemLVL.setText(String.format("Item Level: %s", itemObject.get("averageItemLevel")));
            }

            strength.setText(String.format("Strength: %s", statsObject.get("str")));
            agility.setText(String.format("Agility: %s", statsObject.get("agi")));
            intellect.setText(String.format("Intellect: %s", statsObject.get("int")));
            stamina.setText(String.format("Stamina: %s", statsObject.get("sta")));

            crit.setText(String.format("Critical Strike: %.2f%%", (double)statsObject.get("crit")));
            haste.setText(String.format("Haste: %.2f%%", (double) statsObject.get("haste")));
            mastery.setText(String.format("Mastery: %.2f%%", (double) statsObject.get("mastery")));
            versatility.setText(String.format("Versatility: %.2f%%", (double) statsObject.get("versatilityDamageDoneBonus")));

            equipment = new Gear(itemObject);
            getIcons(view);

            Drawable backgroundStroke;

            for(final ImageView imageView: gear){

                if(itemsInfoList.get(index).getName() != null){
                    backgroundStroke = itemColor(itemsInfoList.get(index), new GradientDrawable());
                    itemName = itemsInfoList.get(index).getName();
                    itemLvl = itemsInfoList.get(index).getItemLevel().toString();
                    armor = itemsInfoList.get(index).getArmor().toString();
                    String tempStats = "";
                    for (Stat stat : itemsInfoList.get(index).getStats()) {
                        tempStats += "+ " + StatsEnum.fromOrdinal(stat.getStat()) + " " + stat.getAmount() + "\n";
                    }
                    nameList.put(index, itemName);
                    if(Integer.valueOf(armor) > 0){
                        stats.put(index, "Item Level " + itemLvl + "\n"+ armor + " Armor\n" + tempStats);
                    }else {
                        stats.put(index, "Item Level " + itemLvl + "\n"+ tempStats);
                    }

                    imageView.setBackground(backgroundStroke);
                }

                imageView.setId(index);
                imageView.setImageDrawable(icons.get(index));
                imageView.setPadding(3,2,2,3);
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
                                    statsView.setText(stats.get(imageView.getId()));
                                    statsView.setTextColor(Color.WHITE);
                                    cardView.setContentPadding(10,10,10,10);
                                    cardView.setBackground(imageView.getBackground());
                                    cardView.setVisibility(View.VISIBLE);
                                }
                                break;
                            }
                            case MotionEvent.ACTION_UP:
                                cardView.setVisibility(View.GONE);
                        }
                        return true;
                    }
                });
                index++;
            }

            for(int i = 0; i < gear.size();i++){
                bonusID = new JSONObject(ConnectionService.getStringJSONFromRequest(URLConstants.getBaseURLforAPI(),
                        swapItemIDBonusID(URLConstants.BONUSID_QUERY, i), bnOAuth2Helper.getAccessToken()));
                bonusIDList.add(bonusID);
            }

        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    private String swapItemIDBonusID(String bonusidQuery, int i) {
        String bonusIds = "";
        for(int j = 0; j < itemsInfoList.get(i).getBonusLists().size(); j++){
            bonusIds += itemsInfoList.get(i).getBonusLists().get(i) + ",";
        }
        return bonusidQuery.replace("id?b1=bonusList", itemsInfoList.get(i).getId().toString() + "?b1=" + bonusIds.substring(0,bonusIds.length()-1));
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

    private String swapRealmCharacterFromURL(String url) {
        return url.replace("realm/character", characterRealm + "/" + characterClicked);
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
            return Color.parseColor("#1fd309");
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
}
