package com.BlizzardArmory.warcraft.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

public class GemInfo {

    @SerializedName("bonus")
    @Expose
    private Bonus bonus;
    @SerializedName("minItemLevel")
    @Expose
    private Integer minItemLevel;

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    public Integer getMinItemLevel() {
        return minItemLevel;
    }

    public void setMinItemLevel(Integer minItemLevel) {
        this.minItemLevel = minItemLevel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("bonus", bonus).append("minItemLevel", minItemLevel).toString();
    }

}