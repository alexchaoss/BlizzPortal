package com.BlizzardArmory.ui.diablo.diablo3.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.diablo.diablo3.favorite.D3FavoriteProfile
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Params

class FavoritesAdapter(private val list: List<D3FavoriteProfile>, private val fragmentManager: FragmentManager, private val battlenetOAuth2Params: BattlenetOAuth2Params, private val context: Context)
    : RecyclerView.Adapter<FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavoritesViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val profile: D3FavoriteProfile = list[position]
        holder.bind(profile, fragmentManager, battlenetOAuth2Params)
    }

    override fun getItemCount(): Int = list.size

}