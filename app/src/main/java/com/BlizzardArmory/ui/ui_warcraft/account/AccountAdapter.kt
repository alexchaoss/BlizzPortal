package com.BlizzardArmory.ui.ui_warcraft.account

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.connection.oauth.BnOAuth2Params
import com.BlizzardArmory.model.warcraft.account.Character

class AccountAdapter(private val list: List<Character>, private val fragmentManager: FragmentManager, private val context: Context, private val bnOAuth2Params: BnOAuth2Params)
    : RecyclerView.Adapter<AccountViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AccountViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val character: Character = list[position]
        holder.bind(character, bnOAuth2Params, fragmentManager)
    }

    override fun getItemCount(): Int = list.size

}