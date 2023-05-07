package com.BlizzardArmory.ui.warcraft.character.raids

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.encounters.Modes

class ModeViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_encounter_mode_list, parent, false)) {


    private var progressBar: ProgressBar? = null
    private var name: TextView? = null
    private var progress: TextView? = null


    init {
        name = itemView.findViewById(R.id.name)
        progress = itemView.findViewById(R.id.progress_count)
        progressBar = itemView.findViewById(R.id.progressbar)
    }

    fun bind(mode: Modes) {
        name?.text = mode.difficulty.name

        var maxCount = 0
        var totalCount = 0

        if (mode.progress.total_count > totalCount) {
            totalCount = mode.progress.total_count
        }
        val totalCountString = "0/$totalCount"
        progress?.text = totalCountString


        if (mode.progress.completed_count > maxCount) {
            maxCount = mode.progress.completed_count
        }

        val count: String = maxCount.toString() + "/" + mode.progress.total_count.toString()

        progress?.text = count
        progressBar?.max = mode.progress.total_count
        progressBar?.progress = maxCount
        setProgressColor(mode, progressBar, maxCount)

    }

    private fun setProgressColor(mode: Modes, progressBar: ProgressBar?, maxCount: Int) {
        val percent = (maxCount * 100) / mode.progress.total_count
        when {
            percent < 40 -> {
                progressBar?.progressDrawable = ContextCompat.getDrawable(context, R.drawable.progress_bar_raid_1)
            }
            percent < 100 -> {
                progressBar?.progressDrawable = ContextCompat.getDrawable(context, R.drawable.progress_bar_raid_half)
            }
            percent == 100 -> {
                progressBar?.progressDrawable = ContextCompat.getDrawable(context, R.drawable.progress_bar_raid_full)
            }
        }
    }

}