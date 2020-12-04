package com.BlizzardArmory.ui.ui_warcraft.reputations

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.reputations.characterreputations.Reputations

class ReputationsAdapter(private val list: List<Reputations>, private val context: Context)
    : RecyclerView.Adapter<ReputationsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReputationsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ReputationsViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: ReputationsViewHolder, position: Int) {
        if (position % 2 == 1) {
            holder.itemView.setBackgroundResource(R.drawable.bgbaground)
        } else {
            holder.itemView.background = null
        }
        val reputations: Reputations = list[position]
        holder.bind(reputations)
    }

    override fun getItemCount(): Int = list.size

}