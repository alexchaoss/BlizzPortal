package com.BlizzardArmory.util

object WoWClassColor {
    @JvmStatic
    fun getClassColor(id: Int): String {
        return when (id) {
            1 -> "#C79C6E"
            2 -> "#F58CBA"
            3 -> "#ABD473"
            4 -> "#FFF569"
            5 -> "#FFFFFF"
            6 -> "#C41F3B"
            7 -> "#0070DE"
            8 -> "#69CCF0"
            9 -> "#9482C9"
            10 -> "#00FF96"
            11 -> "#FF7D0A"
            12 -> "#A330C9"
            else -> "#FFFFFF"
        }
    }
}