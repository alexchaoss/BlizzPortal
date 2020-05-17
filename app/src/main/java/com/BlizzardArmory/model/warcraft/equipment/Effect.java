package com.BlizzardArmory.model.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Effect.
 */
public class Effect {

    @SerializedName("display_string")
    @Expose
    private String displayString;
    @SerializedName("required_count")
    @Expose
    private int requiredCount;

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
     * Gets required count.
     *
     * @return the required count
     */
    public int getRequiredCount() {
        return requiredCount;
    }

    /**
     * Sets required count.
     *
     * @param requiredCount the required count
     */
    public void setRequiredCount(int requiredCount) {
        this.requiredCount = requiredCount;
    }

}
