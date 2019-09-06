package com.BlizzardArmory.warcraft.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

public class AzeriteItem {


    @SerializedName("azeriteLevel")
    @Expose
    private Integer azeriteLevel;

    @SerializedName("azeriteExperience")
    @Expose
    private Integer azeriteExperience;

    @SerializedName("azeriteExperienceRemaining")
    @Expose
    private Integer azeriteExperienceRemaining;

    public Integer getAzeriteLevel() {
        return azeriteLevel;
    }

    public void setAzeriteLevel(Integer azeriteLevel) {
        this.azeriteLevel = azeriteLevel;
    }

    public Integer getAzeriteExperience() {
        return azeriteExperience;
    }

    public void setAzeriteExperience(Integer azeriteExperience) {
        this.azeriteExperience = azeriteExperience;
    }

    public Integer getAzeriteExperienceRemaining() {
        return azeriteExperienceRemaining;
    }

    public void setAzeriteExperienceRemaining(Integer azeriteExperienceRemaining) {
        this.azeriteExperienceRemaining = azeriteExperienceRemaining;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("azeriteLevel", azeriteLevel).append("azeriteExperience", azeriteExperience).append("azeriteExperienceRemaining", azeriteExperienceRemaining).toString();
    }

}