package com.BlizzardArmory.warcraft.talents.specializationdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Talent tier.
 */
public class TalentTier {

    @SerializedName("level")
    @Expose
    private int level;
    @SerializedName("talents")
    @Expose
    private List<Talent> talents = null;

    /**
     * No args constructor for use in serialization
     */
    public TalentTier() {
    }

    /**
     * Instantiates a new Talent tier.
     *
     * @param level   the level
     * @param talents the talents
     */
    public TalentTier(int level, List<Talent> talents) {
        super();
        this.level = level;
        this.talents = talents;
    }

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
     * Gets talents.
     *
     * @return the talents
     */
    public List<Talent> getTalents() {
        return talents;
    }

    /**
     * Sets talents.
     *
     * @param talents the talents
     */
    public void setTalents(List<Talent> talents) {
        this.talents = talents;
    }

}
