package com.BlizzardArmory.model.starcraft.profile

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Archon.
 */
@Keep
data class Archon(
    /**
     * Gets rank.
     *
     * @return the rank
     */
    /**
     * Sets rank.
     *
     * @param rank the rank
     */
    @SerializedName("rank")
    var rank: Int,

    /**
     * Gets league name.
     *
     * @return the league name
     */
    /**
     * Sets league name.
     *
     * @param leagueName the league name
     */
    @SerializedName("leagueName")
    var leagueName: String,

    /**
     * Gets total games.
     *
     * @return the total games
     */
    /**
     * Sets total games.
     *
     * @param totalGames the total games
     */
    @SerializedName("totalGames")
    var totalGames: Int,

    /**
     * Gets total wins.
     *
     * @return the total wins
     */
    /**
     * Sets total wins.
     *
     * @param totalWins the total wins
     */
    @SerializedName("totalWins")
    var totalWins: Int

)