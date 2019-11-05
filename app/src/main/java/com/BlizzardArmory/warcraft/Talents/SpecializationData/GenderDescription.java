package com.BlizzardArmory.warcraft.Talents.SpecializationData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
     * @param female
     * @param male
     */
    public GenderDescription(String male, String female) {
        super();
        this.male = male;
        this.female = female;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getFemale() {
        return female;
    }

    public void setFemale(String female) {
        this.female = female;
    }

}
