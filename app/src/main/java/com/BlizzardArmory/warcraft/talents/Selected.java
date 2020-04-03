package com.BlizzardArmory.warcraft.talents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Selected.
 */
public class Selected {

    @SerializedName("talent")
    @Expose
    private Talent talent;
    @SerializedName("spelltooltip")
    @Expose
    private SpellTooltip spellTooltip;

    /**
     * Gets talent.
     *
     * @return the talent
     */
    public Talent getTalent() {
        return talent;
    }

    /**
     * Sets talent.
     *
     * @param talent the talent
     */
    public void setTalent(Talent talent) {
        this.talent = talent;
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

}
