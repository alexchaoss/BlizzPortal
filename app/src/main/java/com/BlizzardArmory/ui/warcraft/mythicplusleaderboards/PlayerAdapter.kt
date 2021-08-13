package com.BlizzardArmory.ui.warcraft.mythicplusleaderboards

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard.Members
import com.BlizzardArmory.model.warcraft.specialization.Specialization

class PlayerAdapter(private val list: List<Members>, private val context: Context, private val specialization: List<Specialization>, private val region: String)
    : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlayerViewHolder(inflater, parent, context, specialization)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val member: Members = list[position]
        holder.bind(member, region)
    }

    override fun getItemCount(): Int = list.size

}