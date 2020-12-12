package com.BlizzardArmory.model

data class MenuItem(
        val header: Boolean,
        val parent: String,
        val title: String,
        var icon: Int,
        var toggle: Boolean
)
