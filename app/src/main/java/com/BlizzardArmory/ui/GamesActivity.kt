package com.BlizzardArmory.ui

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.BlizzardArmory.BuildConfig
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.ErrorMessages
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BattlenetConstants
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.model.UserInformation
import com.BlizzardArmory.model.news.UserNews
import com.BlizzardArmory.model.warcraft.media.Media
import com.BlizzardArmory.model.warcraft.realm.Realms
import com.BlizzardArmory.ui.news.NewsListFragment
import com.BlizzardArmory.ui.ui_diablo.account.D3Fragment
import com.BlizzardArmory.ui.ui_diablo.favorites.D3FavoriteFragment
import com.BlizzardArmory.ui.ui_overwatch.OWPlatformChoiceDialog
import com.BlizzardArmory.ui.ui_overwatch.favorites.OWFavoritesFragment
import com.BlizzardArmory.ui.ui_starcraft.SC2Fragment
import com.BlizzardArmory.ui.ui_warcraft.account.AccountFragment
import com.BlizzardArmory.ui.ui_warcraft.favorites.WoWFavoritesFragment
import com.BlizzardArmory.ui.ui_warcraft.navigation.WoWNavFragment
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.events.FilterNewsEvent
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import com.google.android.material.navigation.NavigationView
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.games_activity.*
import kotlinx.android.synthetic.main.games_activity_bar.*
import kotlinx.android.synthetic.main.games_activity_nav_header.*
import okhttp3.ResponseBody
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class GamesActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null
    private var battlenetOAuth2Params: BattlenetOAuth2Params? = null
    private lateinit var toggle: ActionBarDrawerToggle
    var searchText: SpannableString? = null
    var wowFavoritesText: SpannableString? = null
    var d3FavoritesText: SpannableString? = null
    var owFavoritesText: SpannableString? = null
    var wowMediaCharacter: Media? = null
    var prefs: SharedPreferences? = null
    val gson = GsonBuilder().create()
    var wowRealms = mutableMapOf<String, Realms>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.games_activity)

        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("Crash Prevented", throwable.message!!)
            handleUncaughtException(thread, throwable)
        }

        drawer_layout.setScrimColor(Color.TRANSPARENT)
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
        initWoWServer()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.itemIconTintList = null
        setSupportActionBar(toolbar_main)

        toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar_main, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = ""
        makeTitlesTransparent()

        settings.setOnClickListener {
            val fragment = SettingsFragment()
            favorite.visibility = View.GONE
            supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "settingsfragment").addToBackStack("settings").commit()
            supportFragmentManager.executePendingTransactions()
            drawer_layout.closeDrawers()
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
    public fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        getRealms()
    }

    private fun handleUncaughtException(thread: Thread?, e: Throwable) {
        FirebaseCrashlytics.getInstance().log(e.message!!)
    }

    private fun initWoWServer() {
        val call: Call<ResponseBody> = RetroClient.getClient.initServer(URLConstants.initServer())
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.i("Server init", response.body().toString())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("Server init Error", "trace", t)
            }
        })
    }

    private fun getRealms() {
        val call: Call<Realms> = RetroClient.getClient.getRealmIndex("us", "dynamic-us", MainActivity.locale, battlenetOAuth2Helper!!.accessToken)
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
        val call2: Call<Realms> = RetroClient.getClient.getRealmIndex("eu", "dynamic-eu", MainActivity.locale, battlenetOAuth2Helper!!.accessToken)
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
        val call3: Call<Realms> = RetroClient.getClient.getRealmIndex("kr", "dynamic-kr", MainActivity.locale, battlenetOAuth2Helper!!.accessToken)
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
        val call4: Call<Realms> = RetroClient.getClient.getRealmIndex("tw", "dynamic-tw", MainActivity.locale, battlenetOAuth2Helper!!.accessToken)
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
        blizzard_swtich.isChecked = userNews?.blizzNews!!
        wow_swtich.isChecked = userNews?.wowNews!!
        d3_swtich.isChecked = userNews?.d3News!!
        sc2_swtich.isChecked = userNews?.sc2News!!
        ow_swtich.isChecked = userNews?.owNews!!
        hs_swtich.isChecked = userNews?.hsNews!!
        hots_swtich.isChecked = userNews?.hotsNews!!

        blizzard_swtich.setOnClickListener {
            userNews?.blizzNews = !userNews?.blizzNews!!
            updateUserNews()
        }
        wow_swtich.setOnClickListener {
            userNews?.wowNews = !userNews?.wowNews!!
            updateUserNews()
        }
        d3_swtich.setOnClickListener {
            userNews?.d3News = !userNews?.d3News!!
            updateUserNews()
        }
        sc2_swtich.setOnClickListener {
            userNews?.sc2News = !userNews?.sc2News!!
            updateUserNews()
        }
        ow_swtich.setOnClickListener {
            userNews?.owNews = !userNews?.owNews!!
            updateUserNews()
        }
        hs_swtich.setOnClickListener {
            userNews?.hsNews = !userNews?.hsNews!!
            updateUserNews()
        }
        hots_swtich.setOnClickListener {
            userNews?.hotsNews = !userNews?.hotsNews!!
            updateUserNews()
        }
    }

    private fun updateUserNews() {
        prefs?.edit()?.putString("user_news", gson.toJson(userNews))?.apply()
        EventBus.getDefault().post(FilterNewsEvent(true))
    }

    private fun makeTitlesTransparent() {
        searchText = SpannableString(nav_view.menu.findItem(R.id.wow_search).title)
        searchText!!.setSpan(ForegroundColorSpan(Color.TRANSPARENT), 0, searchText!!.length, 0)
        nav_view.menu.findItem(R.id.wow_search).title = searchText

        wowFavoritesText = SpannableString(nav_view.menu.findItem(R.id.wow_favorite).title)
        wowFavoritesText!!.setSpan(ForegroundColorSpan(Color.TRANSPARENT), 0, wowFavoritesText!!.length, 0)
        nav_view.menu.findItem(R.id.wow_favorite).title = wowFavoritesText

        d3FavoritesText = SpannableString(nav_view.menu.findItem(R.id.d3_favorite).title)
        d3FavoritesText!!.setSpan(ForegroundColorSpan(Color.TRANSPARENT), 0, d3FavoritesText!!.length, 0)
        nav_view.menu.findItem(R.id.d3_favorite).title = d3FavoritesText

        owFavoritesText = SpannableString(nav_view.menu.findItem(R.id.ow_favorite).title)
        owFavoritesText!!.setSpan(ForegroundColorSpan(Color.TRANSPARENT), 0, owFavoritesText!!.length, 0)
        nav_view.menu.findItem(R.id.ow_favorite).title = owFavoritesText
    }

    private fun downloadUserInfo() {
        val call: Call<UserInformation> = RetroClient.getClient.getUserInfo(battlenetOAuth2Helper!!.accessToken, MainActivity.selectedRegion.toLowerCase(Locale.ROOT))
        call.enqueue(object : Callback<UserInformation> {
            override fun onResponse(call: Call<UserInformation>, response: retrofit2.Response<UserInformation>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        userInformation = response.body()

                        bar_title.text = userInformation?.battleTag
                        battletag.text = userInformation?.battleTag
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
                ErrorMessages.UNEXPECTED
            }
            500 -> {
                ErrorMessages.BLIZZ_SERVERS_DOWN
            }
            else -> {
                ErrorMessages.TURN_ON_CONNECTION_MESSAGE
            }
        }
    }

    private fun getErrorTitle(responseCode: Int): String {
        return when (responseCode) {
            in 201..499 -> {
                ErrorMessages.UNAVAILABLE
            }
            500 -> {
                ErrorMessages.SERVERS_ERROR
            }
            else -> {
                ErrorMessages.NO_INTERNET
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
                .addSideBySideButtons(ErrorMessages.RETRY, 18f, ErrorMessages.BACK, 18f,
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
            "Home" -> {
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                if (supportFragmentManager.findFragmentById(R.id.fragment) != null) {
                    supportFragmentManager.beginTransaction().remove(supportFragmentManager.findFragmentById(R.id.fragment)!!).commit()
                } else if (supportFragmentManager.findFragmentById(R.id.nav_fragment) != null) {
                    supportFragmentManager.beginTransaction().remove(supportFragmentManager.findFragmentById(R.id.nav_fragment)!!).commit()
                }
                supportFragmentManager.executePendingTransactions()
                drawer_layout.closeDrawers()
            }
            "World of Warcraft" -> {
                fragment = AccountFragment()
                favorite.visibility = View.GONE
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "wowfragment").addToBackStack("wow_account").commit()
                supportFragmentManager.executePendingTransactions()
                drawer_layout.closeDrawers()
            }
            searchText -> {
                searchDialog.addTitle("Character Name", 18F, "character_label")
                        .addEditText("character_field")
                        .addMessage("Realm", 18F, "realm_label")
                        .addAutoCompleteEditText("realm_field", wowRealms.values.flatMap { it.realms }.map { it.name }.distinct())
                        .addSpinner(arrayOf("Select Region", "CN", "US", "EU", "KR", "TW"), "region_spinner")
                        .addButton("GO", 16F, { openSearchedWoWCharacter(searchDialog) }, "search_button").show()
                drawer_layout.closeDrawers()
            }
            wowFavoritesText -> {
                fragment = WoWFavoritesFragment()
                favorite.visibility = View.GONE
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "wowfavorites").addToBackStack("wow_fav").commit()
                supportFragmentManager.executePendingTransactions()
                drawer_layout.closeDrawers()
            }
            d3FavoritesText -> {
                fragment = D3FavoriteFragment()
                favorite.visibility = View.GONE
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "d3favorites").addToBackStack("d3_fav").commit()
                supportFragmentManager.executePendingTransactions()
                drawer_layout.closeDrawers()
            }
            owFavoritesText -> {
                fragment = OWFavoritesFragment()
                favorite.visibility = View.GONE
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "owfavorites").addToBackStack("ow_fav").commit()
                supportFragmentManager.executePendingTransactions()
                drawer_layout.closeDrawers()
            }
            "Diablo 3" -> {
                favorite.visibility = View.GONE
                favorite.setImageResource(R.drawable.ic_star_border_black_24dp)
                searchDialog.addTitle("Enter a BattleTag", 18F, "battletag")
                        .addEditText("btag_field")
                        .addSpinner(arrayOf("Select Region", "CN", "US", "EU", "KR", "TW"), "region_spinner")
                        .addSideBySideButtons("Search", 16F, "My Profile", 16F, { searchD3Profile(searchDialog) },
                                {
                                    callD3Activity(userInformation?.battleTag!!, MainActivity.selectedRegion)
                                    searchDialog.cancel()
                                }, "search_button", "myprofile_button").show()
                drawer_layout.closeDrawers()
            }
            "Starcraft 2" -> {
                favorite.visibility = View.GONE
                fragment = SC2Fragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "sc2fragment").addToBackStack("sc2").commit()
                supportFragmentManager.executePendingTransactions()
                drawer_layout.closeDrawers()
            }
            "Overwatch" -> {
                OWPlatformChoiceDialog.overwatchPrompt(this, supportFragmentManager)
                drawer_layout.closeDrawers()
            }
        }
        return true
    }

    private fun searchD3Profile(dialog: DialogPrompt) {
        val btagRegex = Regex(".*#[0-9]*")
        if ((dialog.tagMap["btag_field"] as EditText).text.toString().matches(btagRegex)) {
            Toast.makeText(this, "Please enter a BattleTag", Toast.LENGTH_SHORT).show()
        } else if ((dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString().equals("Select Region", ignoreCase = true)) {
            Toast.makeText(this, "Please enter the region", Toast.LENGTH_SHORT).show()
        } else {
            dialog.cancel()
            callD3Activity((dialog.tagMap["btag_field"] as EditText).text.toString().toLowerCase(Locale.ROOT), (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString())
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

        val call: Call<Media> = RetroClient.getClient.getMedia(characterClicked.toLowerCase(Locale.ROOT), characterRealm.toLowerCase(Locale.ROOT),
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
        var userInformation: UserInformation? = null
        var userNews: UserNews? = null
    }
}