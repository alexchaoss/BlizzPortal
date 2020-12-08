package com.BlizzardArmory.ui.warcraft.progress

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.encounters.Instances

class EncounterAdapter(private val list: List<Instances>, private val raidLevel: String, private val context: Context)
    : RecyclerView.Adapter<EncounterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EncounterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EncounterViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: EncounterViewHolder, position: Int) {
        val instance: Instances = list[position]
        holder.bind(instance, raidLevel)
    }

    override fun getItemCount(): Int = list.size

}