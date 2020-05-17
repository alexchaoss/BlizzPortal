package com.BlizzardArmory.ui

import android.content.*
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.AuthorizationTokenActivity
import com.BlizzardArmory.connection.oauth.BnConstants
import com.BlizzardArmory.connection.oauth.BnOAuth2Params
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE
import com.google.android.play.core.install.model.UpdateAvailability
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private var bnOAuth2Params: BnOAuth2Params? = null
    private var clientID: String? = "339a9ad89d9f4acf889b025ccb439567"
    private val regionList = arrayOf("Select Region", "CN", "US", "EU", "KR", "TW")
    private val languageList = arrayOf("Select Language", "English", "Spanish", "Portuguese", "French", "Russian", "German", "Italian", "Korean", "Chinese", "Taiwanese")
    private lateinit var selectedLanguage: String
    private var sharedPreferences: SharedPreferences? = null
    private val REQUEST_CODE_IN_APP_UPDATE = 7500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkForAppUpdates()

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        initLocale()

        val regionAdapter = setAdapater(regionList)
        regionAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = regionAdapter

        val languageAdapter = setAdapater(languageList)
        languageAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner_language.adapter = languageAdapter

        spinnerSelector(spinner)
        spinnerSelector(spinner_language)

        setSettingsButtons()

        setLoginButtonToBattlenet()
        logout.setOnClickListener { clearCredentials() }
    }

    private fun setSettingsButtons() {
        settings.setOnClickListener {
            settings_layout?.visibility = View.VISIBLE
            linearLayout.visibility = View.GONE
        }

        close_button.setOnClickListener {
            settings_layout?.visibility = View.GONE
            linearLayout.visibility = View.VISIBLE
        }

        OssLicensesMenuActivity.setActivityTitle(getString(R.string.custom_license_title))
        licenses.setOnClickListener { startActivity(Intent(this@MainActivity, OssLicensesMenuActivity::class.java)) }
        rate.setOnClickListener { openAppStoreForReview() }
        donation.setOnClickListener { webview?.loadUrl(URLConstants.PAYPAL_URL) }
    }

    private fun initLocale() {
        locale = if (!sharedPreferences?.contains("locale")!!) {
            sharedPreferences?.edit()?.putString("locale", "en_US")?.apply()
            "en_US"
        } else {
            sharedPreferences?.getString("locale", "en_US")!!
        }
    }

    override fun onResume() {
        super.onResume()
        val appUpdateManager = AppUpdateManagerFactory.create(this)
        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                appUpdateManager.startUpdateFlowForResult(appUpdateInfo, IMMEDIATE, this, REQUEST_CODE_IN_APP_UPDATE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_IN_APP_UPDATE) {
            if (resultCode != RESULT_OK) {
                checkForAppUpdates()
            }
        }
    }

    private fun setAdapater(list: Array<String>): ArrayAdapter<String> {
        return object : ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                tv.setBackgroundColor(Color.BLACK)
                tv.textSize = 20f
                tv.gravity = Gravity.CENTER
                if (position == 0) {
                    tv.setTextColor(Color.GRAY)
                } else {
                    tv.setTextColor(Color.WHITE)
                }
                return view
            }
        }
    }

    private fun spinnerSelector(spinner: Spinner) {
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (regionList.contains(parent.getItemAtPosition(position))) {
                    selectedRegion = parent.getItemAtPosition(position) as String
                } else {
                    selectedLanguage = parent.getItemAtPosition(position) as String
                    Log.i("lang", selectedLanguage)
                    setLocale()
                }
                try {
                    (view as TextView).setTextColor(Color.WHITE)
                    view.textSize = 20f
                    view.gravity = Gravity.CENTER
                } catch (e: Exception) {
                    Log.e("Error", e.toString())
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                (parent.getChildAt(0) as TextView).gravity = Gravity.CENTER
                (parent.getChildAt(0) as TextView).setTextColor(0)
            }
        }
    }

    private fun setLocale() {
        locale = when (selectedLanguage) {
            "English" -> "en_US"
            "Spanish" -> "es_ES"
            "French" -> "fr_FR"
            "Russian" -> "ru_RU"
            "German" -> "de_DE"
            "Portuguese" -> "pt_BR"
            "Italian" -> "it_IT"
            "Korean" -> "ko_KR"
            "Chinese" -> "zh_CN"
            "Taiwanese" -> "zh_TW"
            else -> "en_US"
        }
        sharedPreferences!!.edit().putString("locale", locale).apply()
    }

    private fun setLoginButtonToBattlenet() {
        buttonLogin.setOnClickListener {
            if (selectedRegion == "Select Region") {
                Toast.makeText(applicationContext, "Please select a region", Toast.LENGTH_SHORT).show()
            } else {
                openLoginToBattleNet()
            }
        }
    }

    private fun openLoginToBattleNet() {
        if (isNetworkAvailable()) {
            bnOAuth2Params = BnOAuth2Params(clientID, selectedRegion.toLowerCase(Locale.ROOT),
                    URLConstants.CALLBACK_URL, "Blizzard Games Profiles", BnConstants.SCOPE_WOW, BnConstants.SCOPE_SC2)
            startOauthFlow(bnOAuth2Params!!)
        } else {
            showNoConnectionMessage(this@MainActivity, 0)
        }
    }

    private fun openAppStoreForReview() {
        val uri = Uri.parse("market://details?id=" + this.packageName)
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        try {
            startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + this.packageName)))
        }
    }

    private fun startOauthFlow(bnOAuth2Params: BnOAuth2Params) {
        val intent = Intent(this, AuthorizationTokenActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params)
        intent.putExtra(BnConstants.BUNDLE_REDIRECT_ACTIVITY, GamesActivity::class.java)
        startActivity(intent)
    }

    private fun clearCredentials() {
        val webview = WebView(this)
        webview.settings.javaScriptEnabled = true
        webview.visibility = View.GONE
        webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                Toast.makeText(applicationContext, "Logout Successful", Toast.LENGTH_SHORT).show()
            }
        }
        webview.loadUrl(URLConstants.LOGOUT_URL)
    }

    override fun onBackPressed() {
        if (settings_layout.visibility == View.VISIBLE) {
            settings_layout.visibility = View.GONE
            linearLayout.visibility = View.VISIBLE
        } else {
            super.onBackPressed()
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private fun checkForAppUpdates() {
        val appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            appUpdateManager.startUpdateFlowForResult(appUpdateInfo, IMMEDIATE, this, REQUEST_CODE_IN_APP_UPDATE)
        }
    }

    private fun showNoConnectionMessage(context: Context, responseCode: Int) {
        val builder = AlertDialog.Builder(context, R.style.DialogTransparent)
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(0, 20, 0, 0)
        val titleText = TextView(context)
        titleText.text = "No Internet Connection"
        titleText.textSize = 20f
        titleText.gravity = Gravity.CENTER_HORIZONTAL
        titleText.setPadding(0, 20, 0, 20)
        titleText.layoutParams = layoutParams
        titleText.setTextColor(Color.WHITE)
        val messageText = TextView(context)
        messageText.text = "Make sure that Wi-Fi or mobile data is turned on, then try again."
        messageText.gravity = Gravity.CENTER_HORIZONTAL
        messageText.layoutParams = layoutParams
        messageText.setTextColor(Color.WHITE)
        val buttonLayout = LinearLayout(context)
        buttonLayout.orientation = LinearLayout.HORIZONTAL
        buttonLayout.gravity = Gravity.CENTER
        val button = Button(context)
        button.textSize = 18f
        button.setTextColor(Color.WHITE)
        button.gravity = Gravity.CENTER
        button.width = 200
        button.layoutParams = layoutParams
        button.background = context.getDrawable(R.drawable.buttonstyle)
        val button2 = Button(context)
        button2.textSize = 18f
        button2.setTextColor(Color.WHITE)
        button2.gravity = Gravity.CENTER
        button2.width = 200
        button2.layoutParams = layoutParams
        button2.background = context.getDrawable(R.drawable.buttonstyle)
        when (responseCode) {
            0 -> {
                button.text = "OK"
                button2.text = "RETRY"
                buttonLayout.addView(button)
                buttonLayout.addView(button2)
            }
            /*100 -> {
                button.text = "Yes"
                button2.text = "Cancel"
                titleText.text = "Warning"
                buttonLayout.addView(button)
                buttonLayout.addView(button2)
                messageText.text = "You are about to clear the application data, this will close the application.\n Are you sure you want to continue?"
            }*/
            else -> {
                buttonLayout.addView(button)
                button.text = "OK"
            }
        }
        val dialog = builder.show()
        Objects.requireNonNull(dialog?.window)?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        if (responseCode == 100) {
            dialog?.window?.setLayout(800, 600)
        } else {
            dialog?.window?.setLayout(800, 500)
        }
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER
        linearLayout.addView(titleText)
        linearLayout.addView(messageText)
        linearLayout.addView(buttonLayout)
        val layoutParamsWindow = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(20, 20, 20, 20)
        dialog.addContentView(linearLayout, layoutParamsWindow)
        if (responseCode == 100) {
            button2.setOnClickListener { dialog.cancel() }
            button.setOnClickListener { clearCredentials() }
        } else if (responseCode == 0) {
            dialog.setOnCancelListener { obj: DialogInterface -> obj.cancel() }
            button2.setOnClickListener { openLoginToBattleNet() }
            button.setOnClickListener { dialog.cancel() }
        } else {
            button.setOnClickListener { dialog.cancel() }
            dialog.setOnCancelListener { obj: DialogInterface -> obj.cancel() }
        }
    }

    companion object {
        @JvmField
        var selectedRegion = ""
        var locale = "en_US"
    }
}