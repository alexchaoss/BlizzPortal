package com.BlizzardArmory.model.warcraft.guild.activity

import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName


data class ActivitiesInformation(

    @SerializedName("_links") val _links: Links,
    @SerializedName("guild") val guild: Guild,
    @SerializedName("activities") val activities: List<Activities>
)