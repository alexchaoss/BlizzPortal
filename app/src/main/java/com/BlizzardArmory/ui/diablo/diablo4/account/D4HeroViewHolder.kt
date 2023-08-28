package com.BlizzardArmory.ui.diablo.diablo4.account

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.diablo.diablo4.account.Characters
import com.BlizzardArmory.util.events.D4CharacterEvent
import org.greenrobot.eventbus.EventBus


class D4HeroViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.d4_character_list, parent, false)) {

    var button: ImageButton? = null
    var icon: ImageView? = null
    var name: TextView? = null
    var level: TextView? = null

    init {
        button = itemView.findViewById(R.id.button)
        icon = itemView.findViewById(R.id.classIcon)
        name = itemView.findViewById(R.id.name)
        level = itemView.findViewById(R.id.level)
    }

    fun bind(character: Characters) {
        when (character.charClass) {
            "Necromancer" -> icon?.setBackgroundResource(R.drawable.d4_necromancer)
            "Druid" -> icon?.setBackgroundResource(R.drawable.d4_druid)
            "Rogue" -> icon?.setBackgroundResource(R.drawable.d4_rogue)
            "Barbarian" -> icon?.setBackgroundResource(R.drawable.d4_barbarian)
            "Sorcerer" -> icon?.setBackgroundResource(R.drawable.d4_sorcerer)
        }
        name?.text = character.name
        level?.text = character.level.toString()
        button?.setOnClickListener {
            EventBus.getDefault().post(D4CharacterEvent(character))
        }
    }

}
