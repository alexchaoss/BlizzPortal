package com.BlizzardArmory.ui.overwatch.overwatchleague

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.overwatch.statslab.Stat

class StatsAdapter(private val list: List<Stat>, private val context: Context)
    : RecyclerView.Adapter<StatsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StatsViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        val stat: Stat = list[position]
        holder.bind(stat)
    }

    override fun getItemCount(): Int = list.size

}