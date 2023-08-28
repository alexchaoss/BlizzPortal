package com.BlizzardArmory.ui.diablo.diablo3.characterfragments.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.BlizzardArmory.ui.diablo.diablo3.characterfragments.cube.CharacterCubeFragment
import com.BlizzardArmory.ui.diablo.diablo3.characterfragments.gear.CharacterGearFragment
import com.BlizzardArmory.ui.diablo.diablo3.characterfragments.skill.CharacterSkillFragment
import com.BlizzardArmory.ui.diablo.diablo3.characterfragments.stats.CharacterStatsFragment


class MyAdapter(fm: FragmentManager, lifecycle: Lifecycle, private var totalTabs: Int, internal var bundle: Bundle) :
    FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return totalTabs
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                val statsFragment = CharacterStatsFragment()
                statsFragment.arguments = bundle
                return statsFragment
            }
            1 -> {
                val gearFragment = CharacterGearFragment()
                gearFragment.arguments = bundle
                return gearFragment
            }
            2 -> {
                val skillFragment = CharacterSkillFragment()
                skillFragment.arguments = bundle
                return skillFragment
            }
            3 -> {
                val cubePage = CharacterCubeFragment()
                cubePage.arguments = bundle
                return cubePage
            }
            else -> return CharacterStatsFragment()
        }
    }
}