package com.BlizzardArmory.ui.warcraft.character.raids

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.encounters.Instances

class RaidsAdapter(private val list: List<Instances>, private val raidLevel: String, private val context: Context)
    : RecyclerView.Adapter<RaidsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaidsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RaidsViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: RaidsViewHolder, position: Int) {
        val instance: Instances = list[position]
        holder.bind(instance, raidLevel)
    }

    override fun getItemCount(): Int = list.size

}