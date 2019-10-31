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
import android.support.v7.widget.CardView;
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
import java.util.List;
import java.util.Objects;

public class D3CharacterFragment extends Fragment {

    private int id;

    private static CharacterInformation characterInformation;
    private JSONObject characterInfo;
    private Items itemsInformation;

    private int imageIndex = 0;

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
    private ArrayList<String> itemIconURL = new ArrayList<>();


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

    private CardView cardView;
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
        cardView = view.findViewById(R.id.item_stats);
        cardView.setContentPadding(10, 10, 10, 10);
        ScrollView itemInfoScroll = new ScrollView(view.getContext());
        LinearLayout linearLayoutItemStats = new LinearLayout(view.getContext());
        linearLayoutItemStats.setOrientation(LinearLayout.VERTICAL);
        linearLayoutItemStats.setGravity(Gravity.CENTER);
        itemInfoScroll.addView(linearLayoutItemStats);
        cardView.addView(itemInfoScroll);
        scrollView = view.findViewById(R.id.scrollviewhoriz);
        TextView itemName = new TextView(view.getContext());
        TextView weapontype = new TextView(view.getContext());
        TextView dps = new TextView(view.getContext());
        TextView primarystats = new TextView(view.getContext());
        TextView secondarystats = new TextView(view.getContext());
        TextView gems = new TextView(view.getContext());
        TextView transmog = new TextView(view.getContext());
        TextView flavortext = new TextView(view.getContext());
        TextView misctext = new TextView(view.getContext());
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
                            //setItemInformation();

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

    private void setItemInformation() {
        for (int i = 0; i < items.size(); i++) {

            String primary = "";
            String secondary = "";
            String gem = "";
            String accountBound = "";

            try {
                itemName.setText(Html.fromHtml(items.get(i).getName() + "<br>", Html.FROM_HTML_MODE_LEGACY));
            } catch (Exception e) {
            }

            try {
                if (items.get(i).getType().getOneHanded()) {
                    weapontype.setText(Html.fromHtml("1-Hand" + "<br>", Html.FROM_HTML_MODE_LEGACY));
                } else if (items.get(i).getType().getTwoHanded()) {
                    weapontype.setText(Html.fromHtml("2-Hand" + "<br>", Html.FROM_HTML_MODE_LEGACY));
                }
            } catch (Exception e) {
            }
            try {
                if (items.get(i).getMinDamage() == 0 && items.get(i).getMaxDamage() != 0) {
                    dps.setText(Html.fromHtml(items.get(i).getMinDamage() + "-" + items.get(i).getMaxDamage() + "<br>" + items.get(i).getAttacksPerSecond() + "<br>", Html.FROM_HTML_MODE_LEGACY));
                }
            } catch (Exception e) {
                dps.setText("");
            }

            try {
                for (int j = 0; j < items.get(i).getAttributesHtml().getPrimary().size(); j++) {
                    primary += items.get(i).getAttributesHtml().getPrimary().get(j) + "<br>";
                }
                primarystats.setText(Html.fromHtml(primary, Html.FROM_HTML_MODE_LEGACY));
            } catch (Exception e) {
                primary = "";
            }


            try {
                for (int j = 0; j < items.get(j).getAttributesHtml().getSecondary().size(); j++) {
                    secondary += items.get(i).getAttributesHtml().getSecondary().get(j) + "<br>";
                }
                secondarystats.setText(Html.fromHtml(secondary, Html.FROM_HTML_MODE_LEGACY));
            } catch (Exception e) {
                secondary = "";
            }


            try {
                for (int j = 0; j < items.get(i).getGems().size(); j++) {
                    String gemAttributes = "";
                    for (int k = 0; k < items.get(i).getAttributesHtml().getSecondary().size(); j++) {
                        gemAttributes += items.get(i).getAttributesHtml().getSecondary().get(k) + "<br>";
                    }
                    gem += gemAttributes + "<br>";
                }
                gems.setText(Html.fromHtml(gem, Html.FROM_HTML_MODE_LEGACY));
            } catch (Exception e) {
            }

            try {
                transmog.setText(Html.fromHtml(items.get(i).getTransmog().getName() + "<br>", Html.FROM_HTML_MODE_LEGACY));
            } catch (Exception e) {
            }
            try {
                flavortext.setText(Html.fromHtml(items.get(i).getFlavorText() + "<br>", Html.FROM_HTML_MODE_LEGACY));
            } catch (Exception e) {
            }

            try {
                if (items.get(i).getAccountBound()) {
                    accountBound = "Account Bound";
                }
                misctext.setText(Html.fromHtml(items.get(i).getRequiredLevel() + accountBound, Html.FROM_HTML_MODE_LEGACY));
            } catch (Exception e) {
            }

            Log.i("TEST", "TEST");
            setOnPressItemInformation(imageViewItem.get(i));
        }
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

    private void setOnPressItemInformation(ImageView imageView) {

        imageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            @SuppressWarnings("deprecation")
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        cardView.setVisibility(View.VISIBLE);
                        break;
                    }
                }
                return true;
            }
        });

        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {

            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (oldScrollX != scrollX) {
                    cardView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void getItemIcons() {
        for (int i = 0; i < itemIconURL.size(); i++) {
            ImageRequest imageRequest = new ImageRequest(itemIconURL.get(i), new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap bitmap) {
                    Drawable item = new BitmapDrawable(getResources(), bitmap);
                    imageViewItem.get(imageIndex).setImageDrawable(item);
                    imageIndex++;

                    if (imageIndex == itemIconURL.size()) {
                        Objects.requireNonNull(getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        loadingCircle.setVisibility(View.GONE);
                    }
                }
            }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            try {
                                if (!ConnectionService.isConnected()) {
                                    ConnectionService.showNoConnectionMessage(getActivity().getApplicationContext(), D3CharacterFragment.this);
                                }
                            } catch (Exception e) {
                            }
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
        for (int i = 0; i < imageViewItem.size(); i++) {
            try {
                selectColor(items.get(i).getDisplayColor(), imageViewItem.get(i));
            } catch (Exception e) {
                e.printStackTrace();
                selectColor("", imageViewItem.get(i));
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
                temp = temp.replaceAll("[^\\.0123456789]", "");
                attackSpeed += Double.valueOf(temp);
            }
            if (attributes.get(i).toLowerCase().contains("Critical Hit Damage".toLowerCase())) {
                String temp = attributes.get(i);
                temp = temp.replaceAll("[^\\.0123456789]", "");
                critDamage += Double.valueOf(temp);
            }
            if (attributes.get(i).toLowerCase().contains("Critical Hit Chance".toLowerCase())) {
                String temp = attributes.get(i);
                temp = temp.replaceAll("[^\\.0123456789]", "");
                critChance += Double.valueOf(temp);
            }
            if (attributes.get(i).toLowerCase().contains("Area Damage".toLowerCase())) {
                String temp = attributes.get(i);
                temp = temp.replaceAll("[^\\.0123456789]", "");
                areaDamage += Double.valueOf(temp);
            }
            if (attributes.get(i).toLowerCase().contains("cooldown of all skills".toLowerCase())) {
                String temp = attributes.get(i);
                temp = temp.replaceAll("[^\\.0123456789]", "");
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

    private void getItemIconURL(List<String> itemIconURL) {
        for (int i = 0; i < items.size(); i++) {
            try {
                itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", items.get(i).getIcon()) + ".png");
            } catch (Exception e) {
                Log.e("Error", e.toString());
                itemIconURL.add(null);
            }
        }
    }

}
