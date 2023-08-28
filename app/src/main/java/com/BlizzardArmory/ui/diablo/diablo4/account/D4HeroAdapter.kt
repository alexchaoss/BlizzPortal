package com.BlizzardArmory.ui.diablo.diablo4.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.diablo.diablo4.account.Characters

class D4HeroAdapter(private val list: List<Characters>) : RecyclerView.Adapter<D4HeroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): D4HeroViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return D4HeroViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: D4HeroViewHolder, position: Int) {
        val fallenHero: Characters = list[position]
        holder.bind(fallenHero)
    }

    override fun getItemCount(): Int = list.size

}