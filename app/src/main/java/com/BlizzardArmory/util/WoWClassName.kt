package com.BlizzardArmory.util

object WoWClassName {
    @JvmStatic
    fun get(id: Int): String {
        return when (id) {
            1 -> "WARRIOR"
            2 -> "PALADIN"
            3 -> "HUNTER"
            4 -> "ROGUE"
            5 -> "PRIEST"
            6 -> "DEATH_KNIGHT"
            7 -> "SHAMAN"
            8 -> "MAGE"
            9 -> "WARLOCK"
            10 -> "MONK"
            11 -> "DRUID"
            12 -> "DEMON_HUNTER"
            13 -> "EVOKER"
            else -> ""
        }
    }
}