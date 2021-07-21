package com.BlizzardArmory.ui.warcraft.pvpleaderboards

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.pvp.leaderboards.Entries
import java.util.*
import kotlin.collections.ArrayList

class LeaderboardAdapter(private val list: List<Entries>, private val context: Context, val region: String) :
    RecyclerView.Adapter<LeaderboardViewHolder>() {

    private var fullLeaderboardList = ArrayList<Entries>()

    init {
        fullLeaderboardList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LeaderboardViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        val entry: Entries = fullLeaderboardList[position]

        holder.bind(entry, region)
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
                    return@searchWithConstraint data.contains(const.lowercase())
                }
            }
        }
        notifyDataSetChanged()
    }

    private fun searchWithConstraint(constraint: String, search: (data: String, constraint: String) -> Boolean) {
        for (entry in list) {
            var match = false
            when {
                search(entry.character.name.lowercase(), constraint) -> {
                    match = true
                }
                search(entry.faction.type.lowercase(), constraint) -> {
                    match = true
                }
                search(entry.character.realm.name.lowercase(), constraint) -> {
                    match = true
                }
                search(entry.character.realm.name.lowercase(), constraint) -> {
                    match = true
                }
                else -> {
                    if (constraint.contains("rating")) {
                        if (constraint.contains("=") && search(entry.rating.toString(), constraint.substringAfter("="))) {
                            match = true
                        } else if (constraint.contains(">") && constraint.substringAfter(">")
                                .isDigitsOnly() && entry.rating > constraint.substringAfter(">")
                                .toInt()) {
                            match = true
                        } else if (constraint.contains("<") && constraint.substringAfter("<")
                                .isDigitsOnly() && entry.rating < constraint.substringAfter("<")
                                .toInt()) {
                            match = true
                        }
                    }
                }
            }

            if (match) {
                Log.i("TEST", "Constraint match")
                fullLeaderboardList.add(entry)
            }
        }
    }
}