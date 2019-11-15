
package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Average__ {

    @SerializedName("allDamageDoneAvgPer10Min")
    @Expose
    private int allDamageDoneAvgPer10Min;
    @SerializedName("deathsAvgPer10Min")
    @Expose
    private float deathsAvgPer10Min;
    @SerializedName("healingDoneAvgPer10Min")
    @Expose
    private int healingDoneAvgPer10Min;
    @SerializedName("objectiveTimeAvgPer10Min")
    @Expose
    private String objectiveTimeAvgPer10Min;

    public int getAllDamageDoneAvgPer10Min() {
        return allDamageDoneAvgPer10Min;
    }

    public void setAllDamageDoneAvgPer10Min(int allDamageDoneAvgPer10Min) {
        this.allDamageDoneAvgPer10Min = allDamageDoneAvgPer10Min;
    }

    public float getDeathsAvgPer10Min() {
        return deathsAvgPer10Min;
    }

    public void setDeathsAvgPer10Min(float deathsAvgPer10Min) {
        this.deathsAvgPer10Min = deathsAvgPer10Min;
    }

    public int getHealingDoneAvgPer10Min() {
        return healingDoneAvgPer10Min;
    }

    public void setHealingDoneAvgPer10Min(int healingDoneAvgPer10Min) {
        this.healingDoneAvgPer10Min = healingDoneAvgPer10Min;
    }

    public String getObjectiveTimeAvgPer10Min() {
        return objectiveTimeAvgPer10Min;
    }

    public void setObjectiveTimeAvgPer10Min(String objectiveTimeAvgPer10Min) {
        this.objectiveTimeAvgPer10Min = objectiveTimeAvgPer10Min;
    }

}
