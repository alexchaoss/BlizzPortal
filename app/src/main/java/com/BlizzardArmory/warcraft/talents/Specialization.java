package com.BlizzardArmory.warcraft.talents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    public SpecializationName getSpecialization() {
        return specialization;
    }

    public void setSpecialization(SpecializationName specialization) {
        this.specialization = specialization;
    }

    public List<Talent> getTalents() {
        return talents;
    }

    public void setTalents(List<Talent> talents) {
        this.talents = talents;
    }

    public List<Glyph> getGlyphs() {
        return glyphs;
    }

    public void setGlyphs(List<Glyph> glyphs) {
        this.glyphs = glyphs;
    }

    public List<PvpTalentSlot> getPvpTalentSlots() {
        return pvpTalentSlots;
    }

    public void setPvpTalentSlots(List<PvpTalentSlot> pvpTalentSlots) {
        this.pvpTalentSlots = pvpTalentSlots;
    }

}
