package com.BlizzardArmory.ui.warcraft.character.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.BlizzardArmory.ui.warcraft.character.achievements.AchievementsFragment
import com.BlizzardArmory.ui.warcraft.character.armory.WoWCharacterFragment
import com.BlizzardArmory.ui.warcraft.character.progress.ProgressFragment
import com.BlizzardArmory.ui.warcraft.character.pvp.PvPFragment
import com.BlizzardArmory.ui.warcraft.character.reputations.ReputationsFragment
import com.BlizzardArmory.ui.warcraft.character.talents.TalentsFragment

class NavAdapter(fm: FragmentManager, lifecycle: Lifecycle, private var totalTabs: Int, internal var bundle: Bundle) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        return totalTabs
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                val wowCharacterFragment = WoWCharacterFragment()
                wowCharacterFragment.arguments = bundle
                return wowCharacterFragment
            }
            1 -> {
                val talentsFragment = TalentsFragment()
                talentsFragment.arguments = bundle
                return talentsFragment
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
}