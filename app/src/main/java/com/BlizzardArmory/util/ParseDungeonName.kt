package com.BlizzardArmory.util

object ParseDungeonName {
    fun parseName(dg: String): String {
        return dg.replace("'", "").replace(",", "").replace(":", "").replace(" ", "-")
    }
}