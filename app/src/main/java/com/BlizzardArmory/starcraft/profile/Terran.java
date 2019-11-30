
package com.BlizzardArmory.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Terran {

    @SerializedName("level")
    @Expose
    private int level;
    @SerializedName("maxLevelPoints")
    @Expose
    private int maxLevelPoints;
    @SerializedName("currentLevelPoints")
    @Expose
    private int currentLevelPoints;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxLevelPoints() {
        return maxLevelPoints;
    }

    public void setMaxLevelPoints(int maxLevelPoints) {
        this.maxLevelPoints = maxLevelPoints;
    }

    public int getCurrentLevelPoints() {
        return currentLevelPoints;
    }

    public void setCurrentLevelPoints(int currentLevelPoints) {
        this.currentLevelPoints = currentLevelPoints;
    }

}
