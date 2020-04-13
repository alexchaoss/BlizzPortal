package com.BlizzardArmory.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.BlizzardArmory.R
import com.BlizzardArmory.UserInformation
import com.BlizzardArmory.connection.ErrorMessages
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.oauth.BnConstants
import com.BlizzardArmory.connection.oauth.BnOAuth2Helper
import com.BlizzardArmory.connection.oauth.BnOAuth2Params
import com.BlizzardArmory.ui.ui_diablo.DiabloProfileSearchDialog
import com.BlizzardArmory.ui.ui_overwatch.OWPlatformChoiceDialog
import com.BlizzardArmory.ui.ui_starcraft.SC2Activity
import com.BlizzardArmory.ui.ui_warcraft.activity.WoWActivity
import kotlinx.android.synthetic.main.activity_games.*
import retrofit2.Call
import retrofit2.Callback
import java.util.*

class GamesActivity : AppCompatActivity() {
    private var bnOAuth2Helper: BnOAuth2Helper? = null
    private var bnOAuth2Params: BnOAuth2Params? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        bnOAuth2Params = this.intent?.extras?.getParcelable(BnConstants.BUNDLE_BNPARAMS)
        assert(bnOAuth2Params != null)
        bnOAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params!!)

        //downloadUserInfo()
    }

    private fun downloadUserInfo() {
        val call: Call<UserInformation> = RetroClient.getClient.getUserInfo(bnOAuth2Helper!!.accessToken, MainActivity.selectedRegion.toLowerCase(Locale.ROOT))
        call.enqueue(object : Callback<UserInformation> {
            override fun onResponse(call: Call<UserInformation>, response: retrofit2.Response<UserInformation>) {
                userInformation = response.body()!!
                btag_header.text = userInformation.battleTag
                Log.i("Btag", userInformation.battleTag)
                Log.i("USER_ID", userInformation.userID)
                wowButton.setOnClickListener { callNextActivity(WoWActivity::class.java) }
                diablo3Button.setOnClickListener { DiabloProfileSearchDialog.diabloPrompt(this@GamesActivity, bnOAuth2Params) }
                starcraft2Button.setOnClickListener { callNextActivity(SC2Activity::class.java) }
                overwatchButton.setOnClickListener { OWPlatformChoiceDialog.overwatchPrompt(this@GamesActivity, bnOAuth2Params) }
            }

            override fun onFailure(call: Call<UserInformation>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                callErrorAlertDialog()
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun callNextActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params)
        startActivity(intent)
    }

    private fun callErrorAlertDialog() {
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
        buttonLayout.addView(button)
        buttonLayout.addView(button2)
        val dialog = builder.show()
        Objects.requireNonNull(dialog?.window)?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog?.window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        val linearLayout = LinearLayout(this@GamesActivity)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER
        linearLayout.addView(titleText)
        linearLayout.addView(messageText)
        linearLayout.addView(buttonLayout)
        dialog.addContentView(linearLayout, layoutParams)
        dialog.setOnCancelListener { downloadUserInfo() }
        button.setOnClickListener { dialog.cancel() }
        button2.setOnClickListener { dialog.dismiss() }
    }

    companion object {
        lateinit var userInformation: UserInformation
    }
}