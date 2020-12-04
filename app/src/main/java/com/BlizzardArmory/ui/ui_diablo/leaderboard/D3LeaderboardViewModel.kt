package com.BlizzardArmory.ui.ui_diablo.leaderboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.model.diablo.data.common.Leaderboard
import com.BlizzardArmory.model.diablo.data.eras.index.EraIndex
import com.BlizzardArmory.model.diablo.data.seasons.index.SeasonIndex
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.ui.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class D3LeaderboardViewModel : BaseViewModel() {

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
            val response = RetroClient.getClient().getSeasonIndex(MainActivity.locale, MainActivity.selectedRegion)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    seasonIndex.value = response.body()
                    seasonIndex.value?.season?.forEachIndexed { index, season ->
                        seasonIndexList.add((index + 1).toString())
                    }
                    downloadSeason(seasonIndexList.last(), "rift-barbarian", MainActivity.selectedRegion)
                } else {
                    URLConstants.loading = false
                    errorCode.value = response.code()
                }
            }
        }
        jobs.add(job)
    }

    fun downloadSeason(id: String, leaderboardString: String, region: String) {
        val job = coroutineScope.launch {
            val response = RetroClient.getClient().getSeasonLeaderboard(id.toInt(), leaderboardString, MainActivity.locale, region)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    leaderboard.value = response.body()
                    URLConstants.loading = false
                } else {
                    URLConstants.loading = false
                    errorCode.value = response.code()
                }
            }
        }
        jobs.add(job)
    }

    fun downloadEraIndex() {
        val job = coroutineScope.launch {
            val response = RetroClient.getClient().getEraIndex(MainActivity.locale, MainActivity.selectedRegion)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    eraIndex.value = response.body()
                    eraIndex.value?.era?.forEachIndexed { index, season ->
                        eraIndexList.add((index + 1).toString())
                    }
                } else {
                    URLConstants.loading = false
                    errorCode.value = response.code()
                }
            }
        }
        jobs.add(job)
    }

    fun downloadEra(id: String, leaderboardString: String, region: String) {
        val job = coroutineScope.launch {
            val response = RetroClient.getClient().getEraLeaderboard(id.toInt(), leaderboardString, MainActivity.locale, region)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    leaderboard.value = response.body()
                    URLConstants.loading = false
                } else {
                    URLConstants.loading = false
                    errorCode.value = response.code()
                }
            }
        }
        jobs.add(job)
    }
}