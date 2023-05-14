package com.BlizzardArmory.ui.navigation

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Rect
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.NavigationActivityBarBinding
import com.BlizzardArmory.databinding.NavigationActivityBinding
import com.BlizzardArmory.model.UserInformation
import com.BlizzardArmory.model.menu.Menu
import com.BlizzardArmory.model.news.UserNews
import com.BlizzardArmory.model.warcraft.realm.connected.ConnectedRealms
import com.BlizzardArmory.network.ErrorMessages
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.network.oauth.OauthFlowStarter
import com.BlizzardArmory.ui.diablo.account.D3Fragment
import com.BlizzardArmory.ui.diablo.favorites.D3FavoriteFragment
import com.BlizzardArmory.ui.diablo.leaderboard.D3LeaderboardFragment
import com.BlizzardArmory.ui.news.list.NewsListFragment
import com.BlizzardArmory.ui.overwatch.account.OWFragment
import com.BlizzardArmory.ui.overwatch.account.OWPlatformChoiceDialog
import com.BlizzardArmory.ui.overwatch.favorites.OWFavoritesFragment
import com.BlizzardArmory.ui.overwatch.overwatchleague.OWLeagueFragment
import com.BlizzardArmory.ui.settings.SettingsFragment
import com.BlizzardArmory.ui.starcraft.leaderboard.SC2LeaderboardFragment
import com.BlizzardArmory.ui.starcraft.profile.SC2Fragment
import com.BlizzardArmory.ui.warcraft.account.AccountFragment
import com.BlizzardArmory.ui.warcraft.character.navigation.WoWNavFragment
import com.BlizzardArmory.ui.warcraft.favorites.WoWFavoritesFragment
import com.BlizzardArmory.ui.warcraft.guild.navigation.GuildNavFragment
import com.BlizzardArmory.ui.warcraft.mythicplusleaderboards.MPlusLeaderboardsFragment
import com.BlizzardArmory.ui.warcraft.mythicraidleaderboards.MRaidLeaderboardsFragment
import com.BlizzardArmory.ui.warcraft.pvpleaderboards.PvpLeaderboardsFragment
import com.BlizzardArmory.util.ConnectionStatus
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.ResourceNameToId.getStringIdFromString
import com.BlizzardArmory.util.events.FilterNewsEvent
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import com.BlizzardArmory.util.events.MenuItemEvent
import com.BlizzardArmory.util.state.FavoriteState
import com.BlizzardArmory.util.state.FragmentTag
import com.BlizzardArmory.util.state.RightPanelState
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.discord.panels.OverlappingPanelsLayout
import com.discord.panels.PanelState
import com.discord.panels.PanelsChildGestureRegionObserver
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.disposables.Disposable
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.Locale

class NavigationActivity : LocalizationActivity(),
    PanelsChildGestureRegionObserver.GestureRegionsListener {

    private var prefs: SharedPreferences? = null
    private val gson = GsonBuilder().create()

    private val REQUEST_CODE_IN_APP_UPDATE = 7500

    private var characterClicked: String = ""
    private var characterRealm: String = ""
    private var selectedRegion: String = ""
    private var newsOpened = false
    private lateinit var errorMessage: ErrorMessages

    var favorite: ImageView? = null

    private lateinit var barBinding: NavigationActivityBarBinding
    lateinit var binding: NavigationActivityBinding
    private val viewModel: NavigationViewModel by viewModels()
    private var viewStateDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults()
        }*/

        setObservers()

        installSplashScreen()

        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            handleUncaughtException(thread, throwable)
        }

        barBinding = NavigationActivityBarBinding.inflate(layoutInflater)
        binding = NavigationActivityBinding.inflate(layoutInflater)
        initActivity()
    }

    private fun initActivity() {
        val view = binding.root
        setContentView(view)
        binding.loadingCircle.visibility = View.VISIBLE
        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        if (prefs != null && prefs!!.getBoolean("signedIn", false)) {
            selectedRegion = prefs?.getString("region", "us").toString()
            viewModel.openLoginToBattleNet()
        }

        checkForAppUpdates()

        startWiFiNetworkCallback()
        startDataNetworkCallback()

        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        initLocale()

        binding.fragment.addOnLayoutChangeListener(PanelsChildGestureRegionObserver.Provider.get())

        favorite = binding.topBar.favorite

        errorMessage = ErrorMessages(this.resources)

        getUserNewsPreferences()
        setUserNews()

        if (supportFragmentManager.backStackEntryCount == 0) {
            openNewsFragment()
        }

        setNavigation()
        val menu = Gson().fromJson(
            resources.openRawResource(R.raw.menu_items).bufferedReader().use { it.readText() },
            Menu::class.java
        )
        binding.menu.apply {
            adapter = MenuAdapter(menu.menuList, context)
        }
    }

    private fun checkForAppUpdates() {
        val appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            appUpdateManager.startUpdateFlowForResult(
                appUpdateInfo,
                AppUpdateType.IMMEDIATE, this, REQUEST_CODE_IN_APP_UPDATE
            )
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

    private fun initLocale() {
        locale = if (!prefs?.contains("locale")!!) {
            prefs?.edit()?.putString("locale", "en_US")?.apply()
            "en_US"
        } else {
            prefs?.getString("locale", "en_US")!!
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

    private fun clearCredentials() {
        val webview = WebView(this)
        webview.settings.javaScriptEnabled = true
        webview.visibility = View.GONE
        webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                binding.loadingCircle.visibility = View.GONE
                Snackbar.make(binding.root, "Logout Successful", Snackbar.LENGTH_SHORT).show()
                viewModel.setSignedInStatus(false)
                resetBackStack()
            }
        }
        webview.loadUrl(NetworkUtils.LOGOUT_URL)
    }

    private fun setNavigation() {
        setSupportActionBar(binding.topBar.toolbarMain)
        binding.topBar.toolbarMain.setNavigationOnClickListener {
            if (binding.overlappingPanel.getSelectedPanel() == OverlappingPanelsLayout.Panel.START) {
                binding.overlappingPanel.closePanels()
            } else {
                binding.overlappingPanel.openStartPanel()
            }
        }
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = ""
    }

    private fun openNewsFragment() {
        val newsListFragment = NewsListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, newsListFragment, "news_fragment").commit()
        supportFragmentManager.executePendingTransactions()
    }

    private fun getUserNewsPreferences() {
        val userNewsPrefs = prefs?.getString("user_news", "DEFAULT")
        if (userNewsPrefs == "DEFAULT") {
            userNews = UserNews()
            prefs?.edit()?.putString("user_news", gson.toJson(userNews))?.apply()
        } else {
            userNews = gson.fromJson(userNewsPrefs, UserNews::class.java)
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onResume() {
        super.onResume()

        binding.overlappingPanel.registerStartPanelStateListeners(object :
            OverlappingPanelsLayout.PanelStateListener {
            override fun onPanelStateChange(panelState: PanelState) {
                viewModel.onStartPanelStateChange(panelState)
            }
        })

        binding.overlappingPanel.registerEndPanelStateListeners(object :
            OverlappingPanelsLayout.PanelStateListener {
            override fun onPanelStateChange(panelState: PanelState) {
                viewModel.onEndPanelStateChange(panelState)
            }
        })

        viewStateDisposable = viewModel.observeViewState()
            .subscribe { viewState ->
                handleViewState(viewState)
            }

        PanelsChildGestureRegionObserver.Provider.get().addGestureRegionsUpdateListener(this)
    }

    override fun onPause() {
        super.onPause()
        viewStateDisposable?.dispose()
        PanelsChildGestureRegionObserver.Provider.get().addGestureRegionsUpdateListener(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        PanelsChildGestureRegionObserver.Provider.get().unregister(binding.fragment)
    }

    fun setSignedInStatus(value: Boolean) {
        viewModel.setSignedInStatus(value)
    }

    fun toggleFavoriteButton(state: FavoriteState) {
        when (state) {
            FavoriteState.Hidden -> {
                favorite!!.visibility = View.GONE
                favorite!!.setImageResource(R.drawable.ic_star_border_black_24dp)
                favorite!!.tag = R.drawable.ic_star_border_black_24dp
            }
            FavoriteState.Shown -> {
                favorite!!.visibility = View.VISIBLE
                favorite!!.setImageResource(R.drawable.ic_star_border_black_24dp)
                favorite!!.tag = R.drawable.ic_star_border_black_24dp
            }
            FavoriteState.Full -> {
                favorite!!.visibility = View.VISIBLE
                favorite!!.setImageResource(R.drawable.ic_star_black_24dp)
                favorite!!.tag = R.drawable.ic_star_black_24dp
            }
        }
    }

    fun selectRightPanel(state: RightPanelState) {
        when (state) {
            RightPanelState.NewsSelection -> {
                binding.rightPanelGames.root.visibility = View.VISIBLE
                binding.rightPanelSc2.root.visibility = View.GONE
                binding.rightPanelD3.root.visibility = View.GONE
                binding.rightPanelWowMplus.root.visibility = View.GONE
                binding.rightPanelWowPvp.root.visibility = View.GONE
            }
            RightPanelState.D3Leaderboard -> {
                binding.rightPanelGames.root.visibility = View.GONE
                binding.rightPanelSc2.root.visibility = View.GONE
                binding.rightPanelD3.root.visibility = View.VISIBLE
                binding.rightPanelWowMplus.root.visibility = View.GONE
                binding.rightPanelWowPvp.root.visibility = View.GONE
            }
            RightPanelState.Sc2Leaderboard -> {
                binding.rightPanelGames.root.visibility = View.GONE
                binding.rightPanelSc2.root.visibility = View.VISIBLE
                binding.rightPanelD3.root.visibility = View.GONE
                binding.rightPanelWowMplus.root.visibility = View.GONE
                binding.rightPanelWowPvp.root.visibility = View.GONE
            }
            RightPanelState.WoWMPlusLeaderboard -> {
                binding.rightPanelGames.root.visibility = View.GONE
                binding.rightPanelSc2.root.visibility = View.GONE
                binding.rightPanelD3.root.visibility = View.GONE
                binding.rightPanelWowMplus.root.visibility = View.VISIBLE
                binding.rightPanelWowPvp.root.visibility = View.GONE
            }
            RightPanelState.WoWPvPLeaderboard -> {
                binding.rightPanelGames.root.visibility = View.GONE
                binding.rightPanelSc2.root.visibility = View.GONE
                binding.rightPanelD3.root.visibility = View.GONE
                binding.rightPanelWowMplus.root.visibility = View.GONE
                binding.rightPanelWowPvp.root.visibility = View.VISIBLE
            }
        }
    }

    private fun setObservers() {
        viewModel.getIsReady().observe(this) {
            if (it!!) {
                viewModel.getConnectedRealms()
                viewModel.initWoWServer()
                binding.loadingCircle.visibility = View.GONE
            }
        }

        viewModel.getBnetParams().observe(this) {
            if (viewModel.isSignedIn() == true) {
                viewModel.battlenetOAuth2Helper = BattlenetOAuth2Helper(it)
                viewModel.downloadUserInfo()
            } else {
                if (prefs != null && !prefs!!.getBoolean("signedIn", false)) {
                    login(it)
                } else if (!OauthFlowStarter.started) {
                    OauthFlowStarter.startOauthFlow(it, this, View.VISIBLE)
                }
            }
        }

        viewModel.getUserInformation().observe(this) {
            binding.topBar.barTitle.text = it?.battleTag
            userInformation = it
            when (OauthFlowStarter.lastOpenedFragmentNeedingOAuth) {
                FragmentTag.WOWFRAGMENT.name -> {
                    val fragment = AccountFragment()
                    openWoWAccount(fragment)
                }
                FragmentTag.D3FRAGMENT.name -> {
                    callD3Fragment(it?.battleTag, selectedRegion)
                }
                FragmentTag.SC2FRAGMENT.name -> {
                    val fragment = SC2Fragment()
                    openSc2Fragment(fragment)
                }
                FragmentTag.OVERWATCHFRAGMENT.name -> {
                    val fragment = OWFragment()
                    openOverwatchFragment(fragment)
                }
            }
        }

        viewModel.getSignedInStatus().observe(this) {
            if (it) {
                if(!newsOpened) {
                    openNewsFragment()
                }
                newsOpened = true
                prefs?.edit()?.putString("region", selectedRegion)?.apply()
                viewModel.setBnetParams(intent.getParcelableExtra(BattlenetConstants.BUNDLE_BNPARAMS)!!)
                val menu = Gson().fromJson(
                    resources.openRawResource(R.raw.menu_items_logged_in).bufferedReader()
                        .use { file -> file.readText() }, Menu::class.java
                )
                binding.menu.apply {
                    adapter = MenuAdapter(menu.menuList, context)
                }
            } else {
                viewModel.setUserInfirmation(null)
                val menu = Gson().fromJson(
                    resources.openRawResource(R.raw.menu_items).bufferedReader()
                        .use { file -> file.readText() }, Menu::class.java
                )
                binding.menu.apply {
                    adapter = MenuAdapter(menu.menuList, context)
                }
            }
            prefs?.edit()?.putBoolean("signedIn", it)?.apply()
        }

        viewModel.getWowConnectedRealms().observe(this) {
            realms = it
        }

        viewModel.getShowErrorDialog().observe(this) {
            callErrorAlertDialog(viewModel.errorCode.value!!)
        }

        viewModel.getMedia().observe(this) {
            callWoWCharacterFragment(characterClicked, characterRealm, selectedRegion)
        }
    }

    private fun login(it: BattlenetOAuth2Params) {
        val dialog = DialogPrompt(this)
        dialog.addTitle("Select your region to login", 18F, "title")
            .addSpinner(resources.getStringArray(R.array.regions), "region_spinner")
            .addButtons(
                dialog.Button(
                    "Login",
                    16F,
                    {
                        selectedRegion =
                            (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString()
                        if (resources.getStringArray(R.array.regions)
                                .slice(1 until resources.getStringArray(R.array.regions).size)
                                .contains(selectedRegion)
                        ) {
                            OauthFlowStarter.startOauthFlow(it, this, View.VISIBLE)
                            dialog.dismiss()
                        } else {
                            Snackbar.make(
                                dialog.tagMap["main_container"]!!,
                                "Please select your region",
                                Snackbar.LENGTH_SHORT
                            )
                                .show()
                        }
                    },
                    "login_button"
                ),
                dialog.Button(
                    "Cancel",
                    16F,
                    { dialog.dismiss() },
                    "login_button"
                )
            ).show()
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun localeSelectedReceived(localeSelectedEvent: LocaleSelectedEvent) {
        if (!localeSelectedEvent.locale.contains((getCurrentLanguage().language + "_" + getCurrentLanguage().country))) {
            when (localeSelectedEvent.locale) {
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
    }

    private fun handleUncaughtException(thread: Thread?, e: Throwable) {
        Log.e("Crash Prevented", e.message!!, e)
        FirebaseCrashlytics.getInstance().log(e.message!!)
    }

    private fun setUserNews() {
        binding.rightPanelGames.blizzardSwitch.isChecked = userNews?.blizzNews!!
        binding.rightPanelGames.wowSwitch.isChecked = userNews?.wowNews!!
        binding.rightPanelGames.d3Switch.isChecked = userNews?.d3News!!
        binding.rightPanelGames.sc2Switch.isChecked = userNews?.sc2News!!
        binding.rightPanelGames.owSwitch.isChecked = userNews?.owNews!!
        binding.rightPanelGames.hsSwitch.isChecked = userNews?.hsNews!!
        binding.rightPanelGames.hotsSwitch.isChecked = userNews?.hotsNews!!

        binding.rightPanelGames.blizzardSwitch.setOnClickListener {
            userNews?.blizzNews = !userNews?.blizzNews!!
            updateUserNews()
        }
        binding.rightPanelGames.wowSwitch.setOnClickListener {
            userNews?.wowNews = !userNews?.wowNews!!
            updateUserNews()
        }
        binding.rightPanelGames.d3Switch.setOnClickListener {
            userNews?.d3News = !userNews?.d3News!!
            updateUserNews()
        }
        binding.rightPanelGames.sc2Switch.setOnClickListener {
            userNews?.sc2News = !userNews?.sc2News!!
            updateUserNews()
        }
        binding.rightPanelGames.owSwitch.setOnClickListener {
            userNews?.owNews = !userNews?.owNews!!
            updateUserNews()
        }
        binding.rightPanelGames.hsSwitch.setOnClickListener {
            userNews?.hsNews = !userNews?.hsNews!!
            updateUserNews()
        }
        binding.rightPanelGames.hotsSwitch.setOnClickListener {
            userNews?.hotsNews = !userNews?.hotsNews!!
            updateUserNews()
        }
    }

    private fun updateUserNews() {
        prefs?.edit()?.putString("user_news", gson.toJson(userNews))?.apply()
        EventBus.getDefault().post(FilterNewsEvent(true))
    }

    private fun getErrorMessage(responseCode: Int): String {
        return when (responseCode) {
            in 201..499 -> {
                errorMessage.UNEXPECTED
            }
            500 -> {
                errorMessage.BLIZZ_SERVERS_DOWN
            }
            else -> {
                errorMessage.TURN_ON_CONNECTION_MESSAGE
            }
        }
    }

    private fun getErrorTitle(responseCode: Int): String {
        return when (responseCode) {
            in 201..499 -> {
                errorMessage.UNAVAILABLE
            }
            500 -> {
                errorMessage.SERVERS_ERROR
            }
            else -> {
                errorMessage.NO_INTERNET
            }
        }
    }

    private fun callErrorAlertDialog(responseCode: Int) {
        val dialog = DialogPrompt(this)

        if (responseCode == 404 || responseCode == 403 || responseCode == 500) {
            dialog.setOnCancelListener { finish() }
        } else {
            dialog.setOnCancelListener { viewModel.downloadUserInfo() }
        }

        dialog.addTitle(getErrorTitle(responseCode), 20f, "title")
            .addMessage(getErrorMessage(responseCode), 18f, "message")
            .addButtons(
                dialog.Button(errorMessage.RETRY, 18f, { dialog.dismiss() }, "retry"),
                dialog.Button(
                    errorMessage.BACK, 18f,
                    {
                        dialog.dismiss()
                        onBackPressed()
                    }, "back"
                )
            ).show()
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun menuItemClickedReceived(menuItem: MenuItemEvent) {
        if (viewModel.getIsReady().value != true) {
            return
        }
        OauthFlowStarter.lastOpenedFragmentNeedingOAuth = ""
        val fragment: Fragment
        val searchDialog = DialogPrompt(this)
        when (this.resources.getString(getStringIdFromString(menuItem.menuItem.string, this))) {
            this.resources.getString(R.string.home) -> {
                toggleFavoriteButton(FavoriteState.Hidden)
                resetBackStack()
                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.login) -> {
                viewModel.openLoginToBattleNet()
                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.logout) -> {
                binding.overlappingPanel.closePanels()
                searchDialog.addTitle("Logout", 18F, "title")
                    .addMessage("Do you want to log out?", 16F, "message")
                    .addButtons(
                        searchDialog.Button(
                            "Ok", 16F,
                            {
                                searchDialog.dismiss()
                                binding.loadingCircle.visibility = View.VISIBLE
                                clearCredentials()
                            },
                            "close_button",
                        ),
                        searchDialog.Button(
                            "Cancel", 16F,
                            {
                                searchDialog.dismiss()
                            },
                            "cancel_button",
                        )
                    )
                    .show()

            }
            resources.getString(R.string.account) -> {
                toggleFavoriteButton(FavoriteState.Hidden)
                when (this.resources.getString(
                    getStringIdFromString(
                        menuItem.menuItem.parent,
                        this
                    )
                )) {
                    resources.getString(R.string.world_of_warcraft) -> {
                        fragment = AccountFragment()
                        if (viewModel.isSignedIn() == false) {
                            OauthFlowStarter.lastOpenedFragmentNeedingOAuth =
                                FragmentTag.WOWFRAGMENT.name
                            viewModel.openLoginToBattleNet()
                        } else {
                            openWoWAccount(fragment)
                        }
                    }
                    resources.getString(R.string.diablo_3) -> {
                        callD3Fragment(
                            viewModel.getUserInformation().value?.battleTag,
                            NetworkUtils.region
                        )
                    }
                    resources.getString(R.string.overwatch) -> {
                        fragment = OWFragment()
                        if (viewModel.isSignedIn() == false) {
                            OauthFlowStarter.lastOpenedFragmentNeedingOAuth =
                                FragmentTag.OVERWATCHFRAGMENT.name
                            viewModel.openLoginToBattleNet()
                        } else {
                            openOverwatchFragment(fragment)
                        }
                    }
                    resources.getString(R.string.starcraft_2) -> {
                        fragment = SC2Fragment()
                        if (viewModel.isSignedIn() == false) {
                            OauthFlowStarter.lastOpenedFragmentNeedingOAuth =
                                FragmentTag.SC2FRAGMENT.name
                            viewModel.openLoginToBattleNet()
                        } else {
                            openSc2Fragment(fragment)
                        }
                    }
                }
                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.search_profile) -> {
                when (this.resources.getString(
                    getStringIdFromString(
                        menuItem.menuItem.parent,
                        this
                    )
                )) {
                    resources.getString(R.string.overwatch) -> {
                        OWPlatformChoiceDialog.overwatchPrompt(this, supportFragmentManager)
                    }
                    resources.getString(R.string.diablo_3) -> {
                        searchDialog.addTitle("Enter a BattleTag", 18F, "battletag")
                            .addEditText("btag_field")
                            .addSpinner(resources.getStringArray(R.array.regions), "region_spinner")
                            .addButtons(
                                searchDialog.Button(
                                    "Search",
                                    16F,
                                    { searchD3Profile(searchDialog) },
                                    "search_button"
                                )
                            )
                            .show()
                    }
                }
                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.search_character) -> {
                searchDialog.addTitle("Character Name", 18F, "character_label")
                    .addEditText("character_field")
                    .addMessage("Realm", 18F, "realm_label")
                    .addAutoCompleteEditText(
                        "realm_field",
                        viewModel.getWowConnectedRealms().value!!.values.flatMap { it.results }
                            .flatMap { data -> data.connectedRealm.realms }.map { it.name }
                            .flatMap { it.getAllNames() }.distinct()
                    )
                    .addSpinner(resources.getStringArray(R.array.regions), "region_spinner")
                    .addButtons(
                        searchDialog.Button(
                            "GO",
                            16F,
                            { validSearchedWoWChracterFields(searchDialog) },
                            "search_button"
                        )
                    )
                    .setOnCancelListener {
                        binding.loadingCircle.visibility = View.GONE
                    }
                    .show()
                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.search_guild) -> {
                searchDialog.addTitle("Guild Name", 18F, "guild_label")
                    .addEditText("guild_field")
                    .addMessage("Realm", 18F, "realm_label")
                    .addAutoCompleteEditText(
                        "realm_field",
                        viewModel.getWowConnectedRealms().value!!.values.flatMap { it.results }
                            .flatMap { data -> data.connectedRealm.realms }.map { it.name }
                            .flatMap { it.getAllNames() }.distinct()
                    )
                    .addSpinner(resources.getStringArray(R.array.regions), "region_spinner")
                    .addButtons(
                        searchDialog.Button(
                            "GO",
                            16F,
                            { validSearchedWoWGuildFields(searchDialog) },
                            "search_button"
                        )
                    )
                    .setOnCancelListener {
                        binding.loadingCircle.visibility = View.GONE
                    }
                    .show()

                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.favorites) -> {
                resetBackStack()
                toggleFavoriteButton(FavoriteState.Hidden)
                when (this.resources.getString(
                    getStringIdFromString(
                        menuItem.menuItem.parent,
                        this
                    )
                )) {
                    resources.getString(R.string.world_of_warcraft) -> {
                        fragment = WoWFavoritesFragment()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragment, fragment, FragmentTag.WOWFAVORITES.name)
                            .addToBackStack("wow_fav")
                            .commit()
                        supportFragmentManager.executePendingTransactions()
                    }
                    resources.getString(R.string.diablo_3) -> {
                        fragment = D3FavoriteFragment()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragment, fragment, FragmentTag.D3FAVORITES.name)
                            .addToBackStack("d3_fav")
                            .commit()
                        supportFragmentManager.executePendingTransactions()
                    }
                    resources.getString(R.string.overwatch) -> {
                        fragment = OWFavoritesFragment()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragment, fragment, FragmentTag.OWFAVORITES.name)
                            .addToBackStack("ow_fav")
                            .commit()
                        supportFragmentManager.executePendingTransactions()
                    }
                }
                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.leaderboards) -> {
                resetBackStack()
                toggleFavoriteButton(FavoriteState.Hidden)
                when (this.resources.getString(
                    getStringIdFromString(
                        menuItem.menuItem.parent,
                        this
                    )
                )) {
                    resources.getString(R.string.diablo_3) -> {
                        fragment = D3LeaderboardFragment()
                        resetBackStack()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragment, fragment, FragmentTag.D3LEADERBOARD.name)
                            .addToBackStack(FragmentTag.D3LEADERBOARD.name).commit()
                        supportFragmentManager.executePendingTransactions()
                    }
                    resources.getString(R.string.starcraft_2) -> {
                        fragment = SC2LeaderboardFragment()
                        resetBackStack()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragment, fragment, FragmentTag.SC2LEADERBOARD.name)
                            .addToBackStack("sc2_leaderboard").commit()
                        supportFragmentManager.executePendingTransactions()
                    }
                }
                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.raid_leaderboards) -> {
                toggleFavoriteButton(FavoriteState.Hidden)
                fragment = MRaidLeaderboardsFragment()
                resetBackStack()
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment, fragment, FragmentTag.WOWRAIDLEADERBOARD.name)
                    .addToBackStack("mraid_leaderboard").commit()
                supportFragmentManager.executePendingTransactions()
                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.mplus_leaderboards) -> {
                toggleFavoriteButton(FavoriteState.Hidden)
                fragment = MPlusLeaderboardsFragment()
                resetBackStack()
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment, fragment, FragmentTag.WOWMPLUSLEADERBOARD.name)
                    .addToBackStack("mplus_leaderboard").commit()
                supportFragmentManager.executePendingTransactions()
                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.pvp_leaderboards) -> {
                toggleFavoriteButton(FavoriteState.Hidden)
                fragment = PvpLeaderboardsFragment()
                resetBackStack()
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment, fragment, FragmentTag.WOWPVPLEADERBOARD.name)
                    .addToBackStack("pvp_leaderboard").commit()
                supportFragmentManager.executePendingTransactions()
                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.overwatch_league) -> {
                toggleFavoriteButton(FavoriteState.Hidden)
                fragment = OWLeagueFragment()
                resetBackStack()
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment, fragment, FragmentTag.OVERWATCHLEAGUE.name)
                    .addToBackStack("overwatch_league").commit()
                supportFragmentManager.executePendingTransactions()
                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.settingsTitle) -> {
                toggleFavoriteButton(FavoriteState.Hidden)
                fragment = SettingsFragment()
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment, fragment, FragmentTag.SETTINGSFRAGMENT.name)
                    .addToBackStack("settings")
                    .commit()
                supportFragmentManager.executePendingTransactions()
                binding.overlappingPanel.closePanels()
            }
        }
    }

    private fun openOverwatchFragment(fragment: OWFragment) {
        val bundle = Bundle()
        bundle.putString("username", viewModel.getUserInformation().value?.battleTag)
        bundle.putString("platform", "pc")
        fragment.arguments = bundle
        resetBackStack()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment,
                fragment,
                FragmentTag.OVERWATCHFRAGMENT.name
            )
            .addToBackStack("ow_account").commit()
        supportFragmentManager.executePendingTransactions()
    }

    private fun openSc2Fragment(fragment: Fragment) {
        resetBackStack()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment, fragment, FragmentTag.SC2FRAGMENT.name)
            .addToBackStack("sc2")
            .commit()
        binding.overlappingPanel.closePanels()
    }

    private fun openWoWAccount(fragment: Fragment) {
        resetBackStack()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment, fragment, FragmentTag.WOWFRAGMENT.name)
            .addToBackStack("wow_account").commit()
        supportFragmentManager.executePendingTransactions()
    }

    private fun resetBackStack() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        if (supportFragmentManager.findFragmentById(R.id.fragment) != null) {
            supportFragmentManager.beginTransaction()
                .remove(supportFragmentManager.findFragmentById(R.id.fragment)!!).commit()
        }
        openNewsFragment()
        supportFragmentManager.executePendingTransactions()
    }

    private fun searchD3Profile(dialog: DialogPrompt) {
        when {
            !(dialog.tagMap["btag_field"] as EditText).text.toString()
                .matches(".+#[0-9]+".toRegex()) -> {
                Snackbar.make(
                    dialog.tagMap["main_container"]!!,
                    "Please enter a BattleTag",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }
            (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString()
                .equals("Select Region", ignoreCase = true) -> {
                Snackbar.make(
                    dialog.tagMap["main_container"]!!,
                    "Please enter the region",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }
            else -> {
                dialog.dismiss()
                callD3Fragment(
                    (dialog.tagMap["btag_field"] as EditText).text.toString().replace("#", "-"),
                    (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString()
                )
            }
        }
    }

    private fun validSearchedWoWChracterFields(dialog: DialogPrompt) {
        when {
            (dialog.tagMap["character_field"] as EditText).text.toString() == "" -> {
                Snackbar.make(
                    dialog.tagMap["main_container"]!!,
                    "Please enter the character name",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }
            (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString()
                .equals("Select Region", ignoreCase = true) -> {
                Snackbar.make(
                    dialog.tagMap["main_container"]!!,
                    "Please select the region",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }
            else -> {
                characterClicked = (dialog.tagMap["character_field"] as EditText).text.toString()
                    .lowercase(Locale.getDefault()).replace(" ", "-")
                selectedRegion =
                    (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString()
                if (viewModel.getWowConnectedRealms().value!![selectedRegion]!!.results.flatMap { data -> data.connectedRealm.realms }
                        .map { it.name }.flatMap { it.getAllNames() }
                        .any { it == (dialog.tagMap["realm_field"] as AutoCompleteTextView).text.toString() }) {
                    binding.loadingCircle.visibility = View.VISIBLE
                    characterRealm =
                        viewModel.getWowConnectedRealms().value!![selectedRegion]!!.results.flatMap { data -> data.connectedRealm.realms }
                            .find {
                                it.name.getAllNames()
                                    .contains((dialog.tagMap["realm_field"] as AutoCompleteTextView).text.toString())
                            }?.slug!!
                    viewModel.downloadMedia(characterClicked, characterRealm, selectedRegion)
                    dialog.dismiss()
                } else {
                    Snackbar.make(
                        dialog.tagMap["main_container"]!!,
                        "Please enter a valid realm for this region",
                        Snackbar.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    private fun validSearchedWoWGuildFields(dialog: DialogPrompt) {
        when {
            (dialog.tagMap["guild_field"] as EditText).text.toString() == "" -> {
                Snackbar.make(
                    dialog.tagMap["main_container"]!!,
                    "Please enter the guild name",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }
            (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString()
                .equals("Select Region", ignoreCase = true) -> {
                Snackbar.make(
                    dialog.tagMap["main_container"]!!,
                    "Please select the region",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }
            else -> {
                val guildName = (dialog.tagMap["guild_field"] as EditText).text.toString()
                    .lowercase(Locale.getDefault())
                val selectedRegion =
                    (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString()
                val guildRealm: String
                if (viewModel.getWowConnectedRealms().value!![selectedRegion]!!.results.flatMap { data -> data.connectedRealm.realms }
                        .map { it.name }.flatMap { it.getAllNames() }
                        .any { it == (dialog.tagMap["realm_field"] as AutoCompleteTextView).text.toString() }) {
                    binding.loadingCircle.visibility = View.VISIBLE
                    guildRealm =
                        viewModel.getWowConnectedRealms().value!![selectedRegion]!!.results.flatMap { data -> data.connectedRealm.realms }
                            .find {
                                it.name.getAllNames()
                                    .contains((dialog.tagMap["realm_field"] as AutoCompleteTextView).text.toString())
                            }?.slug!!
                    callWoWGuildFragment(guildName, selectedRegion, guildRealm, dialog)
                    dialog.dismiss()
                } else {
                    Snackbar.make(
                        dialog.tagMap["main_container"]!!,
                        "Please enter a valid realm for this region",
                        Snackbar.LENGTH_SHORT
                    )
                        .show()
                }

            }
        }
    }

    private fun callWoWGuildFragment(
        guildName: String,
        selectedRegion: String,
        guildRealm: String,
        dialog: DialogPrompt
    ) {
        val fragment = GuildNavFragment()
        val bundle = Bundle()
        bundle.putString("guildName", guildName)
        bundle.putString("region", selectedRegion)
        bundle.putString("realm", guildRealm)
        fragment.arguments = bundle
        resetBackStack()
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
            .add(R.id.fragment, fragment, FragmentTag.WOWGUILDNAVFRAGMENT.name)
            .addToBackStack("wow_guild").commit()
        supportFragmentManager.executePendingTransactions()
        dialog.dismiss()
        binding.loadingCircle.visibility = View.GONE
    }

    private fun callD3Fragment(battletag: String?, region: String) {
        val fragment: Fragment = D3Fragment()
        if (viewModel.isSignedIn() == false) {
            OauthFlowStarter.lastOpenedFragmentNeedingOAuth = FragmentTag.D3FRAGMENT.name
            viewModel.openLoginToBattleNet()
        } else {
            val bundle = Bundle()
            bundle.putString("battletag", battletag)
            bundle.putString("region", region)
            fragment.arguments = bundle
            resetBackStack()
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
                .add(R.id.fragment, fragment, FragmentTag.D3FRAGMENT.name)
                .addToBackStack("d3_account").commit()
            supportFragmentManager.executePendingTransactions()
        }
        binding.loadingCircle.visibility = View.GONE
    }

    private fun callWoWCharacterFragment(characterClicked: String, characterRealm: String, selectedRegion: String) {
        if (supportFragmentManager.primaryNavigationFragment != null && supportFragmentManager.primaryNavigationFragment!!.isVisible) {
            supportFragmentManager.beginTransaction()
                .remove(supportFragmentManager.primaryNavigationFragment!!).commit()
        }
        val mediaString = Gson().toJson(viewModel.getMedia().value)
        val woWNavFragment =
            WoWNavFragment.newInstance(
                characterClicked,
                characterRealm,
                mediaString,
                selectedRegion
            )
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
            .add(R.id.fragment, woWNavFragment, FragmentTag.NAVFRAGMENT.name)
            .addToBackStack(FragmentTag.NAVFRAGMENT.name).commit()
        supportFragmentManager.executePendingTransactions()
        binding.loadingCircle.visibility = View.GONE
    }


    override fun onGestureRegionsUpdate(gestureRegions: List<Rect>) {
        binding.overlappingPanel.setChildGestureRegions(gestureRegions)
    }

    private fun handleViewState(viewState: NavigationViewModel.ViewState) {
        binding.overlappingPanel.handleStartPanelState(viewState.startPanelState)
        binding.overlappingPanel.handleEndPanelState(viewState.endPanelState)
    }

    companion object {
        var selectedRegion = "US"
        var locale = "en_US"
        var userInformation: UserInformation? = null
        lateinit var realms: MutableMap<String, ConnectedRealms>
        var userNews: UserNews? = null
    }
}