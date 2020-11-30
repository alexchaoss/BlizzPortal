package com.BlizzardArmory.ui.ui_diablo.account

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.ErrorMessages
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BattlenetConstants
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.databinding.D3FragmentBinding
import com.BlizzardArmory.model.diablo.account.AccountInformation
import com.BlizzardArmory.model.diablo.favorite.D3FavoriteProfile
import com.BlizzardArmory.model.diablo.favorite.D3FavoriteProfiles
import com.BlizzardArmory.ui.GamesActivity
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.ui.news.NewsPageFragment
import com.BlizzardArmory.ui.ui_diablo.favorites.D3FavoriteFragment
import com.BlizzardArmory.ui.ui_diablo.leaderboard.D3LeaderboardFragment
import com.BlizzardArmory.ui.ui_diablo.navigation.D3CharacterNav
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.events.D3CharacterEvent
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback
import java.util.*

class D3Fragment : Fragment() {
    private var prefs: SharedPreferences? = null
    private var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null
    private var battlenetOAuth2Params: BattlenetOAuth2Params? = null
    private var battleTag: String? = ""
    private var selectedRegion: String? = ""
    private lateinit var errorMessages: ErrorMessages

    private var _binding: D3FragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        addOnBackPressCallback(activity as GamesActivity)
        _binding = D3FragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        errorMessages = ErrorMessages(this.resources)
        battleTag = arguments?.getString("battletag")
        selectedRegion = arguments?.getString("region")
        prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        battlenetOAuth2Params = arguments?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
        battlenetOAuth2Helper = BattlenetOAuth2Helper(prefs, battlenetOAuth2Params!!)
        GamesActivity.favorite!!.visibility = View.VISIBLE
        GamesActivity.favorite!!.setImageResource(R.drawable.ic_star_border_black_24dp)
        GamesActivity.favorite!!.tag = R.drawable.ic_star_border_black_24dp
        downloadAccountInformation()
    }

    private fun downloadAccountInformation() {
        URLConstants.loading = true
        binding.loadingCircle.visibility = View.VISIBLE
        val call: Call<AccountInformation> = GamesActivity.client!!.getD3Profile(battleTag, MainActivity.locale, selectedRegion?.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<AccountInformation> {
            override fun onResponse(call: Call<AccountInformation>, response: retrofit2.Response<AccountInformation>) {
                if (response.isSuccessful) {
                    accountInformation = response.body()
                    manageFavorite(accountInformation!!)
                    sortHeroes()
                    val paragon = accountInformation?.paragonLevel.toString() +
                            " | " +
                            "<font color=#b00000>" +
                            accountInformation?.paragonLevelHardcore +
                            "</font>"
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        binding.paragonLevel.text = Html.fromHtml(paragon, Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        binding.paragonLevel.text = Html.fromHtml(paragon)
                    }
                    binding.eliteKills.text = accountInformation?.kills?.elites.toString()
                    binding.lifetimeKills.text = accountInformation?.kills?.monsters.toString()
                    setProgression()
                    setTimePlayed()
                    binding.characterFrameRecycler.apply {
                        adapter = D3CharacterFrameAdapter(accountInformation?.heroes!!)
                    }
                    if (accountInformation?.fallenHeroes != null) {
                        binding.characterDeadRecycler.apply {
                            adapter = D3DeadCharacterAdapter(accountInformation?.fallenHeroes!!)
                        }
                    }
                    requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                    URLConstants.loading = false
                    binding.loadingCircle.visibility = View.GONE
                } else if (response.code() >= 400) {
                    showNoConnectionMessage(response.code())
                }
            }

            override fun onFailure(call: Call<AccountInformation>, t: Throwable) {
                Log.e("Error", t.message, t)
                showNoConnectionMessage(0)
            }
        })
    }

    private fun manageFavorite(accountInformation: AccountInformation) {
        var favoriteProfiles = D3FavoriteProfiles()
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = GsonBuilder().create()
        val favoriteCharactersString = prefs.getString("d3-favorites", "DEFAULT")
        if (favoriteCharactersString != null && favoriteCharactersString != "DEFAULT") {
            favoriteProfiles = gson.fromJson(favoriteCharactersString, D3FavoriteProfiles::class.java)
            var indexOfProfile = -1
            var indexTemp = 0
            for (profile in favoriteProfiles.profiles) {
                if (hasCharacter(profile)) {
                    indexOfProfile = indexTemp
                    GamesActivity.favorite!!.setImageResource(R.drawable.ic_star_black_24dp)
                    GamesActivity.favorite!!.tag = R.drawable.ic_star_black_24dp
                    favoriteProfiles.profiles[indexOfProfile] = D3FavoriteProfile(accountInformation, selectedRegion!!, battleTag!!)
                    prefs.edit().putString("d3-favorites", gson.toJson(favoriteProfiles)).apply()
                    break
                } else {
                    addToFavorite(favoriteProfiles, accountInformation, gson, prefs)
                }
                indexTemp++
            }
            deleteFavorite(favoriteProfiles, accountInformation, indexOfProfile, gson, prefs)
        } else {
            addToFavorite(favoriteProfiles, accountInformation, gson, prefs)
        }
    }

    private fun hasCharacter(profile: D3FavoriteProfile): Boolean {
        return battleTag == profile.battletag && selectedRegion == profile.region
    }

    private fun deleteFavorite(profiles: D3FavoriteProfiles, accountInformation: AccountInformation, indexOfProfile: Int, gson: Gson, prefs: SharedPreferences) {
        var favoriteCharactersString: String
        if (GamesActivity.favorite!!.tag == R.drawable.ic_star_black_24dp && indexOfProfile != -1) {
            GamesActivity.favorite!!.setOnClickListener {
                GamesActivity.favorite!!.setImageResource(R.drawable.ic_star_border_black_24dp)
                GamesActivity.favorite!!.tag = R.drawable.ic_star_border_black_24dp
                profiles.profiles.removeAt(indexOfProfile)
                prefs.edit().putString("d3-favorites", gson.toJson(profiles)).apply()
                Toast.makeText(requireActivity(), "Profile removed from favorites", Toast.LENGTH_SHORT).show()
                addToFavorite(profiles, accountInformation, gson, prefs)

            }
        }
    }

    private fun addToFavorite(profiles: D3FavoriteProfiles, accountInformation: AccountInformation, gson: Gson, prefs: SharedPreferences) {
        var favoriteCharactersString: String
        GamesActivity.favorite!!.setOnClickListener {
            var containsProfile = false
            var indexOfProfile = 0
            for (profile in profiles.profiles) {
                if (hasCharacter(profile)) {
                    containsProfile = true
                    break
                }
                indexOfProfile++
            }
            if (!containsProfile) {
                GamesActivity.favorite!!.setImageResource(R.drawable.ic_star_black_24dp)
                GamesActivity.favorite!!.tag = R.drawable.ic_star_black_24dp
                profiles.profiles.add(D3FavoriteProfile(accountInformation, selectedRegion!!, battleTag!!))
                prefs.edit().putString("d3-favorites", gson.toJson(profiles)).apply()
                Toast.makeText(requireActivity(), "Profile added to favorites", Toast.LENGTH_SHORT).show()
                deleteFavorite(profiles, accountInformation, indexOfProfile, gson, prefs)
            }
        }
    }

    private fun setTimePlayed() {
        Log.i("prog", "barb: " + accountInformation?.timePlayed?.barbarian.toString()
                + ", dh: " + accountInformation?.timePlayed?.demonHunter.toString()
                + ", crusader: " + accountInformation?.timePlayed?.crusader.toString()
                + ", necro: " + accountInformation?.timePlayed?.necromancer.toString()
                + ", wd: " + accountInformation?.timePlayed?.witchDoctor.toString()
                + ", wiz: " + accountInformation?.timePlayed?.wizard.toString()
                + ", monk: " + accountInformation?.timePlayed?.monk.toString())
        binding.barbProgress.progress = (accountInformation?.timePlayed?.barbarian?.times(100))?.toInt()!!
        binding.crusaderProgress.progress = (accountInformation?.timePlayed?.crusader?.times(100))?.toInt()!!
        binding.dhProgress.progress = (accountInformation?.timePlayed?.demonHunter?.times(100))?.toInt()!!
        binding.monkProgress.progress = (accountInformation?.timePlayed?.monk?.times(100))?.toInt()!!
        binding.necroProgress.progress = (accountInformation?.timePlayed?.necromancer?.times(100))?.toInt()!!
        binding.wdProgress.progress = (accountInformation?.timePlayed?.witchDoctor?.times(100))?.toInt()!!
        binding.wizardProgress.progress = (accountInformation?.timePlayed?.wizard?.times(100))?.toInt()!!
    }


    private fun sortHeroes() {
        accountInformation?.heroes = accountInformation!!.heroes.sortedBy { it.lastUpdated }.reversed()
    }

    private fun setProgression() {
        if (accountInformation!!.progression.act1) {
            Glide.with(this).load(URLConstants.getD3Asset("act1_done")).into(binding.progAct1)
        } else {
            Glide.with(this).load(URLConstants.getD3Asset("act1_not_done")).into(binding.progAct1)
        }
        if (accountInformation!!.progression.act2) {
            Glide.with(this).load(URLConstants.getD3Asset("act2_done")).into(binding.progAct2)
        } else {
            Glide.with(this).load(URLConstants.getD3Asset("act2_not_done")).into(binding.progAct2)
        }
        if (accountInformation!!.progression.act3) {
            Glide.with(this).load(URLConstants.getD3Asset("act3_done")).into(binding.progAct3)
        } else {
            Glide.with(this).load(URLConstants.getD3Asset("act3_not_done")).into(binding.progAct3)
        }
        if (accountInformation!!.progression.act4) {
            Glide.with(this).load(URLConstants.getD3Asset("act4_done")).into(binding.progAct4)
        } else {
            Glide.with(this).load(URLConstants.getD3Asset("act4_not_done")).into(binding.progAct4)
        }
        if (accountInformation!!.progression.act5) {
            Glide.with(this).load(URLConstants.getD3Asset("act5_done")).into(binding.progAct5)
        } else {
            Glide.with(this).load(URLConstants.getD3Asset("act5_not_done")).into(binding.progAct5)
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun d3CharacterEventReceived(d3CharacterEvent: D3CharacterEvent) {
        displayFragment(d3CharacterEvent.hero.id)
    }

    private fun displayFragment(characterId: Long) {
        val bundle = Bundle()
        bundle.putString("battletag", battleTag)
        bundle.putLong("id", characterId)
        bundle.putString("region", selectedRegion)
        val d3CharacterNav = D3CharacterNav()
        d3CharacterNav.arguments = bundle
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
        fragmentTransaction.add(R.id.fragment, d3CharacterNav, "d3nav")
        fragmentTransaction.addToBackStack("d3_nav").commit()
        parentFragmentManager.executePendingTransactions()
    }

    private fun getErrorMessage(responseCode: Int): String {
        return when (responseCode) {
            404 -> {
                errorMessages.LOGIN_TO_UPDATE
            }
            503, 403 -> {
                errorMessages.UNEXPECTED
            }
            500 -> {
                errorMessages.BLIZZ_SERVERS_DOWN
            }
            else -> {
                errorMessages.TURN_ON_CONNECTION_MESSAGE
            }
        }

    }

    private fun getErrorTitle(responseCode: Int): String {
        return when (responseCode) {
            404 -> {
                errorMessages.INFORMATION_OUTDATED
            }
            503, 403 -> {
                errorMessages.UNAVAILABLE
            }
            500 -> {
                errorMessages.SERVERS_ERROR
            }
            else -> {
                errorMessages.NO_INTERNET
            }
        }
    }

    private fun showNoConnectionMessage(responseCode: Int) {
        binding.loadingCircle.visibility = View.GONE
        URLConstants.loading = false

        val dialog = DialogPrompt(requireActivity())

        dialog.addTitle(getErrorTitle(responseCode), 20f, "title")
                .addMessage(getErrorMessage(responseCode), 18f, "message")
                .addSideBySideButtons(errorMessages.RETRY, 18f, errorMessages.BACK, 18f,
                        {
                            dialog.cancel()
                            downloadAccountInformation()
                            binding.loadingCircle.visibility = View.VISIBLE
                            URLConstants.loading = true
                        },
                        {
                            dialog.cancel()
                            GamesActivity.hideFavoriteButton()
                            parentFragmentManager.popBackStack()
                            NewsPageFragment.addOnBackPressCallback(activity as GamesActivity)
                        },
                        "retry", "back").show()
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        activity?.supportFragmentManager?.beginTransaction()?.detach(this)?.attach(this)?.commit()
    }

    companion object {
        private var accountInformation: AccountInformation? = null
        fun addOnBackPressCallback(activity: GamesActivity) {
            activity.onBackPressedDispatcher.addCallback {
                if (!URLConstants.loading) {
                    GamesActivity.hideFavoriteButton()
                    when {
                        activity.supportFragmentManager.findFragmentByTag("d3leaderboard") != null -> {
                            D3LeaderboardFragment.addOnBackPressCallback(activity)
                            activity.supportFragmentManager.popBackStack()
                        }
                        activity.supportFragmentManager.findFragmentByTag("d3favorites") != null -> {
                            D3FavoriteFragment.addOnBackPressCallback(activity)
                            activity.supportFragmentManager.popBackStack()
                        }
                        else -> {
                            NewsPageFragment.addOnBackPressCallback(activity)
                            activity.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        }
                    }
                }
            }
        }
    }
}