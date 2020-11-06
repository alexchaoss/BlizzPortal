package com.BlizzardArmory.ui

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
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
import com.BlizzardArmory.util.events.BackPressEvent
import com.BlizzardArmory.util.events.FilterNewsEvent
import com.google.android.material.navigation.NavigationView
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.d3_gear_fragment.*
import kotlinx.android.synthetic.main.d3_skill_fragment.*
import kotlinx.android.synthetic.main.games_activity.*
import kotlinx.android.synthetic.main.games_activity_bar.*
import kotlinx.android.synthetic.main.games_activity_nav_header.*
import org.greenrobot.eventbus.EventBus
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.games_activity)
        drawer_layout.setScrimColor(Color.TRANSPARENT)
        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        battlenetOAuth2Params = this.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)

        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("Crash Prevented", throwable.message!!)
            handleUncaughtException(thread, throwable)
        }

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

    private fun handleUncaughtException(thread: Thread?, e: Throwable) {
        FirebaseCrashlytics.getInstance().log(e.message!!)
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

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment)
        val navFragment = supportFragmentManager.findFragmentById(R.id.nav_fragment)
        val newsFragment = supportFragmentManager.findFragmentById(R.id.news_fragment)
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawers()
        } else if (newsFragment?.tag == "news_page_fragment") {
            supportFragmentManager.popBackStack()
        } else if (!URLConstants.loading && fragment != null) {
            if (navFragment != null && navFragment.isVisible) {
                if (navFragment.tag == "d3nav") {
                    if (URLConstants.loading || skill_tooltip_scroll!!.visibility == View.VISIBLE || item_scroll_view!!.visibility == View.VISIBLE) {
                        EventBus.getDefault().post(BackPressEvent(true))
                    } else {
                        supportFragmentManager.beginTransaction().remove(navFragment).commit()
                    }
                } else if (!URLConstants.loading) {
                    favorite.visibility = View.GONE
                    favorite.setImageResource(R.drawable.ic_star_border_black_24dp)
                    favorite.tag = R.drawable.ic_star_border_black_24dp
                    supportFragmentManager.beginTransaction().remove(navFragment).commit()
                }
            } else if (!URLConstants.loading) {
                Log.i("FRAG1", "closed")
                favorite.visibility = View.GONE
                favorite.setImageResource(R.drawable.ic_star_border_black_24dp)
                favorite.tag = R.drawable.ic_star_border_black_24dp
                if (fragment.tag == "settingsfragment") {
                    supportFragmentManager.popBackStack()
                } else {
                    supportFragmentManager.beginTransaction().remove(fragment).commit()
                }
            }
        } else if (!URLConstants.loading) {
            try {
                super.onBackPressed()
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            } catch (e: java.lang.Exception) {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        } else {
            Log.e("FAIL", "BACKPRESS not working")
        }

    }

    private fun callErrorAlertDialog(responseCode: Int) {
        val builder = AlertDialog.Builder(this@GamesActivity, R.style.DialogTransparent)
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val titleText = TextView(this@GamesActivity)
        val buttonParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        buttonParams.setMargins(10, 20, 10, 20)
        titleText.textSize = 20f
        titleText.gravity = Gravity.CENTER_HORIZONTAL
        titleText.setPadding(0, 20, 0, 20)
        titleText.layoutParams = layoutParams
        titleText.setTextColor(Color.WHITE)
        val messageText = TextView(this@GamesActivity)
        messageText.gravity = Gravity.CENTER_HORIZONTAL
        messageText.layoutParams = layoutParams
        messageText.setTextColor(Color.WHITE)
        val button = Button(this@GamesActivity)
        titleText.text = ErrorMessages.NO_INTERNET
        messageText.text = ErrorMessages.TURN_ON_CONNECTION_MESSAGE
        button.text = ErrorMessages.RETRY
        button.textSize = 18f
        button.setTextColor(Color.WHITE)
        button.gravity = Gravity.CENTER
        button.width = 200
        button.layoutParams = buttonParams
        button.background = getDrawable(R.drawable.buttonstyle)
        val button2 = Button(this@GamesActivity)
        button2.textSize = 20f
        button2.setTextColor(Color.WHITE)
        button2.gravity = Gravity.CENTER
        button2.width = 200
        button2.layoutParams = buttonParams
        button2.background = getDrawable(R.drawable.buttonstyle)
        button2.text = ErrorMessages.BACK
        val buttonLayout = LinearLayout(this@GamesActivity)
        buttonLayout.orientation = LinearLayout.HORIZONTAL
        buttonLayout.gravity = Gravity.CENTER
        when (responseCode) {
            in 201..499 -> {
                titleText.text = ErrorMessages.UNAVAILABLE
                messageText.text = ErrorMessages.UNEXPECTED
                button.text = ErrorMessages.RETRY
                button2.text = ErrorMessages.BACK
                buttonLayout.addView(button)
                buttonLayout.addView(button2)
            }
            500 -> {
                titleText.text = ErrorMessages.SERVERS_ERROR
                messageText.text = ErrorMessages.BLIZZ_SERVERS_DOWN
                button.text = ErrorMessages.BACK
                buttonLayout.addView(button)
            }
            else -> {
                titleText.text = ErrorMessages.NO_INTERNET
                messageText.text = ErrorMessages.TURN_ON_CONNECTION_MESSAGE
                button.text = ErrorMessages.RETRY
                button2.text = ErrorMessages.BACK
                buttonLayout.addView(button)
                buttonLayout.addView(button2)
            }
        }
        val dialog = builder.show()
        dialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog?.window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER
        linearLayout.setPadding(20, 20, 20, 20)
        linearLayout.addView(titleText)
        linearLayout.addView(messageText)
        linearLayout.addView(buttonLayout)
        dialog.addContentView(linearLayout, layoutParams)
        if (responseCode == 404 || responseCode == 403 || responseCode == 500) {
            dialog.setOnCancelListener { finish() }
        } else {
            dialog.setOnCancelListener { downloadUserInfo() }
        }
        button.setOnClickListener { dialog.cancel() }
        button2.setOnClickListener {
            dialog.dismiss()
            onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment: Fragment
        val searchDialog = DialogPrompt(this)
        when (item.title) {
            "Home" -> {
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
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "wowfragment").commit()
                supportFragmentManager.executePendingTransactions()
                drawer_layout.closeDrawers()
            }
            searchText -> {
                searchDialog.addTitle("Character Name", 18F, "character_label")
                        .addEditText("character_field")
                        .addMessage("Realm", 18F, "realm_label")
                        .addEditText("realm_field")
                        .addSpinner(arrayOf("Select Region", "CN", "US", "EU", "KR", "TW"), "region_spinner")
                        .addButton("GO", 16F, { openSearchedWoWCharacter(searchDialog) }, "search_button").show()
                drawer_layout.closeDrawers()
            }
            wowFavoritesText -> {
                fragment = WoWFavoritesFragment()
                favorite.visibility = View.GONE
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "wowfavorites").commit()
                supportFragmentManager.executePendingTransactions()
                drawer_layout.closeDrawers()
            }
            d3FavoritesText -> {
                fragment = D3FavoriteFragment()
                favorite.visibility = View.GONE
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "d3favorites").commit()
                supportFragmentManager.executePendingTransactions()
                drawer_layout.closeDrawers()
            }
            owFavoritesText -> {
                fragment = OWFavoritesFragment()
                favorite.visibility = View.GONE
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "owfavorites").commit()
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
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "sc2fragment").commit()
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
                .addToBackStack(null).commit()
        supportFragmentManager.executePendingTransactions()
    }

    private fun openSearchedWoWCharacter(dialog: DialogPrompt) {
        val characterClicked: String
        val characterRealm: String
        when {
            (dialog.tagMap["character_field"] as EditText).text.toString() == "" -> {
                Toast.makeText(this, "Please enter the character name", Toast.LENGTH_SHORT).show()
            }
            (dialog.tagMap["realm_field"] as EditText).text.toString() == "" -> {
                Toast.makeText(this, "Please enter the realm", Toast.LENGTH_SHORT).show()
            }
            (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString().equals("Select Region", ignoreCase = true) -> {
                Toast.makeText(this, "Please enter the region", Toast.LENGTH_SHORT).show()
            }
            else -> {
                characterClicked = (dialog.tagMap["character_field"] as EditText).text.toString().toLowerCase(Locale.ROOT)
                characterRealm = (dialog.tagMap["realm_field"] as EditText).text.toString().toLowerCase(Locale.ROOT).replace(" ", "-")
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
                .addToBackStack(null).commit()
        supportFragmentManager.executePendingTransactions()
    }

    companion object {
        var userInformation: UserInformation? = null
        var userNews: UserNews? = null
    }
}