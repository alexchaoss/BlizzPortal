package com.BlizzardArmory.ui.diablo.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.BlizzardArmory.ui.diablo.characterfragments.cube.CharacterCubeFragment
import com.BlizzardArmory.ui.diablo.characterfragments.gear.CharacterGearFragment
import com.BlizzardArmory.ui.diablo.characterfragments.skill.CharacterSkillFragment
import com.BlizzardArmory.ui.diablo.characterfragments.stats.CharacterStatsFragment


class MyAdapter(fm: FragmentManager, private var totalTabs: Int, internal var bundle: Bundle) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
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

    override fun getCount(): Int {
        return totalTabs
    }
}