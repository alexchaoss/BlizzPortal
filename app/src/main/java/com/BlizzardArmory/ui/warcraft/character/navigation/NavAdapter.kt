package com.BlizzardArmory.ui.warcraft.character.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.BlizzardArmory.ui.warcraft.character.achievements.AchievementsFragment
import com.BlizzardArmory.ui.warcraft.character.armory.WoWCharacterFragment
import com.BlizzardArmory.ui.warcraft.character.covenant.CovenantFragment
import com.BlizzardArmory.ui.warcraft.character.progress.ProgressFragment
import com.BlizzardArmory.ui.warcraft.character.pvp.PvPFragment
import com.BlizzardArmory.ui.warcraft.character.reputations.ReputationsFragment

class NavAdapter(fm: FragmentManager, private var totalTabs: Int, internal var bundle: Bundle) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                val wowCharacterFragment = WoWCharacterFragment()
                wowCharacterFragment.arguments = bundle
                return wowCharacterFragment
            }
            1 -> {
                val covenantFragment = CovenantFragment()
                covenantFragment.arguments = bundle
                return covenantFragment
            }
            2 -> {
                val reputationsFragment = ReputationsFragment()
                reputationsFragment.arguments = bundle
                return reputationsFragment
            }
            3 -> {
                val progressFragment = ProgressFragment()
                progressFragment.arguments = bundle
                return progressFragment
            }
            4 -> {
                val pvpFragment = PvPFragment()
                pvpFragment.arguments = bundle
                return pvpFragment
            }
            5 -> {
                val categoriesFragment = AchievementsFragment()
                categoriesFragment.arguments = bundle
                return categoriesFragment
            }
            else -> return WoWCharacterFragment()
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}