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
import com.BlizzardArmory.model.warcraft.encounters.Expansion
import com.BlizzardArmory.model.warcraft.encounters.Instances
import com.BlizzardArmory.model.warcraft.encounters.Modes

class EncounterViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context, private val expansion: Expansion) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_encounter_list, parent, false)) {

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
        when (expansion.id) {
            70L -> {
                layoutNormal?.visibility = View.VISIBLE
                layoutLFR?.visibility = View.GONE
                layoutHeroic?.visibility = View.GONE
                layoutMythic?.visibility = View.GONE
            }
            72L -> {
                layoutNormal?.visibility = View.VISIBLE
                layoutLFR?.visibility = View.GONE
                layoutHeroic?.visibility = View.GONE
                layoutMythic?.visibility = View.GONE
            }
            73L -> {
                layoutHeroic?.visibility = View.VISIBLE
                layoutNormal?.visibility = View.VISIBLE
                layoutLFR?.visibility = View.GONE
                layoutMythic?.visibility = View.GONE
            }
            74L -> {
                layoutHeroic?.visibility = View.VISIBLE
                layoutLFR?.visibility = View.VISIBLE
                layoutNormal?.visibility = View.VISIBLE
                layoutMythic?.visibility = View.GONE
            }
            68L -> {
                layoutNormal?.visibility = View.VISIBLE
                layoutLFR?.visibility = View.GONE
                layoutHeroic?.visibility = View.GONE
                layoutMythic?.visibility = View.GONE
            }
        }
    }

    private fun setProgressBarCount(instances: Instances) {
        when (instances.instance.id) {
            284L -> layoutHeroic?.visibility = View.VISIBLE
            758L -> layoutHeroic?.visibility = View.VISIBLE
            761L -> layoutHeroic?.visibility = View.VISIBLE
            187L -> layoutLFR?.visibility = View.VISIBLE
            369L -> layoutMythic?.visibility = View.VISIBLE
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
            when (mode.difficulty.type) {
                "LFR" -> {
                    progressCountLFR?.text = count
                    progressBarLFR?.max = mode.progress.total_count
                    progressBarLFR?.progress = maxCount
                    setProgressColor(mode, progressBarLFR, maxCount)
                }
                "NORMAL" -> {
                    progressCountNormal?.text = count
                    progressBarNormal?.max = mode.progress.total_count
                    progressBarNormal?.progress = maxCount
                    setProgressColor(mode, progressBarNormal, maxCount)
                }
                "LEGACY_10_MAN" -> {
                    progressCountNormal?.text = count
                    progressBarNormal?.max = mode.progress.total_count
                    progressBarNormal?.progress = maxCount
                    setProgressColor(mode, progressBarNormal, maxCount)

                }
                "LEGACY_25_MAN" -> {
                    progressCountNormal?.text = count
                    progressBarNormal?.max = mode.progress.total_count
                    progressBarNormal?.progress = maxCount
                    setProgressColor(mode, progressBarNormal, maxCount)

                }
                "LEGACY_10_MAN_HEROIC" -> {
                    progressCountHeroic?.text = count
                    progressBarHeroic?.max = mode.progress.total_count
                    progressBarHeroic?.progress = maxCount
                    setProgressColor(mode, progressBarHeroic, maxCount)
                }
                "LEGACY_25_MAN_HEROIC" -> {
                    progressCountHeroic?.text = count
                    progressBarHeroic?.max = mode.progress.total_count
                    progressBarHeroic?.progress = maxCount
                    setProgressColor(mode, progressBarHeroic, maxCount)
                }
                "HEROIC" -> {
                    progressCountHeroic?.text = count
                    progressBarHeroic?.max = mode.progress.total_count
                    progressBarHeroic?.progress = maxCount
                    setProgressColor(mode, progressBarHeroic, maxCount)
                }
                "MYTHIC" -> {
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
        when (instances.instance.id) {
            1031L -> return R.drawable.uldir_small
            1176L -> return R.drawable.battle_of_dazaralor_small
            1177L -> return R.drawable.crucible_of_storms_small
            1179L -> return R.drawable.the_eternal_palace_small
            1180L -> return R.drawable.nyalotha_the_waking_city_small
            768L -> return R.drawable.the_emerald_nightmare_small
            861L -> return R.drawable.trial_of_valor_small
            786L -> return R.drawable.the_nighthold_small
            875L -> return R.drawable.tomb_of_sargeras_small
            946L -> return R.drawable.antorus_the_burning_throne_small
            477L -> return R.drawable.highmaul_small
            457L -> return R.drawable.blackrock_foundry_small
            669L -> return R.drawable.hellfire_citadel_small
            317L -> return R.drawable.mogushan_vaults_small
            330L -> return R.drawable.heart_of_fear_small
            320L -> return R.drawable.terrace_of_endless_spring_small
            362L -> return R.drawable.throne_of_thunder_small
            369L -> return R.drawable.siege_of_orgrimmar_small
            75L -> return R.drawable.baradin_hold_small
            73L -> return R.drawable.blackwing_descent_small
            72L -> return R.drawable.the_bastion_of_twilight_small
            74L -> return R.drawable.throne_of_the_four_winds_small
            78L -> return R.drawable.firelands_small
            187L -> return R.drawable.dragon_soul_small
            753L -> return R.drawable.vault_of_archavon_small
            754L -> return R.drawable.naxxramas_small
            755L -> return R.drawable.the_obsidian_sanctum_small
            756L -> return R.drawable.the_eye_of_eternity_small
            759L -> return R.drawable.ulduar_small
            757L -> return R.drawable.trial_of_the_crusader_small
            760L -> return R.drawable.onyxias_lair_small
            758L -> return R.drawable.icecrown_citadel_small
            761L -> return R.drawable.the_ruby_sanctum_small
            745L -> return R.drawable.karazhan_small
            746L -> return R.drawable.gruuls_lair_small
            747L -> return R.drawable.magtheridons_lair_small
            748L -> return R.drawable.serpentshrine_cavern_small
            749L -> return R.drawable.the_eye_small
            750L -> return R.drawable.the_battle_for_mount_hyjal_small
            751L -> return R.drawable.black_temple_small
            752L -> return R.drawable.sunwell_plateau_small
            741L -> return R.drawable.molten_core_small
            742L -> return R.drawable.blackwing_lair_small
            743L -> return R.drawable.ruins_of_ahnqiraj_small
            744L -> return R.drawable.temple_of_ahnqiraj_small
            else -> return 0
        }
    }

}