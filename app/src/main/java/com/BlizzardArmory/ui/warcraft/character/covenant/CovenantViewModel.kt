package com.BlizzardArmory.ui.warcraft.character.covenant

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.covenant.character.soulbind.CharacterSoulbinds
import com.BlizzardArmory.model.warcraft.covenant.covenant.custom.CovenantSpells
import com.BlizzardArmory.model.warcraft.covenant.soulbind.Soulbind
import com.BlizzardArmory.model.warcraft.covenant.techtalent.TechTalent
import com.BlizzardArmory.model.warcraft.covenant.techtalenttree.TechTalentTree
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import java.util.*

class CovenantViewModel(application: Application) : BaseViewModel(application) {

    lateinit var character: String
    lateinit var realm: String
    lateinit var region: String

    private val soulbinds: MutableLiveData<CharacterSoulbinds> = MutableLiveData()
    private val soulbind: MutableLiveData<MutableMap<Long, Soulbind>> = MutableLiveData()
    private val techTalents: MutableLiveData<MutableMap<Long, List<TechTalent>>> = MutableLiveData()
    private val techTrees: MutableLiveData<MutableMap<Long, TechTalentTree>> = MutableLiveData()
    private var covenantClassSpells: MutableLiveData<List<CovenantSpells>> = MutableLiveData()
    private var covenantSpell: MutableLiveData<List<CovenantSpells>> = MutableLiveData()

    private val techTalentsTemp: MutableMap<Long, List<TechTalent>> = mutableMapOf()

    init {
        soulbind.value = mutableMapOf()
        techTrees.value = mutableMapOf()
    }

    fun getSoulbinds(): LiveData<CharacterSoulbinds> {
        return soulbinds
    }

    fun getTechTalents(): LiveData<MutableMap<Long, List<TechTalent>>> {
        return techTalents
    }

    fun getTechTrees(): LiveData<MutableMap<Long, TechTalentTree>> {
        return techTrees
    }

    fun getcovenantClassSpells(): LiveData<List<CovenantSpells>> {
        return covenantClassSpells
    }

    fun getcovenantSpell(): LiveData<List<CovenantSpells>> {
        return covenantSpell
    }

    fun downloadCharacterSoulbinds() {
        executeAPICall({
            RetroClient.getWoWClient(getApplication(), true).getSoulbinds(
                character.lowercase(Locale.getDefault()),
                realm.lowercase(Locale.getDefault()),
                region.lowercase(Locale.getDefault()),
            )
        },
            {
                soulbinds.value = it.body()
                for (soulbind in soulbinds.value?.soulbinds!!) {
                    downloadSoulbind(soulbind.soulbind.id)
                }
            })
    }

    fun downloadSoulbind(id: Long) {
        executeAPICall({
            RetroClient.getWoWClient(getApplication(), true).getSoulbind(
                id,
                "static-${region.lowercase()}",
                region.lowercase(Locale.getDefault())
            )
        },
            {
                soulbind.value?.set(id, it.body()!!)
                downloadTechTree(soulbind.value?.get(id)!!.techtalentTree.id, id)
            })
    }

    private fun downloadTechTree(techTreeIndex: Long, soulbindId: Long) {
        executeAPICall({ RetroClient.getWoWClient(getApplication(), true).getTechTree(techTreeIndex, region.lowercase()) },
            {
                techTrees.value?.set(techTreeIndex, it.body()!!)
                downloadTechTalents(techTrees.value?.get(techTreeIndex)!!.id, soulbindId)
            })
    }

    private fun downloadTechTalents(id: Long, soulbindId: Long) {
        val tempTalents = mutableListOf<TechTalent>()
        for (talent in techTrees.value?.get(id)!!.talents) {
            executeAPICall({ RetroClient.getWoWClient(getApplication(), true).getTechTalent(talent.id, region.lowercase()) },
                {
                    if (tempTalents.size == techTrees.value?.get(id)!!.talents.size - 1) {
                        if (techTalentsTemp.size == soulbind.value!!.size - 1) {
                            techTalentsTemp[soulbindId] = tempTalents.sortedBy { talent -> talent.tier }
                            techTalents.value = techTalentsTemp
                        } else {
                            tempTalents.add(it.body()!!)
                            techTalentsTemp[soulbindId] = tempTalents.sortedBy { talent -> talent.tier }
                        }
                    } else {
                        tempTalents.add(it.body()!!)
                    }
                }, onComplete = {
                    if (!EventBus.getDefault().isRegistered(this@CovenantViewModel)) {
                        EventBus.getDefault().register(this@CovenantViewModel)
                    }
                })

        }
    }

    fun downloadCovenantClassSpell(characterClass: Int) {
        executeAPICall({ RetroClient.getAPIClient(getApplication(), true).getCovenantClassSpells(characterClass) }, { covenantClassSpells.value = it.body() })
    }

    fun downloadCovenantSpell(covenantId: Int) {
        executeAPICall({ RetroClient.getAPIClient(getApplication(), true).getCovenantSpells(covenantId) }, { covenantSpell.value = it.body() })
    }
}