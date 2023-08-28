package com.BlizzardArmory.ui.diablo.diablo4.account

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.diablo.diablo4.account.Account
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class D4ViewModel(application: Application) : BaseViewModel(application) {

    private var profile: MutableLiveData<Account> = MutableLiveData()
    private lateinit var battleTag: String

    fun getProfile(): LiveData<Account> {
        return profile
    }

    fun downloadAccountInformation(battleTag: String) {
        this.battleTag = battleTag
        NetworkUtils.loading = true
        executeAPICall({ RetroClient.getD4Client(getApplication(), true).getAccount(battleTag.replace("#", "-")) },
            {
                profile.value = it.body()
                sortHeroes()
            }, onComplete = {
                NetworkUtils.loading = false
                if (!EventBus.getDefault().isRegistered(this@D4ViewModel)) {
                    EventBus.getDefault().register(this@D4ViewModel)
                }
            })
    }

    private fun sortHeroes() {
        profile.value?.characters = profile.value!!.characters.sortedBy { it.lastUpdate }.reversed()
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
        downloadAccountInformation(battleTag)
    }

}