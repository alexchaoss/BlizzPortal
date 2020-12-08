package com.BlizzardArmory.ui.warcraft.pvp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.pvp.summary.PvpMapStatistics

class BattlegroundViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_battleground_list, parent, false)) {

    private var bgName: TextView? = null
    private var bgGames: TextView? = null
    private var bgWins: TextView? = null
    private var bgWinRate: TextView? = null
    private var layout: LinearLayout? = null

    init {
        bgName = itemView.findViewById(R.id.bgname)
        bgGames = itemView.findViewById(R.id.bggames)
        bgWins = itemView.findViewById(R.id.bgwin)
        bgWinRate = itemView.findViewById(R.id.bgwinrate)
        layout = itemView.findViewById(R.id.recyclerviewposition)
    }

    fun bind(pvpMapStatistics: PvpMapStatistics, position: Int) {
        bgName?.text = pvpMapStatistics.world_map.name
        bgGames?.text = pvpMapStatistics.match_statistics.played.toString()
        bgWins?.text = pvpMapStatistics.match_statistics.won.toString()
        bgWinRate?.text = ((pvpMapStatistics.match_statistics.won * 100) / pvpMapStatistics.match_statistics.played).toString() + "%"
        if (position % 2 == 0) {
            layout?.setBackgroundResource(R.drawable.bgbaground)
        }
    }
}