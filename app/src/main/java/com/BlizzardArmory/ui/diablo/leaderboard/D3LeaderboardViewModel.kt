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
        val job = coroutineScope.launch {
            val response = RetroClient.getD3Client(getApplication()).getSeasonIndex()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    seasonIndex.value = response.body()
                    seasonIndex.value?.season?.forEachIndexed { index, season ->
                        seasonIndexList.add((index + 1).toString())
                    }
                    downloadSeason(seasonIndexList.last(), "rift-barbarian", NetworkUtils.region)
                } else {
                    NetworkUtils.loading = false
                    errorCode.value = response.code()
                }
            }
        }
        jobs.add(job)
    }

    fun downloadSeason(id: String, leaderboardString: String, region: String) {
        val job = coroutineScope.launch {
            val response = RetroClient.getD3Client(getApplication())
                .getSeasonLeaderboard(id.toInt(), leaderboardString, region)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    leaderboard.value = response.body()
                    NetworkUtils.loading = false
                } else {
                    NetworkUtils.loading = false
                    errorCode.value = response.code()
                }
                if (!EventBus.getDefault().isRegistered(this@D3LeaderboardViewModel)) {
                    EventBus.getDefault().register(this@D3LeaderboardViewModel)
                }
            }
        }
        jobs.add(job)
    }

    fun downloadEraIndex() {
        val job = coroutineScope.launch {
            val response = RetroClient.getD3Client(getApplication())
                .getEraIndex()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    eraIndex.value = response.body()
                    eraIndex.value?.era?.forEachIndexed { index, season ->
                        eraIndexList.add((index + 1).toString())
                    }
                } else {
                    NetworkUtils.loading = false
                    errorCode.value = response.code()
                }
            }
        }
        jobs.add(job)
    }

    fun downloadEra(id: String, leaderboardString: String, region: String) {
        val job = coroutineScope.launch {
            val response = RetroClient.getD3Client(getApplication())
                .getEraLeaderboard(id.toInt(), leaderboardString, region)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    leaderboard.value = response.body()
                    NetworkUtils.loading = false
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
        downloadEraIndex()
        downloadEraIndex()
    }
}