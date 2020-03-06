package com.BlizzardArmory.ui.ui_warcraft.progress

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.warcraft.encounters.Expansion
import com.BlizzardArmory.warcraft.encounters.Instances
import com.BlizzardArmory.warcraft.encounters.Modes

class EncounterViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context, private val expansion: Expansion) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.encounter_list, parent, false)) {

    private var raidName: TextView? = null
    private var raidLevel: TextView? = null
    private var banner: ImageView? = null

    private var layoutLFR: LinearLayout? = null
    private var layoutNormal: LinearLayout? = null
    private var layoutHeroic: LinearLayout? = null
    private var layoutMythic: LinearLayout? = null

    private var progressBarLFR: ProgressBar? = null
    private var progressBarNormal: ProgressBar? = null
    private var progressBarHeroic: ProgressBar? = null
    private var progressBarMythic: ProgressBar? = null

    private var progressCountLFR: TextView? = null
    private var progressCountNormal: TextView? = null
    private var progressCountHeroic: TextView? = null
    private var progressCountMythic: TextView? = null


    init {
        raidName = itemView.findViewById(R.id.raid_name)
        raidLevel = itemView.findViewById(R.id.raid_level)
        banner = itemView.findViewById(R.id.raid_image)
        progressBarLFR = itemView.findViewById(R.id.progressBarLFR)
        progressBarNormal = itemView.findViewById(R.id.progressBarNormal)
        progressBarHeroic = itemView.findViewById(R.id.progressBarHeroic)
        progressBarMythic = itemView.findViewById(R.id.progressBarMythic)
        layoutLFR = itemView.findViewById(R.id.lfr)
        layoutNormal = itemView.findViewById(R.id.normal)
        layoutHeroic = itemView.findViewById(R.id.heroic)
        layoutMythic = itemView.findViewById(R.id.mythic)
        progressCountLFR = itemView.findViewById(R.id.progress_count_lfr)
        progressCountNormal = itemView.findViewById(R.id.progress_count_normal)
        progressCountHeroic = itemView.findViewById(R.id.progress_count_heroic)
        progressCountMythic = itemView.findViewById(R.id.progress_count_mythic)
    }

    fun bind(instances: Instances, level: String) {
        banner?.setImageResource(findBanner(instances))
        raidName?.text = instances.instance.name
        raidLevel?.text = level
        setVisibilityBar()
        setProgressBarCount(instances)
    }

    private fun setVisibilityBar() {
        when (expansion.name) {
            "Burning Crusade" -> {
                layoutNormal?.visibility = View.VISIBLE
                layoutLFR?.visibility = View.GONE
                layoutHeroic?.visibility = View.GONE
                layoutMythic?.visibility = View.GONE
            }
            "Wrath of the Lich King" -> {
                layoutNormal?.visibility = View.VISIBLE
                layoutLFR?.visibility = View.GONE
                layoutHeroic?.visibility = View.GONE
                layoutMythic?.visibility = View.GONE
            }
            "Cataclysm" -> {
                layoutHeroic?.visibility = View.VISIBLE
                layoutNormal?.visibility = View.VISIBLE
                layoutLFR?.visibility = View.GONE
                layoutMythic?.visibility = View.GONE
            }
            "Mist of Pandaria" -> {
                layoutHeroic?.visibility = View.VISIBLE
                layoutLFR?.visibility = View.VISIBLE
                layoutNormal?.visibility = View.VISIBLE
                layoutMythic?.visibility = View.GONE
            }
            "Classic" -> {
                layoutNormal?.visibility = View.VISIBLE
                layoutLFR?.visibility = View.GONE
                layoutHeroic?.visibility = View.GONE
                layoutMythic?.visibility = View.GONE
            }
        }
    }

    private fun setProgressBarCount(instances: Instances) {
        when (instances.instance.name) {
            "Trial of the Crusader" -> layoutHeroic?.visibility = View.VISIBLE
            "Icecrown Citadel" -> layoutHeroic?.visibility = View.VISIBLE
            "The Ruby Sanctum" -> layoutHeroic?.visibility = View.VISIBLE
            "Dragon Soul" -> layoutLFR?.visibility = View.VISIBLE
            "Siege of Orgrimmar" -> layoutMythic?.visibility = View.VISIBLE
        }
        var maxCount = 0
        var totalCount = 0

        for (mode in instances.modes) {
            if (mode.progress.total_count > totalCount) {
                totalCount = mode.progress.total_count
            }
        }
        val totalCountString: String? = "0/$totalCount"
        progressCountLFR?.text = totalCountString
        progressCountNormal?.text = totalCountString
        progressCountHeroic?.text = totalCountString
        progressCountMythic?.text = totalCountString

        for (mode in instances.modes) {
            if (mode.progress.completed_count > maxCount) {
                maxCount = mode.progress.completed_count
            }

            val count: String? = maxCount.toString() + "/" + mode.progress.total_count.toString()
            when (mode.difficulty.name) {
                "Raid Finder" -> {
                    progressCountLFR?.text = count
                    progressBarLFR?.max = mode.progress.total_count
                    progressBarLFR?.progress = maxCount
                    setProgressColor(mode, progressBarLFR, maxCount)
                }
                "Normal" -> {
                    progressCountNormal?.text = count
                    progressBarNormal?.max = mode.progress.total_count
                    progressBarNormal?.progress = maxCount
                    setProgressColor(mode, progressBarNormal, maxCount)
                }
                "10 Player" -> {
                    progressCountNormal?.text = count
                    progressBarNormal?.max = mode.progress.total_count
                    progressBarNormal?.progress = maxCount
                    setProgressColor(mode, progressBarNormal, maxCount)

                }
                "25 Player" -> {
                    progressCountNormal?.text = count
                    progressBarNormal?.max = mode.progress.total_count
                    progressBarNormal?.progress = maxCount
                    setProgressColor(mode, progressBarNormal, maxCount)

                }
                "10 Player (Heroic)" -> {
                    progressCountHeroic?.text = count
                    progressBarHeroic?.max = mode.progress.total_count
                    progressBarHeroic?.progress = maxCount
                    setProgressColor(mode, progressBarHeroic, maxCount)
                }
                "25 Player (Heroic)" -> {
                    progressCountHeroic?.text = count
                    progressBarHeroic?.max = mode.progress.total_count
                    progressBarHeroic?.progress = maxCount
                    setProgressColor(mode, progressBarHeroic, maxCount)
                }
                "Heroic" -> {
                    progressCountHeroic?.text = count
                    progressBarHeroic?.max = mode.progress.total_count
                    progressBarHeroic?.progress = maxCount
                    setProgressColor(mode, progressBarHeroic, maxCount)
                }
                "Mythic" -> {
                    progressCountMythic?.text = count
                    progressBarMythic?.max = mode.progress.total_count
                    progressBarMythic?.progress = maxCount
                    setProgressColor(mode, progressBarMythic, maxCount)
                }
            }
        }
    }

    private fun setProgressColor(mode: Modes, progressBar: ProgressBar?, maxCount: Int) {
        val percent = (maxCount * 100) / mode.progress.total_count
        Log.i("PROGRESS", "percent: " + percent + " count: " + maxCount + " total: " + mode.progress.total_count)
        if (percent < 40) {
            progressBar?.progressDrawable = context.getDrawable(R.drawable.progress_bar_raid_1)
        } else if (percent < 100) {
            progressBar?.progressDrawable = context.getDrawable(R.drawable.progress_bar_raid_half)
        } else if (percent == 100) {
            progressBar?.progressDrawable = context.getDrawable(R.drawable.progress_bar_raid_full)
        }
    }

    private fun findBanner(instances: Instances): Int {
        when (instances.instance.name) {
            "Uldir" -> return R.drawable.uldir_small
            "Battle of Dazar'alor" -> return R.drawable.battle_of_dazaralor_small
            "Crucible of Storms" -> return R.drawable.crucible_of_storms_small
            "The Eternal Palace" -> return R.drawable.the_eternal_palace_small
            "Ny'alotha, the Waking City" -> return R.drawable.nyalotha_the_waking_city_small
            "The Emerald Nightmare" -> return R.drawable.the_emerald_nightmare_small
            "Trial of Valor" -> return R.drawable.trial_of_valor_small
            "The Nighthold" -> return R.drawable.the_nighthold_small
            "Tomb of Sargeras" -> return R.drawable.tomb_of_sargeras_small
            "Antorus, the Burning Throne" -> return R.drawable.antorus_the_burning_throne_small
            "Highmaul" -> return R.drawable.highmaul_small
            "Blackrock Foundry" -> return R.drawable.blackrock_foundry_small
            "Hellfire Citadel" -> return R.drawable.hellfire_citadel_small
            "Mogu'shan Vaults" -> return R.drawable.mogushan_vaults_small
            "Heart of Fear" -> return R.drawable.heart_of_fear_small
            "Terrace of Endless Spring" -> return R.drawable.terrace_of_endless_spring_small
            "Throne of Thunder" -> return R.drawable.throne_of_thunder_small
            "Siege of Orgrimmar" -> return R.drawable.siege_of_orgrimmar_small
            "Baradin Hold" -> return R.drawable.baradin_hold_small
            "Blackwing Descent" -> return R.drawable.blackwing_descent_small
            "The Bastion of Twilight" -> return R.drawable.the_bastion_of_twilight_small
            "Throne of the Four Winds" -> return R.drawable.throne_of_the_four_winds_small
            "Firelands" -> return R.drawable.firelands_small
            "Dragon Soul" -> return R.drawable.dragon_soul_small
            "Vault of Archavon" -> return R.drawable.vault_of_archavon_small
            "Naxxramas" -> return R.drawable.naxxramas_small
            "The Obsidian Sanctum" -> return R.drawable.the_obsidian_sanctum_small
            "The Eye of Eternity" -> return R.drawable.the_eye_of_eternity_small
            "Ulduar" -> return R.drawable.ulduar_small
            "Trial of the Crusader" -> return R.drawable.trial_of_the_crusader_small
            "Onyxia's Lair" -> return R.drawable.onyxias_lair_small
            "Icecrown Citadel" -> return R.drawable.icecrown_citadel_small
            "The Ruby Sanctum" -> return R.drawable.the_ruby_sanctum_small
            "Karazhan" -> return R.drawable.karazhan_small
            "Gruul's Lair" -> return R.drawable.gruuls_lair_small
            "Magtheridon's Lair" -> return R.drawable.magtheridons_lair_small
            "Serpentshrine Cavern" -> return R.drawable.serpentshrine_cavern_small
            "The Eye" -> return R.drawable.the_eye_small
            "The Battle for Mount Hyjal" -> return R.drawable.the_battle_for_mount_hyjal_small
            "Black Temple" -> return R.drawable.black_temple_small
            "Sunwell Plateau" -> return R.drawable.sunwell_plateau_small
            "Molten Core" -> return R.drawable.molten_core_small
            "Blackwing Lair" -> return R.drawable.blackwing_lair_small
            "Ruins of Ahn'Qiraj" -> return R.drawable.ruins_of_ahnqiraj_small
            "Temple of Ahn'Qiraj" -> return R.drawable.temple_of_ahnqiraj_small
            else -> return 0
        }
    }

}