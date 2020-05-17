package com.BlizzardArmory.model.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Selected essence.
 */
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

    /**
     * Gets slot.
     *
     * @return the slot
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Sets slot.
     *
     * @param slot the slot
     */
    public void setSlot(int slot) {
        this.slot = slot;
    }

    /**
     * Gets rank.
     *
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * Sets rank.
     *
     * @param rank the rank
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * Gets main spell tooltip.
     *
     * @return the main spell tooltip
     */
    public MainSpellTooltip getMainSpellTooltip() {
        return mainSpellTooltip;
    }

    /**
     * Sets main spell tooltip.
     *
     * @param mainSpellTooltip the main spell tooltip
     */
    public void setMainSpellTooltip(MainSpellTooltip mainSpellTooltip) {
        this.mainSpellTooltip = mainSpellTooltip;
    }

    /**
     * Gets passive spell tooltip.
     *
     * @return the passive spell tooltip
     */
    public PassiveSpellTooltip getPassiveSpellTooltip() {
        return passiveSpellTooltip;
    }

    /**
     * Sets passive spell tooltip.
     *
     * @param passiveSpellTooltip the passive spell tooltip
     */
    public void setPassiveSpellTooltip(PassiveSpellTooltip passiveSpellTooltip) {
        this.passiveSpellTooltip = passiveSpellTooltip;
    }

    /**
     * Gets essence.
     *
     * @return the essence
     */
    public Essence getEssence() {
        return essence;
    }

    /**
     * Sets essence.
     *
     * @param essence the essence
     */
    public void setEssence(Essence essence) {
        this.essence = essence;
    }

    /**
     * Gets media.
     *
     * @return the media
     */
    public Media getMedia() {
        return media;
    }

    /**
     * Sets media.
     *
     * @param media the media
     */
    public void setMedia(Media media) {
        this.media = media;
    }

}
