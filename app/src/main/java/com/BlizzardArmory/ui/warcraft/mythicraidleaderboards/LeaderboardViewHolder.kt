package com.BlizzardArmory.ui.warcraft.mythicraidleaderboards

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.mythicraid.Entries


class LeaderboardViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_mythic_raid_leaderboard_list, parent, false)) {

    var rank: TextView? = null
    var guild: TextView? = null
    var realm: TextView? = null

    init {
        rank = itemView.findViewById(R.id.rank)
        guild = itemView.findViewById(R.id.guild)
        realm = itemView.findViewById(R.id.realm)
    }

    fun bind(entry: Entries, position: Int) {
        rank?.text = position.toString()
        guild?.text = entry.guild.name
        realm?.text = entry.guild.realm.name

        if (entry.faction.type == "HORDE") {
            rank?.setTextColor(Color.parseColor("#CD2B00"))
            guild?.setTextColor(Color.parseColor("#CD2B00"))
            realm?.setTextColor(Color.parseColor("#CD2B00"))
        } else {
            rank?.setTextColor(Color.parseColor("#0C81CE"))
            guild?.setTextColor(Color.parseColor("#0C81CE"))
            realm?.setTextColor(Color.parseColor("#0C81CE"))
        }
    }
}
