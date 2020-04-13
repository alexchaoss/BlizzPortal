package com.BlizzardArmory.ui.ui_diablo.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.BlizzardArmory.ui.ui_diablo.CharacterCubePage
import com.BlizzardArmory.ui.ui_diablo.CharacterGearPage
import com.BlizzardArmory.ui.ui_diablo.CharacterSkillPage
import com.BlizzardArmory.ui.ui_diablo.CharacterStatsPage


class MyAdapter(fm: FragmentManager, private var totalTabs: Int, internal var bundle: Bundle) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                val statsFragment = CharacterStatsPage()
                statsFragment.arguments = bundle
                return statsFragment
            }
            1 -> {
                val gearFragment = CharacterGearPage()
                gearFragment.arguments = bundle
                return gearFragment
            }
            2 -> {
                val skillFragment = CharacterSkillPage()
                skillFragment.arguments = bundle
                return skillFragment
            }
            3 -> {
                val cubePage = CharacterCubePage()
                cubePage.arguments = bundle
                return cubePage
            }
            else -> return CharacterStatsPage()
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}