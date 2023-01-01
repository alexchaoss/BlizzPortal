package com.BlizzardArmory.ui.warcraft.character.talents

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.covenant.techtalent.TalentTree
import com.BlizzardArmory.model.warcraft.talents.playerspec.PlayerSpecializations
import com.BlizzardArmory.model.warcraft.talents.trees.TalentTrees
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.WoWClassName
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*

class TalentsViewModel(application: Application) : BaseViewModel(application) {

    lateinit var character: String
    lateinit var realm: String
    lateinit var region: String


    private var talentTrees: MutableLiveData<TalentTrees> = MutableLiveData()
    private var talentTree: MutableLiveData<TalentTree> = MutableLiveData()
    private var playerSpecialization: MutableLiveData<PlayerSpecializations> = MutableLiveData()
    private var playerClass: MutableLiveData<String> = MutableLiveData()

    fun getTalentTrees(): LiveData<TalentTrees> {
        return talentTrees
    }

    fun getTalentTree(): LiveData<TalentTree> {
        return talentTree
    }

    fun getPlayerSpecialization(): LiveData<PlayerSpecializations> {
        return playerSpecialization
    }

    fun getPlayerClass(): LiveData<String> {
        return playerClass
    }

    fun setPlayerClass(plClass: Int) {
        playerClass.value = WoWClassName.get(plClass)
    }

    fun downloadCharacterSpecialization() {
        executeAPICall({ RetroClient.getWoWClient(getApplication()).getSpecs(character, realm) }, { playerSpecialization.value = it.body() },
            onComplete = {
                if (!EventBus.getDefault().isRegistered(this@TalentsViewModel)) {
                    EventBus.getDefault().register(this@TalentsViewModel)
                }
            })
    }

    fun downloadTalentTreesIndex() {
        executeAPICall(
            { RetroClient.getWoWClient(getApplication()).getTalentTrees() },
            {
                talentTrees.value = it.body()
                downloadTalentTree()
            })
    }

    private fun downloadTalentTree() {
        val classId = talentTrees.value?.classTalentTrees
            ?.find { it.name.lowercase(Locale.getDefault()) == playerClass.value?.lowercase(Locale.getDefault()) }
            ?.key?.href?.split("/")
            ?.last()
            ?.split("?")
            ?.first()

        executeAPICall({ RetroClient.getWoWClient(getApplication(), true).getTalentTree(classId!!.toLong(), playerSpecialization.value?.active_specialization?.id!!.toLong()) },
            { response ->
                talentTree.value = response.body()
                downloadTalentTree()
            }, { response -> errorCode.value = response.code() })
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
        downloadCharacterSpecialization()
    }
}