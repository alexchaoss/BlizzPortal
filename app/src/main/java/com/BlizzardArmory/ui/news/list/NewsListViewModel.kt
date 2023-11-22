package com.BlizzardArmory.ui.news.list

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.news.NewsMetaData
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.util.WebNewsScrapper
import com.BlizzardArmory.util.events.FilterNewsEvent
import com.BlizzardArmory.util.events.LoadNewsEvent
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import com.BlizzardArmory.util.events.MoreNewsClickEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.text.SimpleDateFormat
import java.util.Locale

class NewsListViewModel(application: Application) : BaseViewModel(application) {

    private var downloaded: MutableLiveData<Boolean> = MutableLiveData()
    private var showMore: MutableLiveData<Boolean> = MutableLiveData()
    private var newsList: MutableLiveData<MutableList<NewsMetaData>> = MutableLiveData()
    private var tempList: ArrayList<NewsMetaData> = arrayListOf()

    var pageNumber: Int = 1

    init {
        newsList.value = arrayListOf()
    }

    fun getDownloaded(): MutableLiveData<Boolean> {
        return downloaded
    }

    fun getShowMore(): MutableLiveData<Boolean> {
        return showMore
    }

    fun getNewsList(): LiveData<MutableList<NewsMetaData>> {
        return newsList
    }

    fun setupRecycler() {
        if (newsList.value!!.isNotEmpty()) {
            newsList.value = newsList.value?.distinct()?.toMutableList()
        }
    }

    fun downloadNews() {
        val job = coroutineScope.launch {
            WebNewsScrapper.parseNewsList("https://news.blizzard.com/${NetworkUtils.locale}/blog/list?pageNum=${pageNumber}&pageSize=12&community=all")
            withContext(Dispatchers.Main) {
                downloaded.value = true
            }
        }
        jobs["downloadNews"] = job
    }

    fun filterList() {
        val newsListCopy = WebNewsScrapper.newsList.toList()
        val blizzlist = newsListCopy.filter {
            NavigationActivity.userNews?.blizzNews == true && it.game.lowercase(Locale.getDefault())
                .contains("blizzard|深入暴雪|블리자드 인사이드".toRegex())
        }
        val wowlist = newsListCopy.filter {
            NavigationActivity.userNews?.wowNews == true && it.game.lowercase(Locale.getDefault())
                .contains("warcraft|魔獸|워크 래프트".toRegex())
        }
        val d3list = newsListCopy.filter {
            NavigationActivity.userNews?.d3News == true && it.game.lowercase(Locale.getDefault())
                .contains("diablo|暗黑破壞神|디아블로".toRegex())
        }
        val sc2list = newsListCopy.filter {
            NavigationActivity.userNews?.sc2News == true && it.game.lowercase(Locale.getDefault())
                .contains("starcraft|星海爭霸|스타크래프트".toRegex())
        }
        val owlist = newsListCopy.filter {
            NavigationActivity.userNews?.owNews == true && it.game.lowercase(Locale.getDefault())
                .contains("overwatch|鬥陣特攻|오버워치".toRegex())
        }
        val hslist = newsListCopy.filter {
            NavigationActivity.userNews?.hsNews == true && it.game.lowercase(Locale.getDefault())
                .contains("hearthstone|爐石戰記|하스스톤".toRegex())
        }
        val hotslist = newsListCopy.filter {
            NavigationActivity.userNews?.hotsNews == true && it.game.lowercase(Locale.getDefault())
                .contains("heroes|暴雪英霸|히어로즈 오브 더 스톰".toRegex())
        }

        tempList.clear()
        tempList.addAll(blizzlist)
        tempList.addAll(wowlist)
        tempList.addAll(d3list)
        tempList.addAll(sc2list)
        tempList.addAll(owlist)
        tempList.addAll(hslist)
        tempList.addAll(hotslist)
        tempList.sortBy {
            SimpleDateFormat("EEE MMM d yyyy HH:mm:ss z", Locale.ENGLISH).parse(it.timestamp)
        }
        tempList.reverse()
        tempList.distinctBy { Triple(it.title, it.timestamp, it.url) }

        newsList.value = tempList
        newsList.value?.add(NewsMetaData())

        if (!EventBus.getDefault().isRegistered(this@NewsListViewModel)) {
            EventBus.getDefault().register(this@NewsListViewModel)
        }
        showMore.value = false
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
        downloaded.value = false
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun showMoreEventClicked(moreNewsClickEvent: MoreNewsClickEvent) {
        pageNumber++
        downloadMore(pageNumber)
    }

    private fun downloadMore(pageNumber: Int) {
        val job = coroutineScope.launch {
            WebNewsScrapper.parseMoreNews("https://news.blizzard.com/${NetworkUtils.locale}/blog/list?pageNum=${pageNumber}&pageSize=12&community=all")
            withContext(Dispatchers.Main) {
                showMore.value = true
                EventBus.getDefault().post(LoadNewsEvent())
            }
        }
        jobs["downloadMore"] = job
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun filterEventReceived(filterNewsEvent: FilterNewsEvent) {
        downloaded.value = true
    }
}