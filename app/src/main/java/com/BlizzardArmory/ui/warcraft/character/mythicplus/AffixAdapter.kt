package com.BlizzardArmory.ui.warcraft.character.mythicplus

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.mythicplusprofile.KeystoneAffixes

class AffixAdapter(private val list: List<KeystoneAffixes>, private val context: Context)
    : RecyclerView.Adapter<AffixViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AffixViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AffixViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: AffixViewHolder, position: Int) {
        val mode: KeystoneAffixes = list[position]
        holder.bind(mode)
    }

    override fun getItemCount(): Int = list.size

}