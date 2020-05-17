package com.BlizzardArmory.model.warcraft.talents.specializationdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Pvp talent.
 */
public class PvpTalent {

    @SerializedName("talent")
    @Expose
    private Talent talent;
    @SerializedName("spell_tooltip")
    @Expose
    private SpellTooltip spellTooltip;

    /**
     * No args constructor for use in serialization
     */
    public PvpTalent() {
    }

    /**
     * Instantiates a new Pvp talent.
     *
     * @param talent       the talent
     * @param spellTooltip the spell tooltip
     */
    public PvpTalent(Talent talent, SpellTooltip spellTooltip) {
        super();
        this.talent = talent;
        this.spellTooltip = spellTooltip;
    }

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
