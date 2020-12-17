package com.BlizzardArmory.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.news.NewsPage
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.WebNewsScrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsPageViewModel : BaseViewModel() {

    private var newsPage: MutableLiveData<NewsPage> = MutableLiveData()

    fun getNewsPage(): LiveData<NewsPage> {
        return newsPage
    }

    fun downloadNewsPage(url: String) {
        val job = coroutineScope.launch {
            val response = WebNewsScrapper.parseNewsPage(url)
            withContext(Dispatchers.Main) {
                newsPage.value = response
            }
        }
        jobs.add(job)
    }
}