package com.BlizzardArmory.warcraft.Statistic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Intellect {

    @SerializedName("base")
    @Expose
    private int base;
    @SerializedName("effective")
    @Expose
    private int effective;

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getEffective() {
        return effective;
    }

    public void setEffective(int effective) {
        this.effective = effective;
    }

}