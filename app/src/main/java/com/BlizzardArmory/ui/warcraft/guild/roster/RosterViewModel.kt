package com.BlizzardArmory.ui.warcraft.guild.roster

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.guild.roster.Roster
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe

class RosterViewModel(application: Application) : BaseViewModel(application) {

    private val guildRoster: MutableLiveData<Roster> = MutableLiveData()

    fun getGuildRoster(): MutableLiveData<Roster> {
        return guildRoster
    }

    fun downloadGuildRoster(realm: String, name: String, region: String) {
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient(getApplication()).getGuildRoster(
                realm, name, "profile-$region", region)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    guildRoster.value = response.body()
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