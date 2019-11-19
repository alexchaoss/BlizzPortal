package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Combat {

    @SerializedName("damageDone")
    @Expose
    private int damageDone;
    @SerializedName("deaths")
    @Expose
    private int deaths;
    @SerializedName("eliminations")
    @Expose
    private int eliminations;
    @SerializedName("finalBlows")
    @Expose
    private int finalBlows;
    @SerializedName("meleeFinalBlows")
    @Expose
    private int meleeFinalBlows;
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

    public int getFinalBlows() {
        return finalBlows;
    }

    public void setFinalBlows(int finalBlows) {
        this.finalBlows = finalBlows;
    }

    public int getMeleeFinalBlows() {
        return meleeFinalBlows;
    }

    public void setMeleeFinalBlows(int meleeFinalBlows) {
        this.meleeFinalBlows = meleeFinalBlows;
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
