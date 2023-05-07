package com.BlizzardArmory.ui.diablo.account

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.diablo.account.AccountInformation
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.Locale

class D3ViewModel(application: Application) : BaseViewModel(application) {

    private var profile: MutableLiveData<AccountInformation> = MutableLiveData()
    private lateinit var battleTag: String
    private lateinit var selectedRegion: String

    fun getProfile(): LiveData<AccountInformation> {
        return profile
    }

    fun downloadAccountInformation(battleTag: String, selectedRegion: String) {
        this.battleTag = battleTag
        this.selectedRegion = selectedRegion
        NetworkUtils.loading = true
        executeAPICall({ RetroClient.getD3Client(getApplication()).getD3Profile(battleTag, battlenetOAuth2Helper?.accessToken, selectedRegion.lowercase(Locale.getDefault())) },
            {
                profile.value = it.body()
                sortHeroes()
            }, onComplete = {
                NetworkUtils.loading = false
                if (!EventBus.getDefault().isRegistered(this@D3ViewModel)) {
                    EventBus.getDefault().register(this@D3ViewModel)
                }
            })
    }

    private fun sortHeroes() {
        profile.value?.heroes = profile.value!!.heroes.sortedBy { it.lastUpdated }.reversed()
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
        downloadAccountInformation(battleTag, selectedRegion)
    }

}