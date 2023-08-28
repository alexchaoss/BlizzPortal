package com.BlizzardArmory.model.diablo.diablo3.data.seasons.season

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Links
import com.BlizzardArmory.model.diablo.diablo3.data.common.LeaderboardInfo
import com.google.gson.annotations.SerializedName


@Keep
data class Season(

    @SerializedName("_links") val links: Links,
    @SerializedName("leaderboard") val leaderboardInfo: List<LeaderboardInfo>,
    @SerializedName("season_id") val season_id: Int,
    @SerializedName("last_update_time") val last_update_time: String,
    @SerializedName("generated_by") val generated_by: String
)