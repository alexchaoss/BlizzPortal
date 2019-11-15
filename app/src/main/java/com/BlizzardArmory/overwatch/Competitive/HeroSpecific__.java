
package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeroSpecific__ {

    @SerializedName("enemiesFrozen")
    @Expose
    private int enemiesFrozen;
    @SerializedName("enemiesFrozenAvgPer10Min")
    @Expose
    private float enemiesFrozenAvgPer10Min;
    @SerializedName("enemiesFrozenMostInGame")
    @Expose
    private int enemiesFrozenMostInGame;
    @SerializedName("selfHealing")
    @Expose
    private int selfHealing;
    @SerializedName("selfHealingAvgPer10Min")
    @Expose
    private int selfHealingAvgPer10Min;
    @SerializedName("selfHealingMostInGame")
    @Expose
    private int selfHealingMostInGame;

    public int getEnemiesFrozen() {
        return enemiesFrozen;
    }

    public void setEnemiesFrozen(int enemiesFrozen) {
        this.enemiesFrozen = enemiesFrozen;
    }

    public float getEnemiesFrozenAvgPer10Min() {
        return enemiesFrozenAvgPer10Min;
    }

    public void setEnemiesFrozenAvgPer10Min(float enemiesFrozenAvgPer10Min) {
        this.enemiesFrozenAvgPer10Min = enemiesFrozenAvgPer10Min;
    }

    public int getEnemiesFrozenMostInGame() {
        return enemiesFrozenMostInGame;
    }

    public void setEnemiesFrozenMostInGame(int enemiesFrozenMostInGame) {
        this.enemiesFrozenMostInGame = enemiesFrozenMostInGame;
    }

    public int getSelfHealing() {
        return selfHealing;
    }

    public void setSelfHealing(int selfHealing) {
        this.selfHealing = selfHealing;
    }

    public int getSelfHealingAvgPer10Min() {
        return selfHealingAvgPer10Min;
    }

    public void setSelfHealingAvgPer10Min(int selfHealingAvgPer10Min) {
        this.selfHealingAvgPer10Min = selfHealingAvgPer10Min;
    }

    public int getSelfHealingMostInGame() {
        return selfHealingMostInGame;
    }

    public void setSelfHealingMostInGame(int selfHealingMostInGame) {
        this.selfHealingMostInGame = selfHealingMostInGame;
    }

}
