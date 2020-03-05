package com.BlizzardArmory.ui.ui_warcraft

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.warcraft.encounters.Instances

class EncounterViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.encounter_list, parent, false)){

    private var raidName: TextView? = null
    private var raidLevel: TextView? = null
    private var banner: ImageView? = null


    init {
        raidName = itemView.findViewById(R.id.raid_name)
        raidLevel = itemView.findViewById(R.id.raid_level)
        banner = itemView.findViewById(R.id.raid_image)
    }

    fun bind(instances: Instances, level: String) {
        raidName?.text = instances.instance.name
        raidLevel?.text = level
    }

}