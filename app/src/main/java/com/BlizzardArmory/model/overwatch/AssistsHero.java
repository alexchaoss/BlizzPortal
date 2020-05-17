package com.BlizzardArmory.model.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Assists hero.
 */
public class AssistsHero {

    @SerializedName("defensiveAssists")
    @Expose
    private double defensiveAssists;
    @SerializedName("defensiveAssistsAvgPer10Min")
    @Expose
    private float defensiveAssistsAvgPer10Min;
    @SerializedName("defensiveAssistsMostInGame")
    @Expose
    private double defensiveAssistsMostInGame;
    @SerializedName("healingDone")
    @Expose
    private double healingDone;
    @SerializedName("healingDoneAvgPer10Min")
    @Expose
    private double healingDoneAvgPer10Min;
    @SerializedName("healingDoneMostInGame")
    @Expose
    private double healingDoneMostInGame;
    @SerializedName("offensiveAssists")
    @Expose
    private double offensiveAssists;
    @SerializedName("offensiveAssistsAvgPer10Min")
    @Expose
    private float offensiveAssistsAvgPer10Min;
    @SerializedName("offensiveAssistsMostInGame")
    @Expose
    private double offensiveAssistsMostInGame;

    /**
     * Gets defensive assists.
     *
     * @return the defensive assists
     */
    public double getDefensiveAssists() {
        return defensiveAssists;
    }

    /**
     * Sets defensive assists.
     *
     * @param defensiveAssists the defensive assists
     */
    public void setDefensiveAssists(double defensiveAssists) {
        this.defensiveAssists = defensiveAssists;
    }

    /**
     * Gets defensive assists avg per 10 min.
     *
     * @return the defensive assists avg per 10 min
     */
    public float getDefensiveAssistsAvgPer10Min() {
        return defensiveAssistsAvgPer10Min;
    }

    /**
     * Sets defensive assists avg per 10 min.
     *
     * @param defensiveAssistsAvgPer10Min the defensive assists avg per 10 min
     */
    public void setDefensiveAssistsAvgPer10Min(float defensiveAssistsAvgPer10Min) {
        this.defensiveAssistsAvgPer10Min = defensiveAssistsAvgPer10Min;
    }

    /**
     * Gets defensive assists most in game.
     *
     * @return the defensive assists most in game
     */
    public double getDefensiveAssistsMostInGame() {
        return defensiveAssistsMostInGame;
    }

    /**
     * Sets defensive assists most in game.
     *
     * @param defensiveAssistsMostInGame the defensive assists most in game
     */
    public void setDefensiveAssistsMostInGame(double defensiveAssistsMostInGame) {
        this.defensiveAssistsMostInGame = defensiveAssistsMostInGame;
    }

    /**
     * Gets healing done.
     *
     * @return the healing done
     */
    public double getHealingDone() {
        return healingDone;
    }

    /**
     * Sets healing done.
     *
     * @param healingDone the healing done
     */
    public void setHealingDone(double healingDone) {
        this.healingDone = healingDone;
    }

    /**
     * Gets healing done avg per 10 min.
     *
     * @return the healing done avg per 10 min
     */
    public double getHealingDoneAvgPer10Min() {
        return healingDoneAvgPer10Min;
    }

    /**
     * Sets healing done avg per 10 min.
     *
     * @param healingDoneAvgPer10Min the healing done avg per 10 min
     */
    public void setHealingDoneAvgPer10Min(double healingDoneAvgPer10Min) {
        this.healingDoneAvgPer10Min = healingDoneAvgPer10Min;
    }

    /**
     * Gets healing done most in game.
     *
     * @return the healing done most in game
     */
    public double getHealingDoneMostInGame() {
        return healingDoneMostInGame;
    }

    /**
     * Sets healing done most in game.
     *
     * @param healingDoneMostInGame the healing done most in game
     */
    public void setHealingDoneMostInGame(double healingDoneMostInGame) {
        this.healingDoneMostInGame = healingDoneMostInGame;
    }

    /**
     * Gets offensive assists.
     *
     * @return the offensive assists
     */
    public double getOffensiveAssists() {
        return offensiveAssists;
    }

    /**
     * Sets offensive assists.
     *
     * @param offensiveAssists the offensive assists
     */
    public void setOffensiveAssists(double offensiveAssists) {
        this.offensiveAssists = offensiveAssists;
    }

    /**
     * Gets offensive assists avg per 10 min.
     *
     * @return the offensive assists avg per 10 min
     */
    public float getOffensiveAssistsAvgPer10Min() {
        return offensiveAssistsAvgPer10Min;
    }

    /**
     * Sets offensive assists avg per 10 min.
     *
     * @param offensiveAssistsAvgPer10Min the offensive assists avg per 10 min
     */
    public void setOffensiveAssistsAvgPer10Min(float offensiveAssistsAvgPer10Min) {
        this.offensiveAssistsAvgPer10Min = offensiveAssistsAvgPer10Min;
    }

    /**
     * Gets offensive assists most in game.
     *
     * @return the offensive assists most in game
     */
    public double getOffensiveAssistsMostInGame() {
        return offensiveAssistsMostInGame;
    }

    /**
     * Sets offensive assists most in game.
     *
     * @param offensiveAssistsMostInGame the offensive assists most in game
     */
    public void setOffensiveAssistsMostInGame(double offensiveAssistsMostInGame) {
        this.offensiveAssistsMostInGame = offensiveAssistsMostInGame;
    }

}
