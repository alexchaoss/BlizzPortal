
package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Combat___ {

    @SerializedName("criticalHits")
    @Expose
    private int criticalHits;
    @SerializedName("criticalHitsAccuracy")
    @Expose
    private String criticalHitsAccuracy;
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
    @SerializedName("objectiveTime")
    @Expose
    private String objectiveTime;
    @SerializedName("soloKills")
    @Expose
    private int soloKills;
    @SerializedName("weaponAccuracy")
    @Expose
    private String weaponAccuracy;

    public int getCriticalHits() {
        return criticalHits;
    }

    public void setCriticalHits(int criticalHits) {
        this.criticalHits = criticalHits;
    }

    public String getCriticalHitsAccuracy() {
        return criticalHitsAccuracy;
    }

    public void setCriticalHitsAccuracy(String criticalHitsAccuracy) {
        this.criticalHitsAccuracy = criticalHitsAccuracy;
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

    public int getFinalBlows() {
        return finalBlows;
    }

    public void setFinalBlows(int finalBlows) {
        this.finalBlows = finalBlows;
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

    public String getWeaponAccuracy() {
        return weaponAccuracy;
    }

    public void setWeaponAccuracy(String weaponAccuracy) {
        this.weaponAccuracy = weaponAccuracy;
    }

}
