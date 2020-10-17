package com.BlizzardArmory.ui.news

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.news.NewsMetaData

class NewsAdapter(private val list: List<NewsMetaData>, val context: Context)
    : RecyclerView.Adapter<NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        Log.i("SIZE", list.size.toString())
        val inflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news: NewsMetaData = list[position]
        holder.bind(news)
    }

    override fun getItemCount(): Int = list.size

}