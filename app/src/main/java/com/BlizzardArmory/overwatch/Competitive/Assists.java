package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Assists {

    @SerializedName("healingDone")
    @Expose
    private int healingDone;

    public int getHealingDone() {
        return healingDone;
    }

    public void setHealingDone(int healingDone) {
        this.healingDone = healingDone;
    }

}
