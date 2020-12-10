package com.BlizzardArmory.ui.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.activity.viewModels
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.ActivityMainBinding
import com.BlizzardArmory.network.URLConstants
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.ui.navigation.GamesActivity
import com.BlizzardArmory.util.ConnectionStatus
import com.BlizzardArmory.util.DialogPrompt
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.firebase.crashlytics.FirebaseCrashlytics
import java.util.*


class MainActivity : LocalizationActivity() {


    private var sharedPreferences: SharedPreferences? = null
    private val REQUEST_CODE_IN_APP_UPDATE = 7500
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseCrashlytics.getInstance().sendUnsentReports()

        startWiFiNetworkCallback()
        startDataNetworkCallback()
        checkForAppUpdates()

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        initLocale()

        val regionAdapter = setAdapater(resources.getStringArray(R.array.regions))
        regionAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        binding.spinner.adapter = regionAdapter

        spinnerSelector(binding.spinner)

        setLoginButtonToBattlenet()
        binding.logout.setOnClickListener { clearCredentials() }

        viewModel.getBattlenetOAuth2Params().observe(this, {
            startOauthFlow(it)
        })
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
                selectedRegion = parent.getItemAtPosition(position) as String
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

    private fun clearCredentials() {
        val webview = WebView(this)
        webview.settings.javaScriptEnabled = true
        webview.visibility = View.GONE
        webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                Toast.makeText(this@MainActivity, "Logout Successful", Toast.LENGTH_SHORT).show()
            }
        }
        webview.loadUrl(URLConstants.LOGOUT_URL)
    }

    private fun setLoginButtonToBattlenet() {
        binding.buttonLogin.setOnClickListener {
            if (selectedRegion == "Select Region") {
                Toast.makeText(applicationContext, "Please select a region", Toast.LENGTH_SHORT).show()
            } else {
                checkConnectionBeforeLogin()
            }
        }
    }

    private fun checkConnectionBeforeLogin() {
        if (ConnectionStatus.hasNetwork()) {
            viewModel.openLoginToBattleNet()
        } else {
            val dialog = DialogPrompt(this)
            dialog.addTitle("Internet connection unstable", 20F, "title")
                    .addMessage("Are you currently connected to a network?", 16F, "message")
                    .addSideBySideButtons("Yes", 16F, "No", 16F, { viewModel.openLoginToBattleNet() }, {
                        dialog.dismiss()
                        val confirmDialog = DialogPrompt(this)
                        confirmDialog.addMessage("This application requires an active internet connection to continue", 20F)
                                .addButton("Ok", 16F, { confirmDialog.dismiss() }, "close").show()
                    }, "positive", "negative").show()
        }
    }

    private fun startWiFiNetworkCallback() {
        val cm: ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_WIFI)

        cm.registerNetworkCallback(builder.build(), object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                ConnectionStatus.isWiFiNetworkConnected = true
            }

            override fun onLost(network: Network) {
                ConnectionStatus.isWiFiNetworkConnected = false
            }
        })
    }

    private fun startDataNetworkCallback() {
        val cm: ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)

        cm.registerNetworkCallback(builder.build(), object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                ConnectionStatus.isDataNetworkConnected = true
            }

            override fun onLost(network: Network) {
                ConnectionStatus.isDataNetworkConnected = false
            }
        })
    }

    private fun startOauthFlow(battlenetOAuth2Params: BattlenetOAuth2Params) {
        val intent = Intent(this, AuthorizationTokenActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
        intent.putExtra(BattlenetConstants.BUNDLE_BNPARAMS, battlenetOAuth2Params)
        intent.putExtra(BattlenetConstants.BUNDLE_REDIRECT_ACTIVITY, GamesActivity::class.java)
        startActivity(intent)
    }


    private fun checkForAppUpdates() {
        val appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            appUpdateManager.startUpdateFlowForResult(appUpdateInfo, IMMEDIATE, this, REQUEST_CODE_IN_APP_UPDATE)
        }
    }

    private fun initLocale() {
        locale = if (!sharedPreferences?.contains("locale")!!) {
            sharedPreferences?.edit()?.putString("locale", "en_US")?.apply()
            "en_US"
        } else {
            sharedPreferences?.getString("locale", "en_US")!!
        }
        when (locale) {
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

    companion object {
        @JvmField
        var selectedRegion = ""
        var locale = "en_US"
    }
}