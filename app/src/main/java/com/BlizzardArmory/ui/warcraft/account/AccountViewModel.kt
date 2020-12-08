package com.BlizzardArmory.ui.warcraft.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.account.Account
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.network.URLConstants
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.ui.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class AccountViewModel : BaseViewModel() {

    private var characters: MutableLiveData<Account> = MutableLiveData()

    fun getCharacters(): LiveData<Account> {
        return characters
    }

    fun downloadWoWCharacters() {
        URLConstants.loading = true
        val job = coroutineScope.launch {
            val response = RetroClient.getClient().getAccount(MainActivity.locale, MainActivity.selectedRegion.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    characters.value = response.body()
                    URLConstants.loading = false
                } else {
                    errorCode.value = response.code()
                }
            }
        }
        jobs.add(job)
    }
}