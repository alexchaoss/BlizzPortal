package com.BlizzardArmory.ui.warcraft.guild.activity

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.guild.activity.Activities

class ActivitiesAdapter(private val activities: List<Activities>/*, private val roster: Roster*/, private val context: Context) :
    RecyclerView.Adapter<ActivitiesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitiesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ActivitiesViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: ActivitiesViewHolder, position: Int) {
        if (position % 2 != 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#15FFFFFF"))
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#00000000"))
        }
        val activity = activities[position]
        holder.bind(activity/*, roster*/)
    }

    override fun getItemCount(): Int = activities.size

}