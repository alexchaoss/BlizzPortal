package com.BlizzardArmory.ui.warcraft.character.talents

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.talents.playerspec.PlayerSpecializations
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.WoWClassName
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class TalentsViewModel(application: Application) : BaseViewModel(application) {

    lateinit var character: String
    lateinit var realm: String
    lateinit var region: String

    private var playerSpecialization: MutableLiveData<PlayerSpecializations> = MutableLiveData()
    private var playerClass: MutableLiveData<String> = MutableLiveData()

    fun getPlayerSpecialization(): LiveData<PlayerSpecializations> {
        return playerSpecialization
    }

    fun getPlayerClass(): LiveData<String> {
        return playerClass
    }

    fun setPlayerClass(plClass: Int) {
        playerClass.value = WoWClassName.get(plClass)
    }

    fun downloadCharacterSpecialization() {
        executeAPICall({ RetroClient.getWoWClient(getApplication()).getSpecs(character, realm) }, { playerSpecialization.value = it.body() },
            onComplete = {
                if (!EventBus.getDefault().isRegistered(this@TalentsViewModel)) {
                    EventBus.getDefault().register(this@TalentsViewModel)
                }
            })
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
        downloadCharacterSpecialization()
    }
}