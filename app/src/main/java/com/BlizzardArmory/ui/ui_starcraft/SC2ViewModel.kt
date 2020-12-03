package com.BlizzardArmory.ui.ui_starcraft

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.model.starcraft.Player
import com.BlizzardArmory.model.starcraft.profile.Profile
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.ui.main.MainActivity
import com.BlizzardArmory.ui.navigation.GamesActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class SC2ViewModel : BaseViewModel() {

    private var accountInformation: MutableLiveData<List<Player>> = MutableLiveData()
    private var profile: MutableLiveData<Profile> = MutableLiveData()

    init {
        accountInformation.value = listOf()
    }

    fun getProfile(): LiveData<Profile> {
        return profile
    }

    fun getAccount(): LiveData<List<Player>> {
        return accountInformation
    }

    fun downloadAccountInformation() {
        URLConstants.loading = true
        val job = coroutineScope.launch {
            val response = RetroClient.getClient().getSc2Player(GamesActivity.userInformation!!.userID, MainActivity.locale,
                    MainActivity.selectedRegion.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    accountInformation.value = response.body()
                    downloadProfile()
                } else {
                    Log.e("Error", response.message())
                    errorCode.value = response.code()
                    URLConstants.loading = false
                }
            }
        }
        jobs.add(job)
    }

    private fun downloadProfile() {
        val job = coroutineScope.launch {
            val response = RetroClient.getClient().getSc2Profile(parseRegionId(accountInformation.value!![0].regionId), accountInformation.value!![0].realmId,
                    accountInformation.value!![0].profileId, MainActivity.locale, MainActivity.selectedRegion.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    profile.value = response.body()
                    URLConstants.loading = false
                } else {
                    Log.e("Error", response.message())
                    errorCode.value = response.code()
                    URLConstants.loading = false
                }
            }
        }
        jobs.add(job)
    }

    private fun parseRegionId(regionId: Int): String {
        when (regionId) {
            1 -> return "US"
            2 -> return "EU"
            3 -> return "KO"
            5 -> return "CN"
        }
        return "US"
    }
}