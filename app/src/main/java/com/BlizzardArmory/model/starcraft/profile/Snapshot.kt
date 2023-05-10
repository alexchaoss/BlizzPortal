package com.BlizzardArmory.model.starcraft.profile

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Snapshot.
 */
@Keep
data class Snapshot(

    @SerializedName("seasonSnapshot")
    var seasonSnapshot: SeasonSnapshot,

    @SerializedName("totalRankedSeasonGamesPlayed")
    var totalRankedSeasonGamesPlayed: Int

)