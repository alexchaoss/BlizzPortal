package com.BlizzardArmory.ui.ui_warcraft.reputations

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.warcraft.reputations.characterreputations.Reputations

class ReputationsViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_rep_list, parent, false)) {

    var progressBar: ProgressBar? = null
    var repTier: TextView? = null
    var repName: TextView? = null
    var progressCount: TextView? = null
    var repLayout: LinearLayout? = null

    init {
        progressBar = itemView.findViewById(R.id.progressbar_rep)
        repTier = itemView.findViewById(R.id.rep_tier)
        repName = itemView.findViewById(R.id.rep_name)
        progressCount = itemView.findViewById(R.id.progress_rep)
        repLayout = itemView.findViewById(R.id.rep_layout)
    }

    fun bind(reputations: Reputations, position: Int) {
        if (position % 2 == 1) {
            repLayout?.setBackgroundResource(R.drawable.bgbaground)
        }
        when (reputations.standing.name) {
            "Rank 8",
            "Timelord",
            "Best Friend",
            "Exalted" -> repTier?.setTextColor(Color.parseColor("#28a586"))
            "Whelping",
            "Temporal Trainee",
            "Timehopper",
            "Chrono-Friend",
            "Bronze Ally",
            "Epoch-Mender",
            "Buddy",
            "Good Friend",
            "Rank 7",
            "Rank 6",
            "Rank 5",
            "Rank 4",
            "Rank 3",
            "Rank 2",
            "Rank 1",
            "Revered",
            "Honored",
            "Friendly" -> repTier?.setTextColor(Color.parseColor("#0f9601"))
            "Neutral" -> repTier?.setTextColor(Color.parseColor("#edba03"))
            "Acquaintance",
            "Stranger",
            "Unfriendly" -> repTier?.setTextColor(Color.parseColor("#cc3609"))
            "Hostile",
            "Hated" -> repTier?.setTextColor(Color.parseColor("#d90e03"))
        }
        setTextViewsText(reputations)
        setBarColor(reputations)
    }

    private fun setTextViewsText(reputations: Reputations) {
        if (reputations.standing.name == "Exalted" || reputations.standing.name == "Best Friend" || reputations.standing.name == "Rank 8" || reputations.standing.name == "Timelord") {
            progressBar?.max = 1000
            progressBar?.progress = 1000
        } else {
            progressBar?.max = reputations.standing.max
            progressBar?.progress = reputations.standing.value
            progressCount?.text = reputations.standing.value.toString() + "/" + reputations.standing.max.toString()
        }

        repName?.text = reputations.faction.name
        repTier?.text = reputations.standing.name
    }

    private fun setBarColor(reputations: Reputations) {
        when (reputations.standing.name) {
            "Rank 8",
            "Timelord",
            "Best Friend",
            "Exalted" -> progressBar?.progressDrawable = context.getDrawable(R.drawable.rep_progress_teal)
            "Buddy",
            "Good Friend",
            "Whelping",
            "Temporal Trainee",
            "Timehopper",
            "Chrono-Friend",
            "Bronze Ally",
            "Epoch-Mender",
            "Rank 7",
            "Rank 6",
            "Rank 5",
            "Rank 4",
            "Rank 3",
            "Rank 2",
            "Rank 1",
            "Revered",
            "Honored",
            "Friendly" -> progressBar?.progressDrawable = context.getDrawable(R.drawable.rep_progress_green)
            "Stranger",
            "Unfriendly" -> progressBar?.progressDrawable = context.getDrawable(R.drawable.rep_progress_orange)
            "Acquaintance",
            "Neutral" -> progressBar?.progressDrawable = context.getDrawable(R.drawable.rep_progress_yellow)
            "Hostile",
            "Hated" -> progressBar?.progressDrawable = context.getDrawable(R.drawable.rep_progress_red)
        }
    }
}