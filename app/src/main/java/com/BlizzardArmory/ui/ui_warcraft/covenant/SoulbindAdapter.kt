package com.BlizzardArmory.ui.ui_warcraft.covenant

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.covenant.character.soulbind.Traits
import com.BlizzardArmory.model.warcraft.covenant.techtalent.TechTalent
import com.BlizzardArmory.model.warcraft.covenant.techtalenttree.Talents
import com.BlizzardArmory.model.warcraft.pvp.summary.PvpMapStatistics

class SoulbindAdapter(private val list: List<Traits>, private val talents: List<Talents>, private val context: Context)
    : RecyclerView.Adapter<SoulbindViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoulbindViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SoulbindViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: SoulbindViewHolder, position: Int) {
        val trait: Traits = list[position]
        holder.bind(trait, talents)
    }

    override fun getItemCount(): Int = list.size

}