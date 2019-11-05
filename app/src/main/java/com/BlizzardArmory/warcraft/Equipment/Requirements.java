package com.BlizzardArmory.warcraft.Equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Requirements {

    @SerializedName("level")
    @Expose
    private Level level;
    @SerializedName("faction")
    @Expose
    private Faction faction;

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

}
