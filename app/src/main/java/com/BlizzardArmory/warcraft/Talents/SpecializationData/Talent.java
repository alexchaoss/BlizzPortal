
package com.BlizzardArmory.warcraft.Talents.SpecializationData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Talent {

    @SerializedName("talent")
    @Expose
    private TalentInfo talent;
    @SerializedName("spell_tooltip")
    @Expose
    private SpellTooltip spellTooltip;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Talent() {
    }

    /**
     * 
     * @param talent
     * @param spellTooltip
     */
    public Talent(TalentInfo talent, SpellTooltip spellTooltip) {
        super();
        this.talent = talent;
        this.spellTooltip = spellTooltip;
    }

    public TalentInfo getTalent() {
        return talent;
    }

    public void setTalent(TalentInfo talent) {
        this.talent = talent;
    }

    public SpellTooltip getSpellTooltip() {
        return spellTooltip;
    }

    public void setSpellTooltip(SpellTooltip spellTooltip) {
        this.spellTooltip = spellTooltip;
    }

}
