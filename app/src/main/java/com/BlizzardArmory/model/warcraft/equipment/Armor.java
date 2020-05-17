package com.BlizzardArmory.model.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Armor.
 */
public class Armor {

    @SerializedName("value")
    @Expose
    private int value;
    @SerializedName("display_string")
    @Expose
    private String displayString;
    @SerializedName("display")
    @Expose
    private Display display;

    /**
     * Gets display.
     *
     * @return the display
     */
    public Display getDisplay() {
        return display;
    }

    /**
     * Sets display.
     *
     * @param display the display
     */
    public void setDisplay(Display display) {
        this.display = display;
    }

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
