package com.BlizzardArmory.ui.diablo.diablo3.leaderboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.diablo.diablo3.data.common.Row
import java.sql.Date
import java.text.SimpleDateFormat


class LeaderboardViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.d3_leaderboard_list, parent, false)) {

    var rank: TextView? = null
    var tier: TextView? = null
    var time: TextView? = null
    var players: RecyclerView? = null

    init {
        rank = itemView.findViewById(R.id.rank)
        tier = itemView.findViewById(R.id.tier)
        time = itemView.findViewById(R.id.time)
        players = itemView.findViewById(R.id.players_recycler)
    }

    fun bind(row: Row, region: String) {
        rank?.text = row.order.toString()
        tier?.text = row.data.find { it.id == "RiftLevel" }?.number.toString()
        val date = Date(row.data.find { it.id == "RiftTime" }?.timestamp!!)
        val format = SimpleDateFormat("mm:ss.SSS")
        time?.text = format.format(date)

        players?.apply {
            adapter = PlayerAdapter(row.player, region, context)
        }
    }
}
