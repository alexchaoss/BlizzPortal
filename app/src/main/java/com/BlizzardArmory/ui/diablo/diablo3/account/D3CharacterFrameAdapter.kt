package com.BlizzardArmory.ui.diablo.diablo3.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.diablo.diablo3.account.Hero

class D3CharacterFrameAdapter(private val list: List<Hero>)
    : RecyclerView.Adapter<D3CharacterFrameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): D3CharacterFrameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return D3CharacterFrameViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: D3CharacterFrameViewHolder, position: Int) {
        val hero: Hero = list[position]
        holder.bind(hero)
    }

    override fun getItemCount(): Int = list.size

}