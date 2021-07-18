package com.BlizzardArmory.ui.warcraft.mythicraidleaderboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.mythicraid.Entries
import com.BlizzardArmory.network.ErrorMessages
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.warcraft.guild.navigation.GuildNavFragment
import com.BlizzardArmory.util.DialogPrompt
import java.util.*

class LeaderboardAdapter(private val list: List<Entries>, private val context: Context) :
    RecyclerView.Adapter<LeaderboardViewHolder>() {

    private var fullLeaderboardList = ArrayList<Entries>()

    init {
        fullLeaderboardList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LeaderboardViewHolder(inflater, parent, context)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        val guild: Entries = fullLeaderboardList[position]
        holder.bind(guild, list.indexOfFirst { it.guild.id == guild.guild.id } + 1)
        setOnLickListener(holder, guild, context as NavigationActivity)
    }

    private fun setOnLickListener(holder: LeaderboardViewHolder, guild: Entries, context: NavigationActivity) {
        holder.itemView.setOnClickListener {
            if (guild.region != "cn") {
                val fragment: Fragment = GuildNavFragment()
                val bundle = Bundle()
                bundle.putString("realm", guild.guild.realm.slug)
                bundle.putString("guildName", guild.guild.name)
                bundle.putString("region", guild.region)
                fragment.arguments = bundle
                context.supportFragmentManager.beginTransaction()
                    .add(R.id.fragment, fragment, "guild_nav_fragment").addToBackStack("wow_guild")
                    .commit()
                context.supportFragmentManager.executePendingTransactions()
            } else {
                val dialog = DialogPrompt(context)
                dialog.addTitle(ErrorMessages(context.resources).ERROR, 20F, "title")
                    .addMessage(ErrorMessages(context.resources).CHINESE_MESSAGE, 16F, "message")
                    .addButtons(dialog.Button("Close", 16F, { dialog.dismiss() }, "button"))
                    .show()
            }
        }
    }

    override fun getItemCount(): Int = fullLeaderboardList.size

    fun filter(constraint: String) {
        fullLeaderboardList.clear()
        if (constraint.lowercase(Locale.getDefault()).isEmpty()) {
            fullLeaderboardList.addAll(list)
        } else {
            if (constraint.toIntOrNull() != null && constraint.toInt() <= list.size) {
                fullLeaderboardList.add(list[constraint.toInt() - 1])
            } else {
                searchWithConstraint(constraint) { data, const ->
                    return@searchWithConstraint data.contains(const)
                }
            }
        }
        notifyDataSetChanged()
    }

    private fun searchWithConstraint(constraint: String, search: (data: String, constraint: String) -> Boolean) {
        for (entry in list) {
            var match = false
            when {
                search(entry.guild.name, constraint) -> {
                    match = true
                }
                search(entry.guild.realm.name, constraint) -> {
                    match = true
                }
            }
            if (match) {
                fullLeaderboardList.add(entry)
            }
        }
    }
}