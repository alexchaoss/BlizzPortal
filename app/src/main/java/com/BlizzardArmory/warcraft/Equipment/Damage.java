package com.BlizzardArmory.warcraft.Equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Damage {

    @SerializedName("min_value")
    @Expose
    private int minValue;
    @SerializedName("max_value")
    @Expose
    private int maxValue;
    @SerializedName("display_string")
    @Expose
    private String displayString;
    @SerializedName("damage_class")
    @Expose
    private DamageClass damageClass;

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public String getDisplayString() {
        return displayString;
    }

    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

    public DamageClass getDamageClass() {
        return damageClass;
    }

    public void setDamageClass(DamageClass damageClass) {
        this.damageClass = damageClass;
    }

}
