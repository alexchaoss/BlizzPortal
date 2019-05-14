package com.example.blizzardprofiles.activities;

import android.content.SharedPreferences;
import android.graphics.Color;
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

    private JSONObject characterItems;
    private Gear equipment;

    private TextView characterName;
    private ImageView background;
    private LinearLayout linearLayout;
    private CardView cardView;
    private TextView statsView;
    private TextView nameView;

    private ArrayList<Drawable> icons = new ArrayList<>();
    private ArrayList<ImageView> gear = new ArrayList<>();
    private ArrayList<Item> itemsInfoList = new ArrayList<>();

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
        background = view.findViewById(R.id.background);
        cardView = view.findViewById(R.id.item_stats);
        linearLayout = new LinearLayout(view.getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        cardView.addView(linearLayout);
        statsView = new TextView(view.getContext());
        nameView = new TextView(view.getContext());
        linearLayout.addView(nameView);
        linearLayout.addView(statsView);

        prefs = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        bnOAuth2Params = this.getActivity().getIntent().getExtras().getParcelable(BnConstants.BUNDLE_BNPARAMS);
        bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

        Drawable backgroundMain = new ImageDownload(urlMain, URLConstants.getRenderZoneURL(), view.getContext()).getImageFromURL().get(0);

        try {
            characterItems = new JSONObject(ConnectionService.getStringJSONFromRequest(URLConstants.getBaseURLforAPI(),
                    swapRealmCharacterFromURL(), bnOAuth2Helper.getAccessToken()));
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }

        JSONObject itemObject;
        try {
            String reason = "";
            if (characterItems.isNull("name")) {
                characterName.setText(characterItems.get("reason").toString());
                reason = characterItems.get("reason").toString();
            } else {
                characterName.setText(characterItems.get("name").toString());
            }

            if(!reason.equals("Character not found.")){
                background.setImageDrawable(backgroundMain);
            }

            itemObject = characterItems.getJSONObject("items");
            equipment = new Gear(itemObject);
            getIcons(view);

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

            Drawable backgroundStroke;

            for(final ImageView imageView: gear){

                if(itemsInfoList.get(index).getName() != null){
                    backgroundStroke = itemColor(itemsInfoList.get(index), new GradientDrawable());
                    itemName = itemsInfoList.get(index).getName();
                    itemLvl = itemsInfoList.get(index).getItemLevel().toString();
                    armor = itemsInfoList.get(index).getArmor().toString();
                    String tempStats = "";
                    for (Stat stat : itemsInfoList.get(index).getStats()) {
                        tempStats += "+" + StatsEnum.fromOrdinal(stat.getStat()) + " " + stat.getAmount() + "\n";
                    }
                    nameList.put(index, itemName);
                    stats.put(index, "Item Level " + itemLvl + "\n"+ armor + "Armor\n" + tempStats);
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
                                cardView.setContentPadding(10,10,10,10);
                                cardView.setBackground(imageView.getBackground());
                                nameView.setText(nameList.get(imageView.getId()));
                                nameView.setTextColor(getItemColor(itemsInfoList.get(imageView.getId())));
                                statsView.setPadding(10,10,10,10);
                                statsView.setText(stats.get(imageView.getId()));
                                statsView.setTextColor(Color.WHITE);
                                if(stats.get(imageView.getId()) != null){
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
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    private void getIcons(View view) {
        Gson gson = new GsonBuilder().create();

        if (equipment.getHead() == null) {
            itemsInfoList.add(new Item());
            Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_head);
            icons.add(noItem);
        } else {
            Item head = gson.fromJson(equipment.getHead().toString(), Item.class);
            itemsInfoList.add(head);
            Drawable item = new ImageDownload(head.getIcon()+ ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
            icons.add(item);
        }

        if (equipment.getNeck() == null) {
            itemsInfoList.add(new Item());
            Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_neck);
            icons.add(noItem);
        } else {
            Item neck = gson.fromJson(equipment.getNeck().toString(), Item.class);
            itemsInfoList.add(neck);
            Drawable item = new ImageDownload(neck.getIcon() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
            icons.add(item);
        }

        if (equipment.getShoulder() == null) {
            itemsInfoList.add(new Item());
            Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_shoulders);
            icons.add(noItem);
        } else {
            Item shoulder = gson.fromJson(equipment.getShoulder().toString(), Item.class);
            itemsInfoList.add(shoulder);
            Drawable item = new ImageDownload(shoulder.getIcon() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
            icons.add(item);
        }

        if (equipment.getBack() == null) {
            itemsInfoList.add(new Item());
            Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_back);
            icons.add(noItem);
        } else {
            Item back = gson.fromJson(equipment.getBack().toString(), Item.class);
            itemsInfoList.add(back);
            Drawable item = new ImageDownload(back.getIcon() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
            icons.add(item);
        }

        if (equipment.getChest() == null) {
            itemsInfoList.add(new Item());
            Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_chest);
            icons.add(noItem);
        } else {
            Item chest = gson.fromJson(equipment.getChest().toString(), Item.class);
            itemsInfoList.add(chest);
            Drawable item = new ImageDownload(chest.getIcon() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
            icons.add(item);
        }

        if (equipment.getShirt() == null) {
            itemsInfoList.add(new Item());
            Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_shirt);
            icons.add(noItem);

        } else {
            Log.i("test","test");
            Item shirt = gson.fromJson(equipment.getShirt().toString(), Item.class);
            itemsInfoList.add(shirt);
            Drawable item = new ImageDownload(shirt.getIcon() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
            icons.add(item);
        }

        if (equipment.getTabard() == null) {
            itemsInfoList.add(new Item());
            Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_tabard);
            icons.add(noItem);
        } else {
            Item tabard = gson.fromJson(equipment.getTabard().toString(), Item.class);
            itemsInfoList.add(tabard);
            Drawable item = new ImageDownload(tabard.getIcon() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
            icons.add(item);
        }

        if (equipment.getWrist() == null) {
            itemsInfoList.add(new Item());
            Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_wrist);
            icons.add(noItem);
        } else {
            Item wrist = gson.fromJson(equipment.getWrist().toString(), Item.class);
            itemsInfoList.add(wrist);
            Drawable item = new ImageDownload(wrist.getIcon() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
            icons.add(item);
        }

        if (equipment.getHands() == null) {
            itemsInfoList.add(new Item());
            Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_hands);
            icons.add(noItem);
        } else {
            Item hands = gson.fromJson(equipment.getHands().toString(), Item.class);
            itemsInfoList.add(hands);
            Drawable item = new ImageDownload(hands.getIcon() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
            icons.add(item);
        }

        if (equipment.getWaist() == null) {
            itemsInfoList.add(new Item());
            Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_waist);
            icons.add(noItem);
        } else {
            Item waist = gson.fromJson(equipment.getWaist().toString(), Item.class);
            itemsInfoList.add(waist);
            Drawable item = new ImageDownload(waist.getIcon() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
            icons.add(item);
        }

        if (equipment.getLegs() == null) {
            itemsInfoList.add(new Item());
            Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_legs);
            icons.add(noItem);
        } else {
            Item legs = gson.fromJson(equipment.getLegs().toString(), Item.class);
            itemsInfoList.add(legs);
            Drawable item = new ImageDownload(legs.getIcon() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
            icons.add(item);
        }

        if (equipment.getFeet() == null) {
            itemsInfoList.add(new Item());
            Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_feet);
            icons.add(noItem);
        } else {
            Item feet = gson.fromJson(equipment.getFeet().toString(), Item.class);
            itemsInfoList.add(feet);
            Drawable item = new ImageDownload(feet.getIcon() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
            icons.add(item);
        }

        if (equipment.getFinger1() == null) {
            itemsInfoList.add(new Item());
            Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_ring);
            icons.add(noItem);
        } else {
            Item finger1 = gson.fromJson(equipment.getFinger1().toString(), Item.class);
            itemsInfoList.add(finger1);
            Drawable item = new ImageDownload(finger1.getIcon() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
            icons.add(item);
        }

        if (equipment.getFinger2() == null) {
            itemsInfoList.add(new Item());
            Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_ring);
            icons.add(noItem);
        } else {
            Item finger2 = gson.fromJson(equipment.getFinger2().toString(), Item.class);
            itemsInfoList.add(finger2);
            Drawable item = new ImageDownload(finger2.getIcon() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
            icons.add(item);
        }

        if (equipment.getTrinket1() == null) {
            itemsInfoList.add(new Item());
            Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_trinket);
            icons.add(noItem);
        } else {
            Item trinket1 = gson.fromJson(equipment.getTrinket1().toString(), Item.class);
            itemsInfoList.add(trinket1);
            Drawable item = new ImageDownload(trinket1.getIcon() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
            icons.add(item);
        }

        if (equipment.getTrinket2() == null) {
            itemsInfoList.add(new Item());
            Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_trinket);
            icons.add(noItem);
        } else {
            Item trinket2 = gson.fromJson(equipment.getTrinket2().toString(), Item.class);
            itemsInfoList.add(trinket2);
            Drawable item = new ImageDownload(trinket2.getIcon() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
            icons.add(item);
        }

        if (equipment.getMainHand() == null) {
            itemsInfoList.add(new Item());
            Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_main_hand);
            icons.add(noItem);
        } else {
            Item mainHand = gson.fromJson(equipment.getMainHand().toString(), Item.class);
            itemsInfoList.add(mainHand);
            Drawable item = new ImageDownload(mainHand.getIcon() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
            icons.add(item);
        }

        if (equipment.getOffHand() == null) {
            itemsInfoList.add(new Item());
            Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_shield);
            icons.add(noItem);
        } else {

            Item offHand = gson.fromJson(equipment.getOffHand().toString(), Item.class);
            itemsInfoList.add(offHand);
            Drawable item = new ImageDownload(offHand.getIcon() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
            icons.add(item);
        }
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
