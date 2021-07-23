package com.BlizzardArmory.ui.warcraft.character.covenant

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.covenant.character.soulbind.Traits
import com.BlizzardArmory.model.warcraft.covenant.techtalent.TechTalent

class SoulbindAdapter(private val list: Map<Int, List<TechTalent>>, private val traits: List<Traits>, private val context: Context)
    : RecyclerView.Adapter<SoulbindViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoulbindViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SoulbindViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: SoulbindViewHolder, position: Int) {
        val talents: List<TechTalent> = list[position]!!
        var trait: Traits? = null

        if (traits.size >= position) {
            trait = traits.find { it.tier == position }
        }

        holder.bind(talents, trait)
    }

    override fun getItemCount(): Int = list.size

}