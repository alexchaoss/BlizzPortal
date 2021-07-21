package com.BlizzardArmory.ui.warcraft.mythicplusleaderboards

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard.KeystoneAffixes
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard.LeadingGroups
import com.BlizzardArmory.model.warcraft.specialization.Specialization
import java.util.concurrent.TimeUnit


class LeaderboardViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context, private val specialization: List<Specialization>) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_mplus_leaderboard_list, parent, false)) {

    var rank: TextView? = null
    var team: RecyclerView? = null
    var time: TextView? = null
    var level: TextView? = null
    var affixes: RecyclerView? = null

    init {
        rank = itemView.findViewById(R.id.rank)
        team = itemView.findViewById(R.id.team)
        time = itemView.findViewById(R.id.time)
        level = itemView.findViewById(R.id.level)
        affixes = itemView.findViewById(R.id.affixes)
    }

    fun bind(group: LeadingGroups, position: Int, affixesList: List<KeystoneAffixes>, region: String) {
        rank?.text = position.toString()
        level?.text = group.keystone_levelstone_level.toString()
        time?.text = String.format("%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(group.duration),
            TimeUnit.MILLISECONDS.toMinutes(group.duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(group.duration)), // The change is in this line
            TimeUnit.MILLISECONDS.toSeconds(group.duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(group.duration)));

        team?.apply {
            adapter = PlayerAdapter(group.members, context, specialization, region)
        }
        Log.i("Affixes", affixesList.toString())
        affixes?.apply {
            adapter = AffixesAdapter(affixesList, context)
        }
    }
}
