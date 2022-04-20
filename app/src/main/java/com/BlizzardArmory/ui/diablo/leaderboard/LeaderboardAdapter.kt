package com.BlizzardArmory.ui.diablo.leaderboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.diablo.data.common.Row
import java.util.*

class LeaderboardAdapter(private val list: List<Row>, private val region: String, private val context: Context)
    : RecyclerView.Adapter<LeaderboardViewHolder>() {

    private var fullLeaderboardList = ArrayList<Row>()

    init {
        fullLeaderboardList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LeaderboardViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        val row: Row = fullLeaderboardList[position]
        holder.bind(row, region)
    }

    override fun getItemCount(): Int = fullLeaderboardList.size

    fun filter(constraint: String) {
        fullLeaderboardList.clear()
        if (constraint.lowercase(Locale.getDefault()).isEmpty()) {
            fullLeaderboardList.addAll(list)
        } else {
            if (constraint.toIntOrNull() != null && constraint.toInt() <= list.size) {
                fullLeaderboardList.add(list[constraint.toInt() - 1])
            } else {
                if (constraint == "m" || constraint == "f") {
                    searchWithConstraint(constraint) { data, const ->
                        return@searchWithConstraint data == const.lowercase()
                    }
                } else {
                    searchWithConstraint(constraint) { data, const ->
                        return@searchWithConstraint data.contains(const.lowercase())
                    }
                }
            }
        }
        notifyDataSetChanged()
    }

    private fun searchWithConstraint(constraint: String, search: (data: String, constraint: String) -> Boolean) {
        for (row in list) {
            var match = false
            for (player in row.player) {
                for (data in player.data) {
                    if (data.string != null && search(data.string.lowercase(), constraint)) {
                        match = true
                    }
                }
            }
            if (match) {
                fullLeaderboardList.add(row)
            }
        }
    }
}