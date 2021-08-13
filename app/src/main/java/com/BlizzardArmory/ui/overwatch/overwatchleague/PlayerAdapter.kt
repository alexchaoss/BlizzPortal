package com.BlizzardArmory.ui.overwatch.overwatchleague

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.overwatch.statslab.Player

class PlayerAdapter(private val list: List<Player>, private val context: Context)
    : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlayerViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player: Player = list[position]
        holder.bind(player)
    }

    override fun getItemCount(): Int = list.size

}