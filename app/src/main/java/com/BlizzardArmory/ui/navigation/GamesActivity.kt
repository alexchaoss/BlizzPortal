package com.BlizzardArmory.ui.navigation

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.GamesActivityBinding
import com.BlizzardArmory.model.UserInformation
import com.BlizzardArmory.model.news.UserNews
import com.BlizzardArmory.network.ErrorMessages
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.ui.diablo.account.D3Fragment
import com.BlizzardArmory.ui.diablo.favorites.D3FavoriteFragment
import com.BlizzardArmory.ui.diablo.leaderboard.D3LeaderboardFragment
import com.BlizzardArmory.ui.main.MainActivity
import com.BlizzardArmory.ui.news.NewsListFragment
import com.BlizzardArmory.ui.overwatch.OWPlatformChoiceDialog
import com.BlizzardArmory.ui.overwatch.favorites.OWFavoritesFragment
import com.BlizzardArmory.ui.settings.SettingsFragment
import com.BlizzardArmory.ui.starcraft.SC2Fragment
import com.BlizzardArmory.ui.warcraft.account.AccountFragment
import com.BlizzardArmory.ui.warcraft.favorites.WoWFavoritesFragment
import com.BlizzardArmory.ui.warcraft.navigation.WoWNavFragment
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.events.FilterNewsEvent
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

class GamesActivity : LocalizationActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toggle: ActionBarDrawerToggle
    private var searchText: SpannableString? = null
    private var wowFavoritesText: SpannableString? = null
    private var d3FavoritesText: SpannableString? = null
    private var owFavoritesText: SpannableString? = null
    private var d3LeaderboardText: SpannableString? = null
    private var prefs: SharedPreferences? = null
    private val gson = GsonBuilder().create()

    private var characterClicked: String = ""
    private var characterRealm: String = ""
    private var selectedRegion: String = ""

    private lateinit var errorMessage: ErrorMessages

    private lateinit var binding: GamesActivityBinding
    private val viewModel: GamesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GamesActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            handleUncaughtException(thread, throwable)
        }

        setOberservers()

        favorite = binding.topBar.favorite
        errorMessage = ErrorMessages(this.resources)
        binding.drawerLayout.setScrimColor(Color.TRANSPARENT)
        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        viewModel.getBnetParams().value = this.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)

        openFirstTimeNewsDialog()
        getUserNewsPreferences()
        setUserNews()
        openNewsFragment()

        setNavigation()

        binding.settings.setOnClickListener {
            val fragment = SettingsFragment()
            favorite!!.visibility = View.GONE
            supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "settingsfragment").addToBackStack("settings").commit()
            supportFragmentManager.executePendingTransactions()
            binding.drawerLayout.closeDrawers()
        }
    }

    private fun setNavigation() {
        binding.navView.setNavigationItemSelectedListener(this)
        binding.navView.itemIconTintList = null
        setSupportActionBar(binding.topBar.toolbarMain)

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.topBar.toolbarMain, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = ""
        makeTitlesTransparent()
    }

    private fun openNewsFragment() {
        val newsListFragment = NewsListFragment()
        supportFragmentManager.beginTransaction().replace(R.id.news_fragment, newsListFragment, "news_fragment").commit()
        supportFragmentManager.executePendingTransactions()
    }

    private fun getUserNewsPreferences() {
        val userNewsPrefs = prefs?.getString("user_news", "DEFAULT")
        if (userNewsPrefs == "DEFAULT") {
            userNews = UserNews()
            prefs?.edit()?.putString("user_news", gson.toJson(userNews))?.apply()
        } else {
            userNews = gson.fromJson(userNewsPrefs, UserNews::class.java)
        }
    }

    private fun openFirstTimeNewsDialog() {
        if (!prefs?.contains("news_pulled")!!) {
            val dialog = DialogPrompt(this)
            dialog.addTitle("New Feature!", 20F)
                    .addMessage("Pull from the right side to select which news you want to see here!", 18F)
                    .addButton("Close", 16F, { dialog.dismiss() }).show()
            prefs?.edit()?.putString("news_pulled", "done")?.apply()
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private fun setOberservers() {
        viewModel.getBnetParams().observe(this, {
            viewModel.battlenetOAuth2Helper = BattlenetOAuth2Helper(it)
            viewModel.getRealms()
            viewModel.downloadUserInfo()
        })

        viewModel.getUserInformation().observe(this, {
            userInformation = it
            if (binding.drawerLayout.findViewById<TextView>(R.id.battletag) != null) {
                binding.topBar.barTitle.text = userInformation?.battleTag
                binding.drawerLayout.findViewById<TextView>(R.id.battletag).text = userInformation?.battleTag
            }
        })

        viewModel.getErrorCode().observe(this, {
            callErrorAlertDialog(it)
        })

        viewModel.getMedia().observe(this, {
            callWoWCharacterFragment(characterClicked, characterRealm, selectedRegion)
        })
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun localeSelectedReceived(localeSelectedEvent: LocaleSelectedEvent) {
        viewModel.getRealms()
        when (localeSelectedEvent.locale) {
            "en_US" -> setLanguage("en")
            "es_ES" -> setLanguage("es")
            "fr_FR" -> setLanguage("fr")
            "ru_RU" -> setLanguage("ru")
            "de_DE" -> setLanguage("de")
            "pt_BR" -> setLanguage("pt")
            "it_IT" -> setLanguage("it")
            "ko_KR" -> setLanguage("ko")
            "zh_CN" -> setLanguage("zh", "CN")
            "zh_TW" -> setLanguage("zh", "TW")
        }
    }

    private fun handleUncaughtException(thread: Thread?, e: Throwable) {
        Log.e("Crash Prevented", e.message!!, e)
        FirebaseCrashlytics.getInstance().log(e.message!!)
    }

    private fun setUserNews() {
        binding.blizzardSwtich.isChecked = userNews?.blizzNews!!
        binding.wowSwtich.isChecked = userNews?.wowNews!!
        binding.d3Swtich.isChecked = userNews?.d3News!!
        binding.sc2Swtich.isChecked = userNews?.sc2News!!
        binding.owSwtich.isChecked = userNews?.owNews!!
        binding.hsSwtich.isChecked = userNews?.hsNews!!
        binding.hotsSwtich.isChecked = userNews?.hotsNews!!

        binding.blizzardSwtich.setOnClickListener {
            userNews?.blizzNews = !userNews?.blizzNews!!
            updateUserNews()
        }
        binding.wowSwtich.setOnClickListener {
            userNews?.wowNews = !userNews?.wowNews!!
            updateUserNews()
        }
        binding.d3Swtich.setOnClickListener {
            userNews?.d3News = !userNews?.d3News!!
            updateUserNews()
        }
        binding.sc2Swtich.setOnClickListener {
            userNews?.sc2News = !userNews?.sc2News!!
            updateUserNews()
        }
        binding.owSwtich.setOnClickListener {
            userNews?.owNews = !userNews?.owNews!!
            updateUserNews()
        }
        binding.hsSwtich.setOnClickListener {
            userNews?.hsNews = !userNews?.hsNews!!
            updateUserNews()
        }
        binding.hotsSwtich.setOnClickListener {
            userNews?.hotsNews = !userNews?.hotsNews!!
            updateUserNews()
        }
    }

    private fun updateUserNews() {
        prefs?.edit()?.putString("user_news", gson.toJson(userNews))?.apply()
        EventBus.getDefault().post(FilterNewsEvent(true))
    }

    private fun makeTitlesTransparent() {
        searchText = SpannableString(binding.navView.menu.findItem(R.id.wow_search).title)
        searchText!!.setSpan(ForegroundColorSpan(Color.TRANSPARENT), 0, searchText!!.length, 0)
        binding.navView.menu.findItem(R.id.wow_search).title = searchText

        wowFavoritesText = SpannableString(binding.navView.menu.findItem(R.id.wow_favorite).title)
        wowFavoritesText!!.setSpan(ForegroundColorSpan(Color.TRANSPARENT), 0, wowFavoritesText!!.length, 0)
        binding.navView.menu.findItem(R.id.wow_favorite).title = wowFavoritesText

        d3FavoritesText = SpannableString(binding.navView.menu.findItem(R.id.d3_favorite).title)
        d3FavoritesText!!.setSpan(ForegroundColorSpan(Color.TRANSPARENT), 0, d3FavoritesText!!.length, 0)
        binding.navView.menu.findItem(R.id.d3_favorite).title = d3FavoritesText

        owFavoritesText = SpannableString(binding.navView.menu.findItem(R.id.ow_favorite).title)
        owFavoritesText!!.setSpan(ForegroundColorSpan(Color.TRANSPARENT), 0, owFavoritesText!!.length, 0)
        binding.navView.menu.findItem(R.id.ow_favorite).title = owFavoritesText

        d3LeaderboardText = SpannableString(binding.navView.menu.findItem(R.id.d3_leaderboard).title)
        d3LeaderboardText!!.setSpan(ForegroundColorSpan(Color.TRANSPARENT), 0, d3LeaderboardText!!.length, 0)
        binding.navView.menu.findItem(R.id.d3_leaderboard).title = d3LeaderboardText
    }

    private fun getErrorMessage(responseCode: Int): String {
        return when (responseCode) {
            in 201..499 -> {
                errorMessage.UNEXPECTED
            }
            500 -> {
                errorMessage.BLIZZ_SERVERS_DOWN
            }
            else -> {
                errorMessage.TURN_ON_CONNECTION_MESSAGE
            }
        }
    }

    private fun getErrorTitle(responseCode: Int): String {
        return when (responseCode) {
            in 201..499 -> {
                errorMessage.UNAVAILABLE
            }
            500 -> {
                errorMessage.SERVERS_ERROR
            }
            else -> {
                errorMessage.NO_INTERNET
            }
        }
    }

    private fun callErrorAlertDialog(responseCode: Int) {
        val dialog = DialogPrompt(this)

        if (responseCode == 404 || responseCode == 403 || responseCode == 500) {
            dialog.setOnCancelListener { finish() }
        } else {
            dialog.setOnCancelListener { viewModel.downloadUserInfo() }
        }

        dialog.addTitle(getErrorTitle(responseCode), 20f, "title")
                .addMessage(getErrorMessage(responseCode), 18f, "message")
                .addSideBySideButtons(errorMessage.RETRY, 18f, errorMessage.BACK, 18f,
                        { dialog.dismiss() },
                        {
                            dialog.dismiss()
                            onBackPressed()
                        },
                        "retry", "back").show()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment: Fragment
        val searchDialog = DialogPrompt(this)
        when (item.title) {
            this.resources.getString(R.string.home) -> {
                hideFavoriteButton()
                resetBackStack()
                binding.drawerLayout.closeDrawers()
            }
            "World of Warcraft" -> {
                resetBackStack()
                fragment = AccountFragment()
                favorite!!.visibility = View.GONE
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "wowfragment").addToBackStack("wow_account").commit()
                supportFragmentManager.executePendingTransactions()
                binding.drawerLayout.closeDrawers()
            }
            searchText -> {
                searchDialog.addTitle("Character Name", 18F, "character_label")
                        .addEditText("character_field")
                        .addMessage("Realm", 18F, "realm_label")
                        .addAutoCompleteEditText("realm_field", viewModel.getWowRealms().value!!.values.flatMap { it.realms }.map { it.name }.distinct())
                        .addSpinner(resources.getStringArray(R.array.regions), "region_spinner")
                        .addButton("GO", 16F, { openSearchedWoWCharacter(searchDialog) }, "search_button").show()
                binding.drawerLayout.closeDrawers()
            }
            wowFavoritesText -> {
                fragment = WoWFavoritesFragment()
                favorite!!.visibility = View.GONE
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "wowfavorites").addToBackStack("wow_fav").commit()
                supportFragmentManager.executePendingTransactions()
                binding.drawerLayout.closeDrawers()
            }
            d3FavoritesText -> {
                fragment = D3FavoriteFragment()
                favorite!!.visibility = View.GONE
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "d3favorites").addToBackStack("d3_fav").commit()
                supportFragmentManager.executePendingTransactions()
                binding.drawerLayout.closeDrawers()
            }
            owFavoritesText -> {
                fragment = OWFavoritesFragment()
                favorite!!.visibility = View.GONE
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "owfavorites").addToBackStack("ow_fav").commit()
                supportFragmentManager.executePendingTransactions()
                binding.drawerLayout.closeDrawers()
            }
            "Diablo 3" -> {
                favorite!!.visibility = View.GONE
                favorite?.setImageResource(R.drawable.ic_star_border_black_24dp)
                searchDialog.addTitle("Enter a BattleTag", 18F, "battletag")
                        .addEditText("btag_field")
                        .addSpinner(resources.getStringArray(R.array.regions), "region_spinner")
                        .addSideBySideButtons("Search", 16F, "My Profile", 16F, { searchD3Profile(searchDialog) },
                                {
                                    callD3Activity(userInformation?.battleTag!!, MainActivity.selectedRegion)
                                    searchDialog.dismiss()
                                }, "search_button", "myprofile_button").show()
                binding.drawerLayout.closeDrawers()
            }
            d3LeaderboardText -> {
                favorite!!.visibility = View.GONE
                fragment = D3LeaderboardFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "d3leaderboard").addToBackStack("d3_leaderboard").commit()
                supportFragmentManager.executePendingTransactions()
                binding.drawerLayout.closeDrawers()
            }
            "Starcraft 2" -> {
                favorite!!.visibility = View.GONE
                fragment = SC2Fragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "sc2fragment").addToBackStack("sc2").commit()
                supportFragmentManager.executePendingTransactions()
                binding.drawerLayout.closeDrawers()
            }
            "Overwatch" -> {
                OWPlatformChoiceDialog.overwatchPrompt(this, supportFragmentManager)
                binding.drawerLayout.closeDrawers()
            }
        }
        return true
    }

    private fun resetBackStack() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        if (supportFragmentManager.findFragmentById(R.id.fragment) != null) {
            supportFragmentManager.beginTransaction().remove(supportFragmentManager.findFragmentById(R.id.fragment)!!).commit()
        }
        supportFragmentManager.executePendingTransactions()
    }

    private fun searchD3Profile(dialog: DialogPrompt) {
        val btagRegex = Regex(".*#[0-9]*")
        when {
            (dialog.tagMap["btag_field"] as EditText).text.toString().matches(btagRegex) -> {
                Toast.makeText(dialog.context, "Please enter a BattleTag", Toast.LENGTH_SHORT).show()
            }
            (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString().equals("Select Region", ignoreCase = true) -> {
                Toast.makeText(dialog.context, "Please enter the region", Toast.LENGTH_SHORT).show()
            }
            else -> {
                dialog.dismiss()
                callD3Activity((dialog.tagMap["btag_field"] as EditText).text.toString().toLowerCase(Locale.ROOT), (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString())
            }
        }
    }

    private fun openSearchedWoWCharacter(dialog: DialogPrompt) {
        when {
            (dialog.tagMap["character_field"] as EditText).text.toString() == "" -> {
                Toast.makeText(this, "Please enter the character name", Toast.LENGTH_SHORT).show()
            }
            (dialog.tagMap["realm_field"] as AutoCompleteTextView).text.toString() == "" -> {
                Toast.makeText(this, "Please enter the realm", Toast.LENGTH_SHORT).show()
            }
            (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString().equals("Select Region", ignoreCase = true) -> {
                Toast.makeText(this, "Please enter the region", Toast.LENGTH_SHORT).show()
            }
            else -> {
                characterClicked = (dialog.tagMap["character_field"] as EditText).text.toString().toLowerCase(Locale.ROOT)
                characterRealm = viewModel.getWowRealms().value!![(dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString()]!!.realms.find { it.name == (dialog.tagMap["realm_field"] as AutoCompleteTextView).text.toString() }?.slug!!
                selectedRegion = (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString()
                viewModel.downloadMedia(characterClicked, characterRealm, selectedRegion)
                dialog.dismiss()
            }
        }
    }

    private fun callD3Activity(battletag: String, region: String) {
        val fragment: Fragment = D3Fragment()
        val bundle = Bundle()
        bundle.putString("battletag", battletag)
        bundle.putString("region", region)
        bundle.putParcelable(BattlenetConstants.BUNDLE_BNPARAMS, viewModel.getBnetParams().value)
        fragment.arguments = bundle
        resetBackStack()
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
                .replace(R.id.fragment, fragment, "d3fragment")
                .addToBackStack("d3_account").commit()
        supportFragmentManager.executePendingTransactions()
    }

    private fun callWoWCharacterFragment(characterClicked: String, characterRealm: String, selectedRegion: String) {
        if (supportFragmentManager.primaryNavigationFragment != null && supportFragmentManager.primaryNavigationFragment!!.isVisible) {
            supportFragmentManager.beginTransaction().remove(supportFragmentManager.primaryNavigationFragment!!).commit()
        }
        val mediaString = Gson().toJson(viewModel.getMedia().value)
        val woWNavFragment = WoWNavFragment.newInstance(characterClicked, characterRealm, mediaString, selectedRegion)
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
                .replace(R.id.fragment, woWNavFragment)
                .addToBackStack("wow_nav").commit()
        supportFragmentManager.executePendingTransactions()
    }


    companion object {
        var userInformation: UserInformation? = null
        var userNews: UserNews? = null
        var favorite: ImageView? = null

        fun hideFavoriteButton() {
            favorite!!.visibility = View.GONE
            favorite!!.setImageResource(R.drawable.ic_star_border_black_24dp)
            favorite!!.tag = R.drawable.ic_star_border_black_24dp
        }
    }
}