package com.BlizzardArmory.ui.warcraft.character.raids

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.encounters.Modes

class ModeAdapter(private val list: List<Modes>, private val context: Context)
    : RecyclerView.Adapter<ModeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ModeViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: ModeViewHolder, position: Int) {
        val mode: Modes = list[position]
        holder.bind(mode)
    }

    override fun getItemCount(): Int = list.size

}