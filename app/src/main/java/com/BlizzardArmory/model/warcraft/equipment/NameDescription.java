package com.BlizzardArmory.model.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Name description.
 */
public class NameDescription {

    @SerializedName("display_string")
    @Expose
    private String displayString;
    @SerializedName("color")
    @Expose
    private Color color;

    /**
     * Instantiates a new Name description.
     *
     * @param displayString the display string
     * @param color         the color
     */
    public NameDescription(String displayString, Color color) {
        this.displayString = displayString;
        this.color = color;
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
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }

}