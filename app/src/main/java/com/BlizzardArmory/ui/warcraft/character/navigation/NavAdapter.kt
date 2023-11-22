package com.BlizzardArmory.ui.warcraft.character.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.ui.warcraft.character.achievements.AchievementsFragment
import com.BlizzardArmory.ui.warcraft.character.armory.WoWCharacterFragment
import com.BlizzardArmory.ui.warcraft.character.mythicplus.MythicPlusFragment
import com.BlizzardArmory.ui.warcraft.character.pvp.PvPFragment
import com.BlizzardArmory.ui.warcraft.character.raids.RaidsFragment
import com.BlizzardArmory.ui.warcraft.character.reputations.ReputationsFragment
import com.BlizzardArmory.ui.warcraft.character.talents.ClassicTalentsFragment
import com.BlizzardArmory.ui.warcraft.character.talents.TalentsFragment

class NavAdapter(fm: FragmentManager, lifecycle: Lifecycle, private var totalTabs: Int, internal var bundle: Bundle) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        return totalTabs
    }

    override fun createFragment(position: Int): Fragment {
        if (NetworkUtils.classic1x == true || NetworkUtils.classic == true) {
            return classicNav(position)
        } else {
            return retailNav(position)
        }
    }

    private fun retailNav(position: Int): Fragment {
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
                val raidsFragment = RaidsFragment()
                raidsFragment.arguments = bundle
                return raidsFragment
            }

            4 -> {
                val mythicPlusFragment = MythicPlusFragment()
                mythicPlusFragment.arguments = bundle
                return mythicPlusFragment
            }

            5 -> {
                val pvpFragment = PvPFragment()
                pvpFragment.arguments = bundle
                return pvpFragment
            }

            6 -> {
                val categoriesFragment = AchievementsFragment()
                categoriesFragment.arguments = bundle
                return categoriesFragment
            }

            else -> return WoWCharacterFragment()
        }
    }

    private fun classicNav(position: Int): Fragment {
        when (position) {
            0 -> {
                val wowCharacterFragment = WoWCharacterFragment()
                wowCharacterFragment.arguments = bundle
                return wowCharacterFragment
            }

            1 -> {
                val talentsFragment = ClassicTalentsFragment()
                talentsFragment.arguments = bundle
                return talentsFragment
            }

            2 -> {
                val pvpFragment = PvPFragment()
                pvpFragment.arguments = bundle
                return pvpFragment
            }

            3 -> {
                val categoriesFragment = AchievementsFragment()
                categoriesFragment.arguments = bundle
                return categoriesFragment
            }

            else -> return WoWCharacterFragment()
        }
    }
}