package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Account information.
 */
data class AccountInformation(

        @SerializedName("battleTag")
        @Expose
        var battleTag: String,

        @SerializedName("paragonLevel")
        @Expose
        var paragonLevel: Int,

        @SerializedName("paragonLevelHardcore")
        @Expose
        var paragonLevelHardcore: Int,

        @SerializedName("paragonLevelSeason")
        @Expose
        var paragonLevelSeason: Int,

        @SerializedName("paragonLevelSeasonHardcore")
        @Expose
        var paragonLevelSeasonHardcore: Int,

        @SerializedName("guildName")
        @Expose
        var guildName: String,

        @SerializedName("heroes")
        @Expose
        var heroes: List<Hero>,

        @SerializedName("lastHeroPlayed")
        @Expose
        var lastHeroPlayed: Long,

        @SerializedName("lastUpdated")
        @Expose
        var lastUpdated: Long,

        @SerializedName("kills")
        @Expose
        var kills: Kills,

        @SerializedName("highestHardcoreLevel")
        @Expose
        var highestHardcoreLevel: Int,

        @SerializedName("timePlayed")
        @Expose
        var timePlayed: TimePlayed,

        @SerializedName("progression")
        @Expose
        var progression: Progression,

        @SerializedName("fallenHeroes")
        @Expose
        var fallenHeroes: List<FallenHero>,

        @SerializedName("blacksmith")
        @Expose
        var blacksmith: Blacksmith,

        @SerializedName("jeweler")
        @Expose
        var jeweler: Jeweler,

        @SerializedName("mystic")
        @Expose
        var mystic: Mystic,

        @SerializedName("jewelerSeason")
        @Expose
        var jewelerSeason: JewelerSeason,

        @SerializedName("blacksmithSeason")
        @Expose
        var blacksmithSeason: BlacksmithSeason,


        @SerializedName("mysticSeason")
        @Expose
        var mysticSeason: MysticSeason,

        @SerializedName("blacksmithHardcore")
        @Expose
        var blacksmithHardcore: BlacksmithHardcore,

        @SerializedName("jewelerHardcore")
        @Expose
        var jewelerHardcore: JewelerHardcore,

        @SerializedName("mysticHardcore")
        @Expose
        var mysticHardcore: MysticHardcore,

        @SerializedName("blacksmithSeasonHardcore")
        @Expose
        var blacksmithSeasonHardcore: BlacksmithSeasonHardcore,

        @SerializedName("jewelerSeasonHardcore")
        @Expose
        var jewelerSeasonHardcore: JewelerSeasonHardcore,

        @SerializedName("mysticSeasonHardcore")
        @Expose
        var mysticSeasonHardcore: MysticSeasonHardcore
)