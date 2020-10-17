package com.BlizzardArmory.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.BlizzardArmory.R
import com.BlizzardArmory.model.news.NewsPage
import com.BlizzardArmory.util.HTMLtoViewsConverter
import com.BlizzardArmory.util.WebNewsScrapper
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class NewsPageFragment : Fragment() {

    private var newsPage: NewsPage? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = requireArguments()
        val url = bundle.getString("url")
        GlobalScope.launch(Dispatchers.Main) {
            launch(Dispatchers.IO) {
                newsPage = WebNewsScrapper.parseNewsPage(url!!)
                Log.i("NEWS PAGE", newsPage.toString())
            }.join()
            Glide.with(requireContext()).load(newsPage?.imageURL).into(image)
            game.text = newsPage?.game
            title.text = newsPage?.title
            author.text = newsPage?.author
            date.text = newsPage?.date
            val htmlConverter = HTMLtoViewsConverter(requireContext())
            htmlConverter.parseHtml(newsPage?.body!!)
            container.addView(htmlConverter.linearLayout)
        }
    }
}