package com.BlizzardArmory.model.overwatch.statslab

import androidx.annotation.Keep

@Keep
data class Hero(val name: String, val stats: List<Stat>)