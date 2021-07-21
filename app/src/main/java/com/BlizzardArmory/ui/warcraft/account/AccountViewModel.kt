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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import java.util.*

class AccountViewModel(application: Application) : BaseViewModel(application) {

    private var characters: MutableLiveData<Account> = MutableLiveData()

    fun getCharacters(): LiveData<Account> {
        return characters
    }

    fun downloadWoWCharacters() {
        NetworkUtils.loading = true
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient(getApplication())
                .getAccount(battlenetOAuth2Helper!!.accessToken)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    characters.value = response.body()
                    NetworkUtils.loading = false
                } else {
                    errorCode.value = response.code()
                }
                if (!EventBus.getDefault().isRegistered(this@AccountViewModel)) {
                    EventBus.getDefault().register(this@AccountViewModel)
                }
            }
        }
        jobs.add(job)
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
        downloadWoWCharacters()
    }
}