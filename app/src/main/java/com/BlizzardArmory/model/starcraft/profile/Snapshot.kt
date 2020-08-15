package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Snapshot.
 */
data class Snapshot(

        @SerializedName("seasonSnapshot")
        @Expose
        var seasonSnapshot: SeasonSnapshot,

        @SerializedName("totalRankedSeasonGamesPlayed")
        @Expose
        var totalRankedSeasonGamesPlayed: Int

)