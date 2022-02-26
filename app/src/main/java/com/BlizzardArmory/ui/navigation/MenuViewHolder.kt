package com.BlizzardArmory.ui.navigation

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.menu.MenuItem
import com.BlizzardArmory.model.menu.SubMenuItem
import com.BlizzardArmory.util.ResourceNameToId.getDrawableIdFromString
import com.BlizzardArmory.util.ResourceNameToId.getStringIdFromString
import com.BlizzardArmory.util.events.MenuItemEvent
import com.BlizzardArmory.util.expendablecardview.ExpandableCardview
import com.BlizzardArmory.util.expendablecardview.ExpandableState
import com.BlizzardArmory.util.expendablecardview.StateListener
import org.greenrobot.eventbus.EventBus

class MenuViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.menu_item, parent, false)), StateListener {

    private var card: ExpandableCardview? = null
    private var icon: ImageView? = null
    private var name: TextView? = null
    private var subMenu: RecyclerView? = null
    private var view: View? = null

    private var menuItem: MenuItem? = null

    init {
        card = itemView.findViewById(R.id.card)
        icon = itemView.findViewById(R.id.icon)
        name = itemView.findViewById(R.id.name)
        subMenu = itemView.findViewById(R.id.recycler)
        view = itemView.findViewById(R.id.separator)

    }

    fun bind(menuItem: MenuItem, position: Int) {

        card?.registerStateListener(this)

        this.menuItem = menuItem
        if (position == 0) {
            view?.visibility = View.INVISIBLE
        } else {
            view?.visibility = View.VISIBLE
        }

        icon?.setImageDrawable(
            ResourcesCompat.getDrawable(
                context.resources,
                getDrawableIdFromString(menuItem.icon, context),
                context.theme
            )
        )
        name?.text = context.resources.getString(getStringIdFromString(menuItem.string, context))

        if (menuItem.sub_menu.isNotEmpty()) {
            subMenu?.apply {
                adapter = SubMenuAdapter(menuItem.sub_menu, context)
            }
        } else {
            card?.setCanExpand(false)
            val fakeSubMenu = SubMenuItem("", menuItem.string, "")
            itemView.setOnClickListener {
                EventBus.getDefault().post(MenuItemEvent(fakeSubMenu))
            }
        }

    }

    override fun onChangeStateListener(newState: ExpandableState) {
        when (newState) {
            ExpandableState.CLOSED -> {
                icon?.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        context.resources,
                        getDrawableIdFromString(menuItem?.icon!!, context),
                        context.theme
                    )
                )
            }
            ExpandableState.EXPANDED -> {
                try {
                    icon?.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            context.resources,
                            getDrawableIdFromString(menuItem?.icon!! + "_glow", context),
                            context.theme
                        )
                    )
                } catch (e: Exception) {
                    Log.e("Menu Icon", "Not Game Icon")
                }
            }
        }
    }
}