package com.BlizzardArmory.model.warcraft.guild.activity

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName


@Keep
data class ActivitiesInformation(

    @SerializedName("_links") val _links: Links,
    @SerializedName("guild") val guild: Guild,
    @SerializedName("activities") val activities: List<Activities>
)