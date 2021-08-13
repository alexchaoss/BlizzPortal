package com.BlizzardArmory.ui.overwatch.overwatchleague

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.overwatch.statslab.Match
import com.robertlevonyan.views.expandable.Expandable
import com.robertlevonyan.views.expandable.doOnExpand


class MatchesViewHolder(inflater: LayoutInflater, val parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.overwatch_league_list, parent, false)) {

    var expandable: Expandable? = null
    var date: TextView? = null
    var type: TextView? = null
    var map: TextView? = null
    var team1: TextView? = null
    var team2: TextView? = null
    var team1Recycler: RecyclerView? = null
    var team2Recycler: RecyclerView? = null

    init {
        expandable = itemView.findViewById(R.id.expandable)
        date = itemView.findViewById(R.id.date)
        type = itemView.findViewById(R.id.type)
        map = itemView.findViewById(R.id.map)
        team1 = itemView.findViewById(R.id.team1)
        team2 = itemView.findViewById(R.id.team2)
        team1Recycler = itemView.findViewById(R.id.recyclerTeam1)
        team2Recycler = itemView.findViewById(R.id.recyclerTeam2)
    }

    fun bind(match: Match) {

        date?.text = match.startTime
        type?.text = match.mapType.lowercase().replaceFirstChar { it.uppercase() }
        map?.text = match.mapName

        team1?.text = match.team1.name
        team2?.text = match.team2.name

        team1Recycler?.apply {
            adapter = PlayerAdapter(match.team1.players, context)
        }

        team2Recycler?.apply {
            adapter = PlayerAdapter(match.team2.players, context)
        }
        expandable?.doOnExpand {
            expandable?.requestLayout()
            itemView.requestLayout()
            parent.requestLayout()
        }
    }
}
