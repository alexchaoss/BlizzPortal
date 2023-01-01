package com.BlizzardArmory.ui.warcraft.pvpleaderboards

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.pvp.leaderboards.Leaderboard
import com.BlizzardArmory.model.warcraft.pvp.leaderboards.season.SeasonIndex
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe

class PvpLeaderboardsViewModel(application: Application) : BaseViewModel(application) {

    private var seasonIndex: MutableLiveData<SeasonIndex> = MutableLiveData()
    private var pvpLeaderboard: MutableLiveData<Leaderboard> = MutableLiveData()

    fun getSeasonIndex(): LiveData<SeasonIndex> {
        return seasonIndex
    }

    fun getPvpLeaderboard(): LiveData<Leaderboard> {
        return pvpLeaderboard
    }

    fun downloadSeasonIndex() {
        executeAPICall({ RetroClient.getWoWClient(getApplication()).getPvPSeasonIndex("dynamic-" + NetworkUtils.region) },
            { seasonIndex.value = it.body() })
    }

    fun downloadLeaderboard(seasonId: Int, bracket: String, region: String) {
        executeAPICall({ RetroClient.getWoWClient(getApplication()).getPvPLeaderboard(seasonId, bracket, "dynamic-$region", region) },
            { pvpLeaderboard.value = it.body() })
    }


    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
    }
}