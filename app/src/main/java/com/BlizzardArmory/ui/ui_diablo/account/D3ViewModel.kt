package com.BlizzardArmory.ui.ui_diablo.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.model.diablo.account.AccountInformation
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.ui.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class D3ViewModel : BaseViewModel() {

    private var profile: MutableLiveData<AccountInformation> = MutableLiveData()

    fun getProfile(): LiveData<AccountInformation> {
        return profile
    }

    fun downloadAccountInformation(battleTag: String, selectedRegion: String) {
        URLConstants.loading = true
        val job = coroutineScope.launch {
            val response = RetroClient.getClient().getD3Profile(battleTag, MainActivity.locale, selectedRegion.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    profile.value = response.body()
                    sortHeroes()
                    URLConstants.loading = false
                } else {
                    errorCode.value = response.code()
                    URLConstants.loading = false
                }
            }
        }
        jobs.add(job)
    }


    private fun sortHeroes() {
        profile.value?.heroes = profile.value!!.heroes.sortedBy { it.lastUpdated }.reversed()
    }

}