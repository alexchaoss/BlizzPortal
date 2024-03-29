package com.BlizzardArmory.ui.overwatch.overwatchleague

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.overwatch.statslab.Match


class MatchesViewHolder(inflater: LayoutInflater, val parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.overwatch_league_list, parent, false)) {

    var team1Card: CardView? = null
    var team2Card: CardView? = null
    var date: TextView? = null
    var type: TextView? = null
    var map: TextView? = null
    var team1: TextView? = null
    var team2: TextView? = null
    var team1Recycler: RecyclerView? = null
    var team2Recycler: RecyclerView? = null

    init {
        date = itemView.findViewById(R.id.date)
        type = itemView.findViewById(R.id.type)
        map = itemView.findViewById(R.id.map)
        team1Card = itemView.findViewById(R.id.team1)
        team2Card = itemView.findViewById(R.id.team2)
        team1 = team1Card?.findViewById(R.id.name)
        team2 = team2Card?.findViewById(R.id.name)
        team1Recycler = team1Card?.findViewById(R.id.recycler)
        team2Recycler = team2Card?.findViewById(R.id.recycler)
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
    }
}
