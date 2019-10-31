
package com.BlizzardArmory.warcraft.Talents.SpecializationData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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
     * @param level
     * @param talents
     */
    public TalentTier(int level, List<Talent> talents) {
        super();
        this.level = level;
        this.talents = talents;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Talent> getTalents() {
        return talents;
    }

    public void setTalents(List<Talent> talents) {
        this.talents = talents;
    }

}
