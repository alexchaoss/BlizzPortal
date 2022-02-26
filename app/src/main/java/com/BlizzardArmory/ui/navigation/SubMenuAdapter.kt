package com.BlizzardArmory.ui.navigation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.menu.SubMenuItem

class SubMenuAdapter(private val list: List<SubMenuItem>, val context: Context) :
    RecyclerView.Adapter<MenuSubViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuSubViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MenuSubViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: MenuSubViewHolder, position: Int) {
        val menuItem: SubMenuItem = list[position]
        holder.bind(menuItem)
    }

    override fun getItemCount(): Int = list.size

}