package com.BlizzardArmory.ui.starcraft.leaderboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.starcraft.leaderboard.LadderMembers
import java.util.*

class LeaderboardAdapter(private val list: List<List<LadderMembers>>, private val context: Context, private var playerRank: Int)
    : RecyclerView.Adapter<LeaderboardViewHolder>() {

    private var fullLeaderboardList = ArrayList<List<LadderMembers>>()

    init {
        fullLeaderboardList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LeaderboardViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        val team: List<LadderMembers> = fullLeaderboardList[position]
        holder.bind(team, playerRank +
                list.indexOfFirst { Triple(it[0].joinTimestamp, it[0].wins, it[0].losses) == Triple(team[0].joinTimestamp, team[0].wins, team[0].losses) })
    }

    override fun getItemCount(): Int = fullLeaderboardList.size

    fun filter(constraint: String) {
        fullLeaderboardList.clear()
        if (constraint.lowercase(Locale.getDefault()).isEmpty()) {
            fullLeaderboardList.addAll(list)
        } else {
            if (constraint.toIntOrNull() != null && constraint.toInt() <= list.size + playerRank) {
                fullLeaderboardList.add(list[constraint.toInt() - playerRank])
            } else {
                searchWithConstraint(constraint) { data, const ->
                    return@searchWithConstraint data.contains(const.lowercase())
                }
            }
        }
        notifyDataSetChanged()
    }

    private fun searchWithConstraint(constraint: String, search: (data: String, constraint: String) -> Boolean) {
        for (team in list) {
            var match = false
            for (member in team) {
                when {
                    search(member.character.displayName.lowercase(), constraint) -> {
                        match = true
                    }
                    search(member.character.clanName.lowercase(), constraint) -> {
                        match = true
                    }
                    search(member.character.clanTag.lowercase(), constraint) -> {
                        match = true
                    }
                }
            }

            if (match) {
                fullLeaderboardList.add(team)
            }
        }
    }
}