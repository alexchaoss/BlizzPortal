package com.BlizzardArmory.UI.UI_diablo;

import android.annotation.SuppressLint;
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.BlizzardArmory.R;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.connection.ConnectionService;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

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


    private ImageView paperdoll;
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

    private RelativeLayout itemView;
    private ScrollView itemScrollView;
    private ImageView closeButton;
    private HorizontalScrollView scrollView;

    private TextView itemName;
    private TextView weapontype;
    private TextView dps;
    private TextView primarystats;
    private TextView secondarystats;
    private TextView gems;
    private TextView transmog;
    private TextView flavortext;
    private TextView misctext;

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
        itemView = view.findViewById(R.id.item_stats);
        itemScrollView = view.findViewById(R.id.item_scroll_view);
        itemScrollView.setPadding(10, 10, 10, 10);
        closeButton = view.findViewById(R.id.close_button);
        LinearLayout linearLayoutItemStats = new LinearLayout(view.getContext());
        linearLayoutItemStats.setOrientation(LinearLayout.VERTICAL);
        linearLayoutItemStats.setGravity(Gravity.CENTER);
        itemView.addView(linearLayoutItemStats);
        scrollView = view.findViewById(R.id.scrollviewhoriz);
        itemName = new TextView(view.getContext());
        weapontype = new TextView(view.getContext());
        dps = new TextView(view.getContext());
        primarystats = new TextView(view.getContext());
        secondarystats = new TextView(view.getContext());
        gems = new TextView(view.getContext());
        transmog = new TextView(view.getContext());
        flavortext = new TextView(view.getContext());
        misctext = new TextView(view.getContext());
        LinearLayout.LayoutParams layoutParamsStats = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsStats.setMargins(20, 0, 20, 0);
        linearLayoutItemStats.addView(itemName, layoutParamsStats);
        linearLayoutItemStats.addView(weapontype, layoutParamsStats);
        linearLayoutItemStats.addView(dps, layoutParamsStats);
        linearLayoutItemStats.addView(primarystats, layoutParamsStats);
        linearLayoutItemStats.addView(secondarystats, layoutParamsStats);
        linearLayoutItemStats.addView(gems, layoutParamsStats);
        linearLayoutItemStats.addView(transmog, layoutParamsStats);
        linearLayoutItemStats.addView(flavortext, layoutParamsStats);
        linearLayoutItemStats.addView(misctext, layoutParamsStats);

        closeButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    v.performClick();
                    Log.i("CLOSE", "CLICKED");
                    itemScrollView.setVisibility(View.GONE);
                }
                return false;
            }
        });

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
                            setPaperdoll();
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
                    primary.append(items.get(index).getAttributesHtml().getPrimary().get(j)).append("<br>");
                }
            } catch (Exception e) {
                primary = new StringBuilder();
            }


            try {
                for (int j = 0; j < items.get(j).getAttributesHtml().getSecondary().size(); j++) {
                    secondary.append(items.get(index).getAttributesHtml().getSecondary().get(j)).append("<br>");
                }
            } catch (Exception e) {
                secondary = new StringBuilder();
            }


            try {
                for (int j = 0; j < items.get(index).getGems().size(); j++) {
                    StringBuilder gemAttributes = new StringBuilder();
                    for (int k = 0; k < items.get(index).getAttributesHtml().getSecondary().size(); k++) {
                        gemAttributes.append(items.get(index).getAttributesHtml().getSecondary().get(k)).append("<br>");
                    }
                    gem.append(gemAttributes).append("<br>");
                }
            } catch (Exception e) {
                Log.e("Error", e.toString());
            }


            primaryStatsMap.put(index, primary.toString());
            secondaryStatsMap.put(index, secondary.toString());
            gemsMap.put(index,gems.toString());

            Log.i("TEST", "TEST");
            setOnPressItemInformation(Objects.requireNonNull(imageViewItem.get(items.get(index).getSlots())), index);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setOnPressItemInformation(ImageView imageView, final int index) {

        imageView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.e("TEST", items.get(index).getName());
                    try {
                        itemName.setText(Html.fromHtml(items.get(index).getName() + "<br>", Html.FROM_HTML_MODE_LEGACY));
                    } catch (Exception e) {
                        Log.e("Error", e.toString());
                    }

                    try {
                        if (items.get(index).getType().getOneHanded()) {
                            weapontype.setText(Html.fromHtml("1-Hand" + "<br>", Html.FROM_HTML_MODE_LEGACY));
                        } else if (items.get(index).getType().getTwoHanded()) {
                            weapontype.setText(Html.fromHtml("2-Hand" + "<br>", Html.FROM_HTML_MODE_LEGACY));
                        }
                    } catch (Exception e) {
                        Log.e("Error", e.toString());
                    }
                    try {
                        if (items.get(index).getMinDamage() == 0 && items.get(index).getMaxDamage() != 0) {
                            dps.setText(Html.fromHtml(items.get(index).getMinDamage() + "-" + items.get(index).getMaxDamage() + "<br>" + items.get(index).getAttacksPerSecond() + "<br>", Html.FROM_HTML_MODE_LEGACY));
                        }
                    } catch (Exception e) {
                        dps.setText("");
                    }
                    try {
                        transmog.setText(Html.fromHtml(items.get(index).getTransmog().getName() + "<br>", Html.FROM_HTML_MODE_LEGACY));
                    } catch (Exception e) {
                        Log.e("Error", e.toString());
                    }
                    try {
                        primarystats.setText(Html.fromHtml(primaryStatsMap.get(index), Html.FROM_HTML_MODE_LEGACY));
                        secondarystats.setText(Html.fromHtml(secondaryStatsMap.get(index), Html.FROM_HTML_MODE_LEGACY));
                        gems.setText(Html.fromHtml(gemsMap.get(index), Html.FROM_HTML_MODE_LEGACY));
                    } catch (Exception e) {
                        Log.e("Error", e.toString());

                    }

                    try {
                        flavortext.setText(Html.fromHtml(items.get(index).getFlavorText() + "<br>", Html.FROM_HTML_MODE_LEGACY));
                    } catch (Exception e) {
                        Log.e("Error", e.toString());
                    }

                    try {
                        if (items.get(index).getAccountBound()) {
                            misctext.setText(Html.fromHtml(items.get(index).getRequiredLevel() + "Account bound", Html.FROM_HTML_MODE_LEGACY));
                        }

                    } catch (Exception e) {
                        Log.e("Error", e.toString());
                    }
                    itemScrollView.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });

        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {

            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (oldScrollX != scrollX) {
                    itemScrollView.setVisibility(View.GONE);
                }
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
        for (String key: itemIconURL.keySet()) {
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

        for(String key: imageViewItem.keySet()){
            if(imageViewItem.get(key) == null){
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
                if(temp.charAt(temp.length()-1) == '.'){
                    temp = temp.substring(0, temp.length()-2);
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

    private void getItemIconURL() {
        for (int i = 0; i < items.size(); i++) {
            try {
                itemIconURL.put(items.get(i).getSlots(),URLConstants.D3_ICON_ITEMS.replace("icon.png", items.get(i).getIcon()) + ".png");
            } catch (Exception e) {
                Log.e("Error", e.toString());
                itemIconURL.put("empty", null);
            }
        }
    }

}
