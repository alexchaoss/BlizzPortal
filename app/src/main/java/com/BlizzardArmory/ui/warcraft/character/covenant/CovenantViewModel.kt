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
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient(getApplication(), true).getSoulbinds(
                character.lowercase(Locale.getDefault()),
                realm.lowercase(Locale.getDefault()),
                region.lowercase(Locale.getDefault()),
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    soulbinds.value = response.body()
                    for (soulbind in soulbinds.value?.soulbinds!!) {
                        downloadSoulbind(soulbind.soulbind.id)
                    }

                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                }
            }
        }
        jobs.add(job)
    }

    fun downloadSoulbind(id: Long) {
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient(getApplication(), true).getSoulbind(
                id,
                "static-${region.lowercase()}",
                region.lowercase(Locale.getDefault())
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    soulbind.value?.set(id, response.body()!!)
                    downloadTechTree(soulbind.value?.get(id)!!.techtalentTree.id, id)
                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                }
            }
        }
        jobs.add(job)
    }

    fun downloadTechTree(techTreeIndex: Long, soulbindId: Long) {
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient(getApplication(), true)
                .getTechTree(techTreeIndex, region.lowercase())
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    techTrees.value?.set(techTreeIndex, response.body()!!)
                    downloadTechTalents(techTrees.value?.get(techTreeIndex)!!.id, soulbindId)
                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                }
            }
        }
        jobs.add(job)
    }

    fun downloadTechTalents(id: Long, soulbindId: Long) {
        val tempTalents = mutableListOf<TechTalent>()
        for (talent in techTrees.value?.get(id)!!.talents) {
            val job = coroutineScope.launch {
                val response = RetroClient.getWoWClient(getApplication(), true)
                    .getTechTalent(talent.id, region.lowercase())
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        if (tempTalents.size == techTrees.value?.get(id)!!.talents.size - 1) {
                            if (techTalentsTemp.size == soulbind.value!!.size - 1) {
                                techTalentsTemp.set(soulbindId, tempTalents.sortedBy { it.tier })
                                techTalents.value = techTalentsTemp
                            } else {
                                tempTalents.add(response.body()!!)
                                techTalentsTemp.set(soulbindId, tempTalents.sortedBy { it.tier })
                            }
                        } else {
                            tempTalents.add(response.body()!!)
                        }

                    } else {
                        Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                    }
                }
                if (!EventBus.getDefault().isRegistered(this@CovenantViewModel)) {
                    EventBus.getDefault().register(this@CovenantViewModel)
                }
            }
            jobs.add(job)
        }
    }

    fun downloadCovenantClassSpell(characterClass: Int) {
        val job = coroutineScope.launch {
            val response = RetroClient.getAPIClient(getApplication(), true)
                .getCovenantClassSpells(characterClass)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    covenantClassSpells.value = response.body()
                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                }
            }
        }
        jobs.add(job)
    }

    fun downloadCovenantSpell(covenantId: Int) {
        val job = coroutineScope.launch {
            val response = RetroClient.getAPIClient(getApplication(), true)
                .getCovenantSpells(covenantId)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    covenantSpell.value = response.body()
                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                }
            }
        }
        jobs.add(job)
    }
}