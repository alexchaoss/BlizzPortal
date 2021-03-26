package com.BlizzardArmory.ui.warcraft.character.armory

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.talents.Talent
import com.BlizzardArmory.model.warcraft.talents.TalentsIcons
import com.BlizzardArmory.util.events.TalentClickedEvent
import com.bumptech.glide.Glide
import org.greenrobot.eventbus.EventBus

class TalentViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_talent_list, parent, false)) {

    var level: TextView? = null
    var name: TextView? = null
    var icon: ImageView? = null

    init {
        level = itemView.findViewById(R.id.level)
        name = itemView.findViewById(R.id.name)
        icon = itemView.findViewById(R.id.icon)
    }

    fun bind(talent: Talent, talentsIcons: List<TalentsIcons>) {
        val talentIcon = talentsIcons.find { it.id == talent.talent.id }
        level?.text = talentIcon?.level.toString()
        name?.text = talent.talent.name
        Glide.with(itemView).load(talentIcon?.icon).into(icon!!)
        itemView.setOnTouchListener { view, motionEvent ->
            view.performClick()
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    EventBus.getDefault().post(TalentClickedEvent(
                            talent.spellTooltip.description,
                            talent.spellTooltip.spell.name,
                            talent.spellTooltip.powerCost,
                            talent.spellTooltip.castTime,
                            talent.spellTooltip.cooldown,
                            true))
                }
                MotionEvent.ACTION_UP -> {
                    EventBus.getDefault().post(TalentClickedEvent(
                            talent.spellTooltip.description,
                            talent.spellTooltip.spell.name,
                            talent.spellTooltip.powerCost,
                            talent.spellTooltip.castTime,
                            talent.spellTooltip.cooldown,
                            false))
                }
            }
            return@setOnTouchListener true
        }
    }
}