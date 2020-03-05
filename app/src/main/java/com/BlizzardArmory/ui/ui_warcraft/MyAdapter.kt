package com.BlizzardArmory.ui.ui_warcraft


import android.content.Context;
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int, internal var bundle: Bundle) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                val wowCharacterFragment = WoWCharacterFragment()
                wowCharacterFragment.arguments = bundle
                return wowCharacterFragment
            }
            1 -> {
                return AchievementsFragment()
            }
            2 -> {
                return ProgressFragment()
            }
            3 -> {
                return PvPFragment()
            }
            else -> return WoWCharacterFragment()
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}