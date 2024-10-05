package com.BlizzardArmory.ui.warcraft.mythicraidleaderboards

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.mythicraid.Entries
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.SlugName
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe
import java.util.Locale

class MRaidLeaderboardsViewModel(application: Application) : BaseViewModel(application) {

    private var entries: MutableLiveData<List<Entries>> = MutableLiveData()
    private var tempEntries: MutableList<Entries> = mutableListOf()

    fun getEntries(): LiveData<List<Entries>> {
        return entries
    }

    fun downloadBothLeaderboard(raid: String) {
        tempEntries.clear()
        val job = coroutineScope.launch {
            val job1 = executeAPICall({ RetroClient.getWoWClient(getApplication(), true).getMythicRaidLeaderboards(SlugName.toRaidSlug(raid), "horde", "dynamic-" + NetworkUtils.region) },
                {
                    if (it.body()?.entries != null) {
                        val list = it.body()?.entries?.toMutableList()!!
                        tempEntries.addAll(list)
                    }
                },
                {
                    NetworkUtils.loading = false
                })
            job1.join()

            val job2 = executeAPICall({ RetroClient.getWoWClient(getApplication(), true).getMythicRaidLeaderboards(SlugName.toRaidSlug(raid), "alliance", "dynamic-" + NetworkUtils.region) },
                {
                    if (it.body()?.entries != null) {
                        val list = it.body()?.entries?.toMutableList()!!
                        tempEntries.addAll(list)
                    }
                },
                {
                    NetworkUtils.loading = false
                })
            job2.join()
            withContext(Dispatchers.Main) {
                tempEntries.sortBy { it.timestamp }
                entries.value = tempEntries
            }
            NetworkUtils.loading = false
        }
        jobs["downloadBothLeaderboards"] = job
    }

    fun downloadLeaderboard(raid: String, faction: String) {
        executeAPICall({ RetroClient.getWoWClient(getApplication(), true).getMythicRaidLeaderboards(SlugName.toRaidSlug(raid), faction.lowercase(Locale.getDefault()), "dynamic-" + NetworkUtils.region) },
            {
                entries.value = it.body()?.entries
                NetworkUtils.loading = false
            },
            { NetworkUtils.loading = false })
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
    }
}