package com.BlizzardArmory.ui.starcraft.leaderboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.starcraft.leaderboard.LadderMembers

class PlayerAdapter(private val list: List<LadderMembers>, private val context: Context)
    : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlayerViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val member: LadderMembers = list[position]
        holder.bind(member)
    }

    override fun getItemCount(): Int = list.size

}