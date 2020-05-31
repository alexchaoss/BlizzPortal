package com.BlizzardArmory.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.ErrorMessages
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BnConstants
import com.BlizzardArmory.connection.oauth.BnOAuth2Helper
import com.BlizzardArmory.connection.oauth.BnOAuth2Params
import com.BlizzardArmory.model.UserInformation
import com.BlizzardArmory.ui.ui_diablo.DiabloProfileSearchDialog
import com.BlizzardArmory.ui.ui_overwatch.OWPlatformChoiceDialog
import com.BlizzardArmory.ui.ui_overwatch.favorites.OWFavoritesFragment
import com.BlizzardArmory.ui.ui_starcraft.SC2Fragment
import com.BlizzardArmory.ui.ui_warcraft.WoWCharacterSearchDialog
import com.BlizzardArmory.ui.ui_warcraft.account.AccountFragment
import com.BlizzardArmory.ui.ui_warcraft.favorites.WoWFavoritesFragment
import com.BlizzardArmory.util.events.BackPressEvent
import com.crashlytics.android.Crashlytics
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.d3_gear_fragment.*
import kotlinx.android.synthetic.main.d3_skill_fragment.*
import kotlinx.android.synthetic.main.games_activity.*
import kotlinx.android.synthetic.main.games_activity_bar.*
import kotlinx.android.synthetic.main.games_activity_nav_header.*
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import java.util.*

class GamesActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var bnOAuth2Helper: BnOAuth2Helper? = null
    private var bnOAuth2Params: BnOAuth2Params? = null
    private lateinit var toggle: ActionBarDrawerToggle
    var searchText: SpannableString? = null
    var wowFavoritesText: SpannableString? = null
    var d3FavoritesText: SpannableString? = null
    var owFavoritesText: SpannableString? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.games_activity)
        drawer_layout.setScrimColor(Color.TRANSPARENT)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        bnOAuth2Params = this.intent?.extras?.getParcelable(BnConstants.BUNDLE_BNPARAMS)
        assert(bnOAuth2Params != null)
        bnOAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params!!)

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
            supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "settingsfragment").commit()
            supportFragmentManager.executePendingTransactions()
            drawer_layout.closeDrawers()
        }

        downloadUserInfo()
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
        val call: Call<UserInformation> = RetroClient.getClient.getUserInfo(bnOAuth2Helper!!.accessToken, MainActivity.selectedRegion.toLowerCase(Locale.ROOT))
        call.enqueue(object : Callback<UserInformation> {
            override fun onResponse(call: Call<UserInformation>, response: retrofit2.Response<UserInformation>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        userInformation = response.body()
                        try {
                            Crashlytics.log(Log.INFO, "Battle tag", response.body()!!.battleTag)
                        } catch (e: Exception) {
                            Crashlytics.log(Log.ERROR, "NULL BODY", "Body was null and crashed the app.")
                        }
                        bar_title.text = userInformation?.battleTag
                        battletag.text = userInformation?.battleTag
                    } else {
                        callErrorAlertDialog(500)
                    }
                } else if (response.code() == 500) {
                    callErrorAlertDialog(response.code())
                }
            }

            override fun onFailure(call: Call<UserInformation>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                callErrorAlertDialog(0)
            }
        })
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment)
        val navFragment = supportFragmentManager.findFragmentById(R.id.nav_fragment)
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawers()
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
                    supportFragmentManager.beginTransaction().remove(navFragment).commit()
                }
            } else if (!URLConstants.loading) {
                Log.i("FRAG1", "closed")
                favorite.visibility = View.GONE
                favorite.setImageResource(R.drawable.ic_star_border_black_24dp)
                supportFragmentManager.beginTransaction().remove(fragment).commit()
            }
        } else if (!URLConstants.loading) {
            super.onBackPressed()
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
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
        Objects.requireNonNull(dialog?.window)?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
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

    companion object {
        var userInformation: UserInformation? = null
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment: Fragment

        when (item.title) {
            "World of Warcraft" -> {
                fragment = AccountFragment()
                favorite.visibility = View.GONE
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "wowfragment").commit()
                supportFragmentManager.executePendingTransactions()
                drawer_layout.closeDrawers()
            }
            searchText -> {
                WoWCharacterSearchDialog.characterSearchPrompt(this, supportFragmentManager.primaryNavigationFragment)
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
                fragment = WoWFavoritesFragment()
                favorite.visibility = View.GONE
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "wowfavorites").commit()
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
                favorite.visibility = View.VISIBLE
                favorite.setImageResource(R.drawable.ic_star_border_black_24dp)
                DiabloProfileSearchDialog.diabloPrompt(this, bnOAuth2Params!!, supportFragmentManager)
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
}