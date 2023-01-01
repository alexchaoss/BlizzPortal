package com.BlizzardArmory.ui.warcraft.mythicplusleaderboards

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.expansion.Expansion
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.index.LeaderboardsIndex
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard.Leaderboard
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.season.Periods
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.season.Season
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.season.index.SeasonsIndex
import com.BlizzardArmory.model.warcraft.specialization.Specialization
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe

class MPlusLeaderboardsViewModel(application: Application) : BaseViewModel(application) {

    private var seasonIndex: MutableLiveData<SeasonsIndex> = MutableLiveData()
    private var season: MutableLiveData<Season> = MutableLiveData()
    private var mythicKeystoneLeaderboard: MutableLiveData<List<Leaderboard?>> = MutableLiveData()
    private var mythicKeystoneLeaderboardIndex: MutableLiveData<LeaderboardsIndex> = MutableLiveData()
    private var expansions: MutableLiveData<List<Expansion>> = MutableLiveData()
    private var specs: MutableLiveData<List<Specialization>> = MutableLiveData()

    fun getExpansions(): LiveData<List<Expansion>> {
        return expansions
    }

    fun getSpecializations(): LiveData<List<Specialization>> {
        return specs
    }

    fun getSeasonIndex(): LiveData<SeasonsIndex> {
        return seasonIndex
    }

    fun getSeason(): LiveData<Season> {
        return season
    }

    fun getMythicKeystoneLeaderboard(): LiveData<List<Leaderboard?>> {
        return mythicKeystoneLeaderboard
    }

    fun downloadSpecializations() {
        executeAPICall({ RetroClient.getAPIClient(getApplication()).getAllPlayableSpecializations() }, { specs.value = it.body() })
    }

    fun downloadInstances() {
        val expansions = mutableListOf<Expansion>()
        for (expansionId in 6..9) {
            executeAPICall({ RetroClient.getAPIClient(getApplication()).getExpansion(NetworkUtils.getExpansionFromRIO(expansionId)) },
                {
                    expansions.add(it.body()!!)
                    if (expansions.size == 4) {
                        this@MPlusLeaderboardsViewModel.expansions.value = expansions.sortedBy { xpac -> xpac.dungeons[0].id }
                        Log.i("EXPANSIONS", this@MPlusLeaderboardsViewModel.expansions.value?.last().toString())
                    }
                })
        }
    }

    fun downloadSeasonIndex() {
        executeAPICall({ RetroClient.getWoWClient(getApplication()).getMythicKeystoneSeasonsIndex("dynamic-" + NetworkUtils.region) }, { seasonIndex.value = it.body() })
    }

    fun downloadSeason(seasonId: Int) {
        executeAPICall({ RetroClient.getWoWClient(getApplication()).getMythicKeystoneSeason(seasonId, "dynamic-" + NetworkUtils.region) }, { season.value = it.body() })
    }

    fun downloadMythicKeystoneLeaderboardIndex(connectedRealm: Int) {
        executeAPICall(
            { RetroClient.getWoWClient(getApplication()).getMythicKeystoneLeaderboardsIndex(connectedRealm, "dynamic-" + NetworkUtils.region, NetworkUtils.region) },
            { mythicKeystoneLeaderboardIndex.value = it.body() })
    }

    fun downloadMythicKeystoneLeaderboard(connectedRealm: Int?, dungeonId: Long, periods: List<Periods>, region: String) {
        val tempLeaderboards = mutableListOf<Leaderboard?>()
        for (period in periods) {
            executeAPICall({ RetroClient.getWoWClient(getApplication()).getMythicKeystoneLeaderboard(connectedRealm!!, dungeonId, period.id, "dynamic-${region.lowercase()}", region.lowercase()) },
                {
                    tempLeaderboards.add(it.body())

                    if (tempLeaderboards.size == periods.size) {
                        NetworkUtils.loading = false
                        mythicKeystoneLeaderboard.value = tempLeaderboards
                    }
                }, { tempLeaderboards.add(null) })
        }
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
    }
}