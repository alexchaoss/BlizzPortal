package com.BlizzardArmory.ui.ui_warcraft.progress


import android.content.Context;
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.BlizzardArmory.ui.ui_warcraft.AchievementsFragment
import com.BlizzardArmory.ui.ui_warcraft.PvPFragment
import com.BlizzardArmory.ui.ui_warcraft.WoWCharacterFragment

class MyAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int, internal var bundle: Bundle) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                val wowCharacterFragment = WoWCharacterFragment()
                wowCharacterFragment.arguments = bundle
                return wowCharacterFragment
            }
            1 -> {
                val achievementsFragment = AchievementsFragment()
                achievementsFragment.arguments = bundle
                return achievementsFragment
            }
            2 -> {
                val progressFragment = ProgressFragment()
                progressFragment.arguments = bundle
                return progressFragment
            }
            3 -> {
                val pvPFragment = PvPFragment()
                pvPFragment.arguments = bundle
                return pvPFragment
            }
            else -> return WoWCharacterFragment()
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}