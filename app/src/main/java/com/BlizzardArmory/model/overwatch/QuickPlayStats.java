package com.BlizzardArmory.model.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Quick play stats.
 */
public class QuickPlayStats {

    @SerializedName("eliminationsAvg")
    @Expose
    private float eliminationsAvg;
    @SerializedName("damageDoneAvg")
    @Expose
    private double damageDoneAvg;
    @SerializedName("deathsAvg")
    @Expose
    private float deathsAvg;
    @SerializedName("finalBlowsAvg")
    @Expose
    private float finalBlowsAvg;
    @SerializedName("healingDoneAvg")
    @Expose
    private double healingDoneAvg;
    @SerializedName("objectiveKillsAvg")
    @Expose
    private float objectiveKillsAvg;
    @SerializedName("objectiveTimeAvg")
    @Expose
    private String objectiveTimeAvg;
    @SerializedName("soloKillsAvg")
    @Expose
    private float soloKillsAvg;
    @SerializedName("games")
    @Expose
    private Games games;
    @SerializedName("awards")
    @Expose
    private Awards awards;

    /**
     * Gets eliminations avg.
     *
     * @return the eliminations avg
     */
    public float getEliminationsAvg() {
        return eliminationsAvg;
    }

    /**
     * Sets eliminations avg.
     *
     * @param eliminationsAvg the eliminations avg
     */
    public void setEliminationsAvg(float eliminationsAvg) {
        this.eliminationsAvg = eliminationsAvg;
    }

    /**
     * Gets damage done avg.
     *
     * @return the damage done avg
     */
    public double getDamageDoneAvg() {
        return damageDoneAvg;
    }

    /**
     * Sets damage done avg.
     *
     * @param damageDoneAvg the damage done avg
     */
    public void setDamageDoneAvg(double damageDoneAvg) {
        this.damageDoneAvg = damageDoneAvg;
    }

    /**
     * Gets deaths avg.
     *
     * @return the deaths avg
     */
    public float getDeathsAvg() {
        return deathsAvg;
    }

    /**
     * Sets deaths avg.
     *
     * @param deathsAvg the deaths avg
     */
    public void setDeathsAvg(float deathsAvg) {
        this.deathsAvg = deathsAvg;
    }

    /**
     * Gets final blows avg.
     *
     * @return the final blows avg
     */
    public float getFinalBlowsAvg() {
        return finalBlowsAvg;
    }

    /**
     * Sets final blows avg.
     *
     * @param finalBlowsAvg the final blows avg
     */
    public void setFinalBlowsAvg(float finalBlowsAvg) {
        this.finalBlowsAvg = finalBlowsAvg;
    }

    /**
     * Gets healing done avg.
     *
     * @return the healing done avg
     */
    public double getHealingDoneAvg() {
        return healingDoneAvg;
    }

    /**
     * Sets healing done avg.
     *
     * @param healingDoneAvg the healing done avg
     */
    public void setHealingDoneAvg(double healingDoneAvg) {
        this.healingDoneAvg = healingDoneAvg;
    }

    /**
     * Gets objective kills avg.
     *
     * @return the objective kills avg
     */
    public float getObjectiveKillsAvg() {
        return objectiveKillsAvg;
    }

    /**
     * Sets objective kills avg.
     *
     * @param objectiveKillsAvg the objective kills avg
     */
    public void setObjectiveKillsAvg(float objectiveKillsAvg) {
        this.objectiveKillsAvg = objectiveKillsAvg;
    }

    /**
     * Gets objective time avg.
     *
     * @return the objective time avg
     */
    public String getObjectiveTimeAvg() {
        return objectiveTimeAvg;
    }

    /**
     * Sets objective time avg.
     *
     * @param objectiveTimeAvg the objective time avg
     */
    public void setObjectiveTimeAvg(String objectiveTimeAvg) {
        this.objectiveTimeAvg = objectiveTimeAvg;
    }

    /**
     * Gets solo kills avg.
     *
     * @return the solo kills avg
     */
    public float getSoloKillsAvg() {
        return soloKillsAvg;
    }

    /**
     * Sets solo kills avg.
     *
     * @param soloKillsAvg the solo kills avg
     */
    public void setSoloKillsAvg(float soloKillsAvg) {
        this.soloKillsAvg = soloKillsAvg;
    }

    /**
     * Gets games.
     *
     * @return the games
     */
    public Games getGames() {
        return games;
    }

    /**
     * Sets games.
     *
     * @param games the games
     */
    public void setGames(Games games) {
        this.games = games;
    }

    /**
     * Gets awards.
     *
     * @return the awards
     */
    public Awards getAwards() {
        return awards;
    }

    /**
     * Sets awards.
     *
     * @param awards the awards
     */
    public void setAwards(Awards awards) {
        this.awards = awards;
    }

}
