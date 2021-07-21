package com.BlizzardArmory.ui.diablo.characterfragments.stats

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.diablo.character.CharacterInformation
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*

class CharacterStatsViewModel(application: Application) : BaseViewModel(application) {

    private var characterInformation: MutableLiveData<CharacterInformation> = MutableLiveData()

    private var battletag = ""
    private var selectedRegion = ""
    private var id = 0L

    fun getCharacterInformation(): LiveData<CharacterInformation> {
        return characterInformation
    }

    fun downloadCharacterInformation(battletag: String, id: Long, selectedRegion: String) {
        this.battletag = battletag
        this.id = id
        this.selectedRegion = selectedRegion
        NetworkUtils.loading = true
        val job = coroutineScope.launch {
            val response = RetroClient.getD3Client(getApplication()).getD3Hero(
                battletag,
                id,
                battlenetOAuth2Helper!!.accessToken,
                selectedRegion.lowercase(Locale.getDefault()),
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    characterInformation.value = response.body()
                    NetworkUtils.loading = false
                } else {
                    errorCode.value = response.code()
                    NetworkUtils.loading = false
                }
                if (!EventBus.getDefault().isRegistered(this@CharacterStatsViewModel)) {
                    EventBus.getDefault().register(this@CharacterStatsViewModel)
                }
            }
        }
        jobs.add(job)
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
        downloadCharacterInformation(battletag, id, selectedRegion)
    }
}