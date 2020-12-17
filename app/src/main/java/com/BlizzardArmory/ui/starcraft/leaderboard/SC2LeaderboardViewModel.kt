package com.BlizzardArmory.ui.starcraft.leaderboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.starcraft.leaderboard.LadderMembers
import com.BlizzardArmory.model.starcraft.league.League
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.network.URLConstants
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe

class SC2LeaderboardViewModel : BaseViewModel() {

    private var league: MutableLiveData<League> = MutableLiveData()

    private var leaderboard: MutableLiveData<List<List<LadderMembers>>> = MutableLiveData()

    lateinit var teamType: String

    fun getLeague(): LiveData<League> {
        return league
    }

    fun getLeaderboard(): LiveData<List<List<LadderMembers>>> {
        return leaderboard
    }

    fun downloadLeaderboard(regionId: Int, ladderId: Int) {
        val job = coroutineScope.launch {
            val response = RetroClient.getClient().getSc2LadderLeaderboard(regionId, ladderId)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val board = response.body()
                    leaderboard.value = board!!.ladderMembers.groupBy { it.joinTimestamp }.map { it.value }
                    URLConstants.loading = false
                } else {
                    URLConstants.loading = false
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