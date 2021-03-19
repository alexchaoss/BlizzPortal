package com.BlizzardArmory.ui.warcraft.guild.roster

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.guild.roster.Members
import com.BlizzardArmory.network.URLConstants
import com.bumptech.glide.Glide

class RosterViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_guild_roster_list, parent, false)) {

    var name: TextView? = null
    var level: TextView? = null
    var rank: TextView? = null
    var characterClass: ImageView? = null

    init {
        name = itemView.findViewById(R.id.name)
        level = itemView.findViewById(R.id.level)
        rank = itemView.findViewById(R.id.rank)
        characterClass = itemView.findViewById(R.id.character_class)
    }

    fun bind(member: Members) {
        name?.text = member.character.name
        level?.text = member.character.level.toString()
        val rankString = if (member.rank == 0) {
            "Guild Master"
        } else {
            "Rank ${member.rank}"
        }
        rank?.text = rankString
        getCharacterClassIcon(member.character.playable_class.id)
        getCharacterNameColor(member.character.playable_class.id)
    }

    private fun getCharacterNameColor(id: Int) {
        when (id) {
            1 -> name?.setTextColor(Color.parseColor("#C79C6E"))
            2 -> name?.setTextColor(Color.parseColor("#F58CBA"))
            3 -> name?.setTextColor(Color.parseColor("#ABD473"))
            4 -> name?.setTextColor(Color.parseColor("#FFF569"))
            5 -> name?.setTextColor(Color.parseColor("#FFFFFF"))
            6 -> name?.setTextColor(Color.parseColor("#C41F3B"))
            7 -> name?.setTextColor(Color.parseColor("#0070DE"))
            8 -> name?.setTextColor(Color.parseColor("#69CCF0"))
            9 -> name?.setTextColor(Color.parseColor("#9482C9"))
            10 -> name?.setTextColor(Color.parseColor("#00FF96"))
            11 -> name?.setTextColor(Color.parseColor("#FF7D0A"))
            12 -> name?.setTextColor(Color.parseColor("#A330C9"))
        }
    }

    private fun getCharacterClassIcon(id: Int) {
        Glide.with(itemView).load(URLConstants.getWoWAsset("class/$id")).circleCrop()
            .into(characterClass!!)
    }
}