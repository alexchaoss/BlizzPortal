package com.BlizzardArmory.UI.UI_diablo;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.BlizzardArmory.R;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.connection.ConnectionService;
import com.BlizzardArmory.diablo.Character.CharacterInformation;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class D3CharacterFragment extends Fragment {

    private int id;

    private static CharacterInformation characterInformation;
    private JSONObject characterInfo;
    private Items itemsInformation;

    private int imageIndex= 0;

    private RelativeLayout loadingCircle;

    private List<ImageView> imageViewItem = new ArrayList<>();
    private ImageView shoulders;
    private ImageView hands;
    private ImageView ring1;
    private ImageView main_hand;
    private ImageView head;
    private ImageView chest;
    private ImageView belt;
    private ImageView legs;
    private ImageView boots;
    private ImageView amulet;
    private ImageView bracers;
    private ImageView ring2;
    private ImageView off_hand;
    ArrayList<String> itemIconURL = new ArrayList<>();

    private  ImageView paperdoll;
    private TextView name;
    private TextView lvl_class;

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

    private RequestQueue requestQueue;
    private RequestQueue requestQueueImage;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.d3_character_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        assert bundle != null;
        id = bundle.getInt("id");
        shoulders = view.findViewById(R.id.shoulder);
        hands = view.findViewById(R.id.gloves);
        ring1 = view.findViewById(R.id.ring1);
        main_hand = view.findViewById(R.id.main_hand);
        head = view.findViewById(R.id.head);
        chest = view.findViewById(R.id.chest);
        belt = view.findViewById(R.id.belt);
        legs = view.findViewById(R.id.legs);
        boots = view.findViewById(R.id.boots);
        amulet = view.findViewById(R.id.amulet);
        bracers = view.findViewById(R.id.bracers);
        ring2 = view.findViewById(R.id.ring2);
        off_hand = view.findViewById(R.id.off_hand);
        loadingCircle = view.findViewById(R.id.loadingCircle);
        paperdoll = view.findViewById(R.id.paperdoll);
        name = view.findViewById(R.id.character_name);
        lvl_class = view.findViewById(R.id.level_class);
        top_stat = view.findViewById(R.id.top_stat);
        vitality = view.findViewById(R.id.vitality);
        crit_chance = view.findViewById(R.id.crit);
        crit_damage = view.findViewById(R.id.crit_chance);
        attack_speed = view.findViewById(R.id.attack_speed);
        area_damage = view.findViewById(R.id.area_damage);
        cooldown_reduction = view.findViewById(R.id.cooldown_reduction);

        addImageViewItemsToList();

        loadingCircle.setVisibility(View.VISIBLE);
        Objects.requireNonNull(D3CharacterFragment.this.getActivity()).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        long startTime = System.nanoTime();
        final Gson gson = new GsonBuilder().create();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        BnOAuth2Params bnOAuth2Params = Objects.requireNonNull(Objects.requireNonNull(getActivity()).getIntent().getExtras()).getParcelable(BnConstants.BUNDLE_BNPARAMS);
        assert bnOAuth2Params != null;
        BnOAuth2Helper bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

        Cache cache = new DiskBasedCache(getContext().getCacheDir(), 1024 * 1024 * 5); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueueImage = new RequestQueue(cache, network, 1);
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
                            setPaperdoll();
                            setName();

                            String topStatString = "+" + characterInformation.getStats().getStrength() + " Strength";
                            if(characterInformation.getStats().getStrength() < characterInformation.getStats().getIntelligence()){
                                topStatString = "+" + characterInformation.getStats().getIntelligence() + " Intelligence";
                            }else if (characterInformation.getStats().getIntelligence() < characterInformation.getStats().getDexterity()){
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
                            setItemBackgroundColor();
                            getItemIconURL(itemIconURL);
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

    private void setName() {
        String levelClass = "<font color=#d4a94e>" + characterInformation.getLevel() + "</font>" + "<font color=#555da5> (" + characterInformation.getParagonLevel()
                + ")</font> <font color=#d4a94e>" + characterInformation.getClass_();

        if (Build.VERSION.SDK_INT >= 24) {
            lvl_class.setText(Html.fromHtml(levelClass, Html.FROM_HTML_MODE_LEGACY));
        } else {
            lvl_class.setText(Html.fromHtml(levelClass));
        }
        if(characterInformation.getName().length() > 7 && characterInformation.getName().length() < 10){
            name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
        }else if(characterInformation.getName().length() > 9){
            name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            name.setPadding(0,10,0,0);
        }
        name.setText(characterInformation.getName());
    }

    private void setPaperdoll() {
        switch (characterInformation.getClass_()) {
            case "barbarian":
                if (characterInformation.getGender() == 0) {
                    paperdoll.setImageResource(R.drawable.barbarian_male_background);
                } else if (characterInformation.getGender() == 1) {
                    paperdoll.setImageResource(R.drawable.barbarian_female_background);
                }
                break;
            case "wizard":
                if (characterInformation.getGender() == 0) {
                    paperdoll.setImageResource(R.drawable.wizard_male_background);
                } else if (characterInformation.getGender() == 1) {
                    paperdoll.setImageResource(R.drawable.wizard_female_background);
                }
                break;
            case "demon-hunter":
                if (characterInformation.getGender() == 0) {
                    paperdoll.setImageResource(R.drawable.demon_hunter_male_background);
                } else if (characterInformation.getGender() == 1) {
                    paperdoll.setImageResource(R.drawable.demon_hunter_female_background);
                }
                break;
            case "witch-doctor":
                if (characterInformation.getGender() == 0) {
                    paperdoll.setImageResource(R.drawable.witch_doctor_male_background);
                } else if (characterInformation.getGender() == 1) {
                    paperdoll.setImageResource(R.drawable.witch_doctor_female_background);
                }
                break;
            case "necromancer":
                if (characterInformation.getGender() == 0) {
                    paperdoll.setImageResource(R.drawable.barbarian_male_background);
                } else if (characterInformation.getGender() == 1) {
                    paperdoll.setImageResource(R.drawable.barbarian_female_background);
                }
                break;
            case "monk":
                if (characterInformation.getGender() == 0) {
                    paperdoll.setImageResource(R.drawable.monk_male_background);
                } else if (characterInformation.getGender() == 1) {
                    paperdoll.setImageResource(R.drawable.monk_female_background);
                }
                break;
            case "crusader":
                if (characterInformation.getGender() == 0) {
                    paperdoll.setImageResource(R.drawable.barbarian_male_background);
                } else if (characterInformation.getGender() == 1) {
                    paperdoll.setImageResource(R.drawable.barbarian_female_background);
                }
                break;
        }
    }

    private void getItemIcons() {
        for(int i = 0; i < itemIconURL.size() ; i++){
            ImageRequest imageRequest = new ImageRequest(itemIconURL.get(i), new Response.Listener<Bitmap>(){
                @Override
                public void onResponse(Bitmap bitmap) {
                    Drawable item = new BitmapDrawable(getResources(), bitmap);
                    imageViewItem.get(imageIndex).setImageDrawable(item);
                    imageIndex++;

                    if(imageIndex == itemIconURL.size()){
                        Objects.requireNonNull(getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        loadingCircle.setVisibility(View.GONE);
                    }
                }
            },0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            try {
                                if (!ConnectionService.isConnected()){
                                    ConnectionService.showNoConnectionMessage(getActivity().getApplicationContext(),D3CharacterFragment.this);
                                }
                            }catch (Exception e){
                            }
                            imageIndex++;
                            if(imageIndex == itemIconURL.size()){
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
        imageViewItem.add(shoulders);
        imageViewItem.add(hands);
        imageViewItem.add(ring1);
        imageViewItem.add(main_hand);
        imageViewItem.add(head);
        imageViewItem.add(chest);
        imageViewItem.add(belt);
        imageViewItem.add(legs);
        imageViewItem.add(boots);
        imageViewItem.add(amulet);
        imageViewItem.add(bracers);
        imageViewItem.add(ring2);
        imageViewItem.add(off_hand);
    }

    private void setItemBackgroundColor() {
        try {
            selectColor(itemsInformation.getShoulders().getDisplayColor(), shoulders);
        } catch (Exception e) {
            e.printStackTrace();
            selectColor("", shoulders);
        }
        try {
            selectColor(itemsInformation.getHands().getDisplayColor(), hands);
        } catch (Exception e) {
            e.printStackTrace();
            selectColor("", hands);
        }
        try {
            selectColor(itemsInformation.getLeftFinger().getDisplayColor(), ring1);
        } catch (Exception e) {
            e.printStackTrace();
            selectColor("", ring1);
        }
        try {
            selectColor(itemsInformation.getMainHand().getDisplayColor(), main_hand);
        } catch (Exception e) {
            e.printStackTrace();
            selectColor("", main_hand);
        }
        try {
            selectColor(itemsInformation.getHead().getDisplayColor(), head);
        } catch (Exception e) {
            e.printStackTrace();
            selectColor("", head);
        }
        try {
            selectColor(itemsInformation.getTorso().getDisplayColor(), chest);
        } catch (Exception e) {
            e.printStackTrace();
            selectColor("", chest);
        }
        try {
            selectColor(itemsInformation.getWaist().getDisplayColor(), belt);
        } catch (Exception e) {
            e.printStackTrace();
            selectColor("", belt);
        }
        try {
            selectColor(itemsInformation.getLegs().getDisplayColor(), legs);
        } catch (Exception e) {
            e.printStackTrace();
            selectColor("", legs);
        }
        try {
            selectColor(itemsInformation.getFeet().getDisplayColor(), boots);
        } catch (Exception e) {
            e.printStackTrace();
            selectColor("", boots);
        }
        try {
            selectColor(itemsInformation.getNeck().getDisplayColor(), amulet);
        } catch (Exception e) {
            e.printStackTrace();
            selectColor("", amulet);
        }
        try {
            selectColor(itemsInformation.getBracers().getDisplayColor(), bracers);
        } catch (Exception e) {
            e.printStackTrace();
            selectColor("", bracers);
        }
        try {
            selectColor(itemsInformation.getRightFinger().getDisplayColor(), ring2);
        } catch (Exception e) {
            e.printStackTrace();
            selectColor("", ring2);
        }
        try {
            selectColor(itemsInformation.getOffHand().getDisplayColor(), off_hand);
        } catch (Exception e) {
            e.printStackTrace();
            selectColor("", off_hand);
        }
    }

    private void getAttackStats(){
        try {
            Log.i("TEST", Arrays.toString(itemsInformation.getShoulders().getAttributes().getPrimary().toArray()));
            getAttackSpeedCritDamageCritChance(itemsInformation.getShoulders().getAttributes().getPrimary());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Log.i("TEST", Arrays.toString(itemsInformation.getHands().getAttributes().getPrimary().toArray()));
            getAttackSpeedCritDamageCritChance(itemsInformation.getHands().getAttributes().getPrimary());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Log.i("TEST", Arrays.toString(itemsInformation.getLeftFinger().getAttributes().getPrimary().toArray()));
            getAttackSpeedCritDamageCritChance(itemsInformation.getLeftFinger().getAttributes().getPrimary());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Log.i("TEST", Arrays.toString(itemsInformation.getMainHand().getAttributes().getPrimary().toArray()));
            getAttackSpeedCritDamageCritChance(itemsInformation.getMainHand().getAttributes().getPrimary());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Log.i("TEST", Arrays.toString(itemsInformation.getHead().getAttributes().getPrimary().toArray()));
            getAttackSpeedCritDamageCritChance(itemsInformation.getHead().getAttributes().getPrimary());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Log.i("TEST", Arrays.toString(itemsInformation.getTorso().getAttributes().getPrimary().toArray()));
            getAttackSpeedCritDamageCritChance(itemsInformation.getTorso().getAttributes().getPrimary());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Log.i("TEST", Arrays.toString(itemsInformation.getWaist().getAttributes().getPrimary().toArray()));
            getAttackSpeedCritDamageCritChance(itemsInformation.getWaist().getAttributes().getPrimary());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Log.i("TEST", Arrays.toString(itemsInformation.getLegs().getAttributes().getPrimary().toArray()));
            getAttackSpeedCritDamageCritChance(itemsInformation.getLegs().getAttributes().getPrimary());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Log.i("TEST", Arrays.toString(itemsInformation.getFeet().getAttributes().getPrimary().toArray()));
            getAttackSpeedCritDamageCritChance(itemsInformation.getFeet().getAttributes().getPrimary());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Log.i("TEST", Arrays.toString(itemsInformation.getNeck().getAttributes().getPrimary().toArray()));
            getAttackSpeedCritDamageCritChance(itemsInformation.getNeck().getAttributes().getPrimary());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Log.i("TEST", Arrays.toString(itemsInformation.getBracers().getAttributes().getPrimary().toArray()));
            getAttackSpeedCritDamageCritChance(itemsInformation.getBracers().getAttributes().getPrimary());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Log.i("TEST", Arrays.toString(itemsInformation.getRightFinger().getAttributes().getPrimary().toArray()));
            getAttackSpeedCritDamageCritChance(itemsInformation.getRightFinger().getAttributes().getPrimary());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Log.i("TEST", Arrays.toString(itemsInformation.getOffHand().getAttributes().getPrimary().toArray()));
            getAttackSpeedCritDamageCritChance(itemsInformation.getOffHand().getAttributes().getPrimary());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAttackSpeedCritDamageCritChance(List<String> attributes) {
        for(int i = 0; i < attributes.size(); i++){
            if(attributes.get(i).toLowerCase().contains("Attack Speed".toLowerCase())){
                String temp = attributes.get(i);
                temp = temp.replaceAll("[^\\.0123456789]","");
                attackSpeed += Double.valueOf(temp);
            }
            if(attributes.get(i).toLowerCase().contains("Critical Hit Damage".toLowerCase())){
                String temp = attributes.get(i);
                temp = temp.replaceAll("[^\\.0123456789]","");
                critDamage += Double.valueOf(temp);
            }
            if(attributes.get(i).toLowerCase().contains("Critical Hit Chance".toLowerCase())){
                String temp = attributes.get(i);
                temp = temp.replaceAll("[^\\.0123456789]","");
                critChance += Double.valueOf(temp);
            }
            if(attributes.get(i).toLowerCase().contains("Area Damage".toLowerCase())){
                String temp = attributes.get(i);
                temp = temp.replaceAll("[^\\.0123456789]","");
                areaDamage += Double.valueOf(temp);
            }
            if(attributes.get(i).toLowerCase().contains("cooldown of all skills".toLowerCase())){
                String temp = attributes.get(i);
                temp = temp.replaceAll("[^\\.0123456789]","");
                cooldownReduction += Double.valueOf(temp);
            }
        }
    }

    private void selectColor(String color, ImageView imageView){
        switch(color){
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

    private void getItemIconURL(List<String> itemIconURL) {
        try{
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", itemsInformation.getShoulders().getIcon()) + ".png");
        }catch (Exception e){
            Log.e("Error", e.toString());
            itemIconURL.add(null);
        }
        try{
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", itemsInformation.getHands().getIcon()) + ".png");
        }catch (Exception e){
            Log.e("Error", e.toString());
            itemIconURL.add(null);
        }
        try{
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", itemsInformation.getLeftFinger().getIcon()) + ".png");
        }catch (Exception e){
            Log.e("Error", e.toString());
            itemIconURL.add(null);
        }
        try{
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", itemsInformation.getMainHand().getIcon()) + ".png");
        }catch (Exception e){
            Log.e("Error", e.toString());
            itemIconURL.add(null);
        }
        try{
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", itemsInformation.getHead().getIcon()) + ".png");
        }catch (Exception e){
            Log.e("Error", e.toString());
            itemIconURL.add(null);
        }
        try{
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", itemsInformation.getTorso().getIcon()) + ".png");
        }catch (Exception e){
            Log.e("Error", e.toString());
            itemIconURL.add(null);
        }
        try{
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", itemsInformation.getWaist().getIcon()) + ".png");
        }catch (Exception e){
            Log.e("Error", e.toString());
            itemIconURL.add(null);
        }
        try{
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", itemsInformation.getLegs().getIcon()) + ".png");
        }catch (Exception e){
            Log.e("Error", e.toString());
            itemIconURL.add(null);
        }
        try{
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", itemsInformation.getFeet().getIcon()) + ".png");
        }catch (Exception e){
            Log.e("Error", e.toString());
            itemIconURL.add(null);
        }
        try{
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", itemsInformation.getNeck().getIcon()) + ".png");
        }catch (Exception e){
            Log.e("Error", e.toString());
            itemIconURL.add(null);
        }
        try{
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", itemsInformation.getBracers().getIcon()) + ".png");
        }catch (Exception e){
            Log.e("Error", e.toString());
            itemIconURL.add(null);
        }
        try{
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", itemsInformation.getRightFinger().getIcon()) + ".png");
        }catch (Exception e){
            Log.e("Error", e.toString());
            itemIconURL.add(null);
        }
        try{
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", itemsInformation.getOffHand().getIcon()) + ".png");
        }catch (Exception e){
            Log.e("Error", e.toString());
            itemIconURL.add(null);
        }
    }

}
