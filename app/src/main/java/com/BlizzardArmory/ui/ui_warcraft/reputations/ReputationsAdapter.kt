package com.BlizzardArmory.ui.ui_warcraft.reputations

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.warcraft.reputations.characterreputations.Reputations

class ReputationsAdapter(private val list: List<Reputations>, private val context: Context)
    : RecyclerView.Adapter<ReputationsViewHolder>() {

    var fullRepList = ArrayList<Reputations>()

    init {
        fullRepList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReputationsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ReputationsViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: ReputationsViewHolder, position: Int) {
        val reputations: Reputations = fullRepList[position]
        holder.bind(reputations, position)
    }

    override fun getItemCount(): Int = fullRepList.size

    fun filter(constraint: String) {
        fullRepList.clear()
        if (constraint.toLowerCase().isEmpty()) {
            fullRepList.addAll(list)
        } else {
            for (rep in list) {
                if (rep.faction.name.toLowerCase().trim().contains(constraint.toLowerCase().trim())) {
                    fullRepList.add(rep)
                }
            }
        }
        Log.i("FILTER", list.toString())
        notifyDataSetChanged()
    }


}