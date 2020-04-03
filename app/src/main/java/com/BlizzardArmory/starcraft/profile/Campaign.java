
package com.BlizzardArmory.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Campaign.
 */
public class Campaign {

    @SerializedName("difficultyCompleted")
    @Expose
    private DifficultyCompleted difficultyCompleted;

    /**
     * Gets difficulty completed.
     *
     * @return the difficulty completed
     */
    public DifficultyCompleted getDifficultyCompleted() {
        return difficultyCompleted;
    }

    /**
     * Sets difficulty completed.
     *
     * @param difficultyCompleted the difficulty completed
     */
    public void setDifficultyCompleted(DifficultyCompleted difficultyCompleted) {
        this.difficultyCompleted = difficultyCompleted;
    }

}
