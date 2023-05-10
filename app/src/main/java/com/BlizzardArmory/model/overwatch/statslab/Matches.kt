package com.BlizzardArmory.model.overwatch.statslab

import androidx.annotation.Keep

@Keep
data class Matches(val matchId: Long, val matches: List<Match>)
