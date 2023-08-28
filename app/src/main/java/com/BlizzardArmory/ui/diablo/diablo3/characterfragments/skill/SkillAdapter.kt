package com.BlizzardArmory.ui.diablo.diablo3.characterfragments.skill

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.diablo.diablo3.character.skills.Active
import com.BlizzardArmory.util.events.D3SkillEvent
import org.greenrobot.eventbus.EventBus

class SkillAdapter(private val list: List<Active>)
    : RecyclerView.Adapter<SkillViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SkillViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: SkillViewHolder, position: Int) {
        val skill: Active = list[position]
        holder.bind(skill)
        holder.itemView.setOnClickListener {
            EventBus.getDefault().post(D3SkillEvent(skill))
        }
    }

    override fun getItemCount(): Int = list.size

}