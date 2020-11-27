package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.SerializedName


/**
 * The type Snapshot.
 */
data class Snapshot(

        @SerializedName("seasonSnapshot")
        var seasonSnapshot: SeasonSnapshot,

        @SerializedName("totalRankedSeasonGamesPlayed")
        var totalRankedSeasonGamesPlayed: Int

)