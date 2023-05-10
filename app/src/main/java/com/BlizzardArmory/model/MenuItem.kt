package com.BlizzardArmory.model

import androidx.annotation.Keep

@Keep
data class MenuItem(
    val header: Boolean,
    val parent: String,
    val title: String,
    var icon: Int,
    var toggle: Boolean
)
