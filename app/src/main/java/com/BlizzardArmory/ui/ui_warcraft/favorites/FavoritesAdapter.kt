package com.BlizzardArmory.ui.ui_warcraft.account

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.model.warcraft.favorite.FavoriteCharacter

class FavoritesAdapter(private val list: List<FavoriteCharacter>, private val fragmentManager: FragmentManager, private val context: Context, private val battlenetOAuth2Params: BattlenetOAuth2Params)
    : RecyclerView.Adapter<FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavoritesViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val character: FavoriteCharacter = list[position]
        holder.bind(character, battlenetOAuth2Params, fragmentManager)
    }

    override fun getItemCount(): Int = list.size

}