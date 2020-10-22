package com.BlizzardArmory.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.news.NewsMetaData
import com.BlizzardArmory.ui.GamesActivity
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.util.WebNewsScrapper
import com.BlizzardArmory.util.events.FilterNewsEvent
import kotlinx.android.synthetic.main.news_list_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.text.SimpleDateFormat
import java.util.*

class NewsListFragment : Fragment() {

    var downloaded = false
    var newsList = arrayListOf<NewsMetaData>()
    var tempList = arrayListOf<NewsMetaData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNews()

        news_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 100) {
                    back_to_top.visibility = View.VISIBLE
                } else if (dy == 0) {
                    back_to_top.visibility = View.GONE
                }
            }
        })

        back_to_top.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                GlobalScope.launch(Dispatchers.Main) {
                    news_recycler.layoutManager?.smoothScrollToPosition(news_recycler, RecyclerView.State(), 0)
                    delay((news_recycler.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() * 100L)
                }.join()
                news_recycler.scrollToPosition(0)
            }
            back_to_top.visibility = View.GONE
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private fun setupNews() {
        if (!downloaded) {
            GlobalScope.launch(Dispatchers.Main) {
                launch(Dispatchers.IO) {
                    WebNewsScrapper.parseNewsList("https://news.blizzard.com/${MainActivity.locale}")
                    Log.i("NEWS", WebNewsScrapper.newsList.toString())
                }.join()
                filterList()
                setupRecycler()
                downloaded = true
            }
        } else {
            setupRecycler()
        }
    }

    private fun setupRecycler() {
        newsList = newsList.distinct() as ArrayList<NewsMetaData>
        news_recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = NewsAdapter(newsList, context)
            adapter!!.notifyDataSetChanged()
        }
    }

    private fun filterList() {
        val blizzlist = WebNewsScrapper.newsList.filter { GamesActivity.userNews?.blizzNews!! && it.game.toLowerCase(Locale.ROOT).contains("blizzard") }
        val wowlist = WebNewsScrapper.newsList.filter { GamesActivity.userNews?.wowNews!! && it.game.toLowerCase(Locale.ROOT).contains("warcraft") }
        val d3list = WebNewsScrapper.newsList.filter { GamesActivity.userNews?.d3News!! && it.game.toLowerCase(Locale.ROOT).contains("diablo") }
        val sc2list = WebNewsScrapper.newsList.filter { GamesActivity.userNews?.sc2News!! && it.game.toLowerCase(Locale.ROOT).contains("starcraft") }
        val owlist = WebNewsScrapper.newsList.filter { GamesActivity.userNews?.owNews!! && it.game.toLowerCase(Locale.ROOT).contains("overwatch") }
        val hslist = WebNewsScrapper.newsList.filter { GamesActivity.userNews?.hsNews!! && it.game.toLowerCase(Locale.ROOT).contains("hearthstone") }
        val hotslist = WebNewsScrapper.newsList.filter { GamesActivity.userNews?.hotsNews!! && it.game.toLowerCase(Locale.ROOT).contains("heroes") }

        tempList.clear()
        tempList.addAll(blizzlist)
        tempList.addAll(wowlist)
        tempList.addAll(d3list)
        tempList.addAll(sc2list)
        tempList.addAll(owlist)
        tempList.addAll(hslist)
        tempList.addAll(hotslist)

        newsList = tempList
        newsList.sortBy {
            SimpleDateFormat("EEE MMM d yyyy HH:mm:ss z").parse(it.timestamp)
        }
        newsList.reverse()
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun retryEventReceived(filterNewsEvent: FilterNewsEvent) {
        val recyclerViewState = news_recycler.layoutManager?.onSaveInstanceState()
        filterList()
        setupRecycler()
        news_recycler.layoutManager?.onRestoreInstanceState(recyclerViewState)
    }

}