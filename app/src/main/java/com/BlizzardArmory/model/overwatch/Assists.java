package com.BlizzardArmory.model.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

/**
 * The type Assists.
 */
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

    /**
     * Gets defensive assists.
     *
     * @return the defensive assists
     */
    public Double getDefensiveAssists() {
        return defensiveAssists;
    }

    /**
     * Sets defensive assists.
     *
     * @param defensiveAssists the defensive assists
     */
    public void setDefensiveAssists(Double defensiveAssists) {
        this.defensiveAssists = defensiveAssists;
    }

    /**
     * Gets healing done.
     *
     * @return the healing done
     */
    public Double getHealingDone() {
        return healingDone;
    }

    /**
     * Sets healing done.
     *
     * @param healingDone the healing done
     */
    public void setHealingDone(Double healingDone) {
        this.healingDone = healingDone;
    }

    /**
     * Gets offensive assists.
     *
     * @return the offensive assists
     */
    public Double getOffensiveAssists() {
        return offensiveAssists;
    }

    /**
     * Sets offensive assists.
     *
     * @param offensiveAssists the offensive assists
     */
    public void setOffensiveAssists(Double offensiveAssists) {
        this.offensiveAssists = offensiveAssists;
    }

    /**
     * Gets recon assists.
     *
     * @return the recon assists
     */
    public Double getReconAssists() {
        return reconAssists;
    }

    /**
     * Sets recon assists.
     *
     * @param reconAssists the recon assists
     */
    public void setReconAssists(Double reconAssists) {
        this.reconAssists = reconAssists;
    }

    /**
     * Gets assists.
     *
     * @return the assists
     */
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
