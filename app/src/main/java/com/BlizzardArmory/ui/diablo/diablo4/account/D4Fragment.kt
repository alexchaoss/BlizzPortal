package com.BlizzardArmory.ui.diablo.diablo4.account

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.D4FragmentBinding
import com.BlizzardArmory.model.diablo.diablo4.account.Characters
import com.BlizzardArmory.network.ErrorMessages
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.oauth.OauthFlowStarter
import com.BlizzardArmory.ui.diablo.diablo4.character.CharacterFragment
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.news.page.NewsPageFragment
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.events.D4CharacterEvent
import com.BlizzardArmory.util.state.FavoriteState
import com.BlizzardArmory.util.state.FragmentTag
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class D4Fragment : Fragment() {
    private var prefs: SharedPreferences? = null
    private var battleTag: String? = ""
    private var selectedRegion: String? = ""
    private lateinit var errorMessages: ErrorMessages

    private var _binding: D4FragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: D4ViewModel by activityViewModels()

    private lateinit var navigationActivity: NavigationActivity

    private var dialog: DialogPrompt? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addOnBackPressCallback(activity as NavigationActivity)
        _binding = D4FragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        requireActivity().viewModelStore.clear()
        navigationActivity.toggleFavoriteButton(FavoriteState.Hidden)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationActivity = (requireActivity() as NavigationActivity)
        errorMessages = ErrorMessages(this.resources)
        battleTag = arguments?.getString("battletag")
        selectedRegion = arguments?.getString("region")
        prefs = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        navigationActivity.toggleFavoriteButton(FavoriteState.Shown)
        setObservers()
        viewModel.downloadAccountInformation(battleTag!!)
    }

    private fun setObservers() {
        viewModel.getProfile().observe(viewLifecycleOwner) {
            binding.characters.apply {
                adapter = D4HeroAdapter(it.characters)
            }
            binding.bossesKilled.text = "${binding.bossesKilled.text}  ${it.bossesKilled}"
            binding.dungeons.text = "${binding.dungeons.text}  ${it.dungeonsCompleted}"
            binding.playesrKilled.text = "${binding.playesrKilled.text}  ${it.playersKilled}"
        }

        viewModel.getErrorCode().observe(viewLifecycleOwner) {
            showNoConnectionMessage(it)
        }
    }

    private fun manageFavorite(accountInformation: com.BlizzardArmory.model.diablo.diablo3.account.AccountInformation) {
        var favoriteProfiles = com.BlizzardArmory.model.diablo.diablo3.favorite.D3FavoriteProfiles()
        val prefs = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        val gson = GsonBuilder().create()
        val favoriteCharactersString = prefs.getString("d4-favorites", "DEFAULT")
        if (favoriteCharactersString != null && favoriteCharactersString != "DEFAULT") {
            favoriteProfiles =
                gson.fromJson(favoriteCharactersString, com.BlizzardArmory.model.diablo.diablo3.favorite.D3FavoriteProfiles::class.java)
            var indexOfProfile = -1
            var indexTemp = 0
            for (profile in favoriteProfiles.profiles) {
                if (hasCharacter(profile)) {
                    indexOfProfile = indexTemp
                    navigationActivity.toggleFavoriteButton(FavoriteState.Full)
                    favoriteProfiles.profiles[indexOfProfile] =
                        com.BlizzardArmory.model.diablo.diablo3.favorite.D3FavoriteProfile(accountInformation, selectedRegion!!, battleTag!!)
                    prefs.edit().putString("d4-favorites", gson.toJson(favoriteProfiles)).apply()
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

    private fun hasCharacter(profile: com.BlizzardArmory.model.diablo.diablo3.favorite.D3FavoriteProfile): Boolean {
        return battleTag == profile.battletag && selectedRegion == profile.region
    }

    private fun deleteFavorite(
        profiles: com.BlizzardArmory.model.diablo.diablo3.favorite.D3FavoriteProfiles,
        accountInformation: com.BlizzardArmory.model.diablo.diablo3.account.AccountInformation,
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
        profiles: com.BlizzardArmory.model.diablo.diablo3.favorite.D3FavoriteProfiles,
        accountInformation: com.BlizzardArmory.model.diablo.diablo3.account.AccountInformation,
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
                    com.BlizzardArmory.model.diablo.diablo3.favorite.D3FavoriteProfile(
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
        // binding.loadingCircle.visibility = View.GONE
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
                            viewModel.downloadAccountInformation(battleTag!!)
                            //binding.loadingCircle.visibility = View.VISIBLE
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

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun d4CharacterEventReceived(d4CharacterEvent: D4CharacterEvent) {
        displayFragment(d4CharacterEvent.hero)
    }

    private fun displayFragment(hero: Characters) {
        val bundle = Bundle()
        val d4CharacterFragment = CharacterFragment()
        bundle.putString("battletag", battleTag)
        bundle.putString("id", hero.id)
        d4CharacterFragment.arguments = bundle
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
        fragmentTransaction.add(R.id.fragment, d4CharacterFragment, FragmentTag.NAVFRAGMENT.name)
        fragmentTransaction.addToBackStack("d3_nav").commit()
        parentFragmentManager.executePendingTransactions()
    }

    companion object {
        fun addOnBackPressCallback(activity: NavigationActivity) {
            activity.onBackPressedDispatcher.addCallback {
                when {
                    else -> {
                        NewsPageFragment.addOnBackPressCallback(activity)
                        activity.supportFragmentManager.popBackStack()
                    }
                }
            }
        }
    }
}