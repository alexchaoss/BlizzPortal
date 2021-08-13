package com.BlizzardArmory.ui.overwatch.overwatchleague

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.overwatch.statslab.Hero


class HeroViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.overwatch_league_generic_expand, parent, false)) {

    var name: TextView? = null
    var recycler: RecyclerView? = null

    init {
        name = itemView.findViewById(R.id.name)
        recycler = itemView.findViewById(R.id.recycler)
    }

    fun bind(hero: Hero) {
        name?.text = hero.name
        recycler?.apply {
            adapter = StatsAdapter(hero.stats, context)
        }
    }
}
