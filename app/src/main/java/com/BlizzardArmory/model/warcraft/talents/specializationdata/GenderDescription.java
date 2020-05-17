package com.BlizzardArmory.model.warcraft.talents.specializationdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Gender description.
 */
public class GenderDescription {

    @SerializedName("male")
    @Expose
    private String male;
    @SerializedName("female")
    @Expose
    private String female;

    /**
     * No args constructor for use in serialization
     */
    public GenderDescription() {
    }

    /**
     * Instantiates a new Gender description.
     *
     * @param male   the male
     * @param female the female
     */
    public GenderDescription(String male, String female) {
        super();
        this.male = male;
        this.female = female;
    }

    /**
     * Gets male.
     *
     * @return the male
     */
    public String getMale() {
        return male;
    }

    /**
     * Sets male.
     *
     * @param male the male
     */
    public void setMale(String male) {
        this.male = male;
    }

    /**
     * Gets female.
     *
     * @return the female
     */
    public String getFemale() {
        return female;
    }

    /**
     * Sets female.
     *
     * @param female the female
     */
    public void setFemale(String female) {
        this.female = female;
    }

}
