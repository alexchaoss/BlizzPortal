package com.BlizzardArmory.model.overwatch.statslab

import androidx.annotation.Keep

@Keep
data class Match(val startTime: String, val stage: String, val mapType: String, val mapName: String, val team1: Team, val team2: Team)
