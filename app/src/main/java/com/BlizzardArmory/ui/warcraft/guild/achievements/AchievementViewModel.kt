package com.BlizzardArmory.ui.warcraft.guild.achievements

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.achievements.categories.Categories
import com.BlizzardArmory.model.warcraft.achievements.custom.DetailedAchievement
import com.BlizzardArmory.model.warcraft.achievements.custom.DetailedAchievements
import com.BlizzardArmory.model.warcraft.guild.achievements.AchievementsInformation
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class AchievementViewModel(application: Application) : BaseViewModel(application) {

    private val guildAchievements: MutableLiveData<AchievementsInformation> = MutableLiveData()
    private var categories: MutableLiveData<Categories> = MutableLiveData()
    private var allAchievements: MutableLiveData<DetailedAchievements> = MutableLiveData()
    private var mappedAchievements: MutableLiveData<MutableMap<Long, List<DetailedAchievement>?>> =
        MutableLiveData()

    fun getGuildAchievements(): MutableLiveData<AchievementsInformation> {
        return guildAchievements
    }

    fun getAllAchievements(): LiveData<DetailedAchievements> {
        return allAchievements
    }

    fun getMappedAchievements(): LiveData<MutableMap<Long, List<DetailedAchievement>?>> {
        return mappedAchievements
    }

    fun getCategories(): LiveData<Categories> {
        return categories
    }

    fun downloadGuildAchivements(realm: String, name: String, region: String) {
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient(getApplication())
                .getGuildAchievements(realm, name,
                    "profile-$region", region)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    guildAchievements.value = response.body()
                }
            }
        }
        jobs.add(job)
    }

    fun downloadAchievementInformation() {
        val job = coroutineScope.launch {
            val response = RetroClient.getAPIClient(getApplication()).getAllAchievements()
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

    fun downloadCategories() {
        val job = coroutineScope.launch {
            val response = RetroClient.getAPIClient(getApplication()).getAchievementCategories()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    categories.value = response.body()
                    val tempCats = categories.value?.filter { it.isGuildCategory }
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
        mappedAchievements.value = categories.value?.groupBy { it.id }
            ?.mapValues { map ->
                allAchievements.value?.filter { a ->
                    a.category_id == map.key && guildAchievements.value!!.achievements.any { b -> a.id == b.id }
                }
            }?.toMutableMap()!!
        if (!EventBus.getDefault().isRegistered(this@AchievementViewModel)) {
            EventBus.getDefault().register(this@AchievementViewModel)
        }
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
    }
}