
package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeroSpecific____ {

    @SerializedName("selfHealing")
    @Expose
    private int selfHealing;
    @SerializedName("selfHealingAvgPer10Min")
    @Expose
    private int selfHealingAvgPer10Min;
    @SerializedName("selfHealingMostInGame")
    @Expose
    private int selfHealingMostInGame;

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
