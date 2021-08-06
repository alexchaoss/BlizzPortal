package com.BlizzardArmory.ui.overwatch.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.overwatch.account.favorite.FavoriteProfile

class FavoritesAdapter(private val list: List<FavoriteProfile>, private val fragmentManager: FragmentManager, private val context: Context)
    : RecyclerView.Adapter<FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavoritesViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val profile: FavoriteProfile = list[position]
        holder.bind(profile, fragmentManager)
    }

    override fun getItemCount(): Int = list.size

}