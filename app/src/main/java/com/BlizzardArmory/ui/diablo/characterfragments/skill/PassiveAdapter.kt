package com.BlizzardArmory.ui.diablo.characterfragments.skill

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.diablo.character.skills.Passive
import com.BlizzardArmory.util.events.D3PassiveEvent
import org.greenrobot.eventbus.EventBus

class PassiveAdapter(private val list: List<Passive>)
    : RecyclerView.Adapter<PassiveViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassiveViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PassiveViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PassiveViewHolder, position: Int) {
        val skill: Passive = list[position]
        holder.bind(skill)
        holder.itemView.setOnClickListener {
            EventBus.getDefault().post(D3PassiveEvent(skill))
        }
    }

    override fun getItemCount(): Int = list.size

}