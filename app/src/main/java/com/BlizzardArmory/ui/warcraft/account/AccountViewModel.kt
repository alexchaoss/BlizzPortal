package com.BlizzardArmory.ui.warcraft.account

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.account.Account
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import com.google.common.eventbus.Subscribe
import org.greenrobot.eventbus.EventBus

class AccountViewModel(application: Application) : BaseViewModel(application) {

    private var characters: MutableLiveData<Account> = MutableLiveData()

    fun getCharacters(): LiveData<Account> {
        return characters
    }

    fun downloadWoWCharacters() {
        NetworkUtils.loading = true
        executeAPICall({ RetroClient.getWoWClient(getApplication(), true).getAccount(battlenetOAuth2Helper!!.accessToken) },
            {
                characters.value = it.body()
            }, onComplete = {
                if (!EventBus.getDefault().isRegistered(this@AccountViewModel)) {
                    EventBus.getDefault().register(this@AccountViewModel)
                }
                NetworkUtils.loading = false
            })
    }

    @Subscribe
    override fun localeSelectedReceived(localeSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(localeSelectedEvent)
        downloadWoWCharacters()
    }
}