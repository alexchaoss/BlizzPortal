package com.BlizzardArmory.ui.warcraft.character.achievements

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

class AchievementViewModel : BaseViewModel() {

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
        val job = coroutineScope.launch {
            val response = RetroClient.getAPIClient().getAllAchievements()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    allAchievements.value = response.body()
                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                }
            }
        }
        jobs.add(job)
    }

    fun downloadCharacterAchievements() {
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient().getCharacterAchievements(
                character,
                realm,
                battlenetOAuth2Helper?.accessToken!!,
                region,
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    characterAchievements.value = response.body()
                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                }
            }
        }
        jobs.add(job)
    }

    fun downloadCategories() {
        val job = coroutineScope.launch {
            val response = RetroClient.getAPIClient().getAchievementCategories()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    categories.value = response.body()
                    val tempCats = categories.value?.filter { it.id != 15076L }
                    categories.value?.clear()
                    categories.value?.addAll(tempCats!!)
                    createAchievementsMap()
                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                }
            }
        }
        jobs.add(job)
    }

    private fun createAchievementsMap() {
        Log.i("IS NULL", allAchievements.value.isNullOrEmpty()
            .toString() + " " + categories.value.isNullOrEmpty()
            .toString() + " " + (characterAchievements.value != null).toString())
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