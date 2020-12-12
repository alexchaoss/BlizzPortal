package com.BlizzardArmory.ui.navigation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.MenuItem
import com.BlizzardArmory.util.events.MenuItemClickEvent
import org.greenrobot.eventbus.EventBus

class MenuAdapter(private val list: ArrayList<MenuItem>, private val context: Context)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val hiddenItems = mutableListOf<MenuItem>()

    override fun getItemViewType(position: Int): Int {
        return if (list[position].header) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            0 -> return MenuHeaderViewHolder(inflater, parent, context)
            1 -> return MenuSubViewHolder(inflater, parent, context)
        }
        return MenuHeaderViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val menuItem: MenuItem = list[position]
        holder.itemView.setOnClickListener {
            EventBus.getDefault().post(MenuItemClickEvent(menuItem, position))
        }
        when (holder.itemViewType) {
            0 -> (holder as MenuHeaderViewHolder).bind(menuItem)
            1 -> (holder as MenuSubViewHolder).bind(menuItem)
        }
    }

    override fun getItemCount(): Int = list.size

    fun hideSubMenu(item: MenuItem) {
        if (list.any { it.parent == item.title }) {
            list.removeAll {
                val contains = it.parent == item.title
                if (contains) {
                    hiddenItems.add(it)
                }
                contains
            }
            notifyDataSetChanged()
        }
    }

    fun showSubMenu(item: MenuItem) {
        list.addAll(list.indexOf(list.find { it.title == item.title }) + 1, hiddenItems.filter { it.parent == item.title })
        hiddenItems.removeAll {
            it.parent == item.title
        }
        notifyDataSetChanged()
    }

}