package com.BlizzardArmory.model.warcraft.talents.playerspec

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Links
import com.BlizzardArmory.model.warcraft.common.Character
import com.google.gson.annotations.SerializedName


@Keep
data class PlayerSpecializations(
    @SerializedName("_links") val _links: Links,
    @SerializedName("specializations") val specializations: List<Specializations>,
    @SerializedName("active_specialization") val active_specialization: ActiveSpecialization,
    @SerializedName("character") val character: Character,
    @SerializedName("specialization_groups") var specializationGroups: ArrayList<SpecializationGroups>
)