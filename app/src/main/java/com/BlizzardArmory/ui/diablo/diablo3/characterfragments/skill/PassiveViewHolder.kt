package com.BlizzardArmory.ui.diablo.diablo3.characterfragments.skill

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.diablo.diablo3.character.skills.Passive
import com.BlizzardArmory.network.NetworkUtils
import com.bumptech.glide.Glide


class PassiveViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.d3_passive_list, parent, false)) {

    var icon: ImageView? = null

    init {
        icon = itemView.findViewById(R.id.icon)
    }

    fun bind(skill: Passive) {
        Glide.with(itemView).load(NetworkUtils.D3_ICON_SKILLS.replace("url", skill.skill.icon))
            .into(icon!!)
    }
}
