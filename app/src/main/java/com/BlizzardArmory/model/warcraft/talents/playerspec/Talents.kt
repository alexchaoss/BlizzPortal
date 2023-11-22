package com.BlizzardArmory.model.warcraft.talents.playerspec

import com.BlizzardArmory.model.warcraft.common.SpellTooltip
import com.google.gson.annotations.SerializedName


data class Talents(
    @SerializedName("talent") var talent: Talent,
    @SerializedName("spell_tooltip") var spellTooltip: SpellTooltip,
    @SerializedName("talent_rank") var talentRank: Int
)