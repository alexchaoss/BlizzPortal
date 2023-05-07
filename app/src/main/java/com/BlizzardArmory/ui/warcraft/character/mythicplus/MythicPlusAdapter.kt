package com.BlizzardArmory.ui.warcraft.character.mythicplus

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.mythicplusprofile.BestRuns

class MythicPlusAdapter(private val list: List<BestRuns>, private val context: Context)
    : RecyclerView.Adapter<MythicPlusViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MythicPlusViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MythicPlusViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: MythicPlusViewHolder, position: Int) {
        val bestRuns: BestRuns = list[position]
        holder.bind(bestRuns)
    }

    override fun getItemCount(): Int = list.size

}