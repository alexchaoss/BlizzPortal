package com.BlizzardArmory.ui.diablo.characterfragments.skill

import android.app.Application
import com.BlizzardArmory.R
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import org.greenrobot.eventbus.Subscribe

class CharacterSkillViewModel(application: Application) : BaseViewModel(application) {

    fun getSmallRuneIcon(type: String): String {
        when (type) {
            "a" -> return "small_rune_a"
            "b" -> return "small_rune_b"
            "c" -> return "small_rune_c"
            "d" -> return "small_rune_d"
            "e" -> return "small_rune_e"
        }
        return ""
    }

    fun getRuneIcon(type: String): Int {
        when (type) {
            "a" -> return R.drawable.rune_a
            "b" -> return R.drawable.rune_b
            "c" -> return R.drawable.rune_c
            "d" -> return R.drawable.rune_d
            "e" -> return R.drawable.rune_e
        }
        return 0
    }

    @Subscribe
    override fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(LocaleSelectedEvent)
    }
}