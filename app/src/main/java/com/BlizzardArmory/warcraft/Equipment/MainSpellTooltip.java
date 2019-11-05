package com.BlizzardArmory.warcraft.Equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainSpellTooltip {

    @SerializedName("spell")
    @Expose
    private Spell spell;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cast_time")
    @Expose
    private String castTime;
    @SerializedName("range")
    @Expose
    private String range;

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCastTime() {
        return castTime;
    }

    public void setCastTime(String castTime) {
        this.castTime = castTime;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

}
