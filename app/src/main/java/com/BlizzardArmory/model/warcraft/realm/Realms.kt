package com.BlizzardArmory.model.warcraft.realm

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Links
import com.BlizzardArmory.model.warcraft.common.Realm
import com.google.gson.annotations.SerializedName


@Keep
data class Realms(

    @SerializedName("_links") val links: Links,
    @SerializedName("realms") val realms: List<Realm>
)