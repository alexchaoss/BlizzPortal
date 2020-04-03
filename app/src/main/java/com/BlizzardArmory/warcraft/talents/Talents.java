package com.BlizzardArmory.warcraft.talents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Talents.
 */
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

    /**
     * Gets links.
     *
     * @return the links
     */
    public Links getLinks() {
        return links;
    }

    /**
     * Sets links.
     *
     * @param links the links
     */
    public void setLinks(Links links) {
        this.links = links;
    }

    /**
     * Gets specializations.
     *
     * @return the specializations
     */
    public List<Specialization> getSpecializations() {
        return specializations;
    }

    /**
     * Sets specializations.
     *
     * @param specializations the specializations
     */
    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }

    /**
     * Gets active specialization.
     *
     * @return the active specialization
     */
    public ActiveSpecialization getActiveSpecialization() {
        return activeSpecialization;
    }

    /**
     * Sets active specialization.
     *
     * @param activeSpecialization the active specialization
     */
    public void setActiveSpecialization(ActiveSpecialization activeSpecialization) {
        this.activeSpecialization = activeSpecialization;
    }

    /**
     * Gets character.
     *
     * @return the character
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Sets character.
     *
     * @param character the character
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

}
