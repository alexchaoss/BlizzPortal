package com.BlizzardArmory.ui.ui_diablo.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.connection.oauth.BnOAuth2Params
import com.BlizzardArmory.model.diablo.favorite.D3FavoriteProfile

class FavoritesAdapter(private val list: List<D3FavoriteProfile>, private val fragmentManager: FragmentManager, private val bnOAuth2Params: BnOAuth2Params, private val context: Context)
    : RecyclerView.Adapter<FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavoritesViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val profile: D3FavoriteProfile = list[position]
        holder.bind(profile, fragmentManager, bnOAuth2Params)
    }

    override fun getItemCount(): Int = list.size

}