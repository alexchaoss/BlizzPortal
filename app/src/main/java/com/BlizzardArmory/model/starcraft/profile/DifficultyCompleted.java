
package com.BlizzardArmory.model.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Difficulty completed.
 */
public class DifficultyCompleted {

    @SerializedName("wings-of-liberty")
    @Expose
    private String wingsOfLiberty;
    @SerializedName("heart-of-the-swarm")
    @Expose
    private String heartOfTheSwarm;
    @SerializedName("legacy-of-the-void")
    @Expose
    private String legacyOfTheVoid;

    /**
     * Gets wings of liberty.
     *
     * @return the wings of liberty
     */
    public String getWingsOfLiberty() {
        return wingsOfLiberty;
    }

    /**
     * Sets wings of liberty.
     *
     * @param wingsOfLiberty the wings of liberty
     */
    public void setWingsOfLiberty(String wingsOfLiberty) {
        this.wingsOfLiberty = wingsOfLiberty;
    }

    /**
     * Gets heart of the swarm.
     *
     * @return the heart of the swarm
     */
    public String getHeartOfTheSwarm() {
        return heartOfTheSwarm;
    }

    /**
     * Sets heart of the swarm.
     *
     * @param heartOfTheSwarm the heart of the swarm
     */
    public void setHeartOfTheSwarm(String heartOfTheSwarm) {
        this.heartOfTheSwarm = heartOfTheSwarm;
    }

    /**
     * Gets legacy of the void.
     *
     * @return the legacy of the void
     */
    public String getLegacyOfTheVoid() {
        return legacyOfTheVoid;
    }

    /**
     * Sets legacy of the void.
     *
     * @param legacyOfTheVoid the legacy of the void
     */
    public void setLegacyOfTheVoid(String legacyOfTheVoid) {
        this.legacyOfTheVoid = legacyOfTheVoid;
    }

}
