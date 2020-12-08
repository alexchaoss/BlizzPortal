package com.BlizzardArmory.ui.main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import com.BlizzardArmory.databinding.TokenActivityBinding
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Helper
import kotlinx.coroutines.launch


class AuthorizationTokenActivity : AppCompatActivity() {

    private lateinit var webview: WebView
    private var redirectActivity: Class<*>? = null
    private lateinit var authorizationTokenActivity: AuthorizationTokenActivity

    private var prefs: SharedPreferences? = null

    private lateinit var binding: TokenActivityBinding
    private val viewModel: AuthorizationTokenViewModel by viewModels()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TokenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i(BattlenetConstants.TAG, "Starting task to retrieve request token")
        setOberservers()
        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        viewModel.getBnetParams().value = this.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
        val bundle = this.intent.extras!!
        authorizationTokenActivity = this
        // Receiving redirection activity class
        redirectActivity = bundle[BattlenetConstants.BUNDLE_REDIRECT_ACTIVITY] as Class<*>
    }

    private fun initWebView() {
        webview = WebView(applicationContext)
        webview.settings.javaScriptEnabled = true
        webview.visibility = View.VISIBLE
        val authorizationUrl = viewModel.battlenetOAuth2Helper!!.authorizationUrl
        Log.i(BattlenetConstants.TAG, "Using authorizationUrl = $authorizationUrl")
        webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                Log.d(BattlenetConstants.TAG, "onPageFinished : $url handled = ${viewModel.isHandled()}")
                if (url.startsWith(viewModel.getBnetParams().value!!.rederictUri)) {
                    webview.visibility = View.INVISIBLE
                    setContentView(binding.root)
                    if (!viewModel.isHandled()) {
                        lifecycleScope.launch {
                            viewModel.processToken(url)
                        }
                    }
                } else {
                    webview.visibility = View.VISIBLE
                }
            }
        }
        webview.loadUrl(authorizationUrl)
        setContentView(webview)
    }

    private fun setOberservers() {
        viewModel.getBnetParams().observe(this, {
            viewModel.battlenetOAuth2Helper = BattlenetOAuth2Helper(viewModel.getBnetParams().value!!)
            initWebView()
        })

        viewModel.startActivity().observe(this, {
            onTokenProcessed(it)
        })
    }

    override fun onResume() {
        super.onResume()
        Log.i(BattlenetConstants.TAG, "onResume called with ${viewModel.hasLoggedIn().value!!}")
        if (viewModel.hasLoggedIn().value!!) {
            finish()
        }
    }

    private fun onTokenProcessed(startActivity: Boolean) {
        if (startActivity) {
            Log.i(BattlenetConstants.TAG, " Redirect to the activity you want: " + redirectActivity!!.name)
            val intent = Intent(this@AuthorizationTokenActivity, redirectActivity)
            intent.putExtra(BattlenetConstants.BUNDLE_BNPARAMS, viewModel.getBnetParams().value)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this@AuthorizationTokenActivity, "Oops! There was an error, please try again!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@AuthorizationTokenActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}