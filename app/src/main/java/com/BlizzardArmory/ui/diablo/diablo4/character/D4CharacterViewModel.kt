package com.BlizzardArmory.ui.diablo.diablo4.character

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.diablo.diablo4.character.Character
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class D4CharacterViewModel(application: Application) : BaseViewModel(application) {

    private var character: MutableLiveData<Character> = MutableLiveData()
    private lateinit var battleTag: String
    private lateinit var characterId: String

    fun getCharacter(): LiveData<Character> {
        return character
    }

    fun downloadCharacter(battleTag: String, characterId: String) {
        this.battleTag = battleTag
        NetworkUtils.loading = true
        executeAPICall({ RetroClient.getD4Client(getApplication(), true).getCharacter(battleTag.replace("#", "-"), characterId) },
            {
                character.value = it.body()
            }, onComplete = {
                NetworkUtils.loading = false
                if (!EventBus.getDefault().isRegistered(this@D4CharacterViewModel)) {
                    EventBus.getDefault().register(this@D4CharacterViewModel)
                }
            })
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
        downloadCharacter(battleTag, characterId)
    }

}