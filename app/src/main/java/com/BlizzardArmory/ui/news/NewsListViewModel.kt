package com.BlizzardArmory.ui.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.BlizzardArmory.model.news.NewsMetaData
import com.BlizzardArmory.ui.main.MainActivity
import com.BlizzardArmory.ui.navigation.GamesActivity
import com.BlizzardArmory.util.WebNewsScrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class NewsListViewModel : ViewModel() {

    private val parentJob = SupervisorJob()
    private val coroutineScrope = CoroutineScope(parentJob + Dispatchers.IO)

    private var downloaded: MutableLiveData<Boolean> = MutableLiveData()
    private var newsList: MutableLiveData<ArrayList<NewsMetaData>> = MutableLiveData()
    private var tempList: MutableLiveData<ArrayList<NewsMetaData>> = MutableLiveData()

    init {
        newsList.value = arrayListOf()
        tempList.value = arrayListOf()
    }

    fun getDownloaded(): MutableLiveData<Boolean> {
        return downloaded
    }

    fun getNewsList(): LiveData<ArrayList<NewsMetaData>> {
        return newsList
    }

    fun setupRecycler() {
        if (newsList.value!!.isNotEmpty()) {
            newsList.value = newsList.value!!.distinct() as ArrayList<NewsMetaData>
        }
    }

    suspend fun downloadNews() {
        Log.i("called", "webscrapper download")
        coroutineScrope.launch {
            WebNewsScrapper.parseNewsList("https://news.blizzard.com/${MainActivity.locale}")
            Log.i("NEWS", WebNewsScrapper.newsList.toString())
        }.join()
        downloaded.value = true
    }

    fun filterList() {
        val blizzlist = WebNewsScrapper.newsList.filter { GamesActivity.userNews?.blizzNews!! && it.game.toLowerCase(Locale.ROOT).contains("blizzard|深入暴雪|블리자드 인사이드".toRegex()) }
        val wowlist = WebNewsScrapper.newsList.filter { GamesActivity.userNews?.wowNews!! && it.game.toLowerCase(Locale.ROOT).contains("warcraft|魔獸|워크 래프트".toRegex()) }
        val d3list = WebNewsScrapper.newsList.filter { GamesActivity.userNews?.d3News!! && it.game.toLowerCase(Locale.ROOT).contains("diablo|暗黑破壞神|디아블로".toRegex()) }
        val sc2list = WebNewsScrapper.newsList.filter { GamesActivity.userNews?.sc2News!! && it.game.toLowerCase(Locale.ROOT).contains("starcraft|星海爭霸|스타크래프트".toRegex()) }
        val owlist = WebNewsScrapper.newsList.filter { GamesActivity.userNews?.owNews!! && it.game.toLowerCase(Locale.ROOT).contains("overwatch|鬥陣特攻|오버워치".toRegex()) }
        val hslist = WebNewsScrapper.newsList.filter { GamesActivity.userNews?.hsNews!! && it.game.toLowerCase(Locale.ROOT).contains("hearthstone|爐石戰記|하스스톤".toRegex()) }
        val hotslist = WebNewsScrapper.newsList.filter { GamesActivity.userNews?.hotsNews!! && it.game.toLowerCase(Locale.ROOT).contains("heroes|暴雪英霸|히어로즈 오브 더 스톰".toRegex()) }

        tempList.value!!.clear()
        tempList.value!!.addAll(blizzlist)
        tempList.value!!.addAll(wowlist)
        tempList.value!!.addAll(d3list)
        tempList.value!!.addAll(sc2list)
        tempList.value!!.addAll(owlist)
        tempList.value!!.addAll(hslist)
        tempList.value!!.addAll(hotslist)
        tempList.value!!.sortBy {
            SimpleDateFormat("EEE MMM d yyyy HH:mm:ss z", Locale.ENGLISH).parse(it.timestamp)
        }
        tempList.value!!.reverse()
        newsList.value = tempList.value
    }
}