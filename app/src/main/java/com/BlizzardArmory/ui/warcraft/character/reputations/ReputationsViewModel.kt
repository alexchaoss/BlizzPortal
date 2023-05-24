package com.BlizzardArmory.ui.warcraft.character.reputations

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.reputations.characterreputations.RepByExpansion
import com.BlizzardArmory.model.warcraft.reputations.characterreputations.Reputation
import com.BlizzardArmory.model.warcraft.reputations.characterreputations.Reputations
import com.BlizzardArmory.model.warcraft.reputations.custom.ReputationPlusParentInfo
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*

class ReputationsViewModel(application: Application) : BaseViewModel(application) {

    lateinit var character: String
    lateinit var realm: String
    lateinit var region: String

    val repsByExpac = arrayListOf<ArrayList<Reputations>>()

    private var reputations: MutableLiveData<Reputation> = MutableLiveData()
    private var reputationsWithParentInfo: MutableLiveData<List<ReputationPlusParentInfo>> = MutableLiveData()

    init {
        for (i in 0..9) {
            repsByExpac.add(arrayListOf())
        }
    }

    fun getReputations(): LiveData<Reputation> {
        return reputations
    }

    fun getReputationsWithParentInfo(): LiveData<List<ReputationPlusParentInfo>> {
        return reputationsWithParentInfo
    }

    fun downloadReputationsPlusParentInfo() {
        executeAPICall({ RetroClient.getAPIClient(getApplication(), true).getReputationPlusParentInfo() },
            {
                reputationsWithParentInfo.value = it.body()
                downloadReputations()
            })
    }

    private fun downloadReputations() {
        executeAPICall({
            RetroClient.getWoWClient(getApplication()).getReputations(
                character.lowercase(Locale.getDefault()),
                realm.lowercase(Locale.getDefault()),
                region.lowercase(Locale.getDefault()),
            )
        },
            {
                reputations.value = it.body()
                sortRepsByExpansions(reputations.value!!)
            }, onComplete = {
                if (!EventBus.getDefault().isRegistered(this@ReputationsViewModel)) {
                    EventBus.getDefault().register(this@ReputationsViewModel)
                }
            })

    }


    private fun sortRepsByExpansions(reputation: Reputation) {
        repsByExpac.forEach {
            it.clear()
        }
        for (reps in reputation.reputations) {
            for (enumRep in RepByExpansion.values()) {
                if (reps.faction.id == enumRep.id) {
                    when (enumRep.xpac) {
                        "Classic" -> repsByExpac[0].add(reps)
                        "Burning Crusade" -> repsByExpac[1].add(reps)
                        "Wrath of the Lich King" -> repsByExpac[2].add(reps)
                        "Cataclysm" -> repsByExpac[3].add(reps)
                        "Mists of Pandaria" -> repsByExpac[4].add(reps)
                        "Warlords of Draenor" -> repsByExpac[5].add(reps)
                        "Legion" -> repsByExpac[6].add(reps)
                        "Battle for Azeroth" -> repsByExpac[7].add(reps)
                        "Shadowlands" -> repsByExpac[8].add(reps)
                        "Dragonflight" -> repsByExpac[9].add(reps)
                    }
                }
            }
        }
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
        downloadReputationsPlusParentInfo()
    }
}