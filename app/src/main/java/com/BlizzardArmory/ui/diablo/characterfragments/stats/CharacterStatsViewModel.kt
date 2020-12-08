package com.BlizzardArmory.ui.diablo.characterfragments.stats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.diablo.character.CharacterInformation
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.network.URLConstants
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.ui.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class CharacterStatsViewModel : BaseViewModel() {

    private var characterInformation: MutableLiveData<CharacterInformation> = MutableLiveData()

    fun getCharacterInformation(): LiveData<CharacterInformation> {
        return characterInformation
    }

    fun downloadCharacterInformation(battletag: String, id: Long, selectedRegion: String) {
        URLConstants.loading = true
        val job = coroutineScope.launch {
            val response = RetroClient.getClient().getD3Hero(battletag, id, MainActivity.locale, selectedRegion.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    characterInformation.value = response.body()
                    URLConstants.loading = false
                } else {
                    errorCode.value = response.code()
                    URLConstants.loading = false
                }
            }
        }
        jobs.add(job)
    }
}