
package com.BlizzardArmory.starcraft.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Campaign {

    @SerializedName("difficultyCompleted")
    @Expose
    private DifficultyCompleted difficultyCompleted;

    public DifficultyCompleted getDifficultyCompleted() {
        return difficultyCompleted;
    }

    public void setDifficultyCompleted(DifficultyCompleted difficultyCompleted) {
        this.difficultyCompleted = difficultyCompleted;
    }

}
