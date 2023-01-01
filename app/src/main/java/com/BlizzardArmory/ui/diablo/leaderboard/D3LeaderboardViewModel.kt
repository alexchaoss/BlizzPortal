package com.BlizzardArmory.ui.diablo.leaderboard

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.diablo.data.common.Leaderboard
import com.BlizzardArmory.model.diablo.data.eras.index.EraIndex
import com.BlizzardArmory.model.diablo.data.seasons.index.SeasonIndex
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class D3LeaderboardViewModel(application: Application) : BaseViewModel(application) {

    private var eraIndex: MutableLiveData<EraIndex> = MutableLiveData()
    private var seasonIndex: MutableLiveData<SeasonIndex> = MutableLiveData()

    private var eraIndexList: ArrayList<String> = arrayListOf("Era")
    private var seasonIndexList: ArrayList<String> = arrayListOf("Season")

    private var leaderboard: MutableLiveData<Leaderboard> = MutableLiveData()

    fun getEraIndex(): LiveData<EraIndex> {
        return eraIndex
    }

    fun getSeasonIndex(): LiveData<SeasonIndex> {
        return seasonIndex
    }

    fun getEraIndexList(): ArrayList<String> {
        return eraIndexList
    }

    fun getSeasonIndexList(): ArrayList<String> {
        return seasonIndexList
    }

    fun getLeaderboard(): LiveData<Leaderboard> {
        return leaderboard
    }

    fun downloadSeasonIndex() {
        executeAPICall({ RetroClient.getD3Client(getApplication()).getSeasonIndex() },
            {
                seasonIndex.value = it.body()
                seasonIndex.value?.season?.forEachIndexed { index, _ ->
                    seasonIndexList.add((index + 1).toString())
                }
                downloadSeason(seasonIndexList.last(), "rift-barbarian", NetworkUtils.region)
            }, { NetworkUtils.loading = false })
    }

    fun downloadSeason(id: String, leaderboardString: String, region: String) {
        executeAPICall({ RetroClient.getD3Client(getApplication()).getSeasonLeaderboard(id.toInt(), leaderboardString, region) }, { leaderboard.value = it.body() },
            onComplete = {
                NetworkUtils.loading = false
                if (!EventBus.getDefault().isRegistered(this@D3LeaderboardViewModel)) {
                    EventBus.getDefault().register(this@D3LeaderboardViewModel)
                }
            })
    }

    fun downloadEraIndex() {
        executeAPICall({ RetroClient.getD3Client(getApplication()).getEraIndex() },
            {
                eraIndex.value = it.body()
                eraIndex.value?.era?.forEachIndexed { index, _ ->
                    eraIndexList.add((index + 1).toString())
                }
            }, { NetworkUtils.loading = false })
    }

    fun downloadEra(id: String, leaderboardString: String, region: String) {
        executeAPICall({ RetroClient.getD3Client(getApplication()).getEraLeaderboard(id.toInt(), leaderboardString, region) },
            {
                leaderboard.value = it.body()
            }, onComplete = { NetworkUtils.loading = false })
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
        downloadEraIndex()
        downloadEraIndex()
    }
}