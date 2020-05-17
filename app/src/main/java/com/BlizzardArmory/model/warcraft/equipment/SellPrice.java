package com.BlizzardArmory.model.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Sell price.
 */
public class SellPrice {

    @SerializedName("value")
    @Expose
    private int value;
    @SerializedName("display_strings")
    @Expose
    private DisplayStrings displayStrings;

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Gets display strings.
     *
     * @return the display strings
     */
    public DisplayStrings getDisplayStrings() {
        return displayStrings;
    }

    /**
     * Sets display strings.
     *
     * @param displayStrings the display strings
     */
    public void setDisplayStrings(DisplayStrings displayStrings) {
        this.displayStrings = displayStrings;
    }

}
