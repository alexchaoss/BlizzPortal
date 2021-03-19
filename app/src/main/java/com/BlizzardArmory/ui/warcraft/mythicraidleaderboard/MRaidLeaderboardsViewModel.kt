package com.BlizzardArmory.ui.warcraft.mythicraidleaderboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.mythicraid.Entries
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.network.URLConstants
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.ui.main.MainActivity
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe
import java.util.*

class MRaidLeaderboardsViewModel : BaseViewModel() {

    private var entries: MutableLiveData<List<Entries>> = MutableLiveData()
    private var tempEntries: MutableList<Entries> = mutableListOf()

    fun getEntries(): LiveData<List<Entries>> {
        return entries
    }

    fun downloadBothLeaderboard(raid: String) {
        tempEntries.clear()
        val job = coroutineScope.launch {
            val job1 = coroutineScope.launch {
                val response = RetroClient.getWoWClient().getMythicRaidLeaderboards(
                    parseRaidName(raid), "horde",
                    "dynamic-" + MainActivity.selectedRegion.toLowerCase(Locale.ROOT), MainActivity.locale,
                    MainActivity.selectedRegion.toLowerCase(Locale.ROOT)
                )
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val list = response.body()?.entries?.toMutableList()!!
                        tempEntries.addAll(list)
                    } else {
                        URLConstants.loading = false
                        errorCode.value = response.code()
                    }
                }
            }
            jobs.add(job1)
            job1.join()

            val job2 = coroutineScope.launch {
                val response = RetroClient.getWoWClient().getMythicRaidLeaderboards(
                    parseRaidName(raid), "alliance",
                    "dynamic-" + MainActivity.selectedRegion.toLowerCase(Locale.ROOT), MainActivity.locale,
                    MainActivity.selectedRegion.toLowerCase(Locale.ROOT)
                )
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val list = response.body()?.entries?.toMutableList()!!
                        tempEntries.addAll(list)
                    } else {
                        URLConstants.loading = false
                        errorCode.value = response.code()
                    }
                }
            }
            jobs.add(job2)
            job2.join()
            withContext(Dispatchers.Main) {
                tempEntries.sortBy { it.timestamp }
                entries.value = tempEntries
            }
            URLConstants.loading = false
        }
        jobs.add(job)
    }

    fun downloadLeaderboard(raid: String, faction: String) {
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient().getMythicRaidLeaderboards(
                parseRaidName(raid), faction.toLowerCase(Locale.ROOT),
                "dynamic-" + MainActivity.selectedRegion.toLowerCase(Locale.ROOT), MainActivity.locale,
                MainActivity.selectedRegion.toLowerCase(Locale.ROOT)
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    entries.value = response.body()?.entries
                    URLConstants.loading = false
                } else {
                    URLConstants.loading = false
                    errorCode.value = response.code()
                }
            }
        }
        jobs.add(job)
    }

    fun parseRaidName(name: String): String {
        return name.replace("'", "").replace(" ", "-").toLowerCase(Locale.ROOT)
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
    }
}