package com.BlizzardArmory.ui.overwatch.overwatchleague

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.overwatch.statslab.Match
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection

class MatchesAdapter(private val list: List<Match>, private val context: Context)
    : RecyclerView.Adapter<MatchesViewHolder>() {

    val expansionCollection = ExpansionLayoutCollection()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MatchesViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        val match: Match = list[position]
        holder.bind(match)
        expansionCollection.add(holder.expandable)
    }

    override fun getItemCount(): Int = list.size

}