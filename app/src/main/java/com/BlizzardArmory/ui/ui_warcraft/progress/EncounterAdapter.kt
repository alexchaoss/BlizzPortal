package com.BlizzardArmory.ui.ui_warcraft.progress

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.encounters.Expansion
import com.BlizzardArmory.model.warcraft.encounters.Instances
import java.util.*

class EncounterAdapter(private val list: List<Instances>, private val raidLevel: String, private val context: Context, private val expansion: Expansion)
    : RecyclerView.Adapter<EncounterViewHolder>() {

    private var fullProgressList = ArrayList<Instances>()

    init {
        fullProgressList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EncounterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EncounterViewHolder(inflater, parent, context, expansion)
    }

    override fun onBindViewHolder(holder: EncounterViewHolder, position: Int) {
        val instance: Instances = fullProgressList[position]
        holder.bind(instance, raidLevel)
    }

    override fun getItemCount(): Int = fullProgressList.size

    fun filter(constraint: String) {
        fullProgressList.clear()
        if (constraint.toLowerCase(Locale.ROOT).isEmpty()) {
            fullProgressList.addAll(list)
        } else {
            for (instance in list) {
                if (instance.instance.name.toLowerCase(Locale.ROOT).trim().contains(constraint.toLowerCase(Locale.ROOT).trim())) {
                    fullProgressList.add(instance)
                }
            }
        }
        Log.i("FILTER", fullProgressList.toString())
        notifyDataSetChanged()
    }

}