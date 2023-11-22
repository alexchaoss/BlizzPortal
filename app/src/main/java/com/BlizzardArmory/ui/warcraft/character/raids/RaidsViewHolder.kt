package com.BlizzardArmory.ui.warcraft.character.raids

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.encounters.Instances
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.util.SlugName
import com.bumptech.glide.Glide

class RaidsViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_encounter_list, parent, false)) {

    private var raidName: TextView? = null
    private var raidLevel: TextView? = null
    private var banner: ImageView? = null
    private var recyclerView: RecyclerView? = null


    init {
        raidName = itemView.findViewById(R.id.raid_name)
        raidLevel = itemView.findViewById(R.id.raid_level)
        banner = itemView.findViewById(R.id.raid_image)
        recyclerView = itemView.findViewById(R.id.modes_recycler)
    }

    fun bind(instances: Instances, level: String) {

        Glide.with(itemView.context).load(NetworkUtils.getWoWDungeonAsset(parseDungeonName(instances.instance.name))).into(banner!!)
        raidName?.text = instances.instance.name
        raidLevel?.text = level
        recyclerView?.apply {
            adapter = ModeAdapter(instances.modes, context)
        }
    }

    private fun parseDungeonName(name: String): String {
        return "${SlugName.toSlug(name).lowercase()}-small"
    }
}