package com.BlizzardArmory.ui.overwatch.overwatchleague

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.overwatch.statslab.Stat
import kotlin.math.roundToInt


class StatsViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.overwatch_league_hero_stat, parent, false)) {

    var name: TextView? = null
    var value: TextView? = null

    init {
        name = itemView.findViewById(R.id.name)
        value = itemView.findViewById(R.id.value)
    }

    fun bind(stat: Stat) {
        name?.text = stat.statName
        value?.text = stat.statAmount.roundToInt().toString()
    }
}
