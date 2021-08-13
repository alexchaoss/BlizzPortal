package com.BlizzardArmory.ui.warcraft.guild.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.BlizzardArmory.ui.warcraft.guild.achievements.AchievementsFragment
import com.BlizzardArmory.ui.warcraft.guild.activity.ActivityFragment
import com.BlizzardArmory.ui.warcraft.guild.roster.RosterFragment

class NavAdapter(fm: FragmentManager, lifecycle: Lifecycle, private var totalTabs: Int, internal var bundle: Bundle) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        return totalTabs
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                val activityFragment = ActivityFragment()
                activityFragment.arguments = bundle
                return activityFragment
            }
            1 -> {
                val rosterFragment = RosterFragment()
                rosterFragment.arguments = bundle
                return rosterFragment
            }
            2 -> {
                val achievementsFragment = AchievementsFragment()
                achievementsFragment.arguments = bundle
                return achievementsFragment
            }
            else -> return RosterFragment()
        }
    }
}