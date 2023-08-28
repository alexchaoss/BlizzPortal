package com.BlizzardArmory.ui.auth


import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.BlizzardArmory.databinding.AuthorizationFragmentBinding
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.util.state.FavoriteState
import kotlinx.coroutines.launch


class AuthorizationFragment : Fragment() {

    private var visibility: Int = 0

    private val viewModel: AuthorizationTokenViewModel by activityViewModels()
    private var _binding: AuthorizationFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var navigationActivity: NavigationActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AuthorizationFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        navigationActivity.toggleFavoriteButton(FavoriteState.Hidden)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationActivity = (requireActivity() as NavigationActivity)
        visibility = arguments?.getInt("visible")!!
        Log.d(BattlenetConstants.TAG, "Starting task to retrieve request token")
        setOberservers()
        viewModel.getBnetParams().value = if (Build.VERSION.SDK_INT >= 33) {
            arguments?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS, BattlenetOAuth2Params::class.java)
        } else {
            arguments?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
        }
    }

    private fun initWebView() {
        binding.webview.settings.javaScriptEnabled = true
        val authorizationUrl = viewModel.battlenetOAuth2Helper!!.authorizationUrl
        Log.d(BattlenetConstants.TAG, "Using authorizationUrl = $authorizationUrl")
        binding.webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                Log.d(BattlenetConstants.TAG, "onPageFinished : $url handled = ${viewModel.isHandled()}")
                if (url.startsWith(viewModel.getBnetParams().value!!.rederictUri)) {
                    binding.webview.visibility = View.INVISIBLE
                    if (!viewModel.isHandled()) {
                        lifecycleScope.launch {
                            viewModel.processToken(url)
                        }
                    }
                } else {
                    if (_binding != null) {
                        binding.webview.visibility = View.VISIBLE
                    }
                }
            }

            override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) {
                super.onReceivedHttpError(view, request, errorResponse)
                errorResponse?.let {
                    if (it.statusCode >= 400 && !viewModel.isHandled()) {
                        onTokenProcessed(signedIn = false, error = true)
                    }
                }
            }
        }
        binding.webview.loadUrl(authorizationUrl)
    }

    private fun setOberservers() {
        viewModel.getBnetParams().observe(viewLifecycleOwner) {
            viewModel.battlenetOAuth2Helper = BattlenetOAuth2Helper(it)
            initWebView()
        }

        viewModel.startActivity().observe(viewLifecycleOwner) {
            onTokenProcessed(it)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(BattlenetConstants.TAG, "onResume called with ${viewModel.hasLoggedIn().value!!}")
        if (viewModel.hasLoggedIn().value!!) {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun onTokenProcessed(signedIn: Boolean, error: Boolean = false) {
        navigationActivity.intent.putExtra(
            BattlenetConstants.BUNDLE_BNPARAMS,
            viewModel.getBnetParams().value
        )
        navigationActivity.setSignInError(error)
        navigationActivity.setSignedInStatus(signedIn)
        if (_binding !== null) {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}