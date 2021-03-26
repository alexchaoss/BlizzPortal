package com.BlizzardArmory.ui.warcraft.character.armory

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.talents.Talent
import com.BlizzardArmory.model.warcraft.talents.TalentsIcons

class TalentAdapter(private val list: List<Talent>, private val talentsIcons: List<TalentsIcons>, private val context: Context)
    : RecyclerView.Adapter<TalentViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TalentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TalentViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: TalentViewHolder, position: Int) {
        val talent: Talent = list[position]
        holder.bind(talent, talentsIcons)
    }

    override fun getItemCount(): Int = list.size

}