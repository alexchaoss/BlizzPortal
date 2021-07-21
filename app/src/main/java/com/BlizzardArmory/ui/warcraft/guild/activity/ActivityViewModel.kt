package com.BlizzardArmory.ui.warcraft.guild.activity


import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.guild.Guild
import com.BlizzardArmory.model.warcraft.guild.activity.ActivitiesInformation
import com.BlizzardArmory.model.warcraft.guild.media.Media
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe

class ActivityViewModel(application: Application) : BaseViewModel(application) {

    private val guildSummary: MutableLiveData<Guild> = MutableLiveData()
    private val guildActivity: MutableLiveData<ActivitiesInformation> = MutableLiveData()
    private val guildCrestBorder: MutableLiveData<Media> = MutableLiveData()
    private val guildCrestEmblem: MutableLiveData<Media> = MutableLiveData()

    fun getGuildSummary(): MutableLiveData<Guild> {
        return guildSummary
    }

    fun getGuildActivity(): MutableLiveData<ActivitiesInformation> {
        return guildActivity
    }

    fun getGuildCrestBorder(): MutableLiveData<Media> {
        return guildCrestBorder
    }

    fun getGuildCrestEmblem(): MutableLiveData<Media> {
        return guildCrestEmblem
    }

    fun downloadGuildSummary(realm: String, name: String, region: String) {
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient(getApplication()).getGuildSummary(
                realm, name, "profile-$region", region)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    guildSummary.value = response.body()
                    downloadGuildCrest()
                } else {
                    errorCode.value = response.code()
                }
            }
        }
        jobs.add(job)
    }

    private fun downloadGuildCrest() {

        val job1 = coroutineScope.launch {
            try {
                val response = RetroClient.getWoWClient(getApplication())
                    .getGuildCrestBorder(guildSummary.value?.crest?.border?.id!!)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        guildCrestBorder.value = response.body()
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", "No Crest", e)
            }
        }
        jobs.add(job1)
        val job2 = coroutineScope.launch {
            try {
                val response = RetroClient.getWoWClient(getApplication())
                    .getGuildCrestEmblem(guildSummary.value?.crest?.emblem?.id!!)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        guildCrestEmblem.value = response.body()
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", "No Crest", e)
            }
        }
        jobs.add(job2)


    }

    fun downloadGuildActivity(realm: String, name: String, region: String) {
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient(getApplication()).getGuildActivity(
                realm, name, "profile-$region", region)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    guildActivity.value = response.body()
                } else {
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