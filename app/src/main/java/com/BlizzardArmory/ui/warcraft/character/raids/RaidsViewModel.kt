package com.BlizzardArmory.ui.warcraft.character.raids

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.encounters.EncountersInformation
import com.BlizzardArmory.model.warcraft.encounters.Expansions
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.Locale

class RaidsViewModel(application: Application) : BaseViewModel(application) {

    lateinit var character: String
    lateinit var realm: String
    lateinit var region: String

    private var encounters: MutableLiveData<EncountersInformation> = MutableLiveData()

    fun getEncounters(): LiveData<EncountersInformation> {
        return encounters
    }

    fun downloadEncounterInformation() {
        executeAPICall({ RetroClient.getWoWClient(getApplication()).getEncounters(
            character.lowercase(Locale.getDefault()),
            realm.lowercase(Locale.getDefault()),
            region.lowercase(Locale.getDefault()),
        ) }, { encounters.value = it.body() }, onComplete = {
            if (!EventBus.getDefault().isRegistered(this@RaidsViewModel)) {
                EventBus.getDefault().register(this@RaidsViewModel)
            }
        })
    }


    fun getRaidLevel(expansion: Expansions): String {
        return when (expansion.expansion.id) {
            68L -> "Level 25"
            70L -> "Level 27"
            72L -> "Level 30"
            73L -> "Level 32"
            74L -> "Level 35"
            124L -> "Level 40"
            395L -> "Level 45"
            396L -> "Level 50"
            397L -> "Level 60"
            503L -> "Level 70"
            514L -> "Level 80"
            else -> ""
        }
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
        downloadEncounterInformation()
    }
}