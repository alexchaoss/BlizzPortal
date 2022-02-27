package com.BlizzardArmory.ui.help

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.help.HelpItem

class HelpAdapter(private val list: List<HelpItem>) :
    RecyclerView.Adapter<HelpViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HelpViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: HelpViewHolder, position: Int) {
        val helpItemObject: HelpItem = list[position]
        holder.bind(helpItemObject)
    }

    override fun getItemCount(): Int = list.size

}