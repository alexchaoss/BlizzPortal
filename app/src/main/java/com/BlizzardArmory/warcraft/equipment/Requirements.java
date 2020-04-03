package com.BlizzardArmory.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Requirements.
 */
public class Requirements {

    @SerializedName("level")
    @Expose
    private Level level;
    @SerializedName("faction")
    @Expose
    private Faction faction;

    /**
     * Gets level.
     *
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * Gets faction.
     *
     * @return the faction
     */
    public Faction getFaction() {
        return faction;
    }

    /**
     * Sets faction.
     *
     * @param faction the faction
     */
    public void setFaction(Faction faction) {
        this.faction = faction;
    }

}
