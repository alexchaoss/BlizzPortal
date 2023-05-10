package com.BlizzardArmory.model.warcraft.guild.roster

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName


@Keep
data class Roster(

    @SerializedName("_links") val _links: Links,
    @SerializedName("guild") val guild: Guild,
    @SerializedName("members") val members: List<Members>
)