package com.BlizzardArmory.ui.navigation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.MenuItem

class MenuHeaderViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.menu_item_header, parent, false)) {

    private var icon: ImageView? = null
    private var title: TextView? = null
    private var view: View? = null

    init {
        icon = itemView.findViewById(R.id.icon)
        title = itemView.findViewById(R.id.title)
        view = itemView.findViewById(R.id.separator)
    }

    fun bind(menuItem: MenuItem, position: Int) {
        if (position == 0) {
            view?.visibility = View.GONE
        } else {
            view?.visibility = View.VISIBLE
        }
        icon?.setImageResource(menuItem.icon)
        title?.text = menuItem.title
    }

}