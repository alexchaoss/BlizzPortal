package com.BlizzardArmory.ui.overwatch.overwatchleague

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.overwatch.statslab.Hero

class HeroAdapter(private val list: List<Hero>, private val context: Context)
    : RecyclerView.Adapter<HeroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HeroViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val hero: Hero = list[position]
        holder.bind(hero)
    }

    override fun getItemCount(): Int = list.size

}