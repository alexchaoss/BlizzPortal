package com.BlizzardArmory.warcraft.Equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public SpellTooltip getSpellTooltip() {
        return spellTooltip;
    }

    public void setSpellTooltip(SpellTooltip spellTooltip) {
        this.spellTooltip = spellTooltip;
    }

    public boolean isIsDisplayHidden() {
        return isDisplayHidden;
    }

    public void setIsDisplayHidden(boolean isDisplayHidden) {
        this.isDisplayHidden = isDisplayHidden;
    }

}
