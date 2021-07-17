package com.BlizzardArmory.ui.warcraft.mythicplusleaderboards

import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.instances.Instances
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard.Leaderboard
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.season.Season
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.season.index.SeasonsIndex
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe

class MPlusLeaderboardsViewModel : BaseViewModel() {

    private var seasonIndex: MutableLiveData<SeasonsIndex> = MutableLiveData()
    private var season: MutableLiveData<Season> = MutableLiveData()
    private var mythicKeystoneLeaderboard: MutableLiveData<Leaderboard> = MutableLiveData()
    private var instances: MutableLiveData<Instances> = MutableLiveData()

    fun downloadInstances() {
        val job = coroutineScope.launch {
            val response = RetroClient.getAPIClient().getInstances()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    instances.value = response.body()
                } else {
                    NetworkUtils.loading = false
                    errorCode.value = response.code()
                }
            }
        }
        jobs.add(job)
    }

    fun downloadSeasonIndex() {
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient().getMythicKeystoneSeasonsIndex("dynamic-" + NetworkUtils.region, NetworkUtils.locale, NetworkUtils.region)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    seasonIndex.value = response.body()
                } else {
                    NetworkUtils.loading = false
                    errorCode.value = response.code()
                }
            }

        }
        jobs.add(job)
    }

    fun downloadSeason(seasonId: Int) {
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient().getMythicKeystoneSeason(seasonId, "dynamic-" + NetworkUtils.region, NetworkUtils.locale, NetworkUtils.region)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    season.value = response.body()
                } else {
                    NetworkUtils.loading = false
                    errorCode.value = response.code()
                }
            }

        }
        jobs.add(job)
    }

    fun downloadMythicKeystoneLeaderboard(connectedRealm: Int, dungeonId: Int, period: Int) {
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient().getMythicKeystoneLeaderboard(connectedRealm, dungeonId, period, "dynamic-" + NetworkUtils.region, NetworkUtils.locale, NetworkUtils.region)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    mythicKeystoneLeaderboard.value = response.body()
                } else {
                    NetworkUtils.loading = false
                    errorCode.value = response.code()
                }
            }

        }
        jobs.add(job)
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
    }
}