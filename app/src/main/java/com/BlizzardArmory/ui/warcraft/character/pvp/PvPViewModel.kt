package com.BlizzardArmory.ui.warcraft.character.pvp

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.pvp.bracket.BracketStatistics
import com.BlizzardArmory.model.warcraft.pvp.summary.PvPSummary
import com.BlizzardArmory.model.warcraft.pvp.tiers.Tier
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

class PvPViewModel(application: Application) : BaseViewModel(application) {

    lateinit var character: String
    lateinit var realm: String
    lateinit var region: String

    lateinit var pvpRBG: BracketStatistics
    lateinit var pvp2v2: BracketStatistics
    lateinit var pvp3v3: BracketStatistics

    private var tierRBG: MutableLiveData<Tier> = MutableLiveData()
    private var tier2v2: MutableLiveData<Tier> = MutableLiveData()
    private var tier3v3: MutableLiveData<Tier> = MutableLiveData()
    private var summary: MutableLiveData<PvPSummary> = MutableLiveData()
    private var errorBracket: MutableLiveData<String> = MutableLiveData()

    fun getTierRBG(): LiveData<Tier> {
        return tierRBG
    }

    fun getTier2v2(): LiveData<Tier> {
        return tier2v2
    }

    fun getTier3v3(): LiveData<Tier> {
        return tier3v3
    }

    fun geterrorBracket(): LiveData<String> {
        return errorBracket
    }

    fun getSummary(): LiveData<PvPSummary> {
        return summary
    }

    fun downloadRBGInfo() {
        executeAPICall({ RetroClient.getWoWClient(getApplication()).getPvPBrackets(
            character.lowercase(Locale.getDefault()),
            realm.lowercase(Locale.getDefault()),
            "rbg",
            region.lowercase(Locale.getDefault()),
        ) },
            {
            pvpRBG = it.body()!!
            val url = pvpRBG.tier.key.href.replace("https://${region.lowercase(Locale.getDefault())}.api.blizzard.com/", NetworkUtils.PROXY_BASE_URL)
            downloadBracket(url, "rbg")
        }, { errorBracket.value = "rbg" })
    }

    fun download3v3Info() {
        executeAPICall({ RetroClient.getWoWClient(getApplication()).getPvPBrackets(
            character.lowercase(Locale.getDefault()),
            realm.lowercase(Locale.getDefault()),
            "3v3",
            region.lowercase(Locale.getDefault()),
        ) },
            {
                pvp3v3 = it.body()!!
                val url = pvp3v3.tier.key.href.replace("https://${region.lowercase(Locale.getDefault())}.api.blizzard.com/", NetworkUtils.PROXY_BASE_URL)
                downloadBracket(url, "3v3")
            }, { errorBracket.value = "3v3" })
    }

    fun download2v2Info() {
        executeAPICall({ RetroClient.getWoWClient(getApplication()).getPvPBrackets(
            character.lowercase(Locale.getDefault()),
            realm.lowercase(Locale.getDefault()),
            "2v2",
            region.lowercase(Locale.getDefault()),
        ) },
            {
                pvp2v2 = it.body()!!
                val url = pvp2v2.tier.key.href.replace("https://${region.lowercase(Locale.getDefault())}.api.blizzard.com/", NetworkUtils.PROXY_BASE_URL)
                downloadBracket(url, "2v2")
            }, { errorBracket.value = "2v2" })
    }

    private fun downloadBracket(url: String, bracket: String) {
        executeAPICall({ RetroClient.getWoWClient(getApplication()).getDynamicTier(url, region.lowercase(Locale.getDefault())) },
            {
                when (bracket) {
                    "rbg" -> tierRBG.value = it.body()
                    "2v2" -> tier2v2.value = it.body()
                    "3v3" -> tier3v3.value = it.body()
                }
            }, { errorBracket.value = bracket })
    }

    fun downloadPvPSummary() {
        executeAPICall({ RetroClient.getWoWClient(getApplication()).getPvPSummary(
            character.lowercase(Locale.getDefault()),
            realm.lowercase(Locale.getDefault()),
            region.lowercase(Locale.getDefault()),
        ) }, {summary.value = it.body()}, onComplete = {
            if (!EventBus.getDefault().isRegistered(this@PvPViewModel)) {
                EventBus.getDefault().register(this@PvPViewModel)
            }
        })
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
        download2v2Info()
        download3v3Info()
        downloadRBGInfo()
        downloadPvPSummary()
    }
}