package com.BlizzardArmory.model.warcraft.talents.specializationdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Spell tooltip.
 */
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
     */
    public SpellTooltip() {
    }

    /**
     * Instantiates a new Spell tooltip.
     *
     * @param description the description
     * @param castTime    the cast time
     * @param cooldown    the cooldown
     * @param powerCost   the power cost
     * @param range       the range
     */
    public SpellTooltip(String description, String castTime, String cooldown, String powerCost, String range) {
        super();
        this.description = description;
        this.castTime = castTime;
        this.cooldown = cooldown;
        this.powerCost = powerCost;
        this.range = range;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets cast time.
     *
     * @return the cast time
     */
    public String getCastTime() {
        return castTime;
    }

    /**
     * Sets cast time.
     *
     * @param castTime the cast time
     */
    public void setCastTime(String castTime) {
        this.castTime = castTime;
    }

    /**
     * Gets cooldown.
     *
     * @return the cooldown
     */
    public String getCooldown() {
        return cooldown;
    }

    /**
     * Sets cooldown.
     *
     * @param cooldown the cooldown
     */
    public void setCooldown(String cooldown) {
        this.cooldown = cooldown;
    }

    /**
     * Gets power cost.
     *
     * @return the power cost
     */
    public String getPowerCost() {
        return powerCost;
    }

    /**
     * Sets power cost.
     *
     * @param powerCost the power cost
     */
    public void setPowerCost(String powerCost) {
        this.powerCost = powerCost;
    }

    /**
     * Gets range.
     *
     * @return the range
     */
    public String getRange() {
        return range;
    }

    /**
     * Sets range.
     *
     * @param range the range
     */
    public void setRange(String range) {
        this.range = range;
    }

}
