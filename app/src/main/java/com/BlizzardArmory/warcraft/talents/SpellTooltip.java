package com.BlizzardArmory.warcraft.talents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpellTooltip {

    @SerializedName("spell")
    @Expose
    private Spell spell;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cast_time")
    @Expose
    private String castTime;
    @SerializedName("power_cost")
    @Expose
    private String powerCost;
    @SerializedName("range")
    @Expose
    private String range;
    @SerializedName("cooldown")
    @Expose
    private String cooldown;

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

    public String getPowerCost() {
        return powerCost;
    }

    public void setPowerCost(String powerCost) {
        this.powerCost = powerCost;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getCooldown() {
        return cooldown;
    }

    public void setCooldown(String cooldown) {
        this.cooldown = cooldown;
    }

}
