package com.BlizzardArmory.ui.news.page

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.news.NewsPage
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.WebNewsScrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsPageViewModel(application: Application) : BaseViewModel(application) {

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