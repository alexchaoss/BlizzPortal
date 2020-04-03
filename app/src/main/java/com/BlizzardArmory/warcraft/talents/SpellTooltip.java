package com.BlizzardArmory.warcraft.talents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Spell tooltip.
 */
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

    /**
     * Gets spell.
     *
     * @return the spell
     */
    public Spell getSpell() {
        return spell;
    }

    /**
     * Sets spell.
     *
     * @param spell the spell
     */
    public void setSpell(Spell spell) {
        this.spell = spell;
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

}
