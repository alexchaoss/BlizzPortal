package com.BlizzardArmory.model.diablo.diablo3.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Account information.
 */
@Keep
data class AccountInformation(
    @SerializedName("battleTag")
    var battleTag: String,

    @SerializedName("paragonLevel")
    var paragonLevel: Int,

    @SerializedName("paragonLevelHardcore")
    var paragonLevelHardcore: Int,

    @SerializedName("paragonLevelSeason")
    var paragonLevelSeason: Int,

    @SerializedName("paragonLevelSeasonHardcore")
    var paragonLevelSeasonHardcore: Int,

    @SerializedName("guildName")
    var guildName: String,

    @SerializedName("heroes")
    var heroes: List<Hero>,

    @SerializedName("lastHeroPlayed")
    var lastHeroPlayed: Long,

    @SerializedName("lastUpdated")
    var lastUpdated: Long,

    @SerializedName("kills")
    var kills: Kills,

    @SerializedName("highestHardcoreLevel")
    var highestHardcoreLevel: Int,

    @SerializedName("timePlayed")
    var timePlayed: TimePlayed,

    @SerializedName("progression")
    var progression: Progression,

    @SerializedName("fallenHeroes")
    var fallenHeroes: List<FallenHero>,

    @SerializedName("blacksmith")
    var blacksmith: Blacksmith,

    @SerializedName("jeweler")
    var jeweler: Jeweler,

    @SerializedName("mystic")
    var mystic: Mystic,

    @SerializedName("jewelerSeason")
    var jewelerSeason: JewelerSeason,

    @SerializedName("blacksmithSeason")
    var blacksmithSeason: BlacksmithSeason,

    @SerializedName("mysticSeason")
    var mysticSeason: MysticSeason,

    @SerializedName("blacksmithHardcore")
    var blacksmithHardcore: BlacksmithHardcore,

    @SerializedName("jewelerHardcore")
    var jewelerHardcore: JewelerHardcore,

    @SerializedName("mysticHardcore")
    var mysticHardcore: MysticHardcore,

    @SerializedName("blacksmithSeasonHardcore")
    var blacksmithSeasonHardcore: BlacksmithSeasonHardcore,

    @SerializedName("jewelerSeasonHardcore")
    var jewelerSeasonHardcore: JewelerSeasonHardcore,

    @SerializedName("mysticSeasonHardcore")
    var mysticSeasonHardcore: MysticSeasonHardcore
)