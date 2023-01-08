package com.BlizzardArmory.util

object IdGenarator {
    fun generate(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9') + '-' + '_'
        return (1..length).map { allowedChars.random() }.joinToString("")
    }
}