package com.BlizzardArmory.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Faction.
 */
public class Faction {

    @SerializedName("value")
    @Expose
    private Value value;
    @SerializedName("display_string")
    @Expose
    private String displayString;

    /**
     * Gets value.
     *
     * @return the value
     */
    public Value getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(Value value) {
        this.value = value;
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

}
