package com.BlizzardArmory.ui.warcraft.mythicplusleaderboards

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard.KeystoneAffixes

class AffixesAdapter(private val list: List<KeystoneAffixes>, private val context: Context)
    : RecyclerView.Adapter<AffixesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AffixesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AffixesViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: AffixesViewHolder, position: Int) {
        val affixes: KeystoneAffixes = list[position]
        holder.bind(affixes)
    }

    override fun getItemCount(): Int = list.size

}