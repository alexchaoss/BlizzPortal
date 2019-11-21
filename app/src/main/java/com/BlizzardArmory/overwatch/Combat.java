package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Combat {

    @SerializedName("barrierDamageDone")
    @Expose
    private double barrierDamageDone;
    @SerializedName("damageDone")
    @Expose
    private double damageDone;
    @SerializedName("deaths")
    @Expose
    private double deaths;
    @SerializedName("eliminations")
    @Expose
    private double eliminations;
    @SerializedName("environmentalKills")
    @Expose
    private double environmentalKills;
    @SerializedName("finalBlows")
    @Expose
    private double finalBlows;
    @SerializedName("heroDamageDone")
    @Expose
    private double heroDamageDone;
    @SerializedName("meleeFinalBlows")
    @Expose
    private double meleeFinalBlows;
    @SerializedName("multikills")
    @Expose
    private double multikills;
    @SerializedName("objectiveKills")
    @Expose
    private double objectiveKills;
    @SerializedName("objectiveTime")
    @Expose
    private String objectiveTime;
    @SerializedName("soloKills")
    @Expose
    private double soloKills;
    @SerializedName("timeSpentOnFire")
    @Expose
    private String timeSpentOnFire;

    public double getBarrierDamageDone() {
        return barrierDamageDone;
    }

    public void setBarrierDamageDone(double barrierDamageDone) {
        this.barrierDamageDone = barrierDamageDone;
    }

    public double getDamageDone() {
        return damageDone;
    }

    public void setDamageDone(double damageDone) {
        this.damageDone = damageDone;
    }

    public double getDeaths() {
        return deaths;
    }

    public void setDeaths(double deaths) {
        this.deaths = deaths;
    }

    public double getEliminations() {
        return eliminations;
    }

    public void setEliminations(double eliminations) {
        this.eliminations = eliminations;
    }

    public double getEnvironmentalKills() {
        return environmentalKills;
    }

    public void setEnvironmentalKills(double environmentalKills) {
        this.environmentalKills = environmentalKills;
    }

    public double getFinalBlows() {
        return finalBlows;
    }

    public void setFinalBlows(double finalBlows) {
        this.finalBlows = finalBlows;
    }

    public double getHeroDamageDone() {
        return heroDamageDone;
    }

    public void setHeroDamageDone(double heroDamageDone) {
        this.heroDamageDone = heroDamageDone;
    }

    public double getMeleeFinalBlows() {
        return meleeFinalBlows;
    }

    public void setMeleeFinalBlows(double meleeFinalBlows) {
        this.meleeFinalBlows = meleeFinalBlows;
    }

    public double getMultikills() {
        return multikills;
    }

    public void setMultikills(double multikills) {
        this.multikills = multikills;
    }

    public double getObjectiveKills() {
        return objectiveKills;
    }

    public void setObjectiveKills(double objectiveKills) {
        this.objectiveKills = objectiveKills;
    }

    public String getObjectiveTime() {
        return objectiveTime;
    }

    public void setObjectiveTime(String objectiveTime) {
        this.objectiveTime = objectiveTime;
    }

    public double getSoloKills() {
        return soloKills;
    }

    public void setSoloKills(double soloKills) {
        this.soloKills = soloKills;
    }

    public String getTimeSpentOnFire() {
        return timeSpentOnFire;
    }

    public void setTimeSpentOnFire(String timeSpentOnFire) {
        this.timeSpentOnFire = timeSpentOnFire;
    }

}
