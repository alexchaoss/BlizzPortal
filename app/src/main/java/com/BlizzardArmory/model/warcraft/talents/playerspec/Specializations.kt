package com.BlizzardArmory.model.warcraft.talents.playerspec

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Specializations (

    @SerializedName("specialization") val specialization : Specialization,
    @SerializedName("glyphs") val glyphs : List<Glyphs>,
    @SerializedName("pvp_talent_slots") val pvpTalentSlots : List<PvpTalentSlots>,
    @SerializedName("loadouts") val loadouts : List<Loadouts>
)