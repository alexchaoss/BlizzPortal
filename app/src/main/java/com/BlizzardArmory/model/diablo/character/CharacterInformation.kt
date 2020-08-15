package com.BlizzardArmory.model.diablo.character

import com.BlizzardArmory.model.diablo.character.follower.Followers
import com.BlizzardArmory.model.diablo.character.progression.Progression
import com.BlizzardArmory.model.diablo.character.skills.Skills
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Character information.
 */
data class CharacterInformation(

        @SerializedName("id")
        @Expose
        var id: Long,

        @SerializedName("name")
        @Expose
        var name: String,

        @SerializedName("class")
        @Expose
        var class_: String,

        @SerializedName("gender")
        @Expose
        var gender: Int,

        @SerializedName("level")
        @Expose
        var level: Int,

        @SerializedName("paragonLevel")
        @Expose
        var paragonLevel: Long,

        @SerializedName("kills")
        @Expose
        var kills: Kills,

        @SerializedName("hardcore")
        @Expose
        var hardcore: Boolean,

        @SerializedName("seasonal")
        @Expose
        var seasonal: Boolean,

        @SerializedName("seasonCreated")
        @Expose
        var seasonCreated: Long,

        @SerializedName("skills")
        @Expose
        var skills: Skills,

        @SerializedName("itemsCharacter")
        @Expose
        var itemsCharacter: ItemsCharacter,

        @SerializedName("followers")
        @Expose
        var followers: Followers,

        @SerializedName("legendaryPowers")
        @Expose
        var legendaryPowers: List<LegendaryPower>,

        @SerializedName("progression")
        @Expose
        var progression: Progression,

        @SerializedName("alive")
        @Expose
        var alive: Boolean,

        @SerializedName("lastUpdated")
        @Expose
        var lastUpdated: Long,

        @SerializedName("highestSoloRiftCompleted")
        @Expose
        var highestSoloRiftCompleted: Int,

        @SerializedName("stats")
        @Expose
        var stats: Stats

)