package com.BlizzardArmory.ui.navigation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.menu.MenuItem

class MenuAdapter(private val list: List<MenuItem>, val context: Context) :
    RecyclerView.Adapter<MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MenuViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menuItem: MenuItem = list[position]
        holder.bind(menuItem, position)
    }

    override fun getItemCount(): Int = list.size

}