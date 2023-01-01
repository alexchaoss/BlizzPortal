package com.BlizzardArmory.ui.starcraft.leaderboard

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.starcraft.CurrentSeason
import com.BlizzardArmory.model.starcraft.leaderboard.LadderMembers
import com.BlizzardArmory.model.starcraft.league.League
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe

class SC2LeaderboardViewModel(application: Application) : BaseViewModel(application) {

    private var league: MutableLiveData<League> = MutableLiveData()
    private var season: MutableLiveData<CurrentSeason> = MutableLiveData()
    private var leaderboard: MutableLiveData<List<List<LadderMembers>>> = MutableLiveData()

    var teamType: Int = 0
    lateinit var queueId: String
    var seasonId: Int = 0
    lateinit var leagueString: String

    fun getLeague(): LiveData<League> {
        return league
    }

    fun getCurrentSeason(): LiveData<CurrentSeason> {
        return season
    }

    fun getLeaderboard(): LiveData<List<List<LadderMembers>>> {
        return leaderboard
    }

    fun downloadLeaderboard(regionId: Int, ladderId: Int, region: String) {
        executeAPICall({ RetroClient.getSc2Client(getApplication()).getSc2LadderLeaderboard(parseRegionId(regionId), ladderId, region) },
            {
                val board = it.body()
                leaderboard.value = board!!.ladderMembers.groupBy { team -> Triple(team.joinTimestamp, team.wins, team.losses) }
                    .map { team -> team.value }
            }, onComplete = { NetworkUtils.loading = false })
    }

    fun downloadLeague(region: String) {
        executeAPICall({ RetroClient.getSc2Client(getApplication()).getSc2League(seasonId, getQueueAsInt(), teamType, getLeagueAsInt(), region) }, {league.value = it.body()}, { NetworkUtils.loading = false })
    }

    fun downloadCurrentSeason(regionId: Int, region: String) {
        executeAPICall({  RetroClient.getSc2Client(getApplication()).getSc2CurrentSeason(parseRegionId(regionId), region) }, {season.value = it.body()}, { NetworkUtils.loading = false })
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)

    }

    private fun getQueueAsInt(): Int {
        when (queueId) {
            "1v1" -> return 201
            "2v2" -> return 202
            "3v3" -> return 203
            "4v4" -> return 204
        }
        return 201
    }

    private fun getLeagueAsInt(): Int {
        when (leagueString) {
            "Bronze" -> return 0
            "Silver" -> return 1
            "Gold" -> return 2
            "Platinum" -> return 3
            "Diamond" -> return 4
            "Master" -> return 5
            "Grandmaster" -> return 6
        }
        return 6
    }

    private fun parseRegionId(regionId: Int): String {
        when (regionId) {
            1 -> return "US"
            2 -> return "EU"
            3 -> return "KO"
            5 -> return "CN"
        }
        return "US"
    }
}