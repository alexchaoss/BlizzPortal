
package com.BlizzardArmory.model.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Zerg.
 */
public class Zerg {

    @SerializedName("level")
    @Expose
    private int level;
    @SerializedName("maxLevelPoints")
    @Expose
    private int maxLevelPoints;
    @SerializedName("currentLevelPoints")
    @Expose
    private int currentLevelPoints;

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets max level points.
     *
     * @return the max level points
     */
    public int getMaxLevelPoints() {
        return maxLevelPoints;
    }

    /**
     * Sets max level points.
     *
     * @param maxLevelPoints the max level points
     */
    public void setMaxLevelPoints(int maxLevelPoints) {
        this.maxLevelPoints = maxLevelPoints;
    }

    /**
     * Gets current level points.
     *
     * @return the current level points
     */
    public int getCurrentLevelPoints() {
        return currentLevelPoints;
    }

    /**
     * Sets current level points.
     *
     * @param currentLevelPoints the current level points
     */
    public void setCurrentLevelPoints(int currentLevelPoints) {
        this.currentLevelPoints = currentLevelPoints;
    }

}
