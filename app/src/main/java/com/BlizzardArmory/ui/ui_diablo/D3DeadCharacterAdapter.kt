package com.BlizzardArmory.ui.ui_diablo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.diablo.account.FallenHero

class D3DeadCharacterAdapter(private val list: List<FallenHero>)
    : RecyclerView.Adapter<D3DeadCharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): D3DeadCharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return D3DeadCharacterViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: D3DeadCharacterViewHolder, position: Int) {
        val fallenHero: FallenHero = list[position]
        holder.bind(fallenHero)
    }

    override fun getItemCount(): Int = list.size

}