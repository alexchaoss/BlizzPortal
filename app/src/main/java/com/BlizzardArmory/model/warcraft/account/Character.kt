package com.BlizzardArmory.model.warcraft.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Character.
 */
data class Character(

        @SerializedName("character")
        @Expose
        var characterSelf: CharacterSelf,

        @SerializedName("protected_character")
        @Expose
        var protectedCharacter: ProtectedCharacter,

        @SerializedName("name")
        @Expose
        var name: String,

        @SerializedName("id")
        @Expose
        var id: Long,

        @SerializedName("realm")
        @Expose
        var realm: Realm,

        @SerializedName("playable_class")
        @Expose
        var playableClass: PlayableClass,

        @SerializedName("playable_race")
        @Expose
        var playableRace: PlayableRace,

        @SerializedName("gender")
        @Expose
        var gender: Gender,

        @SerializedName("faction")
        @Expose
        var faction: Faction,

        @SerializedName("level")
        @Expose
        var level: String

)