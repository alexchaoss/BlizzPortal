package com.BlizzardArmory.ui.overwatch

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.overwatch.topheroes.TopHero
import java.util.*

class OWProgressAdapter(private val list: ArrayList<TopHero>, private val context: Context, private val sortedBy: String)
    : RecyclerView.Adapter<OWProgressViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OWProgressViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return OWProgressViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: OWProgressViewHolder, position: Int) {
        val hero: TopHero = list[position]
        holder.bind(hero, sortedBy, list[0])
    }

    override fun getItemCount(): Int = list.size

}