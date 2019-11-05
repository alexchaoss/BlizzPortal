package com.BlizzardArmory.warcraft.Equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectedEssence {

    @SerializedName("slot")
    @Expose
    private int slot;
    @SerializedName("rank")
    @Expose
    private int rank;
    @SerializedName("main_spell_tooltip")
    @Expose
    private MainSpellTooltip mainSpellTooltip;
    @SerializedName("passive_spell_tooltip")
    @Expose
    private PassiveSpellTooltip passiveSpellTooltip;
    @SerializedName("essence")
    @Expose
    private Essence essence;
    @SerializedName("media")
    @Expose
    private Media media;

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public MainSpellTooltip getMainSpellTooltip() {
        return mainSpellTooltip;
    }

    public void setMainSpellTooltip(MainSpellTooltip mainSpellTooltip) {
        this.mainSpellTooltip = mainSpellTooltip;
    }

    public PassiveSpellTooltip getPassiveSpellTooltip() {
        return passiveSpellTooltip;
    }

    public void setPassiveSpellTooltip(PassiveSpellTooltip passiveSpellTooltip) {
        this.passiveSpellTooltip = passiveSpellTooltip;
    }

    public Essence getEssence() {
        return essence;
    }

    public void setEssence(Essence essence) {
        this.essence = essence;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

}
