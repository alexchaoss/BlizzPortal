package com.BlizzardArmory.model.overwatch.topheroes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Top hero.
 */
open class TopHero {
    /**
     * Gets time played.
     *
     * @return the time played
     */
    /**
     * Sets time played.
     *
     * @param timePlayed the time played
     */
    @SerializedName("timePlayed")
    @Expose
    var timePlayed: String? = null

    /**
     * Gets games won.
     *
     * @return the games won
     */
    /**
     * Sets games won.
     *
     * @param gamesWon the games won
     */
    @SerializedName("gamesWon")
    @Expose
    var gamesWon = 0.0

    /**
     * Gets win percentage.
     *
     * @return the win percentage
     */
    /**
     * Sets win percentage.
     *
     * @param winPercentage the win percentage
     */
    @SerializedName("winPercentage")
    @Expose
    var winPercentage = 0.0

    /**
     * Gets weapon accuracy.
     *
     * @return the weapon accuracy
     */
    /**
     * Sets weapon accuracy.
     *
     * @param weaponAccuracy the weapon accuracy
     */
    @SerializedName("weaponAccuracy")
    @Expose
    var weaponAccuracy = 0.0

    /**
     * Gets eliminations per life.
     *
     * @return the eliminations per life
     */
    /**
     * Sets eliminations per life.
     *
     * @param eliminationsPerLife the eliminations per life
     */
    @SerializedName("eliminationsPerLife")
    @Expose
    var eliminationsPerLife = 0f

    /**
     * Gets multi kill best.
     *
     * @return the multi kill best
     */
    /**
     * Sets multi kill best.
     *
     * @param multiKillBest the multi kill best
     */
    @SerializedName("multiKillBest")
    @Expose
    var multiKillBest = 0.0

    /**
     * Gets objective kills.
     *
     * @return the objective kills
     */
    /**
     * Sets objective kills.
     *
     * @param objectiveKills the objective kills
     */
    @SerializedName("objectiveKills")
    @Expose
    var objectiveKills = 0.0

}