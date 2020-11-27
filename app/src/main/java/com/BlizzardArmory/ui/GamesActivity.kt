package com.BlizzardArmory.ui

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.BlizzardArmory.BuildConfig
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.ErrorMessages
import com.BlizzardArmory.connection.NetworkServices
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.oauth.BattlenetConstants
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.databinding.GamesActivityBarBinding
import com.BlizzardArmory.databinding.GamesActivityBinding
import com.BlizzardArmory.databinding.GamesActivityNavHeaderBinding
import com.BlizzardArmory.model.UserInformation
import com.BlizzardArmory.model.news.UserNews
import com.BlizzardArmory.model.warcraft.media.Media
import com.BlizzardArmory.model.warcraft.realm.Realms
import com.BlizzardArmory.ui.news.NewsListFragment
import com.BlizzardArmory.ui.ui_diablo.account.D3Fragment
import com.BlizzardArmory.ui.ui_diablo.favorites.D3FavoriteFragment
import com.BlizzardArmory.ui.ui_diablo.leaderboard.D3LeaderboardFragment
import com.BlizzardArmory.ui.ui_overwatch.OWPlatformChoiceDialog
import com.BlizzardArmory.ui.ui_overwatch.favorites.OWFavoritesFragment
import com.BlizzardArmory.ui.ui_starcraft.SC2Fragment
import com.BlizzardArmory.ui.ui_warcraft.account.AccountFragment
import com.BlizzardArmory.ui.ui_warcraft.favorites.WoWFavoritesFragment
import com.BlizzardArmory.ui.ui_warcraft.navigation.WoWNavFragment
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.events.FilterNewsEvent
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.games_activity_nav_header.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class GamesActivity : LocalizationActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null
    private var battlenetOAuth2Params: BattlenetOAuth2Params? = null
    private lateinit var toggle: ActionBarDrawerToggle
    private var searchText: SpannableString? = null
    private var wowFavoritesText: SpannableString? = null
    private var d3FavoritesText: SpannableString? = null
    private var owFavoritesText: SpannableString? = null
    private var d3LeaderboardText: SpannableString? = null
    private var wowMediaCharacter: Media? = null
    private var prefs: SharedPreferences? = null
    private val gson = GsonBuilder().create()
    private var wowRealms = mutableMapOf<String, Realms>()
    private lateinit var errorMessage: ErrorMessages
    
    private lateinit var binding: GamesActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GamesActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("Crash Prevented", throwable.message!!, throwable)
            handleUncaughtException(thread, throwable)
        }
        client = RetroClient.getClient(this)

        favorite = binding.topBar.favorite
        errorMessage = ErrorMessages(this.resources)
        binding.drawerLayout.setScrimColor(Color.TRANSPARENT)
        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        battlenetOAuth2Params = this.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)

        if (!prefs?.contains("news_pulled")!!) {
            val dialog = DialogPrompt(this)
            dialog.addTitle("New Feature!", 20F)
                    .addMessage("Pull from the right side to select which news you want to see here!", 18F)
                    .addButton("Close", 16F, { dialog.cancel() }).show()
            prefs?.edit()?.putString("news_pulled", "done")?.apply()
        }

        val userNewsPrefs = prefs?.getString("user_news", "DEFAULT")
        if (userNewsPrefs == "DEFAULT") {
            userNews = UserNews()
            prefs?.edit()?.putString("user_news", gson.toJson(userNews))?.apply()
        } else {
            userNews = gson.fromJson(userNewsPrefs, UserNews::class.java)
        }

        setUserNews()

        val newsListFragment = NewsListFragment()
        supportFragmentManager.beginTransaction().replace(R.id.news_fragment, newsListFragment, "news_fragment").commit()
        supportFragmentManager.executePendingTransactions()

        if (BuildConfig.DEBUG && battlenetOAuth2Params == null) {
            error("Assertion failed")
        }

        battlenetOAuth2Helper = BattlenetOAuth2Helper(prefs, battlenetOAuth2Params!!)

        getRealms()

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

        binding.settings.setOnClickListener {
            val fragment = SettingsFragment()
            favorite!!.visibility = View.GONE
            supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "settingsfragment").addToBackStack("settings").commit()
            supportFragmentManager.executePendingTransactions()
            binding.drawerLayout.closeDrawers()
        }

        downloadUserInfo()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun localeSelectedReceived(localeSelectedEvent: LocaleSelectedEvent) {
        getRealms()
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
        FirebaseCrashlytics.getInstance().log(e.message!!)
    }

    private fun getRealms() {
        val call: Call<Realms> = client!!.getRealmIndex("us", "dynamic-us", MainActivity.locale, battlenetOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<Realms> {
            override fun onResponse(call: Call<Realms>, response: Response<Realms>) {
                if (response.isSuccessful) {
                    wowRealms["US"] = response.body()!!
                }
            }

            override fun onFailure(call: Call<Realms>, t: Throwable) {
                Log.e("realms", "trace start")
                Log.e("Error", "trace", t)
            }
        })
        val call2: Call<Realms> = client!!.getRealmIndex("eu", "dynamic-eu", MainActivity.locale, battlenetOAuth2Helper!!.accessToken)
        call2.enqueue(object : Callback<Realms> {
            override fun onResponse(call: Call<Realms>, response: Response<Realms>) {
                if (response.isSuccessful) {
                    wowRealms["EU"] = response.body()!!
                }
            }

            override fun onFailure(call: Call<Realms>, t: Throwable) {
                Log.e("realms", "trace start")
                Log.e("Error", "trace", t)
            }
        })
        val call3: Call<Realms> = client!!.getRealmIndex("kr", "dynamic-kr", MainActivity.locale, battlenetOAuth2Helper!!.accessToken)
        call3.enqueue(object : Callback<Realms> {
            override fun onResponse(call: Call<Realms>, response: Response<Realms>) {
                if (response.isSuccessful) {
                    wowRealms["KR"] = response.body()!!
                }
            }

            override fun onFailure(call: Call<Realms>, t: Throwable) {
                Log.e("realms", "trace start")
                Log.e("Error", "trace", t)
            }
        })
        val call4: Call<Realms> = client!!.getRealmIndex("tw", "dynamic-tw", MainActivity.locale, battlenetOAuth2Helper!!.accessToken)
        call4.enqueue(object : Callback<Realms> {
            override fun onResponse(call: Call<Realms>, response: Response<Realms>) {
                if (response.isSuccessful) {
                    wowRealms["TW"] = response.body()!!
                }
            }

            override fun onFailure(call: Call<Realms>, t: Throwable) {
                Log.e("realms", "trace start")
                Log.e("Error", "trace", t)
            }
        })
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

    private fun downloadUserInfo() {
        val call: Call<UserInformation> = client!!.getUserInfo(battlenetOAuth2Helper!!.accessToken, MainActivity.selectedRegion.toLowerCase(Locale.ROOT))
        call.enqueue(object : Callback<UserInformation> {
            override fun onResponse(call: Call<UserInformation>, response: Response<UserInformation>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        userInformation = response.body()

                        binding.topBar.barTitle.text = userInformation?.battleTag
                        binding.drawerLayout.battletag.text = userInformation?.battleTag
                    } else {
                        callErrorAlertDialog(500)
                    }
                } else if (response.code() == 500) {
                    callErrorAlertDialog(response.code())
                }
                if (userInformation?.battleTag == null) {
                    callErrorAlertDialog(404)
                }
            }

            override fun onFailure(call: Call<UserInformation>, t: Throwable) {
                Log.e("Error", "trace", t)
                callErrorAlertDialog(0)
            }
        })
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
            dialog.setOnCancelListener { downloadUserInfo() }
        }

        dialog.addTitle(getErrorTitle(responseCode), 20f, "title")
                .addMessage(getErrorMessage(responseCode), 18f, "message")
                .addSideBySideButtons(errorMessage.RETRY, 18f, errorMessage.BACK, 18f,
                        { dialog.cancel() },
                        {
                            dialog.cancel()
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
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                if (supportFragmentManager.findFragmentById(R.id.fragment) != null) {
                    supportFragmentManager.beginTransaction().remove(supportFragmentManager.findFragmentById(R.id.fragment)!!).commit()
                }
                supportFragmentManager.executePendingTransactions()
                binding.drawerLayout.closeDrawers()
            }
            "World of Warcraft" -> {
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
                        .addAutoCompleteEditText("realm_field", wowRealms.values.flatMap { it.realms }.map { it.name }.distinct())
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
                                    searchDialog.cancel()
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

    private fun searchD3Profile(dialog: DialogPrompt) {
        val btagRegex = Regex(".*#[0-9]*")
        when {
            (dialog.tagMap["btag_field"] as EditText).text.toString().matches(btagRegex) -> {
                Toast.makeText(this, "Please enter a BattleTag", Toast.LENGTH_SHORT).show()
            }
            (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString().equals("Select Region", ignoreCase = true) -> {
                Toast.makeText(this, "Please enter the region", Toast.LENGTH_SHORT).show()
            }
            else -> {
                dialog.cancel()
                callD3Activity((dialog.tagMap["btag_field"] as EditText).text.toString().toLowerCase(Locale.ROOT), (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString())
            }
        }
    }

    private fun callD3Activity(battletag: String, region: String) {
        val fragment: Fragment = D3Fragment()
        val bundle = Bundle()
        bundle.putString("battletag", battletag)
        bundle.putString("region", region)
        bundle.putParcelable(BattlenetConstants.BUNDLE_BNPARAMS, battlenetOAuth2Params)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
                .replace(R.id.fragment, fragment, "d3fragment")
                .addToBackStack("d3_account").commit()
        supportFragmentManager.executePendingTransactions()
    }

    private fun openSearchedWoWCharacter(dialog: DialogPrompt) {
        val characterClicked: String
        val characterRealm: String
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
                characterRealm = wowRealms[(dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString()]?.realms?.find { it.name == (dialog.tagMap["realm_field"] as AutoCompleteTextView).text.toString() }?.slug!!
                downloadMedia(dialog, characterClicked, characterRealm, (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString())
            }
        }
    }

    private fun downloadMedia(dialog: DialogPrompt, characterClicked: String, characterRealm: String, selectedRegion: String) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val battlenetOAuth2Params: BattlenetOAuth2Params = Objects.requireNonNull(this).intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)!!
        val bnOAuth2Helper = BattlenetOAuth2Helper(prefs, battlenetOAuth2Params)

        val call: Call<Media> = client!!.getMedia(characterClicked.toLowerCase(Locale.ROOT), characterRealm.toLowerCase(Locale.ROOT),
                MainActivity.selectedRegion.toLowerCase(Locale.ROOT), MainActivity.locale, bnOAuth2Helper.accessToken)
        call.enqueue(object : Callback<Media> {
            override fun onResponse(call: Call<Media>, response: Response<Media>) {
                wowMediaCharacter = response.body()
                dialog.cancel()
                callWoWCharacterFragment(characterClicked, characterRealm, selectedRegion)
            }

            override fun onFailure(call: Call<Media>, t: Throwable) {
                Log.e("Error", "trace", t)
                callWoWCharacterFragment(characterClicked, characterRealm, selectedRegion)
            }
        })
    }

    private fun callWoWCharacterFragment(characterClicked: String, characterRealm: String, selectedRegion: String) {
        if (supportFragmentManager.primaryNavigationFragment != null && supportFragmentManager.primaryNavigationFragment!!.isVisible) {
            supportFragmentManager.beginTransaction().remove(supportFragmentManager.primaryNavigationFragment!!).commit()
        }
        val mediaString = Gson().toJson(wowMediaCharacter)
        val woWNavFragment = WoWNavFragment.newInstance(characterClicked, characterRealm, mediaString, selectedRegion)
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
                .replace(R.id.fragment, woWNavFragment)
                .addToBackStack("wow_nav").commit()
        supportFragmentManager.executePendingTransactions()
    }

    companion object {
        var client: NetworkServices? = null
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