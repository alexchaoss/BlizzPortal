package com.BlizzardArmory.model.warcraft.covenant.techtalent

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Links
import com.BlizzardArmory.model.common.Media
import com.BlizzardArmory.model.warcraft.common.SocketType
import com.BlizzardArmory.model.warcraft.common.SpellTooltip
import com.google.gson.annotations.SerializedName


@Keep
data class TechTalent(

    @SerializedName("_links") val links: Links,
    @SerializedName("id") val id: Int,
    @SerializedName("talent_tree") val talent_tree: TalentTree,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("spell_tooltip") val spellTooltip: SpellTooltip?,
    @SerializedName("tier") val tier: Int,
    @SerializedName("display_order") val display_order: Int,
    @SerializedName("prerequisite_talent") val prerequisite_talent: Prerequisite_talent,
    @SerializedName("media") val media: Media,
    @SerializedName("socket_type") val socketType: SocketType?
)