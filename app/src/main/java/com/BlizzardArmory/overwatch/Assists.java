package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Assists {

    @SerializedName("defensiveAssists")
    @Expose
    private double defensiveAssists;
    @SerializedName("healingDone")
    @Expose
    private double healingDone;
    @SerializedName("offensiveAssists")
    @Expose
    private double offensiveAssists;
    @SerializedName("reconAssists")
    @Expose
    private double reconAssists;

    public double getDefensiveAssists() {
        return defensiveAssists;
    }

    public void setDefensiveAssists(double defensiveAssists) {
        this.defensiveAssists = defensiveAssists;
    }

    public double getHealingDone() {
        return healingDone;
    }

    public void setHealingDone(double healingDone) {
        this.healingDone = healingDone;
    }

    public double getOffensiveAssists() {
        return offensiveAssists;
    }

    public void setOffensiveAssists(double offensiveAssists) {
        this.offensiveAssists = offensiveAssists;
    }

    public double getReconAssists() {
        return reconAssists;
    }

    public void setReconAssists(double reconAssists) {
        this.reconAssists = reconAssists;
    }

}
