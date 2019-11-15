
package com.BlizzardArmory.overwatch.QuickPlay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Combat {

    @SerializedName("barrierDamageDone")
    @Expose
    private int barrierDamageDone;
    @SerializedName("damageDone")
    @Expose
    private int damageDone;
    @SerializedName("deaths")
    @Expose
    private int deaths;
    @SerializedName("eliminations")
    @Expose
    private int eliminations;
    @SerializedName("environmentalKills")
    @Expose
    private int environmentalKills;
    @SerializedName("finalBlows")
    @Expose
    private int finalBlows;
    @SerializedName("heroDamageDone")
    @Expose
    private int heroDamageDone;
    @SerializedName("meleeFinalBlows")
    @Expose
    private int meleeFinalBlows;
    @SerializedName("multikills")
    @Expose
    private int multikills;
    @SerializedName("objectiveKills")
    @Expose
    private int objectiveKills;
    @SerializedName("objectiveTime")
    @Expose
    private String objectiveTime;
    @SerializedName("soloKills")
    @Expose
    private int soloKills;
    @SerializedName("timeSpentOnFire")
    @Expose
    private String timeSpentOnFire;

    public int getBarrierDamageDone() {
        return barrierDamageDone;
    }

    public void setBarrierDamageDone(int barrierDamageDone) {
        this.barrierDamageDone = barrierDamageDone;
    }

    public int getDamageDone() {
        return damageDone;
    }

    public void setDamageDone(int damageDone) {
        this.damageDone = damageDone;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getEliminations() {
        return eliminations;
    }

    public void setEliminations(int eliminations) {
        this.eliminations = eliminations;
    }

    public int getEnvironmentalKills() {
        return environmentalKills;
    }

    public void setEnvironmentalKills(int environmentalKills) {
        this.environmentalKills = environmentalKills;
    }

    public int getFinalBlows() {
        return finalBlows;
    }

    public void setFinalBlows(int finalBlows) {
        this.finalBlows = finalBlows;
    }

    public int getHeroDamageDone() {
        return heroDamageDone;
    }

    public void setHeroDamageDone(int heroDamageDone) {
        this.heroDamageDone = heroDamageDone;
    }

    public int getMeleeFinalBlows() {
        return meleeFinalBlows;
    }

    public void setMeleeFinalBlows(int meleeFinalBlows) {
        this.meleeFinalBlows = meleeFinalBlows;
    }

    public int getMultikills() {
        return multikills;
    }

    public void setMultikills(int multikills) {
        this.multikills = multikills;
    }

    public int getObjectiveKills() {
        return objectiveKills;
    }

    public void setObjectiveKills(int objectiveKills) {
        this.objectiveKills = objectiveKills;
    }

    public String getObjectiveTime() {
        return objectiveTime;
    }

    public void setObjectiveTime(String objectiveTime) {
        this.objectiveTime = objectiveTime;
    }

    public int getSoloKills() {
        return soloKills;
    }

    public void setSoloKills(int soloKills) {
        this.soloKills = soloKills;
    }

    public String getTimeSpentOnFire() {
        return timeSpentOnFire;
    }

    public void setTimeSpentOnFire(String timeSpentOnFire) {
        this.timeSpentOnFire = timeSpentOnFire;
    }

}
