package com.BlizzardArmory.ui.warcraft.character.pvp

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

class PvPViewModel : BaseViewModel() {

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
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient().getPvPBrackets(
                character.lowercase(Locale.getDefault()),
                realm.lowercase(Locale.getDefault()),
                "rbg",
                NetworkUtils.locale,
                region.lowercase(Locale.getDefault()),
                battlenetOAuth2Helper!!.accessToken
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    pvpRBG = response.body()!!
                    val url =
                        pvpRBG.tier.key.href.replace("https://${region.lowercase(Locale.getDefault())}.api.blizzard.com/", NetworkUtils.HEROKU_PROXY_BASE_URL)
                    downloadBracket(url, "rbg")
                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                    errorBracket.value = "rbg"
                }
            }
        }
        jobs.add(job)
    }

    fun download3v3Info() {
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient().getPvPBrackets(
                character.lowercase(Locale.getDefault()),
                realm.lowercase(Locale.getDefault()),
                "3v3",
                NetworkUtils.locale,
                region.lowercase(Locale.getDefault()),
                battlenetOAuth2Helper!!.accessToken
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    pvp3v3 = response.body()!!
                    val url =
                        pvp3v3.tier.key.href.replace("https://${region.lowercase(Locale.getDefault())}.api.blizzard.com/", NetworkUtils.HEROKU_PROXY_BASE_URL)
                    downloadBracket(url, "3v3")
                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                    errorBracket.value = "3v3"
                }
            }
        }
        jobs.add(job)
    }

    fun download2v2Info() {
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient().getPvPBrackets(
                character.lowercase(Locale.getDefault()),
                realm.lowercase(Locale.getDefault()),
                "2v2",
                NetworkUtils.locale,
                region.lowercase(Locale.getDefault()),
                battlenetOAuth2Helper!!.accessToken
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    pvp2v2 = response.body()!!
                    val url =
                        pvp2v2.tier.key.href.replace("https://${region.lowercase(Locale.getDefault())}.api.blizzard.com/", NetworkUtils.HEROKU_PROXY_BASE_URL)
                    downloadBracket(url, "2v2")
                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                    errorBracket.value = "2v2"
                }
            }
        }
        jobs.add(job)
    }

    private fun downloadBracket(url: String, bracket: String) {
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient()
                .getDynamicTier(url, region.lowercase(Locale.getDefault()), NetworkUtils.locale)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    when (bracket) {
                        "rbg" -> tierRBG.value = response.body()
                        "2v2" -> tier2v2.value = response.body()
                        "3v3" -> tier3v3.value = response.body()
                    }
                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                    errorBracket.value = bracket
                }
            }
        }
        jobs.add(job)
    }

    fun downloadPvPSummary() {
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient().getPvPSummary(
                character.lowercase(Locale.getDefault()),
                realm.lowercase(Locale.getDefault()),
                NetworkUtils.locale,
                region.lowercase(Locale.getDefault()),
                battlenetOAuth2Helper!!.accessToken
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    summary.value = response.body()
                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                }
                if (!EventBus.getDefault().isRegistered(this@PvPViewModel)) {
                    EventBus.getDefault().register(this@PvPViewModel)
                }
            }
        }
        jobs.add(job)
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