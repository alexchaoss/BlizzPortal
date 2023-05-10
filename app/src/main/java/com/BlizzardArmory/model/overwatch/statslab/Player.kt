package com.BlizzardArmory.model.overwatch.statslab

import androidx.annotation.Keep

@Keep
data class Player(val name: String, val heroes: List<Hero>)
