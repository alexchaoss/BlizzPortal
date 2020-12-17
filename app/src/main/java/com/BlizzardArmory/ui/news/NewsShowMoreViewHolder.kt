package com.BlizzardArmory.ui.news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.util.events.MoreNewsClickEvent
import org.greenrobot.eventbus.EventBus

class NewsShowMoreViewHolder(inflater: LayoutInflater, parent: ViewGroup, val context: Context) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.news_show_more_item, parent, false)) {

    private var showMore: Button? = null


    init {
        showMore = itemView.findViewById(R.id.show_more)
    }

    fun bind() {
        showMore?.setOnClickListener {
            EventBus.getDefault().post(MoreNewsClickEvent())
        }
    }
}