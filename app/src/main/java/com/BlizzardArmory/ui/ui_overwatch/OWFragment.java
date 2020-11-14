package com.BlizzardArmory.ui.ui_overwatch;

import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
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

    private RecyclerView topHeroRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                favorite.setVisibility(View.GONE);
                favorite.setImageResource(R.drawable.ic_star_border_black_24dp);
                favorite.setTag(R.drawable.ic_star_border_black_24dp);
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
        return inflater.inflate(R.layout.ow_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        topHeroRecycler = view.findViewById(R.id.top_hero_recycler);

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
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_dropdown_item_1line, sortHeroList) {

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
                    topHeroRecycler.setAdapter(new OWProgressAdapter(topHeroesCompetitive, requireActivity(), sortHeroList[position]));
                } else {
                    sortList(topHeroesQuickPlay, sortHeroList[position]);
                    topHeroRecycler.setAdapter(new OWProgressAdapter(topHeroesQuickPlay, requireActivity(), sortHeroList[position]));
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
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_dropdown_item_1line, sortCareerHeroes) {

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

    private void setTopCharacterImage(String topCharacterName) {
        Glide.with(this).load(URLConstants.getOWPortraitImage(topCharacterName.toLowerCase())).into(topCharacter);
    }

    private void sortList(ArrayList<TopHero> topHeroes, String howToSort) {
        switch (howToSort) {
            case TIME_PLAYED:
                Collections.sort(topHeroes, (hero1, hero2) -> {
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
                Collections.sort(topHeroes, (hero1, hero2) -> {
                    if (hero1.getGamesWon() > hero2.getGamesWon()) {
                        return -1;
                    } else if (hero1.getGamesWon() < hero2.getGamesWon()) {
                        return 1;
                    }
                    return 0;
                });
                break;
            case WEAPON_ACCURACY:
                Collections.sort(topHeroes, (hero1, hero2) -> {
                    if (hero1.getWeaponAccuracy() > hero2.getWeaponAccuracy()) {
                        return -1;
                    } else if (hero1.getWeaponAccuracy() < hero2.getWeaponAccuracy()) {
                        return 1;
                    }
                    return 0;
                });
                break;
            case ELIMINATIONS_PER_LIFE:
                Collections.sort(topHeroes, (hero1, hero2) -> {
                    if (hero1.getEliminationsPerLife() > hero2.getEliminationsPerLife()) {
                        return -1;
                    } else if (hero1.getEliminationsPerLife() < hero2.getEliminationsPerLife()) {
                        return 1;
                    }
                    return 0;
                });
                break;
            case MULTIKILL_BEST:
                Collections.sort(topHeroes, (hero1, hero2) -> {
                    if (hero1.getMultiKillBest() > hero2.getMultiKillBest()) {
                        return -1;
                    } else if (hero1.getMultiKillBest() < hero2.getMultiKillBest()) {
                        return 1;
                    }
                    return 0;
                });
                break;
            case OBJECTIVE_KILLS:
                Collections.sort(topHeroes, (hero1, hero2) -> {
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
        Glide.with(this).load(accountInformation.getIcon()).into(avatar);
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
        Glide.with(this).load(url).into(imageView);
    }

    private void downloadLevelIcon() {
        Glide.with(this).load(accountInformation.getLevelIcon()).into(levelIcon);
        Glide.with(this).load(accountInformation.getPrestigeIcon()).into(prestigeIcon);
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
        button.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.buttonstyle));

        Button button2 = new Button(requireActivity());

        button2.setTextSize(20);
        button2.setTextColor(Color.WHITE);
        button2.setGravity(Gravity.CENTER);
        button2.setWidth(200);
        button2.setHeight(100);
        button2.setLayoutParams(buttonParams);
        button2.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.buttonstyle));

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