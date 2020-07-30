package com.BlizzardArmory.ui.ui_overwatch;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.BlizzardArmory.R;
import com.BlizzardArmory.connection.ErrorMessages;
import com.BlizzardArmory.connection.NetworkServices;
import com.BlizzardArmory.connection.URLConstants;
import com.BlizzardArmory.model.overwatch.Profile;
import com.BlizzardArmory.model.overwatch.favorite.FavoriteProfile;
import com.BlizzardArmory.model.overwatch.favorite.FavoriteProfiles;
import com.BlizzardArmory.model.overwatch.heroes.Hero;
import com.BlizzardArmory.model.overwatch.topheroes.TopHero;
import com.BlizzardArmory.ui.GamesActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The type Ow activity.
 */
public class OWFragment extends Fragment {

    private NetworkServices networkServices;

    private String username;
    private String platform;

    private Spinner topHeroesListSpinner;
    private Spinner careerListSpinner;
    private GradientDrawable switchCompQuickRadius;

    private RelativeLayout loadingCircle;
    private Profile accountInformation;
    private ArrayList<TopHero> topHeroesQuickPlay;
    private ArrayList<TopHero> topHeroesCompetitive;
    private ArrayList<Hero> careerQuickPlay;
    private ArrayList<Hero> careerCompetitive;

    private ImageView avatar;
    private TextView name;
    private ImageView levelIcon;
    private ImageView prestigeIcon;
    private TextView level;
    private ImageView ratingIconTank;
    private ImageView ratingRankIconTank;
    private TextView ratingTank;
    private ImageView ratingIconDamage;
    private ImageView ratingRankIconDamage;
    private TextView ratingDamage;
    private ImageView ratingIconSupport;
    private ImageView ratingRankIconSupport;
    private TextView ratingSupport;
    private TextView gamesWon;
    //private ImageView endorsementIcon;
    //private TextView endorsement;
    private ImageView topCharacter;

    private final String TIME_PLAYED = "Time Played";
    private final String GAMES_WON = "Games Won";
    private final String WEAPON_ACCURACY = "Weapon Accuracy";
    private final String ELIMINATIONS_PER_LIFE = "Eliminations per Life";
    private final String MULTIKILL_BEST = "Multikill - Best";
    private final String OBJECTIVE_KILLS = "Objective Kills";
    private final String[] sortHeroList = {TIME_PLAYED, GAMES_WON, WEAPON_ACCURACY, ELIMINATIONS_PER_LIFE, MULTIKILL_BEST, OBJECTIVE_KILLS};
    private ArrayList<String> sortCareerHeroes = new ArrayList<>();

    private LinearLayout heroList;

    private TextView quickplay;
    private TextView competitive;
    private boolean comp = false;

    private LinearLayout best;
    private LinearLayout assists;
    private LinearLayout game;
    private LinearLayout average;
    private LinearLayout combat;
    private LinearLayout misc;
    private LinearLayout matchAwards;

    private String privateProfile = "This profile is private and the information unavailable";

    private ImageView favorite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ow_activity, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("TEST", "TEST");
        loadingCircle = view.findViewById(R.id.loadingCircle);
        loadingCircle.setVisibility(View.VISIBLE);
        assert GamesActivity.Companion.getUserInformation() != null;
        avatar = view.findViewById(R.id.avatar);
        name = view.findViewById(R.id.name);
        levelIcon = view.findViewById(R.id.level_icon);
        ratingIconTank = view.findViewById(R.id.rating_icon_tank);
        ratingRankIconTank = view.findViewById(R.id.rating_icon_rank_tank);
        ratingTank = view.findViewById(R.id.rating_tank);
        ratingIconDamage = view.findViewById(R.id.rating_icon_damage);
        ratingRankIconDamage = view.findViewById(R.id.rating_icon_rank_damage);
        ratingDamage = view.findViewById(R.id.rating_damage);
        ratingIconSupport = view.findViewById(R.id.rating_icon_support);
        ratingRankIconSupport = view.findViewById(R.id.rating_icon_rank_support);
        ratingSupport = view.findViewById(R.id.rating_support);
        level = view.findViewById(R.id.level);
        gamesWon = view.findViewById(R.id.games_won);
        topCharacter = view.findViewById(R.id.top_character);
        /*endorsementIcon = findViewById(R.id.endorsement_icon);
        endorsementIcon.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        endorsement = findViewById(R.id.endorsement_level);*/
        prestigeIcon = view.findViewById(R.id.prestige_icon);
        topHeroesListSpinner = view.findViewById(R.id.spinner);
        careerListSpinner = view.findViewById(R.id.spinner_career);
        heroList = view.findViewById(R.id.hero_list);
        competitive = view.findViewById(R.id.competitive);
        quickplay = view.findViewById(R.id.quickplay);
        LinearLayout quickComp = view.findViewById(R.id.quick_comp);
        best = view.findViewById(R.id.best);
        assists = view.findViewById(R.id.assist);
        game = view.findViewById(R.id.game);
        average = view.findViewById(R.id.average);
        combat = view.findViewById(R.id.combat);
        misc = view.findViewById(R.id.misc);
        matchAwards = view.findViewById(R.id.match);

        favorite = requireActivity().findViewById(R.id.favorite);
        favorite.setVisibility(View.VISIBLE);
        favorite.setImageResource(R.drawable.ic_star_border_black_24dp);
        favorite.setTag(R.drawable.ic_star_border_black_24dp);

        GradientDrawable switchCompQuickBorder = new GradientDrawable();
        switchCompQuickBorder.setCornerRadius(5);
        switchCompQuickBorder.setStroke(3, Color.parseColor("#FFFFFF"));
        quickComp.setBackground(switchCompQuickBorder);

        switchCompQuickRadius = new GradientDrawable();
        switchCompQuickRadius.setCornerRadius(5);
        switchCompQuickRadius.setColor(Color.parseColor("#FFFFFF"));
        quickplay.setBackground(switchCompQuickRadius);

        assert this.getArguments() != null;
        username = this.getArguments().getString("username");
        platform = this.getArguments().getString("platform");

        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ow-api.com/v1/stats/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        networkServices = retrofit.create(NetworkServices.class);

        downloadAccountInformation();
    }

    private void downloadAccountInformation() {
        //String testURL = "https://ow-api.com/v1/stats/xbl/global/Hcpeful/complete";
        Log.i("URL", URLConstants.getOWProfile(platform, username));

        requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        URLConstants.loading = true;

        Call<Profile> call = networkServices.getOWProfile(URLConstants.getOWProfile(username, platform));
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(@NotNull Call<Profile> call, @NotNull retrofit2.Response<Profile> response) {

                if (response.isSuccessful()) {
                    try {
                        accountInformation = response.body();
                        manageFavorite();
                        downloadAvatar();
                        setName();
                        downloadLevelIcon();
                        setGamesWon();
                        setRatingInformation();
                        //downloadEndorsementIcon(requestQueue);
                        //endorsement.setText(String.valueOf(accountInformation.getEndorsement()));

                        setTopHeroesLists();
                        setCareerLists();
                        setSpinnerCareerList(careerQuickPlay);

                        if (topHeroesQuickPlay.size() > 0) {
                            setTopCharacterImage(topHeroesQuickPlay.get(0).getClass().getSimpleName());
                        }
                        setSpinnerTopHeroes(topHeroesListSpinner);
                        setSpinnerCareer(careerListSpinner);

                        quickplay.setOnClickListener(v -> {
                            if (comp) {
                                comp = false;
                                quickplay.setBackground(switchCompQuickRadius);
                                quickplay.setTextColor(Color.parseColor("#000000"));
                                competitive.setTextColor(Color.parseColor("#FFFFFF"));
                                competitive.setBackgroundColor(0);
                                sortList(topHeroesQuickPlay, sortHeroList[0]);
                                if (topHeroesQuickPlay.size() > 0) {
                                    setTopCharacterImage(topHeroesQuickPlay.get(0).getClass().getSimpleName());
                                }
                                setSpinnerTopHeroes(topHeroesListSpinner);
                                setSpinnerCareerList(careerQuickPlay);
                                setSpinnerCareer(careerListSpinner);
                            }
                        });

                        competitive.setOnClickListener(v -> {
                            if (!comp) {
                                comp = true;
                                competitive.setBackground(switchCompQuickRadius);
                                competitive.setTextColor(Color.parseColor("#000000"));
                                quickplay.setBackgroundColor(0);
                                quickplay.setTextColor(Color.parseColor("#FFFFFF"));
                                sortList(topHeroesCompetitive, sortHeroList[0]);
                                if (topHeroesCompetitive.size() > 0) {
                                    setTopCharacterImage(topHeroesCompetitive.get(0).getClass().getSimpleName());
                                }
                                setSpinnerTopHeroes(topHeroesListSpinner);
                                setSpinnerCareerList(careerCompetitive);
                                setSpinnerCareer(careerListSpinner);

                            }
                        });

                        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        loadingCircle.setVisibility(View.GONE);
                        URLConstants.loading = false;
                    } catch (Exception e) {
                        gamesWon.setText(privateProfile);
                        gamesWon.setTextSize(18);
                        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        loadingCircle.setVisibility(View.GONE);
                        URLConstants.loading = false;
                        favorite.setOnClickListener(v -> Toast.makeText(requireActivity(), "Can't add private profile to favorites", Toast.LENGTH_SHORT).show());
                        Log.e("Error", "Exception", e);
                    }

                } else if (response.code() >= 400) {
                    showNoConnectionMessage(response.code());
                }
            }

            @Override
            public void onFailure(@NotNull Call<Profile> call, @NotNull Throwable t) {
                Log.e("Error", "trace", t);
                showNoConnectionMessage(0);
            }


        });
    }

    private void manageFavorite() {
        FavoriteProfiles profiles = new FavoriteProfiles();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireActivity());
        Gson gson = new GsonBuilder().create();
        int index = -1;
        int indexTemp = 0;
        String favorites = prefs.getString("ow-favorites", "DEFAULT");
        assert favorites != null;
        if (!favorites.equals("DEFAULT") && !favorites.equals("{\"profile_list\":[]}")) {
            profiles = gson.fromJson(favorites, FavoriteProfiles.class);
            for (FavoriteProfile profile : profiles.getProfiles()) {
                if (Objects.equals(profile.getUsername(), username) && Objects.equals(profile.getPlatform(), platform)) {
                    favorite.setImageResource(R.drawable.ic_star_black_24dp);
                    favorite.setTag(R.drawable.ic_star_black_24dp);
                    index = indexTemp;
                } else {
                    addToFavorites(profiles, prefs, gson, index);
                }
                indexTemp++;
            }
            removeFavorite(profiles, prefs, gson, index);
        } else {
            addToFavorites(profiles, prefs, gson, index);
        }
    }

    private void removeFavorite(FavoriteProfiles profiles, SharedPreferences prefs, Gson gson, int index) {
        if ((int) favorite.getTag() == R.drawable.ic_star_black_24dp && index != -1) {
            favorite.setOnClickListener(v -> {
                favorite.setImageResource(R.drawable.ic_star_border_black_24dp);
                favorite.setTag(R.drawable.ic_star_black_24dp);
                profiles.getProfiles().remove(index);
                prefs.edit().putString("ow-favorites", gson.toJson(profiles)).apply();
                addToFavorites(profiles, prefs, gson, index);
                Toast.makeText(requireActivity(), "Profile removed from favorites", Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void addToFavorites(FavoriteProfiles profiles, SharedPreferences prefs, Gson gson, int index) {
        AtomicBoolean containsProfiles = new AtomicBoolean(false);
        favorite.setOnClickListener(v -> {
            for (FavoriteProfile profile : profiles.getProfiles()) {
                if (Objects.equals(profile.getUsername(), username) && Objects.equals(profile.getPlatform(), platform)) {
                    containsProfiles.set(true);
                    break;
                }
            }
            if (!containsProfiles.get()) {
                favorite.setImageResource(R.drawable.ic_star_black_24dp);
                favorite.setTag(R.drawable.ic_star_black_24dp);
                profiles.getProfiles().add(new FavoriteProfile(platform, username, accountInformation));
                prefs.edit().putString("ow-favorites", gson.toJson(profiles)).apply();
                Toast.makeText(requireActivity(), "Profile added to favorites", Toast.LENGTH_SHORT).show();
            }
            removeFavorite(profiles, prefs, gson, index);
        });

    }

    private void setCareerLists() {
        careerQuickPlay = accountInformation.getQuickPlayStats().getCareerStats().getHeroList();
        careerCompetitive = accountInformation.getCompetitiveStats().getCareerStats().getHeroList();

        for (int i = 0; i < careerQuickPlay.size(); i++) {
            if (careerQuickPlay.get(i) == null) {
                careerQuickPlay.remove(i);
                i--;
            }
        }

        for (int i = 0; i < careerCompetitive.size(); i++) {
            if (careerCompetitive.get(i) == null) {
                careerCompetitive.remove(i);
                i--;
            }
        }
    }

    private void setSpinnerCareerList(ArrayList<Hero> list) {
        sortCareerHeroes.clear();
        for (int i = 0; i < list.size(); i++) {
            String tempName;
            if (list.get(i).getClass().getSimpleName().equals("WreckingBall")) {
                tempName = "Wrecking Ball";
            } else if (list.get(i).getClass().getSimpleName().equals("Dva")) {
                tempName = "D.Va";
            } else if (list.get(i).getClass().getSimpleName().equals("Soldier76")) {
                tempName = "Soldier: 76";
            } else if (list.get(i).getClass().getSimpleName().equals("AllHeroes")) {
                tempName = "All Heroes";
            } else {
                tempName = list.get(i).getClass().getSimpleName() + " ";
            }
            sortCareerHeroes.add(tempName);
        }
    }

    private void setTopHeroesLists() {
        topHeroesQuickPlay = accountInformation.getQuickPlayStats().getTopHeroes().getHeroList();
        topHeroesCompetitive = accountInformation.getCompetitiveStats().getTopHeroes().getHeroList();

        for (int i = 0; i < topHeroesQuickPlay.size(); i++) {
            if (topHeroesQuickPlay.get(i) == null) {
                topHeroesQuickPlay.remove(i);
                i--;
            }
        }

        for (int i = 0; i < topHeroesCompetitive.size(); i++) {
            if (topHeroesCompetitive.get(i) == null) {
                topHeroesCompetitive.remove(i);
                i--;
            }
        }

        sortList(topHeroesCompetitive, sortHeroList[0]);
        sortList(topHeroesQuickPlay, sortHeroList[0]);
    }

    private void setSpinnerTopHeroes(Spinner spinner) {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_dropdown_item_1line, sortHeroList) {

            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setAllCaps(true);
                tv.setBackgroundColor(Color.WHITE);
                tv.setTextSize(15);
                tv.setGravity(Gravity.CENTER);
                return view;


            }
        };

        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(Color.parseColor("#CCCCCC"));
                ((TextView) view).setTextSize(15);
                ((TextView) view).setGravity(Gravity.CENTER_VERTICAL);

                if (comp) {
                    sortList(topHeroesCompetitive, sortHeroList[position]);
                    setProgressBarsTopHeroes((String) parent.getItemAtPosition(position), topHeroesCompetitive);
                } else {
                    sortList(topHeroesQuickPlay, sortHeroList[position]);
                    setProgressBarsTopHeroes((String) parent.getItemAtPosition(position), topHeroesQuickPlay);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER);
                ((TextView) parent.getChildAt(0)).setTextColor(0);
            }
        });
    }

    private void setSpinnerCareer(Spinner spinner) {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_dropdown_item_1line, sortCareerHeroes) {

            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setAllCaps(true);
                tv.setBackgroundColor(Color.WHITE);
                tv.setTextSize(15);
                tv.setGravity(Gravity.CENTER);
                return view;
            }
        };

        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(Color.parseColor("#CCCCCC"));
                ((TextView) view).setTextSize(15);
                ((TextView) view).setGravity(Gravity.CENTER_VERTICAL);

                if (comp) {
                    setCareerStats(position, careerCompetitive);
                } else {
                    setCareerStats(position, careerQuickPlay);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER);
                ((TextView) parent.getChildAt(0)).setTextColor(0);
            }
        });
    }

    private void setCareerStats(int position, ArrayList<Hero> career) {
        best.removeViews(1, best.getChildCount() - 1);
        assists.removeViews(1, assists.getChildCount() - 1);
        game.removeViews(1, game.getChildCount() - 1);
        average.removeViews(1, average.getChildCount() - 1);
        combat.removeViews(1, combat.getChildCount() - 1);
        misc.removeViews(1, misc.getChildCount() - 1);
        matchAwards.removeViews(1, matchAwards.getChildCount() - 1);


        try {
            setSpecificCareerList(career.get(position).getBest().getBestList(), best, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            setSpecificCareerList(career.get(position).getAssists().getAssists(), assists, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            setSpecificCareerList(career.get(position).getAverage().getAverage(), average, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            setSpecificCareerList(career.get(position).getGame().getGame(), game, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            setSpecificCareerList(career.get(position).getMiscellaneous().getMisc(), misc, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            setSpecificCareerList(career.get(position).getMatchAwards().getMatch(), matchAwards, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            setSpecificCareerList(career.get(position).getCombat().getCombat(), combat, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setSpecificCareerList(HashMap<String, String> list, LinearLayout parentLayout, int marginStart) {
        final float scale = requireActivity().getResources().getDisplayMetrics().density;
        int i = 0;
        for (String key : list.keySet()) {
            LinearLayout linearLayout = new LinearLayout(requireActivity());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins((int) (marginStart * scale + 0.5f), 0, 0, 0);
            linearLayout.setLayoutParams(layoutParams);

            TextView value = new TextView(requireActivity());
            value.setText(list.get(key));
            value.setPadding(10, 10, 10, 10);
            value.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            value.setGravity(Gravity.END);
            value.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            TextView text = new TextView(requireActivity());
            text.setText(key);
            text.setPadding(10, 10, 10, 10);

            if (i % 2 == 0) {
                text.setBackgroundColor(Color.parseColor("#e5e7ed"));
                value.setBackgroundColor(Color.parseColor("#e5e7ed"));
            } else {
                text.setBackgroundColor(Color.parseColor("#f6f6f6"));
                value.setBackgroundColor(Color.parseColor("#f6f6f6"));
            }
            linearLayout.addView(text);
            linearLayout.addView(value);
            parentLayout.addView(linearLayout);
            i++;
        }
    }

    private void setProgressBarsTopHeroes(String itemSelected, ArrayList<TopHero> heroes) {
        heroList.removeAllViews();

        Log.i("test", heroes.get(0).getClass().getSimpleName());
        for (int i = 0; i < heroes.size(); i++) {
            LinearLayout linearLayout = new LinearLayout(requireActivity());
            heroList.addView(linearLayout);

            LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(100, 100);
            iconParams.setMarginEnd(5);

            RelativeLayout.LayoutParams nameParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            nameParams.setMarginStart(10);
            nameParams.addRule(RelativeLayout.CENTER_VERTICAL);

            RelativeLayout.LayoutParams dataParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            dataParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            dataParams.addRule(RelativeLayout.CENTER_VERTICAL);
            dataParams.setMarginEnd(10);

            ImageView icon = new ImageView(requireActivity());
            icon.setBackgroundResource(getHeroIcon(heroes.get(i).getClass().getSimpleName()));
            GradientDrawable iconBorder = new GradientDrawable();
            iconBorder.setCornerRadius(5);
            iconBorder.setStroke(3, Color.parseColor("#CCCCCC"));
            icon.setImageDrawable(iconBorder);

            RelativeLayout relativeLayout = new RelativeLayout(requireActivity());
            relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            GradientDrawable layoutBackground = new GradientDrawable();
            layoutBackground.setCornerRadius(5);
            layoutBackground.setColor(Color.parseColor("#283655"));
            relativeLayout.setBackground(layoutBackground);
            relativeLayout.setPadding(5, 5, 5, 5);

            TextView name = new TextView(requireActivity());
            name.setAllCaps(true);
            name.setTextColor(Color.WHITE);
            name.setTextSize(20);
            String tempName;

            if (heroes.get(i).getClass().getSimpleName().equals("WreckingBall")) {
                tempName = "Wrecking Ball ";
            } else if (heroes.get(i).getClass().getSimpleName().equals("Dva")) {
                tempName = "D.Va ";
            } else if (heroes.get(i).getClass().getSimpleName().equals("Soldier76")) {
                tempName = "Soldier: 76 ";
            } else {
                tempName = heroes.get(i).getClass().getSimpleName() + " ";
            }
            name.setText(tempName);
            Typeface face = ResourcesCompat.getFont(requireActivity(), R.font.bignoodletoo);
            name.setTypeface(face);

            TextView data = new TextView(requireActivity());
            data.setTextColor(Color.WHITE);
            data.setTextSize(15);

            linearLayout.addView(icon, iconParams);
            linearLayout.addView(relativeLayout);
            ProgressBar progressBar = new ProgressBar(requireActivity(), null, android.R.attr.progressBarStyleHorizontal);
            progressBar.setProgressDrawable(requireActivity().getDrawable(R.drawable.ow_progressbar));

            switch (itemSelected) {
                case TIME_PLAYED:
                    progressBar.setMax(getSeconds(heroes.get(0)));
                    progressBar.setProgress(getSeconds(heroes.get(i)));
                    progressBar.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                    data.setText(heroes.get(i).getTimePlayed());
                    break;
                case GAMES_WON:
                    progressBar.setMax((int) heroes.get(0).getGamesWon());
                    progressBar.setProgress((int) heroes.get(i).getGamesWon());
                    progressBar.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                    data.setText(String.valueOf((int) heroes.get(i).getGamesWon()));
                    break;
                case WEAPON_ACCURACY:
                    progressBar.setMax((int) heroes.get(0).getWeaponAccuracy());
                    progressBar.setProgress((int) heroes.get(i).getWeaponAccuracy());
                    progressBar.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                    String tempData = (int) heroes.get(i).getWeaponAccuracy() + "%";
                    data.setText(tempData);
                    break;
                case ELIMINATIONS_PER_LIFE:
                    progressBar.setMax((int) heroes.get(0).getEliminationsPerLife());
                    progressBar.setProgress((int) heroes.get(i).getEliminationsPerLife());
                    progressBar.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                    data.setText(String.valueOf(heroes.get(i).getEliminationsPerLife()));
                    break;
                case MULTIKILL_BEST:
                    progressBar.setMax((int) heroes.get(0).getMultiKillBest());
                    progressBar.setProgress((int) heroes.get(i).getMultiKillBest());
                    progressBar.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                    data.setText(String.valueOf((int) heroes.get(i).getMultiKillBest()));
                    break;
                case OBJECTIVE_KILLS:
                    progressBar.setMax((int) heroes.get(0).getObjectiveKills());
                    progressBar.setProgress((int) heroes.get(i).getObjectiveKills());
                    progressBar.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                    data.setText(String.valueOf((int) heroes.get(i).getObjectiveKills()));
            }

            setBackgroundColor(progressBar, heroes.get(i).getClass().getSimpleName());

            relativeLayout.addView(progressBar);
            relativeLayout.addView(name, nameParams);
            relativeLayout.addView(data, dataParams);
        }
    }

    private void setBackgroundColor(ProgressBar backgroundColor, String topCharacterName) {
        int progressBar = 0;

        switch (topCharacterName) {
            case "Ana":
                progressBar = Color.parseColor("#9c978a");
                break;
            case "Ashe":
                progressBar = Color.parseColor("#b3a05f");
                break;
            case "Baptiste":
                progressBar = Color.parseColor("#2892a8");
                break;
            case "Bastion":
                progressBar = Color.parseColor("#24f9f8");
                break;
            case "Brigitte":
                progressBar = Color.parseColor("#efb016");
                break;
            case "DVa":
                progressBar = Color.parseColor("#ee4bb5");
                break;
            case "Echo":
                progressBar = Color.parseColor("#89c8ff");
                break;
            case "Doomfist":
                progressBar = Color.parseColor("#762c21");
                break;
            case "Genji":
                progressBar = Color.parseColor("#abe50b");
                break;
            case "Hanzo":
                progressBar = Color.parseColor("#837c46");
                break;
            case "Junkrat":
                progressBar = Color.parseColor("#fbd73a");
                break;
            case "Lúcio":
                progressBar = Color.parseColor("#aaf531");
                break;
            case "Mccree":
                progressBar = Color.parseColor("#c23f46");
                break;
            case "Mei":
                progressBar = Color.parseColor("#87d7f6");
                break;
            case "Mercy":
                progressBar = Color.parseColor("#fcd849");
                break;
            case "Moira":
                progressBar = Color.parseColor("#7112f4");
                break;
            case "Orisa":
                progressBar = Color.parseColor("#468c43");
                break;
            case "Pharah":
                progressBar = Color.parseColor("#3461a4");
                break;
            case "Reaper":
                progressBar = Color.parseColor("#333333");
                break;
            case "Reinhardt":
                progressBar = Color.parseColor("#929da3");
                break;
            case "Roadhog":
                progressBar = Color.parseColor("#54515a");
                break;
            case "Sigma":
                progressBar = Color.parseColor("#33bbaa");
                break;
            case "Soldier76":
                progressBar = Color.parseColor("#525d9b");
                break;
            case "Sombra":
                progressBar = Color.parseColor("#9762ec");
                break;
            case "Symmetra":
                progressBar = Color.parseColor("#3e90b5");
                break;
            case "Torbjörn":
                progressBar = Color.parseColor("#b04a33");
                break;
            case "Tracer":
                progressBar = Color.parseColor("#ffcf35");
                break;
            case "Widowmaker":
                progressBar = Color.parseColor("#af5e9e");
                break;
            case "Winston":
                progressBar = Color.parseColor("#595959");
                break;
            case "WreckingBall":
                progressBar = Color.parseColor("#4a575f");
                break;
            case "Zarya":
                progressBar = Color.parseColor("#ff73c1");
                break;
            case "Zenyatta":
                progressBar = Color.parseColor("#e1c931");
                break;
        }
        backgroundColor.setProgressTintList(ColorStateList.valueOf(progressBar));
    }

    private int getHeroIcon(String topCharacterName) {
        int id = 0;
        switch (topCharacterName) {
            case "Ana":
                id = R.drawable.ana_icon;
                break;
            case "Ashe":
                id = R.drawable.ashe_icon;
                break;
            case "Baptiste":
                id = R.drawable.baptiste_icon;
                break;
            case "Bastion":
                id = R.drawable.bastion_icon;
                break;
            case "Brigitte":
                id = R.drawable.brigitte_icon;
                break;
            case "DVa":
                id = R.drawable.dva_icon;
                break;
            case "Echo":
                id = R.drawable.echo_icon;
                break;
            case "Doomfist":
                id = R.drawable.doomfist_icon;
                break;
            case "Genji":
                id = R.drawable.genji_icon;
                break;
            case "Hanzo":
                id = R.drawable.hanzo_icon;
                break;
            case "Junkrat":
                id = R.drawable.junkrat_icon;
                break;
            case "Lúcio":
                id = R.drawable.lucio_icon;
                break;
            case "Mccree":
                id = R.drawable.mccree_icon;
                break;
            case "Mei":
                id = R.drawable.mei_icon;
                break;
            case "Mercy":
                id = R.drawable.mercy_icon;
                break;
            case "Moira":
                id = R.drawable.moira_icon;
                break;
            case "Orisa":
                id = R.drawable.orisa_icon;
                break;
            case "Pharah":
                id = R.drawable.pharah_icon;
                break;
            case "Reaper":
                id = R.drawable.reaper_icon;
                break;
            case "Reinhardt":
                id = R.drawable.reinhardt_icon;
                break;
            case "Roadhog":
                id = R.drawable.roadhog_icon;
                break;
            case "Sigma":
                id = R.drawable.sigma_icon;
                break;
            case "Soldier76":
                id = R.drawable.soldier_icon;
                break;
            case "Sombra":
                id = R.drawable.sombra_icon;
                break;
            case "Symmetra":
                id = R.drawable.symmetra_icon;
                break;
            case "Torbjörn":
                id = R.drawable.torbjorn_icon;
                break;
            case "Tracer":
                id = R.drawable.tracer_icon;
                break;
            case "Widowmaker":
                id = R.drawable.widow_icon;
                break;
            case "Winston":
                id = R.drawable.winston_icon;
                break;
            case "WreckingBall":
                id = R.drawable.wrecking_ball_icon;
                break;
            case "Zarya":
                id = R.drawable.zarya_icon;
                break;
            case "Zenyatta":
                id = R.drawable.zenyatta_icon;
                break;
        }
        return id;
    }

    private void setTopCharacterImage(String topCharacterName) {
        switch (topCharacterName) {
            case "Ana":
                topCharacter.setImageResource(R.drawable.ana_portrait);
                break;
            case "Ashe":
                topCharacter.setImageResource(R.drawable.ashe_portrait);
                break;
            case "Baptiste":
                topCharacter.setImageResource(R.drawable.baptiste_portrait);
                break;
            case "Bastion":
                topCharacter.setImageResource(R.drawable.bastion_portrait);
                break;
            case "Brigitte":
                topCharacter.setImageResource(R.drawable.brigitte_portrait);
                break;
            case "DVa":
                topCharacter.setImageResource(R.drawable.dva_portrait);
                break;
            case "Echo":
                topCharacter.setImageResource(R.drawable.echo_portrait);
                break;
            case "Doomfist":
                topCharacter.setImageResource(R.drawable.doomfist_portrait);
                break;
            case "Genji":
                topCharacter.setImageResource(R.drawable.genji_portrait);
                break;
            case "Hanzo":
                topCharacter.setImageResource(R.drawable.hanzo_portrait);
                break;
            case "Junkrat":
                topCharacter.setImageResource(R.drawable.junkrat_portrait);
                break;
            case "Lúcio":
                topCharacter.setImageResource(R.drawable.lucio_portrait);
                break;
            case "Mccree":
                topCharacter.setImageResource(R.drawable.mccree_portrait);
                break;
            case "Mei":
                topCharacter.setImageResource(R.drawable.mei_portrait);
                break;
            case "Mercy":
                topCharacter.setImageResource(R.drawable.mercy_portrait);
                break;
            case "Moira":
                topCharacter.setImageResource(R.drawable.moira_portrait);
                break;
            case "Orisa":
                topCharacter.setImageResource(R.drawable.orisa_portrait);
                break;
            case "Pharah":
                topCharacter.setImageResource(R.drawable.pharah_portrait);
                break;
            case "Reaper":
                topCharacter.setImageResource(R.drawable.reaper_portrait);
                break;
            case "Reinhardt":
                topCharacter.setImageResource(R.drawable.reinhardt_portrait);
                break;
            case "Roadhog":
                topCharacter.setImageResource(R.drawable.roadhog_portrait);
                break;
            case "Sigma":
                topCharacter.setImageResource(R.drawable.sigma_portrait);
                break;
            case "Soldier76":
                topCharacter.setImageResource(R.drawable.soldier_portrait);
                break;
            case "Sombra":
                topCharacter.setImageResource(R.drawable.sombra_portrait);
                break;
            case "Symmetra":
                topCharacter.setImageResource(R.drawable.symmetra_portrait);
                break;
            case "Torbjörn":
                topCharacter.setImageResource(R.drawable.torbjorn_portrait);
                break;
            case "Tracer":
                topCharacter.setImageResource(R.drawable.tracer_portrait);
                break;
            case "Widowmaker":
                topCharacter.setImageResource(R.drawable.widow_portrait);
                break;
            case "Winston":
                topCharacter.setImageResource(R.drawable.winston_portrait);
                break;
            case "WreckingBall":
                topCharacter.setImageResource(R.drawable.wrecking_ball_portrait);
                break;
            case "Zarya":
                topCharacter.setImageResource(R.drawable.zarya_portrait);
                break;
            case "Zenyatta":
                topCharacter.setImageResource(R.drawable.zenyatta_portrait);
                break;
        }
    }

    private void sortList(ArrayList<TopHero> topHeroes, String howToSort) {
        switch (howToSort) {
            case TIME_PLAYED:
                topHeroes.sort((hero1, hero2) -> {
                    int secondsHero1 = getSeconds(hero1);
                    int secondsHero2 = getSeconds(hero2);
                    if (secondsHero1 > secondsHero2) {
                        return -1;
                    } else if (secondsHero1 < secondsHero2) {
                        return 1;
                    }
                    return 0;
                });
                break;
            case GAMES_WON:
                topHeroes.sort((hero1, hero2) -> {
                    if (hero1.getGamesWon() > hero2.getGamesWon()) {
                        return -1;
                    } else if (hero1.getGamesWon() < hero2.getGamesWon()) {
                        return 1;
                    }
                    return 0;
                });
                break;
            case WEAPON_ACCURACY:
                topHeroes.sort((hero1, hero2) -> {
                    if (hero1.getWeaponAccuracy() > hero2.getWeaponAccuracy()) {
                        return -1;
                    } else if (hero1.getWeaponAccuracy() < hero2.getWeaponAccuracy()) {
                        return 1;
                    }
                    return 0;
                });
                break;
            case ELIMINATIONS_PER_LIFE:
                topHeroes.sort((hero1, hero2) -> {
                    if (hero1.getEliminationsPerLife() > hero2.getEliminationsPerLife()) {
                        return -1;
                    } else if (hero1.getEliminationsPerLife() < hero2.getEliminationsPerLife()) {
                        return 1;
                    }
                    return 0;
                });
                break;
            case MULTIKILL_BEST:
                topHeroes.sort((hero1, hero2) -> {
                    if (hero1.getMultiKillBest() > hero2.getMultiKillBest()) {
                        return -1;
                    } else if (hero1.getMultiKillBest() < hero2.getMultiKillBest()) {
                        return 1;
                    }
                    return 0;
                });
                break;
            case OBJECTIVE_KILLS:
                topHeroes.sort((hero1, hero2) -> {
                    if (hero1.getObjectiveKills() > hero2.getObjectiveKills()) {
                        return -1;
                    } else if (hero1.getObjectiveKills() < hero2.getObjectiveKills()) {
                        return 1;
                    }
                    return 0;
                });
        }
    }

    private int getSeconds(TopHero hero) {
        int secondsHero1 = 0;
        if (StringUtils.countMatches(hero.getTimePlayed(), ":") == 2) {
            secondsHero1 += Integer.parseInt(hero.getTimePlayed().substring(0, hero.getTimePlayed().indexOf(":"))) * 3600;
            secondsHero1 += Integer.parseInt(hero.getTimePlayed().substring(hero.getTimePlayed().indexOf(":") + 1, hero.getTimePlayed().lastIndexOf(":"))) * 60;
            secondsHero1 += Integer.parseInt(hero.getTimePlayed().substring(hero.getTimePlayed().lastIndexOf(":") + 1));
        } else if (StringUtils.countMatches(hero.getTimePlayed(), ":") == 1) {
            secondsHero1 += Integer.parseInt(hero.getTimePlayed().substring(0, hero.getTimePlayed().indexOf(":"))) * 60;
            secondsHero1 += Integer.parseInt(hero.getTimePlayed().substring(hero.getTimePlayed().lastIndexOf(":") + 1));
        } else {
            secondsHero1 = Integer.parseInt(hero.getTimePlayed());
        }
        return secondsHero1;
    }

    private void setRatingInformation() {
        if (accountInformation.getRating() > 0) {
            for (int i = 0; i < accountInformation.getRatings().size(); i++) {
                if (accountInformation.getRatings().get(i).getRole().equals("tank") && accountInformation.getRatings().get(i).getLevel() > 0) {
                    ratingTank.setText(String.valueOf(accountInformation.getRatings().get(i).getLevel()));
                    downloadRatingIcon(accountInformation.getRatings().get(i).getRoleIcon(), ratingIconTank);
                    downloadRatingIcon(accountInformation.getRatings().get(i).getRankIcon(), ratingRankIconTank);
                } else {
                    if (accountInformation.getRatings().get(i).getLevel() == 0) {
                        ratingIconTank.setVisibility(View.GONE);
                        ratingRankIconTank.setVisibility(View.GONE);
                        ratingTank.setVisibility(View.GONE);
                    }
                }
                if (accountInformation.getRatings().get(i).getRole().equals("damage") && accountInformation.getRatings().get(i).getLevel() > 0) {
                    ratingDamage.setText(String.valueOf(accountInformation.getRatings().get(i).getLevel()));
                    downloadRatingIcon(accountInformation.getRatings().get(i).getRoleIcon(), ratingIconDamage);
                    downloadRatingIcon(accountInformation.getRatings().get(i).getRankIcon(), ratingRankIconDamage);
                } else {
                    if (accountInformation.getRatings().get(i).getLevel() == 0) {
                        ratingIconDamage.setVisibility(View.GONE);
                        ratingRankIconDamage.setVisibility(View.GONE);
                        ratingDamage.setVisibility(View.GONE);
                    }
                }
                if (accountInformation.getRatings().get(i).getRole().equals("support") && accountInformation.getRatings().get(i).getLevel() > 0) {
                    ratingSupport.setText(String.valueOf(accountInformation.getRatings().get(i).getLevel()));
                    downloadRatingIcon(accountInformation.getRatings().get(i).getRoleIcon(), ratingIconSupport);
                    downloadRatingIcon(accountInformation.getRatings().get(i).getRankIcon(), ratingRankIconSupport);
                } else {
                    if (accountInformation.getRatings().get(i).getLevel() == 0) {
                        ratingIconSupport.setVisibility(View.GONE);
                        ratingRankIconSupport.setVisibility(View.GONE);
                        ratingSupport.setVisibility(View.GONE);
                    }
                }
            }

        } else {
            ratingIconSupport.setVisibility(View.GONE);
            ratingRankIconSupport.setVisibility(View.GONE);
            ratingSupport.setVisibility(View.GONE);
            ratingIconDamage.setVisibility(View.GONE);
            ratingRankIconDamage.setVisibility(View.GONE);
            ratingDamage.setVisibility(View.GONE);
            ratingIconTank.setVisibility(View.GONE);
            ratingRankIconTank.setVisibility(View.GONE);
            ratingTank.setVisibility(View.GONE);
            View view = requireActivity().findViewById(R.id.view2);
            view.setVisibility(View.GONE);
        }
    }

    private void setGamesWon() {
        String tempGames = accountInformation.getGamesWon() + " games won";
        gamesWon.setText(tempGames);
    }

    private void setName() {
        if (accountInformation.getName().contains("#")) {
            int hashtag = accountInformation.getName().indexOf("#");
            String tempName = accountInformation.getName().substring(0, hashtag) + " ";
            name.setText(tempName);
        } else {
            String accountInfo = accountInformation.getName() + " ";
            name.setText(accountInfo);
        }
        level.setText(String.valueOf(accountInformation.getLevel()));
    }

    private void downloadAvatar() {
        Picasso.get().load(accountInformation.getIcon()).into(avatar);
    }

    /*private void downloadEndorsementIcon() {
        StringRequest stringRequest = new StringRequest(accountInformation.getEndorsementIcon(), string -> {
            try {
                SVG endorsement_icon = SVG.getFromString(string);
                PictureDrawable pd = new PictureDrawable(endorsement_icon.renderToPicture());
                endorsementIcon.setImageDrawable(pd);
            } catch (Exception e) {
                Log.e("SVG Exception", e.toString());
            }

        },
                e -> showNoConnectionMessage(0));
        RequestQueueSingleton.Companion.getInstance(requireActivity()).addToRequestQueue(stringRequest);
    }*/

    private void downloadRatingIcon(String url, ImageView imageView) {
        Picasso.get().load(url).into(imageView);
    }

    private void downloadLevelIcon() {
        Picasso.get().load(accountInformation.getLevelIcon()).into(levelIcon);
        Picasso.get().load(accountInformation.getPrestigeIcon()).into(prestigeIcon);
    }

    /**
     * Show no connection message.
     *
     * @param responseCode the response code
     */
    private void showNoConnectionMessage(final int responseCode) {
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        loadingCircle.setVisibility(View.GONE);
        URLConstants.loading = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity(), R.style.DialogTransparent);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(10, 20, 10, 20);

        TextView titleText = new TextView(requireActivity());

        titleText.setTextSize(20);
        titleText.setGravity(Gravity.CENTER_HORIZONTAL);
        titleText.setPadding(0, 20, 0, 20);
        titleText.setLayoutParams(layoutParams);
        titleText.setTextColor(Color.WHITE);

        TextView messageText = new TextView(requireActivity());

        messageText.setGravity(Gravity.CENTER_HORIZONTAL);
        messageText.setPadding(0, 0, 0, 20);
        messageText.setLayoutParams(layoutParams);
        messageText.setTextColor(Color.WHITE);

        Button button = new Button(requireActivity());

        button.setTextSize(20);
        button.setTextColor(Color.WHITE);
        button.setGravity(Gravity.CENTER);
        button.setWidth(200);
        button.setHeight(100);
        button.setLayoutParams(buttonParams);
        button.setBackground(requireActivity().getDrawable(R.drawable.buttonstyle));

        Button button2 = new Button(requireActivity());

        button2.setTextSize(20);
        button2.setTextColor(Color.WHITE);
        button2.setGravity(Gravity.CENTER);
        button2.setWidth(200);
        button2.setHeight(100);
        button2.setLayoutParams(buttonParams);
        button2.setBackground(requireActivity().getDrawable(R.drawable.buttonstyle));

        LinearLayout buttonLayout = new LinearLayout(requireActivity());
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonLayout.setGravity(Gravity.CENTER);
        buttonLayout.addView(button);

        if (responseCode == 404) {
            titleText.setText(ErrorMessages.ACCOUNT_NOT_FOUND);
            messageText.setText(ErrorMessages.OW_ACCOUNT_NOT_FOUND);
            button.setText(ErrorMessages.OK);
            button2.setText(ErrorMessages.BACK);
        } else {
            titleText.setText(ErrorMessages.NO_INTERNET);
            messageText.setText(ErrorMessages.TURN_ON_CONNECTION_MESSAGE);
            button.setText(ErrorMessages.RETRY);
            button2.setText(ErrorMessages.BACK);
            buttonLayout.addView(button2);
        }

        final AlertDialog dialog = builder.show();
        Objects.requireNonNull(dialog.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        LinearLayout linearLayout = new LinearLayout(requireActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setPadding(20, 20, 20, 20);

        linearLayout.addView(titleText);
        linearLayout.addView(messageText);
        linearLayout.addView(buttonLayout);

        dialog.addContentView(linearLayout, layoutParams);

        if (responseCode == 404) {
            dialog.setOnCancelListener(dialog1 -> getParentFragmentManager().beginTransaction().remove(this).commit());
        } else {
            dialog.setOnCancelListener(dialog1 -> downloadAccountInformation());
        }

        button.setOnClickListener(v -> dialog.cancel());
        button2.setOnClickListener(v -> {
            dialog.dismiss();
            getParentFragmentManager().beginTransaction().remove(this).commit();
        });
    }
}