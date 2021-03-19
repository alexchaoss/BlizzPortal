package com.BlizzardArmory.ui.warcraft.guild.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.BlizzardArmory.ui.warcraft.guild.achievements.AchievementsFragment
import com.BlizzardArmory.ui.warcraft.guild.activity.ActivityFragment
import com.BlizzardArmory.ui.warcraft.guild.roster.RosterFragment

class NavAdapter(fm: FragmentManager, private var totalTabs: Int, internal var bundle: Bundle) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
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

    override fun getCount(): Int {
        return totalTabs
    }
}