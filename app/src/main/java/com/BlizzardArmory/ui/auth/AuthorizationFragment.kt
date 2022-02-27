package com.BlizzardArmory.ui.auth


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.BlizzardArmory.databinding.AuthorizationFragmentBinding
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.util.state.FavoriteState
import com.google.android.material.snackbar.Snackbar
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
    ): View? {
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
        Log.i(BattlenetConstants.TAG, "Starting task to retrieve request token")
        setOberservers()
        viewModel.getBnetParams().value =
            arguments?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
    }

    private fun initWebView() {
        binding.webview.settings.javaScriptEnabled = true
        val authorizationUrl = viewModel.battlenetOAuth2Helper!!.authorizationUrl
        Log.i(BattlenetConstants.TAG, "Using authorizationUrl = $authorizationUrl")
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
                    binding.webview.visibility = View.VISIBLE
                }
            }
        }
        binding.webview.loadUrl(authorizationUrl)
    }

    private fun setOberservers() {
        viewModel.getBnetParams().observe(this, {
            viewModel.battlenetOAuth2Helper = BattlenetOAuth2Helper(it)
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
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun onTokenProcessed(signedIn: Boolean) {
        if (!signedIn) {
            Snackbar.make(
                binding.root,
                "Oops! There was an error, please try again!",
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            Log.i("Singed In", "Closing auth fragment")
        }
        navigationActivity.intent.putExtra(
            BattlenetConstants.BUNDLE_BNPARAMS,
            viewModel.getBnetParams().value
        )
        navigationActivity.setSignedInStatus(signedIn)
        requireActivity().supportFragmentManager.popBackStack()
    }
}