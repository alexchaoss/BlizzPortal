package com.BlizzardArmory.ui.warcraft.mythicplusleaderboards

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard.Members
import com.BlizzardArmory.model.warcraft.specialization.Specialization
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.warcraft.character.navigation.WoWNavFragment
import com.BlizzardArmory.util.WoWClassColor


class PlayerViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context, private val specialization: List<Specialization>) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.d3_player_list, parent, false)) {

    var name: TextView? = null
    var icon: ImageView? = null

    init {
        name = itemView.findViewById(R.id.btag)
        icon = itemView.findViewById(R.id.icon)
    }

    fun bind(member: Members, region: String) {
        name?.text = member.profile.name
        val classId = specialization.find { it.id == member.specialization.id }!!.playableClassId
        name?.setTextColor(Color.parseColor(WoWClassColor.getClassColor(classId)))
        icon?.visibility = View.GONE
        onClickCharacter(member, region)
    }

    private fun onClickCharacter(member: Members, region: String) {
        itemView.setOnClickListener {
            val woWNavFragment = WoWNavFragment.newInstance(member.profile.name, member.profile.realm.slug, "null", region)
            (context as NavigationActivity).supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
                .add(R.id.fragment, woWNavFragment, "NAV_FRAGMENT")
                .addToBackStack("wow_nav").commit()
            context.supportFragmentManager.executePendingTransactions()
        }
    }

}
