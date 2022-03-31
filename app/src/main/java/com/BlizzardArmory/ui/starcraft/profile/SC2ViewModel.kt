package com.BlizzardArmory.ui.starcraft.profile

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.starcraft.Player
import com.BlizzardArmory.model.starcraft.profile.Profile
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class SC2ViewModel(application: Application) : BaseViewModel(application) {

    private var accountInformation: MutableLiveData<List<Player>> = MutableLiveData()
    private var profile: MutableLiveData<Profile> = MutableLiveData()

    fun getProfile(): LiveData<Profile> {
        return profile
    }

    fun getAccount(): LiveData<List<Player>> {
        return accountInformation
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
        downloadAccountInformation()
    }

    fun downloadAccountInformation() {
        NetworkUtils.loading = true
        val job = coroutineScope.launch {
            val response = RetroClient.getSc2Client(getApplication())
                .getSc2Player(
                    NavigationActivity.userInformation?.userID,
                    battlenetOAuth2Helper!!.accessToken
                )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    accountInformation.value = response.body()
                    NetworkUtils.loading = false
                } else {
                    Log.e("Error", response.message())
                    errorCode.value = response.code()
                    NetworkUtils.loading = false
                }
            }
        }
        jobs.add(job)
    }

    fun downloadProfile(regionId: Int, realmId: Int, profileId: String) {
        NetworkUtils.loading = true
        val job = coroutineScope.launch {
            val response = RetroClient.getSc2Client(getApplication())
                .getSc2Profile(parseRegionId(regionId), realmId, profileId, battlenetOAuth2Helper!!.accessToken)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    profile.value = response.body()
                    NetworkUtils.loading = false
                } else {
                    Log.e("Error", response.message())
                    errorCode.value = response.code()
                    NetworkUtils.loading = false
                }
            }
            if (!EventBus.getDefault().isRegistered(this@SC2ViewModel)) {
                EventBus.getDefault().register(this@SC2ViewModel)
            }
        }
        jobs.add(job)
    }

    fun parseRegionId(regionId: Int): String {
        when (regionId) {
            1 -> return "US"
            2 -> return "EU"
            3 -> return "KO"
            5 -> return "CN"
        }
        return "US"
    }
}