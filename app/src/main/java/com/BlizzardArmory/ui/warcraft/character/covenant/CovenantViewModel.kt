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
    private val soulbind: MutableLiveData<Soulbind> = MutableLiveData()
    private val techTalents: MutableLiveData<List<TechTalent>> = MutableLiveData()
    private val techTrees: MutableLiveData<TechTalentTree> = MutableLiveData()
    private var covenantClassSpells: MutableLiveData<List<CovenantSpells>> = MutableLiveData()
    private var covenantSpell: MutableLiveData<List<CovenantSpells>> = MutableLiveData()

    fun getSoulbinds(): LiveData<CharacterSoulbinds> {
        return soulbinds
    }

    fun getTechTalents(): LiveData<List<TechTalent>> {
        return techTalents
    }

    fun getTechTrees(): LiveData<TechTalentTree> {
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
                    downloadSoulbind(soulbinds.value!!.soulbinds[0].soulbind.id)
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
                    soulbind.value = response.body()
                    downloadTechTree()
                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                }
            }
        }
        jobs.add(job)
    }

    fun downloadTechTree() {
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient(getApplication(), true)
                .getTechTree(soulbind.value!!.techtalentTree.id, region.lowercase())
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    techTrees.value = response.body()!!
                    downloadTechTalents()
                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                }
            }
        }
        jobs.add(job)
    }

    fun downloadTechTalents() {
        val tempTalents = mutableListOf<TechTalent>()
        for (talent in techTrees.value?.talents!!) {
            val job = coroutineScope.launch {
                val response = RetroClient.getWoWClient(getApplication(), true)
                    .getTechTalent(talent.id, region.lowercase())
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        if (tempTalents.size == techTrees.value!!.talents.size - 1) {
                            Log.i("TEST", "Download talents over")
                            tempTalents.add(response.body()!!)
                            techTalents.value = tempTalents.sortedBy { it.tier }
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
                .getCovenantSpells(characterClass)
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