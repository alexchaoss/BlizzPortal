package com.BlizzardArmory.model.warcraft.talents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Talent.
 */
public class Talent {

    @SerializedName("talent")
    @Expose
    private TalentName talent;
    @SerializedName("spell_tooltip")
    @Expose
    private SpellTooltip spellTooltip;

    /**
     * Gets talent.
     *
     * @return the talent
     */
    public TalentName getTalent() {
        return talent;
    }

    /**
     * Sets talent.
     *
     * @param talent the talent
     */
    public void setTalent(TalentName talent) {
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
