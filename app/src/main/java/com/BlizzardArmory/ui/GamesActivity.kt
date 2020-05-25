package com.BlizzardArmory.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
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
import com.BlizzardArmory.connection.oauth.BnConstants
import com.BlizzardArmory.connection.oauth.BnOAuth2Helper
import com.BlizzardArmory.connection.oauth.BnOAuth2Params
import com.BlizzardArmory.model.UserInformation
import com.BlizzardArmory.ui.ui_diablo.D3Activity
import com.BlizzardArmory.ui.ui_overwatch.OWPlatformChoiceDialog
import com.BlizzardArmory.ui.ui_starcraft.SC2Activity
import com.BlizzardArmory.ui.ui_warcraft.activity.WoWActivity
import com.crashlytics.android.Crashlytics
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.games_activity.*
import kotlinx.android.synthetic.main.games_activity_bar.*
import kotlinx.android.synthetic.main.games_activity_nav_header.*
import retrofit2.Call
import retrofit2.Callback
import java.util.*

class GamesActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var bnOAuth2Helper: BnOAuth2Helper? = null
    private var bnOAuth2Params: BnOAuth2Params? = null
    private lateinit var toggle: ActionBarDrawerToggle

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

        downloadUserInfo()
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
                        supportActionBar?.title = userInformation?.battleTag
                        battletag.text = userInformation?.battleTag
                        /*wowButton.setOnClickListener { callNextActivity(WoWActivity::class.java) }
                        diablo3Button.setOnClickListener { DiabloProfileSearchDialog.diabloPrompt(this@GamesActivity, bnOAuth2Params!!) }
                        starcraft2Button.setOnClickListener { callNextActivity(SC2Activity::class.java) }
                        overwatchButton.setOnClickListener { OWPlatformChoiceDialog.overwatchPrompt(this@GamesActivity, bnOAuth2Params) }*/
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
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawers()
        } else if (supportFragmentManager.findFragmentById(R.id.fragment) != null) {
            if (supportFragmentManager.findFragmentById(R.id.nav_fragment) != null) {
                supportFragmentManager.beginTransaction().remove(supportFragmentManager.findFragmentById(R.id.nav_fragment)!!).commit()
            } else {
                supportFragmentManager.beginTransaction().remove(supportFragmentManager.findFragmentById(R.id.fragment)!!).commit()
            }
        } else {
            super.onBackPressed()
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
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
                fragment = WoWActivity()
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "wowfragment").commit()
                supportFragmentManager.executePendingTransactions()
                drawer_layout.closeDrawers()
            }
            "Diablo 3" -> {
                fragment = D3Activity()
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "diablo3fragment").commit()
                supportFragmentManager.executePendingTransactions()
                drawer_layout.closeDrawers()
            }
            "Starcraft 2" -> {
                fragment = SC2Activity()
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "sc2fragment").commit()
                supportFragmentManager.executePendingTransactions()
                drawer_layout.closeDrawers()
            }
            "Overwatch" -> {
                OWPlatformChoiceDialog.overwatchPrompt(this, supportFragmentManager, bnOAuth2Params)
                drawer_layout.closeDrawers()
            }
        }
        return true
    }
}