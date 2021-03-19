package com.BlizzardArmory.ui.warcraft.guild.achievements

import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe

class AchievementViewModel : BaseViewModel() {

    fun downloadGuildSummary(realm: String, name: String, region: String) {
        val job = coroutineScope.launch {

            withContext(Dispatchers.Main) {

            }
        }
        jobs.add(job)
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
    }
}