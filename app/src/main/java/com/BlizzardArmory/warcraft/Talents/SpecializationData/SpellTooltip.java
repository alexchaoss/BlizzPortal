
package com.BlizzardArmory.warcraft.Talents.SpecializationData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpellTooltip {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cast_time")
    @Expose
    private String castTime;
    @SerializedName("cooldown")
    @Expose
    private String cooldown;
    @SerializedName("power_cost")
    @Expose
    private String powerCost;
    @SerializedName("range")
    @Expose
    private String range;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SpellTooltip() {
    }

    /**
     * 
     * @param castTime
     * @param cooldown
     * @param description
     * @param range
     * @param powerCost
     */
    public SpellTooltip(String description, String castTime, String cooldown, String powerCost, String range) {
        super();
        this.description = description;
        this.castTime = castTime;
        this.cooldown = cooldown;
        this.powerCost = powerCost;
        this.range = range;
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

    public String getCooldown() {
        return cooldown;
    }

    public void setCooldown(String cooldown) {
        this.cooldown = cooldown;
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

}
