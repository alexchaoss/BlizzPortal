package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

public class Assists {

    @SerializedName("defensiveAssists")
    @Expose
    private Double defensiveAssists = (double) 0;
    @SerializedName("healingDone")
    @Expose
    private Double healingDone = (double) 0;
    @SerializedName("offensiveAssists")
    @Expose
    private Double offensiveAssists = (double) 0;
    @SerializedName("reconAssists")
    @Expose
    private Double reconAssists = (double) 0;

    public Double getDefensiveAssists() {
        return defensiveAssists;
    }

    public void setDefensiveAssists(Double defensiveAssists) {
        this.defensiveAssists = defensiveAssists;
    }

    public Double getHealingDone() {
        return healingDone;
    }

    public void setHealingDone(Double healingDone) {
        this.healingDone = healingDone;
    }

    public Double getOffensiveAssists() {
        return offensiveAssists;
    }

    public void setOffensiveAssists(Double offensiveAssists) {
        this.offensiveAssists = offensiveAssists;
    }

    public Double getReconAssists() {
        return reconAssists;
    }

    public void setReconAssists(Double reconAssists) {
        this.reconAssists = reconAssists;
    }

    public HashMap<String, String> getAssists() {
        HashMap<String, String> assistList = new HashMap<>();
        NumberFormat formatter = new DecimalFormat("#0");

        assistList.put("DEFENSIVE ASSISTS", formatter.format(defensiveAssists));
        assistList.put("HEALING DONE", formatter.format(healingDone));
        assistList.put("OFFENSIVE ASSISTS", formatter.format(offensiveAssists));
        assistList.put("RECON ASSISTS", formatter.format(reconAssists));

        return assistList;
    }

}
