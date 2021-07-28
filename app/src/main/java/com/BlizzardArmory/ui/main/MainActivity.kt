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
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.viewModels
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.MainActivityBinding
import com.BlizzardArmory.network.oauth.OauthFlowStarter
import com.BlizzardArmory.util.ConnectionStatus
import com.BlizzardArmory.util.DialogPrompt
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics


class MainActivity : LocalizationActivity() {


    private var sharedPreferences: SharedPreferences? = null
    private val REQUEST_CODE_IN_APP_UPDATE = 7500
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
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

        viewModel.getBattlenetOAuth2Params().observe(this, {
            OauthFlowStarter.startOauthFlow(it, this, View.VISIBLE)
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
        return object :
            ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list) {
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

    private fun setLoginButtonToBattlenet() {
        binding.buttonLogin.setOnClickListener {
            if (selectedRegion == getString(R.string.select_region)) {
                Snackbar.make(binding.root, getString(R.string.please_select_region), Snackbar.LENGTH_SHORT)
                    .show()
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
            dialog.addTitle(getString(R.string.unstable_connection), 20F, "title")
                .addMessage(getString(R.string.connected_network), 16F, "message")
                .addButtons(
                    dialog.Button(getString(R.string.yes), 16F, { viewModel.openLoginToBattleNet() }, "positive"),
                    dialog.Button(
                        getString(R.string.no), 16F,
                        {
                            dialog.dismiss()
                            val confirmDialog = DialogPrompt(this)
                            confirmDialog.addMessage(getString(R.string.connection_required), 20F)
                                .addButtons(dialog.Button(getString(R.string.ok), 16F, { confirmDialog.dismiss() }, "close"))
                                .show()
                        }, "negative"
                    )
                ).show()
        }
    }

    private fun startWiFiNetworkCallback() {
        val cm: ConnectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder =
            NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_WIFI)

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
        val cm: ConnectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder =
            NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)

        cm.registerNetworkCallback(builder.build(), object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                ConnectionStatus.isDataNetworkConnected = true
            }

            override fun onLost(network: Network) {
                ConnectionStatus.isDataNetworkConnected = false
            }
        })
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