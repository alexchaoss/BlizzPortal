package com.BlizzardArmory.warcraft.talents.specializationdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Talent.
 */
public class Talent {

    @SerializedName("talent")
    @Expose
    private TalentInfo talent;
    @SerializedName("spell_tooltip")
    @Expose
    private SpellTooltip spellTooltip;

    /**
     * No args constructor for use in serialization
     */
    public Talent() {
    }

    /**
     * Instantiates a new Talent.
     *
     * @param talent       the talent
     * @param spellTooltip the spell tooltip
     */
    public Talent(TalentInfo talent, SpellTooltip spellTooltip) {
        super();
        this.talent = talent;
        this.spellTooltip = spellTooltip;
    }

    /**
     * Gets talent.
     *
     * @return the talent
     */
    public TalentInfo getTalent() {
        return talent;
    }

    /**
     * Sets talent.
     *
     * @param talent the talent
     */
    public void setTalent(TalentInfo talent) {
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
