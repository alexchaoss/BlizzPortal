package com.BlizzardArmory.ui.warcraft.mythicplusleaderboards

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard.Leaderboard
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard.LeadingGroups
import com.BlizzardArmory.model.warcraft.specialization.Specialization
import java.util.*
import kotlin.collections.ArrayList

class LeaderboardAdapter(private val list: List<LeadingGroups>, private val context: Context, private val specialization: List<Specialization>, private val leaderboard: List<Leaderboard>, val region: String) :
    RecyclerView.Adapter<LeaderboardViewHolder>() {

    private var fullLeaderboardList = ArrayList<LeadingGroups>()

    init {
        fullLeaderboardList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LeaderboardViewHolder(inflater, parent, context, specialization)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        val group: LeadingGroups = fullLeaderboardList[position]
        val newPosition = list.indexOfFirst {
            Triple(it.completed_timestamp, it.keystone_levelstone_level, it.duration) == Triple(group.completed_timestamp, group.keystone_levelstone_level, group.duration)
        }
        val affixes = leaderboard.find { it.period_end_timestamp >= group.completed_timestamp && it.period_start_timestamp <= group.completed_timestamp }!!.keystone_affixesstone_affixes
        holder.bind(group, 1 + newPosition, affixes, region)
    }

    override fun getItemCount(): Int = fullLeaderboardList.size

    fun filter(constraint: String) {
        fullLeaderboardList.clear()
        if (constraint.lowercase(Locale.getDefault()).isEmpty()) {
            fullLeaderboardList.addAll(list)
        } else {
            if (constraint.toIntOrNull() != null && constraint.toInt() > 0 && constraint.toInt() - 1 <= list.size) {
                fullLeaderboardList.add(list[constraint.toInt() - 1])
            } else {
                searchWithConstraint(constraint) { data, const ->
                    return@searchWithConstraint data.contains(const)
                }
            }
        }
        notifyDataSetChanged()
    }

    private fun searchWithConstraint(constraint: String, search: (data: String, constraint: String) -> Boolean) {
        for (entry in list) {
            var match = false
            for (members in entry.members) {
                Log.i("Name", members.profile.name)
                if (search(members.profile.name, constraint)) {
                    match = true
                }
            }
            if (match) {
                Log.i("TEST", "Constraint match")
                fullLeaderboardList.add(entry)
            }
        }
    }
}