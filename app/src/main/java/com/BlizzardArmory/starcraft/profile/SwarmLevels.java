
package com.BlizzardArmory.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SwarmLevels {

    @SerializedName("level")
    @Expose
    private int level;
    @SerializedName("terran")
    @Expose
    private Terran terran;
    @SerializedName("zerg")
    @Expose
    private Zerg zerg;
    @SerializedName("protoss")
    @Expose
    private Protoss protoss;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Terran getTerran() {
        return terran;
    }

    public void setTerran(Terran terran) {
        this.terran = terran;
    }

    public Zerg getZerg() {
        return zerg;
    }

    public void setZerg(Zerg zerg) {
        this.zerg = zerg;
    }

    public Protoss getProtoss() {
        return protoss;
    }

    public void setProtoss(Protoss protoss) {
        this.protoss = protoss;
    }

}
