package com.BlizzardArmory.model.warcraft.covenant.covenant

import androidx.annotation.Keep
import com.BlizzardArmory.model.warcraft.common.SpellTooltip
import com.google.gson.annotations.SerializedName


@Keep
data class ClassAbilities(

    @SerializedName("id") val id: Int,
    @SerializedName("playable_class") val playable_class: PlayableClass,
    @SerializedName("spell_tooltip") val spell_tooltip: SpellTooltip
)