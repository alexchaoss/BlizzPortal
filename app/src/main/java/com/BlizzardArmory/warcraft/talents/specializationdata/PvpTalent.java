package com.BlizzardArmory.warcraft.talents.specializationdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
     * @param talent
     * @param spellTooltip
     */
    public PvpTalent(Talent talent, SpellTooltip spellTooltip) {
        super();
        this.talent = talent;
        this.spellTooltip = spellTooltip;
    }

    public Talent getTalent() {
        return talent;
    }

    public void setTalent(Talent talent) {
        this.talent = talent;
    }

    public SpellTooltip getSpellTooltip() {
        return spellTooltip;
    }

    public void setSpellTooltip(SpellTooltip spellTooltip) {
        this.spellTooltip = spellTooltip;
    }

}
