package com.BlizzardArmory.ui.ui_diablo.leaderboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.model.diablo.data.common.Player
import com.BlizzardArmory.model.diablo.favorite.D3FavoriteProfile

class PlayerAdapter(private val list: List<Player>, private val region: String, private val context: Context)
    : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlayerViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player: Player = list[position]
        holder.bind(player, region)
    }

    override fun getItemCount(): Int = list.size

}