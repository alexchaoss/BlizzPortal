package com.BlizzardArmory.util

import com.BlizzardArmory.model.warcraft.ClassColor

object WoWClassColor {
    @JvmStatic
    fun getClassColor(id: Int): String {
        return when (id) {
            1 -> ClassColor.WARRIOR.color
            2 -> ClassColor.PALADIN.color
            3 -> ClassColor.HUNTER.color
            4 -> ClassColor.ROGUE.color
            5 -> ClassColor.PRIEST.color
            6 -> ClassColor.DEATH_KNIGHT.color
            7 -> ClassColor.SHAMAN.color
            8 -> ClassColor.MAGE.color
            9 -> ClassColor.WARLOCK.color
            10 -> ClassColor.MONK.color
            11 -> ClassColor.DRUID.color
            12 -> ClassColor.DEMON_HUNTER.color
            13 -> ClassColor.EVOKER.color
            else -> "#000000"
        }
    }
}