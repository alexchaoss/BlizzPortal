package com.BlizzardArmory.ui.warcraft.guild.activity


import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.guild.Guild
import com.BlizzardArmory.model.warcraft.guild.activity.ActivitiesInformation
import com.BlizzardArmory.model.warcraft.guild.media.Media
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.network.URLConstants
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.ui.main.MainActivity
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe

class ActivityViewModel : BaseViewModel() {

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
            val response = RetroClient.getWoWClient().getGuildSummary(
                realm, name, "profile-$region", MainActivity.locale,
                region, battlenetOAuth2Helper?.accessToken!!
            )
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
            val response = RetroClient.getWoWClient()
                .getGuildCrestBorder(guildSummary.value?.crest?.border?.id!!)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    guildCrestBorder.value = response.body()
                }
            }
        }
        jobs.add(job1)
        val job2 = coroutineScope.launch {
            val response = RetroClient.getWoWClient()
                .getGuildCrestEmblem(guildSummary.value?.crest?.emblem?.id!!)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    guildCrestEmblem.value = response.body()
                }
            }
        }
        jobs.add(job2)
    }

    fun downloadGuildActivity(realm: String, name: String, region: String) {
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient().getGuildActivity(
                realm, name, "profile-$region", MainActivity.locale,
                region, battlenetOAuth2Helper?.accessToken!!
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    guildActivity.value = response.body()
                } else {
                    errorCode.value = response.code()
                }
                URLConstants.loading = false
            }
        }
        jobs.add(job)
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
    }
}