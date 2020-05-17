package com.BlizzardArmory.model.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Passive spell tooltip.
 */
public class PassiveSpellTooltip {

    @SerializedName("spell")
    @Expose
    private Spell spell;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cast_time")
    @Expose
    private String castTime;

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

}
