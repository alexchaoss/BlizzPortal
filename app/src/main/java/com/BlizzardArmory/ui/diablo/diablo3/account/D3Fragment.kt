package com.BlizzardArmory.ui.diablo.diablo3.account

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.D3FragmentBinding
import com.BlizzardArmory.model.diablo.diablo3.account.AccountInformation
import com.BlizzardArmory.model.diablo.diablo3.account.Hero
import com.BlizzardArmory.model.diablo.diablo3.favorite.D3FavoriteProfile
import com.BlizzardArmory.model.diablo.diablo3.favorite.D3FavoriteProfiles
import com.BlizzardArmory.network.ErrorMessages
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.network.oauth.OauthFlowStarter
import com.BlizzardArmory.ui.diablo.diablo3.characterfragments.navigation.D3CharacterNav
import com.BlizzardArmory.ui.diablo.diablo3.favorites.D3FavoriteFragment
import com.BlizzardArmory.ui.diablo.diablo3.leaderboard.D3LeaderboardFragment
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.news.page.NewsPageFragment
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.events.D3CharacterEvent
import com.BlizzardArmory.util.state.FavoriteState
import com.BlizzardArmory.util.state.FragmentTag
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class D3Fragment : Fragment() {
    private var prefs: SharedPreferences? = null
    private var battleTag: String? = ""
    private var selectedRegion: String? = ""
    private lateinit var errorMessages: ErrorMessages

    private var _binding: D3FragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: D3ViewModel by activityViewModels()

    private lateinit var navigationActivity: NavigationActivity

    private var dialog: DialogPrompt? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addOnBackPressCallback(activity as NavigationActivity)
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
        requireActivity().viewModelStore.clear()
        navigationActivity.toggleFavoriteButton(FavoriteState.Hidden)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationActivity = (requireActivity() as NavigationActivity)
        errorMessages = ErrorMessages(this.resources)
        battleTag = arguments?.getString("battletag")
        selectedRegion = arguments?.getString("region")
        prefs = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        viewModel.getBnetParams().value = activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
        navigationActivity.toggleFavoriteButton(FavoriteState.Shown)
        setObservers()
    }

    private fun setObservers() {
        viewModel.getBnetParams().observe(viewLifecycleOwner) {
            viewModel.battlenetOAuth2Helper = BattlenetOAuth2Helper(it)
            binding.loadingCircle.visibility = View.VISIBLE
            viewModel.downloadAccountInformation(battleTag!!, selectedRegion!!)
        }

        viewModel.getProfile().observe(viewLifecycleOwner) {
            setHeaderData()
            setProgression()
            setTimePlayed()
            binding.characterFrameRecycler.apply {
                adapter =
                    D3CharacterFrameAdapter(viewModel.getProfile().value?.heroes!!.sortedByDescending { hero -> hero.lastUpdated })
            }
            if (viewModel.getProfile().value?.fallenHeroes != null) {
                binding.characterDeadRecycler.apply {
                    adapter =
                        D3DeadCharacterAdapter(viewModel.getProfile().value?.fallenHeroes!!.sortedBy { hero -> hero.death.time })
                }
            }
            manageFavorite(it)
            binding.loadingCircle.visibility = View.GONE
        }

        viewModel.getErrorCode().observe(viewLifecycleOwner) {
            showNoConnectionMessage(it)
        }
    }

    private fun setHeaderData() {
        val paragon = viewModel.getProfile().value?.paragonLevel.toString() +
                " | " +
                "<font color=#b00000>" +
                viewModel.getProfile().value?.paragonLevelHardcore +
                "</font>"
        binding.paragonLevel.text = HtmlCompat.fromHtml(paragon, HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.eliteKills.text = viewModel.getProfile().value?.kills?.elites.toString()
        binding.lifetimeKills.text = viewModel.getProfile().value?.kills?.monsters.toString()
    }

    private fun manageFavorite(accountInformation: AccountInformation) {
        var favoriteProfiles = D3FavoriteProfiles()
        val prefs = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        val gson = GsonBuilder().create()
        val favoriteCharactersString = prefs.getString("d3-favorites", "DEFAULT")
        if (favoriteCharactersString != null && favoriteCharactersString != "DEFAULT") {
            favoriteProfiles =
                gson.fromJson(favoriteCharactersString, D3FavoriteProfiles::class.java)
            var indexOfProfile = -1
            var indexTemp = 0
            for (profile in favoriteProfiles.profiles) {
                if (hasCharacter(profile)) {
                    indexOfProfile = indexTemp
                    navigationActivity.toggleFavoriteButton(FavoriteState.Full)
                    favoriteProfiles.profiles[indexOfProfile] =
                        D3FavoriteProfile(accountInformation, selectedRegion!!, battleTag!!)
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

    private fun deleteFavorite(
        profiles: D3FavoriteProfiles,
        accountInformation: AccountInformation,
        indexOfProfile: Int,
        gson: Gson,
        prefs: SharedPreferences
    ) {
        if (navigationActivity.favorite!!.tag == R.drawable.ic_star_black_24dp && indexOfProfile != -1) {
            navigationActivity.favorite!!.setOnClickListener {
                navigationActivity.toggleFavoriteButton(FavoriteState.Shown)
                profiles.profiles.removeAt(indexOfProfile)
                prefs.edit().putString("d3-favorites", gson.toJson(profiles)).apply()
                Snackbar.make(binding.root, "Profile removed from favorites", Snackbar.LENGTH_SHORT)
                    .show()
                addToFavorite(profiles, accountInformation, gson, prefs)
            }
        }
    }

    private fun addToFavorite(
        profiles: D3FavoriteProfiles,
        accountInformation: AccountInformation,
        gson: Gson,
        prefs: SharedPreferences
    ) {
        navigationActivity.favorite!!.setOnClickListener {
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
                navigationActivity.toggleFavoriteButton(FavoriteState.Full)
                profiles.profiles.add(
                    D3FavoriteProfile(
                        accountInformation,
                        selectedRegion!!,
                        battleTag!!
                    )
                )
                prefs.edit().putString("d3-favorites", gson.toJson(profiles)).apply()
                Snackbar.make(binding.root, "Profile added to favorites", Snackbar.LENGTH_SHORT)
                    .show()
                deleteFavorite(profiles, accountInformation, indexOfProfile, gson, prefs)
            }
        }
    }

    private fun setTimePlayed() {
        binding.barbProgress.progress =
            (viewModel.getProfile().value?.timePlayed?.barbarian?.times(100))?.toInt()!!
        binding.crusaderProgress.progress =
            (viewModel.getProfile().value?.timePlayed?.crusader?.times(100))?.toInt()!!
        binding.dhProgress.progress =
            (viewModel.getProfile().value?.timePlayed?.demonHunter?.times(100))?.toInt()!!
        binding.monkProgress.progress =
            (viewModel.getProfile().value?.timePlayed?.monk?.times(100))?.toInt()!!
        binding.necroProgress.progress =
            (viewModel.getProfile().value?.timePlayed?.necromancer?.times(100))?.toInt()!!
        binding.wdProgress.progress =
            (viewModel.getProfile().value?.timePlayed?.witchDoctor?.times(100))?.toInt()!!
        binding.wizardProgress.progress =
            (viewModel.getProfile().value?.timePlayed?.wizard?.times(100))?.toInt()!!
    }


    private fun setProgression() {
        if (viewModel.getProfile().value!!.progression.act1) {
            Glide.with(this).load(NetworkUtils.getD3Asset("act1_done")).into(binding.progAct1)
        } else {
            Glide.with(this).load(NetworkUtils.getD3Asset("act1_not_done")).into(binding.progAct1)
        }
        if (viewModel.getProfile().value!!.progression.act2) {
            Glide.with(this).load(NetworkUtils.getD3Asset("act2_done")).into(binding.progAct2)
        } else {
            Glide.with(this).load(NetworkUtils.getD3Asset("act2_not_done")).into(binding.progAct2)
        }
        if (viewModel.getProfile().value!!.progression.act3) {
            Glide.with(this).load(NetworkUtils.getD3Asset("act3_done")).into(binding.progAct3)
        } else {
            Glide.with(this).load(NetworkUtils.getD3Asset("act3_not_done")).into(binding.progAct3)
        }
        if (viewModel.getProfile().value!!.progression.act4) {
            Glide.with(this).load(NetworkUtils.getD3Asset("act4_done")).into(binding.progAct4)
        } else {
            Glide.with(this).load(NetworkUtils.getD3Asset("act4_not_done")).into(binding.progAct4)
        }
        if (viewModel.getProfile().value!!.progression.act5) {
            Glide.with(this).load(NetworkUtils.getD3Asset("act5_done")).into(binding.progAct5)
        } else {
            Glide.with(this).load(NetworkUtils.getD3Asset("act5_not_done")).into(binding.progAct5)
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun d3CharacterEventReceived(d3CharacterEvent: D3CharacterEvent) {
        displayFragment(d3CharacterEvent.hero)
    }

    private fun displayFragment(hero: Hero) {
        val bundle = Bundle()
        bundle.putString("battletag", battleTag)
        bundle.putLong("id", hero.id)
        bundle.putString("region", selectedRegion)
        bundle.putString("class", hero.classSlug)
        bundle.putInt("gender", hero.gender)
        val d3CharacterNav = D3CharacterNav()
        d3CharacterNav.arguments = bundle
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
        fragmentTransaction.add(R.id.fragment, d3CharacterNav, FragmentTag.NAVFRAGMENT.name)
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
        NetworkUtils.loading = false
        if (dialog == null) {
            dialog = DialogPrompt(requireActivity())
            dialog?.setCancellable(false)

            if (responseCode == 401) {
                OauthFlowStarter.lastOpenedFragmentNeedingOAuth = FragmentTag.D3FRAGMENT.name
                OauthFlowStarter.bundle = arguments
                OauthFlowStarter.startOauthFlow(
                    viewModel.getBnetParams().value!!,
                    navigationActivity,
                    View.GONE
                )
            } else {
                dialog!!.addTitle(getErrorTitle(responseCode), 20f, "title")
                    .addMessage(getErrorMessage(responseCode), 18f, "message")
                    .addButtons(
                        dialog!!.Button(errorMessages.RETRY, 18f, {
                            dialog!!.dismiss()
                            viewModel.downloadAccountInformation(battleTag!!, selectedRegion!!)
                            binding.loadingCircle.visibility = View.VISIBLE
                            NetworkUtils.loading = true
                            dialog = null
                        }, "retry"), dialog!!.Button(
                            errorMessages.BACK, 18f,

                            {
                                dialog!!.dismiss()
                                dialog = null
                                parentFragmentManager.popBackStack()
                                NewsPageFragment.addOnBackPressCallback(activity as NavigationActivity)
                            }, "back"
                        )
                    ).show()
            }
        }
    }

    companion object {
        fun addOnBackPressCallback(activity: NavigationActivity) {
            activity.onBackPressedDispatcher.addCallback {
                when {
                    activity.supportFragmentManager.findFragmentByTag(FragmentTag.D3LEADERBOARD.name) != null -> {
                        D3LeaderboardFragment.addOnBackPressCallback(activity)
                        activity.supportFragmentManager.popBackStack()
                    }
                    activity.supportFragmentManager.findFragmentByTag(FragmentTag.D3FAVORITES.name) != null -> {
                        D3FavoriteFragment.addOnBackPressCallback(activity)
                        activity.supportFragmentManager.popBackStack()
                    }
                    else -> {
                        NewsPageFragment.addOnBackPressCallback(activity)
                        activity.supportFragmentManager.popBackStack()
                    }
                }
            }
        }
    }
}