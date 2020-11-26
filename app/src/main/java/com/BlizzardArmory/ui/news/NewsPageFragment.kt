package com.BlizzardArmory.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.BlizzardArmory.databinding.NewsFragmentBinding
import com.BlizzardArmory.model.news.NewsPage
import com.BlizzardArmory.ui.GamesActivity
import com.BlizzardArmory.util.HTMLtoViewsConverter
import com.BlizzardArmory.util.WebNewsScrapper
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class NewsPageFragment : Fragment() {

    private var newsPage: NewsPage? = null
    private lateinit var binding: NewsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        addOnBackPressCallback(activity as GamesActivity)
        binding = NewsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = requireArguments()
        val url = bundle.getString("url")
        GlobalScope.launch(Dispatchers.Main) {
            launch(Dispatchers.IO) {
                newsPage = WebNewsScrapper.parseNewsPage(url!!)
            }.join()
            Glide.with(requireContext()).load(newsPage?.imageURL).into(binding.image)
            binding.game.text = newsPage?.game
            binding.title.text = newsPage?.title
            binding.author.text = newsPage?.author
            binding.date.text = newsPage?.date
            val htmlConverter = HTMLtoViewsConverter(requireContext())
            htmlConverter.parseHtml(newsPage?.body!!)
            binding.container.addView(htmlConverter.linearLayout)
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

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        activity?.supportFragmentManager?.beginTransaction()?.detach(this)?.attach(this)?.commit()
    }

    companion object{
        fun addOnBackPressCallback(activity: GamesActivity){
            activity.onBackPressedDispatcher.addCallback {
                NewsListFragment.addOnBackPressCallback(activity)
                activity.supportFragmentManager.popBackStack()
            }
        }
    }
}