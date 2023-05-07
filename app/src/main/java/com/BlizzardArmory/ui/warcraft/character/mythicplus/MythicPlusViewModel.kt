package com.BlizzardArmory.ui.warcraft.character.mythicplus

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.encounters.EncountersInformation
import com.BlizzardArmory.model.warcraft.mythicplusprofile.index.MythicPlusProfileIndex
import com.BlizzardArmory.model.warcraft.mythicplusprofile.season.MythicPlusProfileSeason
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.Locale

class MythicPlusViewModel(application: Application) : BaseViewModel(application) {

    lateinit var character: String
    lateinit var realm: String
    lateinit var region: String

    private var encounters: MutableLiveData<EncountersInformation> = MutableLiveData()

    private var mythicKeystoneIndex: MutableLiveData<MythicPlusProfileIndex> = MutableLiveData()
    private var mythicKeystoneSeason: MutableLiveData<MythicPlusProfileSeason> = MutableLiveData()

    fun getIndex(): LiveData<MythicPlusProfileIndex> {
        return mythicKeystoneIndex
    }

    fun getSeason(): LiveData<MythicPlusProfileSeason> {
        return mythicKeystoneSeason
    }

    fun downloadMythicKeystoneIndex() {
        executeAPICall({ RetroClient.getWoWClient(getApplication()).getMythicKeystoneProfileIndex(
            character.lowercase(Locale.getDefault()),
            realm.lowercase(Locale.getDefault()),
            region.lowercase(Locale.getDefault()),
        ) }, { mythicKeystoneIndex.value = it.body() }, onComplete = {
            if (!EventBus.getDefault().isRegistered(this@MythicPlusViewModel)) {
                EventBus.getDefault().register(this@MythicPlusViewModel)
            }
        })
    }

    fun downloadMythicKeystoneSeason(seasonId: Int) {
        executeAPICall({ RetroClient.getWoWClient(getApplication()).getMythicKeystoneProfileSeason(
            seasonId,
            character.lowercase(Locale.getDefault()),
            realm.lowercase(Locale.getDefault()),
            region.lowercase(Locale.getDefault()),
        ) }, { mythicKeystoneSeason.value = it.body() }, onComplete = {
            if (!EventBus.getDefault().isRegistered(this@MythicPlusViewModel)) {
                EventBus.getDefault().register(this@MythicPlusViewModel)
            }
        })
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
        downloadMythicKeystoneIndex()
    }
}