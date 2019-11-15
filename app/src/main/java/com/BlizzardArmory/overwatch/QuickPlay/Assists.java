
package com.BlizzardArmory.overwatch.QuickPlay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Assists {

    @SerializedName("defensiveAssists")
    @Expose
    private int defensiveAssists;
    @SerializedName("healingDone")
    @Expose
    private int healingDone;
    @SerializedName("offensiveAssists")
    @Expose
    private int offensiveAssists;
    @SerializedName("reconAssists")
    @Expose
    private int reconAssists;

    public int getDefensiveAssists() {
        return defensiveAssists;
    }

    public void setDefensiveAssists(int defensiveAssists) {
        this.defensiveAssists = defensiveAssists;
    }

    public int getHealingDone() {
        return healingDone;
    }

    public void setHealingDone(int healingDone) {
        this.healingDone = healingDone;
    }

    public int getOffensiveAssists() {
        return offensiveAssists;
    }

    public void setOffensiveAssists(int offensiveAssists) {
        this.offensiveAssists = offensiveAssists;
    }

    public int getReconAssists() {
        return reconAssists;
    }

    public void setReconAssists(int reconAssists) {
        this.reconAssists = reconAssists;
    }

}
