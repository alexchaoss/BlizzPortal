package com.BlizzardArmory.warcraft.talents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Selected {

    @SerializedName("talent")
    @Expose
    private Talent talent;
    @SerializedName("spelltooltip")
    @Expose
    private SpellTooltip spellTooltip;

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
