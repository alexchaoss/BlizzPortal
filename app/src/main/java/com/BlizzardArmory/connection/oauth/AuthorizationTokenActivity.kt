package com.BlizzardArmory.connection.oauth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.TokenActivityBinding
import com.BlizzardArmory.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.URLDecoder


class AuthorizationTokenActivity : AppCompatActivity() {

    private var oAuth2Helper: BattlenetOAuth2Helper? = null
    private lateinit var webview: WebView
    var handled = false
    private var hasLoggedIn = false
    private var battlenetOAuth2Params: BattlenetOAuth2Params? = null
    private var redirectActivity: Class<*>? = null
    private lateinit var authorizationTokenActivity: AuthorizationTokenActivity

    private var startActivity = false

    private lateinit var binding: TokenActivityBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TokenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i(BattlenetConstants.TAG, "Starting task to retrieve request token")
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val bundle = this.intent.extras!!
        authorizationTokenActivity = this
        // Receiving redirection activity class
        redirectActivity = bundle[BattlenetConstants.BUNDLE_REDIRECT_ACTIVITY] as Class<*>
        // Receiving BnOAuth2Params from intent
        battlenetOAuth2Params = bundle.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)

        // Init helper and webview
        oAuth2Helper = BattlenetOAuth2Helper(prefs, battlenetOAuth2Params!!)
        webview = WebView(applicationContext)
        webview.settings.javaScriptEnabled = true
        webview.visibility = View.VISIBLE
        val authorizationUrl = oAuth2Helper!!.authorizationUrl
        Log.i(BattlenetConstants.TAG, "Using authorizationUrl = $authorizationUrl")
        handled = false
        webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                Log.d(BattlenetConstants.TAG, "onPageFinished : $url handled = $handled")
                if (url.startsWith(battlenetOAuth2Params!!.rederictUri)) {
                    webview.visibility = View.INVISIBLE
                    setContentView(binding.root)
                    val rotation = AnimationUtils.loadAnimation(this@AuthorizationTokenActivity, R.anim.rotate)
                    rotation.fillAfter = true
                    binding.loadingImage.startAnimation(rotation)

                    if (!handled) {
                        lifecycleScope.launch {
                            val job = async(Dispatchers.Default) {
                                processToken(url)
                            }
                            job.join()
                            onTokenProcessed()
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

    override fun onResume() {
        super.onResume()
        Log.i(BattlenetConstants.TAG, "onResume called with $hasLoggedIn")
        if (hasLoggedIn) {
            finish()
        }
    }

    private fun processToken(url: String) {
        if (url.startsWith(battlenetOAuth2Params!!.rederictUri)) {
            Log.i(BattlenetConstants.TAG, "Redirect URL found: $url")
            handled = true
            try {
                if (url.contains("code=")) {
                    val authorizationCode = extractCodeFromUrl(url)
                    Log.i(BattlenetConstants.TAG, "Found code = $authorizationCode")
                    oAuth2Helper!!.retrieveAndStoreAccessToken(authorizationCode, this)

                    startActivity = true
                    hasLoggedIn = true
                } else if (url.contains("error=") || oAuth2Helper?.accessToken == null) {
                    startActivity = false
                }
            } catch (e: Exception) {
                Log.e(BattlenetConstants.TAG, "Error processing token", e)
            }
        } else {
            Log.i(BattlenetConstants.TAG, "Not doing anything for url $url")
        }
    }

    private fun extractCodeFromUrl(url: String): String {
        val encodedCode = url.substring(battlenetOAuth2Params!!.rederictUri.length + 7, url.length)
        return URLDecoder.decode(encodedCode, "UTF-8")
    }

    private fun onTokenProcessed() {
        if (startActivity) {
            startActivity = false
            Log.i(BattlenetConstants.TAG, " Redirect to the activity you want: " + redirectActivity!!.name)
            val intent = Intent(this@AuthorizationTokenActivity, redirectActivity)
            intent.putExtra(BattlenetConstants.BUNDLE_BNPARAMS, battlenetOAuth2Params)
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