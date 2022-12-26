package com.BlizzardArmory.ui.warcraft.character.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.BlizzardArmory.ui.warcraft.character.achievements.AchievementsFragment
import com.BlizzardArmory.ui.warcraft.character.armory.WoWCharacterFragment
import com.BlizzardArmory.ui.warcraft.character.covenant.CovenantFragment
import com.BlizzardArmory.ui.warcraft.character.progress.ProgressFragment
import com.BlizzardArmory.ui.warcraft.character.pvp.PvPFragment
import com.BlizzardArmory.ui.warcraft.character.reputations.ReputationsFragment

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
            /*1 -> {
                val covenantFragment = CovenantFragment()
                covenantFragment.arguments = bundle
                return covenantFragment
            }*/
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
            4 -> {
                val categoriesFragment = AchievementsFragment()
                categoriesFragment.arguments = bundle
                return categoriesFragment
            }
            else -> return WoWCharacterFragment()
        }
    }
}