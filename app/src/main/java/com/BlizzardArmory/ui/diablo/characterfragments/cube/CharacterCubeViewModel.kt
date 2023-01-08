package com.BlizzardArmory.ui.diablo.characterfragments.cube

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.diablo.character.CharacterInformation
import com.BlizzardArmory.model.diablo.item.SingleItem
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

class CharacterCubeViewModel(application: Application) : BaseViewModel(application) {

    private val singleItem: MutableLiveData<SingleItem> = MutableLiveData()
    private lateinit var characterInformation: CharacterInformation

    fun getItem(): LiveData<SingleItem> {
        return singleItem
    }

    fun downloadCubeItems(characterInformation: CharacterInformation) {
        this.characterInformation = characterInformation
        for (i in characterInformation.legendaryPowers.indices) {
            val endpoint = characterInformation.legendaryPowers[i].tooltipParams.replace("/item/", "")
            executeAPICall({ RetroClient.getD3Client(getApplication(), true).getItem(endpoint) },
                {
                    singleItem.value = it.body()!!
                    if (i == characterInformation.legendaryPowers.size - 1) {
                        NetworkUtils.loading = false
                        if (!EventBus.getDefault().isRegistered(this@CharacterCubeViewModel)) {
                            EventBus.getDefault().register(this@CharacterCubeViewModel)
                        }
                    }
                })
        }
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
        downloadCubeItems(characterInformation)
    }
}