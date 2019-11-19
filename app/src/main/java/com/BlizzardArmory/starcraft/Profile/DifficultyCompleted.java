
package com.BlizzardArmory.starcraft.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    public String getWingsOfLiberty() {
        return wingsOfLiberty;
    }

    public void setWingsOfLiberty(String wingsOfLiberty) {
        this.wingsOfLiberty = wingsOfLiberty;
    }

    public String getHeartOfTheSwarm() {
        return heartOfTheSwarm;
    }

    public void setHeartOfTheSwarm(String heartOfTheSwarm) {
        this.heartOfTheSwarm = heartOfTheSwarm;
    }

    public String getLegacyOfTheVoid() {
        return legacyOfTheVoid;
    }

    public void setLegacyOfTheVoid(String legacyOfTheVoid) {
        this.legacyOfTheVoid = legacyOfTheVoid;
    }

}
