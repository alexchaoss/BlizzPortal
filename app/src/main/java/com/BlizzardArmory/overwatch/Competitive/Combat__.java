
package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Combat__ {

    @SerializedName("damageDone")
    @Expose
    private int damageDone;
    @SerializedName("deaths")
    @Expose
    private int deaths;
    @SerializedName("objectiveTime")
    @Expose
    private String objectiveTime;
    @SerializedName("weaponAccuracy")
    @Expose
    private String weaponAccuracy;

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

    public String getObjectiveTime() {
        return objectiveTime;
    }

    public void setObjectiveTime(String objectiveTime) {
        this.objectiveTime = objectiveTime;
    }

    public String getWeaponAccuracy() {
        return weaponAccuracy;
    }

    public void setWeaponAccuracy(String weaponAccuracy) {
        this.weaponAccuracy = weaponAccuracy;
    }

}
