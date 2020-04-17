package com.BlizzardArmory.ui.ui_warcraft.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.ErrorMessages
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BnConstants
import com.BlizzardArmory.connection.oauth.BnOAuth2Helper
import com.BlizzardArmory.connection.oauth.BnOAuth2Params
import com.BlizzardArmory.ui.GamesActivity
import com.BlizzardArmory.ui.IOnBackPressed
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.ui.ui_diablo.DiabloProfileSearchDialog
import com.BlizzardArmory.ui.ui_overwatch.OWPlatformChoiceDialog
import com.BlizzardArmory.ui.ui_starcraft.SC2Activity
import com.BlizzardArmory.ui.ui_warcraft.WoWCharacterSearchDialog
import com.BlizzardArmory.warcraft.account.Account
import com.BlizzardArmory.warcraft.account.Character
import kotlinx.android.synthetic.main.wow_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class WoWActivity : AppCompatActivity() {

    private var charaters: Account? = null
    private val characterList = ArrayList<Character>()
    private var bnOAuth2Params: BnOAuth2Params? = null
    private var bnOAuth2Helper: BnOAuth2Helper? = null
    private val charactersByRealm = arrayListOf<List<Character>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wow_activity)
        btag_header.text = GamesActivity.userInformation.battleTag

        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        loadingCircle.visibility = View.VISIBLE
        URLConstants.loading = true

        val prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        bnOAuth2Params = intent?.extras?.getParcelable(BnConstants.BUNDLE_BNPARAMS)
        bnOAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params!!)
        downloadWoWCharacters()

        //Button calls
        diablo3Button.setOnClickListener { DiabloProfileSearchDialog.diabloPrompt(this@WoWActivity, bnOAuth2Params) }
        starcraft2Button.setOnClickListener { callNextActivity(SC2Activity::class.java) }
        overwatchButton.setOnClickListener {
            OWPlatformChoiceDialog.myProfileChosen = false
            OWPlatformChoiceDialog.overwatchPrompt(this@WoWActivity, bnOAuth2Params)
        }
        search.setOnClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment)
            WoWCharacterSearchDialog.characterSearchPrompt(this@WoWActivity, fragment)
        }
    }

    private fun downloadWoWCharacters() {
        val call: Call<Account> = RetroClient.getClient.getAccount(MainActivity.locale, bnOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<Account> {
            override fun onResponse(call: Call<Account>, response: Response<Account>) {
                when {
                    response.isSuccessful -> {
                        Log.i("TEST", response.raw().request.url.toString())
                        charaters = response.body()
                        populateRecyclerView()
                    }
                    response.code() >= 400 -> {
                        Log.e("Error", "Response code: " + response.code())
                        callErrorAlertDialog(response.code())
                    }
                }

            }

            override fun onFailure(call: Call<Account>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                callErrorAlertDialog(0)
            }
        })
    }

    private fun populateRecyclerView() {
        if (charaters?.wowAccounts != null) {
            for (wowAccount in charaters!!.wowAccounts) {
                characterList.addAll(wowAccount.characters)
            }
        }
        Log.i("TEST", characterList.get(0).realm.name)
        for (characters in characterList.groupBy { it.realm.name }) {
            charactersByRealm.add(characters.value.sortedBy { it.level.toInt() }.reversed())
        }
        charactersByRealm.sortBy { it[0].realm.name }

        for (realm in charactersByRealm) run {
            val paramsButton: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            val button = TextView(this@WoWActivity)
            button.setBackgroundResource(R.drawable.progress_collapse_header)
            button.setTextColor(Color.WHITE)
            var realmNamePlus = "+ " + realm[0].realm.name
            var realmNameMinus = "- " + realm[0].realm.name
            button.text = realmNamePlus
            button.textSize = 18F
            button.layoutParams = paramsButton
            linear_wow_characters.addView(button)

            var expand = false
            val paramsRecyclerView: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            val recyclerView = RecyclerView(this@WoWActivity)
            recyclerView.layoutParams = paramsRecyclerView
            linear_wow_characters.addView(recyclerView)

            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@WoWActivity)
                adapter = bnOAuth2Params?.let { ActivityAdapter(realm, supportFragmentManager, this@WoWActivity, it) }
                loadingCircle.visibility = View.GONE
                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }
            recyclerView.visibility = View.GONE

            button.setOnClickListener {
                Log.i("CLICKED", "realm")
                if (!expand) {
                    expand = true
                    recyclerView.visibility = View.VISIBLE
                    button.text = realmNameMinus
                } else {
                    expand = false
                    recyclerView.visibility = View.GONE
                    button.text = realmNamePlus
                }
            }
        }
    }

    private fun callNextActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params)
        startActivity(intent)
    }

    override fun onBackPressed() {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment)
            if (fragment !is IOnBackPressed || !(fragment as IOnBackPressed).onBackPressed()) {
                super.onBackPressed()
            } else if (!URLConstants.loading) {
                val intent = Intent(this, GamesActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
    }

    private fun callErrorAlertDialog(responseCode: Int) {
        URLConstants.loading = false
        val builder = AlertDialog.Builder(this@WoWActivity, R.style.DialogTransparent)
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val buttonParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        buttonParams.setMargins(10, 20, 10, 20)
        val titleText = TextView(this@WoWActivity)
        titleText.textSize = 20f
        titleText.gravity = Gravity.CENTER_HORIZONTAL
        titleText.setPadding(0, 20, 0, 20)
        titleText.layoutParams = layoutParams
        titleText.setTextColor(Color.WHITE)
        val messageText = TextView(this@WoWActivity)
        messageText.gravity = Gravity.CENTER_HORIZONTAL
        messageText.layoutParams = layoutParams
        messageText.setTextColor(Color.WHITE)
        val button = Button(this@WoWActivity)
        button.textSize = 18f
        button.setTextColor(Color.WHITE)
        button.gravity = Gravity.CENTER
        button.width = 200
        button.layoutParams = buttonParams
        button.background = getDrawable(R.drawable.buttonstyle)
        val button2 = Button(this@WoWActivity)
        button2.textSize = 20f
        button2.setTextColor(Color.WHITE)
        button2.gravity = Gravity.CENTER
        button2.width = 200
        button2.height = 100
        button2.layoutParams = buttonParams
        button2.background = getDrawable(R.drawable.buttonstyle)
        val buttonLayout = LinearLayout(this@WoWActivity)
        buttonLayout.orientation = LinearLayout.HORIZONTAL
        buttonLayout.gravity = Gravity.CENTER
        when (responseCode) {
            in 400..499 -> {
                titleText.text = ErrorMessages.INFORMATION_OUTDATED
                messageText.text = ErrorMessages.LOGIN_TO_UPDATE
                button2.text = ErrorMessages.BACK
                buttonLayout.addView(button2)
            }
            500 -> {
                titleText.text = ErrorMessages.SERVERS_ERROR
                messageText.text = ErrorMessages.BLIZZ_SERVERS_DOWN
                button2.text = ErrorMessages.BACK
                buttonLayout.addView(button2)
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
        dialog.show()
        Objects.requireNonNull(dialog?.window)?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
        val linearLayout = LinearLayout(this@WoWActivity)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER
        linearLayout.setPadding(20, 20, 20, 20)
        linearLayout.addView(titleText)
        linearLayout.addView(messageText)
        linearLayout.addView(buttonLayout)
        dialog.addContentView(linearLayout, layoutParams)
        dialog.setOnCancelListener { downloadWoWCharacters() }
        button.setOnClickListener { dialog.cancel() }
        button2.setOnClickListener {
            dialog.dismiss()
            onBackPressed()
        }
    }
}