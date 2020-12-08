package com.BlizzardArmory.ui.warcraft.pvp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.pvp.summary.PvpMapStatistics

class BattlegroundAdapter(private val list: List<PvpMapStatistics>, private val context: Context)
    : RecyclerView.Adapter<BattlegroundViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BattlegroundViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BattlegroundViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: BattlegroundViewHolder, position: Int) {
        val pvpMapStatistics: PvpMapStatistics = list[position]
        holder.bind(pvpMapStatistics, position)
    }

    override fun getItemCount(): Int = list.size

}