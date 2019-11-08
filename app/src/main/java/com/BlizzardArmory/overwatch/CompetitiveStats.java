
package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompetitiveStats {

    @SerializedName("eliminationsAvg")
    @Expose
    private float eliminationsAvg;
    @SerializedName("damageDoneAvg")
    @Expose
    private int damageDoneAvg;
    @SerializedName("deathsAvg")
    @Expose
    private float deathsAvg;
    @SerializedName("finalBlowsAvg")
    @Expose
    private float finalBlowsAvg;
    @SerializedName("healingDoneAvg")
    @Expose
    private int healingDoneAvg;
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
    private Games_ games;
    @SerializedName("awards")
    @Expose
    private Awards_ awards;

    public float getEliminationsAvg() {
        return eliminationsAvg;
    }

    public void setEliminationsAvg(float eliminationsAvg) {
        this.eliminationsAvg = eliminationsAvg;
    }

    public int getDamageDoneAvg() {
        return damageDoneAvg;
    }

    public void setDamageDoneAvg(int damageDoneAvg) {
        this.damageDoneAvg = damageDoneAvg;
    }

    public float getDeathsAvg() {
        return deathsAvg;
    }

    public void setDeathsAvg(float deathsAvg) {
        this.deathsAvg = deathsAvg;
    }

    public float getFinalBlowsAvg() {
        return finalBlowsAvg;
    }

    public void setFinalBlowsAvg(float finalBlowsAvg) {
        this.finalBlowsAvg = finalBlowsAvg;
    }

    public int getHealingDoneAvg() {
        return healingDoneAvg;
    }

    public void setHealingDoneAvg(int healingDoneAvg) {
        this.healingDoneAvg = healingDoneAvg;
    }

    public float getObjectiveKillsAvg() {
        return objectiveKillsAvg;
    }

    public void setObjectiveKillsAvg(float objectiveKillsAvg) {
        this.objectiveKillsAvg = objectiveKillsAvg;
    }

    public String getObjectiveTimeAvg() {
        return objectiveTimeAvg;
    }

    public void setObjectiveTimeAvg(String objectiveTimeAvg) {
        this.objectiveTimeAvg = objectiveTimeAvg;
    }

    public float getSoloKillsAvg() {
        return soloKillsAvg;
    }

    public void setSoloKillsAvg(float soloKillsAvg) {
        this.soloKillsAvg = soloKillsAvg;
    }

    public Games_ getGames() {
        return games;
    }

    public void setGames(Games_ games) {
        this.games = games;
    }

    public Awards_ getAwards() {
        return awards;
    }

    public void setAwards(Awards_ awards) {
        this.awards = awards;
    }

}