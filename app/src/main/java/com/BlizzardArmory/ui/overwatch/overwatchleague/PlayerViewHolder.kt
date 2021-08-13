package com.BlizzardArmory.ui.overwatch.overwatchleague

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.overwatch.statslab.Player


class PlayerViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.overwatch_player_list, parent, false)) {

    var name: TextView? = null

    init {
        name = itemView.findViewById(R.id.name)
    }

    fun bind(player: Player) {
        name?.text = player.name
    }
}
