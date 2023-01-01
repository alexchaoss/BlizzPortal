package com.BlizzardArmory.ui.warcraft.guild.roster

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.guild.roster.Roster
import com.BlizzardArmory.network.NetworkUtils
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
        executeAPICall({ RetroClient.getWoWClient(getApplication()).getGuildRoster(realm, name, "profile-$region", region) }, { guildRoster.value = it.body() })
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
    }
}