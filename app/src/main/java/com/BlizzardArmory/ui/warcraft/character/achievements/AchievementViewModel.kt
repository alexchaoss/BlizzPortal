package com.BlizzardArmory.ui.warcraft.character.achievements

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.achievements.categories.Categories
import com.BlizzardArmory.model.warcraft.achievements.characterachievements.Achievements
import com.BlizzardArmory.model.warcraft.achievements.custom.DetailedAchievement
import com.BlizzardArmory.model.warcraft.achievements.custom.DetailedAchievements
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class AchievementViewModel(application: Application) : BaseViewModel(application) {

    lateinit var character: String
    lateinit var realm: String
    lateinit var region: String

    private var categories: MutableLiveData<Categories> = MutableLiveData()
    private var characterAchievements: MutableLiveData<Achievements> = MutableLiveData()
    private var allAchievements: MutableLiveData<DetailedAchievements> = MutableLiveData()
    private var mappedAchievements: MutableLiveData<MutableMap<Long, List<DetailedAchievement>?>> = MutableLiveData()

    fun getAllAchievements(): LiveData<DetailedAchievements> {
        return allAchievements
    }

    fun getCharacterAchievements(): LiveData<Achievements> {
        return characterAchievements
    }

    fun getMappedAchievements(): LiveData<MutableMap<Long, List<DetailedAchievement>?>> {
        return mappedAchievements
    }

    fun getCategories(): LiveData<Categories> {
        return categories
    }

    fun downloadAchievementInformation() {
        executeAPICall({ RetroClient.getAPIClient(getApplication()).getAllAchievements() }, { allAchievements.value = it.body() })
    }

    fun downloadCharacterAchievements() {
        executeAPICall({
            RetroClient.getWoWClient(getApplication()).getCharacterAchievements(
                character,
                realm,
                region,
            )
        }, { characterAchievements.value = it.body() })
    }

    fun downloadCategories() {
        executeAPICall({ RetroClient.getAPIClient(getApplication()).getAchievementCategories() },
            {
                categories.value = it.body()
                val tempCats = categories.value?.filter { cat -> cat.id != 15076L }
                categories.value?.clear()
                categories.value?.addAll(tempCats!!)
                createAchievementsMap()
            })
    }

    private fun createAchievementsMap() {
        mappedAchievements.value = categories.value?.groupBy { it.id }
            ?.mapValues { map ->
                allAchievements.value?.filter { a ->
                    a.category_id == map.key && characterAchievements.value!!.achievements.any { b -> a.id == b.id }
                }
            }?.toMutableMap()!!
        if (!EventBus.getDefault().isRegistered(this@AchievementViewModel)) {
            EventBus.getDefault().register(this@AchievementViewModel)
        }
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
        downloadAchievementInformation()
    }
}