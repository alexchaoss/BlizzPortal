package com.BlizzardArmory.ui.ui_warcraft.account

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.connection.oauth.BnOAuth2Params
import com.BlizzardArmory.model.warcraft.favorites.FavoriteCharacter

class FavoritesAdapter(private val list: List<FavoriteCharacter>, private val fragmentManager: FragmentManager, private val context: Context, private val bnOAuth2Params: BnOAuth2Params)
    : RecyclerView.Adapter<FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavoritesViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val character: FavoriteCharacter = list[position]
        holder.bind(character, fragmentManager, bnOAuth2Params)
    }

    override fun getItemCount(): Int = list.size

}