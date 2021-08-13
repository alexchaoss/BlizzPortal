package com.BlizzardArmory.ui.diablo.characterfragments.skill

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.BlizzardArmory
import com.BlizzardArmory.R
import com.BlizzardArmory.model.diablo.character.skills.Active
import com.BlizzardArmory.network.NetworkUtils
import com.bumptech.glide.Glide


class SkillViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.d3_skill_list, parent, false)) {

    var icon: ImageView? = null
    var name: TextView? = null
    var rune: TextView? = null
    var runeIcon: ImageView? = null

    init {
        icon = itemView.findViewById(R.id.icon)
        name = itemView.findViewById(R.id.name)
        rune = itemView.findViewById(R.id.rune)
        runeIcon = itemView.findViewById(R.id.rune_icon)
    }

    fun bind(skill: Active) {
        name?.text = skill.skill.name
        if (skill.rune != null) {
            rune?.text = skill.rune!!.name
            runeIcon?.setImageResource(itemView.context.resources.getIdentifier(getSmallRuneIcon(skill.rune!!.type), "drawable", BlizzardArmory.instance?.packageName))
        }
        Glide.with(itemView).load(NetworkUtils.D3_ICON_SKILLS.replace("url", skill.skill.icon))
            .into(icon!!)
    }

    private fun getSmallRuneIcon(type: String): String {
        when (type) {
            "a" -> return "small_rune_a"
            "b" -> return "small_rune_b"
            "c" -> return "small_rune_c"
            "d" -> return "small_rune_d"
            "e" -> return "small_rune_e"
        }
        return ""
    }
}
