package com.BlizzardArmory.model.warcraft.talents.specializationdata

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Specialization data.
 */
data class SpecializationData(

        @SerializedName("_links")
        @Expose
        var links: Links,

        @SerializedName("id")
        @Expose
        var id: Long,

        @SerializedName("playable_class")
        @Expose
        var playableClass: PlayableClass,

        @SerializedName("name")
        @Expose
        var name: String,

        @SerializedName("gender_description")
        @Expose
        var genderDescription: GenderDescription,

        @SerializedName("media")
        @Expose
        var media: Media,

        @SerializedName("role")
        @Expose
        var role: Role,

        @SerializedName("talent_tiers")
        @Expose
        var talentTiers: List<TalentTier>,

        @SerializedName("pvp_talents")
        @Expose
        var pvpTalents: List<PvpTalent>
)