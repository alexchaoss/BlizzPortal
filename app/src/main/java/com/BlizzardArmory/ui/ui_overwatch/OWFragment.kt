package com.BlizzardArmory.ui.ui_overwatch

import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.ErrorMessages
import com.BlizzardArmory.connection.NetworkServices
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.URLConstants.getOWPortraitImage
import com.BlizzardArmory.connection.URLConstants.getOWProfile
import com.BlizzardArmory.databinding.OwFragmentBinding
import com.BlizzardArmory.model.overwatch.Profile
import com.BlizzardArmory.model.overwatch.favorite.FavoriteProfile
import com.BlizzardArmory.model.overwatch.favorite.FavoriteProfiles
import com.BlizzardArmory.model.overwatch.heroes.Hero
import com.BlizzardArmory.model.overwatch.topheroes.TopHero
import com.BlizzardArmory.ui.GamesActivity
import com.BlizzardArmory.ui.news.NewsPageFragment
import com.BlizzardArmory.ui.ui_overwatch.favorites.OWFavoritesFragment
import com.BlizzardArmory.util.DialogPrompt
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.sc2_fragment.*
import org.apache.commons.lang3.StringUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.collections.ArrayList

/**
 * The type Ow activity.
 */
class OWFragment : Fragment() {
    private var errorMessages: ErrorMessages? = null
    private lateinit var networkServices: NetworkServices
    private var username: String? = null
    private var platform: String? = null
    private var switchCompQuickRadius: GradientDrawable? = null
    private var accountInformation: Profile? = null
    private var topHeroesQuickPlay = arrayListOf<TopHero>()
    private var topHeroesCompetitive = arrayListOf<TopHero>()
    private var careerQuickPlay = arrayListOf<Hero>()
    private var careerCompetitive = arrayListOf<Hero>()

    //private ImageView endorsementIcon;
    //private TextView endorsement;
    private val TIME_PLAYED = "Time Played"
    private val GAMES_WON = "Games Won"
    private val WEAPON_ACCURACY = "Weapon Accuracy"
    private val ELIMINATIONS_PER_LIFE = "Eliminations per Life"
    private val MULTIKILL_BEST = "Multikill - Best"
    private val OBJECTIVE_KILLS = "Objective Kills"
    private val sortHeroList = arrayOf(TIME_PLAYED, GAMES_WON, WEAPON_ACCURACY, ELIMINATIONS_PER_LIFE, MULTIKILL_BEST, OBJECTIVE_KILLS)
    private val sortCareerHeroes = arrayListOf<String>()
    private var comp = false
    private val privateProfile = "This profile is private and the information unavailable"

    private var _binding: OwFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        addOnBackPressCallback(activity as GamesActivity)
        _binding = OwFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        errorMessages = ErrorMessages(this.resources)
        binding.loadingCircle.visibility = View.VISIBLE
        /*endorsementIcon = findViewById(R.id.endorsement_icon);
        endorsementIcon.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        endorsement = findViewById(R.id.endorsement_level);*/
        GamesActivity.favorite!!.visibility = View.VISIBLE
        GamesActivity.favorite!!.setImageResource(R.drawable.ic_star_border_black_24dp)
        GamesActivity.favorite!!.tag = R.drawable.ic_star_border_black_24dp
        val switchCompQuickBorder = GradientDrawable()
        switchCompQuickBorder.cornerRadius = 5f
        switchCompQuickBorder.setStroke(3, Color.parseColor("#FFFFFF"))
        binding.quickComp.background = switchCompQuickBorder
        switchCompQuickRadius = GradientDrawable()
        switchCompQuickRadius!!.cornerRadius = 5f
        switchCompQuickRadius!!.setColor(Color.parseColor("#FFFFFF"))
        binding.quickplay.background = switchCompQuickRadius
        username = this.arguments?.getString("username")
        platform = this.arguments?.getString("platform")
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder().baseUrl("https://ow-api.com/v1/stats/").addConverterFactory(GsonConverterFactory.create(gson)).build()
        networkServices = retrofit.create(NetworkServices::class.java)
        downloadAccountInformation()
    }

    private fun downloadAccountInformation() {
        //String testURL = "https://ow-api.com/v1/stats/xbl/global/Hcpeful/complete";
        Log.i("URL", getOWProfile(platform!!, username!!))
        URLConstants.loading = true
        val call = networkServices.getOWProfile(getOWProfile(username!!, platform!!))
        call.enqueue(object : Callback<Profile?> {
            override fun onResponse(call: Call<Profile?>, response: Response<Profile?>) {
                if (response.isSuccessful) {
                    try {
                        accountInformation = response.body()
                        manageFavorite()
                        downloadAvatar()
                        setName()
                        downloadLevelIcon()
                        setGamesWon()
                        setRatingInformation()
                        //downloadEndorsementIcon(requestQueue);
                        //endorsement.setText(String.valueOf(accountInformation.getEndorsement()));
                        setTopHeroesLists()
                        setCareerLists()
                        setSpinnerCareerList(careerQuickPlay)
                        if (topHeroesQuickPlay.size > 0) {
                            setTopCharacterImage(topHeroesQuickPlay[0].javaClass.simpleName)
                        }
                        setSpinnerTopHeroes(binding.spinner)
                        setSpinnerCareer(binding.spinnerCareer)
                        binding.quickplay.setOnClickListener {
                            if (comp) {
                                comp = false
                                binding.quickplay.background = switchCompQuickRadius
                                binding.quickplay.setTextColor(Color.parseColor("#000000"))
                                binding.competitive.setTextColor(Color.parseColor("#FFFFFF"))
                                binding.competitive.setBackgroundColor(0)
                                sortList(topHeroesQuickPlay, sortHeroList[0])
                                if (topHeroesQuickPlay.size > 0) {
                                    setTopCharacterImage(topHeroesQuickPlay[0].javaClass.simpleName)
                                }
                                setSpinnerTopHeroes(binding.spinner)
                                setSpinnerCareerList(careerQuickPlay)
                                setSpinnerCareer(binding.spinnerCareer)
                            }
                        }
                        binding.competitive.setOnClickListener {
                            if (!comp) {
                                comp = true
                                binding.competitive.background = switchCompQuickRadius
                                binding.competitive.setTextColor(Color.parseColor("#000000"))
                                binding.quickplay.setBackgroundColor(0)
                                binding.quickplay.setTextColor(Color.parseColor("#FFFFFF"))
                                sortList(topHeroesCompetitive, sortHeroList[0])
                                if (topHeroesCompetitive.size > 0) {
                                    setTopCharacterImage(topHeroesCompetitive[0].javaClass.simpleName)
                                }
                                setSpinnerTopHeroes(binding.spinner)
                                setSpinnerCareerList(careerCompetitive)
                                setSpinnerCareer(binding.spinnerCareer)
                            }
                        }
                        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                        loadingCircle!!.visibility = View.GONE
                        URLConstants.loading = false
                    } catch (e: Exception) {
                        binding.gamesWon.text = privateProfile
                        binding.gamesWon.textSize = 18f
                        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                        loadingCircle!!.visibility = View.GONE
                        URLConstants.loading = false
                        GamesActivity.favorite!!.setOnClickListener { Toast.makeText(requireActivity(), "Can't add private profile to favorites", Toast.LENGTH_SHORT).show() }
                        Log.e("Error", "Exception", e)
                    }
                } else if (response.code() >= 400) {
                    showNoConnectionMessage(response.code())
                }
            }

            override fun onFailure(call: Call<Profile?>, t: Throwable) {
                Log.e("Error", "trace", t)
                showNoConnectionMessage(0)
            }
        })
    }

    private fun manageFavorite() {
        var profiles = FavoriteProfiles()
        val prefs = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        val gson = GsonBuilder().create()
        var index = -1
        var indexTemp = 0
        val favorites = prefs.getString("ow-favorites", "DEFAULT")!!
        if (favorites != "DEFAULT" && favorites != "{\"profile_list\":[]}") {
            profiles = gson.fromJson(favorites, FavoriteProfiles::class.java)
            for (profile in profiles.profiles) {
                if (profile.username == username && profile.platform == platform) {
                    GamesActivity.favorite!!.setImageResource(R.drawable.ic_star_black_24dp)
                    GamesActivity.favorite!!.tag = R.drawable.ic_star_black_24dp
                    index = indexTemp
                    profiles.profiles[index] = FavoriteProfile(platform!!, username!!, accountInformation!!)
                    prefs.edit().putString("ow-favorites", gson.toJson(profiles)).apply()
                } else {
                    addToFavorites(profiles, prefs, gson, index)
                }
                indexTemp++
            }
            removeFavorite(profiles, prefs, gson, index)
        } else {
            addToFavorites(profiles, prefs, gson, index)
        }
    }

    private fun removeFavorite(profiles: FavoriteProfiles, prefs: SharedPreferences, gson: Gson, index: Int) {
        if (GamesActivity.favorite!!.tag as Int == R.drawable.ic_star_black_24dp && index != -1) {
            GamesActivity.favorite!!.setOnClickListener {
                GamesActivity.favorite!!.setImageResource(R.drawable.ic_star_border_black_24dp)
                GamesActivity.favorite!!.tag = R.drawable.ic_star_black_24dp
                profiles.profiles.removeAt(index)
                prefs.edit().putString("ow-favorites", gson.toJson(profiles)).apply()
                addToFavorites(profiles, prefs, gson, index)
                Toast.makeText(requireActivity(), "Profile removed from favorites", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addToFavorites(profiles: FavoriteProfiles, prefs: SharedPreferences, gson: Gson, index: Int) {
        val containsProfiles = AtomicBoolean(false)
        GamesActivity.favorite!!.setOnClickListener {
            for (profile in profiles.profiles) {
                if (profile.username == username && profile.platform == platform) {
                    containsProfiles.set(true)
                    break
                }
            }
            if (!containsProfiles.get()) {
                GamesActivity.favorite!!.setImageResource(R.drawable.ic_star_black_24dp)
                GamesActivity.favorite!!.tag = R.drawable.ic_star_black_24dp
                profiles.profiles.add(FavoriteProfile(platform!!, username!!, accountInformation!!))
                prefs.edit().putString("ow-favorites", gson.toJson(profiles)).apply()
                Toast.makeText(requireActivity(), "Profile added to favorites", Toast.LENGTH_SHORT).show()
            }
            removeFavorite(profiles, prefs, gson, index)
        }
    }

    private fun setCareerLists() {
        careerQuickPlay = accountInformation?.quickPlayStats?.careerStats?.getFullHeroList()!!
        careerCompetitive = accountInformation?.competitiveStats?.careerStats?.getFullHeroList()!!

        Log.i("TEST", careerQuickPlay.size.toString() + " + " + careerCompetitive.size.toString() )
    }

    private fun setSpinnerCareerList(career: ArrayList<Hero>) {
        sortCareerHeroes.clear()
        sortCareerHeroes.addAll(career.map { it.getName() })
    }

    private fun setTopHeroesLists() {
        topHeroesQuickPlay = accountInformation?.quickPlayStats?.topHeroes?.getFullHeroList()!!
        topHeroesCompetitive = accountInformation?.competitiveStats?.topHeroes?.getFullHeroList()!!

        sortList(topHeroesCompetitive, sortHeroList[0])
        sortList(topHeroesQuickPlay, sortHeroList[0])
    }

    private fun setSpinnerTopHeroes(spinner: Spinner?) {
        val arrayAdapter: ArrayAdapter<String> = object : ArrayAdapter<String>(requireActivity(), android.R.layout.simple_dropdown_item_1line, sortHeroList) {
            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent)
                try {
                    val tv = view as TextView
                    tv.isAllCaps = true
                    tv.setBackgroundColor(Color.WHITE)
                    tv.textSize = 15f
                    tv.gravity = Gravity.CENTER
                } catch (e: Exception) {
                    Log.e("Dropdown", "error", e)
                }
                return view
            }
        }
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner!!.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                (view as TextView).setTextColor(Color.parseColor("#CCCCCC"))
                view.textSize = 15f
                view.gravity = Gravity.CENTER_VERTICAL
                if (comp) {
                    sortList(topHeroesCompetitive, sortHeroList[position])
                    binding.topHeroRecycler.adapter = OWProgressAdapter(topHeroesCompetitive, requireActivity(), sortHeroList[position])
                } else {
                    sortList(topHeroesQuickPlay, sortHeroList[position])
                    binding.topHeroRecycler.adapter = OWProgressAdapter(topHeroesQuickPlay, requireActivity(), sortHeroList[position])
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                (parent.getChildAt(0) as TextView).gravity = Gravity.CENTER
                (parent.getChildAt(0) as TextView).setTextColor(0)
            }
        }
    }

    private fun setSpinnerCareer(spinner: Spinner?) {
        val arrayAdapter: ArrayAdapter<String> = object : ArrayAdapter<String>(requireActivity(), android.R.layout.simple_dropdown_item_1line, sortCareerHeroes) {
            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                tv.isAllCaps = true
                tv.setBackgroundColor(Color.WHITE)
                tv.textSize = 15f
                tv.gravity = Gravity.CENTER
                return view
            }
        }
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner!!.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                (view as TextView).setTextColor(Color.parseColor("#CCCCCC"))
                view.textSize = 15f
                view.gravity = Gravity.CENTER_VERTICAL
                if (comp) {
                    setCareerStats(position, careerCompetitive)
                } else {
                    setCareerStats(position, careerQuickPlay)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                (parent.getChildAt(0) as TextView).gravity = Gravity.CENTER
                (parent.getChildAt(0) as TextView).setTextColor(0)
            }
        }
    }

    private fun setCareerStats(position: Int, career: ArrayList<Hero>) {
        binding.best.removeViews(1, binding.best.childCount - 1)
        binding.assist.removeViews(1, binding.assist.childCount - 1)
        binding.game.removeViews(1, binding.game.childCount - 1)
        binding.average.removeViews(1, binding.average.childCount - 1)
        binding.combat.removeViews(1, binding.combat.childCount - 1)
        binding.misc.removeViews(1, binding.misc.childCount - 1)
        binding.match.removeViews(1, binding.match.childCount - 1)
        try {
            setSpecificCareerList(career[position].best?.bestList!!, binding.best, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            setSpecificCareerList(career[position].assists?.assists!!, binding.assist, 5)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            setSpecificCareerList(career[position].average?.average!!, binding.average, 5)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            setSpecificCareerList(career[position].game?.game!!, binding.game, 5)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            setSpecificCareerList(career[position].miscellaneous!!.misc, binding.misc, 5)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            setSpecificCareerList(career[position].matchAwards!!.match, binding.match, 5)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            setSpecificCareerList(career[position].combat?.combat!!, binding.combat, 5)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setSpecificCareerList(list: HashMap<String, String>, parentLayout: LinearLayout?, marginStart: Int) {
        val scale = requireActivity().resources.displayMetrics.density
        for ((i, key) in list.keys.withIndex()) {
            val linearLayout = LinearLayout(requireActivity())
            linearLayout.orientation = LinearLayout.HORIZONTAL
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins((marginStart * scale + 0.5f).toInt(), 0, 0, 0)
            linearLayout.layoutParams = layoutParams
            val value = TextView(requireActivity())
            value.text = list[key]
            value.setPadding(10, 10, 10, 10)
            value.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
            value.gravity = Gravity.END
            value.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            val text = TextView(requireActivity())
            text.text = key
            text.setPadding(10, 10, 10, 10)
            if (i % 2 == 0) {
                text.setBackgroundColor(Color.parseColor("#e5e7ed"))
                value.setBackgroundColor(Color.parseColor("#e5e7ed"))
            } else {
                text.setBackgroundColor(Color.parseColor("#f6f6f6"))
                value.setBackgroundColor(Color.parseColor("#f6f6f6"))
            }
            linearLayout.addView(text)
            linearLayout.addView(value)
            parentLayout!!.addView(linearLayout)
        }
    }

    private fun setTopCharacterImage(topCharacterName: String) {
        Glide.with(this).load(getOWPortraitImage(topCharacterName.toLowerCase(Locale.ROOT))).into(binding.topCharacter)
    }

    private fun sortList(topHeroes: ArrayList<TopHero>?, howToSort: String) {
        when (howToSort) {
            TIME_PLAYED -> topHeroes?.sortByDescending { getSeconds(it) }
            GAMES_WON -> topHeroes?.sortByDescending { it.gamesWon }
            WEAPON_ACCURACY -> topHeroes?.sortByDescending { it.weaponAccuracy }
            ELIMINATIONS_PER_LIFE -> topHeroes?.sortByDescending { it.eliminationsPerLife }
            MULTIKILL_BEST -> topHeroes?.sortByDescending { it.multiKillBest }
            OBJECTIVE_KILLS -> topHeroes?.sortByDescending { it.objectiveKills }
        }
    }

    private fun getSeconds(hero: TopHero): Int {
        var secondsHero1 = 0
        when {
            StringUtils.countMatches(hero.timePlayed, ":") == 2 -> {
                secondsHero1 += hero.timePlayed!!.substring(0, hero.timePlayed!!.indexOf(":")).toInt() * 3600
                secondsHero1 += hero.timePlayed!!.substring(hero.timePlayed!!.indexOf(":") + 1, hero.timePlayed!!.lastIndexOf(":")).toInt() * 60
                secondsHero1 += hero.timePlayed!!.substring(hero.timePlayed!!.lastIndexOf(":") + 1).toInt()
            }
            StringUtils.countMatches(hero.timePlayed, ":") == 1 -> {
                secondsHero1 += hero.timePlayed!!.substring(0, hero.timePlayed!!.indexOf(":")).toInt() * 60
                secondsHero1 += hero.timePlayed!!.substring(hero.timePlayed!!.lastIndexOf(":") + 1).toInt()
            }
            else -> {
                secondsHero1 = hero.timePlayed!!.toInt()
            }
        }
        return secondsHero1
    }

    private fun setRatingInformation() {
        if (accountInformation!!.rating > 0) {
            for (i in accountInformation!!.ratings.indices) {
                if (accountInformation!!.ratings[i].role == "tank" && accountInformation!!.ratings[i].level > 0) {
                    binding.ratingTank.text = accountInformation!!.ratings[i].level.toString()
                    downloadRatingIcon(accountInformation!!.ratings[i].roleIcon, binding.ratingIconTank)
                    downloadRatingIcon(accountInformation!!.ratings[i].rankIcon, binding.ratingIconRankTank)
                } else {
                    if (accountInformation!!.ratings[i].level == 0) {
                        binding.ratingIconTank.visibility = View.GONE
                        binding.ratingIconRankTank.visibility = View.GONE
                        binding.ratingTank.visibility = View.GONE
                    }
                }
                if (accountInformation!!.ratings[i].role == "damage" && accountInformation!!.ratings[i].level > 0) {
                    binding.ratingDamage.text = accountInformation!!.ratings[i].level.toString()
                    downloadRatingIcon(accountInformation!!.ratings[i].roleIcon, binding.ratingIconDamage)
                    downloadRatingIcon(accountInformation!!.ratings[i].rankIcon, binding.ratingIconRankDamage)
                } else {
                    if (accountInformation!!.ratings[i].level == 0) {
                        binding.ratingIconDamage.visibility = View.GONE
                        binding.ratingIconRankDamage.visibility = View.GONE
                        binding.ratingDamage.visibility = View.GONE
                    }
                }
                if (accountInformation!!.ratings[i].role == "support" && accountInformation!!.ratings[i].level > 0) {
                    binding.ratingSupport.text = accountInformation!!.ratings[i].level.toString()
                    downloadRatingIcon(accountInformation!!.ratings[i].roleIcon, binding.ratingIconSupport)
                    downloadRatingIcon(accountInformation!!.ratings[i].rankIcon, binding.ratingIconRankSupport)
                } else {
                    if (accountInformation!!.ratings[i].level == 0) {
                        binding.ratingIconSupport.visibility = View.GONE
                        binding.ratingIconRankSupport.visibility = View.GONE
                        binding.ratingSupport.visibility = View.GONE
                    }
                }
            }
        } else {
            binding.ratingIconSupport.visibility = View.GONE
            binding.ratingIconRankSupport.visibility = View.GONE
            binding.ratingSupport.visibility = View.GONE
            binding.ratingIconDamage.visibility = View.GONE
            binding.ratingIconRankDamage.visibility = View.GONE
            binding.ratingDamage.visibility = View.GONE
            binding.ratingIconTank.visibility = View.GONE
            binding.ratingIconRankTank.visibility = View.GONE
            binding.ratingTank.visibility = View.GONE
            val view = requireActivity().findViewById<View>(R.id.view2)
            view.visibility = View.GONE
        }
    }

    private fun setGamesWon() {
        val tempGames = accountInformation!!.gamesWon.toString() + " games won"
        binding.gamesWon.text = tempGames
    }

    private fun setName() {
        if (accountInformation!!.name.contains("#")) {
            val hashtag = accountInformation!!.name.indexOf("#")
            val tempName = accountInformation!!.name.substring(0, hashtag) + " "
            name!!.text = tempName
        } else {
            val accountInfo = accountInformation!!.name + " "
            name!!.text = accountInfo
        }
        binding.level.text = accountInformation!!.level.toString()
    }

    private fun downloadAvatar() {
        Glide.with(this).load(accountInformation!!.icon).into(avatar!!)
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
    private fun downloadRatingIcon(url: String, imageView: ImageView?) {
        Glide.with(this).load(url).into(imageView!!)
    }

    private fun downloadLevelIcon() {
        Glide.with(this).load(accountInformation!!.levelIcon).into(binding.levelIcon)
        Glide.with(this).load(accountInformation!!.prestigeIcon).into(binding.prestigeIcon)
    }

    private fun getErrorMessage(responseCode: Int): String {
        return when (responseCode) {
            404 -> {
                errorMessages!!.OW_ACCOUNT_NOT_FOUND
            }
            else -> {
                errorMessages!!.TURN_ON_CONNECTION_MESSAGE
            }
        }

    }

    private fun getErrorTitle(responseCode: Int): String {
        return when (responseCode) {
            404 -> {
                errorMessages!!.ACCOUNT_NOT_FOUND
            }
            else -> {
                errorMessages!!.NO_INTERNET
            }
        }
    }

    private fun showNoConnectionMessage(responseCode: Int) {
        loadingCircle!!.visibility = View.GONE
        URLConstants.loading = false

        val dialog = DialogPrompt(requireActivity())

        dialog.addTitle(getErrorTitle(responseCode), 20f, "title")
                .addMessage(getErrorMessage(responseCode), 18f, "message")
                .addSideBySideButtons(errorMessages!!.RETRY, 18f, errorMessages!!.BACK, 18f,
                        {
                            dialog.cancel()
                            downloadAccountInformation()
                            loadingCircle!!.visibility = View.VISIBLE
                            URLConstants.loading = true
                        },
                        {
                            dialog.cancel()
                            GamesActivity.hideFavoriteButton()
                            parentFragmentManager.beginTransaction().remove(this).commit()
                            NewsPageFragment.addOnBackPressCallback(activity as GamesActivity)
                        }, "retry", "back").show()
    }

    companion object{
        fun addOnBackPressCallback(activity: GamesActivity){
            activity.onBackPressedDispatcher.addCallback{
                if(!URLConstants.loading) {
                    GamesActivity.hideFavoriteButton()
                    if (activity.supportFragmentManager.findFragmentByTag("owfavorites") != null) {
                        OWFavoritesFragment.addOnBackPressCallback(activity)
                        activity.supportFragmentManager.popBackStack()
                    } else {
                        NewsPageFragment.addOnBackPressCallback(activity)
                        activity.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    }
                }
            }
        }
    }
}