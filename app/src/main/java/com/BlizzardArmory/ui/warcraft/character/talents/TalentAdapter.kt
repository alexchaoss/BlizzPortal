package com.BlizzardArmory.ui.warcraft.character.talents

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.covenant.character.soulbind.Traits
import com.BlizzardArmory.model.warcraft.covenant.techtalent.TechTalent
import com.BlizzardArmory.model.warcraft.talents.playerspec.Loadouts
import com.BlizzardArmory.model.warcraft.talents.playerspec.SelectedClassTalents

class TalentAdapter<T>(private val list: List<T>, private val context: Context)
    : RecyclerView.Adapter<TalentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TalentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TalentViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: TalentViewHolder, position: Int) {
        val talent = list[position]
        holder.bind(talent)
    }

    override fun getItemCount(): Int = list.size
}