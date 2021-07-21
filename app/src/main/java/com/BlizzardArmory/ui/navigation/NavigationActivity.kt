package com.BlizzardArmory.ui.navigation

import android.content.SharedPreferences
import android.graphics.Rect
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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.NavigationActivityBarBinding
import com.BlizzardArmory.databinding.NavigationActivityBinding
import com.BlizzardArmory.model.MenuItem
import com.BlizzardArmory.model.UserInformation
import com.BlizzardArmory.model.news.UserNews
import com.BlizzardArmory.model.warcraft.realm.connected.ConnectedRealms
import com.BlizzardArmory.network.ErrorMessages
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.network.oauth.OauthFlowStarter
import com.BlizzardArmory.ui.diablo.account.D3Fragment
import com.BlizzardArmory.ui.diablo.favorites.D3FavoriteFragment
import com.BlizzardArmory.ui.diablo.leaderboard.D3LeaderboardFragment
import com.BlizzardArmory.ui.news.NewsListFragment
import com.BlizzardArmory.ui.overwatch.OWFragment
import com.BlizzardArmory.ui.overwatch.OWPlatformChoiceDialog
import com.BlizzardArmory.ui.overwatch.favorites.OWFavoritesFragment
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
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.events.FilterNewsEvent
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import com.BlizzardArmory.util.events.MenuItemClickEvent
import com.BlizzardArmory.util.state.FavoriteState
import com.BlizzardArmory.util.state.RightPanelState
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.discord.panels.OverlappingPanelsLayout
import com.discord.panels.PanelState
import com.discord.panels.PanelsChildGestureRegionObserver
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.disposables.Disposable
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

class NavigationActivity : LocalizationActivity(),
    PanelsChildGestureRegionObserver.GestureRegionsListener {

    private var prefs: SharedPreferences? = null
    private val gson = GsonBuilder().create()

    private var characterClicked: String = ""
    private var characterRealm: String = ""
    private var selectedRegion: String = ""

    private lateinit var errorMessage: ErrorMessages

    var favorite: ImageView? = null

    lateinit var barBinding: NavigationActivityBarBinding
    lateinit var binding: NavigationActivityBinding
    private val viewModel: NavigationViewModel by viewModels()
    private var viewStateDisposable: Disposable? = null

    private val menuList = arrayListOf<MenuItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            handleUncaughtException(thread, throwable)
        }
        barBinding = NavigationActivityBarBinding.inflate(layoutInflater)
        binding = NavigationActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.fragment.addOnLayoutChangeListener(PanelsChildGestureRegionObserver.Provider.get())

        setOberservers()

        favorite = binding.topBar.favorite

        errorMessage = ErrorMessages(this.resources)
        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        viewModel.getBnetParams().value = this.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)

        getUserNewsPreferences()
        setUserNews()
        if (supportFragmentManager.backStackEntryCount == 0) {
            openNewsFragment()
        }

        viewModel.initWoWServer()

        setNavigation()

        createMenuList()

        binding.menu.apply {
            adapter = MenuAdapter(menuList, context)
            (binding.menu.adapter as MenuAdapter).toggleSubMenu(false, menuList.find { it.title == resources.getString(R.string.world_of_warcraft) }!!)
            (binding.menu.adapter as MenuAdapter).toggleSubMenu(false, menuList.find { it.title == resources.getString(R.string.diablo_3) }!!)
            (binding.menu.adapter as MenuAdapter).toggleSubMenu(false, menuList.find { it.title == resources.getString(R.string.overwatch) }!!)
            (binding.menu.adapter as MenuAdapter).toggleSubMenu(false, menuList.find { it.title == resources.getString(R.string.starcraft_2) }!!)
        }

        if (savedInstanceState == null) {
            when (OauthFlowStarter.lastOpenedFragmentNeedingOAuth) {
                "AccountFragment" -> {
                    var fragment = AccountFragment()
                    openWoWAccount(fragment)
                }
                "D3Fragment" -> {
                    callD3Fragment(OauthFlowStarter.bundle?.getString("battletag")!!, OauthFlowStarter.bundle?.getString("region")!!)
                }
            }
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
                finish()
            }
        }
        webview.loadUrl(NetworkUtils.LOGOUT_URL)
    }

    private fun createMenuList() {
        menuList.add(MenuItem(true, "", resources.getString(R.string.home), R.drawable.ic_baseline_home_24, false))
        menuList.add(MenuItem(true, "", resources.getString(R.string.world_of_warcraft), R.drawable.wow_icon, false))
        menuList.add(MenuItem(false, resources.getString(R.string.world_of_warcraft), resources.getString(R.string.account), R.drawable.ic_baseline_account_circle_24, false))
        menuList.add(MenuItem(false, resources.getString(R.string.world_of_warcraft), resources.getString(R.string.search_character), R.drawable.rep_search, false))
        menuList.add(MenuItem(false, resources.getString(R.string.world_of_warcraft), resources.getString(R.string.search_guild), R.drawable.rep_search, false))
        menuList.add(MenuItem(false, resources.getString(R.string.world_of_warcraft), resources.getString(R.string.raid_leaderboards), R.drawable.ic_baseline_leaderboard_24, false))
        menuList.add(MenuItem(false, resources.getString(R.string.world_of_warcraft), resources.getString(R.string.mplus_leaderboards), R.drawable.ic_baseline_leaderboard_24, false))
        menuList.add(MenuItem(false, resources.getString(R.string.world_of_warcraft), resources.getString(R.string.pvp_leaderboards), R.drawable.ic_baseline_leaderboard_24, false))
        menuList.add(MenuItem(false, resources.getString(R.string.world_of_warcraft), resources.getString(R.string.favorites), R.drawable.ic_star_black_24dp, false))
        menuList.add(MenuItem(true, "", resources.getString(R.string.diablo_3), R.drawable.diablo3_icon, false))
        menuList.add(MenuItem(false, resources.getString(R.string.diablo_3), resources.getString(R.string.account), R.drawable.ic_baseline_account_circle_24, false))
        menuList.add(MenuItem(false, resources.getString(R.string.diablo_3), resources.getString(R.string.search_profile), R.drawable.rep_search, false))
        menuList.add(MenuItem(false, resources.getString(R.string.diablo_3), resources.getString(R.string.leaderboards), R.drawable.ic_baseline_leaderboard_24, false))
        menuList.add(MenuItem(false, resources.getString(R.string.diablo_3), resources.getString(R.string.favorites), R.drawable.ic_star_black_24dp, false))
        menuList.add(MenuItem(true, "", resources.getString(R.string.starcraft_2), R.drawable.sc2_icon, false))
        menuList.add(MenuItem(false, resources.getString(R.string.starcraft_2), resources.getString(R.string.account), R.drawable.ic_baseline_account_circle_24, false))
        menuList.add(MenuItem(false, resources.getString(R.string.starcraft_2), resources.getString(R.string.leaderboards), R.drawable.ic_baseline_leaderboard_24, false))
        menuList.add(MenuItem(true, "", resources.getString(R.string.overwatch), R.drawable.overwatch_icon, false))
        menuList.add(MenuItem(false, resources.getString(R.string.overwatch), resources.getString(R.string.account), R.drawable.ic_baseline_account_circle_24, false))
        menuList.add(MenuItem(false, resources.getString(R.string.overwatch), resources.getString(R.string.search_profile), R.drawable.rep_search, false))
        menuList.add(MenuItem(false, resources.getString(R.string.overwatch), resources.getString(R.string.favorites), R.drawable.ic_star_black_24dp, false))
        menuList.add(MenuItem(true, "", resources.getString(R.string.settingsTitle), R.drawable.settings, false))
        menuList.add(MenuItem(true, "", resources.getString(R.string.logout), R.drawable.logout_icon, false))
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
        Log.i("REMOVED GESTURE", "REMOVED")
        PanelsChildGestureRegionObserver.Provider.get().remove(binding.fragment.id)
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

    private fun setOberservers() {
        viewModel.getBnetParams().observe(this, {
            viewModel.battlenetOAuth2Helper = BattlenetOAuth2Helper(it)
            viewModel.getConnectedRealms()
            viewModel.downloadUserInfo()
        })

        viewModel.getWowConnectedRealms().observe(this, {
            realms = it
        })

        viewModel.getUserInformation().observe(this, {
            userInformation = it
            binding.topBar.barTitle.text = userInformation?.battleTag
        })

        viewModel.getErrorCode().observe(this, {
            callErrorAlertDialog(it)
        })

        viewModel.getMedia().observe(this, {
            callWoWCharacterFragment(characterClicked, characterRealm, selectedRegion)
        })
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun localeSelectedReceived(localeSelectedEvent: LocaleSelectedEvent) {
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
    public fun menuItemClickedReceived(menuItem: MenuItemClickEvent) {
        val fragment: Fragment
        val searchDialog = DialogPrompt(this)
        when (menuItem.menuItem.title) {
            this.resources.getString(R.string.home) -> {
                toggleFavoriteButton(FavoriteState.Hidden)
                resetBackStack()
                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.logout) -> {

                binding.loadingCircle.visibility = View.VISIBLE
                clearCredentials()
            }
            resources.getString(R.string.diablo_3) -> {
                (binding.menu.adapter as MenuAdapter).toggleSubMenu(!menuItem.menuItem.toggle, menuItem.menuItem)
                menuList.find { it.title == resources.getString(R.string.diablo_3) }?.toggle = !menuItem.menuItem.toggle
            }
            resources.getString(R.string.overwatch) -> {
                (binding.menu.adapter as MenuAdapter).toggleSubMenu(!menuItem.menuItem.toggle, menuItem.menuItem)
                menuList.find { it.title == resources.getString(R.string.overwatch) }?.toggle = !menuItem.menuItem.toggle
            }
            resources.getString(R.string.world_of_warcraft) -> {
                (binding.menu.adapter as MenuAdapter).toggleSubMenu(!menuItem.menuItem.toggle, menuItem.menuItem)
                menuList.find { it.title == resources.getString(R.string.world_of_warcraft) }?.toggle = !menuItem.menuItem.toggle
            }
            resources.getString(R.string.starcraft_2) -> {
                (binding.menu.adapter as MenuAdapter).toggleSubMenu(!menuItem.menuItem.toggle, menuItem.menuItem)
                menuList.find { it.title == resources.getString(R.string.starcraft_2) }?.toggle = !menuItem.menuItem.toggle
            }
            resources.getString(R.string.account) -> {
                toggleFavoriteButton(FavoriteState.Hidden)
                when (menuItem.menuItem.parent) {
                    resources.getString(R.string.world_of_warcraft) -> {
                        fragment = AccountFragment()
                        openWoWAccount(fragment)
                    }
                    resources.getString(R.string.diablo_3) -> {
                        callD3Fragment(userInformation?.battleTag!!, NetworkUtils.region)
                    }
                    resources.getString(R.string.overwatch) -> {
                        val bundle = Bundle()
                        fragment = OWFragment()
                        bundle.putString("username", userInformation?.battleTag)
                        bundle.putString("platform", "pc")
                        fragment.arguments = bundle
                        resetBackStack()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment, fragment, "overwatchfragment")
                            .addToBackStack("ow_account").commit()
                        supportFragmentManager.executePendingTransactions()
                    }
                    resources.getString(R.string.starcraft_2) -> {
                        fragment = SC2Fragment()
                        resetBackStack()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragment, fragment, "sc2fragment").addToBackStack("sc2")
                            .commit()
                        binding.overlappingPanel.closePanels()
                    }
                }
                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.search_profile) -> {
                when (menuItem.menuItem.parent) {
                    resources.getString(R.string.overwatch) -> {
                        OWPlatformChoiceDialog.overwatchPrompt(this, supportFragmentManager)
                    }
                    resources.getString(R.string.diablo_3) -> {
                        searchDialog.addTitle("Enter a BattleTag", 18F, "battletag")
                            .addEditText("btag_field")
                            .addSpinner(resources.getStringArray(R.array.regions), "region_spinner")
                            .addButtons(searchDialog.Button("Search", 16F, { searchD3Profile(searchDialog) }, "search_button"))
                            .show()
                    }
                }
                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.search_character) -> {
                binding.loadingCircle.visibility = View.VISIBLE
                searchDialog.addTitle("Character Name", 18F, "character_label")
                    .addEditText("character_field")
                    .addMessage("Realm", 18F, "realm_label")
                    .addAutoCompleteEditText("realm_field", viewModel.getWowConnectedRealms().value!!.values.flatMap { it.results }
                        .flatMap { data -> data.connectedRealm.realms }.map { it.name }
                        .flatMap { it.getAllNames() }.distinct())
                    .addSpinner(resources.getStringArray(R.array.regions), "region_spinner")
                    .addButtons(searchDialog.Button("GO", 16F, { validSearchedWoWChracterFields(searchDialog) }, "search_button"))
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
                    .addAutoCompleteEditText("realm_field", viewModel.getWowConnectedRealms().value!!.values.flatMap { it.results }
                        .flatMap { data -> data.connectedRealm.realms }.map { it.name }
                        .flatMap { it.getAllNames() }.distinct())
                    .addSpinner(resources.getStringArray(R.array.regions), "region_spinner")
                    .addButtons(searchDialog.Button("GO", 16F, { validSearchedWoWGuildFields(searchDialog) }, "search_button"))
                    .setOnCancelListener {
                        binding.loadingCircle.visibility = View.GONE
                    }
                    .show()

                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.favorites) -> {
                resetBackStack()
                toggleFavoriteButton(FavoriteState.Hidden)
                when (menuItem.menuItem.parent) {
                    resources.getString(R.string.world_of_warcraft) -> {
                        fragment = WoWFavoritesFragment()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragment, fragment, "wowfavorites").addToBackStack("wow_fav")
                            .commit()
                        supportFragmentManager.executePendingTransactions()
                    }
                    resources.getString(R.string.diablo_3) -> {
                        fragment = D3FavoriteFragment()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragment, fragment, "d3favorites").addToBackStack("d3_fav")
                            .commit()
                        supportFragmentManager.executePendingTransactions()
                    }
                    resources.getString(R.string.overwatch) -> {
                        fragment = OWFavoritesFragment()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragment, fragment, "owfavorites").addToBackStack("ow_fav")
                            .commit()
                        supportFragmentManager.executePendingTransactions()
                    }
                }
                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.leaderboards) -> {
                resetBackStack()
                toggleFavoriteButton(FavoriteState.Hidden)
                when (menuItem.menuItem.parent) {
                    resources.getString(R.string.diablo_3) -> {
                        fragment = D3LeaderboardFragment()
                        resetBackStack()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragment, fragment, "d3leaderboard")
                            .addToBackStack("d3_leaderboard").commit()
                        supportFragmentManager.executePendingTransactions()
                    }
                    resources.getString(R.string.starcraft_2) -> {
                        fragment = SC2LeaderboardFragment()
                        resetBackStack()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragment, fragment, "sc2leaderboard")
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
                    .add(R.id.fragment, fragment, "mraidleaderboard")
                    .addToBackStack("mraid_leaderboard").commit()
                supportFragmentManager.executePendingTransactions()
                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.mplus_leaderboards) -> {
                toggleFavoriteButton(FavoriteState.Hidden)
                fragment = MPlusLeaderboardsFragment()
                resetBackStack()
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment, fragment, "mplusleaderboard")
                    .addToBackStack("mplus_leaderboard").commit()
                supportFragmentManager.executePendingTransactions()
                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.pvp_leaderboards) -> {
                toggleFavoriteButton(FavoriteState.Hidden)
                fragment = PvpLeaderboardsFragment()
                resetBackStack()
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment, fragment, "pvpleaderboard")
                    .addToBackStack("pvp_leaderboard").commit()
                supportFragmentManager.executePendingTransactions()
                binding.overlappingPanel.closePanels()
            }
            resources.getString(R.string.settingsTitle) -> {
                toggleFavoriteButton(FavoriteState.Hidden)
                fragment = SettingsFragment()
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment, fragment, "settingsfragment").addToBackStack("settings")
                    .commit()
                supportFragmentManager.executePendingTransactions()
                binding.overlappingPanel.closePanels()
            }
        }
    }

    private fun openWoWAccount(fragment: Fragment) {
        resetBackStack()
        supportFragmentManager.beginTransaction().add(R.id.fragment, fragment, "wowfragment")
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
                Snackbar.make(dialog.tagMap["main_container"]!!, "Please enter a BattleTag", Snackbar.LENGTH_SHORT)
                    .show()
            }
            (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString()
                .equals("Select Region", ignoreCase = true) -> {
                Snackbar.make(dialog.tagMap["main_container"]!!, "Please enter the region", Snackbar.LENGTH_SHORT)
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
                Snackbar.make(dialog.tagMap["main_container"]!!, "Please enter the character name", Snackbar.LENGTH_SHORT)
                    .show()
            }
            (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString()
                .equals("Select Region", ignoreCase = true) -> {
                Snackbar.make(dialog.tagMap["main_container"]!!, "Please enter the region", Snackbar.LENGTH_SHORT)
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
                    characterRealm =
                        viewModel.getWowConnectedRealms().value!![selectedRegion]!!.results.flatMap { data -> data.connectedRealm.realms }
                            .find {
                                it.name.getAllNames()
                                    .contains((dialog.tagMap["realm_field"] as AutoCompleteTextView).text.toString())
                            }?.slug!!
                    viewModel.downloadMedia(characterClicked, characterRealm, selectedRegion)
                    dialog.dismiss()
                } else {
                    Snackbar.make(dialog.tagMap["main_container"]!!, "Please enter a valid realm for this region", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun validSearchedWoWGuildFields(dialog: DialogPrompt) {
        when {
            (dialog.tagMap["guild_field"] as EditText).text.toString() == "" -> {
                Snackbar.make(dialog.tagMap["main_container"]!!, "Please enter the character name", Snackbar.LENGTH_SHORT)
                    .show()
            }
            (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString()
                .equals("Select Region", ignoreCase = true) -> {
                Snackbar.make(dialog.tagMap["main_container"]!!, "Please enter the character name", Snackbar.LENGTH_SHORT)
                    .show()
            }
            else -> {
                val guildName = (dialog.tagMap["guild_field"] as EditText).text.toString()
                    .lowercase(Locale.getDefault())
                val selectedRegion =
                    (dialog.tagMap["region_spinner"] as Spinner).selectedItem.toString()
                var guildRealm: String
                if (viewModel.getWowConnectedRealms().value!![selectedRegion]!!.results.flatMap { data -> data.connectedRealm.realms }
                        .map { it.name }.flatMap { it.getAllNames() }
                        .any { it == (dialog.tagMap["realm_field"] as AutoCompleteTextView).text.toString() }) {
                    guildRealm =
                        viewModel.getWowConnectedRealms().value!![selectedRegion]!!.results.flatMap { data -> data.connectedRealm.realms }
                            .find {
                                it.name.getAllNames()
                                    .contains((dialog.tagMap["realm_field"] as AutoCompleteTextView).text.toString())
                            }?.slug!!
                    callWoWGuildFragment(guildName, selectedRegion, guildRealm, dialog)
                    dialog.dismiss()
                } else {
                    Snackbar.make(dialog.tagMap["main_container"]!!, "Please enter a valid realm for this region", Snackbar.LENGTH_SHORT)
                        .show()
                }

            }
        }
    }

    private fun callWoWGuildFragment(guildName: String, selectedRegion: String, guildRealm: String, dialog: DialogPrompt) {
        val fragment = GuildNavFragment()
        val bundle = Bundle()
        bundle.putString("guildName", guildName)
        bundle.putString("region", selectedRegion)
        bundle.putString("realm", guildRealm)
        fragment.arguments = bundle
        resetBackStack()
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
            .add(R.id.fragment, fragment, "guild_nav_fragment")
            .addToBackStack("wow_guild").commit()
        supportFragmentManager.executePendingTransactions()
        dialog.dismiss()
    }

    private fun callD3Fragment(battletag: String, region: String) {
        val fragment: Fragment = D3Fragment()
        val bundle = Bundle()
        bundle.putString("battletag", battletag)
        bundle.putString("region", region)
        fragment.arguments = bundle
        resetBackStack()
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
            .add(R.id.fragment, fragment, "d3fragment")
            .addToBackStack("d3_account").commit()
        supportFragmentManager.executePendingTransactions()
    }

    private fun callWoWCharacterFragment(characterClicked: String, characterRealm: String, selectedRegion: String) {
        if (supportFragmentManager.primaryNavigationFragment != null && supportFragmentManager.primaryNavigationFragment!!.isVisible) {
            supportFragmentManager.beginTransaction()
                .remove(supportFragmentManager.primaryNavigationFragment!!).commit()
        }
        val mediaString = Gson().toJson(viewModel.getMedia().value)
        val woWNavFragment =
            WoWNavFragment.newInstance(characterClicked, characterRealm, mediaString, selectedRegion)
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
            .add(R.id.fragment, woWNavFragment, "NAV_FRAGMENT")
            .addToBackStack("wow_nav").commit()
        supportFragmentManager.executePendingTransactions()
        binding.loadingCircle.visibility = View.GONE
    }


    override fun onGestureRegionsUpdate(gestureRegions: List<Rect>) {
        when (supportFragmentManager.fragments.last().tag) {
            "NAV_FRAGMENT",
            "overwatchfragment",
            "guild_nav_fragment" -> {
                for (rect in gestureRegions) {
                    rect.set(
                        (resources.displayMetrics.widthPixels * 0.25).toInt(), rect.top,
                        (resources.displayMetrics.widthPixels * 0.75).toInt(), rect.bottom
                    )
                }
            }
            else -> {
                for (rect in gestureRegions) {
                    rect.set(resources.displayMetrics.widthPixels, rect.top, 0, rect.bottom)
                }
            }
        }

        binding.overlappingPanel.setChildGestureRegions(gestureRegions)
    }

    private fun handleViewState(viewState: NavigationViewModel.ViewState) {
        binding.overlappingPanel.handleStartPanelState(viewState.startPanelState)
        binding.overlappingPanel.handleEndPanelState(viewState.endPanelState)
    }

    companion object {
        var userInformation: UserInformation? = null
        lateinit var realms: MutableMap<String, ConnectedRealms>
        var userNews: UserNews? = null
    }
}