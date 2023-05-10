package com.BlizzardArmory.model.starcraft.league

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Key(

    @SerializedName("league_id") val league_id: Int,
    @SerializedName("season_id") val season_id: Int,
    @SerializedName("queue_id") val queue_id: Int,
    @SerializedName("team_type") val team_type: Int
)