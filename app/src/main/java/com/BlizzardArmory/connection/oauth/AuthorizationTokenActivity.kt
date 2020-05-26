package com.BlizzardArmory.connection.oauth

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.BlizzardArmory.R
import com.BlizzardArmory.ui.MainActivity
import kotlinx.android.synthetic.main.token_activity.*
import java.net.URLDecoder


class AuthorizationTokenActivity : AppCompatActivity() {

    private var oAuth2Helper: BnOAuth2Helper? = null
    private lateinit var webview: WebView
    var handled = false
    private var hasLoggedIn = false
    private var bnOAuth2Params: BnOAuth2Params? = null
    private var redirectActivity: Class<*>? = null
    private lateinit var authorizationTokenActivity: AuthorizationTokenActivity

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.token_activity)
        Log.i(BnConstants.TAG, "Starting task to retrieve request token")
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val bundle = this.intent.extras!!
        authorizationTokenActivity = this
        // Receiving redirection activity class
        redirectActivity = bundle[BnConstants.BUNDLE_REDIRECT_ACTIVITY] as Class<*>
        // Receiving BnOAuth2Params from intent
        bnOAuth2Params = bundle.getParcelable(BnConstants.BUNDLE_BNPARAMS)

        // Init helper and webview
        oAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params!!)
        webview = WebView(applicationContext)
        webview.settings.javaScriptEnabled = true
        webview.visibility = View.VISIBLE
        val authorizationUrl = oAuth2Helper!!.authorizationUrl
        Log.i(BnConstants.TAG, "Using authorizationUrl = $authorizationUrl")
        handled = false
        webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                Log.d(BnConstants.TAG, "onPageFinished : $url handled = $handled")
                if (url.startsWith(bnOAuth2Params!!.rederictUri)) {
                    webview.visibility = View.INVISIBLE
                    setContentView(R.layout.token_activity)
                    val rotation = AnimationUtils.loadAnimation(authorizationTokenActivity, R.anim.rotate)
                    rotation.fillAfter = true
                    loading_image.startAnimation(rotation)

                    if (!handled) {
                        ProcessToken(url).execute()
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
        Log.i(BnConstants.TAG, "onResume called with $hasLoggedIn")
        if (hasLoggedIn) {
            finish()
        }
    }

    private inner class ProcessToken internal constructor(var url: String) : AsyncTask<Uri?, Void?, Void?>() {
        var startActivity = false
        override fun doInBackground(vararg params: Uri?): Void? {
            if (url.startsWith(bnOAuth2Params!!.rederictUri)) {
                Log.i(BnConstants.TAG, "Redirect URL found: $url")
                handled = true
                try {
                    if (url.contains("code=")) {
                        val authorizationCode = extractCodeFromUrl(url)
                        Log.i(BnConstants.TAG, "Found code = $authorizationCode")
                        oAuth2Helper!!.retrieveAndStoreAccessToken(authorizationCode)

                        startActivity = true
                        hasLoggedIn = true
                    } else if (url.contains("error=") || oAuth2Helper?.accessToken == null) {
                        startActivity = false
                    }
                } catch (e: Exception) {
                    Log.e(BnConstants.TAG, "Error processing token", e)
                }
            } else {
                Log.i(BnConstants.TAG, "Not doing anything for url $url")
            }
            return null
        }

        @Throws(Exception::class)
        private fun extractCodeFromUrl(url: String): String {
            val encodedCode = url.substring(bnOAuth2Params!!.rederictUri.length + 7, url.length)
            return URLDecoder.decode(encodedCode, "UTF-8")
        }

        override fun onPreExecute() {}
        override fun onPostExecute(result: Void?) {
            if (startActivity) {
                Log.i(BnConstants.TAG, " Redirect to the activity you want: " + redirectActivity!!.name)
                val intent = Intent(this@AuthorizationTokenActivity, redirectActivity)
                intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params)
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
}