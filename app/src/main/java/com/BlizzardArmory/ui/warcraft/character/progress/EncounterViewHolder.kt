package com.BlizzardArmory.ui.warcraft.character.progress

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.encounters.Instances
import com.BlizzardArmory.network.NetworkUtils
import com.bumptech.glide.Glide

class EncounterViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
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
        Glide.with(itemView.context).load(NetworkUtils.getWoWAsset(findBanner(instances)))
            .into(banner!!)
        raidName?.text = instances.instance.name
        raidLevel?.text = level
        recyclerView?.apply {
            adapter = ModeAdapter(instances.modes, context)
        }
    }

    private fun findBanner(instances: Instances): String {
        when (instances.instance.id) {
            1193L -> return "sanctum_of_domination_small"
            1190L -> return "castle_nathria_small"
            1031L -> return "uldir_small"
            1176L -> return "battle_of_dazaralor_small"
            1177L -> return "crucible_of_storms_small"
            1179L -> return "the_eternal_palace_small"
            1180L -> return "nyalotha_the_waking_city_small"
            768L -> return "the_emerald_nightmare_small"
            861L -> return "trial_of_valor_small"
            786L -> return "the_nighthold_small"
            875L -> return "tomb_of_sargeras_small"
            946L -> return "antorus_the_burning_throne_small"
            477L -> return "highmaul_small"
            457L -> return "blackrock_foundry_small"
            669L -> return "hellfire_citadel_small"
            317L -> return "mogushan_vaults_small"
            330L -> return "heart_of_fear_small"
            320L -> return "terrace_of_endless_spring_small"
            362L -> return "throne_of_thunder_small"
            369L -> return "siege_of_orgrimmar_small"
            75L -> return "baradin_hold_small"
            73L -> return "blackwing_descent_small"
            72L -> return "the_bastion_of_twilight_small"
            74L -> return "throne_of_the_four_winds_small"
            78L -> return "firelands_small"
            187L -> return "dragon_soul_small"
            753L -> return "vault_of_archavon_small"
            754L -> return "naxxramas_small"
            755L -> return "the_obsidian_sanctum_small"
            756L -> return "the_eye_of_eternity_small"
            759L -> return "ulduar_small"
            757L -> return "trial_of_the_crusader_small"
            760L -> return "onyxias_lair_small"
            758L -> return "icecrown_citadel_small"
            761L -> return "the_ruby_sanctum_small"
            745L -> return "karazhan_small"
            746L -> return "gruuls_lair_small"
            747L -> return "magtheridons_lair_small"
            748L -> return "serpentshrine_cavern_small"
            749L -> return "the_eye_small"
            750L -> return "the_battle_for_mount_hyjal_small"
            751L -> return "black_temple_small"
            752L -> return "sunwell_plateau_small"
            741L -> return "molten_core_small"
            742L -> return "blackwing_lair_small"
            743L -> return "ruins_of_ahnqiraj_small"
            744L -> return "temple_of_ahnqiraj_small"
            else -> return ""
        }
    }

}