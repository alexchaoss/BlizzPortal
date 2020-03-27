package com.BlizzardArmory.ui.ui_warcraft


import android.content.Context;
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.BlizzardArmory.ui.ui_warcraft.progress.ProgressFragment
import com.BlizzardArmory.ui.ui_warcraft.pvp.PvPFragment
import com.BlizzardArmory.ui.ui_warcraft.reputations.ReputationsFragment

class MyAdapter(private val myContext: Context, fm: FragmentManager, private var totalTabs: Int, internal var bundle: Bundle) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                val wowCharacterFragment = WoWCharacterFragment()
                wowCharacterFragment.arguments = bundle
                return wowCharacterFragment
            }
            1 -> {
                val reputationsFragment = ReputationsFragment()
                reputationsFragment.arguments = bundle
                return reputationsFragment
            }
            2 -> {
                val progressFragment = ProgressFragment()
                progressFragment.arguments = bundle
                return progressFragment
            }
            3 -> {
                val pvpFragment = PvPFragment()
                pvpFragment.arguments = bundle
                return pvpFragment
            }
            else -> return WoWCharacterFragment()
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}