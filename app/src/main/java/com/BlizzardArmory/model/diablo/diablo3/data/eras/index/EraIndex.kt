package com.BlizzardArmory.model.diablo.diablo3.data.eras.index

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName


@Keep
data class EraIndex(

    @SerializedName("_links") val links: Links,
    @SerializedName("era") val era: List<Era>,
    @SerializedName("current_era") val current_era: Int,
    @SerializedName("last_update_time") val last_update_time: String,
    @SerializedName("generated_by") val generated_by: String
)