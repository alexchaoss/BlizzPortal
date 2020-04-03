package com.BlizzardArmory.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Damage.
 */
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

    /**
     * Gets min value.
     *
     * @return the min value
     */
    public int getMinValue() {
        return minValue;
    }

    /**
     * Sets min value.
     *
     * @param minValue the min value
     */
    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    /**
     * Gets max value.
     *
     * @return the max value
     */
    public int getMaxValue() {
        return maxValue;
    }

    /**
     * Sets max value.
     *
     * @param maxValue the max value
     */
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Gets display string.
     *
     * @return the display string
     */
    public String getDisplayString() {
        return displayString;
    }

    /**
     * Sets display string.
     *
     * @param displayString the display string
     */
    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

    /**
     * Gets damage class.
     *
     * @return the damage class
     */
    public DamageClass getDamageClass() {
        return damageClass;
    }

    /**
     * Sets damage class.
     *
     * @param damageClass the damage class
     */
    public void setDamageClass(DamageClass damageClass) {
        this.damageClass = damageClass;
    }

}
