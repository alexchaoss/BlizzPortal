package com.BlizzardArmory.model.diablo.diablo3.character

import androidx.annotation.Keep
import com.BlizzardArmory.model.diablo.diablo3.character.follower.Followers
import com.BlizzardArmory.model.diablo.diablo3.character.progression.Progression
import com.BlizzardArmory.model.diablo.diablo3.character.skills.Skills
import com.google.gson.annotations.SerializedName


/**
 * The type Character information.
 */
@Keep
data class CharacterInformation(

    @SerializedName("id")
    var id: Long,

    @SerializedName("name")
    var name: String,

    @SerializedName("class")
    var class_: String,

    @SerializedName("gender")
    var gender: Int,

    @SerializedName("level")
    var level: Int,

    @SerializedName("paragonLevel")
    var paragonLevel: Long,

    @SerializedName("kills")
    var kills: Kills,

    @SerializedName("hardcore")
    var hardcore: Boolean,

    @SerializedName("seasonal")
    var seasonal: Boolean,

    @SerializedName("seasonCreated")
    var seasonCreated: Long,

    @SerializedName("skills")
    var skills: Skills,

    @SerializedName("itemsCharacter")
    var itemsCharacter: ItemsCharacter,

    @SerializedName("followers")
    var followers: Followers,

    @SerializedName("legendaryPowers")
    var legendaryPowers: List<LegendaryPower>,

    @SerializedName("progression")
    var progression: Progression,

    @SerializedName("alive")
    var alive: Boolean,

    @SerializedName("lastUpdated")
    var lastUpdated: Long,

    @SerializedName("highestSoloRiftCompleted")
    var highestSoloRiftCompleted: Int,

    @SerializedName("stats")
    var stats: Stats

)