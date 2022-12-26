package com.BlizzardArmory.ui.warcraft.character.reputations

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.reputations.characterreputations.Reputations

class ReputationsViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_rep_list, parent, false)) {

    private var progressBar: ProgressBar? = null
    private var repTier: TextView? = null
    private var repName: TextView? = null
    private var progressCount: TextView? = null
    private var repLayout: LinearLayout? = null
    private val renownTierRegex = """\b(renown|tier)\b""".toRegex(RegexOption.IGNORE_CASE)

    init {
        progressBar = itemView.findViewById(R.id.progressbar_rep)
        repTier = itemView.findViewById(R.id.rep_tier)
        repName = itemView.findViewById(R.id.rep_name)
        progressCount = itemView.findViewById(R.id.progress_rep)
        repLayout = itemView.findViewById(R.id.rep_layout)
    }

    fun bind(reputations: Reputations) {
        when (reputations.standing.tier) {
            7 -> repTier?.setTextColor(Color.parseColor("#28a586"))
            4, 5, 6 -> repTier?.setTextColor(Color.parseColor("#0f9601"))
            3 -> repTier?.setTextColor(Color.parseColor("#edba03"))
            1, 2 -> repTier?.setTextColor(Color.parseColor("#cc3609"))
            0 -> repTier?.setTextColor(Color.parseColor("#d90e03"))
        }
        Log.i("TEST REGEX STANDING", reputations.standing.name + renownTierRegex.containsMatchIn(reputations.standing.name).toString())
        if (renownTierRegex.containsMatchIn(reputations.standing.name)) {
            repTier?.setTextColor(Color.parseColor("#01b2f1"))
        }
        setTextViewsText(reputations)
        setBarColor(reputations)
    }

    private fun setTextViewsText(reputations: Reputations) {
        if (reputations.standing.tier >= 7) {
            progressBar?.max = 1000
            progressBar?.progress = 1000
            progressCount?.text = ""
        } else {
            progressBar?.max = reputations.standing.max
            progressBar?.progress = reputations.standing.value
            progressCount?.text = reputations.standing.value.toString() + "/" + reputations.standing.max.toString()
        }

        repName?.text = reputations.faction.name
        repTier?.text = reputations.standing.name
    }

    private fun setBarColor(reputations: Reputations) {
        when (reputations.standing.tier) {
            7 -> progressBar?.progressDrawable = ContextCompat.getDrawable(context, R.drawable.rep_progress_teal)
            4, 5, 6 -> progressBar?.progressDrawable = ContextCompat.getDrawable(context, R.drawable.rep_progress_green)
            3 -> progressBar?.progressDrawable = ContextCompat.getDrawable(context, R.drawable.rep_progress_yellow)
            1, 2 -> progressBar?.progressDrawable = ContextCompat.getDrawable(context, R.drawable.rep_progress_orange)
            0 -> progressBar?.progressDrawable = ContextCompat.getDrawable(context, R.drawable.rep_progress_red)
        }
        if (renownTierRegex.containsMatchIn(reputations.standing.name)) {
            progressBar?.progressDrawable = ContextCompat.getDrawable(context, R.drawable.rep_progress_blue)
        }
    }
}