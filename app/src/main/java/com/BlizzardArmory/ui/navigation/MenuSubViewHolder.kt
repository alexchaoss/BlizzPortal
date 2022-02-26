package com.BlizzardArmory.ui.navigation

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.menu.SubMenuItem
import com.BlizzardArmory.util.ResourceNameToId.getDrawableIdFromString
import com.BlizzardArmory.util.ResourceNameToId.getStringIdFromString
import com.BlizzardArmory.util.events.MenuItemEvent
import org.greenrobot.eventbus.EventBus


class MenuSubViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.menu_item_sub, parent, false)) {

    private var icon: ImageView? = null
    private var title: TextView? = null

    init {
        icon = itemView.findViewById(R.id.icon)
        title = itemView.findViewById(R.id.title)
    }

    fun bind(menuItem: SubMenuItem) {

        val outValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        itemView.setBackgroundResource(outValue.resourceId)

        icon?.setImageDrawable(
            ResourcesCompat.getDrawable(
                context.resources,
                getDrawableIdFromString(menuItem.icon, context),
                context.theme
            )
        )
        title?.text = context.resources.getString(getStringIdFromString(menuItem.string, context))

        itemView.setOnClickListener {
            EventBus.getDefault().post(MenuItemEvent(menuItem))
        }
    }

}