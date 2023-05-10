package com.BlizzardArmory.model.overwatch.statslab

import androidx.annotation.Keep

@Keep
data class Team(val name: String, val players: List<Player>)
