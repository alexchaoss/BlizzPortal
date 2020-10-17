package com.BlizzardArmory.ui.news

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.news.NewsMetaData
import com.bumptech.glide.Glide

class NewsViewHolder(inflater: LayoutInflater, parent: ViewGroup, val context: Context) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.news_list_item, parent, false)) {

    var image: ImageView? = null
    var title: TextView? = null
    var description: TextView? = null
    var time: TextView? = null
    var game: TextView? = null

    init {
        image = itemView.findViewById(R.id.image)
        title = itemView.findViewById(R.id.title)
        description = itemView.findViewById(R.id.description)
        time = itemView.findViewById(R.id.time)
        game = itemView.findViewById(R.id.game)
    }

    fun bind(news: NewsMetaData) {
        Glide.with(itemView).load(news.imageUrl).into(image!!)
        title?.text = news.title
        description?.text = news.description
        game?.text = news.game
        time?.text = news.timeElapsed

        itemView.setOnClickListener {
            val fragment: Fragment = NewsPageFragment()
            val bundle = Bundle()
            bundle.putString("url", news.url)
            fragment.arguments = bundle
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
                    .replace(R.id.news_fragment, fragment, "news_page_fragment")
                    .addToBackStack("news_page").commit()
            fragmentManager.executePendingTransactions()
        }
    }
}