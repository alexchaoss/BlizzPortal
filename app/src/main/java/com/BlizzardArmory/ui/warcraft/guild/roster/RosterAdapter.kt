package com.BlizzardArmory.ui.warcraft.guild.roster

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.guild.roster.Members
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.warcraft.character.navigation.WoWNavFragment
import java.util.*

class RosterAdapter(private val activities: List<Members>, private val context: Context, private val region: String) :
    RecyclerView.Adapter<RosterViewHolder>() {

    private var fullActivitiesList = ArrayList<Members>()

    init {
        fullActivitiesList.addAll(activities)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RosterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RosterViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RosterViewHolder, position: Int) {
        if (position % 2 != 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#15FFFFFF"))
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#00000000"))
        }
        val member = fullActivitiesList[position]
        holder.bind(member)

        holder.itemView.setOnClickListener {
            val woWNavFragment =
                WoWNavFragment.newInstance(member.character.name, member.character.realm.slug, "null", region)
            (context as NavigationActivity).supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
                .add(R.id.fragment, woWNavFragment, "NAV_FRAGMENT")
                .addToBackStack("wow_nav").commit()
            context.supportFragmentManager.executePendingTransactions()
        }
    }

    override fun getItemCount(): Int = fullActivitiesList.size

    fun filter(constraint: String) {
        Log.i("Filter", constraint)
        fullActivitiesList.clear()
        if (constraint.lowercase(Locale.getDefault()).isEmpty()) {
            fullActivitiesList.addAll(activities)
        } else {
            searchWithConstraint(constraint) { data, const ->
                return@searchWithConstraint data.contains(const)
            }
        }
        notifyDataSetChanged()
    }

    private fun searchWithConstraint(constraint: String, search: (data: String, constraint: String) -> Boolean) {
        for (entry in activities) {
            var match = false
            when {
                search(entry.character.name, constraint) -> {
                    match = true
                }
                search(entry.character.level.toString(), constraint) -> {
                    match = true
                }
                search(getClassNameFromId(entry.character.playable_class.id), constraint) -> {
                    match = true
                }
                search(getRaceNameFromId(entry.character.playable_race.id), constraint) -> {
                    match = true
                }
                search(entry.rank.toString(), constraint) -> {
                    match = true
                }
            }
            if (match) {
                fullActivitiesList.add(entry)
            }
        }
        Log.i("TEST", fullActivitiesList.size.toString())
    }

    private fun getClassNameFromId(id: Int): String {
        return when (id) {
            1 -> "warrior"
            2 -> "paladin"
            3 -> "hunter"
            4 -> "rogue"
            5 -> "priest"
            6 -> "death knight"
            7 -> "shaman"
            8 -> "mage"
            9 -> "warlock"
            10 -> "monk"
            11 -> "druid"
            12 -> "demon hunter"
            else -> ""
        }
    }

    private fun getRaceNameFromId(id: Int): String {
        return when (id) {
            1 -> "human"
            2 -> "orc"
            3 -> "dwarf"
            4 -> "night elf"
            5 -> "undead"
            6 -> "tauren"
            7 -> "gnome"
            8 -> "troll"
            9 -> "goblin"
            10 -> "blood elf"
            11 -> "draenei"
            22 -> "worgen"
            24, 25, 26 -> "pandaren"
            27 -> "nightborn"
            28 -> "highmountain tauren"
            29 -> "void elf"
            30 -> "lightforged draenei"
            31 -> "zandalari troll"
            32 -> "kul tiran"
            34 -> "dark iron dwarf"
            35 -> "vulpera"
            36 -> "mag'har orc"
            37 -> "mechagnome"
            else -> ""
        }
    }

}