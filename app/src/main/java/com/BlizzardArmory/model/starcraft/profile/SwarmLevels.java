
package com.BlizzardArmory.model.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Swarm levels.
 */
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
     * Gets terran.
     *
     * @return the terran
     */
    public Terran getTerran() {
        return terran;
    }

    /**
     * Sets terran.
     *
     * @param terran the terran
     */
    public void setTerran(Terran terran) {
        this.terran = terran;
    }

    /**
     * Gets zerg.
     *
     * @return the zerg
     */
    public Zerg getZerg() {
        return zerg;
    }

    /**
     * Sets zerg.
     *
     * @param zerg the zerg
     */
    public void setZerg(Zerg zerg) {
        this.zerg = zerg;
    }

    /**
     * Gets protoss.
     *
     * @return the protoss
     */
    public Protoss getProtoss() {
        return protoss;
    }

    /**
     * Sets protoss.
     *
     * @param protoss the protoss
     */
    public void setProtoss(Protoss protoss) {
        this.protoss = protoss;
    }

}
