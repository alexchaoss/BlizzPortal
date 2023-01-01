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
        executeAPICall({ RetroClient.getWoWClient(getApplication()).getGuildSummary(realm, name, "profile-$region", region) },
            {
                guildSummary.value = it.body()
                downloadGuildCrest()
            })
    }

    private fun downloadGuildCrest() {
        executeAPICall({ RetroClient.getWoWClient(getApplication()).getGuildCrestBorder(guildSummary.value?.crest?.border?.id!!) }, { guildCrestBorder.value = it.body() })
        executeAPICall({ RetroClient.getWoWClient(getApplication()).getGuildCrestEmblem(guildSummary.value?.crest?.emblem?.id!!) }, { guildCrestEmblem.value = it.body() })
    }

    fun downloadGuildActivity(realm: String, name: String, region: String) {
        executeAPICall({ RetroClient.getWoWClient(getApplication()).getGuildActivity(realm, name, "profile-$region", region) }, { guildActivity.value = it.body() })
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
    }
}