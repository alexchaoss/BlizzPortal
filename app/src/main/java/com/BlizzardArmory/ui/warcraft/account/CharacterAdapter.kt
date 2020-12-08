package com.BlizzardArmory.ui.warcraft.account

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.account.Character
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Params

class CharacterAdapter(private val list: List<Character>, private val fragmentManager: FragmentManager, private val context: Context, private val battlenetOAuth2Params: BattlenetOAuth2Params)
    : RecyclerView.Adapter<CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character: Character = list[position]
        holder.bind(character, battlenetOAuth2Params, fragmentManager)
    }

    override fun getItemCount(): Int = list.size

}