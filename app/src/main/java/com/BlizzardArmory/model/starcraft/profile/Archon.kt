package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Archon.
 */
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
        @Expose
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
        @Expose
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
        @Expose
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
        @Expose
        var totalWins: Int

)