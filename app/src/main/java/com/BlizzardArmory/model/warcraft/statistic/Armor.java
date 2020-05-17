package com.BlizzardArmory.model.warcraft.statistic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Armor.
 */
public class Armor {

    @SerializedName("base")
    @Expose
    private int base;
    @SerializedName("effective")
    @Expose
    private int effective;

    /**
     * Gets base.
     *
     * @return the base
     */
    public int getBase() {
        return base;
    }

    /**
     * Sets base.
     *
     * @param base the base
     */
    public void setBase(int base) {
        this.base = base;
    }

    /**
     * Gets effective.
     *
     * @return the effective
     */
    public int getEffective() {
        return effective;
    }

    /**
     * Sets effective.
     *
     * @param effective the effective
     */
    public void setEffective(int effective) {
        this.effective = effective;
    }

}