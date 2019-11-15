
package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Assists_ {

    @SerializedName("healingDone")
    @Expose
    private int healingDone;
    @SerializedName("healingDoneAvgPer10Min")
    @Expose
    private int healingDoneAvgPer10Min;
    @SerializedName("healingDoneMostInGame")
    @Expose
    private int healingDoneMostInGame;

    public int getHealingDone() {
        return healingDone;
    }

    public void setHealingDone(int healingDone) {
        this.healingDone = healingDone;
    }

    public int getHealingDoneAvgPer10Min() {
        return healingDoneAvgPer10Min;
    }

    public void setHealingDoneAvgPer10Min(int healingDoneAvgPer10Min) {
        this.healingDoneAvgPer10Min = healingDoneAvgPer10Min;
    }

    public int getHealingDoneMostInGame() {
        return healingDoneMostInGame;
    }

    public void setHealingDoneMostInGame(int healingDoneMostInGame) {
        this.healingDoneMostInGame = healingDoneMostInGame;
    }

}
