package com.BlizzardArmory.model.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Selected power.
 */
public class SelectedPower {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("tier")
    @Expose
    private int tier;
    @SerializedName("spell_tooltip")
    @Expose
    private SpellTooltip spellTooltip;
    @SerializedName("is_display_hidden")
    @Expose
    private boolean isDisplayHidden;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets tier.
     *
     * @return the tier
     */
    public int getTier() {
        return tier;
    }

    /**
     * Sets tier.
     *
     * @param tier the tier
     */
    public void setTier(int tier) {
        this.tier = tier;
    }

    /**
     * Gets spell tooltip.
     *
     * @return the spell tooltip
     */
    public SpellTooltip getSpellTooltip() {
        return spellTooltip;
    }

    /**
     * Sets spell tooltip.
     *
     * @param spellTooltip the spell tooltip
     */
    public void setSpellTooltip(SpellTooltip spellTooltip) {
        this.spellTooltip = spellTooltip;
    }

    /**
     * Is is display hidden boolean.
     *
     * @return the boolean
     */
    public boolean isIsDisplayHidden() {
        return isDisplayHidden;
    }

    /**
     * Sets is display hidden.
     *
     * @param isDisplayHidden the is display hidden
     */
    public void setIsDisplayHidden(boolean isDisplayHidden) {
        this.isDisplayHidden = isDisplayHidden;
    }

}
