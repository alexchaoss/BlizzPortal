package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.SerializedName


/**
 * The type Player v player.
 */
open class PlayerVPlayer {
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
    var rank = 0

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
    var leagueName: String? = null

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
    var totalGames = 0

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
    var totalWins = 0

}