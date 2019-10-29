
package com.BlizzardArmory.warcraft.Talents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Talent {

    @SerializedName("talent")
    @Expose
    private TalentName talent;
    @SerializedName("spell_tooltip")
    @Expose
    private SpellTooltip spellTooltip;

    public TalentName getTalent() {
        return talent;
    }

    public void setTalent(TalentName talent) {
        this.talent = talent;
    }

    public SpellTooltip getSpellTooltip() {
        return spellTooltip;
    }

    public void setSpellTooltip(SpellTooltip spellTooltip) {
        this.spellTooltip = spellTooltip;
    }

}
