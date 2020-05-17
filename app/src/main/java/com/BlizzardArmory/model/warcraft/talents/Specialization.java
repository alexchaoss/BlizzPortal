package com.BlizzardArmory.model.warcraft.talents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Specialization.
 */
public class Specialization {

    @SerializedName("specialization")
    @Expose
    private SpecializationName specialization;
    @SerializedName("talents")
    @Expose
    private List<Talent> talents = null;
    @SerializedName("glyphs")
    @Expose
    private List<Glyph> glyphs = null;
    @SerializedName("pvp_talent_slots")
    @Expose
    private List<PvpTalentSlot> pvpTalentSlots = null;

    /**
     * Gets specialization.
     *
     * @return the specialization
     */
    public SpecializationName getSpecialization() {
        return specialization;
    }

    /**
     * Sets specialization.
     *
     * @param specialization the specialization
     */
    public void setSpecialization(SpecializationName specialization) {
        this.specialization = specialization;
    }

    /**
     * Gets talents.
     *
     * @return the talents
     */
    public List<Talent> getTalents() {
        return talents;
    }

    /**
     * Sets talents.
     *
     * @param talents the talents
     */
    public void setTalents(List<Talent> talents) {
        this.talents = talents;
    }

    /**
     * Gets glyphs.
     *
     * @return the glyphs
     */
    public List<Glyph> getGlyphs() {
        return glyphs;
    }

    /**
     * Sets glyphs.
     *
     * @param glyphs the glyphs
     */
    public void setGlyphs(List<Glyph> glyphs) {
        this.glyphs = glyphs;
    }

    /**
     * Gets pvp talent slots.
     *
     * @return the pvp talent slots
     */
    public List<PvpTalentSlot> getPvpTalentSlots() {
        return pvpTalentSlots;
    }

    /**
     * Sets pvp talent slots.
     *
     * @param pvpTalentSlots the pvp talent slots
     */
    public void setPvpTalentSlots(List<PvpTalentSlot> pvpTalentSlots) {
        this.pvpTalentSlots = pvpTalentSlots;
    }

}
