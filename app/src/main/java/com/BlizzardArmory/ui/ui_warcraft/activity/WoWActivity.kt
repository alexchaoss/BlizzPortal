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
import com.BlizzardArmory.URLConstants
import com.BlizzardArmory.UserInformation
import com.BlizzardArmory.connection.NetworkServices
import com.BlizzardArmory.ui.GamesActivity
import com.BlizzardArmory.ui.IOnBackPressed
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.ui.ui_diablo.DiabloProfileSearchDialog
import com.BlizzardArmory.ui.ui_overwatch.OWPlatformChoiceDialog
import com.BlizzardArmory.ui.ui_starcraft.SC2Activity
import com.BlizzardArmory.ui.ui_warcraft.WoWCharacterSearchDialog
import com.BlizzardArmory.warcraft.account.Account
import com.BlizzardArmory.warcraft.account.Character
import com.dementh.lib.battlenet_oauth2.BnConstants
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.wow_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class WoWActivity : AppCompatActivity() {

    private var charaters: Account? = null
    private val characterList = ArrayList<Character>()
    private var bnOAuth2Params: BnOAuth2Params? = null
    private var bnOAuth2Helper: BnOAuth2Helper? = null
    private val charactersByRealm = arrayListOf<List<Character>>()
    private var retrofit: Retrofit? = null
    private var gson: Gson? = null
    private lateinit var networkServices: NetworkServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wow_activity)
        btag_header.text = UserInformation.getBattleTag()

        gson = GsonBuilder().create()
        retrofit = Retrofit.Builder().baseUrl(URLConstants.getBaseURLforAPI(MainActivity.selectedRegion)).addConverterFactory(GsonConverterFactory.create(gson!!)).build()
        networkServices = retrofit?.create(NetworkServices::class.java)!!

        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        loadingCircle.visibility = View.VISIBLE
        URLConstants.loading = true

        val prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        bnOAuth2Params = Objects.requireNonNull(intent.extras).getParcelable(BnConstants.BUNDLE_BNPARAMS)
        bnOAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params)
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

        val call: Call<Account> = networkServices.getAccount("profile-" + MainActivity.selectedRegion.toLowerCase(Locale.ROOT), "en_US", bnOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<Account> {
            override fun onResponse(call: Call<Account>, response: retrofit2.Response<Account>) {
                charaters = response.body()
                populateRecyclerView()
            }

            override fun onFailure(call: Call<Account>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
            }
        })
    }

    private fun populateRecyclerView() {
        for (wowAccount in charaters!!.wowAccounts) {
            characterList.addAll(wowAccount.characters)
        }

        for (characters in characterList.groupBy { it.realm.name }) {
            charactersByRealm.add(characters.value.sortedBy { it.level.toInt() }.reversed())
        }
        charactersByRealm.sortBy { it[0].realm.name }

        for (realm in charactersByRealm) run {
            val paramsButton: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            val button = TextView(this@WoWActivity)
            button.setBackgroundResource(R.drawable.progress_collapse_header)
            button.setTextColor(Color.WHITE)
            button.text = "+ " + realm.get(0).realm.name
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
                    button.text = "- " + realm.get(0).realm.name
                } else {
                    expand = false
                    recyclerView.visibility = View.GONE
                    button.text = "+ " + realm.get(0).realm.name
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
        buttonLayout.addView(button)
        if (responseCode == 404) {
            titleText.text = "Information Outdated"
            messageText.text = "Please login in game to update this account's information."
            button.text = "OK"
        } else {
            titleText.text = "No Internet Connection"
            messageText.text = "Make sure that Wi-Fi or mobile data is turned on, then try again."
            button.text = "Retry"
            button2.text = "Back"
            buttonLayout.addView(button2)
        }
        val dialog = builder.show()
        dialog.show()
        Objects.requireNonNull(dialog.window).addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
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
        button2.setOnClickListener { onBackPressed() }
    }
}