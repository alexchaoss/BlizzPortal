package com.BlizzardArmory.warcraft.Talents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Talents {

    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("specializations")
    @Expose
    private List<Specialization> specializations = null;
    @SerializedName("active_specialization")
    @Expose
    private ActiveSpecialization activeSpecialization;
    @SerializedName("character")
    @Expose
    private Character character;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }

    public ActiveSpecialization getActiveSpecialization() {
        return activeSpecialization;
    }

    public void setActiveSpecialization(ActiveSpecialization activeSpecialization) {
        this.activeSpecialization = activeSpecialization;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

}
