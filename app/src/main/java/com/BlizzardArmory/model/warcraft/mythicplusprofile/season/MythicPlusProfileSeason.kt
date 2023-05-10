package com.BlizzardArmory.model.warcraft.mythicplusprofile.season

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Links
import com.BlizzardArmory.model.warcraft.mythicplusprofile.BestRuns
import com.BlizzardArmory.model.warcraft.mythicplusprofile.Character
import com.BlizzardArmory.model.warcraft.mythicplusprofile.MythicRating
import com.google.gson.annotations.SerializedName


@Keep
data class MythicPlusProfileSeason(
    @SerializedName("_links") var Links: Links,
    @SerializedName("season") var season: Season,
    @SerializedName("best_runs") var bestRuns: ArrayList<BestRuns>,
    @SerializedName("character") var character: Character,
    @SerializedName("mythic_rating") var mythicRating: MythicRating
)