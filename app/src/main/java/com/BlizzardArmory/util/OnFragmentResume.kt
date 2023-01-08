package com.BlizzardArmory.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object OnFragmentResume {
    fun onResume(fragmentManager: FragmentManager, tag: String, onVisible: (Boolean) -> Unit) {
        fragmentManager.addOnBackStackChangedListener {
            val fragments: List<Fragment> = fragmentManager.fragments
            if (fragments.isNotEmpty() && fragments[fragments.size - 1].tag == tag) {
                onVisible(true)
            } else {
                onVisible(false)
            }
        }
    }
}