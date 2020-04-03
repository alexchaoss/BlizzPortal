package com.BlizzardArmory.overwatch.topheroes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Top hero.
 */
public class TopHero {

    @SerializedName("timePlayed")
    @Expose
    private String timePlayed;
    @SerializedName("gamesWon")
    @Expose
    private double gamesWon;
    @SerializedName("winPercentage")
    @Expose
    private double winPercentage;
    @SerializedName("weaponAccuracy")
    @Expose
    private double weaponAccuracy;
    @SerializedName("eliminationsPerLife")
    @Expose
    private float eliminationsPerLife;
    @SerializedName("multiKillBest")
    @Expose
    private double multiKillBest;
    @SerializedName("objectiveKills")
    @Expose
    private double objectiveKills;

    /**
     * Gets time played.
     *
     * @return the time played
     */
    public String getTimePlayed() {
        return timePlayed;
    }

    /**
     * Sets time played.
     *
     * @param timePlayed the time played
     */
    public void setTimePlayed(String timePlayed) {
        this.timePlayed = timePlayed;
    }

    /**
     * Gets games won.
     *
     * @return the games won
     */
    public double getGamesWon() {
        return gamesWon;
    }

    /**
     * Sets games won.
     *
     * @param gamesWon the games won
     */
    public void setGamesWon(double gamesWon) {
        this.gamesWon = gamesWon;
    }

    /**
     * Gets win percentage.
     *
     * @return the win percentage
     */
    public double getWinPercentage() {
        return winPercentage;
    }

    /**
     * Sets win percentage.
     *
     * @param winPercentage the win percentage
     */
    public void setWinPercentage(double winPercentage) {
        this.winPercentage = winPercentage;
    }

    /**
     * Gets weapon accuracy.
     *
     * @return the weapon accuracy
     */
    public double getWeaponAccuracy() {
        return weaponAccuracy;
    }

    /**
     * Sets weapon accuracy.
     *
     * @param weaponAccuracy the weapon accuracy
     */
    public void setWeaponAccuracy(double weaponAccuracy) {
        this.weaponAccuracy = weaponAccuracy;
    }

    /**
     * Gets eliminations per life.
     *
     * @return the eliminations per life
     */
    public float getEliminationsPerLife() {
        return eliminationsPerLife;
    }

    /**
     * Sets eliminations per life.
     *
     * @param eliminationsPerLife the eliminations per life
     */
    public void setEliminationsPerLife(float eliminationsPerLife) {
        this.eliminationsPerLife = eliminationsPerLife;
    }

    /**
     * Gets multi kill best.
     *
     * @return the multi kill best
     */
    public double getMultiKillBest() {
        return multiKillBest;
    }

    /**
     * Sets multi kill best.
     *
     * @param multiKillBest the multi kill best
     */
    public void setMultiKillBest(double multiKillBest) {
        this.multiKillBest = multiKillBest;
    }

    /**
     * Gets objective kills.
     *
     * @return the objective kills
     */
    public double getObjectiveKills() {
        return objectiveKills;
    }

    /**
     * Sets objective kills.
     *
     * @param objectiveKills the objective kills
     */
    public void setObjectiveKills(double objectiveKills) {
        this.objectiveKills = objectiveKills;
    }
}
