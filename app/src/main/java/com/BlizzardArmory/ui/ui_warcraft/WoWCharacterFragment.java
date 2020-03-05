package com.BlizzardArmory.ui.ui_warcraft;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.BlizzardArmory.BuildConfig;
import com.BlizzardArmory.R;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.ui.IOnBackPressed;
import com.BlizzardArmory.warcraft.charactersummary.CharacterSummary;
import com.BlizzardArmory.warcraft.equipment.Enchantment;
import com.BlizzardArmory.warcraft.equipment.Equipment;
import com.BlizzardArmory.warcraft.equipment.EquippedItem;
import com.BlizzardArmory.warcraft.equipment.ItemEquipped;
import com.BlizzardArmory.warcraft.equipment.SelectedEssence;
import com.BlizzardArmory.warcraft.equipment.SelectedPower;
import com.BlizzardArmory.warcraft.equipment.Set;
import com.BlizzardArmory.warcraft.equipment.Socket;
import com.BlizzardArmory.warcraft.equipment.media.Media;
import com.BlizzardArmory.warcraft.statistic.Statistic;
import com.BlizzardArmory.warcraft.talents.Specialization;
import com.BlizzardArmory.warcraft.talents.Talent;
import com.BlizzardArmory.warcraft.talents.Talents;
import com.BlizzardArmory.warcraft.talents.specializationdata.SpecializationData;
import com.BlizzardArmory.warcraft.talents.specializationdata.TalentTier;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class WoWCharacterFragment extends Fragment implements IOnBackPressed {

    private String characterRealm;
    private String characterClicked;
    private String region;

    private AlertDialog dialog;

    //Character information
    private CharacterSummary characterSummary;
    private com.BlizzardArmory.warcraft.media.Media media;
    private Statistic statistic;
    private Talents talentsInfo;
    private Equipment equipment;
    private ArrayList<SpecializationData> specializationData = new ArrayList<>();

    private HashMap<String, String> imageURLs = new HashMap<>();

    private TextView characterName;
    private TextView itemLVL;
    private TextView levelRaceClass;
    private ImageView background;
    private RelativeLayout itemView;
    private ScrollView itemScrollView;
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

    private ImageView closeButton;
    private RelativeLayout loadingCircle;
    //Containers
    private HashMap<String, ImageView> gearImageView = new HashMap<>();


    private HashMap<String, String> stats = new HashMap<>();
    private HashMap<String, String> nameList = new HashMap<>();

    private Cache cache;
    private Network network;
    private RequestQueue requestQueue;

    private String urlMain = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wow_character_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        assert bundle != null;
        characterRealm = bundle.getString("realm");
        characterClicked = bundle.getString("character");
        media = new Gson().fromJson(bundle.getString("media"), com.BlizzardArmory.warcraft.media.Media.class);
        urlMain = bundle.getString("url");
        region = bundle.getString("region");
        URLConstants.loading = true;
        dialog = null;

        scrollView = view.findViewById(R.id.scrollView3);
        characterName = view.findViewById(R.id.character_name);
        itemLVL = view.findViewById(R.id.item_lvl);
        levelRaceClass = view.findViewById(R.id.level_race_class);
        background = view.findViewById(R.id.background);
        itemView = view.findViewById(R.id.item_stats);
        itemScrollView = view.findViewById(R.id.item_scroll_view);
        itemScrollView.setPadding(10, 10, 10, 10);
        closeButton = view.findViewById(R.id.close_button);
        LinearLayout linearLayoutItemStats = new LinearLayout(view.getContext());
        linearLayoutItemStats.setOrientation(LinearLayout.VERTICAL);
        linearLayoutItemStats.setGravity(Gravity.CENTER);
        itemView.addView(linearLayoutItemStats);
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

        activateCloseButton();

        Objects.requireNonNull(WoWCharacterFragment.this.getActivity()).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


        LinearLayout.LayoutParams layoutParamsName = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams layoutParamsStats = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsName.setMargins(20, 20, 20, -5);
        nameView.setLayoutParams(layoutParamsName);
        layoutParamsStats.setMargins(20, 0, 20, 0);
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

        cache = new DiskBasedCache(Objects.requireNonNull(getContext()).getCacheDir(), 1024 * 1024 * 5); // 1MB cap
        network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();

        downloadBackground();

        downloadAndSetCharacterInformation(bnOAuth2Helper);

        downloadStats(bnOAuth2Helper);

        downloadTalents(bnOAuth2Helper);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000000;
        Log.i("time", String.valueOf(duration));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void activateCloseButton() {
        closeButton.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                v.performClick();
                Log.i("CLOSE", "CLICKED");
                itemScrollView.setVisibility(View.GONE);
            }
            return false;
        });
    }

    private void downloadTalents(final BnOAuth2Helper bnOAuth2Helper) {
        final Gson gson = new GsonBuilder().create();
        try {
            String talentsURL = replaceZoneRealmCharacterNameURL(URLConstants.WOW_TALENT_QUERY);
            talentsURL = talentsURL.replace("TOKEN", bnOAuth2Helper.getAccessToken());
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI(region) + talentsURL, null,
                    response -> {
                        final int chunkSize = 2048;
                        for (int i = 0; i < response.toString().length(); i += chunkSize) {
                            Log.d("talent", response.toString().substring(i, Math.min(response.toString().length(), i + chunkSize)));
                        }
                        talentsInfo = gson.fromJson(response.toString(), Talents.class);

                        for (final Specialization specialization : talentsInfo.getSpecializations()) {
                            try {
                                JsonObjectRequest jsonRequest1 = new JsonObjectRequest(Request.Method.GET, specialization.getSpecialization().getKey().getHref() + URLConstants.ACCESS_TOKEN_AND_LOCALE + bnOAuth2Helper.getAccessToken(), null,
                                        response1 -> {
                                            specializationData.add(gson.fromJson(response1.toString(), SpecializationData.class));
                                            if (specializationData.size() == talentsInfo.getSpecializations().size()) {
                                                setTalentInformation();
                                            }
                                        },
                                        e -> {
                                            noTalent.setVisibility(View.VISIBLE);
                                            noTalent.setText("Talent information outdated");
                                        });
                                requestQueue.add(jsonRequest1);
                            } catch (Exception e) {
                                Log.e("Error Spec Download", e.toString());
                            }
                        }
                    },
                    e -> {
                        noTalent.setVisibility(View.VISIBLE);
                        noTalent.setText("Talent information outdated");
                    });

            requestQueue.add(jsonRequest);
        } catch (Exception e) {
            Log.e("Error Talents Download", e.toString());
        }

    }

    private void callErrorAlertDialog(int responseCode) {
        Objects.requireNonNull(getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        loadingCircle.setVisibility(View.GONE);
        URLConstants.loading = false;
        if (dialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()), R.style.DialogTransparent);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 20, 0, 0);

            TextView titleText = new TextView(getContext());

            titleText.setTextSize(20);
            titleText.setGravity(Gravity.CENTER_HORIZONTAL);
            titleText.setPadding(0, 20, 0, 20);
            titleText.setLayoutParams(layoutParams);
            titleText.setTextColor(Color.WHITE);

            TextView messageText = new TextView(getContext());

            messageText.setGravity(Gravity.CENTER_HORIZONTAL);
            messageText.setLayoutParams(layoutParams);
            messageText.setTextColor(Color.WHITE);

            Button button = new Button(getContext());

            if (responseCode == 404) {
                titleText.setText("Information Outdated");
                messageText.setText("Please login in game to update this character's information.");
                button.setText("OK");
            } else {
                titleText.setText("No Internet Connection");
                messageText.setText("Make sure that Wi-Fi or mobile data is turned on, then try again.");
                button.setText("RETRY");
            }

            button.setTextSize(18);
            button.setTextColor(Color.WHITE);
            button.setGravity(Gravity.CENTER);
            button.setWidth(200);
            button.setLayoutParams(layoutParams);
            button.setBackground(getContext().getDrawable(R.drawable.buttonstyle));

            dialog = builder.show();
            Objects.requireNonNull(dialog.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            dialog.getWindow().setLayout(800, 500);

            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setGravity(Gravity.CENTER);

            linearLayout.addView(titleText);
            linearLayout.addView(messageText);
            linearLayout.addView(button);

            LinearLayout.LayoutParams layoutParamsWindow = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(20, 20, 20, 20);

            dialog.addContentView(linearLayout, layoutParamsWindow);

            dialog.setOnCancelListener(dialog1 -> {
                if (responseCode != 404) {
                    dialog.hide();
                    WoWCharacterFragment fragment = (WoWCharacterFragment)
                            Objects.requireNonNull(getFragmentManager()).findFragmentById(R.id.fragment);

                    getFragmentManager().beginTransaction()
                            .detach(Objects.requireNonNull(fragment))
                            .attach(fragment)
                            .commit();
                } else {
                    Objects.requireNonNull(getFragmentManager()).beginTransaction().remove(WoWCharacterFragment.this).commit();
                }
            });

            button.setOnClickListener(v -> dialog.cancel());
        }
    }

    private void downloadStats(BnOAuth2Helper bnOAuth2Helper) {
        final Gson gson = new GsonBuilder().create();
        try {
            String statsURL = replaceZoneRealmCharacterNameURL(URLConstants.WOW_STATS_QUERY);
            statsURL = statsURL.replace("TOKEN", bnOAuth2Helper.getAccessToken());
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI(region) + statsURL, null,
                    response -> {
                        statistic = gson.fromJson(response.toString(), Statistic.class);
                        setCharacterInformationTextviews();
                    },
                    e -> {
                        if (e.networkResponse == null) {
                            callErrorAlertDialog(0);
                        } else {
                            callErrorAlertDialog(e.networkResponse.statusCode);
                        }
                    });

            requestQueue.add(jsonRequest);
        } catch (Exception e) {
            Log.e("Error Stats Download", e.toString());
        }

    }

    private void downloadAndSetCharacterInformation(final BnOAuth2Helper bnOAuth2Helper) {
        final Gson gson = new GsonBuilder().create();
        try {
            String characterURL = replaceZoneRealmCharacterNameURL(URLConstants.WOW_CHARACTER_QUERY);
            characterURL = characterURL.replace("TOKEN", bnOAuth2Helper.getAccessToken());

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI(region) + characterURL, null,
                    response -> {
                        characterSummary = gson.fromJson(response.toString(), CharacterSummary.class);
                        characterName.setText(characterSummary.getName());
                        itemLVL.setText(String.format("Item Level : %s", characterSummary.getEquippedItemLevel()));
                        String levelRaceClassString = characterSummary.getLevel() + " " + characterSummary.getRace().getName() + " " + characterSummary.getCharacterClass().getName();
                        levelRaceClass.setText(levelRaceClassString);

                        try {
                            String equipmentURL = replaceZoneRealmCharacterNameURL(URLConstants.WOW_ITEM_QUERY);

                            equipmentURL = equipmentURL.replace("TOKEN", bnOAuth2Helper.getAccessToken());
                            JsonObjectRequest jsonRequest1 = new JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI(region) + equipmentURL, null,
                                    response1 -> {
                                        final int chunkSize = 2048;
                                        for (int i = 0; i < response1.toString().length(); i += chunkSize) {
                                            Log.d("JSON", response1.toString().substring(i, Math.min(response1.toString().length(), i + chunkSize)));
                                        }
                                        final Gson gsonEquipment = new GsonBuilder().registerTypeAdapter(EquippedItem.class, new EquippedItem.NameDescriptionDeserializer()).create();
                                        equipment = gsonEquipment.fromJson(response1.toString(), Equipment.class);

                                        for (int i = 0; i < equipment.getEquippedItems().size(); i++) {
                                            try {
                                                downloadIcons(equipment, i, equipment.getEquippedItems().get(i).getSlot().getType(), bnOAuth2Helper, gson);
                                            } catch (Exception e) {
                                                break;
                                            }
                                            try {
                                                setCharacterItemsInformation(i);
                                            } catch (Exception e) {
                                                StringBuilder error = new StringBuilder();
                                                for (int j = 0; j < e.getStackTrace().length; j++) {
                                                    error.append(e.getStackTrace()[j]).append("\n");
                                                }
                                                Log.e("Character Information Error", error.toString());
                                            }
                                        }

                                        for (String slot : gearImageView.keySet()) {
                                            boolean slotEquipped = false;
                                            for (int i = 0; i < equipment.getEquippedItems().size(); i++) {
                                                if (slot.equals(equipment.getEquippedItems().get(i).getSlot().getType())) {
                                                    slotEquipped = true;
                                                }
                                            }
                                            if (!slotEquipped) {
                                                getEmptySlotIcon(slot, WoWCharacterFragment.this.getContext());
                                            }
                                        }
                                    },
                                    e -> {
                                        if (e.networkResponse == null) {
                                            callErrorAlertDialog(0);
                                        } else {
                                            callErrorAlertDialog(e.networkResponse.statusCode);
                                        }
                                    });

                            requestQueue.add(jsonRequest1);
                        } catch (Exception e) {
                            Log.e("Error Equipment Download", e.toString());
                        }


                    },
                    e -> {
                        if (e.networkResponse == null) {
                            callErrorAlertDialog(0);
                        } else {
                            callErrorAlertDialog(e.networkResponse.statusCode);
                        }
                    });

            requestQueue.add(jsonRequest);
        } catch (Exception e) {
            Log.e("Error", e.toString() + "\n");
            for (int i = 0; i < e.getStackTrace().length; i++) {
                Log.e("Error", e.getStackTrace()[i].toString() + "\n");
            }
        }


    }

    private void downloadBackground() {
        if (media != null) {
            ImageRequest imageRequest = new ImageRequest(media.getRenderUrl(), bitmap -> {
                backgroundMain = new BitmapDrawable(getResources(), bitmap);
                background.setImageDrawable(backgroundMain);
            }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                    e -> {
                        Log.e("BAD URL BACKGROUND", urlMain);
                    });
            requestQueue.add(imageRequest);
        }
    }

    private void downloadIcons(final Equipment equipment, final int index, final String itemSlot, BnOAuth2Helper bnOAuth2Helper, final Gson gson) {
        try {

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, equipment.getEquippedItems().get(index).getMedia().getKey().getHref()
                    + "&" + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken(), null,
                    response -> {
                        Media media = gson.fromJson(response.toString(), Media.class);
                        imageURLs.put(itemSlot, media.getAssets().get(0).getValue());
                        Log.i("Icon URL", media.getAssets().get(0).getValue());

                        ImageRequest imageRequest = new ImageRequest(imageURLs.get(itemSlot), bitmap -> {
                            if (index == equipment.getEquippedItems().size() - 1) {
                                Objects.requireNonNull(getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                loadingCircle.setVisibility(View.GONE);
                                URLConstants.loading = false;
                            }
                            item = new BitmapDrawable(getResources(), bitmap);
                            setIcons(itemSlot, equipment, item);
                        }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                                e -> {
                                    if (e.networkResponse == null) {
                                        callErrorAlertDialog(0);
                                    } else {
                                        callErrorAlertDialog(e.networkResponse.statusCode);
                                    }
                                });
                        requestQueue.add(imageRequest);
                    },
                    e -> {
                        if (index == equipment.getEquippedItems().size() - 1) {
                            Objects.requireNonNull(getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            loadingCircle.setVisibility(View.GONE);
                            URLConstants.loading = false;
                        }
                        Drawable errorIcon = getResources().getDrawable(R.drawable.error_icon, Objects.requireNonNull(getContext()).getTheme());
                        setIcons(itemSlot, equipment, errorIcon);
                    });

            requestQueue.add(jsonRequest);
        } catch (Exception e) {
            try {
                Log.e("Error Image URL", e.toString() + "\n" + equipment.getEquippedItems().get(index).getMedia().getKey().getHref()
                        + "&" + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken());
            } catch (Exception f) {
                Log.e("Error", f.toString());
            }
        }

    }

    private void setIcons(String itemSlot, Equipment equipment, Drawable item) {
        Objects.requireNonNull(gearImageView.get(itemSlot)).setImageDrawable(item);
        Objects.requireNonNull(gearImageView.get(itemSlot)).setPadding(3, 3, 3, 3);
        Objects.requireNonNull(gearImageView.get(itemSlot)).setClipToOutline(true);
        Drawable backgroundStroke = null;
        for (int i = 0; i < equipment.getEquippedItems().size(); i++) {
            if (equipment.getEquippedItems().get(i).getSlot().getType().equals(itemSlot)) {
                backgroundStroke = itemColor(equipment.getEquippedItems().get(i), new GradientDrawable());
            }
        }
        Objects.requireNonNull(gearImageView.get(itemSlot)).setBackground(backgroundStroke);
    }

    private String replaceZoneRealmCharacterNameURL(String url) {
        String newURL = url;
        if (region == null) {
            newURL = newURL.replace("zone", URLConstants.getRegion());
        } else {
            newURL = newURL.replace("zone", region.toLowerCase());
        }
        newURL = newURL.replace("realm", characterRealm);
        newURL = newURL.replace("characterName", characterClicked.toLowerCase());
        return newURL;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void getEmptySlotIcon(String itemSlot, Context context) {
        Objects.requireNonNull(gearImageView.get(itemSlot)).setPadding(3, 3, 3, 3);
        Objects.requireNonNull(gearImageView.get(itemSlot)).setClipToOutline(true);
        GradientDrawable backgroundStroke = new GradientDrawable();
        backgroundStroke.setCornerRadius(3);
        backgroundStroke.setColor(Color.parseColor("#000000"));
        backgroundStroke.setStroke(3, Color.GRAY);
        Objects.requireNonNull(gearImageView.get(itemSlot)).setBackground(backgroundStroke);

        switch (itemSlot) {
            case "HEAD":
                Objects.requireNonNull(gearImageView.get(itemSlot)).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_head));
                break;
            case "NECK":
                Objects.requireNonNull(gearImageView.get(itemSlot)).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_neck));
                break;
            case "SHOULDER":
                Objects.requireNonNull(gearImageView.get(itemSlot)).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_shoulders));
                break;
            case "CHEST":
            case "BACK":
                Objects.requireNonNull(gearImageView.get(itemSlot)).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_chest));
                break;
            case "SHIRT":
                Objects.requireNonNull(gearImageView.get(itemSlot)).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_shirt));
                break;
            case "TABARD":
                Objects.requireNonNull(gearImageView.get(itemSlot)).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_tabard));
                break;
            case "WRIST":
                Objects.requireNonNull(gearImageView.get(itemSlot)).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_wrist));
                break;
            case "HANDS":
                Objects.requireNonNull(gearImageView.get(itemSlot)).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_hands));
                break;
            case "WAIST":
                Objects.requireNonNull(gearImageView.get(itemSlot)).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_waist));
                break;
            case "LEGS":
                Objects.requireNonNull(gearImageView.get(itemSlot)).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_legs));
                break;
            case "FEET":
                Objects.requireNonNull(gearImageView.get(itemSlot)).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_feet));
                break;
            case "FINGER_1":
            case "FINGER_2":
                Objects.requireNonNull(gearImageView.get(itemSlot)).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_ring));
                break;
            case "TRINKET_1":
            case "TRINKET_2":
                Objects.requireNonNull(gearImageView.get(itemSlot)).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_trinket));
                break;
            case "MAIN_HAND":
                Objects.requireNonNull(gearImageView.get(itemSlot)).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_main_hand));
                break;
            case "OFF_HAND":
                Objects.requireNonNull(gearImageView.get(itemSlot)).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.empty_shield));
                break;
        }
    }

    private Drawable itemColor(EquippedItem equippedItem, GradientDrawable drawable) {
        drawable.setCornerRadius(3);
        drawable.setColor(Color.parseColor("#000000"));
        drawable.setStroke(3, getItemColor(equippedItem));

        return drawable;
    }

    private int getItemColor(EquippedItem item) {
        try {
            switch (item.getQuality().getType()) {
                case "POOR":
                    return Color.parseColor("#9d9d9d");
                case "COMMON":
                    return Color.parseColor("#ffffff");
                case "UNCOMMON":
                    return Color.parseColor("#1eff00");
                case "RARE":
                    return Color.parseColor("#0070dd");
                case "EPIC":
                    return Color.parseColor("#a335ee");
                case "LEGENDARY":
                    return Color.parseColor("#ff8000");
                case "ARTIFACT":
                    return Color.parseColor("#e6ca80");
                case "HEIRLOOM":
                    return Color.parseColor("#01c5f7");
            }
        } catch (Exception e) {
            return Color.GRAY;
        }

        return 0;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setOnPressItemInformation(final ImageView imageView, final int index) {
        imageView.setOnTouchListener((v, event) -> {
            closeButton.requestFocus();
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                nameView.setText(nameList.get(equipment.getEquippedItems().get(index).getSlot().getType()));
                nameView.setTextColor(getItemColor(equipment.getEquippedItems().get(index)));
                nameView.setTextSize(20);

                statsView.setText(Html.fromHtml(stats.get(equipment.getEquippedItems().get(index).getSlot().getType()), Html.FROM_HTML_MODE_LEGACY, source -> {
                    int resourceId = getResources().getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID);
                    Drawable drawable = getResources().getDrawable(resourceId, Objects.requireNonNull(WoWCharacterFragment.this.getContext()).getTheme());
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    return drawable;
                }, null));

                statsView.setTextColor(Color.WHITE);
                statsView.setTextSize(13);
                itemScrollView.setPadding(10, 10, 10, 10);
                itemScrollView.setBackground(imageView.getBackground());
                itemScrollView.setVisibility(View.VISIBLE);
                itemScrollView.setVerticalScrollBarEnabled(true);
            } else if (itemScrollView.getVisibility() == View.VISIBLE && event.getAction() == MotionEvent.ACTION_DOWN) {
                itemScrollView.setVisibility(View.GONE);
            }
            return true;
        });

        scrollView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (oldScrollY != scrollY) {
                itemScrollView.setVisibility(View.GONE);
            }
        });
    }

    private void setTalentInformation() {
        for (int i = 0; i < talentsInfo.getSpecializations().size(); i++) {
            if (talentsInfo.getActiveSpecialization().getName().equals(talentsInfo.getSpecializations().get(i).getSpecialization().getName())) {
                try {
                    talents.addAll(talentsInfo.getSpecializations().get(i).getTalents());
                } catch (Exception e) {
                    talents.add(new Talent());
                }
                spec.setText(String.format("Specialization: %s", talentsInfo.getActiveSpecialization().getName()));
            }
        }

        if (talentsInfo.getSpecializations().size() == 4) {
            specs.addTab(specs.newTab());
            specs.getTabAt(3).setText(talentsInfo.getSpecializations().get(3).getSpecialization().getName());
        }

        for (int i = 0; i < talentsInfo.getSpecializations().size(); i++) {
            TabLayout.Tab tab = specs.getTabAt(i);
            Objects.requireNonNull(tab).setText(talentsInfo.getSpecializations().get(i).getSpecialization().getName());
            Log.i("spec", talentsInfo.getSpecializations().get(i).getSpecialization().getName());
        }

        if (talentsInfo.getSpecializations().size() == 2) {
            TabLayout.Tab tab3 = specs.getTabAt(2);
            assert tab3 != null;
            tab3.setText("Unavailable");
        } else if (talentsInfo.getSpecializations().size() == 1) {
            TabLayout.Tab tab2 = specs.getTabAt(1);
            assert tab2 != null;
            tab2.setText("Unavailable");
            TabLayout.Tab tab3 = specs.getTabAt(2);
            assert tab3 != null;
            tab3.setText("Unavailable");
        }


        talentsTierContainer = new ArrayList<>(Arrays.asList(fifteen, thirty, forty_five, sixty, seventy_five, ninety, hundred));
        talentsContainer = new ArrayList<>(Arrays.asList(fifteenTalent, thirtyTalent, forty_fiveTalent, sixtyTalent, seventy_fiveTalent, ninetyTalent, hundredTalent));
        talentsTier = new ArrayList<>(Arrays.asList("15", "30", "45", "60", "75", "90", "100"));

        try {

            for (SpecializationData specializationList : specializationData) {
                if (specializationList.getName().equals(talentsInfo.getActiveSpecialization().getName())) {
                    sortTalents(specializationList);
                }
            }

            if (talents.size() > 0) {
                for (int i = 0; i < talents.size(); i++) {
                    noTalent.setVisibility(View.INVISIBLE);
                    talentsTierContainer.get(i).setGravity(Gravity.CENTER);
                    talentsTierContainer.get(i).setText(talentsTier.get(i));
                    if (talents.get(i).getTalent().getName() != null) {
                        talentsContainer.get(i).setText(talents.get(i).getTalent().getName());
                    } else {
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
                        if (!Objects.equals(tab.getText(), "Unavailable")) {
                            getTalentsForSpecificSpec(0);
                        } else {
                            removeTalents();
                            noTalent.setVisibility(View.VISIBLE);
                        }
                        break;
                    case 1:
                        if (!Objects.equals(tab.getText(), "Unavailable")) {
                            getTalentsForSpecificSpec(1);
                        } else {
                            removeTalents();
                            noTalent.setVisibility(View.VISIBLE);
                        }
                        break;
                    case 2:
                        if (!Objects.equals(tab.getText(), "Unavailable")) {
                            getTalentsForSpecificSpec(2);
                        } else {
                            removeTalents();
                            noTalent.setVisibility(View.VISIBLE);
                        }
                        break;
                    case 3:
                        if (!Objects.equals(tab.getText(), "Unavailable")) {
                            getTalentsForSpecificSpec(3);
                        } else {
                            removeTalents();
                            noTalent.setVisibility(View.VISIBLE);
                        }
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
    }

    private void sortTalents(final SpecializationData specializationData) {
        talents.sort((talent1, talent2) -> {
            int talentNumber1 = 0;
            int talentNumber2 = 0;
            for (TalentTier talentTier : specializationData.getTalentTiers()) {
                for (com.BlizzardArmory.warcraft.talents.specializationdata.Talent talent : talentTier.getTalents()) {
                    if (talent1.getTalent().getName().equals(talent.getTalent().getName())) {
                        talentNumber1 = talentTier.getLevel();
                    }
                    if (talent2.getTalent().getName().equals(talent.getTalent().getName())) {
                        talentNumber2 = talentTier.getLevel();
                    }
                }
            }
            if (talentNumber1 < talentNumber2) {
                return -1;
            } else if (talentNumber1 > talentNumber2) {
                return 1;
            }
            return 0;
        });
    }

    private void getTalentsForSpecificSpec(int position) {
        if (talentsInfo.getSpecializations().get(position).getTalents() == null) {
            removeTalents();
            noTalent.setVisibility(View.VISIBLE);
        } else {
            talents.clear();
            talents.addAll(talentsInfo.getSpecializations().get(position).getTalents());
            noTalent.setVisibility(View.INVISIBLE);

            for (SpecializationData specializationList : specializationData) {
                if (specializationList.getName().equals(talentsInfo.getSpecializations().get(position).getSpecialization().getName())) {
                    sortTalents(specializationList);
                }
            }

            for (int i = 0; i < talents.size(); i++) {
                talentsTierContainer.get(i).setGravity(Gravity.CENTER);
                talentsTierContainer.get(i).setText(talentsTier.get(i));
                talentsContainer.get(i).setText(talents.get(i).getTalent().getName());
            }
        }
    }

    private void removeTalents() {
        for (int i = 0; i < talentsContainer.size(); i++) {
            talentsTierContainer.get(i).setText("");
            talentsContainer.get(i).setText("");
        }
    }

    private void setCharacterItemsInformation(final int index) {
        EquippedItem equippedItem = equipment.getEquippedItems().get(index);
        nameList.put(equippedItem.getSlot().getType(), equippedItem.getName());
        StringBuilder slot = new StringBuilder(equippedItem.getInventoryType().getName());
        String itemLvl = "<font color=#edc201>" + equippedItem.getLevel().getDisplayString() + "</font><br>";
        String nameDescription = "";
        StringBuilder damageInfo = new StringBuilder();
        String durability = "";
        String transmog = "";
        String armor = "";
        String requiredLevel = "";
        String description = "";
        String trigger = "";
        String HoALevel = "";
        String bind = "";
        String itemSubclass;
        StringBuilder enchant = new StringBuilder();
        StringBuilder setInfo = new StringBuilder();
        String socketBonus = "";
        StringBuilder sockets = new StringBuilder();
        StringBuilder statsString = new StringBuilder();
        StringBuilder azeriteSpells = new StringBuilder();
        StringBuilder sellPrice = new StringBuilder();
        try {
            assert equippedItem.getSet() != null;
            setInfo.append("<font color=#edc201>").append(equippedItem.getSet().getDisplayString()).append("</font><br>");
            setInfo.append(formatSetItemText(equippedItem.getSet()));
        } catch (Exception e) {
            setInfo.replace(0, setInfo.length(), "");
            Log.e("set info", "none");
        }

        try {
            sellPrice.append(equippedItem.getSellPrice().getDisplayStrings().getHeader()).append(" ");
            if (!equippedItem.getSellPrice().getDisplayStrings().getGold().equals("0")) {
                sellPrice.append(equippedItem.getSellPrice().getDisplayStrings().getGold()).append(" <img src=\"gold\">");
            }
            if (!equippedItem.getSellPrice().getDisplayStrings().getSilver().equals("0")) {
                sellPrice.append(equippedItem.getSellPrice().getDisplayStrings().getSilver()).append(" <img src=\"silver\">");
            }
            if (!equippedItem.getSellPrice().getDisplayStrings().getCopper().equals("0")) {
                sellPrice.append(equippedItem.getSellPrice().getDisplayStrings().getCopper()).append(" <img src=\"copper\">");
            }
        } catch (Exception e) {
            Log.e("Sell price", "none");
        }

        try {
            durability = equippedItem.getDurability().getDisplayString();
        } catch (Exception e) {
            Log.e("Durability", "none");
        }
        try {
            for (Enchantment enchantment : equippedItem.getEnchantments()) {
                if (!enchantment.getDisplayString().equals("null")) {
                    enchant.append("<font color=#00ff00>").append(enchantment.getDisplayString()).append("</font><br>");
                }
            }
        } catch (Exception e) {
            Log.e("Enchant", "none");
        }
        try {
            itemSubclass = equippedItem.getItemSubclass().getName();
            int length = slot.length() + itemSubclass.length();
            while (length < 45) {
                slot.append("&nbsp;");
                length++;
            }
            int lengthSubClass = itemSubclass.length();
            while (lengthSubClass < 15) {
                slot.append("&nbsp;");
                lengthSubClass++;
            }
            Log.i("length", "" + slot.length());
            if (lengthSubClass + length == 60 && itemSubclass.equals("Miscellaneous")) {
                slot = new StringBuilder(slot.substring(0, slot.length() - 12));
            }
            slot.append(itemSubclass).append("<br>");
        } catch (Exception e) {
            Log.e("Item sub class", "none");
        }

        try {
            transmog = "<font color=#f57bf5>" + equippedItem.getTransmog().getDisplayString().replace("\n", "<br>") + "</font><br>";
        } catch (Exception e) {
            Log.e("Transmog", "none");
        }

        try {
            if (equippedItem.getArmor().getDisplayString() == null) {
                armor = ("<font color=#" + RGBToHexHTML(equippedItem.getArmor().getDisplay().getColor()) + ">") + equippedItem.getArmor().getDisplay().getDisplayString() + "</font><br>";
            } else {
                armor = equippedItem.getArmor().getDisplayString() + "<br>";
            }
        } catch (Exception e) {
            Log.e("Armor", "none");
        }

        try {
            requiredLevel = equippedItem.getRequirements().getLevel().getDisplayString();
        } catch (Exception e) {
            Log.e("Level", "none");
        }

        try {
            description = "<font color=#edc201>" + equippedItem.getDescription() + "</font>";
        } catch (Exception e) {
            Log.e("Description", "none");
        }

        if (!equippedItem.getNameDescription().equals("")) {
            Log.i("name descript", equippedItem.getNameDescription());
            nameDescription = "<font color=#00ff00>" + equippedItem.getNameDescription() + "</font><br>";
        } else {
            try {
                Log.i("name descript obj", equippedItem.getNameDescriptionObject().getDisplayString());
                nameDescription = "<font color=#" + getNameDescriptionColor(equippedItem) + ">" + equippedItem.getNameDescriptionObject().getDisplayString() + "</font><br>";
            } catch (Exception e) {
                Log.e("Error", e.toString());
                Log.e("Name description", "none");
            }
        }

        try {
            if (equippedItem.getSpells().get(0).getColor() != null) {
                trigger = "<font color=#" + RGBToHexHTML(equippedItem.getSpells().get(0).getColor()) + ">" + equippedItem.getSpells().get(0).getDescription() + "</font>";
            } else {
                trigger = "<font color=#00ff00>" + equippedItem.getSpells().get(0).getDescription() + "</font>";
            }

        } catch (Exception e) {
            Log.e("trigger", "none");
        }

        try {
            if (equippedItem.getName().equals("Heart of Azeroth")) {
                azeriteSpells.append("<br>");
                for (SelectedEssence selectedEssence : equippedItem.getAzeriteDetails().getSelectedEssences()) {
                    String essenceImage = "<img src=\"" + getEssenceImage(selectedEssence.getSlot()) + "\"> ";
                    String color = "<font color=#" + getEssenceRankColor(selectedEssence.getRank()) + ">";
                    azeriteSpells.append(essenceImage).append(color).append(selectedEssence.getEssence().getName()).append("</font><br>");
                }
            } else {
                azeriteSpells = new StringBuilder("<br><font color=#edc201>" + equippedItem.getAzeriteDetails().getSelectedPowersString() + "</font><br>");
                for (SelectedPower selectedPower : equippedItem.getAzeriteDetails().getSelectedPowers()) {
                    if (!selectedPower.isIsDisplayHidden()) {
                        azeriteSpells.append("- ").append(selectedPower.getSpellTooltip().getSpell().getName()).append("<br>");
                        azeriteSpells.append("<font color=#00ff00>").append(selectedPower.getSpellTooltip().getDescription()).append("</font><br>");
                    }
                }
            }
        } catch (Exception e) {
            Log.e("Azerite", "none");
        }

        try {
            for (Socket currentSocket : equippedItem.getSockets()) {

                if (currentSocket.getDisplayString() == null) {
                    sockets.append("<img src=\"").append(getSocketColor(currentSocket)).append("\"> ");
                    sockets.append(currentSocket.getSocketType().getName()).append("<br>");
                } else {
                    sockets.append("<img src=\"").append(getSocketColor(currentSocket)).append("\"> ");
                    sockets.append(currentSocket.getDisplayString()).append("<br>");
                }
            }
        } catch (Exception e) {
            Log.e("Socket", "none");
        }

        try {
            if (!equippedItem.getSocketBonus().equals("null")) {
                socketBonus = "<font color=#00ff00>" + equippedItem.getSocketBonus() + "</font><br>";
            }
        } catch (Exception e) {
            Log.e("socket bonus", "none");
        }

        try {
            bind = equippedItem.getBinding().getName() + "<br>";
        } catch (Exception e) {
            Log.e("bind", "none");
        }

        if (equippedItem.getName().equals("Heart of Azeroth")) {
            HoALevel = "<font color=#edc201>" + equippedItem.getAzeriteDetails().getLevel().getDisplayString() + "</font><br>";
        }
        if (equippedItem.getItemClass().getName().equals("Weapon")) {
            damageInfo = new StringBuilder(equippedItem.getWeapon().getDamage().getDisplayString());
            int length = damageInfo.length() + equippedItem.getWeapon().getAttackSpeed().getDisplayString().length();
            while (length < 39) {
                damageInfo.append("&nbsp;");
                length++;
            }
            damageInfo.append(equippedItem.getWeapon().getAttackSpeed().getDisplayString()).append("<br>").append(equippedItem.getWeapon().getDps().getDisplayString()).append("<br>");
        }

        try {
            for (int i = 0; i < equippedItem.getStats().size(); i++) {
                statsString.append("<font color=#" + RGBToHexHTML(equippedItem.getStats().get(i).getDisplayString().getColor()) + ">").append(equippedItem.getStats().get(i).getDisplayString().getDisplayString()).append("</font>").append("<br>");
            }
        } catch (Exception e) {
            for (int i = 0; i < equippedItem.getStats().size(); i++) {
                if (equippedItem.getStats().get(i).isIsEquipBonus()) {
                    statsString.append("<font color=#00ff00>").append(equippedItem.getStats().get(i).getDisplay_string()).append("</font>").append("<br>");
                } else {
                    statsString.append(equippedItem.getStats().get(i).getDisplay_string()).append("<br>");
                }
            }
        }


        stats.put(equippedItem.getSlot().getType(), String.format("%s%s%s%s%s%s%s%s%s", nameDescription, itemLvl, transmog, HoALevel, bind, slot.toString(), damageInfo.toString(), armor, statsString.toString()));

        if (!enchant.toString().equals("")) {
            stats.put(equippedItem.getSlot().getType(), stats.get(equippedItem.getSlot().getType()) + String.format("<br>%s", enchant));
        }

        stats.put(equippedItem.getSlot().getType(), stats.get(equippedItem.getSlot().getType()) + azeriteSpells);

        if (!trigger.equals("")) {
            stats.put(equippedItem.getSlot().getType(), stats.get(equippedItem.getSlot().getType()) + String.format("<br>%s<br>", trigger));
        }

        if (sockets.length() > 4) {
            stats.put(equippedItem.getSlot().getType(), stats.get(equippedItem.getSlot().getType()) + String.format("<br>%s", sockets));
        }

        if (!socketBonus.equals("")) {
            stats.put(equippedItem.getSlot().getType(), stats.get(equippedItem.getSlot().getType()) + String.format("%s", socketBonus));
        }

        if (!setInfo.toString().equals("")) {
            stats.put(equippedItem.getSlot().getType(), stats.get(equippedItem.getSlot().getType()) + String.format("<br>%s", setInfo));
        }

        if (!durability.equals("")) {
            stats.put(equippedItem.getSlot().getType(), stats.get(equippedItem.getSlot().getType()) + String.format("<br>%s<br>", durability));
        }

        if (!requiredLevel.equals("")) {
            if (durability.equals("")) {
                stats.put(equippedItem.getSlot().getType(), stats.get(equippedItem.getSlot().getType()) + String.format("<br>%s<br>", requiredLevel));
            } else {
                stats.put(equippedItem.getSlot().getType(), stats.get(equippedItem.getSlot().getType()) + String.format("%s<br>", requiredLevel));
            }
        }

        if (!sellPrice.toString().equals("")) {
            stats.put(equippedItem.getSlot().getType(), stats.get(equippedItem.getSlot().getType()) + String.format("%s<br>", sellPrice));
        }

        if (!description.equals("<font color=#edc201>null</font>")) {
            stats.put(equippedItem.getSlot().getType(), stats.get(equippedItem.getSlot().getType()) + String.format("<br>%s<br>", description));
        }

        Log.i("Stats per slot: ", equippedItem.getSlot().getName() + ": " + stats.get(equippedItem.getSlot().getType()));
        setOnPressItemInformation(Objects.requireNonNull(gearImageView.get(equippedItem.getSlot().getType())), index);
    }

    private StringBuilder formatSetItemText(Set set) {
        int equippedSetItem = 0;
        int[] itemRequiredForEffect = new int[set.getEffects().size()];
        StringBuilder setInfo = new StringBuilder();

        for (int i = 0; i < set.getEffects().size(); i++) {
            itemRequiredForEffect[i] = set.getEffects().get(i).getRequiredCount();
        }

        for (ItemEquipped itemEquipped : set.getItems()) {
            if (itemEquipped.isIsEquipped()) {
                setInfo.append("&nbsp;&nbsp;&nbsp;&nbsp;<font color=#ffff98>").append(itemEquipped.getItem().getName()).append("</font><br>");
                equippedSetItem++;
            } else {
                setInfo.append("&nbsp;&nbsp;&nbsp;&nbsp;<font color=#6e6e6e>").append(itemEquipped.getItem().getName()).append("</font><br>");
            }
        }

        setInfo.append("<br>");

        for (int i = 0; i < itemRequiredForEffect.length; i++) {
            if (itemRequiredForEffect[i] <= equippedSetItem) {
                setInfo.append("<font color=#ffff98>").append(set.getEffects().get(i).getDisplayString()).append("</font><br>");
            } else {
                setInfo.append("<font color=#6e6e6e>").append(set.getEffects().get(i).getDisplayString()).append("</font><br>");
            }
        }
        return setInfo;
    }

    private String RGBToHexHTML(com.BlizzardArmory.warcraft.equipment.Color color) {

        return String.format("#%X", Color.rgb(color.getR(), color.getG(), color.getB())).substring(3);
    }

    private String getNameDescriptionColor(EquippedItem equippedItem) {
        String red = Integer.toHexString(equippedItem.getNameDescriptionObject().getColor().getR());
        String green = Integer.toHexString(equippedItem.getNameDescriptionObject().getColor().getG());
        String blue = Integer.toHexString(equippedItem.getNameDescriptionObject().getColor().getB());

        if (red.equals("0")) {
            red += "0";
        }
        if (green.equals("0")) {
            green += "0";
        }
        if (blue.equals("0")) {
            blue += "0";
        }
        return red + green + blue;
    }

    private String getSocketColor(Socket currentSocket) {
        switch (currentSocket.getSocketType().getType()) {
            case "PRISMATIC":
                return "socket_prismatic";
            case "META":
                return "socket_meta";
            case "PUNCHCARDRED":
                return "socket_red";
            case "PUNCHCARDYELLOW":
                return "socket_yellow";
            case "PUNCHCARDBLUE":
                return "socket_blue";
        }
        return "socket_prismatic";
    }

    private String getEssenceImage(int essenceSlot) {
        switch (essenceSlot) {
            case 0:
                return "essence_border_gold";
            case 1:
            case 3:
                return "essence_border_silver";
        }
        return "essence_border_gold";
    }

    private String getEssenceRankColor(int rank) {
        switch (rank) {
            case 1:
                return "00ff00";
            case 2:
                return "0070ff";
            case 3:
                return "663288";
            case 4:
                return "ff8000";
        }
        return "00ff00";
    }

    private void setCharacterInformationTextviews() {
        health.setText(String.format("Health: %s", statistic.getHealth()));
        power.setText(String.format("%s: %s", statistic.getPowerType().getName(), statistic.getPower()));

        strength.setText(String.format("Strength: %s", statistic.getStrength().getEffective()));
        agility.setText(String.format("Agility: %s", statistic.getAgility().getEffective()));
        intellect.setText(String.format("Intellect: %s", statistic.getIntellect().getEffective()));
        stamina.setText(String.format("Stamina: %s", statistic.getStamina().getEffective()));

        crit.setText(String.format(Locale.ENGLISH, "Critical Strike: %.2f%%", statistic.getMeleeCrit().getValue()));
        haste.setText(String.format(Locale.ENGLISH, "Haste: %.2f%%", (double) statistic.getMeleeHaste().getValue()));
        mastery.setText(String.format(Locale.ENGLISH, "Mastery: %.2f%%", (double) statistic.getMastery().getValue()));
        versatility.setText(String.format(Locale.ENGLISH, "Versatility: %.2f%%", (double) statistic.getVersatilityDamageDoneBonus()));
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
